
<!DOCTYPE HTML>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="true"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
<meta name="viewpoint"
	content="width = device-width, initial-scale = 1.0">
	
<link rel="stylesheet"  type="text/css" href="resources/css/bootstrap.min.css"/>
<link rel="stylesheet"  type="text/css" href="resources/css/styles.css"/>
<meta charset="utf-8">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<title>首页</title>
<!--[if lt IE 9]><script src="js/html5.js" type="text/javascript"></script><![endif]-->
<link rel="stylesheet" type="text/css" href="resources/css/public.css"/>
<link rel="stylesheet" type="text/css" href="resources/css/Home page.css"/>
</head>
<body>

<!------------header start------------->
<header>
	<div class="header_top">
    	<div class="header_logo"><img  src="resources/images/logo.png" alt=""/></div>
        <div class="hreder_member">
        	<ul class="nav navbar-nav navbar-right">
				
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

				
			</ul></div>
    	<nav>
        	<a class="current" href="/" target="_blank">首页</a>
            <a href="/" target="_blank">服务中心</a>
            <a href="/" target="_blank">解决方案</a>
            <a href="/" target="_blank">核心价值</a>
            <a href="/" target="_blank">关于TM</a>
        </nav>
    </div>
</header>
<!------------header end------------->
<div class="clear"></div>
<!------------banner start------------->
<div class="banner" style="background:url(resources/images/banner.jpg) center no-repeat;"><a href="" target="_blank"></a></div>
<!------------banner end------------->
<div class="clear"></div>
<!------------section start------------->
<section>
    <div class="section_box1">
        <div class="banner_zindex">
       	  <div class="query">
            	<table width="370" border="0">
  					<tr>
    					<td><form name="form1" method="post" action="">
    					  <label for="textfield"></label>
    					  <input onBlur="if (value ==''){value='请输入包裹运单好跟踪状态'}" onFocus="if (value =='请输入包裹运单好跟踪状态'){value =''}" value="请输入包裹运单好跟踪状态" type="text" name="textfield" id="textfield">
  					  </form></td>
    					<td><a href="#" target="_blank"><img src="resources/images/buttun1.png" width="141" height="33"></a></td>
 					</tr>
				</table>
          </div>
            <div class="square">
            	<ul>
                	<li><a href="<c:url value = "/zhiyoucheck"/>" target="_blank" name = "shenfenzheng"><img src="resources/images/buttun2.png" width="100" height="101"></a></li>
                    <li><a href="#" target="_blank"><img src="resources/images/buttun3.png" width="101" height="101"></a></li>
                    <li><a href="#" target="_blank"><img src="resources/images/buttun4.png" width="101" height="101"></a></li>
                </ul>
            </div>
            <div class="information">
            	<h1>TM公告</h1>
                <ul>
                	<li><a href="#" target="_blank">的时间啊胡椒粉开始的副科级了的改扩建啦</a></li>
                    <li><a href="#" target="_blank">奥迪康师傅绿色短款阿克顿叫顾客阿拉克海景房的诶平了埃及恐惧感诶</a></li>
                    <li><a href="#" target="_blank">奥迪康师傅绿色短款阿克顿叫顾客阿拉克海景房的恐惧感诶诶平了埃及</a></li>
                    <li><a href="#" target="_blank">的时间啊胡椒粉开始的副科级了的改扩建啦</a></li>
                </ul>
            </div>
      </div>
        <div class="section_con">
        	<div class="biaoti">跨境电商供应链及国际物流解决方案专家</div>
        	<div>
            	<div class="libiao_lf">
                	<h1>国际配送</h1>
                    <ul>
                    	<li><a href="#" target="_blank">稳定的国际配送服务</a>.</li>
                        <li><a href="#" target="_blank">本地化客户服务团队</a></li>
                    </ul>
                </div>
                <div class="libiao_lf">
                	<h1>海外仓储</h1>
                    <ul>
                    	<li><a href="#" target="_blank">遍布全球的服务网点</a></li>
                        <li><a href="#" target="_blank">专业的电商仓储服务</a></li>
                        <li><a href="#" target="_blank">高效的配货包装服务</a></li>
                    </ul>
                </div>
            </div> 
      </div>
      <div class="section_con">
        	<div>
            	<div class="libiao_lf">
                	<h1>国际配送</h1>
                    <ul>
                    	<li><a href="#" target="_blank">稳定的国际配送服务</a></li>
                        <li><a href="#" target="_blank">本地化客户服务团队</a></li>
                    </ul>
                </div>
                <div class="libiao_lf">
                	<h1>海外仓储</h1>
                    <ul>
                    	<li><a href="#" target="_blank">遍布全球的服务网点</a></li>
                        <li><a href="#" target="_blank">专业的电商仓储服务</a></li>
                        <li><a href="#" target="_blank">高效的配货包装服务</a></li>
                    </ul>
                </div>
            </div> 
      </div>
    </div>
  <div class="jiameng">
   	<h1>合作伙伴</h1>
      <ul>
       	  <li><a href="http://shop66795712.taobao.com/?spm=a230r.7195193.1997079397.22.EFagyE" target="_blank"><img src="resources/images/jm_198.jpg" width="150" height="61"></a></li>
          <li><a href="http://shop71318939.taobao.com/?spm=a230r.7195193.1997079397.2.kW7n2o" target="_blank"><img src="resources/images/jm_200.jpg" width="150" height="61"></a></li>
          <li><a href="#" target="_blank"><img src="resources/images/jm_202.jpg" width="149" height="61"></a></li>
          <li><a href="#" target="_blank"><img src="resources/images/jm_204.jpg" width="148" height="61"></a></li>
          <li><a href="#" target="_blank"><img src="resources/images/jm_206.jpg" width="149" height="61"></a></li>
          <li><img src="resources/images/jm_208.jpg" width="150" height="61"></li> 
      </ul>
        <ul>
       	  <li><a href="#" target="_blank"><img src="resources/images/jm_228.jpg" width="150" height="58"></a></li>
          <li><a href="#" target="_blank"><img src="resources/images/jm_230.jpg" width="150" height="58"></a></li>
          <li><a href="#" target="_blank"><img src="resources/images/jm_232.jpg" width="149" height="58"></a></li>
          <li><a href="#" target="_blank"><img src="resources/images/jm_234.jpg" width="148" height="58"></a></li>
          <li><a href="#" target="_blank"><img src="resources/images/jm_236.jpg" width="149" height="58"></a></li>
          <li><a href="#" target="_blank"><img src="resources/images/jm_238.jpg" width="150" height="58"></a></li>
      </ul>
    </div>
   <!------------页脚-------------> 
   <div class="foot">
   		<ul>
   			<li><a href="#" target="_blanck">帮助及问答</a></li>
   			<li><a href="#" target="_blanck">资源下载</a></li>
   			<li><a href="#" target="_blanck">加盟合作</a></li>
   			<li><a href="#" target="_blanck">服务协议</a></li>
   		</ul>
   </div>
</section>
<!------------section end------------->
<div class="clear"></div>


<script src="http://code.jquery.com/jquery-1.10.2.min.js"></script>
	<script src="resources/js/bootstrap.js"></script>
	<script type="text/javascript" src="resources/js/annotorious.min.js"></script>
</body>
</html>