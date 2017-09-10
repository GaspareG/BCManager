package re.gaspa.bcmanager.ui.models;

import android.content.ContentValues;
import android.content.Context;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.location.Location;
import android.location.LocationManager;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import android.util.StringBuilderPrinter;

import java.io.BufferedReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import re.gaspa.bcmanager.R;
import re.gaspa.bcmanager.utils.Utils;

/**
 * Created by gaspare on 29/08/17.
 */

public class BusinessCard implements Parcelable {

    private Integer id;
    private Boolean preferito;

    private String nome;
    private String telefono;
    private String email;
    private String sito;
    private String telegram;
    private String colore;

    private String casaCitta;
    private String casaStrada;
    private Location casaCoordinate;

    private String lavoroRuolo;
    private String lavoroLuogo;
    private Location lavoroCoordinate;

    private Bitmap profilo;
    private Bitmap sfondo;

    public BusinessCard() {
        this.id = 0;
        this.preferito = false;
        this.nome = "";
        this.telefono = "";
        this.email = "";
        this.lavoroRuolo = "";
        this.lavoroLuogo = "";
        this.lavoroCoordinate = null;
        this.casaCitta = "";
        this.casaStrada = "";
        this.casaCoordinate = null;
        this.sito = "";
        this.telegram = "";
        this.colore = "#4CAF50";
        this.profilo = null;
        this.sfondo = null;
    }

    public BusinessCard(Integer id, Boolean preferito, String nome, String telefono, String email, String lavoroRuolo, String lavoroLuogo, Location lavoroCoordinate, String casaCitta, String casaStrada, Location casaCoordinate, String sito, String telegram, String colore, Bitmap profilo, Bitmap sfondo) {
        this.id = id;
        this.preferito = preferito;
        this.nome = nome;
        this.telefono = telefono;
        this.email = email;
        this.lavoroRuolo = lavoroRuolo;
        this.lavoroLuogo = lavoroLuogo;
        this.lavoroCoordinate = lavoroCoordinate;
        this.casaCitta = casaCitta;
        this.casaStrada = casaStrada;
        this.casaCoordinate = casaCoordinate;
        this.sito = sito;
        this.telegram = telegram;
        this.colore = colore;
        this.profilo = profilo;
        this.sfondo = sfondo;
    }

