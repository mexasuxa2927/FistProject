package com.example.fistproject.FragmentPage;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.fistproject.FistScreen;
import com.example.fistproject.MainActivity;
import com.example.fistproject.R;
import com.google.firebase.auth.FirebaseAuth;

public class SettingFragment extends Fragment {

    private Button setting;
    private Button signOut;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_setting, container, false);

        setting  =view.findViewById(R.id.button_setting);
        signOut  =view.findViewById(R.id.button_singout);

        signOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                SettingFragment.this.startActivity(new Intent(getActivity(), FistScreen.class));
                getActivity().finish();
            }
        });

      return view;
    }
}