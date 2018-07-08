package com.example.meri.newproject.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.meri.newproject.R;

public class RecyclerViewHolder extends RecyclerView.ViewHolder{

    private OnItemClickListener mOnItemClickListener;

    private View.OnClickListener mOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if(mOnClickListener != null){
                mOnItemClickListener.onStarClick(getAdapterPosition());
            }
        }
    };

    private TextView mName;
    private ImageView mFavorite;
    private TextView mNumber;

    public RecyclerViewHolder(View itemView) {
        super(itemView);

        mName = itemView.findViewById(R.id.text_contacts_fragment_name);
        mFavorite = itemView.findViewById(R.id.image_contacts_fragment_star);
        mNumber = itemView.findViewById(R.id.text_contacts_fragment_number);

        mFavorite.setOnClickListener(mOnClickListener);
    }

    public TextView getName() {
        return mName;
    }

    public ImageView getFavorite() {
        return mFavorite;
    }

    public TextView getNumber() {
        return mNumber;
    }

    public void setName(TextView name) {
        mName = name;
    }

    public void setFavorite(ImageView favorite) {
        mFavorite = favorite;
    }

    public void setNumber(TextView number) {
        mNumber = number;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        mOnItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener{
        void onStarClick(int position);
    }
}
