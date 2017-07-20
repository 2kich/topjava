<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Create/update meal</title>
</head>

<body>

<h3><a href="index.html">Home</a></h3>
<h3><a href="meals">Meals</a></h3>
<h2>Create/update meal</h2>


<form name="editMeal" method="post" action="meals">
    <input name="id" type="hidden" value="${meal.id}" />
    DateTime:
    <input name="dateTime" type="datetime-local" value="${meal.dateTime}" /> <br/>
    Description:
    <input name="description" type="text" value="${meal.description}" /> <br/>
    Calories:
    <input name="calories" type="text" value="${meal.calories}" /> <br/>
    <input name="save" type="submit" value="Save" />
</form>

</body>
</html>