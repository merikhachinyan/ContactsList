package com.example.meri.newproject;

import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.meri.newproject.adapters.ContactsPagerAdapter;
import com.example.meri.newproject.fragments.ContactsFragment;

public class MainActivity extends AppCompatActivity {

    private TabLayout mTabLayout;
    private ViewPager mViewPager;

    private ContactsPagerAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTabLayout = findViewById(R.id.view_main_activity_tab_layout);
        mViewPager = findViewById(R.id.view_main_activity_view_pager);

        mTabLayout.setupWithViewPager(mViewPager);
        mAdapter = new ContactsPagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(mAdapter);
    }
}
