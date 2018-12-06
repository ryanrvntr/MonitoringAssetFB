package com.example.ryanr.monitoringassetfb.Activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.example.ryanr.monitoringassetfb.ClickListener;
import com.example.ryanr.monitoringassetfb.Model.RayonModel;
import com.example.ryanr.monitoringassetfb.R;
import com.example.ryanr.monitoringassetfb.RecyclerTouchListener;
import com.example.ryanr.monitoringassetfb.ViewHolder.ViewHolderRayon;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RayonActivity extends AppCompatActivity {
    FirebaseRecyclerAdapter<RayonModel,ViewHolderRayon> firebaseRecyclerAdapter;
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
        setContentView(R.layout.activity_rayon);


        mRecyclerView = findViewById(R.id.rvRayon);
        mRecyclerView.setHasFixedSize(true);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mReference = mFirebaseDatabase.getReference("Rayon");
    }

    @Override
    protected void onStart() {
        super.onStart();
                firebaseRecyclerAdapter =
                new FirebaseRecyclerAdapter<RayonModel, ViewHolderRayon>(RayonModel.class,R.layout.row_rayon,ViewHolderRayon.class,mReference) {
                    @Override
                    protected void populateViewHolder(ViewHolderRayon viewHolder, RayonModel model, final int position) {
                        viewHolder.setDetail(getApplicationContext(),model.getNama_rayon(),model.getAlamat());
                        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent = new Intent(getApplicationContext(),HomeActivity.class);
                                intent.putExtra("id_rayon",firebaseRecyclerAdapter.getRef(position).getKey());
                                intent.putExtra("nama_rayon",firebaseRecyclerAdapter.getItem(position).getNama_rayon());
                                startActivity(intent);
                            }
                        });
                    }
                };
        mRecyclerView.setAdapter(firebaseRecyclerAdapter);

    }
}
