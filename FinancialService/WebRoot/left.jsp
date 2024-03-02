<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>无标题文档</title>
<link href="css/style.css" rel="stylesheet" type="text/css" />
<script language="JavaScript" src="js/jquery.js"></script>

<script type="text/javascript">
$(function(){	
	//导航切换
	$(".menuson li").click(function(){
		$(".menuson li.active").removeClass("active")
		$(this).addClass("active");
	});
	
	$('.title').click(function(){
		var $ul = $(this).next('ul');
		$('dd').find('ul').slideUp();
		if($ul.is(':visible')){
			$(this).next('ul').slideUp();
		}else{
			$(this).next('ul').slideDown();
		}
	});
})	
</script>


</head>

<body style="background:#f0f9fd;">
    
    <dl class="leftmenu">
        
    <dd>
    <div class="title">
    <span><img src="images/leftico01.png" /></span>管理信息
    </div>
    	<ul class="menuson">
        <li  class="active"><cite></cite><a href="index.jsp" target="rightFrame">首页</a><i></i></li>
        <li><cite></cite><a href="<%=path%>/servlet/RegisterAction?action_flag=listUser" target="rightFrame">注册用户</a><i></i></li>
        <li><cite></cite><a href="<%=path%>/servlet/MoneyAction?action_flag=listPcMoney" target="rightFrame">用户账单</a><i></i></li>
        
        
                    
       
       
       
        </ul>    
    </dd>
        
    
    
    
    
    </dl>
</body>
</html>
