<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
      <c:if test="${ not empty error}">
         <font color="red"><c:out value="${error}" /></font>
      </c:if>
      <f:form class="form-signin" method="POST" modelAttribute="user" action="editUser">
         <label for="inputEmail">Adresse Email<font color="red">*</font></label>
         <f:input type="email" id="inputEmail" path="email" value="${user.email}" />
         <label for="inputNom">Nom</label>
         <f:input type="text" id="inputNom" path="nom" value="${user.nom}" />
         <label for="inputPrenom">Prénom</label>
         <f:input type="text" id="inputPrenom" path="prenom" value="${user.prenom}" />
         <label for="inputPassword">Mot de passe<font color="red" >*</font></label>
         <f:input type="password" id="inputPassword" path="password"/>
         <label for="confirmPassword">Confirmation<font color="red">*</font></label>
         <f:input type="password" id="confirmPassword" path="confirmPassword"/>
         <br>
         <button class="btn btn-lg btn-primary btn-block" type="submit">Enregistrement</button>
      </f:form>

   </div>
   <%@ include file="../../bloc/footer.jsp"%>
</body>
</html>