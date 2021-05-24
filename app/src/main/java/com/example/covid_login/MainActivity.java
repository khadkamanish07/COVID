package com.example.covid_login;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.firebase.auth.FirebaseAuth;
import com.leo.simplearcloader.SimpleArcLoader;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    TextView Cases,Active, Recovered,Critical,TodayCases,TotalDeaths,TodayDeaths;
    SimpleArcLoader simpleArcLoader;
    ScrollView scrollView;


    private static final String TAG = "MainActivity";
    private static final int ERROR_DIALOG_REQUEST = 9001;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Cases = findViewById(R.id.Cases);
        Recovered = findViewById(R.id.Recovered);
        Critical = findViewById(R.id.Critical);
        Active = findViewById(R.id.Active);
        TodayCases = findViewById(R.id.TodayCases);
        TotalDeaths = findViewById(R.id.TotalDeaths);
        TodayDeaths = findViewById(R.id.TodayDeaths);

        simpleArcLoader = findViewById(R.id.loader);
        scrollView = findViewById(R.id.scrollStats);




        if(check_compatible()){
            init();
        }
        fetchData();
    }


    private void fetchData(){
        String url = "https://disease.sh/v3/covid-19/all";

        simpleArcLoader.start();
        StringRequest request = new StringRequest(Request.Method.GET,url,
                new Response.Listener<String>(){
                    @Override
                    public void onResponse(String response) {

                        try {
                                JSONObject jsonObject = new JSONObject(response.toString());

                            Cases.setText(jsonObject.getString("cases"));

                            TotalDeaths.setText(jsonObject.getString("deaths"));
                            Recovered.setText(jsonObject.getString("recovered"));
                            Critical.setText(jsonObject.getString("critical"));
                            Active.setText(jsonObject.getString("active"));
                            TodayCases.setText(jsonObject.getString("todayCases"));
                            TodayDeaths.setText(jsonObject.getString("todayDeaths"));


                            simpleArcLoader.stop();
                            simpleArcLoader.setVisibility(View.GONE);
                            scrollView.setVisibility(View.VISIBLE);


                        } catch (JSONException e) {
                            e.printStackTrace();
                            simpleArcLoader.stop();
                            simpleArcLoader.setVisibility(View.GONE);
                            scrollView.setVisibility(View.VISIBLE);
                        }


                    }
                },new Response.ErrorListener(){
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        simpleArcLoader.stop();
                        simpleArcLoader.setVisibility(View.GONE);
                        scrollView.setVisibility(View.VISIBLE);
                        Toast.makeText(MainActivity.this,error.getMessage(),Toast.LENGTH_SHORT).show();
                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);


    }




    private void init(){
        Button btninfo = (Button) findViewById(R.id.info_button);
        btninfo.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, infoActivity.class));
            }
        });

        Button btnsymptom = (Button) findViewById(R.id.check_symptoms_button);
        btnsymptom.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(MainActivity.this, SymptomsActivity.class);
                startActivity(intent);
            }
        });

        Button btnmap = (Button) findViewById(R.id.maps_button);
        btnmap.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentn = new Intent(MainActivity.this,Map.class);
                startActivity(intentn);
            }
        });


    }


    //checking if the google service version on the phone is compatible
    public boolean check_compatible(){
        Log.d(TAG,"isServicesOK: checking google services version");
        int available = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(MainActivity.this);
        if(available == ConnectionResult.SUCCESS){
            //eveything is fine
            Log.d(TAG,"isServiesOK: Google Play Services is working");
            return true;
        }
        else if(GoogleApiAvailability.getInstance().isUserResolvableError((available)))
        {//solvable error occured
            Log.d(TAG,"isServicesOK: solvable error occured");
            Dialog dialog = GoogleApiAvailability.getInstance().getErrorDialog(MainActivity.this,available,ERROR_DIALOG_REQUEST);
            dialog.show();
        }
        else{
            Toast.makeText(this,"Map request denied.",Toast.LENGTH_SHORT).show();
        }
        return false;
    }

    public boolean onCreateOptionsMenu(Menu menu1){
        getMenuInflater().inflate(R.menu.menu,menu1);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item){
        if(item.getItemId() ==R.id.profile){
            startActivity(new Intent(MainActivity.this,profile.class));
        }
        if(item.getItemId()==R.id.AboutDevelopers){
           // startActivity(new Intent(MainActivity.this,developer.class));
        }
        if(item.getItemId()==R.id.logout){

                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(getApplicationContext(),login.class));
                finish();

        }
        return true ;
    }
    /*Button news_btn = (Button) findViewById(R.id.news_button);
    news_btn.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            startActivity(new Intent(getApplicationContext(), news.class));
        }
    });*/



}
