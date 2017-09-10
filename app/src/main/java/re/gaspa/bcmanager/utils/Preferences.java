package re.gaspa.bcmanager.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.location.Location;
import android.location.LocationManager;
import android.preference.PreferenceManager;
import android.util.Log;

import re.gaspa.bcmanager.ui.models.BusinessCard;

public class Preferences {

    private static BusinessCard personal = null;
    private static SharedPreferences preferences = null;

    public static void setPersonalBusinessCard(Context ctx, BusinessCard personal) {
        Preferences.personal = personal;
        SharedPreferences preferences = getPreferences(ctx);
        SharedPreferences.Editor editor = preferences.edit();

        editor.putString("name", personal.getNome());
        editor.putString("phone", personal.getTelefono());
        editor.putString("email", personal.getEmail());
        editor.putString("website", personal.getSito());
        editor.putString("telegram", personal.getTelegram());
        editor.putString("color", personal.getColore());

        editor.putString("jobRole", personal.getLavoroRuolo());
        editor.putString("jobName", personal.getLavoroLuogo());
        Location lavoroCoordinate = personal.getLavoroCoordinate();
        if (lavoroCoordinate != null) {
            editor.putFloat("jobLat", (float) lavoroCoordinate.getLatitude());
            editor.putFloat("jobLng", (float) lavoroCoordinate.getLongitude());
        }

        editor.putString("houseCity", personal.getCasaCitta());
        editor.putString("houseStreet", personal.getCasaStrada());
        Location casaCoordinate = personal.getCasaCoordinate();
        if (casaCoordinate != null) {
            editor.putFloat("houseLat", (float) casaCoordinate.getLatitude());
            editor.putFloat("houseLng", (float) casaCoordinate.getLongitude());
        }

        Bitmap profilo = personal.getProfilo();
        Bitmap sfondo = personal.getSfondo();
        if (profilo != null) {
            String profiloString = Utils.encodeTobase64(profilo);
            editor.putString("profilo", profiloString);
        }
        if (sfondo != null) {
            String sfondoString = Utils.encodeTobase64(sfondo);
            editor.putString("sfondo", sfondoString);
        }

        editor.apply();
    }

    public static BusinessCard getPersonalBusinessCard(Context ctx) {
        if (Preferences.personal == null) {
            SharedPreferences preferences = getPreferences(ctx);

            String nome = preferences.getString("name", "");
            String telefono = preferences.getString("phone", "");
            String email = preferences.getString("email", "");
            String sito = preferences.getString("website", "");
            String telegram = preferences.getString("telegram", "");
            String colore = preferences.getString("color", "");

            String lavoroRuolo = preferences.getString("jobRole", "");
            String lavoroLuogo = preferences.getString("jobName", "");
            Float lavoroCoordinateLat = preferences.getFloat("jobLat", 0.f);
            Float lavoroCoordinateLng = preferences.getFloat("jobLng", 0.f);
            Location lavoroCoordinate = new Location(LocationManager.GPS_PROVIDER);
            lavoroCoordinate.setLatitude(lavoroCoordinateLat);
            lavoroCoordinate.setLongitude(lavoroCoordinateLng);

            String casaCitta = preferences.getString("houseCity", "");
            String casaStrada = preferences.getString("houseStreet", "");
            Float casaCoordinateLat = preferences.getFloat("houseLat", 0.f);
            Float casaCoordinateLng = preferences.getFloat("houseLng", 0.f);
            Location casaCoordinate = new Location(LocationManager.GPS_PROVIDER);
            casaCoordinate.setLatitude(casaCoordinateLat);
            casaCoordinate.setLongitude(casaCoordinateLng);

            String profilo = preferences.getString("profilo", null);
            String sfondo = preferences.getString("sfondo", null);

            Preferences.personal = new BusinessCard();
            Preferences.personal.setId(0);
            Preferences.personal.setPreferito(false);
            Preferences.personal.setNome(nome);
            Preferences.personal.setTelefono(telefono);
            Preferences.personal.setEmail(email);
            Preferences.personal.setSito(sito);
            Preferences.personal.setTelegram(telegram);
            Preferences.personal.setColore(colore);

            Preferences.personal.setLavoroRuolo(lavoroRuolo);
            Preferences.personal.setLavoroLuogo(lavoroLuogo);
            Preferences.personal.setLavoroCoordinate(lavoroCoordinate);

            Preferences.personal.setCasaCitta(casaCitta);
            Preferences.personal.setCasaStrada(casaStrada);
            Preferences.personal.setCasaCoordinate(casaCoordinate);

            if (profilo != null)
                Preferences.personal.setProfilo(Utils.decodeBase64(profilo));
            if (sfondo != null)
                Preferences.personal.setSfondo(Utils.decodeBase64(sfondo));

        }
        return Preferences.personal;
    }

    public static SharedPreferences getPreferences(Context ctx) {
        if (Preferences.preferences == null && ctx != null) {
            Preferences.preferences = PreferenceManager.getDefaultSharedPreferences(ctx);
        }
        return Preferences.preferences;
    }

    public static boolean getFirstOpen(Context ctx) {
        SharedPreferences preferences = Preferences.getPreferences(ctx);
        return preferences.getBoolean("open", true);
    }

    public static void setFirstOpen(Context ctx, boolean value) {
        SharedPreferences preferences = Preferences.getPreferences(ctx);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean("open", value);
        editor.apply();
    }

    public static void clearData() {
        SharedPreferences preferences = Preferences.getPreferences(null);
        SharedPreferences.Editor editor = preferences.edit();
        editor.clear();
        editor.apply();
    }
}
