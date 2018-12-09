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

import com.example.ryanr.monitoringassetfb.Model.PropertyModel;
import com.example.ryanr.monitoringassetfb.Model.RayonModel;
import com.example.ryanr.monitoringassetfb.R;
import com.example.ryanr.monitoringassetfb.ViewHolder.ViewHolderInventory;
import com.example.ryanr.monitoringassetfb.ViewHolder.ViewHolderProperty;
import com.example.ryanr.monitoringassetfb.ViewHolder.ViewHolderRayon;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class PropertyActivity extends AppCompatActivity {
    FirebaseRecyclerAdapter<PropertyModel,ViewHolderProperty> firebaseRecyclerAdapter;
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
        setContentView(R.layout.activity_property);

        mRecyclerView = findViewById(R.id.rvProperti);
        mRecyclerView.setHasFixedSize(true);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mReference = mFirebaseDatabase.getReference("Property");
    }

    @Override
    protected void onStart() {
        super.onStart();
        firebaseRecyclerAdapter =
                new FirebaseRecyclerAdapter<PropertyModel, ViewHolderProperty>(PropertyModel.class,R.layout.row_property,ViewHolderProperty.class,mReference) {
                    @Override
                    protected void populateViewHolder(ViewHolderProperty viewHolder, PropertyModel model, final int position) {
                        viewHolder.setDetail(getApplicationContext(), model.getNama_property(), model.getAlamat(), model.getGambar());
                        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent = new Intent(getApplicationContext(),DetailProperty.class);
                                intent.putExtra("nama", firebaseRecyclerAdapter.getItem(position).getNama_property());
                                intent.putExtra("alamat", firebaseRecyclerAdapter.getItem(position).getAlamat());
                                intent.putExtra("kode_pos", firebaseRecyclerAdapter.getItem(position).getKode_pos());
                                intent.putExtra("keterangan", firebaseRecyclerAdapter.getItem(position).getKeterangan());
                                intent.putExtra("gambar", firebaseRecyclerAdapter.getItem(position).getGambar());
                                intent.putExtra("longitude", firebaseRecyclerAdapter.getItem(position).getLongitude());
                                intent.putExtra("latitude", firebaseRecyclerAdapter.getItem(position).getLatitude());

                                startActivity(intent);
                            }
                        });

                    }
                };
        mRecyclerView.setAdapter(firebaseRecyclerAdapter);
    }
}
