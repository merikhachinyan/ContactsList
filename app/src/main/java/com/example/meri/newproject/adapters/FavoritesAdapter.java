package com.example.meri.newproject.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.meri.newproject.R;
import com.example.meri.newproject.holder.RecyclerViewHolder;
import com.example.meri.newproject.item.Contact;

import java.util.ArrayList;
import java.util.List;

public class FavoritesAdapter extends RecyclerView.Adapter<RecyclerViewHolder>{

    private List<Contact> mFavoriteContacts;

    private OnAdapterItemClickListener mOnAdapterItemClickListener;

    public FavoritesAdapter() {
        mFavoriteContacts = new ArrayList<>();
    }

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view,
                parent, false);

        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {

        Contact contact = mFavoriteContacts.get(position);

        holder.getName().setText(contact.getName());
        holder.getNumber().setText(contact.getNumber());
        holder.getFavorite().setImageResource(R.drawable.ic_close_black_24dp);

        holder.setOnItemClickListener(new RecyclerViewHolder.OnItemClickListener() {
            @Override
            public void onStarClick(int position) {
                mOnAdapterItemClickListener.onRemoveItem(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mFavoriteContacts.size();
    }

    public void addItems(Contact contact){
        mFavoriteContacts.add(contact);
    }

    public boolean containsContact(Contact contact) {
        for (Contact it : mFavoriteContacts) {
            if (it.getId() == contact.getId()) {
                return true;
            }
        }
        return false;
    }

    public void removeContact(Contact contact){
        for (Contact it : mFavoriteContacts){
            if (it.getId() == contact.getId()){
                mFavoriteContacts.remove(it);
                break;
            }
        }
    }

    public List<Contact> getFavoriteContacts() {
        return mFavoriteContacts;
    }

    public Contact getFavoriteContact(int position){
        return mFavoriteContacts.get(position);
    }

    public void removeContact(int position){
        mFavoriteContacts.remove(position);
        notifyDataSetChanged();
    }

    public void setAdapterOnItemClickListener(OnAdapterItemClickListener onItemClickListener){
        mOnAdapterItemClickListener = onItemClickListener;
    }

    public interface OnAdapterItemClickListener{
        void onRemoveItem(int position);
    }
}