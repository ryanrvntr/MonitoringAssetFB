package com.example.ryanr.monitoringassetfb.ViewHolder;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ryanr.monitoringassetfb.R;
import com.squareup.picasso.Picasso;

public class ViewHolderProperty extends RecyclerView.ViewHolder {
    View mView;

    public ViewHolderProperty(@NonNull View itemView) {
        super(itemView);
        mView = itemView;
    }

    public void setDetail(Context context, String nama_property, String alamat , String gambar){
        TextView mProperty = mView.findViewById(R.id.tvProperty);
        TextView mAlamat = mView.findViewById(R.id.tvAlamatP);
        ImageView imgProperti = mView.findViewById(R.id.ivProperty);


        mProperty.setText(nama_property);
        mAlamat.setText(alamat);
        Picasso.get().load(gambar).into(imgProperti);
    }
}
