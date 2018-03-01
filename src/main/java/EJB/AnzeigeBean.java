/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EJB;

import JPA.Anzeige;
import JPA.Kategorie;
import JPA.PreisArt;
import java.util.Date;
import java.util.List;
import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

@Stateless
@RolesAllowed("todo-app-user")
public class AnzeigeBean extends EntityBean<Anzeige, Long> {

    @PersistenceContext
    EntityManager em;

    public AnzeigeBean() {
        super(Anzeige.class);
    }

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
    public Anzeige createNewAnzeige(String titel, String beschreibung, String ort, long preisvorstellung, String postleitzahl, Date erstelldatum, Date onlineBis, PreisArt preisArt, String artDerAnzeige) {
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
     * Suche nach Aufgaben anhand ihrer Bezeichnung, Kategorie und Status.
     * 
     * Anders als in der Vorlesung behandelt, wird die SELECT-Anfrage hier
     * mit der CriteriaBuilder-API vollkommen dynamisch erzeugt.
     * 
     * @param search In der Kurzbeschreibung enthaltener Text (optional)
     * @param category Kategorie (optional)
     * @param status Status (optional)
     * @return Liste mit den gefundenen Aufgaben
     */
    public List<Anzeige> search(String search, Kategorie category) {
        // Hilfsobjekt zum Bauen des Query
        CriteriaBuilder cb = this.em.getCriteriaBuilder();
        
        // SELECT t FROM Task t
        CriteriaQuery<Anzeige> query = cb.createQuery(Anzeige.class);
        Root<Anzeige> from = query.from(Anzeige.class);
        query.select(from);

        
        // WHERE t.category = :category
        if (category != null) {
            query.where(cb.equal(from.get("kategorie"), category));
        }
        
        
        return em.createQuery(query).getResultList();
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
