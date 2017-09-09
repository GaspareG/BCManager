package re.gaspa.bcmanager.utils;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;

import re.gaspa.bcmanager.R;
import re.gaspa.bcmanager.ui.models.BusinessCard;

/**
 * Created by gaspare on 04/09/17.
 */

public class Utils {


    public static final int MAX_PROFILE_SIZE = 100;
    public static final int MAX_BACKGROUND_SIZE = 400;

    public static void openTelegram(String nick, Context context) {
        if (nick.startsWith("@")) nick.replaceFirst("@", "");
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("tg://resolve?domain=" + nick));
        context.startActivity(intent);
    }


    public static void openWebsite(String url, Context context) {
        if (!url.startsWith("http://") && !url.startsWith("https://"))
            url = "http://" + url;

        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        context.startActivity(i);
    }

    public static void openMail(String mail, Context context) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        Uri data = Uri.parse("mailto:" + mail);
        intent.setData(data);
        context.startActivity(intent);
    }

    public static void openMessage(String numero, Context context) {
        context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.fromParts("sms", numero, null)));
    }

    public static void openNumber(String number, Context context) {
        Uri call = Uri.parse("tel:" + number);
        Intent surf = new Intent(Intent.ACTION_DIAL, call);
        context.startActivity(surf);
    }

    public static void openLocation(Location city, Context context) {

        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("geo:" + city.getLatitude() + "," + city.getLongitude() + "?q=" + city.getLatitude() + "," + city.getLongitude()));
        context.startActivity(intent);
    }

    public static String encodeTobase64(Bitmap image) {
        if (image == null) return "";

        Bitmap immagex = image;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        immagex.compress(Bitmap.CompressFormat.PNG, 100, baos);
        byte[] b = baos.toByteArray();
        String imageEncoded = Base64.encodeToString(b, Base64.NO_WRAP);
        return imageEncoded;
    }

    public static Bitmap decodeBase64(String input) {
        byte[] decodedByte = Base64.decode(input, 0);
        return BitmapFactory.decodeByteArray(decodedByte, 0, decodedByte.length);
    }

    public static Bitmap loadBitmapFromView(View v) {
        Bitmap b = Bitmap.createBitmap(v.getLayoutParams().width, v.getLayoutParams().height, Bitmap.Config.ARGB_8888);
        Canvas c = new Canvas(b);
        v.layout(0, 0, v.getLayoutParams().width, v.getLayoutParams().height);
        v.draw(c);
        return b;
    }

    public static BusinessCard[] getFakeBusinessCard(Context ctx) {
        BusinessCard ret[] = new BusinessCard[3];

        ret[0] = new BusinessCard();
        ret[0].setPreferito(false);
        ret[0].setNome("Gaspare Ferraro");
        ret[0].setTelefono("+393926477802");
        ret[0].setEmail("ferraro@gaspa.re");
        ret[0].setSito("gaspa.re");
        ret[0].setTelegram("@GaspareG");
        ret[0].setColore("#009688");

        ret[0].setProfilo(BitmapFactory.decodeResource(ctx.getResources(), R.drawable.default_profile1));
        Log.d("DATABASE", "SET PROFILO " + Utils.encodeTobase64(ret[0].getProfilo()).length() + " bytes");
        ret[0].setSfondo(BitmapFactory.decodeResource(ctx.getResources(), R.drawable.default_background1));
        Log.d("DATABASE", "SET SFONDO " + Utils.encodeTobase64(ret[0].getSfondo()).length() + " bytes");

        ret[0].setCasaCitta("Genova");
        ret[0].setCasaStrada("Passo ca' dei rissi 7");
        Location casaCoordinate = new Location(LocationManager.GPS_PROVIDER);
        casaCoordinate.setLatitude(44.455902);
        casaCoordinate.setLongitude(8.977273);
        ret[0].setCasaCoordinate(casaCoordinate);
        ret[0].setLavoroRuolo("Studente");
        ret[0].setLavoroLuogo("Università di Pisa");
        Location lavoroCoordinate = new Location(LocationManager.GPS_PROVIDER);
        lavoroCoordinate.setLatitude(43.720563);
        lavoroCoordinate.setLongitude(10.408381);
        ret[0].setLavoroCoordinate(lavoroCoordinate);

        ret[1] = new BusinessCard();
        ret[1].setPreferito(true);
        ret[1].setNome("Elon Musk");
        ret[1].setTelefono("+01333123456");
        ret[1].setEmail("info@elonmusknews.org");
        ret[1].setSito("elonmusknews.org");
        ret[1].setTelegram("@ElonMusk");
        ret[1].setColore("#2196F3");
        ret[1].setProfilo(BitmapFactory.decodeResource(ctx.getResources(), R.drawable.default_profile2));
        ret[1].setSfondo(BitmapFactory.decodeResource(ctx.getResources(), R.drawable.default_background2));
        ret[1].setCasaCitta("Bel Air");
        ret[1].setCasaStrada("Maraga Dr 1");
        Location casaCoordinate2 = new Location(LocationManager.GPS_PROVIDER);
        casaCoordinate2.setLatitude(34.095165);
        casaCoordinate2.setLongitude(-118.469207);
        ret[1].setCasaCoordinate(casaCoordinate2);
        ret[1].setLavoroRuolo("CEO");
        ret[1].setLavoroLuogo("Tesla");
        Location lavoroCoordinate2 = new Location(LocationManager.GPS_PROVIDER);
        lavoroCoordinate2.setLatitude(33.921303);
        lavoroCoordinate2.setLongitude(-118.330186);
        ret[1].setLavoroCoordinate(lavoroCoordinate2);

        ret[2] = new BusinessCard();
        ret[2].setPreferito(true);
        ret[2].setNome("DonalD Trump");
        ret[2].setTelefono("+01001001001");
        ret[2].setEmail("info@donaldjtrump.com");
        ret[2].setSito("donaldjtrump.com");
        ret[2].setTelegram("@realDonaldTrump");
        ret[2].setColore("#FF9800");
        ret[2].setProfilo(BitmapFactory.decodeResource(ctx.getResources(), R.drawable.default_profile3));
        ret[2].setSfondo(BitmapFactory.decodeResource(ctx.getResources(), R.drawable.default_background3));
        ret[2].setCasaCitta("New York");
        ret[2].setCasaStrada("5th Avenue");
        Location casaCoordinate3 = new Location(LocationManager.GPS_PROVIDER);
        casaCoordinate3.setLatitude(40.762286);
        casaCoordinate3.setLongitude(-73.973766);
        ret[2].setCasaCoordinate(casaCoordinate3);
        ret[2].setLavoroRuolo("USA President");
        ret[2].setLavoroLuogo("White House");
        Location lavoroCoordinate3 = new Location(LocationManager.GPS_PROVIDER);
        lavoroCoordinate3.setLatitude(38.897421);
        lavoroCoordinate3.setLongitude(-77.036588);
        ret[2].setLavoroCoordinate(lavoroCoordinate3);

        return ret;
    }

    public static void deleteAll() {
        deleteContacts();
        deleteProfile();
        Preferences.setFirstOpen(null, true);
    }

    public static void deletePreferite() {
        Database.resetPreferite();
    }

    public static void deleteProfile() {
        Preferences.clearData();
    }

    public static void deleteContacts() {
        Database.clearTable();
    }

    public static Bitmap getResizedBitmap(Bitmap image, int maxSize) {
        int width = image.getWidth();
        int height = image.getHeight();

        float bitmapRatio = (float) width / (float) height;
        if (bitmapRatio > 1) {
            width = maxSize;
            height = (int) (width / bitmapRatio);
        } else {
            height = maxSize;
            width = (int) (height * bitmapRatio);
        }
        return Bitmap.createScaledBitmap(image, width, height, true);
    }

    public static Bitmap getCircularBitmap(Bitmap bitmap) {
        Bitmap output;

        if (bitmap.getWidth() > bitmap.getHeight()) {
            output = Bitmap.createBitmap(bitmap.getHeight(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        } else {
            output = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getWidth(), Bitmap.Config.ARGB_8888);
        }

        Canvas canvas = new Canvas(output);

        final int color = 0xff424242;
        final Paint paint = new Paint();
        final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());

        float r = 0;

        if (bitmap.getWidth() > bitmap.getHeight()) {
            r = bitmap.getHeight() / 2;
        } else {
            r = bitmap.getWidth() / 2;
        }

        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(color);
        canvas.drawCircle(r, r, r, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);
        return output;
    }


    public static double truncate(double number, int precision) {
        double prec = Math.pow(10, precision);
        int integerPart = (int) number;
        double fractionalPart = number - integerPart;
        fractionalPart *= prec;
        int fractPart = (int) fractionalPart;
        fractionalPart = (double) (integerPart) + (double) (fractPart) / prec;
        return fractionalPart;
    }
}
