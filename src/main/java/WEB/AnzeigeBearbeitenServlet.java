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
import EJB.BenutzerBean;
import JPA.Anzeige;
import JPA.Benutzer;
import JPA.PreisArt;
import java.io.IOException;
import java.util.Date;
import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Seite zum Anlegen oder Bearbeiten einer Aufgabe.
 */
@WebServlet(urlPatterns = "/app/anzeige/*")
public class AnzeigeBearbeitenServlet extends HttpServlet {

    @EJB
    AnzeigeBean anzeigeBean;

    @EJB
    KategorieBean kategorieBean;

    @EJB
    BenutzerBean benutzerBean;

    @EJB
    ValidationBean validationBean;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Verfügbare Kategorien und Stati für die Suchfelder ermitteln
        request.setAttribute("kategorien", this.kategorieBean.findAllKategorie());
//        request.setAttribute("statuses", AnzeiStatus.values());

        // Zu bearbeitende Aufgabe einlesen
        HttpSession session = request.getSession();

        Anzeige anzeige = this.getRequestedTask(request);
        request.setAttribute("edit", anzeige.getId() != 0);
                                
        if (session.getAttribute("task_form") == null) {
            // Keine Formulardaten mit fehlerhaften Daten in der Session,
            // daher Formulardaten aus dem Datenbankobjekt übernehmen
            request.setAttribute("task_form", this.createTaskForm(anzeige));
        }

        // Anfrage an die JSP weiterleiten
        request.getRequestDispatcher("/WEB-INF/app/anzeige_edit.jsp").forward(request, response);

