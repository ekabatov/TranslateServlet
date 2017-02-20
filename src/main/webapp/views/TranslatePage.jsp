<%@ page language="java" pageEncoding="windows-1251" session="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Google Translate</title>
    <style type=\"text/css\">
        TABLE {
            font-size: 70%;
            font-family: Verdana, Arial, Helvetica, sans-serif;
            color: #333366;
            border-collapse: collapse;
        }

        P {
            font-size: 70%;
        }
    </style>
    <style>
        #txt {
            height: 20px;
        }

        #btn {
            height: 20px;
            font-size: 90%;
        }
    </style>
</head>
<body>
<form action="${pageContext.servletContext.contextPath}/BookTranslate" method="POST">
    <p>Word : <input type='text' name='word' id='txt'>
        <input type='submit' value='Translate' id='btn' name='buttonTrans'>
        Translate : <input type='text' name='trWord' id='txt' value='${trWord}'>
        Page : <input type='text' name='nmPage' id='txt' value='${numberPage}'>
        <input type='submit' value='Save Txt' id='btn' name='sendToTxt'></p>
    ${table}
</form>
</body>
</html>
