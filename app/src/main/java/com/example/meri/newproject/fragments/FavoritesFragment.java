package com.example.meri.newproject.fragments;


import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
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
import com.example.meri.newproject.adapters.FavoritesAdapter;
import com.example.meri.newproject.item.Contact;
import com.example.meri.newproject.model.ContactsViewModel;

public class FavoritesFragment extends Fragment {

    private FavoritesAdapter.OnAdapterItemClickListener mOnAdapterItemClickListener =
            new FavoritesAdapter.OnAdapterItemClickListener() {
                @Override
                public void onRemoveItem(int position) {
                    mModel.setFavoriteContact(mAdapter.getFavoriteContact(position));
                    mAdapter.removeContact(position);
                    Toast.makeText(getContext(), Integer.toString(position), Toast.LENGTH_SHORT).show();
                }
            };

    private RecyclerView mRecyclerView;
    private FavoritesAdapter mAdapter;

    private ContactsViewModel mModel;

    public FavoritesFragment() {
    }

    private void init(View view){
        mRecyclerView = view.findViewById(R.id.view_favorites_fragment_recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        mAdapter = new FavoritesAdapter();
        mRecyclerView.setAdapter(mAdapter);

        mAdapter.setAdapterOnItemClickListener(mOnAdapterItemClickListener);

        mModel = ViewModelProviders.of(getActivity()).get(ContactsViewModel.class);
        mModel.getSelectedContact().observe(this, new Observer<Contact>() {
            @Override
            public void onChanged(@Nullable Contact contact) {
                if(mAdapter.containsContact(contact)){
                    mAdapter.removeContact(contact);
                    mAdapter.notifyDataSetChanged();
                } else {
                    mAdapter.addItems(contact);
                    mAdapter.notifyDataSetChanged();
                }
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_favorites, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        init(view);
    }
}
