<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jstl/jstl.jsp"%>
	
	<c:choose>
		<c:when test="${dasEventVO.dasEventCode eq 'EVENT_01'}">
			<div class="event1"><span class="warning"></span><c:out value="${dasEventVO.dasEventCodeNm}" /></div>
		</c:when>
		<c:when test="${dasEventVO.dasEventCode eq 'EVENT_02'}">
			<div class="event2"><span class="warning"></span><c:out value="${dasEventVO.dasEventCodeNm}" /></div>
		</c:when>
		<c:when test="${dasEventVO.dasEventCode eq 'EVENT_03'}">
			<div class="event3"><span class="warning"></span><c:out value="${dasEventVO.dasEventCodeNm}" /></div>
		</c:when>
		<c:when test="${dasEventVO.dasEventCode eq 'EVENT_04'}">
			<div class="event4"><span class="warning"></span><c:out value="${dasEventVO.dasEventCodeNm}" /></div>
		</c:when>
	</c:choose>
	
	<ul>
		<li><span>발 생 구 : </span><c:out value="${dasEventVO.dasEventTunnelNm}" /></li>
		<li><span>구&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;간 : </span><c:out value="${dasEventVO.dasEventLocationStr}" />(<c:out value="${dasEventVO.dasEventLocation}" />m)</li>
		<li><span>발생내용 : </span><p class="fontEvnet"><c:out value="${dasEventVO.dasEventCodeNm}" /></p></li>
		<li><span>발생시간 : </span><c:out value="${dasEventVO.dasEventTime}" /></li>
	</ul>