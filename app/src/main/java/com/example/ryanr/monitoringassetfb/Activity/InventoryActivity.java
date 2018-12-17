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
import android.widget.Button;
import android.widget.Toast;

import com.example.ryanr.monitoringassetfb.Model.InventoryModel;
import com.example.ryanr.monitoringassetfb.R;
import com.example.ryanr.monitoringassetfb.ViewHolder.ViewHolderInventory;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class InventoryActivity extends AppCompatActivity {
    FirebaseRecyclerAdapter<InventoryModel,ViewHolderInventory> firebaseRecyclerAdapter;
    FirebaseDatabase mFirebaseDatabase;
    RecyclerView mRecyclerView;
    DatabaseReference mReference;
    Button mTambah;

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
        setContentView(R.layout.activity_inventory);
        final Intent intent = getIntent();
        final String id_rayon= intent.getStringExtra("id_rayon");
        mRecyclerView = findViewById(R.id.rvInventory);
        mRecyclerView.setHasFixedSize(true);
        mTambah = findViewById(R.id.tambahInventory);

        mTambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(InventoryActivity.this,TambahInventoryActivity.class);
                i.putExtra("id_rayon",id_rayon);
                startActivity(i);
            }
        });

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mReference = mFirebaseDatabase.getReference("Inventory");
        if (!id_rayon.isEmpty() && id_rayon != null) {
            Toast.makeText(getApplicationContext(),id_rayon.toString(),Toast.LENGTH_SHORT).show();
            LoadListInventory(id_rayon);
        }else {
            Toast.makeText(getApplicationContext(),"null",Toast.LENGTH_SHORT).show();
        }
    }

    private void LoadListInventory(String id_rayon) {

        firebaseRecyclerAdapter =
                new FirebaseRecyclerAdapter<InventoryModel, ViewHolderInventory>(InventoryModel.class,R.layout.row_inventory,ViewHolderInventory.class,mReference.orderByChild("rayonId").equalTo(id_rayon))
                {
                    @Override
                    protected void populateViewHolder(ViewHolderInventory viewHolder, final InventoryModel model, final int position) {
                        viewHolder.setDetail(getApplicationContext(),model.getNama(),model.getMerk(),model.getGambar());
                        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent = new Intent(getApplication(),DetailInventoryActivity.class);
                                intent.putExtra("nama",firebaseRecyclerAdapter.getItem(position).getNama());
                                intent.putExtra("gambar",firebaseRecyclerAdapter.getItem(position).getGambar());
                                intent.putExtra("jenis",firebaseRecyclerAdapter.getItem(position).getJenis_inventory());
                                intent.putExtra("jumlah",firebaseRecyclerAdapter.getItem(position).getJumlah());
                                intent.putExtra("keterangan",firebaseRecyclerAdapter.getItem(position).getKeterangan());
                                intent.putExtra("merk",firebaseRecyclerAdapter.getItem(position).getMerk());
                                intent.putExtra("ruang",firebaseRecyclerAdapter.getItem(position).getRuang());
                                intent.putExtra("satuan",firebaseRecyclerAdapter.getItem(position).getSatuan());
                                startActivity(intent);
                            }
                        });

                    }
                };
        mRecyclerView.setAdapter(firebaseRecyclerAdapter);

    }
}
