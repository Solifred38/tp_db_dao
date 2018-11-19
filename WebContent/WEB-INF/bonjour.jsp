<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
 "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<title>Gestion de courses</title>
</meta>
</head>
<body>
	<h1>Logiciel de Gestion de Courses</h1>
	<form method="post" action="bonjour">
		<select name="magasins">
			<option>tous</option>
			<option>satoriz</option>
			<option>carrefour</option>
			<option>casino</option>
		</select>
		<table rules="all" style="border: solid 1px black;">
			<tbody>
				<tr>
					<td>Nom</td>
					<td>Description</td>
					<td>Prix</td>
					<td>Supprimer</td>
				</tr>
				<%
					int i = 0;
				%>
				<c:forEach var="article" items="${ articles }">
					<tr>
						<td><c:out value="${ article.nom }" /></td>
						<td><c:out value="${ article.description }" /></td>
						<td><c:out value="${ article.prix }" /></td>
						<td><select name="magasins">
								<option>tous</option>
								<option>satoriz</option>
								<option>carrefour</option>
								<option>casino</option>
						</select></td>
						<td><input type="checkbox" value="<%=i%>" name="<%=i++%>"></td>
					</tr>
				</c:forEach>
				<tr>
				<tr>
					<td><input id="nom" name="nom"></td>
					<td><input id="description" name="description"></td>
					<td><input id="prix" name="prix"></td>
					<td><select name="magasins">
							<option>tous</option>
							<option>satoriz</option>
							<option>carrefour</option>
							<option>casino</option>
					</select></td>
					<td><input id="todelete" type="checkbox" name="todelete"></td>
					
				</tr>
			</tbody>
		</table>
		<input type="submit" value="Soumettre la requête">
	</form>
</body>
</html>
