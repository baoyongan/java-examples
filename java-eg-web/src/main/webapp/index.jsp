<%@page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<html>
<body>

<jsp:forward page='login.jsp' />
<%--下面这种方式会正对客户端对session的禁用处理自动添加jsessionid--%>
<%--<jsp:forward page='<%=response.encodeURL("login.jsp") %>' />--%>
<%--客户端禁用session的时候处理方式--%>
<%--<a href="login.jsp;jsessionid=<%=session.getId()%>"> 登陆</a>--%>
</body>
</html>
