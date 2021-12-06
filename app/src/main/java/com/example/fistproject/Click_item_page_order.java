package com.example.fistproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class Click_item_page_order extends AppCompatActivity {

    private ImageView imageView;
    private TextView nameText;
    private TextView   discText;
    private Button botton;
    private DatabaseReference myRef;
    private String username;
    private FirebaseUser user;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_click_item_page_order);

        imageView   = findViewById(R.id.imageView_click_item);
        nameText   =findViewById(R.id.name_item_click);
        discText  =findViewById(R.id.disc_click_item);
        botton  = findViewById(R.id.button_click_order_item);
        user = FirebaseAuth.getInstance().getCurrentUser();
        Intent intent  = getIntent();


        username = user.getUid();

        nameText.setText(intent.getStringExtra("name"));
        discText.setText(intent.getStringExtra("disc"));


        Glide.with(getApplicationContext()).load(intent.getStringExtra("link")).into(imageView);

        myRef  = FirebaseDatabase.getInstance().getReference("oreder").child(username);


        botton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                myRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                       myRef.child(intent.getStringExtra("id")).removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                           @Override
                           public void onComplete(@NonNull Task<Void> task) {
                               if(task.isSuccessful()){
                                   Toast.makeText(getApplicationContext(), "delete item", Toast.LENGTH_SHORT).show();
                                   startActivity(new Intent(Click_item_page_order.this,MainActivity.class));
                                   finish();
                               }
                           }
                       });

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }

                });

            }

        });

    }


}