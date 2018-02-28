/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JPA;

import java.io.Serializable;
import javax.persistence.*;

@Entity
public class Nachricht implements Serializable {

    @Id
    @GeneratedValue
    private long id = 0;

    private Benutzer vonBenutzer = new Benutzer();
    private Benutzer anBenutzer = new Benutzer();

    @Lob
    private String text = "";

    @ManyToOne
    Benutzer benutzer = null;

    //<editor-fold defaultstate="collapsed" desc="Konstrukturen">
    public Nachricht() {
    }

    public Nachricht(Benutzer vonBenutzer, Benutzer anBenutzer, String text) {
        this.vonBenutzer = vonBenutzer;
        this.anBenutzer = anBenutzer;
        this.text = text;
    }

    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Setter und Getter">
    public void setId(long id) {
        this.id = id;
    }

    public void setVonBenutzer(Benutzer vonBenutzer) {
        this.vonBenutzer = vonBenutzer;
    }

    public void setAnBenutzer(Benutzer anBenutzer) {
        this.anBenutzer = anBenutzer;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setBenutzer(Benutzer benutzer) {
        this.benutzer = benutzer;
    }

    public long getId() {
        return id;
    }

    public Benutzer getVonBenutzer() {
        return vonBenutzer;
    }

    public Benutzer getAnBenutzer() {
        return anBenutzer;
    }

    public String getText() {
        return text;
    }

    public Benutzer getBenutzer() {
        return benutzer;
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
