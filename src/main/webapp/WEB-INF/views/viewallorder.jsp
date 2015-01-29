
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

	<c:forEach items="${orderlist }" var="order">
		<div class="container">
			<div class="row">
				<div class="col-md-2"></div>
				<div class="col-md-8">
					<table class="table table-striped">
						<tbody>
						<thead>
							<tr>
								<th colspan=2>订单号：${order.shiporderid }</th>


								<c:choose>
									<c:when test="${order.status==1 }">
										<th>状态： 由Excel上传，未审完善 <a
											href="<c:url value = "/editzhiyouorder?shiporderid=${order.shiporderid }"/>">完善
										</a>
										</th>
									</c:when>
									<c:when test="${order.status==2 }">
										<th>状态：已完善，等待上传身份证 <a
											href="<c:url value = "/editzhiyouorder?shiporderid=${order.shiporderid }"/>">编辑
										</a>
										<c:if test="${!order.iduploaded }">
										<a
											href="<c:url value = "/uploadidremander?shiporderid=${order.shiporderid }"/>">提醒买家上传身份证
										</a>
										</c:if>
										</th>
									</c:when>
									<c:when test="${order.status==3 }">
										<th>状态：身份证已上传，待验证 <a
											href="<c:url value = "/checked?shiporderid=${order.shiporderid }"/>">验证
										</a> <a
											href="<c:url value = "/denied?shiporderid=${order.shiporderid }"/>">拒绝
										</a>
										</th>
									</c:when>
									<c:when test="${order.status== 4 }">

										<th>状态：身份证信息已验收，等待发货
										 <sec:authorize access="hasRole('ROLE_SHIPER')">
												<a href="<c:url value = "/shiped?shiporderid=${order.shiporderid }"/>">已发货
												</a>
											</sec:authorize>
										</th>
									</c:when>
									<c:when test="${order.status== 5 }">

										<th>状态：已发货</th>
									</c:when>

								</c:choose>



							</tr>
						</thead>
						<tr>
							<th colspan=3>详细地址：${order.address.line1 }</th>
						</tr>

						<tr>
							<td>省：${order.address.province }</td>
							<td>城市：${order.address.city }</td>
							<td>邮编：${order.address.zip }</td>
						</tr>
						<tr>
							<td>收件人：${order.address.idphoto.name }</td>
							<td>电话：${order.address.phone }</td>
							<td>店铺名称：${order.shopname }</td>
						</tr>
						<tr>
							<td>淘宝ID：${order.taobaoid }</td>
							<td>付款日期：${order.paydate }</td>
							<td></td>
						</tr>
						<tr>
							<th colspan=3>备注：${order.address.memo }</th>
						</tr>
						<tr>
							<th colspan=3>身份证号码：${order.address.idphoto.idnumber }</th>
						</tr>
						<tr>
							<th colspan=3>订单明细</th>
						</tr>
						<tr>
							<td>商品ID</td>
							<td>数量</td>
							<td>尺寸</td>
						</tr>
						<c:forEach items="${order.orderitems }" var="item">
							<tr>
								<td>${item.productid }</td>
								<td>${item.quantity }</td>
								<td>${item.size }</td>
							</tr>



						</c:forEach>
						<c:if test="${order.status>2 }">
							<tr>
								<th colspan=3><img
									src="resources/upload/${order.address.idphoto.idphotoid}ZM${order.address.idphoto.type}"
									width="340" height="220" /> <img
									src="resources/upload/${order.address.idphoto.idphotoid}BM${order.address.idphoto.type}"
									width="340" height="220" /></th>
							</tr>
						</c:if>


						</tbody>
					</table>
				</div>
				<div class="col-md-2"></div>
			</div>
		</div>
	</c:forEach>




	<script src="http://code.jquery.com/jquery-1.10.2.min.js"></script>
	<script src="resources/js/bootstrap.js"></script>
	<script type="text/javascript" src="resources/js/annotorious.min.js"></script>

</body>
</html>