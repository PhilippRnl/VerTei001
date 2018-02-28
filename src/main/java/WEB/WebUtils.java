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

import java.sql.Date;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.servlet.http.HttpServletRequest;

/**
 * Statische Hilfsmethoden
 */
public class WebUtils {
    public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd.MM.yyyy");
    public static final SimpleDateFormat TIME_FORMAT = new SimpleDateFormat("HH:mm:ss");
    
    /**
     * Stellt sicher, dass einer URL der Kontextpfad der Anwendung vorangestellt
     * ist. Denn sonst ruft man aus Versehen Seiten auf, die nicht zur eigenen
     * Webanwendung gehören.
     * 
     * @param request HttpRequest-Objekt
     * @param url Die aufzurufende URL
     * @return  Die vollständige URL
     */
    public static String appUrl(HttpServletRequest request, String url) {
        return request.getContextPath() + url;
    }
    
    /**
     * Formatiert ein Datum für die Ausgabe, z.B. 31.12.9999
     * @param date Datum
     * @return String für die Ausgabe
     */
    public static String formatDate(Date date) {
        return DATE_FORMAT.format(date);
    }
    
    /**
     * Formatiert eine Uhrzeit für die Ausgabe, z.B. 11:50:00
     * @param time Uhrzeit
     * @return String für die Ausgabe
     */
    public static String formatTime(Time time) {
        return TIME_FORMAT.format(time);
    }
    
    /**
     * Erzeugt ein Datumsobjekt aus dem übergebenen String, z.B. 03.06.1986
     * @param input Eingegebener String
     * @return Datumsobjekt oder null bei einem Fehler
     */
    public static Date parseDate(String input) {
        try {
            java.util.Date date = DATE_FORMAT.parse(input);
            return new Date(date.getTime());
        } catch (ParseException ex) {
            return null;
        }
    }
    
    /**
     * Erzeugt ein Uhrzeitobjekt aus dem übergebenen String, z.B. 09:20:00
     * @param input Eingegebener String
     * @return Uhrzeitobjekt oder null bei einem Fehler
     */
    public static Time parseTime(String input) {
        try {
            java.util.Date date = TIME_FORMAT.parse(input);
            return new Time(date.getTime());
        } catch (ParseException ex) {
            return null;
        }
    }

}
