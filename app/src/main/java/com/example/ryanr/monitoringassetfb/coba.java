//package com.example.ryanr.monitoringassetfb;
//
//
//import android.content.ContentResolver;
//import android.content.Context;
//import android.content.Intent;
//import android.net.Uri;
//import android.support.annotation.NonNull;
//import android.support.design.widget.Snackbar;
//import android.support.v7.app.AppCompatActivity;
//import android.os.Bundle;
//import android.text.TextUtils;
//import android.util.Log;
//import android.view.View;
//import android.view.inputmethod.InputMethodManager;
//import android.webkit.MimeTypeMap;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.ImageView;
//import android.support.v4.app.FragmentActivity.*;
//import android.widget.Toast;
//
//import com.google.android.gms.tasks.Continuation;
//import com.google.android.gms.tasks.OnCompleteListener;
//import com.google.android.gms.tasks.OnFailureListener;
//import com.google.android.gms.tasks.OnSuccessListener;
//import com.google.android.gms.tasks.Task;
//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;
//import com.google.firebase.storage.FirebaseStorage;
//import com.google.firebase.storage.OnProgressListener;
//import com.google.firebase.storage.StorageReference;
//import com.google.firebase.storage.UploadTask;
//import com.squareup.picasso.Picasso;
//
//import co.uas.android_eai_admin.Biodata.DataModel_Biodata;
//import co.uas.android_eai_admin.Biodata.Tambah_Biodata;
//import co.uas.android_eai_admin.R;
//
//import static android.text.TextUtils.isEmpty;
//
//public class Tambah_Dosen extends AppCompatActivity {
//
//    //variable untuk refers ke Firebase Realtime Database
//    private DatabaseReference database;
//
//    private static final int PICK_IMAGE_REQUEST = 1;
//    private static final String TAG = "Tambah_Dosen";
//
//    //variable komponen dalam layout
//    private Button btnSubmit;
//    private EditText etnama;
//    private EditText etnidn;
//    private EditText etttl;
//    private EditText etagama;
//    private EditText etjk;
//    private EditText etalamat;
//    private EditText ettelp;
//    private EditText etpwd;
//    private EditText etemail;
//    private Button btnFoto;
//    private EditText etfoto;
//    private ImageView imgPrev;
//
//    private Uri fotoUri;
//
//    private StorageReference storage;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_tambah_dosen);
//
//        //inisiasi komponen dari layout
//        btnSubmit = (Button) findViewById(R.id.btn_submit);
//        etnama = (EditText) findViewById(R.id.namaDsn);
//        etnidn = (EditText) findViewById(R.id.nidnDsn);
//        etttl = (EditText) findViewById(R.id.ttlDsn);
//        etagama = (EditText) findViewById(R.id.agamaDsn);
//        etjk = (EditText) findViewById(R.id.jkDsn);
//        etalamat = (EditText) findViewById(R.id.alamatDsn);
//        ettelp = (EditText) findViewById(R.id.telpDsn);
//        etpwd = (EditText) findViewById(R.id.pwdDsn);
//        etemail = (EditText) findViewById(R.id.emailDsn);
//        btnFoto = (Button) findViewById(R.id.btn_photo);
//        etfoto = (EditText) findViewById(R.id.phtDsn);
//        imgPrev = (ImageView) findViewById(R.id.imagePreview);
//
//
//        storage = FirebaseStorage.getInstance().getReference("Dosen");
//
//        //mengambil reference database ke Firebase
//        database = FirebaseDatabase.getInstance().getReference();
//
//        btnFoto.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                openFileChooser();
//            }
//        });
//
//        final DataModel_Dosen dataModel1 = (DataModel_Dosen) getIntent().getSerializableExtra("data1");
//
//        //action untuk button submit
//        btnSubmit.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if (!isEmpty(etnama.getText().toString()) && !isEmpty(etnidn.getText().toString()) && !isEmpty(etttl.getText().toString()) && !isEmpty(etagama.getText().toString()) && !isEmpty(etjk.getText().toString()) && !isEmpty(etalamat.getText().toString()) && !isEmpty(ettelp.getText().toString()) && !isEmpty(etpwd.getText().toString()) && !isEmpty(etemail.getText().toString()))
//                {
//                    //ToDO IKI LOH COI!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
//
//                    if (fotoUri != null) {
//                        final StorageReference fileReference = storage.child(etfoto.getText().toString().trim() + "." + getFileExtension(fotoUri));
//
//                        fileReference.putFile(fotoUri).continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
//                            @Override
//                            public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
//
//                                if(!task.isSuccessful())
//                                {
//                                    throw task.getException();
//                                }
//                                return fileReference.getDownloadUrl();
//                            }
//                        }).addOnCompleteListener(new OnCompleteListener<Uri>() {
//                            @Override
//                            public void onComplete(@NonNull Task<Uri> task) {
//                                if(task.isSuccessful())
//                                {
//                                    Uri downloadUri = task.getResult();
//                                    Log.e(TAG, "then : " + downloadUri.toString());
//
//                                    Toast.makeText(Tambah_Dosen.this, "Upload Sukses", Toast.LENGTH_LONG).show();
//                                    submitDataModel(new DataModel_Dosen(etnama.getText().toString(), etnidn.getText().toString(), etttl.getText().toString(), etagama.getText().toString(), etjk.getText().toString(), etalamat.getText().toString(), ettelp.getText().toString() , etpwd.getText().toString(), etemail.getText().toString(), etfoto.getText().toString(), downloadUri.toString()));
//                                }
//                            }
//                        });
//                    }
//                }
//                else {
//                    Snackbar.make(findViewById(R.id.btn_submit), "Data barang tidak boleh kosong", Snackbar.LENGTH_LONG).show();
//                }
//                InputMethodManager imm = (InputMethodManager)
//                        getSystemService(Context.INPUT_METHOD_SERVICE);
//                imm.hideSoftInputFromWindow(
//                        etnama.getWindowToken(), 0);
//            }
//        });
//    }
//
//    private String getFileExtension(Uri uri) {
//        ContentResolver cR = getContentResolver();
//        MimeTypeMap mime = MimeTypeMap.getSingleton();
//        return mime.getExtensionFromMimeType(cR.getType(uri));
//    }
//
//    private void openFileChooser() {
//        Intent intent = new Intent();
//        intent.setType("image/*");
//        intent.setAction(Intent.ACTION_GET_CONTENT);
//        startActivityForResult(intent, PICK_IMAGE_REQUEST);
//    }
//
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//
//        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
//            fotoUri = data.getData();
//
//            Picasso.with(this).load(fotoUri).into(imgPrev);
//            imgPrev.setImageURI(fotoUri);
//        }
//    }
//
//    private boolean isEmpty(String s)
//    {
//        return TextUtils.isEmpty(s);
//    }
//
//    public void submitDataModel(DataModel_Dosen dataModel1) {
//        database.child("DataDosen").push().setValue(dataModel1).addOnSuccessListener(this, new OnSuccessListener<Void>()
//        {
//            @Override
//            public void onSuccess(Void aVoid) {
//                etnama.setText("");
//                etnidn.setText("");
//                etttl.setText("");
//                etjk.setText("");
//                etagama.setText("");
//                etalamat.setText("");
//                ettelp.setText("");
//                etpwd.setText("");
//                etemail.setText("");
//                Snackbar.make(findViewById(R.id.btn_submit), "Data Berhasil ditambahkan", Snackbar.LENGTH_LONG).show();
//            }
//        });
//    }
//}
