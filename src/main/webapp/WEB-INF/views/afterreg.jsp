
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
				<c:choose>
					<c:when test="${username== 'anonymousUser' }">
						<li class="active"><a href="<c:url value = "/loginsignin"/>">登录／注册
								</a></li>
					</c:when>
					<c:when test="${username== '' }">
						<li class="active"><a href="<c:url value = "/loginsignin"/>">登录／注册
								</a></li>
					</c:when>
					<c:when test="${username != 'anonymousUser' }">
						<li class="dropdown"><a href="#" class="dropdown-toggle"
							data-toggle="dropdown">您好,${username }<b class="caret"></b></a>
							<ul class="dropdown-menu">
								<li><a href="viewaddress">我的地址簿</a></li>
								<li><a href="idphoto">我的身份证</a></li>
								<li class="divider"></li>
								<li><a href="vieworder">我的订单</a></li>
								<li class="divider"></li>
								<li><a href="<c:url value="j_spring_security_logout"/>">登出</a></li>
							</ul></li>
					</c:when>

				</c:choose>
				<sec:authorize access="hasRole('ROLE_SHIPER')">
					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown">发货人 <b class="caret"></b></a>
						<ul class="dropdown-menu">
							<li><a href="<c:url value = "/viewallorder"/>">订单管理</a></li>

						</ul></li>
				</sec:authorize>
				<sec:authorize access="hasRole('ROLE_ADMIN')">
					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown">管理员<b class="caret"></b></a>
						<ul class="dropdown-menu">
							<li><a href="<c:url value = "/editorpicture"/>">Picture</a></li>
							<li><a href="<c:url value = "/editorquestion"/>">Question</a></li>
							<li><a href="#">Answer</a></li>
						</ul></li>
				</sec:authorize>

				
			</ul>
		</div>
	</div>
</div>
	
<h1>等待验证</h1>


</body>
</html>