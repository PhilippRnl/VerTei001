/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JPA;

import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import java.io.Serializable;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

/**
 *
 * @author phili
 */
@Entity
public class Anzeige implements Serializable{
    
    @Id 
    @GeneratedValue
    public long id = 0;
    
    @Column(nullable=false, length=50)
    public String titel ="";
    
    @Column(nullable=false, length=50)
    public String beschreibung ="";
    
    @Column(nullable=false, length=50)
    public long preisvorstellung =0;
    
    @Column(nullable=false, length=50)
    public long postleitzahl;
    
    @Column
    @NotNull(message = "Das Datum darf nicht leer sein.")
    private Date erstelldatum;
    
    @Column(nullable=false)
    public String ort;
    
    @Column
    @NotNull(message = "Das Datum darf nicht leer sein.")
    private Date onlineBis;
    
    @Enumerated(EnumType.STRING)
    @NotNull
    private PreisArt preisArt = PreisArt.VB;
    
    private String artDerAnzeige = "";
    
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitel() {
        return titel;
    }

    public void setTitel(String titel) {
        this.titel = titel;
    }

    public String getBeschreibung() {
        return beschreibung;
    }

    public void setBeschreibung(String beschreibung) {
        this.beschreibung = beschreibung;
    }

    public String getOrt() {
        return ort;
    }

    public void setOrt(String ort) {
        this.ort = ort;
    }

    public long getPreisvorstellung() {
        return preisvorstellung;
    }

    public void setPreisvorstellung(long preisvorstellung) {
        this.preisvorstellung = preisvorstellung;
    }

    public long getPostleitzahl() {
        return postleitzahl;
    }

    public void setPostleitzahl(long postleizahl) {
        this.postleitzahl = postleitzahl;
    }

    public Date getErstelldatum() {
        return erstelldatum;
    }

    public void setErstelldatum(Date erstelldatum) {
        this.erstelldatum = erstelldatum;
    }

    public Date getOnlineBis() {
        return onlineBis;
    }

    public void setOnlineBis(Date onlineBis) {
        this.onlineBis = onlineBis;
    }

    public PreisArt getPreisArt() {
        return preisArt;
    }

    public void setPreisArt(PreisArt preisArt) {
        this.preisArt = preisArt;
    }

    public String getArtDerAnzeige() {
        return artDerAnzeige;
    }

    public void setArtDerAnzeige(String artDerAnzeige) {
        this.artDerAnzeige= artDerAnzeige;
    }
    
    @ManyToOne
    Benutzer releasedBenutzer = null;

    @ManyToMany
    List<Benutzer> noticedBenutzer = new ArrayList<>();

    @OneToMany(mappedBy = "anzeige")
    List<Foto> fotos = new ArrayList<>();

    @ManyToMany
    List<Kategorie> kategorien = new ArrayList<>();

    //<editor-fold defaultstate="collapsed" desc="Konstrukturen">
    public Anzeige() {
    }

    public Anzeige(String titel, String beschreibung, String ort, long preisvorstellung, long postleitzahl, Date erstelldatum, Date onlineBis, PreisArt preisArt, String artDerAnzeige) {
        this.titel = titel;
        this.beschreibung = beschreibung;
        this.preisvorstellung = preisvorstellung;
        this.postleitzahl = postleitzahl;
        this.ort = ort;
        this.erstelldatum = erstelldatum;
        this.onlineBis = onlineBis;
        this.preisArt = preisArt;
        this.artDerAnzeige = artDerAnzeige;
       
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
