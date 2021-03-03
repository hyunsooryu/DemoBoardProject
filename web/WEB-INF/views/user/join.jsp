<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<c:set var='root' value="${pageContext.request.contextPath}/"/>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>미니 프로젝트</title>
    <!-- Bootstrap CDN -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js"></script>
    <script src="https://unpkg.com/axios/dist/axios.min.js"></script>
</head>
<body>
<c:import url="/WEB-INF/views/include/top_menu.jsp"/>
<div class="container" style="margin-top:100px">
    <div class="row">
        <div class="col-sm-3"></div>
        <div class="col-sm-6">
            <div class="card shadow">
                <div class="card-body">
                    <form:form action="${root}user/join_pro" method="post" modelAttribute="joinUserBean">
                        <form:hidden path="userIdExist"/>
                        <div class="form-group">
                            <form:label path="userName">이름</form:label>
                            <form:input path="userName"  class="form-control"/>
                            <form:errors path="userName"/>
                        </div>
                        <div class="form-group">
                            <form:label path="userId" >아이디</form:label>
                            <div class="input-group">
                                <form:input path="userId" class="form-control"/>
                                <div class="input-group-append">
                                    <button id="checkBtn" type="button" class="btn btn-primary">중복확인</button>
                                </div>
                            </div>
                            <form:errors path="userId"/>
                        </div>
                        <div class="form-group">
                            <form:label path="userPw">비밀번호</form:label>
                            <form:password path="userPw" class="form-control"/>
                            <form:errors path="userPw"/>
                        </div>
                        <div class="form-group">
                            <form:label path="userPw2">비밀번호 확인</form:label>
                            <form:password path="userPw2" class="form-control"/>
                            <form:errors path="userPw2"/>
                        </div>
                        <div class="form-group">
                            <div class="text-right">
                                <form:button class="btn btn-primary">회원가입</form:button>
                            </div>
                        </div>
                    </form:form>
                </div>
            </div>
        </div>
        <div class="col-sm-3"></div>
    </div>
</div>
<c:import url="/WEB-INF/views/include/bottom_info.jsp"/>
<script>

    document.getElementById("checkBtn").addEventListener("click",function(){
       checkUserIdExist();
    });

    function checkUserIdExist(){
       var userIdObject = document.getElementById("userId");
       var userId = userIdObject.value.trim();
       if(userId.length == 0){
           alert("아이디를 입력해주세요");
           return;
       }

       axios.get('${root}api/user/' + userId)
        .then(function(response){
            if(response.data.trim() == "N"){
                alert("사용할 수 있는 아이디입니다.");
                document.getElementById("userIdExist").value = "false";
            }else{
                alert("이미 중복 된 아이디가 있습니다.");
                userIdObject.value = "";
                document.getElementById("userIdExist").value = "true";
            }
        });

    }

</script>
</body>
</html>








