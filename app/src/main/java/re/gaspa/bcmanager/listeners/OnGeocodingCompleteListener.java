package re.gaspa.bcmanager.listeners;

import android.location.Address;

import java.util.List;

public interface OnGeocodingCompleteListener {

    void OnGeocodingComplete(List<Address> addresses);

}
