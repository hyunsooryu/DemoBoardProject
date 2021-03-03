<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<c:set var='root' value="${pageContext.request.contextPath}/"/>
<script>
    var flag = "${successFlg}";
    switch(flag){
        case "login_pro":
            location.href = "${root}";
            break;

        case "join_pro":
            location.href = "${root}user/login";
            break;

        case "login_fail":
            alert("아이디 혹은 비밀번호가 일치하지 않습니다.");
            location.href = "${root}user/login?fail=true";
            break;

        case "log_out":
            alert("로그아웃 되었습니다.");
            location.href = "${root}main";
            break;

    }
</script>








