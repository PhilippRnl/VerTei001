/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JPA;

import java.io.Serializable;
import javax.persistence.*;


@Entity
public class Foto implements Serializable {

    @Id
    @GeneratedValue
    private long id = 0;

    @Lob
    private String bezeichnung = "";
    private String bildDaten = "";

    @ManyToOne
    Anzeige anzeige = null;

    //<editor-fold defaultstate="collapsed" desc="Konstrukturen">
    public Foto() {
    }

    public Foto(String bezeichnung, String bildDaten) {
        this.bezeichnung = bezeichnung;
        this.bildDaten = bildDaten;
    }
    //</editor-fold>
    
    
    //<editor-fold defaultstate="collapsed" desc="Setter und Getter">
    public void setId(long id) {
        this.id = id;
    }

    public void setBezeichnung(String bezeichnung) {
        this.bezeichnung = bezeichnung;
    }

    public void setBildDaten(String bildDaten) {
        this.bildDaten = bildDaten;
    }

    public void setAnzeige(Anzeige anzeige) {
        this.anzeige = anzeige;
    }

    public long getId() {
        return id;
    }

    public String getBezeichnung() {
        return bezeichnung;
    }

    public String getBildDaten() {
        return bildDaten;
    }

    public Anzeige getAnzeige() {
        return anzeige;
    }
    //</editor-fold>
    
    
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
