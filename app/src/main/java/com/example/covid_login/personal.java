package com.example.covid_login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class personal extends AppCompatActivity {
    EditText name_e,email_e,zip_e,phone_e,password_e;
    Button save,back;
    FirebaseAuth fireAuth;
    FirebaseFirestore fstore;
    FirebaseUser user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal);
        fireAuth = FirebaseAuth.getInstance();
        fstore = FirebaseFirestore.getInstance();
        user =fireAuth.getCurrentUser();

        Intent data= getIntent();
        final String name = data.getStringExtra("name");
        String email = data.getStringExtra("email");
        final String password=data.getStringExtra("password");
        String zip = data.getStringExtra("zipcode");
        String phone = data.getStringExtra("phone");


        name_e = findViewById(R.id.name_edit);
        email_e =findViewById(R.id.email_edit);
        password_e=findViewById(R.id.password_edit);
        zip_e = findViewById(R.id.zip_edit);
        phone_e = findViewById(R.id.phone_edit);
        save= findViewById(R.id.save_button);
        back = findViewById(R.id.back_button);

        name_e.setText(name);
        email_e.setText(email);
        password_e.setText(password);
        zip_e.setText(zip);
        phone_e.setText(phone);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(personal.this,profile.class));
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(name_e.getText().toString().isEmpty() || email_e.getText().toString().isEmpty()||password_e.getText().toString().isEmpty()|| zip_e.getText().toString().isEmpty()|| phone_e.getText().toString().isEmpty()) {
                    Toast.makeText(personal.this, "Fields cannot be empty", Toast.LENGTH_SHORT).show();
                    return;
                }
                final String email = email_e.getText().toString();
                final String pswd = password_e.getText().toString();
                user.updateEmail(email).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        DocumentReference docref = fstore.collection("Users").document(user.getUid());
                        Map<String,Object> edited = new HashMap<>();
                        edited.put("email",email);
                        edited.put("name",name_e.getText().toString());
                        edited.put("phone",phone_e.getText().toString());
                        edited.put("zipcode",zip_e.getText().toString());
                        docref.update(edited).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(personal.this,"Updated successfully.",Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(personal.this,profile.class));
                                finish();
                            }
                        });

                        Toast.makeText(personal.this,"Updated user profile.",Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(personal.this,e.getMessage(),Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });
    }
}