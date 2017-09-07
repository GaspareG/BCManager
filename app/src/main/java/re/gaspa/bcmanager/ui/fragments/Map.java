package re.gaspa.bcmanager.ui.fragments;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.databinding.DataBindingUtil;
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

import re.gaspa.bcmanager.R;
import re.gaspa.bcmanager.databinding.FragmentMapBinding;

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

/*        FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
        ft.remove(fragment);
        ft.commit();*/

        mBinding = DataBindingUtil.inflate(LayoutInflater.from(getContext()),
                R.layout.fragment_map, container, false);


        FragmentManager fm = getActivity().getSupportFragmentManager();/// getChildFragmentManager();
        supportMapFragment = (SupportMapFragment) fm.findFragmentById(R.id.map);
        if (supportMapFragment == null) {
            supportMapFragment = SupportMapFragment.newInstance();
            fm.beginTransaction().replace(R.id.map_container, supportMapFragment).commit();
        }
        supportMapFragment.getMapAsync(this);

        return mBinding.getRoot();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;
        //if (ActivityCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                //   ActivityCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // return;
            //}
        //map.setMyLocationEnabled(true);
        //map.animateCamera(CameraUpdateFactory.zoomTo(15));

        map.setOnMarkerClickListener(this);

        LatLng latLng = new LatLng(0, 0); // new LatLng(currentLatLng.getLatitude(),currentLatLng.getLongitude());
        if(currentPositionMarker == null) {
            currentPositionMarker = new MarkerOptions();
            currentPositionMarker.position(latLng).title("My Location");
            currentLocationMarker = map.addMarker(currentPositionMarker);
            // currentLocationMarker.setTag();
        }
    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        // marker.getTag();
        return true;
    }
}
