<%@ page language="java" pageEncoding="windows-1251" session="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title></title>
</head>
<body>
    <c:forEach items="${books}" var="book" varStatus="status">
        <br><a href="${pageContext.servletContext.contextPath}/BookTranslate?id=${book.nameFile}&firstStart=true">Открыть ${book.name}</a>
    </c:forEach>
</body>
</html>
