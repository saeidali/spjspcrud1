<%--
ignore jsp code.
that is just for test
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div align="left">
    <%@ include file="menu.jsp" %>
</div>
<div align="center">

    <form action="/uploadcsv" method="post" enctype="multipart/form-data">
        <input type="file" name="file"/>
        <input type="submit"/>
    </form>
    <h5 style="color: red">${message}</h5>
</div>
</body>
</html>
