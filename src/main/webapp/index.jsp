<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>欢迎界面</title>
    </head>
    <body>
        <c:set var="path" value="${pageContext.servletContext.contextPath }" scope="application"></c:set>
        <c:redirect url="/student/studentList"></c:redirect>
    </body>
</html>