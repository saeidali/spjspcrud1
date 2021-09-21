<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div align="left">
    <%@ include file="menu.jsp" %>
</div>
<div align="center">
    <h2>User Registration</h2>
    <form:form method="post" modelAttribute="user" action="processForm">
        <form:label path="id">id (just for search):</form:label>
        <form:input path="id"/><br/>

        <form:label path="name">Full name:</form:label>
        <form:input path="name"/><form:errors path="name" cssStyle='color:red'/><br/>

        <form:label path="email">E-mail:</form:label>
        <form:input path="email"/>
        <form:errors path="email" cssStyle='color: red;'/><br/>

        <form:label path="password">Password:</form:label>
        <form:password path="password" showPassword="true"/><form:errors path="password" cssStyle='color: red;'/><br/>

        <form:label path="birthday">Birthday (yyyy-mm-dd):</form:label>
        <form:input path="birthday"/>
        <form:errors path="birthday" cssStyle="color: red"/><br/>

        <form:label path="gender">Gender:</form:label>
        <form:radiobutton path="gender" value="Male"/>Male
        <form:radiobutton path="gender" value="Female"/>Female<br/>

        <form:label path="profession">Profession:</form:label>
        <form:select path="profession" items="${professionList}"/><br/>

        <form:label path="married">Married?</form:label>
        <form:checkbox path="married"/><br/>

        <form:label path="note">Note:</form:label>
        <form:textarea path="note"/>
        <form:errors path="note" cssStyle='color: red'/><br/>

        <input type="submit" name="edit" value="edit"/>
        <input type="submit" name="delete" value="delete"/>
        <input type="submit" name="add" value="add"/>
        <input type="submit" name="search" value="search"/>
    </form:form>
</div>

</body>
</html>
