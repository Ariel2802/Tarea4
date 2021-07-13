package com.example.tarea4;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;

import java.util.ArrayList;

public class InfoAdapter implements GoogleMap.InfoWindowAdapter {

    Context context;

    public InfoAdapter(Context context) {
    this.context = context;
    }

    @Override
    public View getInfoWindow(Marker marker) {
        View view = LayoutInflater.from(context).inflate(R.layout.lyt_info, null);
        TextView txtFacultad = view.findViewById(R.id.txtFacultad);
        txtFacultad.setText(marker.getTitle());

        TextView txtCoord = view.findViewById(R.id.txtCoord);
        txtCoord.setText(marker.getSnippet());
        return view;
    }

    @Override
    public View getInfoContents(Marker marker) {
        return null;
    }
}
