<%-- 
    Copyright © 2018 Dennis Schulmeister-Zimolong

    E-Mail: dhbw@windows3.de
    Webseite: https://www.wpvs.de/

    Dieser Quellcode ist lizenziert unter einer
    Creative Commons Namensnennung 4.0 International Lizenz.
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib tagdir="/WEB-INF/tags/templates" prefix="template"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<template:base>
    <jsp:attribute name="title">
        Übersicht
    </jsp:attribute>

    <jsp:attribute name="head">
        <link rel="stylesheet" href="<c:url value="/css/task_list.css"/>" />
    </jsp:attribute>

    <jsp:attribute name="menu">
        <div class="menuitem">
            <a href="<c:url value="/app/task/new/"/>">Angebot anlegen</a>
        </div>
        
        <div class="menuitem">
            <a href="<c:url value="/app/categories/"/>">Kategorien bearbeiten</a>
        </div>
        <div class="menuitem">
            <a href="<c:url value="/app/categories/"/>">Benutzer bearbeiten</a>
        </div>
        
    </jsp:attribute>

    <jsp:attribute name="content">
        <%-- Suchfilter --%>
        <form method="GET" class="horizontal" id="search">
            <input type="text" name="search_text" value="${param.search_text}" placeholder="Beschreibung"/>

            <select name="search_category">
                

                <c:forEach items="${categories}" var="categories">
                    <option value="${category.id}" ${param.search_category == category.id ? 'selected' : ''}>
                        <c:out value="${category.name}" />
                    </option>
                </c:forEach>
            </select>

            <select name="search_status">
                

                <c:forEach items="${statuses}" var="status">
                    <option value="${status}" ${param.search_status == status ? 'selected' : ''}>
                        <c:out value="${status.label}"/>
                    </option>
                </c:forEach>
            </select>

            <button class="icon-search" type="submit">
                Suchen
            </button>
        </form>

        <%-- Gefundene Aufgaben --%>
        <c:choose>
            <c:when test="${empty tasks}">
                <p>
                    Es wurden keine Aufgaben gefunden. 🐈
                </p>
            </c:when>
            <c:otherwise>
                <jsp:useBean id="utils" class="dhbwka.wwi.vertsys.javaee.jtodo.web.WebUtils"/>
                
                <table>
                    <thead>
                        <tr>
                            <th>Bezeichnung</th>
                            <th>Kategorie</th>
                            <th>Benutzer</th>
                            <th>Angebotstyp</th>
                            <th>Preistyp</th>
                            <th>Datum</th>
                        </tr>
                    </thead>
                    <c:forEach items="${tasks}" var="task">
                        <tr>
                            <td>
                                <a href="<c:url value="/app/task/${task.id}/"/>">
                                    <c:out value="${task.shortText}"/>
                                </a>
                            </td>
                            <td>
                                <c:out value="${task.category.name}"/>
                            </td>
                            <td>
                                <c:out value="${task.owner.username}"/>
                            </td>
                            <td>
                                <c:out value="${task.status.label}"/>
                            </td>
                            <td>
                                <c:out value="${utils.formatDate(task.dueDate)}"/>
                                <c:out value="${utils.formatTime(task.dueTime)}"/>
                            </td>
                        </tr>
                    </c:forEach>
                </table>
            </c:otherwise>
        </c:choose>
    </jsp:attribute>
</template:base>