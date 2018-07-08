package com.example.meri.newproject.adapters;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.meri.newproject.fragments.ContactsFragment;
import com.example.meri.newproject.fragments.FavoritesFragment;

public class ContactsPagerAdapter extends FragmentPagerAdapter{

    private static final int FRAGMENTS_COUNT = 2;
    private static final String CONTACTS_TAB = "Contacts";
    private static final String FAVORITES_TAB = "Favorites";

    public ContactsPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        if(position == 0){
            return new ContactsFragment();
        } else {
            return new FavoritesFragment();
        }
    }

    @Override
    public int getCount() {
        return FRAGMENTS_COUNT;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        if(position == 0){
            return CONTACTS_TAB;
        } else {
            return FAVORITES_TAB;
        }
    }
}
