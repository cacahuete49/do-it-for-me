<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!--     <nav class="navbar navbar-default navbar-fixed-top"> -->
<nav class="navbar navbar-default navbar-static-top">
   <div class="container">
      <div class="navbar-header">
         <button type="button" class="navbar-toggle collapsed" data-toggle="collapse"
            data-target="#navbar" aria-expanded="false" aria-controls="navbar">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
         </button>
         <a class="navbar-brand" href="/myProject/">DoItForMe</a>
      </div>
      <div id="navbar" class="navbar-collapse collapse">
         <ul class="nav navbar-nav">
            <%--  class="active" --%>
            <li><a href="/myProject/">Accueil</a></li>
            <li><a href="/myProject#ancre_contenu">Actu</a></li>
            <li><a href="boutique">Boutique</a></li>
         </ul>
         <ul class="nav navbar-nav navbar-right">
            <li><a>Bonjour ${user.prenom}</a></li>
            <li><a href="coordonnees">Mon compte</a>
            <li><a href="listeScenarii">Mes scenarii</a>
            <li><a href="deconnexion">Me déconnecter</a>
         </ul>
      </div>
      <!--/.nav-collapse -->
   </div>
</nav>
<c:out value="${sessionScope.user}" />