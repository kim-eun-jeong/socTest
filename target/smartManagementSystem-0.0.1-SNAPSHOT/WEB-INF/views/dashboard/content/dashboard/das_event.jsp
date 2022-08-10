<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jstl/jstl.jsp"%>
	<c:set var="heightStart" value="${597}"/>
	<c:set var="height" value="${521/1235}"/><!--518 > 504  -->
	<c:set var="leftStart" value="${1086}"/>
	<c:set var="left" value="${255/565}"/>
	<input type="hidden" id="dasLastTime" value="${commonVO.dasLastTime}" />
	<ul>
		<c:forEach var="data" items="${dasEventList}" varStatus="status">
			<c:set var="das_top"  value="${heightStart-(height*data.dasEventLocation)}" />
			<c:set var="das_left"  value="${leftStart-(left*data.dasEventLocation)}" />
			
			<c:choose>
				<c:when test="${data.dasEventTunnel eq 'TUNNEL_01'}">
					<li class="das_tunnel01 <c:out value="${commonVO.dashboard_dasEventId eq data.dasEventId ? 'selected':''}" /> <c:out value="${commonVO.dashboard_order ? 'new':''}" /> " style="top:<c:out value="${das_top}" />px;"  onClick="das_popup('<c:out value="${data.dasEventId}"/>', <c:out value="${status.index}" />)"></li>
				</c:when>
				<c:when test="${data.dasEventTunnel eq 'TUNNEL_02'}">
					<li class="das_tunnel02 <c:out value="${commonVO.dashboard_dasEventId eq data.dasEventId ? 'selected':''}" /> <c:out value="${commonVO.dashboard_order ? 'new':''}" /> " style="top:<c:out value="${das_top}" />px;"  onClick="das_popup('<c:out value="${data.dasEventId}"/>', <c:out value="${status.index}" />)"></li>
				</c:when>
				<c:when test="${data.dasEventTunnel eq 'TUNNEL_03'}">
					<li class="das_tunnel03 <c:out value="${commonVO.dashboard_dasEventId eq data.dasEventId ? 'selected':''}" /> <c:out value="${commonVO.dashboard_order ? 'new':''}" /> " style="left:<c:out value="${das_left}" />px;"  onClick="das_popup('<c:out value="${data.dasEventId}"/>', <c:out value="${status.index}" />)"></li>
				</c:when>
				<c:when test="${data.dasEventTunnel eq 'TUNNEL_04'}">
					<li class="das_tunnel04 <c:out value="${commonVO.dashboard_dasEventId eq data.dasEventId ? 'selected':''}" /> <c:out value="${commonVO.dashboard_order ? 'new':''}" /> " style="top:<c:out value="${das_top}" />px;" onClick="das_popup('<c:out value="${data.dasEventId}"/>', <c:out value="${status.index}" />)"></li>
				</c:when>
				<c:when test="${data.dasEventTunnel eq 'TUNNEL_05'}">
					<li class="das_tunnel05 <c:out value="${commonVO.dashboard_dasEventId eq data.dasEventId ? 'selected':''}" /> <c:out value="${commonVO.dashboard_order ? 'new':''}" /> " style="left:<c:out value="${das_left}" />px;"  onClick="das_popup('<c:out value="${data.dasEventId}"/>', <c:out value="${status.index}" />)"></li>
				</c:when>
			</c:choose>
        </c:forEach>
	</ul>