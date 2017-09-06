package re.gaspa.bcmanager.ui.fragments;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlacePicker;

import re.gaspa.bcmanager.R;
import re.gaspa.bcmanager.databinding.FragmentEditProfileBinding;

/**
 * Created by gaspare on 28/08/17.
 */

public class EditProfile extends Fragment implements View.OnClickListener {

    private FragmentEditProfileBinding mBinding;
    private int PLACE_HOME_REQUEST = 1;
    private int PLACE_WORK_REQUEST = 2;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        mBinding = DataBindingUtil.inflate(LayoutInflater.from(getContext()),
                R.layout.fragment_edit_profile, container, false);


        mBinding.buttonHomePosition.setOnClickListener(this);
        mBinding.buttonWorkPosition.setOnClickListener(this);

        mBinding.fab.setOnClickListener(this);

        return mBinding.getRoot();
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();

        if( id == R.id.button_home_position )
        {

            PlacePicker.IntentBuilder builder = new PlacePicker.IntentBuilder();

            try {
                startActivityForResult(builder.build(getActivity()), PLACE_HOME_REQUEST);
            } catch (GooglePlayServicesRepairableException e) {
                e.printStackTrace();
            } catch (GooglePlayServicesNotAvailableException e) {
                e.printStackTrace();
            }
        }
        else if( id == R.id.button_work_position )
        {

            PlacePicker.IntentBuilder builder = new PlacePicker.IntentBuilder();

            try {
                startActivityForResult(builder.build(getActivity()), PLACE_WORK_REQUEST);
            } catch (GooglePlayServicesRepairableException e) {
                e.printStackTrace();
            } catch (GooglePlayServicesNotAvailableException e) {
                e.printStackTrace();
            }
        }
        else if( id == R.id.fab )
        {

        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PLACE_HOME_REQUEST) {
            if (resultCode == Activity.RESULT_OK) {
                Place place = PlacePicker.getPlace(data, getActivity());
                //locationButton.setText(String.format("Place: %s", place.getName()));
                String toastMsg = String.format("Place: %s", place.getName());
                place.getLatLng();

                mBinding.textCity.setText(place.getAddress());
                mBinding.textStreet.setText(place.getAddress());
                mBinding.textHomeCoord.setText(place.getLatLng().latitude + " " + place.getLatLng().longitude);
                Toast.makeText(getContext(), toastMsg, Toast.LENGTH_LONG).show();
            }
        }
        else if (requestCode == PLACE_WORK_REQUEST) {
            if (resultCode == Activity.RESULT_OK) {
                Place place = PlacePicker.getPlace(data, getActivity());
                //locationButton.setText(String.format("Place: %s", place.getName()));
                String toastMsg = String.format("Place: %s", place.getName());
                place.getLatLng();

                mBinding.textJobplace.setText(place.getAddress());
                mBinding.textJobCoord.setText(place.getLatLng().latitude + " " + place.getLatLng().longitude);
                Toast.makeText(getContext(), toastMsg, Toast.LENGTH_LONG).show();
            }
        }
    }
}
