package re.gaspa.bcmanager.ui.listeners;

import java.util.ArrayList;

import re.gaspa.bcmanager.ui.models.BusinessCard;

/**
 * Created by gaspare on 10/09/17.
 */

public interface OnDatabaseLoadListener {

    void OnDatabaseLoad(ArrayList<BusinessCard> businessCards);

}
