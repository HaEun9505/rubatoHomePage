<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
		int checkId = Integer.parseInt(request.getAttribute("checkIdValue").toString());
		int checkPw = Integer.parseInt(request.getAttribute("checkPwValue").toString());
		if(checkId == 0) {	
	%>
		<script type="text/javascript">
			alert("입력하신 아이디는 존재하지 않습니다. 다시 확인해주세요.")
			history.go(-1);
		</script>
	<%
		} else if(checkPw == 0) {	
	%>
		<script type="text/javascript">
			alert("입력하신 비밀번호가 맞지 않습니다.. 다시 확인해주세요.")
			history.go(-1);
		</script>
	<%
		} else {
			response.sendRedirect("index");
		}
	%>
</body>
</html>