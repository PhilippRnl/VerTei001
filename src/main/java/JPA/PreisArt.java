/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JPA;

/**
 *
 * @author phili
 */
public enum PreisArt {
    VB, FP, Kostenlos, Vorschlag;
    
    public String getLabel() {
        switch (this) {
            case VB:
                return "VB";
            case FP:
                return "FP";
            case Kostenlos:
                return "Kostenlos";
            case Vorschlag:
                return "Bitte Preis vorschlagen.";
            default:
                return this.toString();
        }
    }
    
    
    
    
    
    
    
    
}
