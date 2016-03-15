<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html lang="es">
<head>
<meta charset="utf-8">
<title>Spring MVC. CreateCourt</title>
<style>.error {	color: red;}</style>
</head>
<body>
	<h1>Create court</h1>
	<form:form action="create-court" modelAttribute="court">
		<form:hidden path="courtId"/>
		<input type="submit" value="Crear">
	</form:form>

	<a href="<c:url value="/home"/>">Home</a>


</body>
</html>