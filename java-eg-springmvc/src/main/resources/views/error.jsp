<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>

    <meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
    <title>@ControllerAdvice Demo</title>

</head>
<body>

    ${errorMessage},${errorMessage} <!-- EL表达式不生效，上面的uri不能解析-->
</body>
</html>
