package com.example.einarlogi.hangedman;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Help extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);

        TextView title = (TextView) findViewById(R.id.title);
        TextView about = (TextView) findViewById(R.id.about);

        about.setText(R.string.about_text);

        Button backBtn = (Button) findViewById(R.id.b_back);

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}
