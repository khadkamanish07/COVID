package com.example.covid_login;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class news extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        get_news();
    }
    private void get_news(){
       // String url = ""
    }
}