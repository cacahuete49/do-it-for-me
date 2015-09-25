<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<%@ page session="false"%>
<!DOCTYPE html>
<html>
<%@ include file="../../bloc/head.jsp"%>
<head>
<title>Admin - Messages</title>
</head>
<body>
   <%@ include file="../../bloc/header.jsp"%>
   <div class="container marketing">
      <%-- for each item a vendre --%>
      <c:forEach items="${messages}" var="message">
         <div class="row">
            <div class="col-md-12">
               Sujet : <c:out value="${message.objet}" />
               <br />
               <blockquote>
                  <c:out value="${message.message}" />
               </blockquote>
               <small><c:out value="${message.email}" /> - <c:out value="${message.dateEnvoi}" /></small>
            </div>
         </div>
         <hr class="featurette-lg-divider" />
      </c:forEach>

      <%--finfor each --%>
   </div>
   <%@ include file="../../bloc/footer.jsp"%>
</body>
</html>
