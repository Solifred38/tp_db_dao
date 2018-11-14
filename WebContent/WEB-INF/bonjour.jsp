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
        <p>
            <label for="nom">Article : </label>
            <input type="text" name="nom" id="nom" />
        </p>
        <p>
            <label for="description">Description : </label>
            <input type="text" name="description" id="description" />
        </p>
        <p>
            <label for="prix">Prix : </label>
            <input type="text" name="prix" id="prix" />
        </p>
        
        <input type="submit" />
    </form>
  <table>
  <tr>
  		<td> Nom </td>
  		<td> Description </td>
  		<td> Prix </td>
  		<td> Supprimer </td>
  		

  <c:forEach var="article" items="${ articles }">
  </tr>  
            <td><c:out value="${ article.nom }" /></td>
            <td><c:out value="${ article.description }" /></td>
            <td><c:out value="${ article.prix }" /></td>
            <td><input type="checkbox" value="${article.todelete}" /></td>
  </tr>            
  </c:forEach>
  </table>  
</body>
</html>