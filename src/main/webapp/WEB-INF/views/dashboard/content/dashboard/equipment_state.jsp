<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jstl/jstl.jsp"%>
	<ul>
		<c:forEach var="data" items="${equipmentList}" varStatus="status">
			<c:choose>
				<c:when test="${fn:contains(data.equipmentName,'DAS')}">
					<c:choose>
						<c:when test="${data.equipmentState eq 'NORMAL'}">
							<li class="das_normal"></li>
						</c:when>
						<c:when test="${data.equipmentState eq 'ERROR'}">
							<li class="das_error"></li>
						</c:when>
					</c:choose>
				</c:when>
				<c:when test="${fn:contains(data.equipmentName,'DTS')}">
					<c:choose>
						<c:when test="${data.equipmentState eq 'NORMAL'}">
							<li class="dts_normal"></li>
						</c:when>
						<c:when test="${data.equipmentState eq 'ERROR'}">
							<li class="dts_error"></li>
						</c:when>
					</c:choose>
				</c:when>
			</c:choose>
        </c:forEach>
	</ul>