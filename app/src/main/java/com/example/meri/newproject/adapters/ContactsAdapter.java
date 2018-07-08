package com.example.meri.newproject.adapters;


import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.meri.newproject.R;
import com.example.meri.newproject.holder.RecyclerViewHolder;
import com.example.meri.newproject.item.Contact;

import java.util.ArrayList;
import java.util.List;

public class ContactsAdapter extends RecyclerView.Adapter<RecyclerViewHolder>{

    public static final boolean CONTACTS_MODE = false;
    public static final boolean FAVORITES_MODE = true;

    private OnContactClickListener mOnContactClickListener;

    private List<Contact> mContacts;
    private SparseBooleanArray mFavoriteItems;

    private Context mContext;

    public ContactsAdapter(Context context) {
        mContext = context;
        mContacts = new ArrayList<>();
        mFavoriteItems = new SparseBooleanArray();
    }

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view,
                parent, false);

        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerViewHolder holder, int position) {
        Contact contact = mContacts.get(position);

        holder.getName().setText(contact.getName());
        holder.getNumber().setText(contact.getNumber());

        if(!getFavoritesMode(position) || getFavoriteItems().indexOfKey(position) < 0){
            holder.getFavorite().setImageResource(R.drawable.ic_star_border_black_24dp);
        } else {
            holder.getFavorite().setImageResource(R.drawable.ic_star_black_24dp);
        }

        holder.setOnItemClickListener(new RecyclerViewHolder.OnItemClickListener() {
            @Override
            public void onStarClick(int position) {
                mOnContactClickListener.onAddFavorite(holder, position);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mContacts.size();
    }

    public Contact getContactsItem(int position){
        return mContacts.get(position);
    }

    public int getContactPosition(Contact contact){
        for (int i = 0; i < mContacts.size(); i++){
            if (contact.getId() == mContacts.get(i).getId()){
                return i;
            }
        }
        return 0;
    }

    public void setFavoritesMode(int position, boolean mode){
        mFavoriteItems.put(position, mode);
    }

    public boolean getFavoritesMode(int key){
        return mFavoriteItems.get(key);
    }

    public SparseBooleanArray getFavoriteItems() {
        return mFavoriteItems;
    }

    public void getContacts(){
        String id, name, number;

        ContentResolver cr = mContext.getContentResolver();
        Cursor cursor = cr.query(ContactsContract.Contacts.CONTENT_URI, null, null,
                null, null);

        if(cursor.getCount() > 0){
            while (cursor.moveToNext()) {
                id = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));
                name = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));

                if(Integer.parseInt(cursor.getString(cursor.getColumnIndex(ContactsContract
                        .Contacts.HAS_PHONE_NUMBER))) > 0){

                    Cursor phones = mContext.getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                                    null, ContactsContract.CommonDataKinds.Phone.CONTACT_ID +
                            " = " + id, null, null);

                    phones.moveToNext();
                    number = phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds
                                .Phone.NUMBER));
                    mContacts.add(new Contact(id, name, number));

                    phones.close();
                }
            }
        }
    }

    public void setContactClickListener(OnContactClickListener onContactClickListener){
        mOnContactClickListener = onContactClickListener;
    }

    public interface OnContactClickListener{
        void onAddFavorite(RecyclerViewHolder holder, int position);
    }
}
