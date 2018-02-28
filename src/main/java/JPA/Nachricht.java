/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JPA;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author phili
 */

@Entity
public class Nachricht implements Serializable {
    
    @Id
    @GeneratedValue
    public long id=0;
    
    @Column(nullable=false, length=50)
    public String vonBenutzer="";
    
    @Column(nullable=false, length=50)
    public String anBenutzer="";
    
    @Column(nullable=false, length=50)
    public long zuArtikel
    
    @Lob
    public String Text ="";

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getVonBenutzer() {
        return vonBenutzer;
    }

    public void setVonBenutzer(String vonBenutzer) {
        this.vonBenutzer = vonBenutzer;
    }

    public String getAnBenutzer() {
        return anBenutzer;
    }

    public void setAnBenutzer(String anBenutzer) {
        this.anBenutzer = anBenutzer;
    }

    public long getZuArtikel() {
        return zuArtikel;
    }

    public void setZuArtikel(long zuArtikel) {
        this.zuArtikel = zuArtikel;
    }

    public String getText() {
        return Text;
    }

    public void setText(String Text) {
        this.Text = Text;
    }

    public Nachricht() {
    }
    
    public Nachricht(long id, String vonBenutzer, String anBenutzer, String zuArtikel, String text) {
        
    }
    
    
}
