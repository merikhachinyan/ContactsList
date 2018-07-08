package com.example.meri.newproject.item;

public class Contact {

    private String mId;
    private String mName;
    private String mNumber;

    public Contact(String id, String name, String number) {
        mId = id;
        mName = name;
        mNumber = number;
    }

    public String getId(){
        return mId;
    }
    public String getName() {
        return mName;
    }

    public String getNumber() {
        return mNumber;
    }

    public void setName(String name) {
        mName = name;
    }

    public void setNumber(String number) {
        mNumber = number;
    }

    public void setId(String id){
        mId = id;
    }
}
