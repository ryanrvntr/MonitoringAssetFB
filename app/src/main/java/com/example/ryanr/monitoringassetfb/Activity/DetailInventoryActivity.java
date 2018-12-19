package com.example.ryanr.monitoringassetfb.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ryanr.monitoringassetfb.R;
import com.squareup.picasso.Picasso;

public class DetailInventoryActivity extends AppCompatActivity {
    TextView mNama,mJenis,mJumlah,mKeterangan,mMerk,mRuang,mSatuan;
    ImageView mGambar;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_inventory);
        String name = getIntent().getStringExtra("nama");
        String gambar = getIntent().getStringExtra("gambar");
        String jenis = getIntent().getStringExtra("jenis");
        int jumlah = getIntent().getIntExtra("jumlah",0);
        String keterangan = getIntent().getStringExtra("keterangan");
        String merk = getIntent().getStringExtra("merk");
        String ruang = getIntent().getStringExtra("ruang");
        String satuan = getIntent().getStringExtra("satuan");

        mNama = findViewById(R.id.tvNama);
        mJenis = findViewById(R.id.tvJenis);
        mJumlah = findViewById(R.id.tv_jumlah);
        mKeterangan = findViewById(R.id.keterangan);
        mMerk = findViewById(R.id.tvMerk);
        mRuang = findViewById(R.id.tvRuang);
        mSatuan = findViewById(R.id.tvSatuan);
        mGambar = findViewById(R.id.ivImage);

        mNama.setText(name);
        Picasso.get().load(gambar).into(mGambar);
        mRuang.setText(ruang);
        mJenis.setText(jenis);
        mJumlah.setText(String.valueOf(jumlah));
        mSatuan.setText(satuan);
        mMerk.setText(merk);
        mKeterangan.setText(keterangan);
    }
}
