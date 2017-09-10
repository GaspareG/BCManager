package re.gaspa.bcmanager.listeners;

import java.util.ArrayList;

import re.gaspa.bcmanager.ui.models.BusinessCard;


public interface OnDatabaseLoadListener {

    void OnDatabaseLoad(ArrayList<BusinessCard> businessCards);

}
