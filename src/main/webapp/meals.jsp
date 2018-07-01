<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>


<html>
<head>
    <title>Meals</title>
    <link rel="stylesheet" href="/topjava/css/bootstrap.css">
</head>
<body>
<jsp:include page="/WEB-INF/header.jsp"/>
<h2>MEALS</h2>


<div class="">
    <table class="table " id="datatable"  style="width: 650px;">
    <thead>
    <tr role="row">
        <th class="" tabindex="0"  rowspan="1" colspan="1"  style="width: 40px;">п/п</th>
        <th class="" tabindex="0"  rowspan="1" colspan="1"  style="width: 162px;">Дата/Время</th>
        <th class="" tabindex="0"  rowspan="1" colspan="1"  style="width: 162px;">Описание</th>
        <th class="" tabindex="0"  rowspan="1" colspan="1"  style="width: 162px;">Калории</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${meals}" var="meal" varStatus="counter">
        <jsp:useBean id="meal"
                     type="ru.javawebinar.topjava.model.MealWithExceed"/>
        <c:choose>
            <c:when test="${meal.exceed eq true}">
                <tr class="text-danger">
                    <td>${counter.index}</td> <td>${meal.dateTime}</td><td>${meal.description} </td><td>${meal.calories}</td>
                </tr>
            </c:when>
            <c:when test="${meal.exceed eq false}">
                <tr class="text-success">
                    <td>${counter.index}</td> <td>${meal.dateTime}</td><td>${meal.description} </td><td>${meal.calories}</td>
                </tr>
            </c:when>
        </c:choose>
    </c:forEach>
    </tbody>


    </table>
</div>
<jsp:include page="/WEB-INF/footer.jsp"/>
</body>
</html>