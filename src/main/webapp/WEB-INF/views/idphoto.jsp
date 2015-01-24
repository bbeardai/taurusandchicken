
<!DOCTYPE HTML>
<%@ page language="java" pageEncoding="UTF-8"%>

<%@ include file="template/tag.jsp"%>
<html>
<%@ include file="template/htmlHead.jsp"%>
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
				<sec:authorize access="hasRole('ROLE_SHIPER')">
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
			<c:forEach items="${piclist }" var="pic" varStatus="stat" begin="0">
				<c:if test="${stat.count%4==0}">
		</div>
		<div class="row">
			</c:if>
			<div class="col-md-8">
	<div class="container">

				<h3>姓名：${pic.name }</h3><h3>    </h3><h3>身份证号码：${pic.idnumber }</h3><br>
				<img src="resources/upload/${pic.idphotoid}ZM${pic.type}"
					 width="340" height="220"/>
					<img src="resources/upload/${pic.idphotoid}BM${pic.type}"
					 width="340" height="220"/><br><br>
</div>

			</div>
			</c:forEach>
		</div>
	</div>


	<div class="navbar navbar-default navbar-fixed-bottom">
		<div class="container">
			<div class="panel panel-default">

				<div class="panel-body">

					<form action="<c:url value='/uploadid'/>" method="post"
						enctype="multipart/form-data">
						<p>
							<label for="filezm">身份证正面</label> <input id="filezm"
								name="filezm" type="file" class="form-control" />
						</p>
						<p>
							<label for="filebm">身份证背面</label> <input id="filebm"
								name="filebm" type="file" class="form-control" />
						</p>
						<p>
							<label for="name">姓名</label> <input id="name"
								name="name" type="text" class="form-control" />
						</p>
						<p>
							<label for="idnumber">身份证号码</label> <input id="idnumber"
								name="idnumber" type="text" class="form-control" />
						</p>
						 <input type="submit" value = "上传" class="btn btn-primary btn-lg pull-right" />
					</form>
				</div>
			</div>
		</div>
	</div>
	<script src="http://code.jquery.com/jquery-1.10.2.min.js"></script>
	<script src="resources/js/bootstrap.js"></script>
	<script type="text/javascript" src="resources/js/annotorious.min.js"></script>
</body>
</html>