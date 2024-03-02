<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	   String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()

            + path;
	List<Map<String, Object>> list = (List<Map<String, Object>>) request.getAttribute("bill");
%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head href="<%=basePath%>">
<title>账单查看</title>
<link href="../css/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/jquery.js"></script>

<script type="text/javascript">
	$(document).ready(function() {
		$(".click").click(function() {
			$(".tip").fadeIn(200);
		});

		$(".tiptop a").click(function() {
			$(".tip").fadeOut(200);
		});

		$(".sure").click(function() {
			$(".tip").fadeOut(100);
		});

		$(".cancel").click(function() {
			$(".tip").fadeOut(100);
		});

	});
</script>


</head>


<body>

	<div class="place">
		<span>位置：</span>
		<ul class="placeul">
			<li><a href="#">首页</a></li>
			<li><a href="#">用户账单</a></li>
			<li style="float:right;">
				<form action="BillServlet" method="post">
  				<input type="text" name="username" placeholder="请输入查询的用户名">
  				<input type="submit" value="查询">
				</form>
			</li>
		</ul>
	</div>

	<div class="rightinfo">


		<table class="tablelist">
			<thead>
				<tr>
					<th>序号</th>
					<th>用户名</th>
					<th>名称</th>
					<th>类型</th>
					<th>金额</th>
					<th>添加时间 </th>
					<th>备注 </th>
					<th>支付方式 </th>
					
				</tr>
			</thead>
			<tbody>




				<%
					int houseNumber = 0;
											if (!list.isEmpty()) {
												/*  for(Map<String,Object> map:list){ */
												for (int i = 0; i < list.size(); i++) {
													houseNumber = i + 1;
													Map<String, Object> map = list.get(i);
				%>


				<tr>
					<td ><%=houseNumber%></td>
					<td><%=map.get("lookMoneyUserName")%></td>
					<td><%=map.get("lookMoneyTypeName")%></td>
					<td><% if (map.get("typeMessage").equals("1")){ %>收入<% } else if (map.get("typeMessage").equals("2")) { %> 支出  <% } %></td>
					<td><%=map.get("lookMoneyMoney")%></td>
					<td><%=map.get("lookMoneyTime")%></td>
					<td><%=map.get("tipMessage")%></td>
					<td><%=map.get("lookMoneyPayType")%></td>
				</tr>

				<%
					}
											}
				%>
			</tbody>
		</table>







	</div>

	<script type="text/javascript">
		$('.tablelist tbody tr:odd').addClass('odd');
	</script>
	


</table>
</body>
</html>
