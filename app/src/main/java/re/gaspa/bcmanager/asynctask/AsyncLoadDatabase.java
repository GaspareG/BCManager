package re.gaspa.bcmanager.asynctask;

import android.content.Context;
import android.os.AsyncTask;

import java.util.ArrayList;

import re.gaspa.bcmanager.listeners.OnDatabaseLoadListener;
import re.gaspa.bcmanager.ui.models.BusinessCard;
import re.gaspa.bcmanager.utils.Database;


public class AsyncLoadDatabase extends AsyncTask<Void, Void, ArrayList<BusinessCard>> {

    private final Context context;
    private final OnDatabaseLoadListener listener;
    private boolean preferite;
    private boolean clear;
    private String name;

    public AsyncLoadDatabase(Context context, OnDatabaseLoadListener listener, boolean clear, String name, boolean preferite) {
        this.listener = listener;
        this.context = context;
        this.preferite = preferite;
        this.name = name;
        this.clear = clear;
    }

    protected ArrayList<BusinessCard> doInBackground(Void... voids) {
        Database.getDatabase(clear);
        if (context != null && clear) Database.createTable(context);
        return Database.getBusinessCards(preferite, name);
    }


    protected void onPostExecute(ArrayList<BusinessCard> result) {
        if (listener != null)
            listener.OnDatabaseLoad(result);
    }

}
