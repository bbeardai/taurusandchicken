
<!DOCTYPE HTML>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="true"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<html>

<head>
<meta name="viewpoint"
	content="width = device-width, initial-scale = 1.0">

<%@ page contentType="text/html;charset=UTF-8"%>
<link rel="stylesheet" type="text/css"
	href="resources/css/bootstrap.min.css" />
<link rel="stylesheet" type="text/css" href="resources/css/styles.css" />
<link type="text/css" rel="stylesheet"
	href="resources/css/annotorious.css" />
<title>Taurus and Chicken</title>
</head>
<body>
	<div class="navbar navbar-inverse navbar-fixed-top">
		<div class="container">
			<a href="http://localhost:8080/web/" class="navbar-brand"> Taurus
				and Chicken</a>

			<div class="collapse navbar-collapse navHeaderCollapse">
				<ul class="nav navbar-nav navbar-right">
					<li class="active">
					<li class="active"><a href="http://localhost:8080/web/">首页</a></li>
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
									<li><a href="allpicture">我的地址簿</a></li>
									<li><a href="userquestion">我的身份证</a></li>
									<li class="divider"></li>
									<li><a href="changepass">我的订单</a></li>
									<li class="divider"></li>
									<li><a href="<c:url value="j_spring_security_logout"/>">登出</a></li>
								</ul></li>
						</c:when>

					</c:choose>
					<sec:authorize access="hasRole('ROLE_SHIPER')">
						<li class="dropdown"><a href="#" class="dropdown-toggle"
							data-toggle="dropdown">发货人 <b class="caret"></b></a>
							<ul class="dropdown-menu">
								<li><a href="<c:url value = "/usermanage"/>">订单管理</a></li>

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

	<script src="http://code.jquery.com/jquery-1.10.2.min.js"></script>
	<script src="resources/js/bootstrap.js"></script>
	<script type="text/javascript" src="resources/js/annotorious.min.js"></script>

</body>
</html>