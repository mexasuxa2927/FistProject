package com.example.fistproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {

    private Button sign_up;
    private Button login;
    private FirebaseAuth mAuth;
    private EditText email_login,password_login;
    
    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        sign_up = findViewById(R.id.signup);
        login = findViewById(R.id.button);
        mAuth  = FirebaseAuth.getInstance();
        
        email_login = findViewById(R.id.email);
        password_login = findViewById(R.id.password);
        
        sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Login.this,Sign_up.class));
                finish();
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email,password;
                
                email = email_login.getText().toString();
                password = password_login.getText().toString();
                if(email.isEmpty()||password.isEmpty()){
                    Toast.makeText(Login.this, "email or password not fill", Toast.LENGTH_SHORT).show();
                }
                else{
                  loginEmailPassword(email,password);
                }
                
                
            }
        });

    }

    private void loginEmailPassword(String email,String password) {
        mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(Login.this, "login is successful", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(Login.this,MainActivity.class));
                    finish();

                }
                else{
                    Toast.makeText(Login.this, "login is't  successful", Toast.LENGTH_SHORT).show();

                }
            }
        });
    }
}