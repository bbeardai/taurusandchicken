
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
						data-toggle="dropdown">客服 <b class="caret"></b></a>
						<ul class="dropdown-menu">
							<li><a href="<c:url value = "/viewallorder"/>">订单管理</a></li>
							<li><a href="<c:url value = "/newzhiyouorder"/>">添加直邮订单</a></li>
<sec:authorize access="hasRole('ROLE_CSADMIN')">
							<li><a href="<c:url value = "/viewallorderadmin"/>">查看所有订单</a></li>
</sec:authorize>
						</ul></li>
				</sec:authorize>
				<sec:authorize access="hasRole('ROLE_SHIPER')">
					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown">发货人 <b class="caret"></b></a>
						<ul class="dropdown-menu">
							<li><a href="<c:url value = "/viewshipingorder"/>">查看发货订单</a></li>
							
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
			

			<div class="col-md-8">
			<h3>${message }</h3>
				<div class="panel panel-default">
					<div class="panel-heading">
						<h3 class="panel-title">编辑直邮订单</h3>
					</div>
					<div class="panel-body">
						<form action=" <c:url value='/savezhiyouorder' />" method="get" class="form-horizontal">
							<div class="form-group">
								<label for="shiporderid" class="col-sm-2 control-label">订单号：</label> 
								<input  type="hidden" value="${order.shiporderid }" name = "shiporderid" />
								
								<div class="col-sm-10">${order.shiporderid }</div>
							</div>
							<div class="form-group">
								<label for="taobaoid" class="col-sm-2 control-label">淘宝用户名：</label> 
								<div class="col-sm-10">
								<input id="taobaoid" value = "${order.taobaoid }" name="taobaoid" type="text" class="form-control" />
								</div>
							</div>
							<div class="form-group">
								<label for="paydate" class="col-sm-2 control-label">付款日期：</label> 
								<div class="col-sm-10">
								<input id="paydate" value = "${order.paydate }" name="paydate" type="text" class="form-control" />
								</div>
							</div>
							<div class="form-group">
								<label for="shopname" class="col-sm-2 control-label">店铺名称：</label> 
								<div class="col-sm-10">
								<input id="shopname" value = "${order.shopname }" name="shopname" type="text" class="form-control" />
								</div>
							</div>
							<div class="form-group">
								<label for="province" class="col-sm-2 control-label">省：</label> 
								<div class="col-sm-4">
								<input id="province" value = "${order.address.province }"  name="province" type="text" class="form-control" />
								</div>
								<label for="city" class="col-sm-2 control-label">城市：</label> 
								<div class="col-sm-4">
								<input id="city" value = "${order.address.city }" name="city" type="text" class="form-control" />
								</div>
							</div>
							<div class="form-group">
								<label for="line1" class="col-sm-2 control-label">详细地址：</label> 
								<div class="col-sm-6">
								<input id="line1"  value = "${order.address.line1 }" name="line1" type="text" class="form-control" />
								</div>
								<label for="zip" class="col-sm-2 control-label">邮编：</label> 
								<div class="col-sm-2">
								<input id="zip" value = "${order.address.zip }" name="zip" type="text" class="form-control" />
								</div>
							</div>
							<div class="form-group">
								<label for="name" class="col-sm-2 control-label">收件人姓名：</label> 
								<div class="col-sm-3">
								<input id="name"  value = "${order.address.idphoto.name }" name="name" type="text" class="form-control" />
								</div>
								<label for="phone" class="col-sm-2 control-label">电话：</label> 
								<div class="col-sm-5">
								<input id="phone"  value = "${order.address.phone }" name="phone" type="tel" class="form-control" />
								</div>
							</div>
							<div class="form-group">
								<label for="memo" class="col-sm-2 control-label">备注</label> 
								<div class="col-sm-10">
								<input id="memo"  value = "${order.address.memo }" name="memo" type="text" class="form-control" />
								</div>
							</div>
							
						<table class="table table-striped">
						<thead>	
							<tr>
							<td>商品ID</td>
							<td>数量</td>
							<td>尺寸</td>
							<td>操作</td>
						</tr>
						</thead>
						<tbody>
						<c:forEach items="${order.orderitems }" var="item">
							<tr>
								<td>${item.productid }</td>
								<td>${item.quantity }</td>
								<td>${item.size }</td>
								
								<td><a href="<c:url value = "/deleteorderitem?orderitemid=${item.orderitemid }&shiporderid=${order.shiporderid }"/>">删除</a></td>
							</tr>



						</c:forEach>
							</tbody>
							</table>
							
							
						<input type="submit" value="保存" class="btn btn-danger" />
						</form>
					</div>
				</div>

			</div>
			<div class="col-md-1"></div>
			<div class="col-md-3">
				<form action=" <c:url value='/additem2' />" method="get" >
							<p>
								<label for="productid">商品ID</label> <input id="productid"
									name="productid" type="text" class="form-control" />
							</p>
							<p>
								<label for="quantity">数量</label> <input id="quantity"
									name="quantity" type="number" class="form-control" />
							</p>
							<p>
								<label for="size">尺寸</label> 
								<select name = "size">
									  <option value="成人S">成人S</option>
									  <option value="成人M">成人M</option>
									  <option value="成人L">成人L</option>
									  <option value="成人XL">成人XL</option>
									  <option value="成人2XL">成人2XL</option>
									  <option value="成人3XL">成人3XL</option>
									  <option value="成人4XL">成人4XL</option>
									  <option value="成人5XL">成人5XL</option>
									  <option value="女款S">女款S</option>
									  <option value="女款M">女款M</option>
									  <option value="女款L">女款L</option>
									  <option value="女款XL">女款XL</option>
									  
								</select>
							</p>
							<input  type="hidden" value="${order.shiporderid }" name = "shiporderid" />
							<input type="submit" value="添加" class="btn btn-danger" />
						</form>
				</div>
		</div>
	</div>
	


	<script src="http://code.jquery.com/jquery-1.10.2.min.js"></script>
	<script src="resources/js/bootstrap.js"></script>
	<script type="text/javascript" src="resources/js/annotorious.min.js"></script>
	<script type="text/javascript" src="resources/js/address.js"></script>
</body>
</html>