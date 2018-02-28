/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EJB;

import JPA.Kategorie;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class KategorieBean {
    @PersistenceContext
    EntityManager em;
    
    /**
     * Anlegen einer neuen Kategorie.
     * @param slug
     * @param name
     * @return Der angelegten Kategorie
     */
    public Kategorie createNewKategorie(int slug, String name) {
        Kategorie kategorie = new Kategorie(slug, name);
        em.persist(kategorie);
        return em.merge(kategorie);
    }
    
    /**
     * @return Liste mit allen Kategorien in umgedrehter Anlagereihenfolge
     */
    public List<Kategorie> findAllKategorie() {
        return em.createQuery("SELECT w FROM Kategorie w ORDER BY w.slug DESC").getResultList();
    }
    
    /**
     * Einzelne Kategorie ermitteln
     * @param slug ID der Kategorie
     * @return Gefundenes Objekt oder null
     */
    public Kategorie findKategorie(int slug) {
        return em.find(Kategorie.class, slug);
    }
    
    /**
     * Löschen einer Kategorie.
     * @param slug ID der zu löschenden Kategorie
     * @return Die gelöschte Kategorie oder null
     */
    public Kategorie deleteKategorie(int slug) {
        Kategorie kategorie = em.find(Kategorie.class, slug);
        
        if (kategorie != null) {
            em.remove(kategorie);
        }
        
        return kategorie;
    }
    
    /**
     * Änderungen an einer Kategorie speichern.
     * @param kategorie Die zu ändernde Kategorie
     * @return Neue, gespeicherte Version der Kategorie
     */
    public Kategorie updateKategorie(Kategorie kategorie) {
        return em.merge(kategorie);
    }
    
}
