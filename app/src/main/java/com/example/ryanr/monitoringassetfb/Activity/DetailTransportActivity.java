package com.example.ryanr.monitoringassetfb.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ryanr.monitoringassetfb.R;
import com.squareup.picasso.Picasso;

public class DetailTransportActivity extends AppCompatActivity {
    TextView mNama,mJenis,mNoPolisi,mKeterangan,mPemilik,mPengguna,mStanAwal,mStanAkhir,mRayon;
    ImageView mGambar;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_inventory);
        String name = getIntent().getStringExtra("nama");
        String gambar = getIntent().getStringExtra("gambar");
        String jenis = getIntent().getStringExtra("jenis");
        String keterangan = getIntent().getStringExtra("keterangan");
        String pemilik = getIntent().getStringExtra("pemilik");
        String pengguna = getIntent().getStringExtra("pengguna");
        String no_polisi = getIntent().getStringExtra("no_polisi");
        String rayon = getIntent().getStringExtra("rayon");
        int stanAwal = getIntent().getIntExtra("stan_awal",0);
        int stanAkhir = getIntent().getIntExtra("stan_akhir",0);

        mNama = findViewById(R.id.tvNama);
        mJenis = findViewById(R.id.tvJenis);
        mPemilik = findViewById(R.id.tvPemilik);
        mKeterangan = findViewById(R.id.keterangan);
        mPengguna= findViewById(R.id.tvPengguna);
        mNoPolisi = findViewById(R.id.tvNoPolisi);
        mStanAwal = findViewById(R.id.tvStanAwal);
        mStanAkhir = findViewById(R.id.tvStanAkhir);
        mRayon = findViewById(R.id.tvRayon);
        mGambar = findViewById(R.id.ivImage);

        mNama.setText(name);
        Picasso.get().load(gambar).into(mGambar);
        mPemilik.setText(pemilik);
        mPengguna.setText(pengguna);
        mJenis.setText(jenis);
        mStanAwal.setText(String.valueOf(stanAwal));
        mStanAkhir.setText(String.valueOf(stanAkhir));
        mRayon.setText(rayon);
        mNoPolisi.setText(no_polisi);
        mKeterangan.setText(keterangan);
    }
}
