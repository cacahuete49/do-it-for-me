<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<%@ page session="false"%>
<!DOCTYPE html>
<html>
<%@ include file="../bloc/head.jsp"%>
<head>
<title>Boutique</title>
</head>
<body>
   <%@ include file="../bloc/header.jsp"%>
   <div class="container marketing">
      <%-- for each item a vendre --%>
      <c:forEach items="${scenariiList}" var="scenario">
         <div class="row">
            <div class="col-md-1">
               <img class="featurette-image center-block" data-src="holder.js/96x96"
                  alt="img 96x96" src="resources/img/xml.png" data-holder-rendered="true"
                  height="46px" />
            </div>
            <div class="col-md-8">
               <c:out value="${scenario.description}" />
               <p>Nombre de t&eacute;l&eacute;chargement:
                  ${fn:length(scenario.listeUser)}</p>
            </div>
            <div class="col-md-2">
               <f:form class="form" modelAttribute="scenario" method="POST" action="achat">
                  <f:hidden path="id" value="${scenario.id}" />
                  <c:choose>
                     <c:when test="${(user.id==0)}">
                        <f:button type="button" class="btn btn-lg btn-info" title=""
                           data-container="body" data-toggle="popover"
                           data-placement="right" data-trigger="hover"
                           data-content="Veuillez vous connecter">
                        Acheter <c:out value="${scenario.prix}" />&euro;
                        </f:button>
                     </c:when>
                     <c:otherwise>
                        <f:button type="submit" class="btn btn-lg btn-info" action="achat">
                        Acheter <c:out value="${scenario.prix}" />&euro;
                        </f:button>
                     </c:otherwise>
                  </c:choose>
               </f:form>
               <p>
                  <c:forEach var="j" begin="1" end="5">
                     <fmt:parseNumber var="note" type="number" value="${scenario.note}" />
                     <fmt:parseNumber var="count" type="number" value="${j}" />
                     <c:choose>
                        <c:when test="${count <= note}">
                           <img src="resources/img/staron.gif" />
                        </c:when>
                        <c:otherwise>
                           <img src="resources/img/staroff.gif" />
                        </c:otherwise>
                     </c:choose>
                  </c:forEach>

               </p>
            </div>
         </div>
         <hr class="featurette-lg-divider" />
      </c:forEach>

      <%--finfor each --%>
   </div>
   <%@ include file="../bloc/footer.jsp"%>
</body>
</html>
