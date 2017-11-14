package com.example.einarlogi.hangedman;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;

public class HighScore extends AppCompatActivity {


    SharedPreferences myPrefs;
    ListView highScores;
    Button back;

    String[] names = {"","","","","","","","","",""};
    int[] scores = {0,0,0,0,0,0,0,0,0,0};
    String[] highScoreList = {"","","","","","","","","",""};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_high_score);

        myPrefs = getSharedPreferences("prefs", 0);
        for (int i = 0; i < 10; i++){
            String score = "score" + i;
            scores[i] = myPrefs.getInt(score, 8);
            String name = "name" + i;
            names[i] = myPrefs.getString(name, "");
            if (scores[i] == 8) highScoreList[i] = i + 1 + ". No score registered.";
            else if (!names[i].equals(""))highScoreList[i] = i + 1 + ". Failed guesses : " + scores[i] + "    -    " + names[i];
        }

        ListAdapter highScoreAdapter = new ArrayAdapter<String>(this, R.layout.high_score_items, highScoreList);
        highScores = (ListView) findViewById(R.id.highScoreList);
        highScores.setAdapter(highScoreAdapter);


        back = (Button) findViewById(R.id.backBtn);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });




    }
}
