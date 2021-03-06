<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="basePath" value="${pageContext.request.contextPath}" />

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="UTF-8">
<title>采购事务模块</title>

<script src="${basePath}/jquery/jquery-3.2.1.min.js"></script>

<!-- JavaScript 点击链接切换division  -->
<script type="text/javascript"
	src="${basePath}/jquery/OwnJavaScript/ClickSwitchDivision.js"></script>

<!-- bootstrap JS -->
<script src="${basePath}/jquery/bootstrap.js"></script>
<script src="${basePath}/jquery/bootstrap.min.js"></script>

<!-- bootstrap CSS -->
<link rel="stylesheet" type="text/css"
	href="${basePath}/CssFrame/bootstrap.css">
<link rel="stylesheet" type="text/css"
	href="${basePath}/CssFrame/bootstrap-theme.css">

<!-- address tag css -->
<link rel="stylesheet" type="text/css"
	href="${basePath}/PatternStyle/Accesses.css">

</head>
<body>
	<div class="accesses">
		<ul>
			<li><a href="#" class="a_links" onclick="opens(1)">申请采购货品</a></li>

			<li><a href="javascript:opens(2);" class="a_links">查看申请单(仅限本人)</a></li>

			<li><a href="javascript:opens(3);" class="a_links">按类型查询</a></li>

			<li><a
				href="/stocker-manager/PurchaseController/jumpToPurchaseTranceLogHandler"
				class="a_links">采买活动记录</a></li>

			<li><a href="/stocker-manager/cross/toTransfer" class="a_links">返回导航页</a></li>

			<li><a href="/stocker-manager/login.jsp" class="a_links"> <b>
						返回首页 </b></a></li>

		</ul>
	</div>

	<div class="detail_zone div_module" style="display: none;" id="room1">
		<jsp:include page="AddApp.jsp"></jsp:include>
	</div>

	<div class="detail_zone div_module" style="display: none;" id="room2">
		<jsp:include page="ExhibitionPrivatePurchase.jsp"></jsp:include>
	</div>

	<div class="detail_zone div_module" style="display: none;" id="room3">
		<jsp:include page="SearchPurchasesByCondition.jsp"></jsp:include>
	</div>

</body>
</html>