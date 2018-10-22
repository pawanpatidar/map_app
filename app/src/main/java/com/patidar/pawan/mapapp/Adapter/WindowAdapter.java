package com.patidar.pawan.mapapp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;
import com.patidar.pawan.mapapp.R;

import java.util.zip.Inflater;

public class WindowAdapter implements GoogleMap.InfoWindowAdapter {

    private final View mWindow;
    private final Context context;

    public WindowAdapter(Context context) {
        this.context = context;
        mWindow = LayoutInflater.from(context).inflate(R.layout.info_window,null);
    }

    private void setWindowText(Marker marker,View view){
        String title = marker.getTitle();
        String eve_place =marker.getSnippet();
        TextView event_name= view.findViewById(R.id.event_name);
        TextView place =  view.findViewById(R.id.event_locate);
        event_name.setText(title);
        place.setText(eve_place);
    }
    @Override
    public View getInfoWindow(Marker marker) {
        setWindowText(marker,mWindow);
        return mWindow;
    }



    @Override

    public View getInfoContents(Marker marker) {
        setWindowText(marker,mWindow);
        return mWindow;
    }
}
