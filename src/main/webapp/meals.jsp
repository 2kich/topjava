<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://javawebinar.ru/functions" prefix="f" %>


<html>
<head>
    <title>Meals</title>
</head>
<body>
<h3><a href="index.html">Home</a></h3>
<h2>Meals</h2>

<table border="2" style="background: cornsilk">
    <tr>
        <th>Date</th>
        <th>Description</th>
        <th>Calories</th>
    </tr>
    <c:forEach items="${requestScope.meals}" var="meal">

        <tr style="color: ${meal.isExceed() ? 'darkgreen' : 'red'}">
                <%--<td width="200">${meal.dateTime.toLocalDate()} ${meal.dateTime.toLocalTime()}</td>--%> <%--Just hard code for simle use--%>
            <td width="200">${f:formatLocalDateTime(meal.dateTime, 'yyyy-MM-dd HH:mm:ss')}</td>
            <td width="200">${meal.description}</td>
            <td width="200">${meal.calories}</td>

        </tr>
    </c:forEach>
</table>

</body>
</html>