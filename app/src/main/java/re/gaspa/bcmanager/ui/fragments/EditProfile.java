package re.gaspa.bcmanager.ui.fragments;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.databinding.DataBindingUtil;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlacePicker;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Locale;

import re.gaspa.bcmanager.R;
import re.gaspa.bcmanager.databinding.DialogColorBinding;
import re.gaspa.bcmanager.databinding.FragmentEditProfileBinding;
import re.gaspa.bcmanager.ui.activities.Main;
import re.gaspa.bcmanager.ui.models.BusinessCard;
import re.gaspa.bcmanager.utils.Preferences;

/**
 * Created by gaspare on 28/08/17.
 */

public class EditProfile extends Fragment implements View.OnClickListener {

    private FragmentEditProfileBinding mBinding;
    private DialogColorBinding mBindingColor;
    private BusinessCard personal;
    private int choosed_color;
    private int choose_color;

    private int PLACE_HOME_REQUEST = 1;
    private int PLACE_WORK_REQUEST = 2;
    private int CHOOSE_PROFILE = 3;
    private int CHOOSE_BACKGROUND = 4;
    private Bitmap profileBitmap = null;
    private Bitmap backgroundBitmap = null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        mBinding = DataBindingUtil.inflate(LayoutInflater.from(getContext()),
                R.layout.fragment_edit_profile, container, false);


        mBinding.buttonHomePosition.setOnClickListener(this);
        mBinding.buttonWorkPosition.setOnClickListener(this);

        mBinding.fab.setOnClickListener(this);
        mBinding.fabColor.setOnClickListener(this);
        mBinding.fabProfile.setOnClickListener(this);
        mBinding.fabBackground.setOnClickListener(this);

        this.changeColor(ContextCompat.getColor(this.getContext(), R.color.colorPrimary));

        personal = Preferences.getPersonalBusinessCard(null);
        if (personal == null) personal = new BusinessCard();
        else load_data();

