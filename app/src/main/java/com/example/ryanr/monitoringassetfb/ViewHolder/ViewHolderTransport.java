package com.example.ryanr.monitoringassetfb.ViewHolder;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.ryanr.monitoringassetfb.R;

public class ViewHolderTransport extends RecyclerView.ViewHolder {
    View mView;

    public ViewHolderTransport(@NonNull View itemView) {
        super(itemView);

        mView = itemView;
    }

    public void setDetails(Context context ,String nama_kendaraan, String nomor_polisi){
        TextView mNama = mView.findViewById(R.id.tvTransport);
        TextView mNopol = mView.findViewById(R.id.tvNoPolisi);

        mNama.setText(nama_kendaraan);
        mNopol.setText(nomor_polisi);
    }
}
