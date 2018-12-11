package com.example.ryanr.monitoringassetfb.Activity;

import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ryanr.monitoringassetfb.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.squareup.picasso.Picasso;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageClickListener;
import com.synnapps.carouselview.ImageListener;

public class DetailPropertyActivity extends FragmentActivity implements OnMapReadyCallback {
    TextView mNama,mAlamat,mKodePos,mKeterangan,mLongitude,mLatitude;
    ImageView mGambar;
    long longitude, latitude;

    private GoogleMap mMap;
    CarouselView carouselView;
    int[] mapAndImage = {R.id.map, R.id.ivImage};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_property);

        carouselView = findViewById(R.id.carouselView);
        carouselView.setPageCount(mapAndImage.length);
        carouselView.setImageClickListener((ImageClickListener) imageListener);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        String nama = getIntent().getStringExtra("nama");
        String alamat = getIntent().getStringExtra("alamat");
        String kode_pos = getIntent().getStringExtra("kode_pos");
        String keterangan = getIntent().getStringExtra("keterangan");
        String gambar = getIntent().getStringExtra("gambar");
        longitude = getIntent().getLongExtra("longitude",0);
        latitude = getIntent().getLongExtra("latitude",0);

        mNama = findViewById(R.id.tvNama);
        mAlamat = findViewById(R.id.tvAlamat);
        mKodePos = findViewById(R.id.tvKodePos);
        mKeterangan = findViewById(R.id.keterangan);
        mGambar = findViewById(R.id.ivImage);

        mNama.setText(nama);
        mAlamat.setText(alamat);
        mKeterangan.setText(keterangan);
        mKodePos.setText(kode_pos);
        Picasso.get().load(gambar).into(mGambar);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(longitude, latitude);
        mMap.addMarker(new MarkerOptions().position(sydney).title("This is position"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }

    ImageListener imageListener = new ImageListener() {
        @Override
        public void setImageForPosition(int position, ImageView imageView) {
            imageView.setImageResource(mapAndImage[position]);
        }
    };
}
