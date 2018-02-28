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


import EJB.AnzeigeBean;
import EJB.KategorieBean;
import EJB.ValidationBean;
import JPA.Kategorie;
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
 * Seite zum Anzeigen und Bearbeiten der Kategorien. Die Seite besitzt ein
 * Formular, mit dem ein neue Kategorie angelegt werden kann, sowie eine Liste,
 * die zum Löschen der Kategorien verwendet werden kann.
 */
@WebServlet(urlPatterns = {"/app/kategorien/"})
public class KategorieListServlet extends HttpServlet {

    @EJB
    KategorieBean KategorieBean;
    
    @EJB
    AnzeigeBean anzeigeBean;

    @EJB
    ValidationBean validationBean;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Alle vorhandenen Kategorien ermitteln
        request.setAttribute("kategorien", this.KategorieBean.findAllKategorie());

        // Anfrage an dazugerhörige JSP weiterleiten
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/app/category_list.jsp");
        dispatcher.forward(request, response);

        // Alte Formulardaten aus der Session entfernen
        HttpSession session = request.getSession();
        session.removeAttribute("kategorien_form");
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Angeforderte Aktion ausführen
        request.setCharacterEncoding("utf-8");
        
        String action = request.getParameter("action");

        if (action == null) {
            action = "";
        }

        switch (action) {
            case "create":
                this.createCategory(request, response);
                break;
            case "delete":
                this.deleteKategorien(request, response);
                break;
        }
    }

    /**
     * Aufgerufen in doPost(): Neue Kategorie anlegen
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    private void createCategory(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Formulareingaben prüfen
        String name = request.getParameter("name");
        
        String slug = request.getParameter("slug");

        Kategorie kategorie = new Kategorie(slug, name);
        List<String> errors = this.validationBean.validate(kategorie);

        // Neue Kategorie anlegen
        if (errors.isEmpty()) {
            this.KategorieBean.createNewKategorie(slug, name);
        }

        // Browser auffordern, die Seite neuzuladen
        if (!errors.isEmpty()) {
            FormValues formValues = new FormValues();
            formValues.setValues(request.getParameterMap());
            formValues.setErrors(errors);

            HttpSession session = request.getSession();
            session.setAttribute("kategorien_form", formValues);
        }

        response.sendRedirect(request.getRequestURI());
    }

    /**
     * Aufgerufen in doPost(): Markierte Kategorien löschen
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    private void deleteKategorien(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Markierte Kategorie IDs auslesen
        String[] kategorieIds = request.getParameterValues("kategorien");

        if (kategorieIds == null) {
            kategorieIds = new String[0];
        }

        // Kategorien löschen
        for (String kategorieId : kategorieIds) {
            // Zu löschende Kategorie ermitteln
            Kategorie kategorie;

            try {
                kategorie = this.KategorieBean.findKategorie(kategorieId);
            } catch (NumberFormatException ex) {
                continue;
            }
            
            if (kategorie == null) {
                continue;
            }
            
            
            // Und weg damit
            this.KategorieBean.deleteKategorie(kategorie);
        }
        
        // Browser auffordern, die Seite neuzuladen
        response.sendRedirect(request.getRequestURI());
    }

}
