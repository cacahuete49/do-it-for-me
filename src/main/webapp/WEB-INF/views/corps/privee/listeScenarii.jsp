<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<%@ include file="../../bloc/head.jsp"%>
<head>
<title>Mes scenarii</title>
</head>
<body>
   <%@ include file="../../bloc/header.jsp"%>
   <div class="container marketing">
      <c:if test="${empty user.mesScenarii }">
         Il serait temps de passer par la <a href="boutique">boutique</a> !
         
      </c:if>
      <c:set var="count" value="0" scope="page" />
      <c:forEach items="${user.mesScenarii}" var="scenario">
         <c:set var="count" value="${count + 1}" scope="page"/>
         <div class="row">
            <div class="col-md-1">
               <img class="featurette-image center-block" data-src="holder.js/96x96"
                  alt="img 96x96" src="resources/img/xml.png" data-holder-rendered="true"
                  height="46px" />
            </div>
            <div class="col-md-7">
               <p>Description:</p>
               <c:out value="${scenario.scenario.description}" />
            </div>
            <div class="col-md-2">
               <c:out value="${scenario.scenario.prix}" />
               &euro;
            </div>
            <div class="col-md-2">
               Note:
               <div id='<c:out value="A${count}"/>' >
                  <script type='text/javascript'>
                     CreateListeEtoile('<c:out value="A${count}"/>',<c:out value="${scenario.noteUser}"/>,5);
                     GestionHover('<c:out value="A${count}"/>',<c:out value="${scenario.noteUser}"/>, 5);
                  </script>
               </div>
               <f:form class="form" modelAttribute="scenario" method="POST" action="note">
                  <f:hidden path="id" value="${scenario.scenario.id}"/>
                  <f:hidden path="note" value="${scenario.noteUser}" />
               </f:form>
            </div>
         </div>
         <hr class="featurette-lg-divider" />
      </c:forEach>
   </div>
   <%@ include file="../../bloc/footer.jsp"%>
</body>
</html>