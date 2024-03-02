<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>欢迎登录后台管理系统</title>
<link href="css/style.css" rel="stylesheet" type="text/css" />
<script language="JavaScript" src="js/jquery.js"></script>
<script src="js/cloud.js" type="text/javascript"></script>

<script language="javascript">
	$(function() {
		$('.loginbox').css({
			'position' : 'absolute',
			'left' : ($(window).width() - 692) / 2
		});
		$(window).resize(function() {
			$('.loginbox').css({
				'position' : 'absolute',
				'left' : ($(window).width() - 692) / 2
			});
		})
	});
</script>


<script type="text/javascript">
	function login() {
		var th = document.formLogin;
		if (th.name.value != "admin") {
			alert("用户名不正确！！");
			th.username.focus();
			return;
		}
		if (th.password.value != "123456") {
			alert("密码不正确！！");
			th.pswd.focus();
			return;
		}

		th.action = "main.jsp";

	}
</script>


</head>

<body
	style="background-color:#1c77ac; background-image:url(images/light.png); background-repeat:no-repeat; background-position:center top; overflow:hidden;">



	<div id="mainBody">
		<div id="cloud1" class="cloud"></div>
		<div id="cloud2" class="cloud"></div>
	</div>


	<div class="logintop">
		<span>欢迎登录后台管理界面平台</span>
		<ul>
			<li><a href="#">回首页</a></li>
			<li><a href="#">帮助</a></li>
			<li><a href="#">关于</a></li>
		</ul>
	</div>

	<div class="loginbody">

		<span class="systemlogo"></span>

		<div class="loginbox">
   <form action="" method="post" name="formLogin">
			<ul>
				<li><input name="name" type="text" class="loginuser" value="admin"
					onclick="JavaScript:this.value=''" /></li>
				<li><input name="password" type="text" class="loginpwd" value="密码"
					onclick="JavaScript:this.value=''" /></li>
				<li><input name="" type="submit"  class="loginbtn" value="登录"
					onclick="javascript:login();"/></li>
			</ul>

   </form>    
		</div>

	</div>



	<div class="loginbm">版权所有 2013  仅供学习交流，勿用于任何商业用途</div>
</body>
</html>
