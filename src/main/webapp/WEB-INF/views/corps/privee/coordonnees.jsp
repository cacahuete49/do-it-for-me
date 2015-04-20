<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<%@ include file="../../bloc/head.jsp"%>
<head>
<title>Coordonnées</title>
</head>
<body>
   <%@ include file="../../bloc/header.jsp"%>
   <div class="container marketing">
   <h2>Coordonnées</h2>
   Email: ${user.email}<br>
   Nom: ${user.nom}<br>
   Prénom: ${user.prenom}<br>
   Nombre de scénarii: ${fn:length(user.mesScenarii)}<br>
   <c:if test="${user.role eq 'admin'}">
      <a href="admin">Administration dashboard</a><br>
   </c:if>
   <a href="editUser">Modifer mes coordonnées</a><br>
   <a href="deleteUser" onclick="return confirm('Êtes-vous sûr de vouloir supprimer votre compte ?');">Détruire mon compte</a>
   </div>
   <%@ include file="../../bloc/footer.jsp"%>
</body>
</html>