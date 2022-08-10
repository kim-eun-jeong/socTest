<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jstl/jstl.jsp"%>
	<c:set var="heightStart" value="${597}"/>
	<c:set var="height" value="${521/1235}"/><!--518 > 504  -->
	<ul>
		<c:forEach var="data" items="${zoneList}" varStatus="status">
			<c:set var="zone_top"  value="${heightStart-(height*data.zoneStart)}" />
			<c:set var="zone_height"  value="${height *(data.zoneEnd-data.zoneStart+1)}" />
			<c:choose>
				<c:when test="${data.zoneType eq 'ZONE_01'}">
					<li class="place <c:out value="${commonVO.dashboard_zoneId eq data.zoneId ? 'selected':''}" /> <c:out value="${data.zoneLine}" /> " style="top:<c:out value="${zone_top}" />px; height:<c:out value="${zone_height}" />px; margin: -<c:out value="${zone_height}" />px 0px; " onClick="zone_popup('<c:out value="${data.zoneId}" />', '<c:out value="${data.zoneLine eq '' or data.zoneLine eq null  ? 'LINE_01' : data.zoneLine}" />', <c:out value="${status.index}" />)"></li>
				</c:when>
				<c:when test="${data.zoneType eq 'ZONE_02'}">
					<li class="door <c:out value="${commonVO.dashboard_zoneId eq data.zoneId ? 'selected':''}" />  <c:out value="${data.zoneLine}" />" style="top:<c:out value="${zone_top}" />px; height:<c:out value="${zone_height}" />px; margin: -<c:out value="${zone_height}" />px 0px;" onClick="zone_popup('<c:out value="${data.zoneId}" />', '<c:out value="${data.zoneLine eq ''  or data.zoneLine eq null  ? 'LINE_01' : data.zoneLine}" />', <c:out value="${status.index}" />)"></li>
				</c:when>
			</c:choose>
        </c:forEach>
	</ul>