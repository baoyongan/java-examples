<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>

    <meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
    <title>@xss Demo</title>

</head>
<body>
<form action="#">
    <input type="text" placeholder="请输入名字" name="name" onblur="document.getElementById('sss').innerHTML=this.value"/>
    <div id="sss"></div>

</form>

</body>
</html>