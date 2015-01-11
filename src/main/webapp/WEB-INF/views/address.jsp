
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
<body onload="Init();">
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
	<div class="container">
		<div class="row">
			<div class="col-md-2"></div>

			<div class="col-md-8">
				<div class="panel panel-default">
					<div class="panel-heading">
						<h3 class="panel-title">编辑地址</h3>
					</div>
					<div class="panel-body">
						<form action=" <c:url value='/addressupdate' />" method="get">
							<p>
								<label for="line1">第一行</label> <input id="line1" name="line1"
									type="text" class="form-control" />
							</p>
							
							<p>
								<label for="city">城市</label> <input id="city" name="city"
									type="text" class="form-control" />
							</p>
							<p>
								<label for="province">省</label> <input id="province"
									name="province" type="text" class="form-control" />
							</p>
							<p>
								<label for="zip">邮编</label> <input id="zip" name="zip"
									type="text" class="form-control" />
							</p>
							<p>
								<label for="phone">电话</label> <input id="phone" name="phone"
									type="text" class="form-control" />
							</p>
							<p>
								<label for="memo">备注</label> <input id="memo" name="memo"
									type="text" class="form-control" />
							</p>
							<p>
								<label for="idphotoid">选择收件人</label>
								 <select id = "idphotoid" name = "idphotoid" class="form-control">
									<c:forEach items="${piclist }" var="pic" >
										<option value = "${pic.idphotoid }">${pic.name }</option>
									</c:forEach>
							</select> 
						如果收件人没有在列表中，请先上传身份证照片
						</p>
						<input type="submit" value="保存" class="btn btn-danger" />
						</form>
					</div>
				</div>

			</div>
			<div class="col-md-2"></div>
		</div>
	</div>

<select id="province" name="province">
            <option value="">请选择....</option>
            <option value="吉林省">吉林省</option>
            <option value="辽宁省">辽宁省</option>
            <option value="山东省">山东省</option>
        </select>
        <select id="city" name="city">
            <option value="">请选择.....</option>
        </select>


	<script>
    	
    
    </script>
	<script src="http://code.jquery.com/jquery-1.10.2.min.js"></script>
	<script src="resources/js/bootstrap.js"></script>
	<script type="text/javascript" src="resources/js/annotorious.min.js"></script>
	<script type="text/javascript" src="resources/js/address.js"></script>
</body>
</html>