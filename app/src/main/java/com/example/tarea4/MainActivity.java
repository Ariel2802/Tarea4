package com.example.tarea4;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Point;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.Projection;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import modelo.Facultad;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mapa;
    ArrayList<Facultad> marcadores;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Llena una lista con los datos de lasfacultades
        obtenerDatos();

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        //-1.0126843626724575, -79.47106342565016; FCEducacion
        //-1.0125758108940996, -79.47065096525907; FCI
        //-1.0128595993830947, -79.46933742937492 FCUED
        //-1.0121708605533355, -79.47018828421086 FCE
        //-1.0813501459700214, -79.50280461114 FCAgropecuarias Facultad de Ciencias Pecuarias
        //https://raw.githubusercontent.com/Ariel2802/Tarea4/master/MiJSON.json
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mapa = googleMap;

        //Pone los marcadores de acuerdo a la lista creada en onCreate
        ponerMarcadores();
        central();
        mapa.setMapType(2);

        mapa.setInfoWindowAdapter(new InfoAdapter(MainActivity.this));

        mapa.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
            @Override
            public void onInfoWindowClick(Marker marker) {
                Toast.makeText(MainActivity.this,
                        ("Información de: " + marker.getTitle()), Toast.LENGTH_SHORT).show();
            }
        });

        mapa.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                marker.showInfoWindow();
                Toast.makeText(MainActivity.this, marker.getTitle(), Toast.LENGTH_SHORT).show();
                return true;
            }
        });
    }

    private void ponerMarcadores() {
        LatLng latLng;
        Facultad facultad;
        for (int i = 0; i < marcadores.size(); i++) {
            facultad = marcadores.get(i);

            latLng = new LatLng(facultad.getLat(), facultad.getLng());

            mapa.addMarker(new MarkerOptions()
                    .position(latLng)
                    .title(facultad.getFacultad())
                    .snippet(facultad.toString()));
        }

    }

    private void obtenerDatos() {
        marcadores = new ArrayList<>();
        String strJSON = "[\n" +
                "   {\n" +
                "      \"Imagen\":\"https://www.uteq.edu.ec/images/about/logo_enf1.jpg\",\n" +
                "      \"Facultad\":\"Facultad de Ciencias de la Educación\",\n" +
                "      \"Decano\":\"Ing. Juan Piguave, PhD\",\n" +
                "      \"lat\":-1.0126843626724575,\n" +
                "      \"lng\":-79.47106342565016\n" +
                "   },\n" +
                "   {\n" +
                "      \"Imagen\":\"https://www.uteq.edu.ec/images/about/logo_fci.jpg\",\n" +
                "      \"Facultad\":\"Facultad de Ciencias de la Ingeniería\",\n" +
                "      \"Decano\":\"Ing. Washington Alberto Chiriboga Casanova, M.Sc\",\n" +
                "      \"lat\":-1.0125758108940996,\n" +
                "      \"lng\":-79.47065096525907\n" +
                "   },\n" +
                "   {\n" +
                "      \"Imagen\":\"https://www.uteq.edu.ec/images/about/logo_ued.jpg\",\n" +
                "      \"Facultad\":\"Facultad de Unidad de Estudios a Distancia\",\n" +
                "      \"Decano\":\"Ing. Juan Piguave, PhD\",\n" +
                "      \"lat\":-1.0128595993830947,\n" +
                "      \"lng\":-79.46933742937492\n" +
                "   },\n" +
                "   {\n" +
                "      \"Imagen\":\"https://www.uteq.edu.ec/images/about/logo_fce.jpg\",\n" +
                "      \"Facultad\":\"Facultad de Ciencias Empresariales\",\n" +
                "      \"Decano\":\"Ing. Mariela Susana Andrade Arias, PhD\",\n" +
                "      \"lat\":-1.0121708605533355,\n" +
                "      \"lng\":-79.47018828421086\n" +
                "   },\n" +
                "   {\n" +
                "      \"Imagen\":\"https://www.uteq.edu.ec/images/about/logo_fcagrop.jpg\",\n" +
                "      \"Facultad\":\"Facultad de Ciencias Agropecuarias\",\n" +
                "      \"Decano\":\"Ing. Leonardo Gonzalo Matute, M.Sc\",\n" +
                "      \"lat\":-1.0813501459700214,\n" +
                "      \"lng\":-79.50280461114\n" +
                "   }\n" +
                "]\n";
        try {
            JSONArray jsonArray = new JSONArray(strJSON);
            int tamanio = jsonArray.length();
            for (int i = 0; i < tamanio; i++) {
                JSONObject json = jsonArray.getJSONObject(i);
                Facultad facultad = new Facultad(json.getString("Facultad"),
                        json.getString("Decano"), json.getString("Imagen"),
                        json.getDouble("lat"), json.getDouble("lng"));
                marcadores.add(facultad);
            }
        } catch (JSONException ex) {
            System.out.println(ex.toString());
        }
    }

    public void central()
    {
        LatLng central = new LatLng(-1.0121708605533355, -79.46958611046);
        CameraPosition cameraPosition = new CameraPosition.Builder().target(central)
                .zoom(18).build();
        mapa.moveCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
    }

    public void eventoCentral(View v) {
        central();
    }

    public void eventoMaria(View v) {
        LatLng maria = new LatLng(-1.0802524900683261, -79.50147916355766);
        CameraPosition cameraPosition = new CameraPosition.Builder().target(maria)
                .zoom(18).build();
        mapa.moveCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
    }
}