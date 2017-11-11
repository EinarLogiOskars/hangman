package com.example.einarlogi.hangedman;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;


public class MainActivity extends AppCompatActivity implements BottomFragment.letterChooser, TopFragment.pictureChanger, MainMenu.mainMenu {

    private Galgelogik logic = new Galgelogik();
    BottomFragment bottomFragment;
    TopFragment topFrag;
    MainMenu mainMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainMenu = (MainMenu)getFragmentManager().findFragmentById(R.id.main_Menu);
        bottomFragment = (BottomFragment)getFragmentManager().findFragmentById(R.id.bottomFrag);
        topFrag = (TopFragment)getFragmentManager().findFragmentById(R.id.topFrag);

    }

    @Override
    public void newGame() {
        getFragmentManager().beginTransaction().hide(mainMenu).commit();
        logic.nulstil();
        changeText(logic.getSynligtOrd());
    }

    @Override
    public void returnToMenu() {
        getFragmentManager().beginTransaction().show(mainMenu).commit();
    }

    @Override
    public void help() {

    }

    @Override
    public void highScore() {

    }

    @Override
    public void chooseLetter(String letter) {

        logic.gætBogstav(letter);
        if (logic.erSidsteBogstavKorrekt()){
            changeText(logic.getSynligtOrd());
        }
        else {
            if (logic.erSpilletTabt()) {
                changeText("You have lost... The word was " + logic.getOrdet());
                letter = "";
            }
            changePic(logic.getAntalForkerteBogstaver());
            usedLetters(letter);
        }
        if (logic.erSpilletVundet()) {
            changeText("Congratulations you have won!! The word was " + logic.getOrdet());
        }
    }

    @Override
    public void changePic(int i) {
        topFrag.changePicture(i);
    }

    @Override
    public void changeText(String word) {
        topFrag.changeTextField(word);
    }

    @Override
    public void usedLetters(String letters) { topFrag.updateUsedLetters(letters); }


}
