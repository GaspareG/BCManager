package re.gaspa.bcmanager.ui.models;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.location.Location;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by gaspare on 29/08/17.
 */

public class BusinessCard implements Parcelable{

    private String nome;
    private String telefono;
    private String email;
    private String ruolo;
    private String citta;
    private String luogo;
    private Location luogoCoordinate ;
    private String sito;
    private String telegram;
    private String colore;

    private Bitmap profilo;
    private Bitmap sfondo;

    public BusinessCard(){}

    public BusinessCard(String nome, String telefono, String email, String ruolo, String citta, String luogo, Location luogoCoordinate, String sito, String telegram, String colore, Bitmap profilo, Bitmap sfondo) {
        this.nome = nome;
        this.telefono = telefono;
        this.email = email;
        this.ruolo = ruolo;
        this.citta = citta;
        this.luogo = luogo;
        this.luogoCoordinate = luogoCoordinate;
        this.sito = sito;
        this.telegram = telegram;
        this.colore = colore;
        this.profilo = profilo;
        this.sfondo = sfondo;
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

    public String getRuolo() {
        return ruolo;
    }

    public void setRuolo(String ruolo) {
        this.ruolo = ruolo;
    }

    public String getCitta() {
        return citta;
    }

    public void setCitta(String citta) {
        this.citta = citta;
    }

    public String getLuogo() {
        return luogo;
    }

    public void setLuogo(String luogo) {
        this.luogo = luogo;
    }

    public Location getLuogoCoordinate() {
        return luogoCoordinate;
    }

    public void setLuogoCoordinate(Location luogoCoordinate) {
        this.luogoCoordinate = luogoCoordinate;
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

    protected BusinessCard(Parcel in) {
        nome = in.readString();
        telefono = in.readString();
        email = in.readString();
        ruolo = in.readString();
        citta = in.readString();
        luogo = in.readString();
        luogoCoordinate = in.readParcelable(Location.class.getClassLoader());
        sito = in.readString();
        telegram = in.readString();
        colore = in.readString();
        profilo = in.readParcelable(Bitmap.class.getClassLoader());
        sfondo = in.readParcelable(Bitmap.class.getClassLoader());
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(nome);
        dest.writeString(telefono);
        dest.writeString(email);
        dest.writeString(ruolo);
        dest.writeString(citta);
        dest.writeString(luogo);
        dest.writeParcelable(luogoCoordinate, flags);
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

    @Override
    public String toString() {
        return "BusinessCard{" +
                "nome='" + nome + '\'' +
                ", telefono='" + telefono + '\'' +
                ", email='" + email + '\'' +
                ", ruolo='" + ruolo + '\'' +
                ", citta='" + citta + '\'' +
                ", luogo='" + luogo + '\'' +
                ", luogoCoordinate=" + luogoCoordinate +
                ", sito='" + sito + '\'' +
                ", telegram='" + telegram + '\'' +
                ", colore='" + colore + '\'' +
                ", profilo=" + profilo +
                ", sfondo=" + sfondo +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BusinessCard that = (BusinessCard) o;

        if (nome != null ? !nome.equals(that.nome) : that.nome != null) return false;
        if (telefono != null ? !telefono.equals(that.telefono) : that.telefono != null)
            return false;
        if (email != null ? !email.equals(that.email) : that.email != null) return false;
        if (ruolo != null ? !ruolo.equals(that.ruolo) : that.ruolo != null) return false;
        if (citta != null ? !citta.equals(that.citta) : that.citta != null) return false;
        if (luogo != null ? !luogo.equals(that.luogo) : that.luogo != null) return false;
        if (luogoCoordinate != null ? !luogoCoordinate.equals(that.luogoCoordinate) : that.luogoCoordinate != null)
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
        int result = nome != null ? nome.hashCode() : 0;
        result = 31 * result + (telefono != null ? telefono.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (ruolo != null ? ruolo.hashCode() : 0);
        result = 31 * result + (citta != null ? citta.hashCode() : 0);
        result = 31 * result + (luogo != null ? luogo.hashCode() : 0);
        result = 31 * result + (luogoCoordinate != null ? luogoCoordinate.hashCode() : 0);
        result = 31 * result + (sito != null ? sito.hashCode() : 0);
        result = 31 * result + (telegram != null ? telegram.hashCode() : 0);
        result = 31 * result + (colore != null ? colore.hashCode() : 0);
        result = 31 * result + (profilo != null ? profilo.hashCode() : 0);
        result = 31 * result + (sfondo != null ? sfondo.hashCode() : 0);
        return result;
    }
}
