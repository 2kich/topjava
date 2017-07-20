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
<h4><a href="meals?action=create">Create</a></h4>

<table border="2" style="background: cornsilk">
    <tr>
        <th>DateTime</th>
        <th>Description</th>
        <th>Calories</th>
        <th>Update</th>
        <th>Delete</th>
    </tr>
    <c:forEach items="${requestScope.mealList}" var="meal">

        <tr style="color: ${meal.isExceed() ? 'darkgreen' : 'red'}">

            <td width="200">${f:formatLocalDateTime(meal.dateTime, 'yyyy-MM-dd HH:mm:ss')}</td>
            <td width="200">${meal.description}</td>
            <td width="200">${meal.calories}</td>
            <td><a href="meals?action=update&id=${meal.id}">Update</a></td>
            <td><a href="meals?action=delete&id=${meal.id}">Delete</a></td>
        </tr>
    </c:forEach>
</table>

</body>
</html>