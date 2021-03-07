<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<c:set var='root' value="${pageContext.request.contextPath}/"/>
<script>
    var flag = "${successFlg}";
    switch(flag){
        case "write_pro":
            alert("입력이 완료되었습니다.");
            location.href = "${root}";
            break;
    }
</script>








