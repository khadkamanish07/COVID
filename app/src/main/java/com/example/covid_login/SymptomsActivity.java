package com.example.covid_login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SymptomsActivity extends AppCompatActivity {

    private TextView symptomsView,questions;
    private Button trueB, falseB, goback;
    private boolean answers;
    private int score=0;
    private int questionNum = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_symptoms);

        symptomsView=(TextView) findViewById(R.id.symptomsNumber);
        questions=(TextView) findViewById(R.id.startQuestion);
        trueB=(Button) findViewById(R.id.yesButton);
        falseB=(Button) findViewById(R.id.noButton);
        goback = (Button) findViewById(R.id.go_back);
        nextQuestion();

        goback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
            }
        });
        trueB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
    if(view==trueB)
    {
        score++;
        nextQuestion(score);

        if(questionNum == Symptoms.symptoms.length)
        {
            Intent i = new Intent(SymptomsActivity.this, resultActivity.class);
            Bundle bundle = new Bundle();
            bundle.putInt("finalSymptom", score);
            i.putExtras(bundle);
            SymptomsActivity.this.finish();
            startActivity(i);
        }
        else
        {
            nextQuestion();
        }
    }
    else
    {
        if(questionNum == Symptoms.symptoms.length)
        {
            Intent i = new Intent(SymptomsActivity.this, resultActivity.class);
            Bundle bundle = new Bundle();
            bundle.putInt("finalSymptom", score);
            i.putExtras(bundle);
            SymptomsActivity.this.finish();
            startActivity(i);
        }
        else
        {
            nextQuestion();
        }
    }
    }
        });




         falseB.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if(view==falseB)
            {

                nextQuestion(score);

                if(questionNum == Symptoms.symptoms.length)
                {
                    Intent i = new Intent(SymptomsActivity.this, resultActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putInt("finalSymptom", score);
                    i.putExtras(bundle);
                    SymptomsActivity.this.finish();
                    startActivity(i);
                }
                else
                {
                    nextQuestion();
                }
            }
            else
            {
                if(questionNum == Symptoms.symptoms.length)
                {
                    Intent i = new Intent(SymptomsActivity.this, resultActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putInt("finalSymptom", score);
                    i.putExtras(bundle);
                    SymptomsActivity.this.finish();
                    startActivity(i);
                }
                else
                {
                    nextQuestion();
                }
            }
        }

});

    }

    private void  nextQuestion()
    {
         questions.setText(Symptoms.symptoms[questionNum]);
         questionNum++;
    }
    public void nextQuestion(int point)
    {
        symptomsView.setText(""+ score);
    }
}