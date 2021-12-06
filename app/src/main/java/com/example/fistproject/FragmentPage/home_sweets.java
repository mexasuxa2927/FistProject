package com.example.fistproject.FragmentPage;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.fistproject.R;
import com.example.fistproject.adapter.CustomAdapter;
import com.example.fistproject.classRecive.ReciveData;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class home_sweets extends Fragment {

    private RecyclerView recyclerView;
    private CustomAdapter customAdapter;
    private DatabaseReference myRef;
    private ArrayList<ReciveData> reciveData;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view  = inflater.inflate(R.layout.fragment_home_sweets, container, false);
        recyclerView  = view.findViewById(R.id.RecyclerFragment_home_sweet);
        myRef  = FirebaseDatabase.getInstance().getReference();
        reciveData  = new ArrayList<>();
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL,false));
        recyclerView.setHasFixedSize(true);

        GetDataFrom();

        return view;

    }

    private void GetDataFrom() {
        Query query  = myRef.child("Sweets");

        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                reciveData.clear();
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    ReciveData data  = new ReciveData();
                    data.setNamedata(dataSnapshot.child("name").getValue().toString());
                    data.setImageLink(dataSnapshot.child("link").getValue().toString());
                    data.setDiscription(dataSnapshot.child("disc").getValue().toString());
                    data.setId_item(dataSnapshot.getKey());
                    reciveData.add(data);


                }
                customAdapter  = new CustomAdapter(getActivity(),getActivity(),reciveData,true,false);
                recyclerView.setAdapter(customAdapter);
                customAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}