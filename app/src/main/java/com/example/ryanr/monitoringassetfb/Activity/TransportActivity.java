package com.example.ryanr.monitoringassetfb.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.example.ryanr.monitoringassetfb.Model.InventoryModel;
import com.example.ryanr.monitoringassetfb.Model.TransportModel;
import com.example.ryanr.monitoringassetfb.R;
import com.example.ryanr.monitoringassetfb.ViewHolder.ViewHolderInventory;
import com.example.ryanr.monitoringassetfb.ViewHolder.ViewHolderTransport;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class TransportActivity extends AppCompatActivity {
    FirebaseRecyclerAdapter<TransportModel,ViewHolderTransport> firebaseRecyclerAdapter;
    FirebaseDatabase mFirebaseDatabase;
    RecyclerView mRecyclerView;
    DatabaseReference mReference;

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
        setContentView(R.layout.activity_transport);
        Intent intent = getIntent();
        String id_rayon = intent.getStringExtra("id_rayon");
        mRecyclerView = findViewById(R.id.rvTransport);
        mRecyclerView.setHasFixedSize(true);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mReference = mFirebaseDatabase.getReference("Transport");
        LoadListTransport(id_rayon);
    }

    private void LoadListTransport(String id_rayon){
        firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<TransportModel, ViewHolderTransport>(TransportModel.class,R.layout.row_transport,ViewHolderTransport.class,mReference.orderByChild("rayonId").equalTo(id_rayon)) {
            @Override
            protected void populateViewHolder(ViewHolderTransport viewHolder, TransportModel model, final int position) {
                viewHolder.setDetails(getApplicationContext(),model.getNama_kendaraan(),model.getNomor_polisi());
                viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getApplication(), DetailTransportActivity.class);
                        intent.putExtra("nama",firebaseRecyclerAdapter.getItem(position).getNama_kendaraan());
                        intent.putExtra("gambar",firebaseRecyclerAdapter.getItem(position).getGambar());
                        intent.putExtra("jenis",firebaseRecyclerAdapter.getItem(position).getJenis());
                        intent.putExtra("no_polisi",firebaseRecyclerAdapter.getItem(position).getNomor_polisi());
                        intent.putExtra("pemilik",firebaseRecyclerAdapter.getItem(position).getPemilik());
                        intent.putExtra("pengguna",firebaseRecyclerAdapter.getItem(position).getPengguna());
                        intent.putExtra("stan_akhir",firebaseRecyclerAdapter.getItem(position).getStan_akhir());
                        intent.putExtra("stan_awal",firebaseRecyclerAdapter.getItem(position).getStan_awal());
                        intent.putExtra("keterangan",firebaseRecyclerAdapter.getItem(position).getKeterangan());
                        intent.putExtra("rayon",firebaseRecyclerAdapter.getItem(position).getRayonId());
                        startActivity(intent);
                    }
                });
            }
        };
        mRecyclerView.setAdapter(firebaseRecyclerAdapter);
    }
}
