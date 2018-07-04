<%--
  Created by IntelliJ IDEA.
  User: tim
  Date: 02.07.2018
  Time: 11:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>


<html>
<head>
    <title>Meals</title>
    <link rel="stylesheet" href="/topjava/css/bootstrap.css">
</head>
<body>
<jsp:include page="/WEB-INF/header.jsp"/>
<h2>EDIT MEAL</h2>


<div class="">
    <table class="table " id="datatable"  style="width: 600px;">
        <thead>
        <tr role="row">
            <th class="" tabindex="0"  rowspan="1" colspan="1"  style="width: 100px;">Параметр</th>
            <th class="" tabindex="0"  rowspan="1" colspan="1"  style="width: 100px;">Значение</th>
        </tr>
        </thead>
        <tbody>
        <form method="post" action="meals" enctype="application/x-www-form-urlencoded">
                <tr role="row">
                    <th class="" tabindex="0"  rowspan="1" colspan="1">Идентификатор:</th>
                    <th class="" tabindex="0"  rowspan="1" colspan="1">${meal.id}<input  type="hidden" name="id" value="${meal.id}"> </th>
                </tr>
                <tr role="row">
                    <th class="" tabindex="0"  rowspan="1" colspan="1">Дата:</th>
                    <th class="" tabindex="0"  rowspan="1" colspan="1"><input  type="datetime-local" name="datetime" value="${meal.dateTime}" required></th>
                </tr>
                <tr role="row">
                    <th class="" tabindex="0"  rowspan="1" colspan="1">Описание:</th>
                    <th class="" tabindex="0"  rowspan="1" colspan="1"><input type="text" name="description" value="${meal.description}" required></th>
                </tr>
                <tr role="row">
                    <th class="" tabindex="0"  rowspan="1" colspan="1">Калорий:</th>
                    <th class="" tabindex="0"  rowspan="1" colspan="1"><input type="number" name="calories" value="${meal.calories}" required></th>
                </tr>
                <tr role="row">
                    <th class="" tabindex="0"  rowspan="1" colspan="1">  </th>
                    <th class="" tabindex="0"  rowspan="1" colspan="1"><button type="submit"> Сохранить </button> <button onclick="window.history.back()"> Отменить</button></th>
                </tr>

        </form>>

        </tbody>


    </table>

<jsp:include page="/WEB-INF/footer.jsp"/>
</body>
</html>