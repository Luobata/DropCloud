
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>DropCloud</title>
<link rel="stylesheet" type="text/css" href="css/hello.css">
<script type="text/javascript">
	function resetValue(){
		document.getElementById("userName").value="";
		document.getElementById("password").value="";
	}
	
	function loadimage() {
		document.getElementById("randImage").src="image.jsp?"+Math.round();
	}
</script>

</head>
<body>
	<img class="back" src="img/login_back.png">

	<img class="hello" src="img/Hello.png">

	<div class="login_area">
		<form action="login" method="post">
			<div><input class="user"  name="user.userName"placeholder="用户名"></div>
			<div><input class="password" name="user.Password_Md5" placeholder="密码"></div>
			<div>
				<input class="login" type="submit"  value="登陆">
				<input class="reg" type="button"  value="注册">
			</div>

		</form>
	</div>
</body>