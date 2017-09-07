/*
    TODO:
    - Splash screen
    - Riconoscimento primo avvio
    - Pagina crediti
    - Pagina aiuto
    - Pagina impostazioni
    - Fix bug selezione su condividi
    - Database utenti
    - Marker su mappa
    - Ricerca su lista
    - Preferiti e filtro
    - Export businesscard (testo, vcard, immagine, bluetooth, nfc, wifi)
    - Import businesscard (testo, vcard, immagine, bluetooth, nfc, wifi)
 */

package re.gaspa.bcmanager.ui.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import re.gaspa.bcmanager.R;
import re.gaspa.bcmanager.utils.Utils;

public class Splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Utils.getPreferences(this.getBaseContext());

    }
}
