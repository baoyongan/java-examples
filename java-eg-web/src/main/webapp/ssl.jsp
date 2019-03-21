<%@ page import="java.util.Enumeration" %>
<%@page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<html>
<body>

<p>request 请求信息</p>

<pre>
    <%
        for (Enumeration en =request.getAttributeNames();en.hasMoreElements();) {
            String name= (String) en.nextElement();
            out.println(name);
            out.println(" = "+request.getAttribute(name));
            out.println();
        }
    %>
</pre>

</body>
</html>
