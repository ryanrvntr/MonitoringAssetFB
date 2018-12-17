package com.example.ryanr.monitoringassetfb.Activity;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ryanr.monitoringassetfb.Model.InventoryModel;
import com.example.ryanr.monitoringassetfb.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;

public class TambahInventoryActivity extends AppCompatActivity {
    private static final int PICK_IMAGE_REQUEST = 1;
    EditText mNama,mRuang,mSatuan,mJumlah,mJenis,mMerk,mKeterangan;
    Button mChoose,mSubmit,mBack;

    private Uri mImageUri;

    private StorageTask mUploadTask;

    private StorageReference mStorageRef;
    private DatabaseReference mDatabaseRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_inventory);

        Intent intent = getIntent();
        final String id_rayon= intent.getStringExtra("id_rayon");

        mNama = findViewById(R.id.etNamaInventory);
        mJenis = findViewById(R.id.etJenis);
        mRuang = findViewById(R.id.etRuang);
        mSatuan = findViewById(R.id.etSatuan);
        mJumlah = findViewById(R.id.etJumlah);
        mMerk = findViewById(R.id.etMerk);
        mKeterangan = findViewById(R.id.etKeterangan);
        mChoose = findViewById(R.id.choosefile);
        mSubmit = findViewById(R.id.submit);

        mStorageRef = FirebaseStorage.getInstance().getReference("Inventory");
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("Inventory");

        mChoose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFileChooser();
            }
        });

        mSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mUploadTask != null && mUploadTask.isInProgress()) {
                    Toast.makeText(TambahInventoryActivity.this, "Upload in progress", Toast.LENGTH_SHORT).show();
                } else {
                    uploadFile(id_rayon);
                }
            }
        });
    }

    private void openFileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK
                && data != null && data.getData() != null) {
            mImageUri = data.getData();
        }
    }

    private String getFileExtension(Uri uri) {
        ContentResolver cR = getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cR.getType(uri));
    }

    private void uploadFile(final String id_rayon){
        if (mImageUri != null){
            StorageReference fileReference = mStorageRef.child(System.currentTimeMillis()+"."+getFileExtension(mImageUri)).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                @Override
                public void onSuccess(Uri uri) {
                Uri downloadUri =
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {

                }
            });
//            mUploadTask = fileReference.putFile(mImageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
//                @Override
//                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
//                    Toast.makeText(TambahInventoryActivity.this,"Upload Succes ....",Toast.LENGTH_LONG).show();
//                    String value = mJumlah.getText().toString();
//                    int finalvalue = Integer.parseInt(value);
//
//                    InventoryModel inventoryModel = new InventoryModel(
//                            mNama.getText().toString(),
//                            mJenis.getText().toString(),
//                            mKeterangan.getText().toString(),
//                            mMerk.getText().toString(),
//                            mRuang.getText().toString(),
//                            mSatuan.getText().toString(),
//                            taskSnapshot.getMetadata().getReference().getDownloadUrl().toString(),
//                            id_rayon,
//                            finalvalue);
//                    mDatabaseRef.push().setValue(inventoryModel);
//                }
//            }).addOnFailureListener(new OnFailureListener() {
//                @Override
//                public void onFailure(@NonNull Exception e) {
//
//                }
//            });
//
//        }
    }
}