        return mBinding.getRoot();
    }

    private void load_data() {

        if (personal == null) return;

        String nome = personal.getNome();
        String telefono = personal.getTelefono();
        String email = personal.getEmail();
        String sito = personal.getSito();
        String telegam = personal.getTelegram();
        String colore = personal.getColore();

        String lavoroRuolo = personal.getLavoroRuolo();
        String lavoroLuogo = personal.getLavoroLuogo();
        Location lavoroCoordinate = personal.getLavoroCoordinate();

        String casaCitta = personal.getCasaCitta();
        String casaStrada = personal.getCasaStrada();
        Location casaCoordinate = personal.getCasaCoordinate();

        Bitmap profilo = personal.getProfilo();
        Bitmap sfondo = personal.getSfondo();

        if (profilo != null)
            mBinding.imageProfile.setImageBitmap(profilo);

        if (sfondo != null)
            mBinding.imageBackground.setImageBitmap(sfondo);

        if (nome != null)
            mBinding.textName.setText(nome);

        if (telefono != null)
            mBinding.textTelefono.setText(telefono);

        if (email != null)
            mBinding.textMail.setText(email);

        if (sito != null)
            mBinding.textWebsite.setText(sito);

        if (telegam != null)
            mBinding.textTelegram.setText(telegam);

        if (colore != null && colore.length() > 0) {
            choosed_color = Color.parseColor(colore);
            changeColor(choosed_color);
        }

        if (lavoroLuogo != null)
            mBinding.textJobplace.setText(lavoroLuogo);
        if (lavoroRuolo != null)
            mBinding.textRole.setText(lavoroRuolo);
        if (lavoroCoordinate != null)
            mBinding.textJobCoord.setText(lavoroCoordinate.getLatitude() + " " + lavoroCoordinate.getLongitude());

        if (casaCitta != null)
            mBinding.textCity.setText(casaCitta);
        if (casaStrada != null)
            mBinding.textStreet.setText(casaStrada);
        if (casaCoordinate != null)
            mBinding.textHomeCoord.setText(casaCoordinate.getLatitude() + " " + casaCoordinate.getLongitude());


    }

    @Override
    public void onClick(View view) {
        int id = view.getId();

        switch (id) {
            case R.id.fab_color1:
                select_color(1);
                break;
            case R.id.fab_color2:
                select_color(2);
                break;
            case R.id.fab_color3:
                select_color(3);
                break;
            case R.id.fab_color4:
                select_color(4);
                break;
            case R.id.fab_color5:
                select_color(5);
                break;
            case R.id.fab_color6:
                select_color(6);
                break;
            case R.id.fab_color7:
                select_color(7);
                break;
            case R.id.fab_color8:
                select_color(8);
                break;
            case R.id.fab_color9:
                select_color(9);
                break;
            case R.id.fab_color10:
                select_color(10);
                break;
            case R.id.fab_color11:
                select_color(11);
                break;
            case R.id.fab_color12:
                select_color(12);
                break;
            case R.id.fab_color13:
                select_color(13);
                break;
            case R.id.fab_color14:
                select_color(14);
                break;
            case R.id.fab_color15:
                select_color(15);
                break;
            case R.id.fab_color16:
                select_color(16);
                break;
            default:
                break;
        }

        if (id == R.id.button_home_position) {

            PlacePicker.IntentBuilder builder = new PlacePicker.IntentBuilder();

            try {
                startActivityForResult(builder.build(getActivity()), PLACE_HOME_REQUEST);
            } catch (GooglePlayServicesRepairableException | GooglePlayServicesNotAvailableException e) {
                e.printStackTrace();
            }
        } else if (id == R.id.button_work_position) {

            PlacePicker.IntentBuilder builder = new PlacePicker.IntentBuilder();

            try {
                startActivityForResult(builder.build(getActivity()), PLACE_WORK_REQUEST);
            } catch (GooglePlayServicesRepairableException | GooglePlayServicesNotAvailableException e) {
                e.printStackTrace();
            }

        } else if (id == R.id.fab_profile) {
            Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            if (takePictureIntent.resolveActivity(getActivity().getPackageManager()) != null) {
                startActivityForResult(takePictureIntent, CHOOSE_PROFILE);
            }

        } else if (id == R.id.fab_background) {
            Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
            photoPickerIntent.setType("image/*");
            startActivityForResult(photoPickerIntent, CHOOSE_BACKGROUND);
           /* Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            if (takePictureIntent.resolveActivity(getActivity().getPackageManager()) != null) {
                startActivityForResult(takePictureIntent, CHOOSE_BACKGROUND);
            }*/
        } else if (id == R.id.fab_color) {
            mBindingColor = DataBindingUtil.inflate(getActivity().getLayoutInflater(),
                    R.layout.dialog_color, (ViewGroup) this.getView().getParent(), false);

            choose_color = ContextCompat.getColor(getContext(), R.color.colorPicker1);
            mBindingColor.fabColor1.setOnClickListener(this);
            mBindingColor.fabColor2.setOnClickListener(this);
            mBindingColor.fabColor3.setOnClickListener(this);
            mBindingColor.fabColor4.setOnClickListener(this);
            mBindingColor.fabColor5.setOnClickListener(this);
            mBindingColor.fabColor6.setOnClickListener(this);
            mBindingColor.fabColor7.setOnClickListener(this);
            mBindingColor.fabColor8.setOnClickListener(this);
            mBindingColor.fabColor9.setOnClickListener(this);
            mBindingColor.fabColor10.setOnClickListener(this);
            mBindingColor.fabColor11.setOnClickListener(this);
            mBindingColor.fabColor12.setOnClickListener(this);
            mBindingColor.fabColor13.setOnClickListener(this);
            mBindingColor.fabColor14.setOnClickListener(this);
            mBindingColor.fabColor15.setOnClickListener(this);
            mBindingColor.fabColor16.setOnClickListener(this);

            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setView(mBindingColor.getRoot());
            builder.setTitle(getActivity().getString(R.string.choose_color));
            builder.setPositiveButton(R.string.alert_dialog_ok, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    choosed_color = choose_color;
                    changeColor(choose_color);
                    dialogInterface.dismiss();
                }
            });
            builder.setNegativeButton(R.string.alert_dialog_cancel, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                }
            });
            builder.create().show();
        } else if (id == R.id.fab) {
            personal.setNome(mBinding.textName.getText().toString());
            personal.setTelefono(mBinding.textTelefono.getText().toString());
            personal.setEmail(mBinding.textMail.getText().toString());
            personal.setSito(mBinding.textWebsite.getText().toString());
            personal.setTelegram(mBinding.textTelegram.getText().toString());
            personal.setColore(String.format("#%06X", (0xFFFFFF & choosed_color)));

            personal.setProfilo(profileBitmap);
            personal.setSfondo(backgroundBitmap);

            personal.setCasaCitta(mBinding.textCity.getText().toString());
            personal.setCasaStrada(mBinding.textStreet.getText().toString());

            Location casaCoordinate = new Location(LocationManager.GPS_PROVIDER);
            String casaCoordinateString = mBinding.textHomeCoord.getText().toString();
            if (casaCoordinateString.length() > 0) {
                String v[] = casaCoordinateString.split(" ");
                if (v.length == 2) {
                    casaCoordinate.setLatitude(Double.valueOf(v[0]));
                    casaCoordinate.setLongitude(Double.valueOf(v[1]));
                }
            }
            personal.setCasaCoordinate(casaCoordinate);

            personal.setLavoroRuolo(mBinding.textRole.getText().toString());
            personal.setLavoroLuogo(mBinding.textJobplace.getText().toString());

            Location lavoroCoordinate = new Location(LocationManager.GPS_PROVIDER);
            String lavoroCoordinateString = mBinding.textJobCoord.getText().toString();
            if (lavoroCoordinateString.length() > 0) {
                String v[] = lavoroCoordinateString.split(" ");
                if (v.length == 2) {
                    lavoroCoordinate.setLatitude(Double.valueOf(v[0]));
                    lavoroCoordinate.setLongitude(Double.valueOf(v[1]));
                }
            }
            personal.setLavoroCoordinate(lavoroCoordinate);

            Preferences.setPersonalBusinessCard(null, personal);
            Toast.makeText(getContext(), "Profilo salvato!", Toast.LENGTH_LONG).show();

            try {
                ((Main) getActivity()).updateProfile();
            } catch (Exception e) {

            }
        }
    }

    private void select_color(int i) {
        mBindingColor.fabColor1.setImageDrawable(null);
        mBindingColor.fabColor2.setImageDrawable(null);
        mBindingColor.fabColor3.setImageDrawable(null);
        mBindingColor.fabColor4.setImageDrawable(null);
        mBindingColor.fabColor5.setImageDrawable(null);
        mBindingColor.fabColor6.setImageDrawable(null);
        mBindingColor.fabColor7.setImageDrawable(null);
        mBindingColor.fabColor8.setImageDrawable(null);
        mBindingColor.fabColor9.setImageDrawable(null);
        mBindingColor.fabColor10.setImageDrawable(null);
        mBindingColor.fabColor11.setImageDrawable(null);
        mBindingColor.fabColor12.setImageDrawable(null);
        mBindingColor.fabColor13.setImageDrawable(null);
        mBindingColor.fabColor14.setImageDrawable(null);
        mBindingColor.fabColor15.setImageDrawable(null);
        mBindingColor.fabColor16.setImageDrawable(null);
        switch (i) {
            case 1:
                mBindingColor.fabColor1.setImageResource(R.drawable.icon_done);
                choose_color = ContextCompat.getColor(getContext(), R.color.colorPicker1);
                break;
            case 2:
                mBindingColor.fabColor2.setImageResource(R.drawable.icon_done);
                choose_color = ContextCompat.getColor(getContext(), R.color.colorPicker2);
                break;
            case 3:
                mBindingColor.fabColor3.setImageResource(R.drawable.icon_done);
                choose_color = ContextCompat.getColor(getContext(), R.color.colorPicker3);
                break;
            case 4:
                mBindingColor.fabColor4.setImageResource(R.drawable.icon_done);
                choose_color = ContextCompat.getColor(getContext(), R.color.colorPicker4);
                break;
            case 5:
                mBindingColor.fabColor5.setImageResource(R.drawable.icon_done);
                choose_color = ContextCompat.getColor(getContext(), R.color.colorPicker5);
                break;
            case 6:
                mBindingColor.fabColor6.setImageResource(R.drawable.icon_done);
                choose_color = ContextCompat.getColor(getContext(), R.color.colorPicker6);
                break;
            case 7:
                mBindingColor.fabColor7.setImageResource(R.drawable.icon_done);
                choose_color = ContextCompat.getColor(getContext(), R.color.colorPicker7);
                break;
            case 8:
                mBindingColor.fabColor8.setImageResource(R.drawable.icon_done);
                choose_color = ContextCompat.getColor(getContext(), R.color.colorPicker8);
                break;
            case 9:
                mBindingColor.fabColor9.setImageResource(R.drawable.icon_done);
                choose_color = ContextCompat.getColor(getContext(), R.color.colorPicker9);
                break;
            case 10:
                mBindingColor.fabColor10.setImageResource(R.drawable.icon_done);
                choose_color = ContextCompat.getColor(getContext(), R.color.colorPicker10);
                break;
            case 11:
                mBindingColor.fabColor11.setImageResource(R.drawable.icon_done);
                choose_color = ContextCompat.getColor(getContext(), R.color.colorPicker11);
                break;
            case 12:
                mBindingColor.fabColor12.setImageResource(R.drawable.icon_done);
                choose_color = ContextCompat.getColor(getContext(), R.color.colorPicker12);
                break;
            case 13:
                mBindingColor.fabColor13.setImageResource(R.drawable.icon_done);
                choose_color = ContextCompat.getColor(getContext(), R.color.colorPicker13);
                break;
            case 14:
                mBindingColor.fabColor14.setImageResource(R.drawable.icon_done);
                choose_color = ContextCompat.getColor(getContext(), R.color.colorPicker14);
                break;
            case 15:
                mBindingColor.fabColor15.setImageResource(R.drawable.icon_done);
                choose_color = ContextCompat.getColor(getContext(), R.color.colorPicker15);
                break;
            case 16:
                mBindingColor.fabColor16.setImageResource(R.drawable.icon_done);
                choose_color = ContextCompat.getColor(getContext(), R.color.colorPicker16);
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PLACE_HOME_REQUEST) {
            if (resultCode == Activity.RESULT_OK) {
                Place place = PlacePicker.getPlace(data, getActivity());

                mBinding.textCity.setText(place.getAddress());
                mBinding.textStreet.setText(place.getAddress());
                mBinding.textHomeCoord.setText(place.getLatLng().latitude + " " + place.getLatLng().longitude);

                Geocoder gc = new Geocoder(this.getContext(), Locale.getDefault());

                // TODO May in async task?
                if (gc.isPresent()) {
                    List<Address> addresses = null;
                    try {
                        addresses = gc.getFromLocation(place.getLatLng().latitude, place.getLatLng().longitude, 1);

                        if (addresses.size() > 0) {
                            mBinding.textCity.setText(addresses.get(0).getLocality());
                            mBinding.textStreet.setText(addresses.get(0).getAddressLine(0));
                        }
                    } catch (IOException e) {

                    }
                }
            }
        } else if (requestCode == PLACE_WORK_REQUEST) {
            if (resultCode == Activity.RESULT_OK) {
                Place place = PlacePicker.getPlace(data, getActivity());

                mBinding.textJobplace.setText(place.getAddress());
                mBinding.textJobCoord.setText(place.getLatLng().latitude + " " + place.getLatLng().longitude);
            }
        } else if (requestCode == CHOOSE_PROFILE) {
            if (resultCode == Activity.RESULT_OK) {
                Bundle extras = data.getExtras();
                profileBitmap = (Bitmap) extras.get("data");
                mBinding.imageProfile.setImageBitmap(profileBitmap);
            }
        } else if (requestCode == CHOOSE_BACKGROUND) {
            if (resultCode == Activity.RESULT_OK) {
                try {
                    Uri selectedImage = data.getData();
                    InputStream imageStream = getActivity().getContentResolver().openInputStream(selectedImage);
                    backgroundBitmap = BitmapFactory.decodeStream(imageStream);
                    mBinding.imageBackground.setImageBitmap(backgroundBitmap);
                } catch (FileNotFoundException e) {
                    Log.d("EXCEPTION", e.toString());
                }
            }
        }
    }

    public void changeColor(int color) {
        mBinding.iconHome.setImageTintList(ColorStateList.valueOf(color));
        mBinding.iconHomeCoord.setImageTintList(ColorStateList.valueOf(color));
        mBinding.iconJobCoord.setImageTintList(ColorStateList.valueOf(color));
        mBinding.iconMail.setImageTintList(ColorStateList.valueOf(color));
        mBinding.iconName.setImageTintList(ColorStateList.valueOf(color));
        mBinding.iconTelefono.setImageTintList(ColorStateList.valueOf(color));
        mBinding.iconTelegram.setImageTintList(ColorStateList.valueOf(color));
        mBinding.iconWebsite.setImageTintList(ColorStateList.valueOf(color));
        mBinding.iconWork.setImageTintList(ColorStateList.valueOf(color));

        mBinding.imageProfile.setBorderColor(color);

        mBinding.fabColor.setBackgroundTintList(ColorStateList.valueOf(color));
        mBinding.fabBackground.setBackgroundTintList(ColorStateList.valueOf(color));
        mBinding.fabProfile.setBackgroundTintList(ColorStateList.valueOf(color));

    }
}
