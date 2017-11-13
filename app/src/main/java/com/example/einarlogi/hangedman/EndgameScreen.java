package com.example.einarlogi.hangedman;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class EndgameScreen extends AppCompatActivity {

    Galgelogik gameLogic;
    TextView condition;
    TextView word;
    TextView highScore;
    Button playAgain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_endgame);


        condition = (TextView) findViewById(R.id.condition);
        word = (TextView) findViewById(R.id.word);
        highScore = (TextView) findViewById(R.id.highScore);
        playAgain = (Button) findViewById(R.id.playAgainBtn);



    }
}
