package com.example.covid_login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class resultActivity extends AppCompatActivity {

    TextView Score, finalMessage;
    Button gobackButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        Score = (TextView) findViewById(R.id.message);
        finalMessage = (TextView) findViewById(R.id.finalResult);

        Bundle bundle = getIntent().getExtras();
        int sym = bundle.getInt("finalSymptom");

        finalMessage.setText("You have " + sym + " symptoms.");

        if (sym >= 5) {
            Score.setText("You have Corona.");
        } else {
            Score.setText("You do not have Corona.");
        }

        gobackButton = (Button) findViewById(R.id.backButton);
        gobackButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                startActivity(new Intent(resultActivity.this, MainActivity.class));
            }
        });
    }
}