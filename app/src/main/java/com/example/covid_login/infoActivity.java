package com.example.covid_login;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class infoActivity extends AppCompatActivity {

    TextView aboutView, description1View, symptomsView, description2View,link1View,link2View;
    Button backbtn;
    ScrollView scrollView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);


        aboutView = findViewById(R.id.aboutView);
        description1View = findViewById(R.id.description1View);
        symptomsView = findViewById(R.id.symptomsView);
        description2View = findViewById(R.id.description2View);
        scrollView = findViewById(R.id.scrollView);
        link1View= findViewById(R.id.link1View);
        link2View= findViewById(R.id.link2View);
        backbtn = (Button) findViewById(R.id.back_button);

        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(infoActivity.this,MainActivity.class));
            }
        });

        link1View.setMovementMethod(LinkMovementMethod.getInstance());
        link2View.setMovementMethod(LinkMovementMethod.getInstance());

    }


}