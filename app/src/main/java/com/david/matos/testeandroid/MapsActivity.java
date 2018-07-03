package com.david.matos.testeandroid;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import com.david.matos.testeandroid.DB.Contrato;
import com.david.matos.testeandroid.DB.DB;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    DB mDbHelper;
    SQLiteDatabase db;
    Cursor c, c_pessoas;
    ListView lista;
    SimpleCursorAdapter adapter;
    String[] monumentos;

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        mDbHelper = new DB(getApplicationContext());
        db = mDbHelper.getReadableDatabase();

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng viana = new LatLng(41.693596,  -8.846623);
        mMap.addMarker(new MarkerOptions().position(viana).title("ESTG"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(viana, 18));
        preencheLista(mMap);
    }

    private void preencheLista(GoogleMap mMap) {

        c = db.query(false, Contrato.Monumentos.TABLE_NAME, Contrato.Monumentos.PROJECTION, null, null, null, null, null, null);
        monumentos = new String[c.getCount()];
        int i = 0;
        while(c.moveToNext()){
            System.out.println(i + " " + c.getString(0) + " " + c.getString(1) + " " + c.getString(2) + " " + c.getString(3));
            LatLng aux = new LatLng(Double.parseDouble(c.getString(2)), Double.parseDouble(c.getString(3)));
            mMap.addMarker(new MarkerOptions().position(aux).title(String.valueOf(c.getString(1))));
            i++;
        }


    }
}
