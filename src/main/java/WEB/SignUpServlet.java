/*
 * Copyright © 2018 Dennis Schulmeister-Zimolong
 * 
 * E-Mail: dhbw@windows3.de
 * Webseite: https://www.wpvs.de/
 * 
 * Dieser Quellcode ist lizenziert unter einer
 * Creative Commons Namensnennung 4.0 International Lizenz.
 */
package WEB;


import EJB.ValidationBean;
import EJB.BenutzerBean;
import JPA.Benutzer;
import java.io.IOException;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet für die Registrierungsseite. Hier kann sich ein neuer Benutzer
 * registrieren. Anschließend wird der auf die Startseite weitergeleitet.
 */
@WebServlet(urlPatterns = {"/signup/"})
public class SignUpServlet extends HttpServlet {
    
    @EJB
    ValidationBean validationBean;
            
    @EJB
    BenutzerBean benutzerBean;
    
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // Anfrage an dazugerhörige JSP weiterleiten
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/login/signup.jsp");
        dispatcher.forward(request, response);
        
        // Alte Formulardaten aus der Session entfernen
        HttpSession session = request.getSession();
        session.removeAttribute("signup_form");
    }
    
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // Formulareingaben auslesen
        request.setCharacterEncoding("utf-8");
        
        String benutzername = request.getParameter("signup_benutzername");
        String passwort = request.getParameter("signup_passwort");
        String passwort2 = request.getParameter("signup_passwort2");
        String vorname = request.getParameter("signup_vorname");
        String nachname = request.getParameter("signup_nachname");
        String strasse = request.getParameter("signup_strasse");
        long postleitzahl = Long.parseLong(request.getParameter("signup_postleitzahl"));
        String ort = request.getParameter("signup_ort");
        String land = request.getParameter("signup_land");
        String mail = request.getParameter("signup_mail");
        long telefonnummer = Long.parseLong(request.getParameter("signup_telefonnumer"));
        // Eingaben prüfen
        Benutzer benutzer = new Benutzer(benutzername,  Benutzer.hashPasswort(passwort),  vorname,  nachname,  strasse,  postleitzahl,  ort,  land,  mail,  telefonnummer);
        List<String> errors = this.validationBean.validate(benutzer);
        
        if (passwort != null && passwort2 != null && !passwort.equals(passwort2)) {
            errors.add("Die beiden Passwörter stimmen nicht überein.");
        }
        
        // Neuen Benutzer anlegen
        if (errors.isEmpty()) {
            try {
                this.benutzerBean.createNewBenutzer(benutzername,  Benutzer.hashPasswort(passwort),  vorname,  nachname,  strasse,  postleitzahl,  ort,  land,  mail,  telefonnummer);
            } catch (BenutzerBean.UserAlreadyExistsException ex) {
                errors.add(ex.getMessage());
            }
        }
        
        // Weiter zur nächsten Seite
        if (errors.isEmpty()) {
            // Keine Fehler: Startseite aufrufen
            request.login(benutzername, passwort);
            response.sendRedirect(WebUtils.appUrl(request, "/app/tasks/"));
        } else {
            // Fehler: Formuler erneut anzeigen
            FormValues formValues = new FormValues();
            formValues.setValues(request.getParameterMap());
            formValues.setErrors(errors);
            
            HttpSession session = request.getSession();
            session.setAttribute("signup_form", formValues);
            
            response.sendRedirect(request.getRequestURI());
        }
    }
    
}
