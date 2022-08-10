<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jstl/jstl.jsp"%>
	
<div class="row_box_header">
	<span class="row_box_header_chart_title">전력구 온도 현황</span>
	<div class="row_box_header_time_title">
		<span id="trmp_updateTime"></span>
		<select class="Ml10" id="chartSearchType" onchange="fncTempChartSetting()">
			<option value="">전체</option>
			<c:forEach var="data" items="${lineSearchList}" varStatus="status">
            	<option value="<c:out value="${data.codeId}" />" <c:out value="${commonVO.eventLine eq data.codeId ? 'selected=selected':'' }" />>
            		<c:out value="${data.codeName}" />
            	</option>
	        </c:forEach>
		</select>
	</div>          	
</div>
<div class="row_box_body2">
	<div id="tempContainer"></div>
</div>