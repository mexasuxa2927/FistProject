package com.example.fistproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class FistScreen extends AppCompatActivity {

    private Button startBtn;
    public  String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fist_screen);

        startBtn = findViewById(R.id.startbtn);

        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(FistScreen.this,Login.class));
            }
        });


    }


    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser firebaseUser  = FirebaseAuth.getInstance().getCurrentUser();
        if(firebaseUser!=null){
          startActivity(new Intent(FistScreen.this,MainActivity.class));
          finish();
        }

    }
}