        session.removeAttribute("anzeige_form");
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
            case "save":
                this.anzeigeSpeichern(request, response);
                break;
            case "delete":
                this.anzeigeLoeschen(request, response);
                break;
        }
    }

    /**
     * Aufgerufen in doPost(): Neue oder vorhandene Aufgabe speichern
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    private void anzeigeSpeichern (HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Formulareingaben prüfen
        List<String> errors = new ArrayList<>();

        String anzeigeKategorie = request.getParameter("anzeige_kategorie");
        String anzeigeTitel = request.getParameter("anzeige_titel");
        String anzeigeBenutzer = request.getParameter("anzeige_benutzer");
        String anzeigeBeschreibung = request.getParameter("anzeige_beschreibung");
        String anzeigeOrt = request.getParameter("anzeige_ort");
        long anzeigePreisvorstellung = Long.parseLong(request.getParameter("anzeige_preisvorstellung"));
        long anzeigePostleitzahl = Long.parseLong(request.getParameter("anzeige_postleitzahl"));
        String anzeigeErstelldatum = request.getParameter("anzeige_erstelldatum");
        String anzeigeOnlineBis = request.getParameter("anzeige_onlinebis");
        String anzeigePreisArt = request.getParameter("anzeige_preisart");
        String anzeigeArt = request.getParameter("anzeige_art");        
       
        Anzeige anzeige = this.getRequestedTask(request);
        anzeige.setTitel(anzeigeTitel);
        anzeige.setKategorie(anzeigeKategorie);
        anzeige.setBeschreibung(anzeigeBeschreibung);
        anzeige.setOrt(anzeigeOrt);
        anzeige.setPreisvorstellung(anzeigePreisvorstellung);
        anzeige.setPostleitzahl(anzeigePostleitzahl);
        anzeige.setArtDerAnzeige(anzeigeArt);
        anzeige.setReleasedBenutzer(benutzerBean.findBenutzer(anzeigeBenutzer));
        
        try {
            anzeige.setPreisArt(PreisArt.valueOf(anzeigePreisArt));
        } catch (IllegalArgumentException ex) {
            errors.add("Der ausgewählte Status ist nicht vorhanden.");
        }
        Date datumAnzeigeErstelldatum = WebUtils.parseDate(anzeigeErstelldatum);
        Date datumAnzeigeOnlineBis = WebUtils.parseDate(anzeigeOnlineBis);

        if (datumAnzeigeErstelldatum != null) {
            anzeige.setErstelldatum(datumAnzeigeErstelldatum);
        } else {
            errors.add("Das Datum muss dem Format dd.mm.yyyy entsprechen.");
        }

        if (datumAnzeigeOnlineBis != null) {
            anzeige.setOnlineBis(datumAnzeigeOnlineBis);
        } else {
            errors.add("Das Datum muss dem Format dd.mm.yyyy entsprechen.");
        }

       

        this.validationBean.validate(anzeige, errors);

        // Datensatz speichern
        if (errors.isEmpty()) {
            this.anzeigeBean.update(anzeige);
        }

        // Weiter zur nächsten Seite
        if (errors.isEmpty()) {
            // Keine Fehler: Startseite aufrufen
            response.sendRedirect(WebUtils.appUrl(request, "/app/anzeige/"));
        } else {
            // Fehler: Formuler erneut anzeigen
            FormValues formValues = new FormValues();
            formValues.setValues(request.getParameterMap());
            formValues.setErrors(errors);

            HttpSession session = request.getSession();
            session.setAttribute("anzeige_form", formValues);

            response.sendRedirect(request.getRequestURI());
        }
    }

    /**
     * Aufgerufen in doPost: Vorhandene Aufgabe löschen
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    private void anzeigeLoeschen (HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Datensatz löschen
        Anzeige anzeige = this.getRequestedTask(request);
        this.anzeigeBean.delete(anzeige);

        // Zurück zur Übersicht
        response.sendRedirect(WebUtils.appUrl(request, "/app/anzeige/"));
    }

    /**
     * Zu bearbeitende Aufgabe aus der URL ermitteln und zurückgeben. Gibt
     * entweder einen vorhandenen Datensatz oder ein neues, leeres Objekt
     * zurück.
     *
     * @param request HTTP-Anfrage
     * @return Zu bearbeitende Aufgabe
     */
    private Anzeige getRequestedTask(HttpServletRequest request) {
        // Zunächst davon ausgehen, dass ein neuer Satz angelegt werden soll
        Anzeige anzeige = new Anzeige();
     //   anzeige.setOwner(this.benutzerBean.getAktuellerBenutzer());
       // anzei.setDueDate(new Date(System.currentTimeMillis()));
        //task.setDueTime(new Time(System.currentTimeMillis()));

        // ID aus der URL herausschneiden
        String taskId = request.getPathInfo();

        if (taskId == null) {
            taskId = "";
        }

        taskId = taskId.substring(1);

        if (taskId.endsWith("/")) {
            taskId = taskId.substring(0, taskId.length() - 1);
        }

        // Versuchen, den Datensatz mit der übergebenen ID zu finden
        try {
            anzeige = this.anzeigeBean.findById(anzeige.id);
        } catch (NumberFormatException ex) {
            // Ungültige oder keine ID in der URL enthalten
        }

        return anzeige;
    }

    /**
     * Neues FormValues-Objekt erzeugen und mit den Daten eines aus der
     * Datenbank eingelesenen Datensatzes füllen. Dadurch müssen in der JSP
     * keine hässlichen Fallunterscheidungen gemacht werden, ob die Werte im
     * Formular aus der Entity oder aus einer vorherigen Formulareingabe
     * stammen.
     *
     * @param task Die zu bearbeitende Aufgabe
     * @return Neues, gefülltes FormValues-Objekt
     */
    private FormValues createTaskForm(Anzeige anzeige) {
        Map<String, String[]> values = new HashMap<>();

        values.put("anzeige_benutzer", new String[]{
            anzeige.getReleasedBenutzer().getBenutzername()
        });

         values.put("anzeige_titel", new String[]{
            anzeige.getTitel()
        });
         
         values.put("anzeige_beschreibung", new String[]{
            anzeige.getBeschreibung()
        });
         
          values.put("anzeige_ort", new String[]{
            anzeige.getOrt()
        });
          
           values.put("anzeige_erstelldatum", new String[]{
            anzeige.getErstelldatum().toString()
        });
           
            values.put("anzeige_onlinebis", new String[]{
            anzeige.getOnlineBis().toString()
        });
             values.put("anzeige_preisart", new String[]{
            anzeige.getPreisArt().toString()
        });
            values.put("anzeige_art", new String[]{
            anzeige.getArtDerAnzeige()
        });
          






        
        
        
        
        
        
        
        
        if (anzeige.getKategorie() != null) {
            values.put("anzeige_kategorie", new String[]{
                anzeige.getKategorie().toString()
            });
        }

        
        FormValues formValues = new FormValues();
        formValues.setValues(values);
        return formValues;
    }

}
