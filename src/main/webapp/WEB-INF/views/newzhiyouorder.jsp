
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
		<a href="/" class="navbar-brand"> Taurus and Chicken</a>

		<div class="collapse navbar-collapse navHeaderCollapse">
			<ul class="nav navbar-nav navbar-right">
				<li class="active">
				<li class="active"><a href="/">首页</a></li>
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
							data-toggle="dropdown">您好,${nickname }<b class="caret"></b></a>
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
				<sec:authorize access="hasRole('ROLE_CS')">
					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown">发货人 <b class="caret"></b></a>
						<ul class="dropdown-menu">
							<li><a href="<c:url value = "/viewallorder"/>">订单管理</a></li>
							<li><a href="<c:url value = "/newzhiyouorder"/>">添加直邮订单</a></li>

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
			<h3>${message }</h3>
				<div class="panel panel-default">
					<div class="panel-heading">
						<h3 class="panel-title">编辑地址</h3>
					</div>
					<div class="panel-body">
						<form action=" <c:url value='/addzhiyouorder' />" method="get" class="form-horizontal">
							<div class="form-group">
								<label for="shiporderid" class="col-sm-2 control-label">订单号：</label> 
								<div class="col-sm-10">
								<input id="shiporderid" name="shiporderid" type="text" class="form-control" />
								</div>
							</div>
							<div class="form-group">
								<label for="taobaoid" class="col-sm-2 control-label">淘宝用户名：</label> 
								<div class="col-sm-10">
								<input id="taobaoid" name="taobaoid" type="text" class="form-control" />
								</div>
							</div>
							<div class="form-group">
								<label for="paydate" class="col-sm-2 control-label">付款日期：</label> 
								<div class="col-sm-10">
								<input id="paydate" name="paydate" type="date" class="form-control" />
								</div>
							</div>
							<div class="form-group">
								<label for="shopname" class="col-sm-2 control-label">店铺名称：</label> 
								<div class="col-sm-10">
								<input id="shopname" name="shopname" type="text" class="form-control" />
								</div>
							</div>
							<div class="form-group">
								<label for="province" class="col-sm-2 control-label">省：</label> 
								<div class="col-sm-4">
								<input id="province" name="province" type="text" class="form-control" />
								</div>
								<label for="city" class="col-sm-2 control-label">城市：</label> 
								<div class="col-sm-4">
								<input id="city" name="city" type="text" class="form-control" />
								</div>
							</div>
							<div class="form-group">
								<label for="line1" class="col-sm-2 control-label">详细地址：</label> 
								<div class="col-sm-10">
								<input id="line1" name="line1" type="text" class="form-control" />
								</div>
							</div>
							<div class="form-group">
								<label for="name" class="col-sm-2 control-label">收件人姓名：</label> 
								<div class="col-sm-3">
								<input id="name" name="name" type="text" class="form-control" />
								</div>
								<label for="phone" class="col-sm-2 control-label">电话：</label> 
								<div class="col-sm-5">
								<input id="phone" name="phone" type="tel" class="form-control" />
								</div>
							</div>
							<div class="form-group">
								<label for="memo" class="col-sm-2 control-label">备注</label> 
								<div class="col-sm-10">
								<input id="memo" name="memo" type="text" class="form-control" />
								</div>
							</div>
							
							
							
							
						<input type="submit" value="保存" class="btn btn-danger" />
						</form>
					</div>
				</div>

			</div>
			<div class="col-md-2"></div>
		</div>
	</div>
	<div class="navbar navbar-default navbar-fixed-bottom">
		<div class="container">
			<div class="panel panel-default">

				<div class="panel-body">

					<form action="<c:url value='/uploadexcel'/>" method="post"
						enctype="multipart/form-data" class="form-horizontal">
						<div class="form-group">
						
							<label for="excel" class="col-sm-2">上传Excel文件倒入订单</label>
							<div class="col-sm-4">
							 <input id="excel" name="excel" type="file" class="form-control" />
						</div>
							<div class="col-sm-5">
							 <label for="shopname">店铺名称</label> 
								<select name = "shopname">
										<option value="">请选择。。。</option>
									  <option value="板沙的小米">板沙的小米</option>
									  <option value="themountain">themountain</option>
									  <option value="东海岸代购">东海岸代购</option>
									  <option value="Themountain中国">Themountain中国</option>
									  <option value="伊可原创">伊可原创</option>
									  <option value="洛奇0">洛奇0</option>
									  <option value="芝兰宝贝">芝兰宝贝</option>
									  <option value="美国themountain代购店">美国themountain代购店</option>
									  <option value="美国themountain">美国themountain</option>
									  <option value="板沙的敏敏">板沙的敏敏</option>
									  <option value="米国的梦想">米国的梦想</option>
									  <option value="史密斯杨美国正品">史密斯杨美国正品</option>
									  <option value="恐怖的亮">恐怖的亮</option>
									  <option value="懒惰的刺客">懒惰的刺客</option>
									  <option value="gilt_coco">gilt_coco</option>
									  <option value="eplion">eplion</option>
									  <option value="fsi048865">fsi048865</option>
									  <option value="XXXXXL">XXXXXL</option>
									  <option value="村长也很潮">村长也很潮</option>
									  <option value="寂寞孩子气">寂寞孩子气</option>
									  <option value="美国山牌">美国山牌</option>
									  <option value="dsautox">dsautox</option>
									  
									  
								</select>
						</div>
						
						 <input type="submit" value = "上传" class="btn btn-primary btn-lg pull-right" />
						 </div>
					</form>
				</div>
			</div>
		</div>
	</div>


	<script src="http://code.jquery.com/jquery-1.10.2.min.js"></script>
	<script src="resources/js/bootstrap.js"></script>
	<script type="text/javascript" src="resources/js/annotorious.min.js"></script>
	<script type="text/javascript" src="resources/js/address.js"></script>
</body>
</html>