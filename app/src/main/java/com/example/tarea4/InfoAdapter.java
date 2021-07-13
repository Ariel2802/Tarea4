package com.example.tarea4;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;

import org.json.JSONException;
import org.json.JSONObject;

public class InfoAdapter implements GoogleMap.InfoWindowAdapter {

    Context context;

    public InfoAdapter(Context context) {
        this.context = context;
    }

    @Override
    public View getInfoWindow(Marker marker) {
        View view = LayoutInflater.from(context).inflate(R.layout.lyt_info, null);

        try {
            JSONObject json = new JSONObject(marker.getSnippet());
            ImageView image = view.findViewById(R.id.imageView);
            Glide.with(view).load(json.getString("Imagen")).circleCrop().into(image);

            TextView txtFacultad = view.findViewById(R.id.txtFacultad);
            txtFacultad.setText(marker.getTitle());

            TextView txtDecano = view.findViewById(R.id.txtDecano);
            txtDecano.setText(json.getString("Decano"));

            TextView txtCoord = view.findViewById(R.id.txtCoord);
            LatLng latLng = marker.getPosition();
            String str = String.format("lat: "+ latLng.latitude +"\nlat:"+ latLng.longitude);
            txtCoord.setText(str);

        } catch (JSONException e) {
            System.out.println(e.getMessage());
        }

        return view;
    }

    @Override
    public View getInfoContents(Marker marker) {
        return null;
    }
}
