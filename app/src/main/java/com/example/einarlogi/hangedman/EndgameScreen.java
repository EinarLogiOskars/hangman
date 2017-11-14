package com.example.einarlogi.hangedman;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class EndgameScreen extends AppCompatActivity {

    SharedPreferences myPrefs;
    SharedPreferences.Editor editor;

    TextView condition;
    TextView word;
    TextView fails;
    TextView highScore;
    EditText hSname;
    String[] names = {"","","","","","","","","",""};
    int[] highScores = {0,0,0,0,0,0,0,0,0,0};
    int highScorePos;
    Button playAgain;
    Button submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_endgame);

        myPrefs = getSharedPreferences("prefs", 0);
        for (int i = 9; i >= 0; i--) {
            String score = "score" + i;
            highScores[i] = myPrefs.getInt(score, 6);
            String name = "name" + i;
            names[i] = myPrefs.getString("name", "");
            if (myPrefs.getInt("fails", 0) < highScores[i])highScorePos = i;
        }


        condition = (TextView) findViewById(R.id.condition);
        word = (TextView) findViewById(R.id.word);
        fails = (TextView) findViewById(R.id.fails);
        highScore = (TextView) findViewById(R.id.highScoreText);
        hSname = (EditText) findViewById(R.id.nameInput);
        playAgain = (Button) findViewById(R.id.playAgainBtn);
        submit = (Button) findViewById(R.id.submitBtn);


        if (myPrefs.getBoolean("condition", false)) gameWon(highScores);
        else gameLost();

        playAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent newGame = new Intent(getApplicationContext(), NewGame.class);
                startActivity(newGame);
                finish();
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i = 9; i > highScorePos; i--) {
                    editor = myPrefs.edit();
                    editor.putString("name" + i, myPrefs.getString("name" + (i - 1), ""));
                    editor.putInt("score" + i, myPrefs.getInt("score" + (i - 1), 8));
                    editor.apply();
                }
                editor = myPrefs.edit();
                editor.putString("name" + highScorePos, hSname.getText().toString());
                editor.putInt("score" + highScorePos, myPrefs.getInt("fails", 0));
                editor.apply();
                Intent highScore = new Intent(getApplicationContext(), HighScore.class);
                startActivity(highScore);
                finish();
            }
        });

    }

    public void gameWon(int[] highScores) {
        condition.setText(R.string.victory);
        word.setText("The word was " + myPrefs.getString("word", "----"));
        fails.setText("Failed guesses : " + myPrefs.getInt("fails", 0));
        fails.setVisibility(View.VISIBLE);
        for (int s : highScores) {
            if (s > myPrefs.getInt("fails", 0)){
                highScore.setVisibility(View.VISIBLE);
                hSname.setVisibility(View.VISIBLE);
                submit.setVisibility(View.VISIBLE);
            }
        }
    }

    public void gameLost() {
        condition.setText(R.string.defeat);
        word.setText("The word was " + myPrefs.getString("word", "----"));
    }
}
