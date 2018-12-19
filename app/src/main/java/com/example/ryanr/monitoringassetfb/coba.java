//import com.google.android.gms.common.ConnectionResult;
//import com.google.android.gms.common.GooglePlayServicesUtil;
//import com.google.android.gms.maps.CameraUpdateFactory;
//import com.google.android.gms.maps.GoogleMap;
//import com.google.android.gms.maps.GoogleMap.OnMapLongClickListener;
//import com.google.android.gms.maps.GoogleMap.OnMarkerDragListener;
//import com.google.android.gms.maps.MapFragment;
//import com.google.android.gms.maps.GoogleMap.OnMapClickListener;
//import com.google.android.gms.maps.model.LatLng;
//import com.google.android.gms.maps.model.Marker;
//import com.google.android.gms.maps.model.MarkerOptions;
//import com.google.android.gms.maps.model.Polygon;
//import com.google.android.gms.maps.model.PolygonOptions;
//
//import android.app.Activity;
//import android.app.AlertDialog;
//import android.app.FragmentManager;
//import android.location.Location;
//import android.os.Bundle;
//import android.view.Menu;
//import android.view.MenuItem;
//import android.widget.TextView;
//import android.widget.Toast;
//
//public class MainActivity extends Activity implements OnMapClickListener, OnMapLongClickListener, OnMarkerDragListener {
//
//    final int RQS_GooglePlayServices = 1;
//    private GoogleMap myMap;
//
//    Location myLocation;
//    TextView tvLocInfo;
//
//    boolean markerClicked;
//    PolygonOptions polygonOptions;
//    Polygon polygon;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//
//        tvLocInfo = (TextView) findViewById(R.id.locinfo);
//
//        FragmentManager myFragmentManager = getFragmentManager();
//        MapFragment myMapFragment = (MapFragment) myFragmentManager.findFragmentById(R.id.map);
//        myMap = myMapFragment.getMap();
//
//        myMap.setMyLocationEnabled(true);
//
//        myMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
//
//        myMap.setOnMapClickListener(this);
//        myMap.setOnMapLongClickListener(this);
//        myMap.setOnMarkerDragListener(this);
//
//        markerClicked = false;
//    }
//
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.activity_main, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        switch (item.getItemId()) {
//            case R.id.menu_legalnotices:
//                String LicenseInfo = GooglePlayServicesUtil.getOpenSourceSoftwareLicenseInfo(
//                        getApplicationContext());
//                AlertDialog.Builder LicenseDialog = new AlertDialog.Builder(MainActivity.this);
//                LicenseDialog.setTitle("Legal Notices");
//                LicenseDialog.setMessage(LicenseInfo);
//                LicenseDialog.show();
//                return true;
//        }
//        return super.onOptionsItemSelected(item);
//    }
//
//    @Override
//    protected void onResume() {
//
//        super.onResume();
//
//        int resultCode = GooglePlayServicesUtil.isGooglePlayServicesAvailable(getApplicationContext());
//
//        if (resultCode == ConnectionResult.SUCCESS) {
//            Toast.makeText(getApplicationContext(),
//                    "isGooglePlayServicesAvailable SUCCESS",
//                    Toast.LENGTH_LONG).show();
//        } else {
//            GooglePlayServicesUtil.getErrorDialog(resultCode, this, RQS_GooglePlayServices);
//        }
//
//    }
//
//    @Override
//    public void onMapClick(LatLng point) {
//        tvLocInfo.setText(point.toString());
//        myMap.animateCamera(CameraUpdateFactory.newLatLng(point));
//
//        markerClicked = false;
//    }
//
//    @Override
//    public void onMapLongClick(LatLng point) {
//        tvLocInfo.setText("New marker added@" + point.toString());
//        myMap.addMarker(new MarkerOptions()
//                .position(point)
//                .draggable(true));
//
//        markerClicked = false;
//    }
//
//    @Override
//    public void onMarkerDrag(Marker marker) {
//        tvLocInfo.setText("Marker " + marker.getId() + " Drag@" + marker.getPosition());
//    }
//
//    @Override
//    public void onMarkerDragEnd(Marker marker) {
//        tvLocInfo.setText("Marker " + marker.getId() + " DragEnd");
//    }
//
//    @Override
//    public void onMarkerDragStart(Marker marker) {
//        tvLocInfo.setText("Marker " + marker.getId() + " DragStart");
//
//    }
//
//}