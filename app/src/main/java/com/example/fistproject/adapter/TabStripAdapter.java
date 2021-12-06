package com.example.fistproject.adapter;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.fistproject.FragmentPage.home_drinks;
import com.example.fistproject.FragmentPage.home_foods;
import com.example.fistproject.FragmentPage.home_sweets;
import com.example.fistproject.R;

public class TabStripAdapter extends FragmentStateAdapter {


    public TabStripAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 1 :
                return new home_drinks();
            case 2 :
                return new home_sweets();


        }

        return new home_foods();

    }

    @Override
    public int getItemCount() {
        return 3;
    }
}

