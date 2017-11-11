package com.example.einarlogi.hangedman;


import android.content.Context;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class BottomFragment extends Fragment {

    private letterChooser interfaceImpl;
    InputMethodManager inputManager;

    public interface letterChooser {
        public void chooseLetter(String letter);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.interfaceImpl = (letterChooser)context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_bottom, container, false);

    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Button button = (Button)getView().findViewById(R.id.select);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText editText = (EditText)getView().findViewById(R.id.editText);
                if (editText.getText().toString().length() == 1) {
                    interfaceImpl.chooseLetter(editText.getText().toString());
                }
                else {
                    Toast.makeText(getActivity(), "You can only choose one letter", Toast.LENGTH_SHORT).show();
                }
                editText.setText("");


                inputManager = (InputMethodManager)
                        getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);

                inputManager.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(),
                        InputMethodManager.HIDE_NOT_ALWAYS);
            }

        });

    }


}
