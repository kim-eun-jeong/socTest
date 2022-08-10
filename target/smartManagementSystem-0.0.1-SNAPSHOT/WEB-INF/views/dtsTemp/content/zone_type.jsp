<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jstl/jstl.jsp"%>
	<c:forEach var="data" items="${zoneSearchList}" varStatus="status"> 
        <input type="checkbox" name ="tempZoneCheck"  id="tempZoneCheck_<c:out value="${data.zoneId}" />" value="<c:out value="${data.zoneId}" />"  <c:out value="${fn:contains(commonVO.tempZoneCheck, data.zoneId) ? 'checked=checked' : ''}" />> 
    	<label for="tempZoneCheck_<c:out value="${data.zoneId}" />" class="checkbox"><span></span><c:out value="${data.zoneName}" /></label>
	</c:forEach>
