/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JPA;

import java.util.ArrayList;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.AssertFalse.List;
import javax.validation.constraints.NotNull;


@Entity
public class Benutzer {
    
     
    
    @Id
    @Column(length=30, nullable=false)
    public String benutzername ="";
   
    @Column(nullable=false, length=50)
    @NotNull(message = "Das Passwort darf nicht leer sein.")
    public String passwortHash ="";
    
    @Column(nullable=false, length=50)
    public String vorname ="";
    
    @Column(nullable=false, length=50)
    public String nachname ="";
    
    @Column(nullable=false, length=50)
    public String strasse ="";
    
    @Column(nullable=false, length=50)
    public long plz =0;
    
    @Column(nullable=false, length=50)
    public String ort ="";
    
    @Column(nullable=false, length=50)
    public String land ="";
    
    @Column(nullable=false, length=50)
    public String mail ="";
    
    @Column(nullable=false, length=50)
    public long telefonnummer=0;

    public String getBenutzername() {
        return benutzername;
    }

    public void setBenutzername(String benutzername) {
        this.benutzername = benutzername;
    }

    public String getPasswortHash() {
        return passwortHash;
    }

    public void setPasswortHash(String passwortHash) {
        this.passwortHash = passwortHash;
    }

    public String getVorname() {
        return vorname;
    }

    public void setVorname(String vorname) {
        this.vorname = vorname;
    }

    public String getNachname() {
        return nachname;
    }

    public void setNachname(String nachname) {
        this.nachname = nachname;
    }

    public String getStrasse() {
        return strasse;
    }

    public void setStrasse(String strasse) {
        this.strasse = strasse;
    }

    public long getPlz() {
        return plz;
    }

    public void setPlz(long plz) {
        this.plz = plz;
    }

    public String getOrt() {
        return ort;
    }

    public void setOrt(String ort) {
        this.ort = ort;
    }

    public String getLand() {
        return land;
    }

    public void setLand(String land) {
        this.land = land;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public long getTelefonnummer() {
        return telefonnummer;
    }

    public void setTelefonnummer(long telefonnummer) {
        this.telefonnummer = telefonnummer;
    }

    public Benutzer() {
    }
    
    public Benutzer(String benutzername, String passwortHash, String vorname, String nachname, String strasse, long postleitzahl, String ort, String land, String mail, long telefonnummer) {
        this.benutzername = benutzername;
        this.passwortHash = passwortHash;
        this.vorname = vorname;
        this.nachname = nachname;
        this.strasse = strasse;
        this.plz = postleitzahl;
        this.ort = ort;
        this.land = land; 
        this.mail = mail;
        this.telefonnummer = telefonnummer;
    }
    
    
    //<editor-fold defaultstate="collapsed" desc="Von Objekt geerbter Kram">
    @Override
    protected void finalize() throws Throwable {
        super.finalize(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String toString() {
        return super.toString(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int hashCode() {
        return super.hashCode(); //To change body of generated methods, choose Tools | Templates.
    }
    
    //</editor-fold>

}


