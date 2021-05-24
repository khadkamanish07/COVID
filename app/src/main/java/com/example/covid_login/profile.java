package com.example.covid_login;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import javax.annotation.Nullable;

public class profile extends AppCompatActivity {
    TextView name_p, email_p, zip_p, phone_p,password_p;
    Button edit,back_main;
    Image image;
    FirebaseAuth fireAuth;
    FirebaseFirestore fstore;
    FirebaseUser user;
    String userid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        name_p = findViewById(R.id.name_profile);
        email_p = findViewById(R.id.email_profile);
        password_p=findViewById(R.id.password_profile);
        zip_p = findViewById(R.id.zip_profile);
        phone_p = findViewById(R.id.phone_profile);
        back_main =findViewById(R.id.back_button);
        //image = findViewById(R.id.image_profile);
        edit = findViewById(R.id.edit_button);
        fireAuth = FirebaseAuth.getInstance();
        fstore = FirebaseFirestore.getInstance();
        userid = fireAuth.getCurrentUser().getUid();
        user = fireAuth.getCurrentUser();

        DocumentReference docref = fstore.collection("Users").document(userid);
        docref.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                name_p.setText(documentSnapshot.getString("name"));
                email_p.setText(documentSnapshot.getString("email"));
                password_p.setText(documentSnapshot.getString("password"));
                zip_p.setText(documentSnapshot.getString("zipcode"));
                phone_p.setText(documentSnapshot.getString("phone"));

            }
        });

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent pass = new Intent(view.getContext(), personal.class);
                pass.putExtra("name",name_p.getText().toString());
                pass.putExtra("email",email_p.getText().toString());
                pass.putExtra("password",password_p.getText().toString());
                pass.putExtra("zipcode",zip_p.getText().toString());
                pass.putExtra("phone",phone_p.getText().toString());
                startActivity(pass);

            }
        });


        back_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(profile.this,MainActivity.class));
            }
        });

    }
}