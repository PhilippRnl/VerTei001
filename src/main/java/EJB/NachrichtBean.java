/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Aufgabe.ejb;

import Aufgabe.jpa.Benutzer;
import Aufgabe.jpa.Nachricht;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Fabio Kraemer
 */
@Stateless
public class NachrichtBean {
    @PersistenceContext
    EntityManager em;
    
    /**
     * Anlegen einer neuen Nachricht.
     * @param vonBenutzer
     * @param anBenutzer
     * @param text
     * @return Der angelegten Nachricht
     */
    public Nachricht createNewNachricht(Benutzer vonBenutzer, Benutzer anBenutzer, String text) {
        Nachricht nachricht = new Nachricht(vonBenutzer, anBenutzer, text);
        em.persist(nachricht);
        return em.merge(nachricht);
    }
    
    /**
     * @return Liste mit allen Nachrichten in umgedrehter Anlagereihenfolge
     */
    public List<Nachricht> findAllNachricht() {
        return em.createQuery("SELECT w FROM Nachricht w ORDER BY w.id DESC").getResultList();
    }
    
    /**
     * Einzelne Nachricht ermitteln
     * @param id ID der Nachricht
     * @return Gefundenes Objekt oder null
     */
    public Nachricht findNachricht(long id) {
        return em.find(Nachricht.class, id);
    }
    
    /**
     * Löschen einer Nachricht.
     * @param id ID der zu löschenden Nachricht
     * @return Die gelöschte Nachricht oder null
     */
    public Nachricht deleteNachricht(long id) {
        Nachricht nachricht = em.find(Nachricht.class, id);
        
        if (nachricht != null) {
            em.remove(nachricht);
        }
        
        return nachricht;
    }
    
    /**
     * Änderungen an einer Nachricht speichern.
     * @param nachricht Die zu ändernde Nachricht
     * @return Neue, gespeicherte Version der Nachricht
     */
    public Nachricht updateNachricht(Nachricht nachricht) {
        return em.merge(nachricht);
    }
    
}
