package com.example.ryanr.monitoringassetfb.Activity;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ryanr.monitoringassetfb.Model.InventoryModel;
import com.example.ryanr.monitoringassetfb.Model.TransportModel;
import com.example.ryanr.monitoringassetfb.R;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import static android.text.TextUtils.isEmpty;

public class TambahTransportActivity extends AppCompatActivity {
    private static final int PICK_IMAGE_REQUEST = 1;
    EditText mNama, mJenis, mKeterangan, mNomor, mPemilik, mPengguna, mSakhir,mSawal;
    Button mChoose, mSubmit, mBack;

    private Uri mImageUri;

    private StorageReference mStorageRef;
    private DatabaseReference mDatabaseRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_transport);

        Intent intent = getIntent();
        final String id_rayon = intent.getStringExtra("id_rayon");

        mNama = findViewById(R.id.etNamaKendaraan);
        mJenis = findViewById(R.id.etJenis);
        mKeterangan = findViewById(R.id.etKeterangan);
        mNomor = findViewById(R.id.etNo);
        mPemilik = findViewById(R.id.etPengguna);
        mPengguna = findViewById(R.id.etPengguna);
        mSakhir = findViewById(R.id.etSakhir);
        mSawal = findViewById(R.id.etSawal);

        mChoose = findViewById(R.id.choosefile);
        mSubmit = findViewById(R.id.submit);

        mStorageRef = FirebaseStorage.getInstance().getReference("Transport");
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("Transport");

        mChoose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFileChooser();
            }
        });

        mSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isEmpty(mNama.getText().toString()) &&
                        !isEmpty(mJenis.getText().toString()) &&
                        !isEmpty(mKeterangan.getText().toString()) &&
                        !isEmpty(mNomor.getText().toString()) &&
                        !isEmpty(mPemilik.getText().toString()) &&
                        !isEmpty(mSakhir.getText().toString()) &&
                        !isEmpty(mPengguna.getText().toString()) &&
                        !isEmpty(mSawal.getText().toString())){
                    if (mImageUri != null){
                        final StorageReference reference = mStorageRef.child(System.currentTimeMillis() + "." + getFileExtension(mImageUri));
                        reference.putFile(mImageUri).continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                            @Override
                            public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                                if (!task.isSuccessful()) {
                                    throw task.getException();
                                }
                                return reference.getDownloadUrl();
                            }
                        }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                            @Override
                            public void onComplete(@NonNull Task<Uri> task) {
                                if (task.isSuccessful()) {
                                    Uri downloadUri = task.getResult();
                                    Toast.makeText(TambahTransportActivity.this, "Upload succes", Toast.LENGTH_LONG).show();
                                    String value = mSakhir.getText().toString();
                                    String value1 = mSawal.getText().toString();

                                    int finalvalue = Integer.parseInt(value);
                                    int finalvalue1 = Integer.parseInt(value1);
                                    submitTransport(new TransportModel(downloadUri.toString(),
                                            mJenis.getText().toString(),
                                            mKeterangan.getText().toString(),
                                            mNama.getText().toString(),
                                            mNomor.getText().toString(),
                                            mPemilik.getText().toString(),
                                            mPengguna.getText().toString(),
                                            id_rayon,
                                            finalvalue,
                                            finalvalue1));
                                }
                            }
                        });
                    }
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

    private void submitTransport(TransportModel transportModel) {
        mDatabaseRef.push().setValue(transportModel).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                mNama.setText("");
                mJenis.setText("");
                mNomor.setText("");
                mKeterangan.setText("");
                mSakhir.setText("");
                mSawal.setText("");
                mPengguna.setText("");
                mPemilik.setText("");
                Snackbar.make(findViewById(R.id.submit), "Inventory berhasil ditambahh ...", Snackbar.LENGTH_LONG).show();
            }
        });
    }

}
