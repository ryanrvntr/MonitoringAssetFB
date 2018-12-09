package com.example.ryanr.monitoringassetfb.Activity;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ryanr.monitoringassetfb.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class HomeActivity extends AppCompatActivity {
    TextView id_rayon,nama_rayon;
    ImageView imgProfil;
    CardView cvInventory,cvProperty,cvTransport;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.optionmenu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId()==R.id.logout){
            FirebaseAuth.getInstance().signOut();
        }
        return true;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            String name = user.getDisplayName();
            String email = user.getEmail();
            Uri img = user.getPhotoUrl();
        final Intent intent = getIntent();
        id_rayon = findViewById(R.id.tvRayon);
        nama_rayon = findViewById(R.id.tvEmail);
        final String rayon = intent.getStringExtra("id_rayon");
        String nama = intent.getStringExtra("nama_rayon");
        id_rayon.setText(nama);
        nama_rayon.setText(email);

        cvInventory = findViewById(R.id.cvInventory);
        cvInventory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i  = new Intent(getApplicationContext(),InventoryActivity.class);
                i.putExtra("id_rayon",rayon);
                startActivity(i);
            }
        });

        cvTransport = findViewById(R.id.cvTransport);
        cvTransport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i  = new Intent(getApplicationContext(),TransportActivity.class);
                i.putExtra("id_rayon",rayon);
                startActivity(i);
            }
        });

        cvProperty = findViewById(R.id.cvProperty);
        cvProperty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplication(),PropertyActivity.class);
                startActivity(i);
            }
        });
    }
}
