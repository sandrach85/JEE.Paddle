<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html lang="es">
<head>

<meta charset="utf-8">
<title>Listado de entrenamientos</title>
</head>
<body>
	<H1>Listado de entrenamientos</H1>
	<table border="1">
		<thead>
			<tr>
				<th>Id</th>
				<th>Fecha de inicio</th>
				<th>fecha de finalización</th>
				<th>Pista</th>
				<th>Entrenador</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${trainingList}" var="training">
				<tr>
					<td>${training.id}</td>
					<td>${training.dateIni}</td>
					<td>${training.dateEnd}</td>
					<td>${training.court}</td>
					<td>${training.trainer}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<p>
		<a href="<c:url value='/training/create-training'/>">Crear Entrenamiento</a>
	</p>
	<p>
		<a href="<c:url value='/home'/>">Inicio</a>
	</p>

</body>
</html>