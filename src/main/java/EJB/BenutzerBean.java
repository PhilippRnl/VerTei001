/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EJB;

import JPA.Benutzer;
import java.util.List;
import javax.annotation.Resource;
import javax.annotation.security.RolesAllowed;
import javax.ejb.EJBContext;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


@Stateless
public class BenutzerBean {

    @PersistenceContext
    EntityManager em;
    
    @Resource
    EJBContext ctx;

    /**
     * Anlegen eines neuen Benutzers.
     * @param benutzername
     * @param passwortHash
     * @param vorname
     * @param nachname
     * @param strasse
     * @param plz
     * @param ort
     * @param land
     * @param mail
     * @param telefonnummer
     * @return Der angelegte Benutzer
     */
    public Benutzer createNewBenutzer(String benutzername, String passwortHash, String vorname, String nachname, String strasse, long postleitzahl, String ort, String land, String mail, long telefonnummer) throws UserAlreadyExistsException {
       
        if(em.find(Benutzer.class, benutzername)!= null){
            throw new UserAlreadyExistsException("Der Benutzername ist bereits vergeben");
        }
        
        
        Benutzer benutzer = new Benutzer( benutzername,  passwortHash,  vorname, nachname, strasse, postleitzahl, ort, land, mail, telefonnummer);
        benutzer.addToGroup("todo-app-user");
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

    /**
     * Gibt das Datenbankobjekt des aktuell eingeloggten Benutzers zurück,
     *
     * @return Eingeloggter Benutzer oder null
     */
    public Benutzer getAktuellerBenutzer() {
        return this.em.find(Benutzer.class, this.ctx.getCallerPrincipal().getName());
    }
        /**
     *
     * @param username
     * @param oldPassword
     * @param newPassword
     * @throws UserBean.InvalidCredentialsException
     */
  //  @RolesAllowed("todo-app-user")
    //public void changePassword(String username, String oldPassword, String newPassword) throws InvalidCredentialsException {
      //  User user = em.find(User.class, username);

        //if (user == null || !user.checkPassword(oldPassword)) {
          //  throw new InvalidCredentialsException("Benutzername oder Passwort sind falsch.");
        //}

       // user.setPassword(newPassword);
        //em.merge(user);

    /**
     * Fehler: Der Benutzername ist bereits vergeben
     */
    public class UserAlreadyExistsException extends Exception {

        public UserAlreadyExistsException(String message) {
            super(message);
        }
    }

    /**
     * Fehler: Das übergebene Passwort stimmt nicht mit dem des Benutzers
     * überein
     */
    public class InvalidCredentialsException extends Exception {

        public InvalidCredentialsException(String message) {
            super(message);
        }
    }
  


}
