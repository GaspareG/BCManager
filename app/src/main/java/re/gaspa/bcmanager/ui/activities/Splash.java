git /*
    TODO:
    - Pagina Aiuto 0
    - Pagina Aiuto 1

    - Filtro preferiti
    - Aggiornamento homepage quando cambiano i dati

    - NFC, serializzazione (TO CHECK)

    - Import/Export bluetooth

 */

package re.gaspa.bcmanager.ui.activities;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import re.gaspa.bcmanager.R;
import re.gaspa.bcmanager.ui.models.BusinessCard;
import re.gaspa.bcmanager.utils.Database;
import re.gaspa.bcmanager.utils.Preferences;

public class Splash extends AppCompatActivity {

    private final int STORAGE_PERMISSION = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Preferences.getPreferences(this.getBaseContext());

        int statoPermission = ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION);

        if (statoPermission == PackageManager.PERMISSION_DENIED) {
            Toast.makeText(this, "PERMISSION DENIED", Toast.LENGTH_SHORT);
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, STORAGE_PERMISSION);

        } else continueSplash();

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case STORAGE_PERMISSION:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    continueSplash();
                } else {
                    Toast.makeText(this, "NO", Toast.LENGTH_SHORT).show();
                    finish();
                }
                break;
        }
    }

    public void continueSplash() {
        final Context context = this.getApplicationContext();
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {

                Log.d("FIRST_OPEN", Preferences.getFirstOpen(null) + "");

                if (Preferences.getFirstOpen(null)) {
                    // Creo DB e apro HELP
                    Log.d("FIRST_OPEN", "APRO HELP");
                    Preferences.setFirstOpen(null, false);

                    // Creo DB e apro HELP
                    Log.d("FIRST_OPEN", "CREO DB");
                    Database.getDatabase(true);
                    Database.createTable(context);

                    Log.d("FIRST_OPEN", "CARICO BC");
                    ArrayList<BusinessCard> bc = Database.getBusinessCards();
                    Log.d("FIRST_OPEN", "CARICATE " + bc.size());

                    Intent intent = new Intent(getApplicationContext(), Help.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    getApplicationContext().startActivity(intent);
                } else {
                    Log.d("FIRST_OPEN", "CARICO BC");
                    ArrayList<BusinessCard> bc = Database.getBusinessCards();
                    Log.d("FIRST_OPEN", "CARICATE " + bc.size());

                    Log.d("FIRST_OPEN", "APRO MAIN");
                    Intent intent = new Intent(getApplicationContext(), Main.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    getApplicationContext().startActivity(intent);
                }
            }
        }, 500);
    }
}