    protected BusinessCard(Parcel in) {
        id = in.readInt();
        preferito = in.readByte() != 0;
        nome = in.readString();
        telefono = in.readString();
        email = in.readString();
        lavoroRuolo = in.readString();
        lavoroLuogo = in.readString();
        lavoroCoordinate = in.readParcelable(Location.class.getClassLoader());
        casaCitta = in.readString();
        casaStrada = in.readString();
        casaCoordinate = in.readParcelable(Location.class.getClassLoader());
        sito = in.readString();
        telegram = in.readString();
        colore = in.readString();
        profilo = in.readParcelable(Bitmap.class.getClassLoader());
        sfondo = in.readParcelable(Bitmap.class.getClassLoader());
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeByte((byte) (preferito ? 1 : 0));
        dest.writeString(nome);
        dest.writeString(telefono);
        dest.writeString(email);
        dest.writeString(lavoroRuolo);
        dest.writeString(lavoroLuogo);
        dest.writeParcelable(lavoroCoordinate, flags);
        dest.writeString(casaCitta);
        dest.writeString(casaStrada);
        dest.writeParcelable(casaCoordinate, flags);
        dest.writeString(sito);
        dest.writeString(telegram);
        dest.writeString(colore);
        dest.writeParcelable(profilo, flags);
        dest.writeParcelable(sfondo, flags);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<BusinessCard> CREATOR = new Creator<BusinessCard>() {
        @Override
        public BusinessCard createFromParcel(Parcel in) {
            return new BusinessCard(in);
        }

        @Override
        public BusinessCard[] newArray(int size) {
            return new BusinessCard[size];
        }
    };

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Boolean getPreferito() {
        return preferito;
    }

    public void setPreferito(Boolean preferito) {
        this.preferito = preferito;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLavoroRuolo() {
        return lavoroRuolo;
    }

    public void setLavoroRuolo(String lavoroRuolo) {
        this.lavoroRuolo = lavoroRuolo;
    }

    public String getLavoroLuogo() {
        return lavoroLuogo;
    }

    public void setLavoroLuogo(String lavoroLuogo) {
        this.lavoroLuogo = lavoroLuogo;
    }

    public Location getLavoroCoordinate() {
        return lavoroCoordinate;
    }

    public void setLavoroCoordinate(Location lavoroCoordinate) {
        this.lavoroCoordinate = lavoroCoordinate;
        if (lavoroCoordinate == null) return;
        Double lat = this.lavoroCoordinate.getLatitude();
        Double lng = this.lavoroCoordinate.getLongitude();
        lat = Utils.truncate(lat, 6);
        lng = Utils.truncate(lng, 6);
        Log.d("POS", lat + " " + lng);
        this.lavoroCoordinate.setLatitude(lat);
        this.lavoroCoordinate.setLongitude(lng);
    }

    public String getCasaCitta() {
        return casaCitta;
    }

    public void setCasaCitta(String casaCitta) {
        this.casaCitta = casaCitta;
    }

    public String getCasaStrada() {
        return casaStrada;
    }

    public void setCasaStrada(String casaStrada) {
        this.casaStrada = casaStrada;
    }

    public Location getCasaCoordinate() {
        return casaCoordinate;
    }

    public void setCasaCoordinate(Location casaCoordinate) {
        this.casaCoordinate = casaCoordinate;
        if (casaCoordinate == null) return;
        double lat = this.casaCoordinate.getLatitude();
        double lng = this.casaCoordinate.getLongitude();
        lat = Utils.truncate(lat, 6);
        lng = Utils.truncate(lng, 6);
        Log.d("POS", lat + " " + lng);
        this.casaCoordinate.setLatitude(lat);
        this.casaCoordinate.setLongitude(lng);
    }

    public String getSito() {
        return sito;
    }

    public void setSito(String sito) {
        this.sito = sito;
    }

    public String getTelegram() {
        return telegram;
    }

    public void setTelegram(String telegram) {
        this.telegram = telegram;
    }

    public String getColore() {
        return colore;
    }

    public void setColore(String colore) {

        try {
            int colorCode = Color.parseColor(colore);
            if (colorCode != -1)
                this.colore = colore;
        } catch (Exception e) {
            this.colore = "#4CAF50";
        }
    }

    public Bitmap getProfilo() {
        return profilo;
    }

    public void setProfilo(Bitmap profilo) {
        if (profilo == null) return;
        this.profilo = Utils.getResizedBitmap(profilo, Utils.MAX_PROFILE_SIZE);
    }

    public Bitmap getSfondo() {
        return sfondo;
    }

    public void setSfondo(Bitmap sfondo) {
        if (sfondo == null) return;
        this.sfondo = Utils.getResizedBitmap(sfondo, Utils.MAX_BACKGROUND_SIZE);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BusinessCard that = (BusinessCard) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (preferito != null ? !preferito.equals(that.preferito) : that.preferito != null)
            return false;
        if (nome != null ? !nome.equals(that.nome) : that.nome != null) return false;
        if (telefono != null ? !telefono.equals(that.telefono) : that.telefono != null)
            return false;
        if (email != null ? !email.equals(that.email) : that.email != null) return false;
        if (lavoroRuolo != null ? !lavoroRuolo.equals(that.lavoroRuolo) : that.lavoroRuolo != null)
            return false;
        if (lavoroLuogo != null ? !lavoroLuogo.equals(that.lavoroLuogo) : that.lavoroLuogo != null)
            return false;
        if (lavoroCoordinate != null ? !lavoroCoordinate.equals(that.lavoroCoordinate) : that.lavoroCoordinate != null)
            return false;
        if (casaCitta != null ? !casaCitta.equals(that.casaCitta) : that.casaCitta != null)
            return false;
        if (casaStrada != null ? !casaStrada.equals(that.casaStrada) : that.casaStrada != null)
            return false;
        if (casaCoordinate != null ? !casaCoordinate.equals(that.casaCoordinate) : that.casaCoordinate != null)
            return false;
        if (sito != null ? !sito.equals(that.sito) : that.sito != null) return false;
        if (telegram != null ? !telegram.equals(that.telegram) : that.telegram != null)
            return false;
        if (colore != null ? !colore.equals(that.colore) : that.colore != null) return false;
        if (profilo != null ? !profilo.equals(that.profilo) : that.profilo != null) return false;
        return sfondo != null ? sfondo.equals(that.sfondo) : that.sfondo == null;

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (preferito != null ? preferito.hashCode() : 0);
        result = 31 * result + (nome != null ? nome.hashCode() : 0);
        result = 31 * result + (telefono != null ? telefono.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (lavoroRuolo != null ? lavoroRuolo.hashCode() : 0);
        result = 31 * result + (lavoroLuogo != null ? lavoroLuogo.hashCode() : 0);
        result = 31 * result + (lavoroCoordinate != null ? lavoroCoordinate.hashCode() : 0);
        result = 31 * result + (casaCitta != null ? casaCitta.hashCode() : 0);
        result = 31 * result + (casaStrada != null ? casaStrada.hashCode() : 0);
        result = 31 * result + (casaCoordinate != null ? casaCoordinate.hashCode() : 0);
        result = 31 * result + (sito != null ? sito.hashCode() : 0);
        result = 31 * result + (telegram != null ? telegram.hashCode() : 0);
        result = 31 * result + (colore != null ? colore.hashCode() : 0);
        result = 31 * result + (profilo != null ? profilo.hashCode() : 0);
        result = 31 * result + (sfondo != null ? sfondo.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "BusinessCard{" +
                "id=" + id +
                ", preferito=" + preferito +
                ", nome='" + nome + '\'' +
                ", telefono='" + telefono + '\'' +
                ", email='" + email + '\'' +
                ", lavoroRuolo='" + lavoroRuolo + '\'' +
                ", lavoroLuogo='" + lavoroLuogo + '\'' +
                ", lavoroCoordinate=" + lavoroCoordinate +
                ", casaCitta='" + casaCitta + '\'' +
                ", casaStrada='" + casaStrada + '\'' +
                ", casaCoordinate='" + casaCoordinate + '\'' +
                ", sito='" + sito + '\'' +
                ", telegram='" + telegram + '\'' +
                ", colore='" + colore + '\'' +
                ", profilo=" + profilo +
                ", sfondo=" + sfondo +
                '}';
    }

    public ContentValues getContentValues() {
        ContentValues ret = new ContentValues();
        // TODO ret.put();

        ret.put("ID", this.getId());
        ret.put("PREF", this.getPreferito() ? 1 : 0);

        ret.put("NOME", this.getNome());
        ret.put("TELEFONO", this.getTelefono());
        ret.put("EMAIL", this.getEmail());
        ret.put("SITO", this.getSito());
        ret.put("TELEGRAM", this.getTelegram());
        ret.put("COLOR", this.getColore());

        ret.put("CASACITTA", this.getCasaCitta());
        ret.put("CASASTRADA", this.getCasaStrada());
        ret.put("CASALAT", this.getCasaCoordinate() == null ? 0.0 : this.getCasaCoordinate().getLatitude());
        ret.put("CASALNG", this.getCasaCoordinate() == null ? 0.0 : this.getCasaCoordinate().getLongitude());

        ret.put("LAVORORUOLO", this.getLavoroRuolo());
        ret.put("LAVOROLUOGO", this.getLavoroLuogo());
        ret.put("LAVOROLAT", this.getLavoroCoordinate() == null ? 0.0 : this.getLavoroCoordinate().getLatitude());
        ret.put("LAVOROLNG", this.getLavoroCoordinate() == null ? 0.0 : this.getLavoroCoordinate().getLongitude());

        byte profiloB[] = this.getProfilo() == null ? "".getBytes() : Utils.encodeTobase64(this.getProfilo()).getBytes();
        byte sfondoB[] = this.getSfondo() == null ? "".getBytes() : Utils.encodeTobase64(this.getSfondo()).getBytes();
        Log.d("DATABASE", "PROFILO = " + profiloB.length);
        Log.d("DATABASE", "SFONDO = " + sfondoB.length);
        ret.put("PROFILO", profiloB);
        ret.put("SFONDO", sfondoB);

        return ret;
    }

    public static BusinessCard loadFromCursor(Cursor cursor) {
        BusinessCard ret = new BusinessCard();

        try {
            ret.setId(cursor.getInt(cursor.getColumnIndex("ID")));
            ret.setPreferito(cursor.getInt(cursor.getColumnIndex("PREF")) == 1);
            ret.setNome(cursor.getString(cursor.getColumnIndex("NOME")));
            ret.setTelefono(cursor.getString(cursor.getColumnIndex("TELEFONO")));

            ret.setEmail(cursor.getString(cursor.getColumnIndex("EMAIL")));
            ret.setSito(cursor.getString(cursor.getColumnIndex("SITO")));
            ret.setTelegram(cursor.getString(cursor.getColumnIndex("TELEGRAM")));
            ret.setColore(cursor.getString(cursor.getColumnIndex("COLOR")));

            ret.setProfilo(Utils.decodeBase64(new String(cursor.getBlob(cursor.getColumnIndex("PROFILO")))));
            ret.setSfondo(Utils.decodeBase64(new String(cursor.getBlob(cursor.getColumnIndex("SFONDO")))));

            ret.setCasaCitta(cursor.getString(cursor.getColumnIndex("CASACITTA")));
            ret.setCasaStrada(cursor.getString(cursor.getColumnIndex("CASASTRADA")));
            Location casaCoordinate = new Location(LocationManager.GPS_PROVIDER);
            casaCoordinate.setLatitude(cursor.getDouble(cursor.getColumnIndex("CASALAT")));
            casaCoordinate.setLongitude(cursor.getDouble(cursor.getColumnIndex("CASALNG")));
            ret.setCasaCoordinate(casaCoordinate);

            ret.setLavoroRuolo(cursor.getString(cursor.getColumnIndex("LAVORORUOLO")));
            ret.setLavoroLuogo(cursor.getString(cursor.getColumnIndex("LAVOROLUOGO")));
            Location lavoroCoordinate = new Location(LocationManager.GPS_PROVIDER);
            lavoroCoordinate.setLatitude(cursor.getDouble(cursor.getColumnIndex("LAVOROLAT")));
            lavoroCoordinate.setLongitude(cursor.getDouble(cursor.getColumnIndex("LAVOROLNG")));
            ret.setLavoroCoordinate(lavoroCoordinate);
        } catch (Exception e) {
            Log.d("DATABASE", e.toString());
        }

        return ret;
    }


    public String toVCard() {
        StringBuilder builder = new StringBuilder();

        Log.d("NFC", "CREATE NDEF MESSAGE TO VCARD 1");

        if (this.getNome() != null && this.getNome().length() > 0)
            builder.append("NAME=").append(this.getNome()).append("\n");
        if (this.getTelefono() != null && this.getTelefono().length() > 0)
            builder.append("PHONE=").append(this.getTelefono()).append("\n");
        if (this.getEmail() != null && this.getEmail().length() > 0)
            builder.append("EMAIL=").append(this.getEmail()).append("\n");
        if (this.getSito() != null && this.getSito().length() > 0)
            builder.append("WEBSITE=").append(this.getSito()).append("\n");
        if (this.getTelegram() != null && this.getTelegram().length() > 0)
            builder.append("TELEGRAM=").append(this.getTelegram()).append("\n");
        if (this.getColore() != null && this.getColore().length() > 0)
            builder.append("COLOR=").append(this.getColore()).append("\n");
        if (this.getCasaCitta() != null && this.getCasaCitta().length() > 0)
            builder.append("HOMECITY=").append(this.getCasaCitta()).append("\n");

        Log.d("NFC", "CREATE NDEF MESSAGE TO VCARD 2");

        if (this.getCasaStrada() != null && this.getCasaStrada().length() > 0)
            builder.append("HOMESTREET=").append(this.getCasaStrada()).append("\n");
        if (this.getCasaCoordinate() != null)
            builder.append("HOMECOORD=").append(this.getCasaCoordinate().getLatitude()).append(", ").append(this.getCasaCoordinate().getLongitude()).append("\n");
        if (this.getLavoroRuolo() != null && this.getLavoroRuolo().length() > 0)
            builder.append("JOBROLE=").append(this.getLavoroRuolo()).append("\n");
        if (this.getLavoroLuogo() != null && this.getLavoroLuogo().length() > 0)
            builder.append("JOBPLACE=").append(this.getLavoroLuogo()).append("\n");
        if (this.getLavoroCoordinate() != null)
            builder.append("JOBCOORD=").append(this.getLavoroCoordinate().getLatitude()).append(", ").append(this.getLavoroCoordinate().getLongitude()).append("\n");


        Log.d("NFC", "CREATE NDEF MESSAGE TO VCARD 3");

        if (this.getProfilo() != null)
            builder.append("PROFILE=").append(Utils.encodeTobase64(this.getProfilo())).append("\n");
        if (this.getSfondo() != null)
            builder.append("BACKGROUND=").append(Utils.encodeTobase64(this.getSfondo())).append("\n");

        Log.d("NFC", "CREATE NDEF MESSAGE TO VCARD 4");

        return builder.toString();
    }

    public String toTextMessage() {
        StringBuilder builder = new StringBuilder();

        if (this.getNome() != null && this.getNome().length() > 0)
            builder.append("Nome: ").append(this.getNome()).append("\n");
        if (this.getTelefono() != null && this.getTelefono().length() > 0)
            builder.append("Telefono: ").append(this.getTelefono()).append("\n");
        if (this.getEmail() != null && this.getEmail().length() > 0)
            builder.append("Email: ").append(this.getEmail()).append("\n");
        if (this.getSito() != null && this.getSito().length() > 0)
            builder.append("Sito: ").append(this.getSito()).append("\n");
        if (this.getTelegram() != null && this.getTelegram().length() > 0)
            builder.append("Telegram: ").append(this.getTelegram()).append("\n");
        if (this.getCasaCitta() != null && this.getCasaCitta().length() > 0)
            builder.append("Città: ").append(this.getCasaCitta()).append("\n");
        if (this.getCasaStrada() != null && this.getCasaStrada().length() > 0)
            builder.append("Indirizzo: ").append(this.getCasaStrada()).append("\n");
        if (this.getCasaCoordinate() != null)
            builder.append("Coordinate abitazione: ").append(this.getCasaCoordinate().getLatitude()).append(", ").append(this.getCasaCoordinate().getLongitude()).append("\n");
        if (this.getLavoroRuolo() != null && this.getLavoroRuolo().length() > 0)
            builder.append("Professione: ").append(this.getLavoroRuolo()).append("\n");
        if (this.getLavoroLuogo() != null && this.getLavoroLuogo().length() > 0)
            builder.append("Azienda: ").append(this.getLavoroLuogo()).append("\n");
        if (this.getLavoroCoordinate() != null)
            builder.append("Coordinate azienda: ").append(this.getLavoroCoordinate().getLatitude()).append(", ").append(this.getLavoroCoordinate().getLongitude()).append("\n");

        return builder.toString();
    }

    public static BusinessCard loadFromBuffered(BufferedReader reader) throws IOException {
        BusinessCard toAdd = new BusinessCard();
        String line = reader.readLine();
        while (line != null) {
            int idx = line.indexOf("=");
            if (idx != -1) {
                String key = line.substring(0, idx);
                String value = line.substring(idx + 1);
                if (!(key.length() == 0 || value.length() == 0))
                    switch (key.toUpperCase()) {
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
                            toAdd.setSfondo(Utils.decodeBase64(value));
                            break;
                        case "HOMECOORD":
                            String coord[] = value.replace(" ", "").split(",");
                            if (coord.length == 2) {
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
        return toAdd;
    }

    public static BusinessCard loadFromBuffer(String[] split) {
        BusinessCard toAdd = new BusinessCard();
        for (String line : split) {
            int idx = line.indexOf("=");
            if (idx != -1) {
                String key = line.substring(0, idx);
                String value = line.substring(idx + 1);
                if (!(key.length() == 0 || value.length() == 0))
                    switch (key.toUpperCase()) {
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
                            toAdd.setSfondo(Utils.decodeBase64(value));
                            break;
                        case "HOMECOORD":
                            String coord[] = value.replace(" ", "").split(",");
                            if (coord.length == 2) {
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
        }
        return toAdd;
    }

    public Bitmap getImage(Context context) {
        List<Map.Entry<String, String>> prop = this.getProp();

        Bitmap b = Bitmap.createBitmap(256, 256 + 20 * (1 + prop.size()), Bitmap.Config.ARGB_8888);
        Canvas c = new Canvas(b);
        Paint p = new Paint();
        p.setAntiAlias(true);


        p.setColor(Color.WHITE);
        c.drawRect(0, 0, b.getWidth(), b.getHeight(), p);


        // Disegna Background
        Bitmap background = this.getSfondo();
        if( background == null )
            background = BitmapFactory.decodeResource(context.getResources(), R.drawable.default_background);

        int height = background.getHeight();
        int width = background.getWidth();
        c.drawCircle(50, 50, 25, p);
        Rect src = new Rect(width / 2 - height / 2, 0, width / 2 + height / 2, height);
        Rect dest = new Rect(0, 0, 256, 256);
        c.drawBitmap(background, src, dest, null);

        // Disegna Profilo
        Bitmap profilo = this.getProfilo();
        if( profilo == null )
            profilo = BitmapFactory.decodeResource(context.getResources(), R.drawable.default_profile);

        Bitmap cProfilo = Utils.getCircularBitmap(profilo);
        src = new Rect(0, 0, cProfilo.getWidth(), cProfilo.getWidth());
        dest = new Rect(64, 64, 192, 192);
        c.drawBitmap(cProfilo, src, dest, null);

        // Scrivi dati
        int i = 1;
        p.setColor(Color.BLACK);
        try {
            p.setColor(Color.parseColor(this.getColore()));
        } catch (Exception e) {

        }
        p.setTextAlign(Paint.Align.LEFT);
        for (Map.Entry<String, String> entry : prop) {
            String key = entry.getKey();
            String value = entry.getValue();
            c.drawText(key + " = " + value, 10, 256 + 20 * i++, p);
        }

        return b;
    }

    private List<Map.Entry<String, String>> getProp() {
        List<Map.Entry<String, String>> ret = new ArrayList<>();

        if (this.getNome() != null && this.getNome().length() > 0)
            ret.add(new AbstractMap.SimpleEntry<>("Nome", this.getNome()));

        if (this.getTelefono() != null && this.getTelefono().length() > 0)
            ret.add(new AbstractMap.SimpleEntry<>("Telefono", this.getTelefono()));

        if (this.getEmail() != null && this.getEmail().length() > 0)
            ret.add(new AbstractMap.SimpleEntry<>("E-Mail", this.getEmail()));

        if (this.getSito() != null && this.getSito().length() > 0)
            ret.add(new AbstractMap.SimpleEntry<>("Sito", this.getSito()));

        if (this.getTelegram() != null && this.getTelegram().length() > 0)
            ret.add(new AbstractMap.SimpleEntry<>("Telegram", this.getTelegram()));

        if (this.getCasaCitta() != null && this.getCasaCitta().length() > 0)
            ret.add(new AbstractMap.SimpleEntry<>("Città", this.getCasaCitta()));

        if (this.getCasaStrada() != null && this.getCasaStrada().length() > 0)
            ret.add(new AbstractMap.SimpleEntry<>("Indirizzo", this.getCasaStrada()));

        if (this.getCasaCoordinate() != null)
            ret.add(new AbstractMap.SimpleEntry<>("Coordinate casa", this.getCasaCoordinate().getLatitude() + ", " + this.getCasaCoordinate().getLongitude()));

        if (this.getLavoroRuolo() != null && this.getLavoroRuolo().length() > 0)
            ret.add(new AbstractMap.SimpleEntry<>("Occupazione", this.getLavoroRuolo()));

        if (this.getLavoroLuogo() != null && this.getLavoroLuogo().length() > 0)
            ret.add(new AbstractMap.SimpleEntry<>("Luogo di lavoro", this.getLavoroLuogo()));

        if (this.getLavoroCoordinate() != null)
            ret.add(new AbstractMap.SimpleEntry<>("Coordinate lavoro", this.getLavoroCoordinate().getLatitude() + ", " + this.getLavoroCoordinate().getLongitude()));

        return ret;
    }
}
