<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jstl/jstl.jsp"%>
	<c:set var="heightStart" value="${597}"/>
	<c:set var="height" value="${521/1235}"/><!--518 > 504  -->
	<input type="hidden" id="dtsLastTime" value="${commonVO.dtsLastTime}" />
	<ul>
		<c:forEach var="data" items="${dtsEventList}" varStatus="status">
			<c:set var="dts_top"  value="${heightStart-(height*data.dtsEventLocation)}" />
			<c:choose>
				<c:when test="${data.dtsEventLine eq 'LINE_01'}">
					<li class="dts_line01 <c:out value="${commonVO.dashboard_dtsEventId eq data.dtsEventId ? 'selected':''}" />" style="top:<c:out value="${dts_top}" />px;"  onClick="dts_popup('<c:out value="${data.dtsEventId}"/>', <c:out value="${status.index}" />)"></li>
				</c:when>
				<c:when test="${data.dtsEventLine eq 'LINE_02'}">
					<li class="dts_line02 <c:out value="${commonVO.dashboard_dtsEventId eq data.dtsEventId ? 'selected':''}" />" style="top:<c:out value="${dts_top}" />px;"  onClick="dts_popup('<c:out value="${data.dtsEventId}"/>', <c:out value="${status.index}" />)"></li>
				</c:when>
				<c:when test="${data.dtsEventLine eq 'LINE_03'}">
					<li class="dts_line03 <c:out value="${commonVO.dashboard_dtsEventId eq data.dtsEventId ? 'selected':''}" />" style="top:<c:out value="${dts_top}" />px;"  onClick="dts_popup('<c:out value="${data.dtsEventId}"/>', <c:out value="${status.index}" />)"></li>
				</c:when>
			</c:choose>
        </c:forEach>
	</ul>