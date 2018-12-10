package com.example.ryanr.monitoringassetfb.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ryanr.monitoringassetfb.R;
import com.squareup.picasso.Picasso;

public class DetailProperty extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_property);

        String nama = getIntent().getStringExtra("nama");
        String alamat = getIntent().getStringExtra("alamat");
        String kode_pos = getIntent().getStringExtra("kode_pos");
        String keterangan = getIntent().getStringExtra("keterangan");
        String gambar = getIntent().getStringExtra("gambar");
        long longitude = getIntent().getLongExtra("longitude",0);
        long latitude = getIntent().getLongExtra("latitude",0);

        TextView mNama = findViewById(R.id.tvNama);
        TextView mAlamat = findViewById(R.id.tvAlamat);
        TextView mKodePos = findViewById(R.id.tvKodePos);
        TextView mKeterangan = findViewById(R.id.tvKeterangan);
        ImageView mGambar = findViewById(R.id.ivImage);

        mNama.setText(nama);
        mAlamat.setText(alamat);
        mKeterangan.setText(keterangan);
        mKodePos.setText(kode_pos);
//        mLongitude.setText(String.valueOf(longitude));
//        mLatitude.setText(String.valueOf(latitude));
        Picasso.get().load(gambar).into(mGambar);
    }
}
