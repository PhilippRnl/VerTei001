/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JPA;

import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import java.io.Serializable;
import java.sql.Time;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
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
    public String postleizahl ="";
    
    @Column
    @NotNull(message = "Das Datum darf nicht leer sein.")
    private Date erstelldatum;
    
    @Column
    @NotNull(message = "Das Datum darf nicht leer sein.")
    private Date onlineBis;
    
    @Enumerated(EnumType.STRING)
    @NotNull
    private PreisArt preisArt = PreisArt.VB;
    
    @Column
    public boolean istAngebot = false;

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

    public long getPreisvorstellung() {
        return preisvorstellung;
    }

    public void setPreisvorstellung(long preisvorstellung) {
        this.preisvorstellung = preisvorstellung;
    }

    public String getPostleizahl() {
        return postleizahl;
    }

    public void setPostleizahl(String postleizahl) {
        this.postleizahl = postleizahl;
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

    public boolean isIstAngebot() {
        return istAngebot;
    }

    public void setIstAngebot(boolean istAngebot) {
        this.istAngebot = istAngebot;
    }
    
    //KONSTRUKTORENHINZUFÜGEN
}
