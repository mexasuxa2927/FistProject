package com.example.fistproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class Sign_up extends AppCompatActivity {

    private Button login;
    private Button signUpBtn;
    private FirebaseAuth mAuth;
    private EditText email_sign,password_sign;
    private CheckBox checkBox_sign;

    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseRef;


    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sing_up);
        login = findViewById(R.id.login);
        signUpBtn = findViewById(R.id.button);
        email_sign = findViewById(R.id.email);
        password_sign  = findViewById(R.id.password);
        mAuth = FirebaseAuth.getInstance();
        checkBox_sign = findViewById(R.id.checkbox_sign);
        firebaseDatabase = FirebaseDatabase.getInstance();

        databaseRef = firebaseDatabase.getReference("Users/");

        
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Sign_up.this,Login.class));
                finish();
            }
        });
        
        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(checkBox_sign.isChecked()==false){
                    Toast.makeText(Sign_up.this, "you must agree Terms of Service", Toast.LENGTH_SHORT).show();
                    checkBox_sign.setTextColor(getResources().getColor(R.color.red));
                }

                else{
                    String email ,password ;
                    email  = email_sign.getText().toString();
                    password = password_sign.getText().toString();
                    checkBox_sign.setTextColor(getResources().getColor(R.color.black));
                    CreateAccount(email,password);
                }
            }
        });
        
    }

    private void CreateAccount(String email,String password) {

        mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(Sign_up.this, "register is successful", Toast.LENGTH_SHORT).show();
                    Intent  intent = new Intent(Sign_up.this,Login.class);
                    startActivity(intent);
                    finish();

                }
                else
                {
                    Toast.makeText(Sign_up.this, "register is unsuccessful", Toast.LENGTH_SHORT).show();
                }

            }
        });
        



        
    }

}