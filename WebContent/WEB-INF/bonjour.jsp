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
    
    <ul>
        <c:forEach var="article" items="${ articles }">
            <li><c:out value="${ article.nom }" /> <c:out value="${ article.description }" /><c:out value="${ article.prix }" /></li>
        </c:forEach>
    </ul>    
</body>
</html>