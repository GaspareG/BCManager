package re.gaspa.bcmanager.ui.activities;

import android.content.Intent;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import re.gaspa.bcmanager.R;
import re.gaspa.bcmanager.ui.models.BusinessCard;
import re.gaspa.bcmanager.utils.Database;
import re.gaspa.bcmanager.utils.Utils;

public class OpenVcard extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_vcard);

        Intent i = getIntent();
        String action = i.getAction();
        String type = i.getType();

        Log.d("INTENT", "RICEVUTO QUALCOSA");

        if (Intent.ACTION_VIEW.equals(action) && type != null) {
            Uri uri = i.getData();
            if (uri != null) {
                Toast.makeText(this, uri.toString(), Toast.LENGTH_SHORT).show();
            }
            try {
                BusinessCard toAdd = new BusinessCard();
                InputStream is = getContentResolver().openInputStream(uri);
                BufferedReader reader = new BufferedReader(new InputStreamReader(is));
                String line = reader.readLine();
                while (line != null) {
                    int idx = line.indexOf("=");
                    if( idx != -1 )
                    {
                        String key = line.substring(0, idx);
                        String value = line.substring(idx+1);
                        if( !( key.length() == 0 || value.length() == 0) )
                        switch ( key.toUpperCase() )
                        {
                            case "NAME":
                                toAdd.setNome(value);
                                break;
                            case "PHONE":
                                toAdd.setTelefono(value);
                                break;
                            case "EMAIL":
                                toAdd.setEmail(value);
                                break;
                            case "WEBSITE":
                                toAdd.setSito(value);
                                break;
                            case "TELEGRAM":
                                toAdd.setTelegram(value);
                                break;
                            case "COLOR":
                                toAdd.setColore(value);
                                break;
                            case "HOMECITY":
                                toAdd.setCasaCitta(value);
                                break;
                            case "HOMESTREET":
                                toAdd.setCasaStrada(value);
                                break;
                            case "JOBROLE":
                                toAdd.setLavoroRuolo(value);
                                break;
                            case "JOBPLACE":
                                toAdd.setLavoroLuogo(value);
                                break;
                            case "PROFILE":
                                toAdd.setProfilo(Utils.decodeBase64(value));
                                break;
                            case "BACKGROUND":
                                toAdd.setProfilo(Utils.decodeBase64(value));
                                break;
                            case "HOMECOORD":
                                String coord[] = value.replace(" ","").split(",");
                                if( coord.length == 2 )
                                {
                                    Location location = new Location(LocationManager.GPS_PROVIDER);
                                    location.setLatitude(Double.valueOf(coord[0]));
                                    location.setLongitude(Double.valueOf(coord[1]));
                                    toAdd.setCasaCoordinate(location);
                                }
                                break;
                            case "JOBCOORD":
                                String coordJ[] = value.replace(" ", "").split(",");
                                if (coordJ.length == 2) {
                                    Location location = new Location(LocationManager.GPS_PROVIDER);
                                    location.setLatitude(Double.valueOf(coordJ[0]));
                                    location.setLongitude(Double.valueOf(coordJ[1]));
                                    toAdd.setLavoroCoordinate(location);
                                }
                                break;
                        }

                    }
                    line = reader.readLine();
                }
                Database.addBusinessCard(toAdd);
                Intent intent = new Intent(getApplicationContext(), Main.class);
                getApplicationContext().startActivity(intent);

            }
            catch (IOException e) {

            }
        }

        setResult(RESULT_OK);


    }

    @Override
    protected void onNewIntent(Intent intent) {

        Log.d("INTENT", "RICEVUTO QUALCOSA");

    }


}
