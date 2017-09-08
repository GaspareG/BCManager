package re.gaspa.bcmanager.ui.models;

import android.content.ContentValues;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Location;
import android.location.LocationManager;
import android.os.Parcel;
import android.os.Parcelable;

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
        this.colore = "";
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
        this.colore = colore;
    }

    public Bitmap getProfilo() {
        return profilo;
    }

    public void setProfilo(Bitmap profilo) {
        this.profilo = profilo;
    }

    public Bitmap getSfondo() {
        return sfondo;
    }

    public void setSfondo(Bitmap sfondo) {
        this.sfondo = sfondo;
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

        ret.put("PROFILO", this.getProfilo() == null ? "".getBytes() : Utils.encodeTobase64(this.getProfilo()).getBytes());
        ret.put("SFONDO", this.getSfondo() == null ? "".getBytes() : Utils.encodeTobase64(this.getSfondo()).getBytes());

        return ret;
    }

    public static BusinessCard loadFromCursor(Cursor cursor) {
        BusinessCard ret = new BusinessCard();

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

        return ret;
    }

}
