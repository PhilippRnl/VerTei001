/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EJB;

import javax.persistence.Column;
import javax.persistence.Id;

/**
 *
 * @author phili
 */
public class BenutzerBean {
    @Id
    @Column(length=30, nullable=false)
    public String benutzername ="";
   
    @Column(nullable=false, length=50)
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
    
}
