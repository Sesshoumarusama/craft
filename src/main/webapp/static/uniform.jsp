<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!--使用el表达式-->
<%@ page isELIgnored="false"%>
<c:set value="${pageContext.request.contextPath}" var="ctx"/>
<script>
    /*定义的全局变量*/
    var currentUserId = '${sessionScope.userId}';
    var currentUserName = '${sessionScope.userName}';
</script>

