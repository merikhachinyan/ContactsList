package com.example.meri.newproject.fragments;

import android.Manifest;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.meri.newproject.R;
import com.example.meri.newproject.adapters.ContactsAdapter;
import com.example.meri.newproject.holder.RecyclerViewHolder;
import com.example.meri.newproject.item.Contact;
import com.example.meri.newproject.model.ContactsViewModel;

public class ContactsFragment extends Fragment {

    private static final int PERMISSIONS_REQUEST_READ_CONTACTS = 100;

    private ContactsAdapter.OnContactClickListener mOnItemListener =
            new ContactsAdapter.OnContactClickListener() {
                @Override
                public void onAddFavorite(RecyclerViewHolder holder, int position) {

                    if(!mAdapter.getFavoritesMode(position) || mAdapter.getFavoriteItems()
                            .indexOfKey(position) < 0){

                        mAdapter.setFavoritesMode(position, ContactsAdapter.FAVORITES_MODE);
                        holder.getFavorite().setImageResource(R.drawable.ic_star_black_24dp);
                        mModel.setSelectedContact(mAdapter.getContactsItem(position));
                    } else {
                        mAdapter.setFavoritesMode(position, ContactsAdapter.CONTACTS_MODE);
                        holder.getFavorite().setImageResource(R.drawable.ic_star_border_black_24dp);
                        mModel.setSelectedContact(mAdapter.getContactsItem(position));
                    }
                }
            };

    private RecyclerView mRecyclerView;
    private ContactsAdapter mAdapter;

    private ContactsViewModel mModel;

    public ContactsFragment() {
    }

    private void init(View view){
        mRecyclerView = view.findViewById(R.id.view_contacts_fragment_recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        mAdapter = new ContactsAdapter(getContext());
        mAdapter.setContactClickListener(mOnItemListener);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_contacts, container, false);

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        if(requestCode == PERMISSIONS_REQUEST_READ_CONTACTS){
            if(grantResults[0] == PackageManager.PERMISSION_GRANTED){
                Toast.makeText(getContext(), Integer.toString(mAdapter.getItemCount()), Toast.LENGTH_SHORT).show();
                //showContacts();
            } else {
                Toast.makeText(getContext(), "This app need permission",
                        Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        init(view);

        mModel = ViewModelProviders.of(getActivity()).get(ContactsViewModel.class);
        mModel.getFavoriteContact().observe(this, new Observer<Contact>() {
            @Override
            public void onChanged(@Nullable Contact contact) {
                if (contact != null){
                    int position = mAdapter.getContactPosition(contact);
                    mAdapter.setFavoritesMode(position, ContactsAdapter.CONTACTS_MODE);
                    mAdapter.notifyDataSetChanged();
                }
            }
        });
        showContacts();
    }

    private void showContacts(){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M &&
                getContext().checkSelfPermission(Manifest.permission.READ_CONTACTS)
                        != PackageManager.PERMISSION_GRANTED){

            requestPermissions(new String[]{Manifest.permission.READ_CONTACTS}, PERMISSIONS_REQUEST_READ_CONTACTS);
        } else {
            mAdapter.getContacts();
            mRecyclerView.setAdapter(mAdapter);
        }
    }
}
