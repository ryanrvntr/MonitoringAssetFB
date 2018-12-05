package com.example.ryanr.monitoringassetfb.ViewHolder;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ryanr.monitoringassetfb.R;
import com.squareup.picasso.Picasso;

public class ViewHolderInventory extends RecyclerView.ViewHolder {
    View mView;

    public ViewHolderInventory(@NonNull View itemView) {
        super(itemView);

        mView = itemView;
    }

    public void setDetail(Context context, String nama, String merk , String gambar){
        TextView mInventory = mView.findViewById(R.id.tvInventory);
        TextView mMerk = mView.findViewById(R.id.tvMerk);
        ImageView imgInventory = mView.findViewById(R.id.ivInventory);


        mInventory.setText(nama);
        mMerk.setText(merk);
        Picasso.get().load(gambar).into(imgInventory);
    }
}
