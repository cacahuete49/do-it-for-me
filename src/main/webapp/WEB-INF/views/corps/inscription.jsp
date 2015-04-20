<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
		<f:form class="form-signin" method="POST" modelAttribute="user"
			action="createUser">
			<h2 class="form-signin-heading">Enregistrez-vous</h2>
			<c:if test="${ not empty error}">
				<font color="red"><c:out value="${error}" /></font>
			</c:if>
			<label for="inputEmail">Adresse Email<font color="red">*</font></label>
			<f:input type="email" id="inputEmail" class="form-control"
				path="email" />
			<label for="inputNom">Nom</label>
			<f:input type="text" id="inputNom" class="form-control" path="nom" />
			<label for="inputPrenom">Prénom</label>
			<f:input type="text" id="inputPrenom" class="form-control"
				path="prenom" />
			<label for="inputPassword">Mot de passe<font color="red">*</font></label>
			<f:input type="password" id="inputPassword" class="form-control"
				path="password" />
			<label for="confirmPassword">Confirmation<font color="red">*</font></label>
			<f:input type="password" id="confirmPassword" class="form-control"
				path="confirmPassword" />
			<br>
			<font color="red">(* Obligatoire)</font>
			<button class="btn btn-lg btn-primary btn-block" type="submit">Enregistrement</button>
		</f:form>

	</div>
	<%@ include file="../bloc/footer.jsp"%>
</body>
</html>