package com.example.einarlogi.hangedman;


import android.content.Context;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;


public class MainMenu extends Fragment {

    private mainMenu interfaceImpl;

    public interface mainMenu {
        public void newGame();
        public void help();
        public void highScore();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.interfaceImpl = (mainMenu) context;
    }

    public MainMenu() {
        // Required empty public constructor
    }

    public static MainMenu newInstance(String param1, String param2) {
        MainMenu fragment = new MainMenu();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main_menu, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ImageButton newGame = (ImageButton) getView().findViewById(R.id.newGameBtn);
        newGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                interfaceImpl.newGame();
            }
        });
    }
}
