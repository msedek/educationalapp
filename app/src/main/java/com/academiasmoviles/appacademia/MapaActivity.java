package com.academiasmoviles.appacademia;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.academiasmoviles.appacademia.Model.Alumno;
import com.academiasmoviles.appacademia.SQLite.DBHelper;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapaActivity extends FragmentActivity implements OnMapReadyCallback{

    double latitud, longitud;
    String direccion;

    DBHelper myDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mapa);

        // Inicializar la bd
        myDB = DBHelper.GetDBHelper(this);

        // Obtenemos datos del alumno desde SQLite
        Intent intent = getIntent();

        if (intent.hasExtra("ID"))
        {
            String id = intent.getExtras().getString("ID");

            obtenerAlumno(id);
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        LatLng latLng = new LatLng(latitud, longitud);

        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(latLng);
        markerOptions.title(direccion);

        googleMap.addMarker(markerOptions);

        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 17));
    }

    private void obtenerAlumno(String id)
    {
        Alumno alumno = myDB.getAlumno(id);

        direccion = alumno.getDireccion();

        latitud = Double.parseDouble(alumno.getLatitud());

        longitud = Double.parseDouble(alumno.getLongitud());

        // Mostramos el mapa
        SupportMapFragment mapFragment =
                (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.activity_mapa);

        mapFragment.getMapAsync(this);
    }
}
