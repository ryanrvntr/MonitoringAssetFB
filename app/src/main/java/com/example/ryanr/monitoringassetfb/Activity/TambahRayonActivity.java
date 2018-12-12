package com.example.ryanr.monitoringassetfb.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.ryanr.monitoringassetfb.Model.RayonModel;
import com.example.ryanr.monitoringassetfb.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class TambahRayonActivity extends AppCompatActivity {
    private DatabaseReference reference;
    private EditText mNama_Rayon,mAlamat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_rayon);

        reference = FirebaseDatabase.getInstance().getReference();

        mNama_Rayon = findViewById(R.id.etNamaRayon);
        mAlamat = findViewById(R.id.etAlamat);

        findViewById(R.id.submit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sNama_Rayon = mNama_Rayon.getText().toString();
                String sAlamat = mAlamat.getText().toString();
                if (sNama_Rayon.equals("")){
                    mNama_Rayon.setError("Nama Rayon Masih Kosong");
                    mNama_Rayon.requestFocus();
                }else if (sAlamat.equals("")){
                    mAlamat.requestFocus();
                    mAlamat.setError("Alamat Masih Kosong");
                }else {
                    submitRayon(new RayonModel(sNama_Rayon.toLowerCase(),sAlamat.toLowerCase()));
                    Intent intent = new Intent(getApplicationContext(),RayonActivity.class);
                    startActivity(intent);
                }
            }
        });
    }

    private void submitRayon(RayonModel rayonModel){
        reference.child("Rayon").push().setValue(rayonModel).addOnSuccessListener(this, new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                mNama_Rayon.setText("");
                mAlamat.setText("");
                Toast.makeText(getApplicationContext(),"Berhasil menambahkan Rayon",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
