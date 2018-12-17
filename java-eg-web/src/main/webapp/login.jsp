<%@ page import="java.util.Date" %>
<%@page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<html>
<body>
<h2>登陆</h2>
<p> 时间:<%=(new Date().toLocaleString())%></p>


<form action="login" method="post">
    <%--客户端禁用session的时候处理方式--%>
<%--<form action="login;jsessionid=<%=session.getId() %>" method="post">--%>
<%--<form action='<%=response.encodeURL("login") %>' method="post">--%>
    <input type="text" name="name" placeholder="请输入名称"/>
    <input type="password" name="password" placeholder="请输入密码" />
    <input type="submit" value="登录">
</form>

</body>
</html>
