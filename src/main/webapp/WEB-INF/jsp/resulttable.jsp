<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <form action="/filtersearch" method="GET">
        <%--    <input type = "hidden" name = "size" value="2"  />--%>
        <%--    <input type = "hidden" name = "page" value="0"  />--%>
        Search :<br/>
        <div>
            field:
            <select id="dropdown" name="field">
                <option value="id" selected>id</option>
                <option value="name">name</option>
                <option value="birthday">birthday</option>
            </select>
            <br/><br/>
            <%--    <input type = "text" name = "field"  /></br>--%>
            search name:
            <input type="text" name="name"/></br>
            <input type="submit" value="Select Subject"/>
        </div>
    </form>
    <br/>
    All Results
    <br/><br/>
    <div>
        <table border="1">
            <!-------------------------------------------------->
            <tbody id="myTable">

            <tr>
                <th>id</th>
                <th>name</th>
                <th>gender</th>
                <th>birthday</th>
                <th>married</th>
                <th>email</th>
                <th>note</th>
                <th>profession</th>
            </tr>
            <c:choose>

                <c:when test="${users.size() > 0 }">

                    <c:forEach var="emp" items="${users}">
                        <tr align="center">

                            <td>${emp.id}</td>
                            <td>${emp.name}</td>
                            <td>${emp.gender}</td>
                            <td>${emp.birthday}</td>
                            <td>${emp.married}</td>
                            <td>${emp.email}</td>
                            <td>${emp.note}</td>
                            <td>${emp.profession}</td>
                        </tr>
                    </c:forEach>
                </c:when>
                <c:otherwise>
                    <tr align="center">
                        <td colspan="5">No Users available</td>
                    </tr>
                </c:otherwise>
            </c:choose>

            <c:if test="${users.size() > 0 }">
                <div class="panel-footer">
                    <ul class="pagination">
                        <c:forEach begin="1" end="${totalPage}" var="page">
                            <a href="/filtersearch?page=${page-1}&size=${size}&field=${field}&name=${name}"
                               class="page-link">${page}</a>
                        </c:forEach>
                    </ul>
                </div>
            </c:if>

            </tbody>
        </table>
        </form>
    </div>
</div>
</body>
</html>
