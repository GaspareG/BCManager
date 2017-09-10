package re.gaspa.bcmanager.asynctask;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.os.AsyncTask;

import com.google.android.gms.maps.model.LatLng;

import java.util.List;
import java.util.Locale;

import re.gaspa.bcmanager.listeners.OnGeocodingCompleteListener;


public class AsyncGeocoding extends AsyncTask<Void, Void, List<Address>> {

    private final OnGeocodingCompleteListener listener;
    private final Context context;
    private LatLng coord;

    public AsyncGeocoding(Context context, LatLng coord, OnGeocodingCompleteListener listener) {
        this.context = context;
        this.listener = listener;
        this.coord = coord;
    }

    protected List<Address> doInBackground(Void... voids) {
        List<Address> ret = null;
        if (Geocoder.isPresent()) {
            Geocoder gc = new Geocoder(context, Locale.getDefault());
            try {
                ret = gc.getFromLocation(coord.latitude, coord.longitude, 1);
            } catch (Exception ignored) {

            }
        }
        return ret;
    }

    protected void onPostExecute(List<Address> addresses) {
        listener.OnGeocodingComplete(addresses);
    }

}
