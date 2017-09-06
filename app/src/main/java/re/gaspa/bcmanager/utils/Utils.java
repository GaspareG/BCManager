package re.gaspa.bcmanager.utils;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.util.Base64;
import android.util.Log;
import android.view.View;

import java.io.ByteArrayOutputStream;

import re.gaspa.bcmanager.ui.activities.Splash;
import re.gaspa.bcmanager.ui.models.BusinessCard;

/**
 * Created by gaspare on 04/09/17.
 */

public class Utils {

    private static BusinessCard personal = null;
    private static SharedPreferences preferences = null;

    public static void openTelegram(String nick, Context context)
    {
        if( nick.startsWith("@") ) nick.replaceFirst("@","");
        Intent telegram = new Intent(android.content.Intent.ACTION_SEND);
        telegram.setData(Uri.parse("http://telegram.me/"+nick));
        telegram.setPackage("org.telegram.messenger");
        context.startActivity(Intent.createChooser(telegram, "Share with"));
    }

    public static void openWebsite(String url, Context context)
    {
        if( !url.startsWith("http://") && !url.startsWith("https://") )
            url = "http://" + url;

        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        context.startActivity(i);
    }

    public static void openMail(String mail, Context context)
    {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        Uri data = Uri.parse("mailto:" + mail);
        intent.setData(data);
        context.startActivity(intent);
    }


    public static void openNumber(String number, Context context) {
        Uri call = Uri.parse("tel:" + number);
        Intent surf = new Intent(Intent.ACTION_DIAL, call);
        context.startActivity(surf);
    }

    public static void openLocation(Location city, Context context) {

        Uri gmmIntentUri = Uri.parse("google.streetview:cbll="+city.getLatitude()+","+city.getLongitude());
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
        mapIntent.setPackage("com.google.android.apps.maps");
        context.startActivity(mapIntent);
    }

    public static void setPersonalBusinessCard(Context ctx, BusinessCard personal)
    {
        Utils.personal = personal;
        SharedPreferences preferences = Utils.getPreferences(ctx);
        SharedPreferences.Editor editor = preferences.edit();

        editor.putString("name", personal.getNome());
        editor.putString("phone", personal.getTelefono());
        editor.putString("email", personal.getEmail());
        editor.putString("website", personal.getSito());
        editor.putString("telegram", personal.getTelegram());
        editor.putString("color", personal.getColore());


        editor.putString("jobRole",personal.getLavoroRuolo());
        editor.putString("jobName",personal.getLavoroLuogo());
        Location lavoroCoordinate = personal.getLavoroCoordinate();
        if( lavoroCoordinate != null )
        {
            editor.putFloat("jobLat", (float) lavoroCoordinate.getLatitude());
            editor.putFloat("jobLng", (float) lavoroCoordinate.getLongitude());
        }

        editor.putString("houseCity",personal.getCasaCitta());
        editor.putString("houseStreet",personal.getCasaStrada());
        Location casaCoordinate = personal.getCasaCoordinate();
        if( casaCoordinate != null )
        {
            editor.putFloat("houseLat", (float) casaCoordinate.getLatitude());
            editor.putFloat("houseLng", (float) casaCoordinate.getLongitude());
        }

        Bitmap profilo = personal.getProfilo();
        Bitmap sfondo = personal.getSfondo();
        if( profilo != null )
        {
            String profiloString = Utils.encodeTobase64(profilo);
            Log.d("SHARED", "PROFILO " + profiloString.length());
            editor.putString("profilo", profiloString);
        }
        if( sfondo != null )
        {
            String sfondoString = Utils.encodeTobase64(sfondo);
            Log.d("SHARED", "SFONDO " + sfondoString.length());
            editor.putString("sfondo", sfondoString );
        }
        // TODO Bitmap profilo;
        // TODO Bitmap sfondo;

        editor.commit();
    }

    public static BusinessCard getPersonalBusinessCard(Context ctx)
    {
        if( personal == null )
        {
            SharedPreferences preferences = Utils.getPreferences(ctx);

            //Integer id;
            //Boolean preferito;
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

            personal = new BusinessCard();
            personal.setId(0);
            personal.setPreferito(false);
            personal.setNome(nome);
            personal.setTelefono(telefono);
            personal.setEmail(email);
            personal.setSito(sito);
            personal.setTelegram(telegram);
            personal.setColore(colore);

            personal.setLavoroRuolo(lavoroRuolo);
            personal.setLavoroLuogo(lavoroLuogo);
            personal.setLavoroCoordinate(lavoroCoordinate);

            personal.setCasaCitta(casaCitta);
            personal.setCasaStrada(casaStrada);
            personal.setCasaCoordinate(casaCoordinate);

            if( profilo != null )
            Log.d("SHARED", "PROFILO " + profilo.length());
            if( sfondo != null )
            Log.d("SHARED", "SFONDO " + sfondo.length());

            if( profilo != null )
                personal.setProfilo( Utils.decodeBase64(profilo) );
            if( sfondo != null )
                personal.setSfondo( Utils.decodeBase64(sfondo) );

        }
        return personal;
    }

    public static SharedPreferences getPreferences(Context ctx) {
        if( preferences == null && ctx != null)
        {
            preferences = PreferenceManager.getDefaultSharedPreferences(ctx);
        }
        return preferences;
    }

    public static String encodeTobase64(Bitmap image)
    {
        Bitmap immagex=image;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        immagex.compress(Bitmap.CompressFormat.PNG, 100, baos);
        byte[] b = baos.toByteArray();
        String imageEncoded = Base64.encodeToString(b,Base64.DEFAULT);
        return imageEncoded;
    }

    public static Bitmap decodeBase64(String input)
    {
        byte[] decodedByte = Base64.decode(input, 0);
        return BitmapFactory.decodeByteArray(decodedByte, 0, decodedByte.length);
    }

    public static Bitmap loadBitmapFromView(View v) {
        Bitmap b = Bitmap.createBitmap( v.getLayoutParams().width, v.getLayoutParams().height, Bitmap.Config.ARGB_8888);
        Canvas c = new Canvas(b);
        v.layout(0, 0, v.getLayoutParams().width, v.getLayoutParams().height);
        v.draw(c);
        return b;
    }
}
