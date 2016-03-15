<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html lang="es">
<head>
<meta charset="utf-8">
<title>Spring MVC. Court List</title>
</head>

<body>
	<H1>Listado de pistas</H1>
	<table border="1">
		<thead>
			<tr>
				<th>Active</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${courtList}" var="court">
				<tr>
					<td><c:if test="${court.active==true}">
							<h5>Active</h5>
						</c:if> 
						<c:if test="${court.active==false}">
							<h5>No active</h5>
						</c:if>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<p>
		<a href="<c:url value='/create-court'/>">Create Court</a>
	</p>
	<p>
		<a href="<c:url value='/home'/>">Home</a>
	</p>


</body>
</html>