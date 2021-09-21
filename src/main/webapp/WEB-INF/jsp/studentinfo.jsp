<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Student Info</title>
    <link href="<c:url value='/bootstrap.css' />" rel="stylesheet"/>
    <link href="<c:url value='/app.css'/>" rel="stylesheet"/>
</head>
<body>
<div>
    <%@ include file="menu.jsp" %>
</div>

<h2>Student Info</h2>
<div class="generic-container">
    <div class="well lead">Student Info</div>
    <form:form method="post" modelAttribute="studentdto" action="processStudentForm" class="form-horizontal">
        <div class="row">
            <div class="form-group col-md-12">
                <label class="col-md-3 control-lable">id (just for search):</label>
                <div class="col-md-7">
                    <form:input path="id" class="form-control input-sm"/>
                    <div class="has-error">
                        <form:errors path="id"/>
                            ${message}
                            <%--                        <form:errors path="id"  class="help-inline"/>--%>
                    </div>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="form-group col-md-12">
                <label class="col-md-3 control-lable">Full name:</label>
                <div class="col-md-7">
                    <form:input path="name" class="form-control input-sm"/>
                    <form:errors path="name"/>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="form-group col-md-12">
                <label class="col-md-3 control-lable">E-mail:</label>
                <div class="col-md-7">
                    <form:input path="email" class="form-control input-sm"/>
                        <%--                    <div class="has-error">--%>
                    <form:errors path="email"/>
                </div>
            </div>
        </div>

        <div class="row">
            <div class="form-group col-md-12">
                <label class="col-md-3 control-lable">Address :</label>
                <div class="col-md-7">
                    <form:input path="firstAddress" class="form-control input-sm"/>
                    <form:errors path="firstAddress"/>
                </div>
            </div>
        </div>

        <div class="row">
            <div class="form-group col-md-12">
                <label class="col-md-3 control-lable">Address :</label>
                <div class="col-md-7">
                    <form:input path="secondAddress" class="form-control input-sm"/>

                        <%--                <div class="has-error">--%>
                    <form:errors path="secondAddress"/>
                </div>
            </div>
        </div>


        <input type="submit" name="edit" value="edit" class="btn btn-primary btn-sm"/>
        <input type="submit" name="delete" value="delete" class="btn btn-primary btn-sm"/>
        <input type="submit" name="add" value="add" class="btn btn-primary btn-sm"/>
        <input type="submit" name="search" value="search" class="btn btn-primary btn-sm"/>
    </form:form>
</div>

</body>
</html>
