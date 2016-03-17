<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html lang="es">
<head>
<meta charset="utf-8">
<title>Crear entrenamiento</title>
</head>
<body>
<h1>Crear un entrenamiento</h1>
<form:form action="create-training" modelAttribute="training">
		
		<p>Fecha de inicio:
			<input type="datetime-local" path="dateIni" placeholder="Fecha de inicio" required="required" />
		</p>
		<p>Fecha de finalización:
			<input type="datetime-local" path="dateEnd" placeholder="Fecha de finalización" required="required" />
		<p>Pista:
			<form:input path="court.courtId" placeholder="Pista" required="required" />
		</p>
		<p>Entrenador:
			<form:input path="trainer.username" placeholder="Entrenador" required="required" />
		</p>
		
		<p><input type="submit" value="Crear"></p>
	</form:form>
<a href="<c:url value="/training/list-trainings"/>">Home</a>
<a href="<c:url value="/home"/>">Home</a>
</body>
</html>