package com.example.fistproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.widget.FrameLayout;


import com.etebarian.meowbottomnavigation.MeowBottomNavigation;
import com.example.fistproject.FragmentPage.SettingFragment;
import com.example.fistproject.FragmentPage.homeFragment;
import com.example.fistproject.FragmentPage.orderFragment;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    private MeowBottomNavigation bottomNavigation;
    private static final int ID_HOME = 1;
    private static final int ID_NOTI = 3;
    private static final int ID_ORDER = 2;
    private DatabaseReference myRef;
    private FirebaseUser user;
    private FrameLayout frameLayout;
    private String username;
    private int item_count;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bottomNavigation = findViewById(R.id.meowBottomNavigation);
        frameLayout = findViewById(R.id.FrameLayout);

        user  = FirebaseAuth.getInstance().getCurrentUser();
        username = user.getUid();
        bottomNavigation.add(new MeowBottomNavigation.Model(ID_HOME, R.drawable.home_icon_nav_2));
        bottomNavigation.add(new MeowBottomNavigation.Model(ID_ORDER, R.drawable.order_icon_nav));
        bottomNavigation.add(new MeowBottomNavigation.Model(ID_NOTI, R.drawable.setting_icon_menu));

        myRef = FirebaseDatabase.getInstance().getReference("oreder").child(username);
        CountItem();


        bottomNavigation.setOnShowListener(new MeowBottomNavigation.ShowListener() {
            @Override
            public void onShowItem(MeowBottomNavigation.Model item) {
                Fragment fragment = null;

                switch (item.getId()) {
                    case 1:{
                        fragment = new homeFragment();
                        CountItem();
                        break;}
                    case 2:{
                        fragment = new orderFragment();
                        CountItem();
                        break;}
                    case 3: {
                        fragment = new SettingFragment();
                        CountItem();
                        break;
                    }
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.FrameLayout, fragment).commit();


            }
        });


        bottomNavigation.show(1 ,true);

        bottomNavigation.setOnClickMenuListener(new MeowBottomNavigation.ClickListener() {
            @Override
            public void onClickItem(MeowBottomNavigation.Model item) {


            }
        });

        bottomNavigation.setOnReselectListener(new MeowBottomNavigation.ReselectListener() {
            @Override
            public void onReselectItem(MeowBottomNavigation.Model item) {


            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                item_count  =(int) snapshot.getChildrenCount();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        bottomNavigation.setCount(2, String.valueOf(item_count));

    }
   private void CountItem(){
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                item_count  =(int) snapshot.getChildrenCount();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
       bottomNavigation.setCount(2, String.valueOf(item_count));
    }



}


