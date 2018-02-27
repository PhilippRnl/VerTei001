/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JPA;

import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
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
    public String Titel ="";
    
    @Column(nullable=false, length=50)
    public String Beschreibung ="";
    
    @Column(nullable=false, length=50)
    public long preisvorstellung =0;
    
    @Column(nullable=false, length=50)
    public String Postleizahl ="";
    
    
    
    
    
    
}
