<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!--     <nav class="navbar navbar-default navbar-fixed-top"> -->
<nav class="navbar navbar-default navbar-static-top">
   <div class="container">
      <div class="navbar-header">
         <button type="button" class="navbar-toggle collapsed" data-toggle="collapse"
            data-target="#navbar" aria-expanded="false" aria-controls="navbar">
            <span class="sr-only">Toggle navigation</span> <span class="icon-bar"></span>
            <span class="icon-bar"></span>
         </button>
         <a class="navbar-brand" href="/myProject/">DoItForMe</a>
      </div>
      <div id="navbar" class="navbar-collapse collapse">
         <ul class="nav navbar-nav">
            <%--  class="active" --%>
            <li><a href="/myProject/">Accueil</a></li>
            <li><a href="/myProject/#ancre_contenu">Actu</a></li>
            <li><a href="https://play.google.com/store/apps/details?id=fr.k49.ChargeurScenarii" target="blank">Téléchargement</a></li>
            <li><a href="boutique">Boutique</a></li>
            <li><a href="contact">Nous Contacter</a></li>
         </ul>
         <c:choose>
         <c:when test="${(user.id==0)}">
            <ul class="nav navbar-nav navbar-right">
               <li><a href="inscription">S'inscrire</a></li>
               <!--             w -->
               <li class="dropdown" id="menuLogin"><a class="dropdown-toggle"
                  href="#" data-toggle="dropdown" id="navLogin">Se connecter</a>
                  <div class="dropdown-menu pull-right" style="padding: 17px;">
                     <f:form class="form" modelAttribute="user" method="POST"
                        action="connectUser">
                        <c:if test="${ not empty error}">
                           <font color="red"><c:out value="${error}"/></font>
                        </c:if>
                        <label>Email</label>
                        <f:input name="email" id="email" type="text" path="email" />
                        <label>Mot de passe</label>
                        <f:input name="password" id="password" type="password"
                           path="password" />
                        <br>
                        <button type="submit" id="btnLogin" class="btn" name="login">Connexion</button>
                     </f:form>
                  </div></li>
               <!-- w -->
            </ul>
         </c:when>
         <c:otherwise>
            <ul class="nav navbar-nav navbar-right">
               <li><a>Bonjour ${user.prenom}</a></li>
               <li><a href="coordonnees">Mon compte</a>
               <li><a href="mesScenarii">Mes scenarii</a>
               <li><a href="deconnexion">Me déconnecter</a>
            </ul>
         </c:otherwise>
         </c:choose>
      </div>
   </div>
</nav>