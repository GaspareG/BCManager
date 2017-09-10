package re.gaspa.bcmanager.ui.listeners;

import android.location.Address;

import java.util.ArrayList;
import java.util.List;

import re.gaspa.bcmanager.ui.models.BusinessCard;

/**
 * Created by gaspare on 10/09/17.
 */

public interface OnGeocodingCompleteListener {

    void OnGeocodingComplete(List<Address> addresses);

}
