<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Users List</title>
    <link href="<c:url value='bootstrap.css' />" rel="stylesheet"/>
    <link href="<c:url value='/app.css' />" rel="stylesheet"/>
</head>
<body>
<div align="left">
    <%@ include file="menu.jsp" %>
</div>
<form method="get" action="/studenttable">
    <label>address</label>
    <input name="address" type="text">
    <input name="page" type="hidden" value="0">
    <input name="size" type="hidden" value="2">
    <button href="">Search</button>
</form>
<div class="generic-container">
    <div class="panel panel-default">
        <!-- Default panel contents -->
        <div class="panel-heading"><span class="lead">List of Users </span></div>
        <table class="table table-hover">
            <thead>
            <tr>
                <th>id</th>
                <th>name</th>
                <th>Email</th>
                <th>first address</th>
                <th>second address</th>
                <th width="100"></th>
                <th width="100"></th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${studentdtolist}" var="student">
                <tr>
                    <td>${student.id}</td>
                    <td>${student.name}</td>
                    <td>${student.email}</td>
                    <td>${student.firstAddress}</td>
                    <td>${student.secondAddress}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
    <div class="well">
        <c:if test="${studentdtolist.size() > 0 }">
            <div class="panel-footer">
                <ul class="pagination">
                    <c:forEach begin="1" end="${totalPage}" var="page">
                        <a href="/studenttable?address=${address}&page=${page-1}&size=${size}"
                           class="page-link">${page}</a>
                    </c:forEach>
                </ul>
            </div>
        </c:if>
    </div>
</div>
</body>
</html>
