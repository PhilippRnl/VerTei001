/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import JPA.Anzeige;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class AnzeigeBean {

    @PersistenceContext
    EntityManager em;
    
    /**
     * Anlegen einer neuen Anzeige.
     * @param art
     * @param titel
     * @param beschreibung
     * @param erstellungsDatum
     * @param onlineBis
     * @param preisVorstellung
     * @param artDesPreises
     * @param plz
     * @param ort
     * @return 
     */
    public Anzeige createNewAnzeige(String art, String titel, String beschreibung, Date erstellungsDatum, Date onlineBis, double preisVorstellung, String artDesPreises, int plz, String ort) {
        Anzeige anzeige = new Anzeige(art, titel, beschreibung, erstellungsDatum, onlineBis, preisVorstellung, artDesPreises, plz, ort);
        em.persist(anzeige);
        return em.merge(anzeige);
    }

    /**
     * @return Liste mit allen Anzeigen in umgedrehter Anlagereihenfolge
     */
    public List<Anzeige> findAllAnzeigen() {
        return em.createQuery("SELECT w FROM Anzeige w ORDER BY w.id DESC").getResultList();
    }

    /**
     * Einzelnen Textschnippsel ermitteln
     *
     * @param id ID der Anzeige
     * @return Gefundenes Objekt oder null
     */
    public Anzeige findAnzeige(long id) {
        return em.find(Anzeige.class, id);
    }

    /**
     * Löschen einer Anzeige.
     *
     * @param id ID der zu löschenden Anzeige
     * @return Der gelöschten Anzeige oder null
     */
    public Anzeige deleteAnzeige(long id) {
        Anzeige anzeige = em.find(Anzeige.class, id);

        if (anzeige != null) {
            em.remove(anzeige);
        }

        return anzeige;
    }

    /**
     * Änderungen an einem Anzeige speichern.
     *
     * @param anzeige Die zu ändernde Anzeige
     * @return Neue, gespeicherte Version der Anzeige
     */
    public Anzeige updateAnzeige(Anzeige anzeige) {
        return em.merge(anzeige);
    }
}
