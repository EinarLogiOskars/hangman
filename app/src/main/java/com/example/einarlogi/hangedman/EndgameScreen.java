package com.example.einarlogi.hangedman;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class EndgameScreen extends AppCompatActivity {

    Galgelogik gameLogic;

    SharedPreferences myPrefs;
    SharedPreferences.Editor editor;

    TextView condition;
    TextView word;
    TextView highScore;
    Button playAgain;
    Boolean gameWon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_endgame);

        myPrefs = getSharedPreferences("prefs", 0);




        condition = (TextView) findViewById(R.id.condition);
        word = (TextView) findViewById(R.id.word);
        highScore = (TextView) findViewById(R.id.highScore);
        playAgain = (Button) findViewById(R.id.playAgainBtn);


        if (myPrefs.getBoolean("condition", false)) {
            gameWon();
        }
        else {
            gameLost();
        }

        playAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent newGame = new Intent(getApplicationContext(), NewGame.class);
                startActivity(newGame);
            }
        });

    }

    public void gameWon() {
        condition.setText("You have won!");
        word.setText("The word was " + myPrefs.getString("word", "----"));
    }

    public void gameLost() {
        condition.setText("You have lost!");
        word.setText("The word was " + myPrefs.getString("word", "----"));
    }
}
