package com.example.einarlogi.hangedman;


import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;


public class TopFragment extends Fragment {

    ImageView imgView;
    TextView txtView;
    TextView usedLtrs;
    ImageButton imgBtn;
    private pictureChanger interfaceImpl;

    public interface pictureChanger {
        public void changePic(int i);
        public void changeText(String word);
        public void usedLetters(String letters);
        public void returnToMenu();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.interfaceImpl = (pictureChanger) context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_top, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        imgView = (ImageView) getView().findViewById(R.id.imageView);
        txtView = (TextView) getView().findViewById(R.id.textView);
        usedLtrs = (TextView) getView().findViewById(R.id.usedLettersField);
        imgBtn = (ImageButton) getView().findViewById(R.id.imageButton);

        imgBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                interfaceImpl.returnToMenu();
            }
        });


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

            case 7:
                imgView.setImageResource(R.drawable.new_game_pic);
                break;

            case 8:
                imgView.setImageResource(R.drawable.help);
                break;

            case 9:
                imgView.setImageResource(R.drawable.welcome);
                break;

        }
    }

    public void changeTextField(String word) {
        txtView.setText(word);
    }

    public void updateUsedLetters(String letters) { usedLtrs.append(" " + letters); }



}
