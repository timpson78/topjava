<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<html>
<head>
    <title>Meals</title>
    <link rel="stylesheet" href="/topjava/css/bootstrap.css">
</head>
<body>
<jsp:include page="/WEB-INF/header.jsp"/>
<h2>MEALS</h2>
<br>
<div class="">
<a href="?action=add">Добавить запись</a>
</div>
<br>
<div class="">
    <table class="table " id="datatable"  style="width: 850px;">
    <thead>
    <tr role="row">
        <th class="" tabindex="0"  rowspan="1" colspan="1"  style="width: 40px;">п/п</th>
        <th class="" tabindex="0"  rowspan="1" colspan="1"  style="width: 162px;">Дата/Время</th>
        <th class="" tabindex="0"  rowspan="1" colspan="1"  style="width: 162px;">Описание</th>
        <th class="" tabindex="0"  rowspan="1" colspan="1"  style="width: 162px;">Калории</th>
        <th class="" tabindex="0"  rowspan="1" colspan="1"  style="width: 100px">Изменить</th>
        <th class="" tabindex="0"  rowspan="1" colspan="1"  style="width: 100px;">Удалить</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${meals}" var="meal" varStatus="counter">
        <jsp:useBean id="meal"
                     type="ru.javawebinar.topjava.model.MealWithExceed"/>
        <c:choose>
            <c:when test="${meal.exceed eq true}">
                <tr class="text-danger">
                    <td>${counter.index}</td> <td><fmt:parseDate value="${meal.dateTime}" pattern="yyyy-MM-dd'T'HH:mm" var="parseDateTime" /> <fmt:formatDate pattern="dd.MM.yyyy HH:mm"  value="${parseDateTime}" /></td>
                    <td>${meal.description} </td><td>${meal.calories}</td> <td><a href="?action=edit&id=${meal.id}">Изменить</a></td><td><a href="?action=delete&id=${meal.id}">Удалить</a></td>
                </tr>
            </c:when>
            <c:when test="${meal.exceed eq false}">
                <tr class="text-success">
                    <td>${counter.index}</td> <td><fmt:parseDate value="${meal.dateTime}" pattern="yyyy-MM-dd'T'HH:mm" var="parseDateTime" /> <fmt:formatDate pattern="dd.MM.yyyy HH:mm"  value="${parseDateTime}" /></td>
                    <td>${meal.description} </td><td>${meal.calories}</td><td><a href="?action=edit&id=${meal.id}">Изменить</a></td><td><a href="?action=delete&id=${meal.id}">Удалить</a></td>
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