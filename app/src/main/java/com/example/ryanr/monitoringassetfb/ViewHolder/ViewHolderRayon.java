package com.example.ryanr.monitoringassetfb.ViewHolder;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.ryanr.monitoringassetfb.R;

public class ViewHolderRayon extends RecyclerView.ViewHolder {
    View mView;

    public ViewHolderRayon(@NonNull View itemView) {
        super(itemView);

        mView = itemView;
    }
    public void setDetail(Context context, String nama_rayon, String alamat){
        TextView mRayon = mView.findViewById(R.id.tvRayon);
        TextView mAlamat = mView.findViewById(R.id.tvAlamat);

        mRayon.setText(nama_rayon);
        mAlamat.setText(alamat);
    }
}