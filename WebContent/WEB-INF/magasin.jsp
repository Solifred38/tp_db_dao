<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
 "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="UTF-8">
<title>Creation de magasin</title>
</head>
<body>
<h1>Creation d'un magasin</h1>
<form method="post" action="gerer_magasin" name="n">
<table rules="all" style="border: solid 1px black;">
			<tbody>
				<tr id="titrecolonnes">
					<td>Nom</td>
					<td>Supprimer</td>
				</tr>
				<%
					int i = 0;
				%>
				<c:forEach var="magasin" items="${ magasins }">
					<tr>
						<td><c:out value="${ magasin.nom }" /></td>
					    <td><input type="checkbox" value="<%=i%>" name="<%=i++%>"></td>
					</tr>
				</c:forEach>
				<tr>
				<tr>
					<td><input id="nom_magasin" name="nom_magasin"></td>
					<td><input id="todelete" type="checkbox" name="todelete"></td>

				</tr>
			</tbody>
		</table>
		<input type="submit" name="Requete_Principale_Magasins" value="Soumettre la requête">

</form>
</body>
</html>