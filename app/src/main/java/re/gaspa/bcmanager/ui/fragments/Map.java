package re.gaspa.bcmanager.ui.fragments;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.databinding.DataBindingUtil;
import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

import re.gaspa.bcmanager.R;
import re.gaspa.bcmanager.databinding.FragmentMapBinding;
import re.gaspa.bcmanager.ui.activities.BusinessCardActivity;
import re.gaspa.bcmanager.ui.models.BusinessCard;
import re.gaspa.bcmanager.utils.Database;

/**
 * Created by gaspare on 28/08/17.
 */

public class Map extends Fragment implements OnMapReadyCallback, GoogleMap.OnMarkerClickListener {

    private FragmentMapBinding mBinding;
    private SupportMapFragment supportMapFragment;
    private GoogleMap map;
    private MarkerOptions currentPositionMarker = null;
    private Marker currentLocationMarker;
    private Context mContext;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        mContext = getActivity();
                mBinding = DataBindingUtil.inflate(LayoutInflater.from(getContext()),
                R.layout.fragment_map, container, false);


        FragmentManager fm = getChildFragmentManager();
      /*  supportMapFragment = (SupportMapFragment) fm.findFragmentById(R.id.map);
        if (supportMapFragment == null) {
            supportMapFragment = SupportMapFragment.newInstance();
            fm.beginTransaction().replace(R.id.map_container, supportMapFragment).commit();
        }*/

        supportMapFragment = (SupportMapFragment) fm.findFragmentByTag("mapFragment");
        if (supportMapFragment == null) {
            supportMapFragment = new SupportMapFragment();
            FragmentTransaction ft = fm.beginTransaction();
            ft.add(R.id.map_container, supportMapFragment, "mapFragment");
            ft.commit();
            fm.executePendingTransactions();
        }

        supportMapFragment.getMapAsync(this);

        return mBinding.getRoot();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;
        map.setOnMarkerClickListener(this);

        for( BusinessCard bc : Database.getBusinessCards() ) addMarker(bc);
    }

    public void addMarker(BusinessCard businessCard)
    {
        Location casa = businessCard.getCasaCoordinate();
        if( casa == null || ( casa.getLongitude() == 0.0 && casa.getLatitude() == 0.0 ) ) return;

        LatLng latLng = new LatLng(casa.getLatitude(), casa.getLongitude());
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(latLng).title(businessCard.getNome());
        Marker marker = map.addMarker(markerOptions);
        marker.setTag(businessCard);

    }

    @Override
    public boolean onMarkerClick(Marker marker) {

        BusinessCard obj = (BusinessCard) marker.getTag();
        Intent intent = new Intent(this.getContext(), BusinessCardActivity.class);
        intent.putExtra("businesscard", obj);
        this.getContext().startActivity(intent);

        return true;
    }
}
