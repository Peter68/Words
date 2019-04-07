package com.example.words;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import static com.example.words.R.*;
import static java.lang.Thread.currentThread;
import static java.lang.Thread.sleep;

public class MainActivity extends AppCompatActivity {
    TextView wordInput;
    TextView translateInput;
    Button btnClick;
    Button btnCheck;

    private HashMap<String, String> words;
    private List<String> wordsKeys;

    String selectedWordKey, translateOK, translateIn, message;

    private void getNewWord() {
        Random rand = new Random();
        selectedWordKey = wordsKeys.get(rand.nextInt(words.size()));
        wordInput.setText(selectedWordKey);
        translateOK = words.get(selectedWordKey);
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.activity_main);

        wordInput = (TextView) findViewById(R.id.wordInput);
        translateInput = (EditText) findViewById(R.id.translateInput);


        btnClick = (Button) findViewById(R.id.btnClick);
        btnClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BufferedReader br = new BufferedReader(new InputStreamReader(getResources().openRawResource(R.raw.dictionary)));
                Type mapType = new TypeToken<HashMap<String, String>>() {}.getType();
                words = new Gson().fromJson(br, mapType);
                wordsKeys = new ArrayList<String>(words.keySet());

                getNewWord();

            }


        });

        btnCheck = (Button) findViewById(id.btnCheck);

        btnCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                translateIn = translateInput.getText().toString();

                if (translateOK.compareToIgnoreCase(translateIn) == 0)
                    toastMessage("OK\n" + "\"" + translateOK + "\"" + " to prawidłowe tłumaczenie");
                else
                    toastMessage("ŻLE, prawidłowe tłumaczenie to: " + "\"" + translateOK + "\"");
                try {
                    Thread.sleep(1000); // 1 second
                } catch (InterruptedException ex) {
                    // handle error
                }
                getNewWord();

            }
        });
    }

    private void toastMessage(String message) {
        Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
    }


}
