<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jstl/jstl.jsp"%>

<div class="dashboardImg" >
	<div class="alarmBtn" onClick="fncDeleteAllEvent();">알림 삭제</div>
	<div class="temp_popup">
		<jsp:include page="./dashboard/temp_popup.jsp" />
	</div>
	
	<div class="event_popup dts_popup DiNone">
		<jsp:include page="./dashboard/dts_popup.jsp" />
	</div>
	<div class="event_popup dts_popup_focus DiNone">
	</div>
	<div class="event_popup zone_popup DiNone">
		<jsp:include page="./dashboard/zone_popup.jsp" />
	</div>
	<div class="event_popup zone_popup_focus DiNone">
	</div>
	<div class="event_popup das_popup DiNone">
		<jsp:include page="./dashboard/das_popup.jsp" />
	</div>
	<div class="event_popup das_popup_focus DiNone">
	</div>
	
	<div class="equipment">
		<c:if test="${equipmentList.size() > 0}">
			<jsp:include page="./dashboard/equipment_state.jsp" />
		</c:if>
	</div>
	
	<div class="dts_event">
		<c:if test="${dtsEventList.size() > 0}">
			<jsp:include page="./dashboard/dts_event.jsp" />
		</c:if>
	</div>
	<div class="das_event">
		<c:if test="${dasEventList.size() > 0}">
			<jsp:include page="./dashboard/das_event.jsp" />
		</c:if>
	</div>
	<div class="zone">
		<c:if test="${zoneList.size() > 0}">
			<jsp:include page="./dashboard/zone.jsp" />
		
		</c:if>
	</div>
</div>

<script src='<c:out value="${pageContext.request.contextPath}" />/resources/js/custom/dashboard/dashboard.js'></script>
