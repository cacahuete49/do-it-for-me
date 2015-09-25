<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<%@ page session="false"%>
<!DOCTYPE html>
<html>
<%@ include file="../../bloc/head.jsp"%>
<head>
<title>Admin - DashBoard</title>
</head>
<body>
   <%@ include file="../../bloc/header.jsp"%>
   <div class="container marketing">
      <h2>Synthèse administration</h2>
      <div class="row">
         <h3>Message</h3>
         <p>
            Vous avez ${(messages)} <a href="adminmessages">messages</a>
         </p>

      </div>
      <hr class="featurette-lg-divider" />
      <div class="row">
         <h3>Utilisateur</h3>
         <p>Utilisateur payant : ${(usersPayant)}</p>
         <p>Utilisateur gratuit : ${(usersGratuit)}</p>
         <p>Total : ${users}</p>
      </div>
      <hr class="featurette-lg-divider" />
      <div class="row">
         <h3>Scenario</h3>
         <p>Les utilisateurs ont téléchargé ${(achats)} scenarii.</p>
         <p>Gain :${(gain)} &euro;</p>
      </div>
      <hr class="featurette-lg-divider" />
   </div>
   <%@ include file="../../bloc/footer.jsp"%>
</body>
</html>
