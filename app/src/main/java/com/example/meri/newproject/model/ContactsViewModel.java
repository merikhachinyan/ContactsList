package com.example.meri.newproject.model;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.example.meri.newproject.item.Contact;


public class ContactsViewModel extends ViewModel{

    private final MutableLiveData<Contact> selectedContact =
            new MutableLiveData<>();

    private final MutableLiveData<Contact> favoriteContact =
            new MutableLiveData<>();

    public MutableLiveData<Contact> getSelectedContact(){
        return selectedContact;
    }

    public void setSelectedContact(Contact contact){
        selectedContact.setValue(contact);
    }

    public void setFavoriteContact(Contact contact){
        favoriteContact.setValue(contact);
    }

    public MutableLiveData<Contact> getFavoriteContact(){
        return favoriteContact;
    }
}

