<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jstl/jstl.jsp"%>

	<div>
		<table class="tstyle_list2" id="dasTable">
			<colgroup>
				<col width="23%">
				<col width="23%">
				<col width="23%">
				<col width="*">
			</colgroup>
	 		<thead>
				<tr>
	            	<th>발생구</th>
	              	<th>구간</th>
	              	<th>발생내용</th>
	              	<th>발생시간</th>
	        	</tr>
			</thead>
	        <tbody>
		        <c:choose>
	            	<c:when test="${dasEventList.size() > 0}">
			           	<c:forEach var="data" items="${dasEventList}" varStatus="status">
			               	<tr  onclick="das_popup('<c:out value="${data.dasEventId}" />', <c:out value="${status.index}" />)">
			                	<td><c:out value="${data.dasEventTunnelNm}" /></td>
			                	<td>
			                		<c:out value="${data.dasEventLocationStr}" />
			                  		<br/>
			                  		( <c:out value="${data.dasEventLocation}" />m )
			                	</td>
			                	<td class='event'><c:out value="${data.dasEventCodeNm}" /></td>
			                	<td><c:out value="${data.dasEventTime}" /></td>
			                </tr>
						</c:forEach>
					</c:when>
					<c:otherwise>
						<tr class="noData">
							<td colspan="4" class="TxtC">데이터가 없습니다.</td>
						</tr>
					</c:otherwise>
				</c:choose>
	   		</tbody>
		</table>
	</div>
<script src='<c:out value="${pageContext.request.contextPath}" />/resources/js/custom/dashboard/das_list.js'></script>