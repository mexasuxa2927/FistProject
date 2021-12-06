package com.example.fistproject.FragmentPage;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.fistproject.R;
import com.example.fistproject.adapter.CustomAdapter;
import com.example.fistproject.classRecive.ReciveData;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class orderFragment extends Fragment {
    private RecyclerView recyclerView;
    private CustomAdapter customAdapter;
    private DatabaseReference myRef;
    private ArrayList<ReciveData> reciveData;
    private FirebaseUser user;
    private String username;
    private ImageButton clearDataOrder;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view  =inflater.inflate(R.layout.fragment_order, container, false);
        clearDataOrder = view.findViewById(R.id.clearData);
        recyclerView = view.findViewById(R.id.recyclerFragment_order);
        myRef  = FirebaseDatabase.getInstance().getReference();
        reciveData  = new ArrayList<>();
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(),RecyclerView.VERTICAL,false));
        recyclerView.setHasFixedSize(true);
        user= FirebaseAuth.getInstance().getCurrentUser();
        username  = user.getUid();
        GetDataFrom();


        clearDataOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myRef.child("oreder").child(username).removeValue();

            }
        });

        return view;
    }
    private void GetDataFrom() {
        Query query  = myRef.child("oreder").child(username);

        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                reciveData.clear();
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    ReciveData data  = new ReciveData();
                    data.setNamedata(dataSnapshot.child("name").getValue().toString());
                    data.setImageLink(dataSnapshot.child("link").getValue().toString());
                    data.setDiscription(dataSnapshot.child("disc").getValue().toString());
                    data.setId_item(dataSnapshot.getKey().toString());

                    reciveData.add(data);
                    Log.i("Message",""+data.getId_item());

                }
                customAdapter  = new CustomAdapter(getActivity(), getActivity(),reciveData,false,true);
                recyclerView.setAdapter(customAdapter);
                customAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}