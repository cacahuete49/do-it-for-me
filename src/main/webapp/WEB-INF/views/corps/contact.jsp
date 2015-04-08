<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
      <f:form class="form-signin" method="POST" modelAttribute="message"
         action="addMessage">
         <h2 class="form-signin-heading">Nous contacter</h2>
         <label for="inputEmail">Email</label>
         <c:choose>
            <c:when test="${(user.id==0)}">
               <f:input type="email" id="inputEmail" path="email" />
            </c:when>
            <c:otherwise>
               <f:input type="email" id="inputEmail" path="email" value="${user.email }" />
            </c:otherwise>
         </c:choose>
         <label for="inputObjet">Objet</label>
         <f:input type="text" id="inputObjet" maxlength="50" path="objet" />
         <label for="inputMessage">Message</label>
         <f:textarea id="inputMessage" path="message"></f:textarea>
         <button class="btn btn-lg btn-primary btn-block" type="submit" name="sendMessage">Envoyer</button>
      </f:form>

   </div>
   <%@ include file="../bloc/footer.jsp"%>
</body>
</html>