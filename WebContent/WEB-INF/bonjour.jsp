<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<title>Test</title>
</head>
<body>
    <form method="post" action="bonjour">
	    <table>
		    <tr>
		  		<td> Nom </td>
		  		<td> Description </td>
		  		<td> Prix </td>
		  		<td> Supprimer </td>
			</tr>	
			<tr>
			    <td><input type="text" name="nom" id="nom" /></td>
			    <td><input type="text" name="description" id="description" /></td>
			    <td><input type="text" name="prix" id="prix" /></td>
			    <td><input type="checkbox" name="todelete" id="todelete"  /></td>
			
			</tr>
	    </table>    
        <input type="submit" />
    </form>
    <form method="post" action="delete">
		  <table rules="all" style="border:solid 1px black;">
		  <tr>
		  		<td> Nom </td>
		  		<td> Description </td>
		  		<td> Prix </td>
		  		<td> Supprimer </td>
		  </tr>		
		
		  <c:forEach var="article" items="${ articles }">
		  <tr>  
		            <td><c:out value="${ article.nom }" /></td>
		            <td><c:out value="${ article.description }" /></td>
		            <td><c:out value="${ article.prix }" /></td>
		            <td><input type="checkbox" value="${article.idarticle}" /></td>
		  </tr>            
		  </c:forEach>
		  </table>  
		  <input type="submit" />
  	</form>
</body>
</html>