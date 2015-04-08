<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="f" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<%@ include file="../bloc/head.jsp"%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Inscription</title>
</head>
<body>
   <%@ include file="../bloc/header.jsp"%>
   <div class="container">
      <!--   -->
      <f:form class="form-signin" method="POST" modelAttribute="user" action="createUser">
         <h2 class="form-signin-heading">Enregistrez-vous</h2>
         <c:if test="${ not empty error}">
            <font color="red"><c:out value="${error}" /></font>
         </c:if>
         <label for="inputEmail">Adresse Email<font color="red">*</font></label>
         <f:input type="email" id="inputEmail" path="email" />
         <label for="inputNom">Nom</label>
         <f:input type="text" id="inputNom" path="nom" />
         <label for="inputPrenom">Prénom</label>
         <f:input type="text" id="inputPrenom" path="prenom" />
         <label for="inputPassword">Mot de passe<font color="red">*</font></label>
         <f:input type="password" id="inputPassword" path="password" />
         <label for="confirmPassword">Confirmation<font color="red">*</font></label>
         <f:input type="password" id="confirmPassword" path="confirmPassword" />
         <br>
         <font color="red">(* Obligatoire)</font>
         <button class="btn btn-lg btn-primary btn-block" type="submit">Enregistrement</button>
      </f:form>

   </div>
   <%@ include file="../bloc/footer.jsp"%>
</body>
</html>