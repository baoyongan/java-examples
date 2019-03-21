<%@ page import="java.util.Date" %>
<%@page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<body>
<h2>登陆首页</h2>
<p> 时间:<%=(new Date().toLocaleString())%></p>
<p>Welcome:${user}</p>

<form action="message" method="post" >
    <input type="text" name="message" /> <input  type="submit" value="发送"/>
</form>

<form action="redirect" method="post" >
    <input type="text" name="redirect" /> <input  type="submit" value="重定向"/>
</form>
</body>
</html>
