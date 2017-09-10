package re.gaspa.bcmanager.ui.activities;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import re.gaspa.bcmanager.R;
import re.gaspa.bcmanager.asynctask.AsyncLoadDatabase;
import re.gaspa.bcmanager.listeners.OnDatabaseLoadListener;
import re.gaspa.bcmanager.ui.models.BusinessCard;
import re.gaspa.bcmanager.utils.Preferences;

public class Splash extends AppCompatActivity implements OnDatabaseLoadListener {

    private final int STORAGE_PERMISSION = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);


        Preferences.getPreferences(this.getBaseContext());

        int statoPermission = ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION);

        if (statoPermission == PackageManager.PERMISSION_DENIED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, STORAGE_PERMISSION);

        } else continueSplash();

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String permissions[], @NonNull int[] grantResults) {
        switch (requestCode) {
            case STORAGE_PERMISSION:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    continueSplash();
                } else {
                    Toast.makeText(this, R.string.permission, Toast.LENGTH_SHORT).show();
                    finish();
                }
                break;
        }
    }

    public void continueSplash() {
        final Context context = this.getApplicationContext();
        final Splash th = this;
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                new AsyncLoadDatabase(context, th, Preferences.getFirstOpen(null), "", false).execute();
            }
        }, 500);
    }

    @Override
    public void OnDatabaseLoad(ArrayList<BusinessCard> businessCards) {
        if( Preferences.getFirstOpen(null) )
        {
            Preferences.setFirstOpen(null, false);
            Intent intent = new Intent(getApplicationContext(), Help.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            getApplicationContext().startActivity(intent);
        }
        else
        {
            Intent intent = new Intent(getApplicationContext(), Main.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            getApplicationContext().startActivity(intent);
        }
    }
}
