package com.example.ryanr.monitoringassetfb.Activity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.FragmentManager;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ryanr.monitoringassetfb.Model.InventoryModel;
import com.example.ryanr.monitoringassetfb.Model.PropertyModel;
import com.example.ryanr.monitoringassetfb.R;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polygon;
import com.google.android.gms.maps.model.PolygonOptions;
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

public class TambahPropertyActivity extends AppCompatActivity implements OnMapReadyCallback{
    //implements GoogleMap.OnMapClickListener, GoogleMap.OnMapLongClickListener, GoogleMap.OnMarkerDragListener, OnMapReadyCallback {
    private static final int PICK_IMAGE_REQUEST = 1;
    EditText mNama, mAlamat, mKeterangan, mKodepos,mLat,mLong;
    Button mChoose, mSubmit, mBack,mCheck;
    GoogleMap map;
    Double latidude , longitude;

    private Uri mImageUri;

    private StorageReference mStorageRef;
    private DatabaseReference mDatabaseRef;

    @SuppressLint("MissingPermission")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_property);

        mNama = findViewById(R.id.etNamaProperty);
        mAlamat = findViewById(R.id.etAlamat);
        mKeterangan = findViewById(R.id.etKeterangan);
        mKodepos = findViewById(R.id.etKodePos);
        mLat = findViewById(R.id.etLat);
        mLong = findViewById(R.id.etLong);
        mCheck = findViewById(R.id.check);
        mSubmit = findViewById(R.id.submit);
        mChoose = findViewById(R.id.choosefile);

        mStorageRef = FirebaseStorage.getInstance().getReference("Property");
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("Property");

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        mChoose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFileChooser();
            }
        });

        mSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isEmpty(mNama.getText().toString()) && !isEmpty(mAlamat.getText().toString()) && !isEmpty(mKodepos.getText().toString()) && !isEmpty(mKeterangan.getText().toString()) && !isEmpty(mLat.getText().toString()) && !isEmpty(mLong.getText().toString())) {
                    if (mImageUri != null) {
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
                                    Toast.makeText(TambahPropertyActivity.this, "Upload succes", Toast.LENGTH_LONG).show();
                                    String value = mLat.getText().toString();
                                    String value1 = mLong.getText().toString();
                                    latidude = Double.parseDouble(value);
                                    longitude = Double.parseDouble(value1);
                                    submitProperty(new PropertyModel(mNama.getText().toString(),
                                            mAlamat.getText().toString(),
                                            mKeterangan.getText().toString(),
                                            mKodepos.getText().toString(),
                                            downloadUri.toString(),
                                            latidude,
                                            longitude));
                                }
                            }
                        });
                    }
                }
            }
        });

        mCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String value = mLat.getText().toString();
                String value1 = mLong.getText().toString();
                latidude = Double.parseDouble(value);
                longitude = Double.parseDouble(value1);
                setMap(map,latidude,longitude);
            }
        });



//        FragmentManager myFragmentManager = getFragmentManager();
//        SupportMapFragment myMapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
//        myMapFragment.getMapAsync(this);
//        LatLng sydney = new LatLng(-33.852, 151.211);
//        MarkerOptions marker = new MarkerOptions()
//                .position(sydney)
//                .icon(BitmapDescriptorFactory.fromResource(R.drawable.marker));
        // map.addMarker(marker);

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

    private void submitProperty(PropertyModel propertyModel) {
        mDatabaseRef.push().setValue(propertyModel).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                mNama.setText("");
                mAlamat.setText("");
                mKodepos.setText("");
                mKeterangan.setText("");
                mLat.setText("");
                mLong.setText("");
                Snackbar.make(findViewById(R.id.submit), "Property berhasil ditambahh ...", Snackbar.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        latidude = -6.221866;
        longitude = 106.854355;
        map = googleMap;
        float zommLevel = 16.0f;
        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(latidude,longitude);
        map.addMarker(new MarkerOptions().position(sydney).title("This is position"));
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney,zommLevel));
    }

    public void setMap(GoogleMap googleMap , double latidude ,double longitude){
        map = googleMap;
        float zommLevel = 16.0f;
        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(latidude,longitude);
        map.addMarker(new MarkerOptions().position(sydney).title("This is position"));
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney,zommLevel));
    }
//    @Override
//    protected void onPostResume() {
//        super.onPostResume();
//        int resultCode = GooglePlayServicesUtil.isGooglePlayServicesAvailable(getApplicationContext());
//
//        if (resultCode == ConnectionResult.SUCCESS) {
//            Toast.makeText(getApplicationContext(), "isGooglePlayServicesAvailable SUCCESS", Toast.LENGTH_LONG).show();
//        } else {
//            GooglePlayServicesUtil.getErrorDialog(resultCode, this, RQS_GooglePlayServices);
//        }
//    }

//    @Override
//    public void onMapClick(LatLng latLng) {
////        map.animateCamera(CameraUpdateFactory.newLatLng(latLng));
////        markerClicked = false;
//        map.addMarker(new MarkerOptions()
//                .position(latLng)
//                .icon(BitmapDescriptorFactory.fromResource(R.drawable.marker))
//                .draggable(true));
//
//        markerClicked = false;
//    }
//
//    @Override
//    public void onMapLongClick(LatLng latLng) {
//        map.addMarker(new MarkerOptions()
//                .position(latLng)
//                .draggable(true));
//
//        markerClicked = false;
//    }

//    @Override
//    public void onMarkerDragStart(Marker marker) {
//        latidude = marker.getPosition().latitude;
//        longitude = marker.getPosition().longitude;
//    }
//
//    @Override
//    public void onMarkerDrag(Marker marker) {
//
//    }
//
//    @Override
//    public void onMarkerDragEnd(Marker marker) {
//
//    }

//    @Override
//    public void onMapReady(GoogleMap googleMap) {
//        map = googleMap;
//        map.setMapType(GoogleMap.MAP_TYPE_NORMAL);
//        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//            // TODO: Consider calling
//            //    ActivityCompat#requestPermissions
//            // here to request the missing permissions, and then overriding
//            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
//            //                                          int[] grantResults)
//            // to handle the case where the user grants the permission. See the documentation
//            // for ActivityCompat#requestPermissions for more details.
//            return;
//        }
//        map.setMyLocationEnabled(true);
//        map.setMapType(GoogleMap.MAP_TYPE_HYBRID);
//        map.setOnMapClickListener(this);
//        map.setOnMapLongClickListener(this);
//        map.setOnMarkerDragListener(this);
//
//        markerClicked = false;
//        LatLng sydney = new LatLng(latidude,longitude);
//        MarkerOptions marker = new MarkerOptions()
//
//    }


}

