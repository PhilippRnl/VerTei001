/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EJB;

import JPA.Anzeige;
import JPA.PreisArt;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class AnzeigeBean extends EntityBean <Anzeige, Long> {

    @PersistenceContext
    EntityManager em;

    public AnzeigeBean(Class<Anzeige> entityClass) {
        super(entityClass);
    }
    
    /**
     * Anlegen einer neuen Anzeige.
     * @param titel
     * @param beschreibung
     * @param preisvorstellung
     * @param postleitzahl
     * @param erstelldatum
     * @param ort
     * @param onlineBis
     * @return 
     */
    public Anzeige createNewAnzeige(String titel, String beschreibung, String ort, long preisvorstellung, long postleitzahl, Date erstelldatum, Date onlineBis, PreisArt preisArt, String artDerAnzeige) {
        Anzeige anzeige = new Anzeige(titel,  beschreibung,  ort, preisvorstellung, postleitzahl, erstelldatum,  onlineBis, preisArt, artDerAnzeige);
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
    
     public List<Anzeige> findByBenutzername(String benutzername) {
        return em.createQuery("SELECT t FROM Task t WHERE t.owner.username = :username ORDER BY t.dueDate, t.dueTime")
                 .setParameter("benutzername", benutzername)
                 .getResultList();
    }
}
