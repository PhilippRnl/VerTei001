/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Aufgabe.ejb;

import Aufgabe.jpa.Benutzer;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Fabio Kraemer
 */
@Stateless
public class BenutzerBean {

    @PersistenceContext
    EntityManager em;

    /**
     * Anlegen eines neuen Benutzers.
     * @param benutzername
     * @param passwort
     * @param vorname
     * @param nachname
     * @param ort
     * @param hausnummer
     * @param plz
     * @param strasse
     * @param land
     * @param email
     * @param telefonnummer
     * @return Der angelegte Benutzer
     */
    public Benutzer createNewBenutzer(String benutzername, String passwort, String vorname, String nachname, String strasse, int hausnummer, int plz, String ort, String land, String email, String telefonnummer) {
        Benutzer benutzer = new Benutzer(benutzername, passwort, vorname, nachname, strasse, hausnummer, plz, ort, land, email, telefonnummer);
        em.persist(benutzer);
        return em.merge(benutzer);
    }

    /**
     * @return Liste mit allen Benutzern in umgedrehter Anlagereihenfolge
     */
    public List<Benutzer> findAllBenutzer() {
        return em.createQuery("SELECT w FROM Benutzer w ORDER BY w.benutzername DESC").getResultList();
    }

    /**
     * Einzelnen Benutzer ermitteln
     *
     * @param benutzername ID des Benutzer
     * @return Gefundenes Objekt oder null
     */
    public Benutzer findBenutzer(String benutzername) {
        return em.find(Benutzer.class, benutzername);
    }

    /**
     * Löschen eines Benutzers
     * @param benutzername ID des zu löschenden Benutzers
     * @return Der gelöschte Benutzer oder null
     */
    public Benutzer deleteBenutzer(String benutzername) {
        Benutzer benutzer = em.find(Benutzer.class, benutzername);

        if (benutzer != null) {
            em.remove(benutzer);
        }

        return benutzer;
    }

    /**
     * Änderungen an einem Benutzer speichern.
     * @param benutzer Der zu ändernde Textschnippsel
     * @return Neue, gespeicherte Version des Benutzers
     */
    public Benutzer updateBenutzer(Benutzer benutzer) {
        return em.merge(benutzer);
    }
}
