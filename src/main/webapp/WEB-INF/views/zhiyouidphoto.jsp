
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
					<li class="active"><a href="<c:url value = "/loginsignin"/>">登录／注册
					</a></li>
				</ul>
			</div>
		</div>
	</div>
	<div class="container">
		<div class="row">
			<div class="col-md-2"></div>
			<div class="col-md-3">
				<div class="panel panel-default">
					<div class="panel-heading">
						<h3 class="panel-title">请上传身份证照片</h3>
					</div>
					<div class="panel-body">
					
						<form action="<c:url value='/zhiyouuploadid'/>" method="post"
						enctype="multipart/form-data">
						<p>
							<label for="filezm">身份证正面</label> <input id="filezm"
								name="filezm" type="file" class="form-control" />
						</p>
						<p>
							<label for="filebm">身份证背面</label> <input id="filebm"
								name="filebm" type="file" class="form-control" />
						</p>
						<input type="hidden"  name="shiporderid" value = "${shiporderid }"/>
						<input type="hidden"  name="idnumber" value = "${idnumber }"/>
						
						
						 <input type="submit" value = "上传" class="btn btn-primary btn-lg pull-right" />
					</form>
					</div>
				</div>

			</div>
			<div class="col-md-2"></div>
			<div class="col-md-3">
				
			</div>
			<div class="col-md-2"></div>
		</div>
	</div>


	<script src="//code.jquery.com/jquery-1.11.0.min.js"></script>
	<script src="//code.jquery.com/jquery-migrate-1.2.1.min.js"></script>
	<script src="resources/js/jquery.validate.min.js"></script>
	<script src="//netdna.bootstrapcdn.com/bootstrap/3.1.1/js/bootstrap.min.js"></script>
	<script src="resources/js/login.js"></script>
</body>
</html>