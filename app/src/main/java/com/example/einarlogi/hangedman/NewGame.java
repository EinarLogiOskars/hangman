package com.example.einarlogi.hangedman;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;



public class NewGame extends AppCompatActivity {

    Galgelogik gameLogic;

    SharedPreferences myPrefs;
    SharedPreferences.Editor editor;

    int wrongGuesses;
    Button startBtn;
    Button returnBtn;
    ImageView imgView;
    TextView wordView;
    TextView usedLetters;
    EditText letterField;
    Button guessBtn;
    InputMethodManager inputManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newgame);

        gameLogic = new Galgelogik();

        class downloadWords extends AsyncTask {
            @Override
            protected Object doInBackground(Object... arg0) {
                try {
                    gameLogic.hentOrdFraDr();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return null;
            }
        }
        new downloadWords().execute();

        myPrefs = getSharedPreferences("prefs", 0);

        imgView = (ImageView) findViewById(R.id.imageView);
        wordView = (TextView) findViewById(R.id.wordView);
        usedLetters = (TextView) findViewById(R.id.usedLettersField);
        letterField = (EditText) findViewById(R.id.letterField);
        guessBtn = (Button) findViewById(R.id.guessBtn);
        returnBtn = (Button) findViewById(R.id.returnBtn);

        imgView.setVisibility(View.GONE);
        wordView.setVisibility(View.GONE);
        usedLetters.setVisibility(View.GONE);
        letterField.setVisibility(View.GONE);
        guessBtn.setVisibility(View.GONE);



        startBtn = (Button) findViewById(R.id.startBtn);

        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startBtn.setVisibility(View.GONE);
                imgView.setVisibility(View.VISIBLE);
                wordView.setVisibility(View.VISIBLE);
                usedLetters.setVisibility(View.VISIBLE);
                letterField.setVisibility(View.VISIBLE);
                guessBtn.setVisibility(View.VISIBLE);
                newGame();
            }
        });

        guessBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                guess();

            }
        });

        returnBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent mainMenu = new Intent(getApplicationContext(), MainActivity.class);
                //startActivity(mainMenu);
                finish();
            }
        });



    }

    public void newGame() {

        wrongGuesses = 0;
        gameLogic.nulstil();
        changeTextField(gameLogic.getSynligtOrd());
        imgView.setImageResource(R.drawable.galge);

        editor = myPrefs.edit();
        editor.putString("word", gameLogic.getOrdet());
        editor.apply();

    }

    public void guess() {

        String letter = letterField.getText().toString();
        if (letter.length() == 1 && !gameLogic.getBrugteBogstaver().contains(letter)){
            gameLogic.gætBogstav(letter);
            if (gameLogic.erSidsteBogstavKorrekt()) {changeTextField(gameLogic.getSynligtOrd());}
            else{
                wrongGuesses++;
                changePicture(wrongGuesses);
            }
            updateUsedLetters();
        }
        else if(letter.length() != 1) {
            Toast.makeText(getApplication(),"You must choose one letter and one only!", Toast.LENGTH_SHORT).show();
        }
        letterField.setText("");

        if (gameLogic.erSpilletSlut()) {

            if (gameLogic.erSpilletVundet()){
                editor = myPrefs.edit();
                editor.putBoolean("condition", true);
                editor.putInt("fails", wrongGuesses);
                editor.apply();
            }
            else if (gameLogic.erSpilletTabt()){
                editor = myPrefs.edit();
                editor.putBoolean("condition", false);
                editor.apply();
            }
            editor = myPrefs.edit();
            editor.putInt("fails", gameLogic.getAntalForkerteBogstaver());
            editor.apply();

            Intent gameOver = new Intent(getApplicationContext(), EndgameScreen.class);
            startActivity(gameOver);
            finish();

        }


        inputManager = (InputMethodManager)
                getApplication().getSystemService(Context.INPUT_METHOD_SERVICE);

        inputManager.hideSoftInputFromWindow(guessBtn.getWindowToken(),
                InputMethodManager.HIDE_NOT_ALWAYS);

    }

    public void changePicture(int i) {
        switch (i) {
            case 1:
                imgView.setImageResource(R.drawable.forkert1);
                break;

            case 2:
                imgView.setImageResource(R.drawable.forkert2);
                break;

            case 3:
                imgView.setImageResource(R.drawable.forkert3);
                break;

            case 4:
                imgView.setImageResource(R.drawable.forkert4);
                break;

            case 5:
                imgView.setImageResource(R.drawable.forkert5);
                break;

            case 6:
                imgView.setImageResource(R.drawable.forkert6);
                break;


        }
    }

    public void changeTextField(String word) {
        wordView.setText(word);
    }

    public void updateUsedLetters() {
        String letters = gameLogic.getBrugteBogstaver().toString();
        if (letters != null){
            letters = letters.substring(1, letters.length() - 1);
        }
        usedLetters.setText("Used letters : " + letters);
    }


}
