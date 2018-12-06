package com.example.ryanr.monitoringassetfb.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
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
        Intent intent = getIntent();
        String id_rayon= intent.getStringExtra("id_rayon");
        mRecyclerView = findViewById(R.id.rvInventory);
        mRecyclerView.setHasFixedSize(true);

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
                    protected void populateViewHolder(ViewHolderInventory viewHolder, InventoryModel model, int position) {
                        viewHolder.setDetail(getApplicationContext(),model.getNama(),model.getMerk(),model.getGambar());

                    }
                };
        mRecyclerView.setAdapter(firebaseRecyclerAdapter);

    }
}
