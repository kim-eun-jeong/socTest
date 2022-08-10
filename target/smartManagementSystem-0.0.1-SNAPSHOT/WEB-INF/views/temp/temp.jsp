<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jstl/jstl.jsp"%>

<section class="content">  
	<div class="member_wrap_list W59 Fl" id="listDiv">
		<jsp:include page="./content/list.jsp" />
	</div>
	<div class="member_wrap_detail W39 Fr" id="detailDiv">
		<jsp:include page="./content/detail.jsp" />
	</div>
</section>

<script src="${pageContext.request.contextPath}/resources/js/custom/temp/temp.js"></script>