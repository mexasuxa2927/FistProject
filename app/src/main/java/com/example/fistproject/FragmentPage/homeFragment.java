package com.example.fistproject.FragmentPage;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.fistproject.R;
import com.example.fistproject.adapter.TabStripAdapter;
import com.google.android.material.tabs.TabLayout;


public class homeFragment extends Fragment {

    TabLayout tabLayout;
    ViewPager2 viewPager2;
    TabStripAdapter adapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_home, container, false);

        tabLayout  = view.findViewById(R.id.pagerTablay);
        viewPager2 = view.findViewById(R.id.viewPager);

        FragmentManager fm  = getActivity().getSupportFragmentManager();
        adapter  =  new TabStripAdapter(fm,getLifecycle());
        viewPager2.setAdapter(adapter);


        tabLayout.addTab(tabLayout.newTab().setText("Foods"));
        tabLayout.addTab(tabLayout.newTab().setText("Drinks"));
        tabLayout.addTab(tabLayout.newTab().setText("Sweets"));




        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {

            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager2.setCurrentItem(tab.getPosition());

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                tabLayout.selectTab(tabLayout.getTabAt(position));

            }
        });




        return view;


    }
}