<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jstl/jstl.jsp"%>
	<div>
		<table class="tstyle_list2" id="dtsTable">
			<colgroup>
				<col width="19%">
				<col width="20%">
				<col width="18%">
				<col width="16%">
				<col width="*">
			</colgroup>
	 		<thead>
				<tr>
	            	<th>전력선</th>
	              	<th>구간</th>
	              	<th>단계</th>
	              	<th>온도(℃)</th>
	              	<th>발생시간</th>
	        	</tr>
			</thead>
	        <tbody>
		        <c:choose>
	            	<c:when test="${dtsEventList.size() > 0}">
	            		<c:forEach var="data" items="${dtsEventList}" varStatus="status">
			               	<tr onclick="dts_popup('<c:out value="${data.dtsEventId}" />', <c:out value="${status.index}" />)">
			                	<td><c:out value="${data.dtsEventLineNm}" /></td>
			                  	<td>
			                  		<c:out value="${data.dtsEventLocationStr}" />
			                  		<br/>
			                  		( <c:out value="${data.dtsEventLocation}" />m )
			                  	</td>
			                  	<td class='<c:out value="${data.dtsEventCode eq 'EVENT_01' ? 'attention' : 'warning'}" />'>
			                  		<c:out value="${data.dtsEventCodeNm}" />
			                  	</td>
			                  	<td class='<c:out value="${data.dtsEventCode eq 'EVENT_01' ? 'attention' : 'warning'}" />'>
			                  		<fmt:formatNumber value="${data.dtsEventTemp}" pattern="0.0"/>
			                  	</td>
			                  	<td><c:out value="${data.dtsEventTime}" /></td>
			                </tr>
						</c:forEach>
					</c:when>
					<c:otherwise>
						<tr class="noData">
							<td colspan="5" class="TxtC">데이터가 없습니다.</td>
						</tr>
					</c:otherwise>
				</c:choose>
	   		</tbody>
		</table>
	</div>
<script src='<c:out value="${pageContext.request.contextPath}" />/resources/js/custom/dashboard/dts_list.js'></script>