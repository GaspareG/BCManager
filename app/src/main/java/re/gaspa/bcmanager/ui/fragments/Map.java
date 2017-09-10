package re.gaspa.bcmanager.ui.fragments;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

import re.gaspa.bcmanager.R;
import re.gaspa.bcmanager.asynctask.AsyncLoadDatabase;
import re.gaspa.bcmanager.databinding.FragmentMapBinding;
import re.gaspa.bcmanager.ui.activities.BusinessCardActivity;
import re.gaspa.bcmanager.listeners.OnDatabaseLoadListener;
import re.gaspa.bcmanager.ui.models.BusinessCard;

public class Map extends Fragment implements OnMapReadyCallback, GoogleMap.OnMarkerClickListener, OnDatabaseLoadListener {

    private GoogleMap map;

    private MapView mapView;
    private ArrayList<Marker> markers;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        FragmentMapBinding mBinding = DataBindingUtil.inflate(LayoutInflater.from(getContext()),
                R.layout.fragment_map, container, false);

        markers = new ArrayList<>();

        mapView = mBinding.mapView;
        mapView.onCreate(savedInstanceState);
        mapView.onResume();
        mapView.getMapAsync(this);
        return mBinding.getRoot();
    }


    @Override
    public void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;
        map.setOnMarkerClickListener(this);

        new AsyncLoadDatabase(null, this, false, "", false).execute();

    }

    public void addMarker(BusinessCard businessCard) {
        Location casa = businessCard.getCasaCoordinate();
        if (casa == null || (casa.getLongitude() == 0.0 && casa.getLatitude() == 0.0)) return;
        LatLng latLng = new LatLng(casa.getLatitude(), casa.getLongitude());
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(latLng).title(businessCard.getNome());
        Marker marker = map.addMarker(markerOptions);
        marker.setTag(businessCard);
        markers.add(marker);

    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        BusinessCard obj = (BusinessCard) marker.getTag();
        Intent intent = new Intent(this.getContext(), BusinessCardActivity.class);
        intent.putExtra("businesscard", obj);
        this.getContext().startActivity(intent);
        return true;
    }

    @Override
    public void OnDatabaseLoad(ArrayList<BusinessCard> businessCards) {
        for (Marker m : markers) m.remove();
        map.clear();
        markers.clear();
        for (BusinessCard bc : businessCards) addMarker(bc);
    }
}
