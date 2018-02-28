/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JPA;

import JPA.Anzeige;
import java.io.Serializable;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Kategorie implements Serializable{
    @Id
    private String slug = "";
    private String name = "";
    
    @ManyToMany(mappedBy="kategorien")
    List<Anzeige> anzeigen = new ArrayList<>();
    
    @OneToMany
    List<Kategorie> kategorien = new ArrayList<>();
    
    
    //<editor-fold defaultstate="collapsed" desc="Konstrukturen">
    public Kategorie() {
    }
    public Kategorie(String slug, String name) {
        this.slug = slug;
        this.name = name;
    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Setter und Getter">
    public void setSlug(String slug) {
        this.slug = slug;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAnzeigen(List<Anzeige> anzeigen) {
        this.anzeigen = anzeigen;
    }

    public void setKategorien(List<Kategorie> kategorien) {
        this.kategorien = kategorien;
    }

    public String getSlug() {
        return slug;
    }

    public String getName() {
        return name;
    }

    public List<Anzeige> getAnzeigen() {
        return anzeigen;
    }

    public List<Kategorie> getKategorien() {
        return kategorien;
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
