
<!DOCTYPE HTML>
<%@ page language="java" pageEncoding="UTF-8"%>

<%@ include file="template/tag.jsp"%>
<html>
<%@ include file="template/htmlHead.jsp"%>
<body>
<div class="navbar navbar-inverse navbar-fixed-top">
	<div class="container">
		<a href="/web" class="navbar-brand"> Taurus and Chicken</a>

		<div class="collapse navbar-collapse navHeaderCollapse">
			<ul class="nav navbar-nav navbar-right">
				<li class="active">
				<li class="active"><a href="/web">首页</a></li>
				<li class="active"><a href="<c:url value = "/loginsignin"/>">登录／注册
								</a></li>
			</ul>
		</div>
	</div>
</div>
<h1>对不起，登录失败哦</h1>



</body>
</html>