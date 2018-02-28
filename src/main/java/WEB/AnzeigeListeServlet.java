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
import JPA.Anzeige;
import JPA.Kategorie;
import java.io.IOException;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet für die Startseite bzw. jede Seite, die eine Liste der Aufgaben
 * zeigt.
 */
@WebServlet(urlPatterns = {"/app/tasks/"})
public class AnzeigeListeServlet extends HttpServlet {

    @EJB
    private KategorieBean kategorieBean;
    
    @EJB
    private AnzeigeBean anzeigeBean;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Verfügbare Kategorien und Stati für die Suchfelder ermitteln
        request.setAttribute("kategorie", this.kategorieBean.findAllKategorie());
   

        // Suchparameter aus der URL auslesen
        String searchText = request.getParameter("search_text");
        String searchKategorie = request.getParameter("search_kategorie");
        String searchStatus = request.getParameter("search_status");
        String searchSlug = request.getParameter("search_slug");

        // Anzuzeigende Aufgaben suchen
        Kategorie kategorie = null;
      

        if (searchKategorie != null) {
            try {
                kategorie = this.kategorieBean.findKategorie(searchSlug);
            } catch (NumberFormatException ex) {
                kategorie = null;
            }
        }

   //     if (searchStatus != null) {
   //         try {
   //             status = TaskStatus.valueOf(searchStatus);
   //         } catch (IllegalArgumentException ex) {
   //             status = null;
   //         }

        

        List<Anzeige> anzeige = this.anzeigeBean.search(searchText, new Kategorie (searchSlug, searchKategorie));
        request.setAttribute("Anzeige", anzeige);

        // Anfrage an die JSP weiterleiten
        request.getRequestDispatcher("/WEB-INF/app/task_list.jsp").forward(request, response);
    }
}
