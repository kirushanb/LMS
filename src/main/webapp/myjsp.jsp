<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<table>

<tbody>
                  <c:forEach var="movie" items="${messaheInterface}">
                     <tr>
                        <td>${movie.kkk}</td>
                        
                     </tr>
                  </c:forEach>
               </tbody>
</table>
</body>
</html>