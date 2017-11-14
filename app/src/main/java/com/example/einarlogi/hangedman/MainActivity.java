package com.example.einarlogi.hangedman;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class MainActivity extends AppCompatActivity {

    Button newGameBtn;
    Button highScore;
    Button helpBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        newGameBtn = (Button) findViewById(R.id.newGameBtn);
        newGameBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent newgame = new Intent(getApplicationContext(), NewGame.class);
                startActivity(newgame);
            }
        });

        highScore = (Button) findViewById(R.id.highScoreBtn);
        highScore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent highScore = new Intent(getApplicationContext(), HighScore.class);
                startActivity(highScore);
            }
        });

        helpBtn = (Button) findViewById(R.id.helpBtn);
        helpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent help = new Intent(getApplicationContext(), Help.class);
                startActivity(help);
            }
        });

    }





}
