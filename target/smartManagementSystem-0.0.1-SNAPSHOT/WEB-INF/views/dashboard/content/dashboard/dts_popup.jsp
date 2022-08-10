<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jstl/jstl.jsp"%>

	<div>
		<c:choose>
			<c:when test="${dtsEventVO.dtsEventCode eq 'EVENT_01'}">
				<span class="attention"></span>
			</c:when>
			<c:when test="${dtsEventVO.dtsEventCode eq 'EVENT_02'}">
				<span class="warning"></span>
			</c:when>
		</c:choose>
		온도이상
	</div>
	<ul>
		<li><span>전&nbsp;&nbsp;력&nbsp;&nbsp;선 : </span><c:out value="${dtsEventVO.dtsEventLineNm}" /></li>
		<li><span>구&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;간 : </span><c:out value="${dtsEventVO.dtsEventLocationStr}" />(<c:out value="${dtsEventVO.dtsEventLocation}" />m)</li>
		<li><span>중요지점&nbsp;&nbsp;: </span><c:out value="${dtsEventVO.dtsEventZoneNm}" /></li>
			<c:choose>
				<c:when test="${dtsEventVO.dtsEventCode eq 'EVENT_01'}">
					<li><span>온도/단계 : </span><p class="fontAttention"><fmt:formatNumber value="${dtsEventVO.dtsEventTemp}" pattern="0.0"/> ℃ /<span class="attention"> <c:out value="${dtsEventVO.dtsEventCodeNm}" /></span></p></li>
				</c:when>
				<c:when test="${dtsEventVO.dtsEventCode eq 'EVENT_02'}">
					<li><span>온도/단계 : </span><p class="fontWarning"><fmt:formatNumber value="${dtsEventVO.dtsEventTemp}" pattern="0.0"/> ℃ /<span class="warning"> <c:out value="${dtsEventVO.dtsEventCodeNm}" /></span></p></li>
				</c:when>
			</c:choose>
		
		<li><span>발생시간&nbsp;&nbsp;: </span><c:out value="${dtsEventVO.dtsEventTime}" /></li>
	</ul>
