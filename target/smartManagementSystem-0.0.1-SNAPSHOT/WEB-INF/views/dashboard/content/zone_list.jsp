<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jstl/jstl.jsp"%>

<div class="row_box_header">
	<span class="row_box_header_temp_title">전력구 중요지점 현황</span>
	<div class="row_box_header_time_title">
		<span>업데이트 : <c:out value="${commonVO.dashboard_updateTime}" /></span>
	</div>          	
</div>

<div class="row_box_body">			
	<div>
		<table class="tstyle_list2" id="zoneTable">
			<colgroup>
				<col width="*">
				<col width="20%">
				<col width="20%">
				<col width="20%">
			</colgroup>
	 		<thead>
				<tr>
	            	<th>지점명</th>
	              	<th>구간</th>
	              	<th>전력선</th>
	              	<th>온도(℃)</th>
	              	<th>상태</th>
	        	</tr>
			</thead>
	        <tbody>
		        <c:choose>
	            	<c:when test="${dtsZoneList.size() > 0}">
			           	<c:forEach var="data" items="${dtsZoneList}" varStatus="status">
			               	<tr class="<c:out value="${data.zoneId}_${data.zoneLine}_${data.zoneIndex}" />" onclick="zone_popup('<c:out value="${data.zoneId}" />', '<c:out value="${data.zoneLine}" />', <c:out value="${data.zoneIndex}" />)">
			                	<td class="DiNone"><c:out value="${data.zoneTypeNm}" /></td>
			                	<td class="DiNone"><c:out value="${data.zoneState}" /></td>
			                	<td class="DiNone">
			                		<c:out value="${data.zoneLocationEndStr}" />
			                  		<br/>
			                  		(<c:out value="${data.zoneLocationEnd}" />m)
			                  	</td>
			                	<td><c:out value="${data.zoneNm}" /></td>
			                  	<td>
			                  		<c:out value="${data.zoneLocationStr}" />
			                  		<br/>
			                  		(<c:out value="${data.zoneLocation}" />m)
			                  	</td>	
			                  	<td><c:out value="${data.zoneLineNm}" /></td>	
			                  	<td class="${data.zoneState}"><fmt:formatNumber value="${data.zoneTemp}" pattern="0.0"/></td>
			                  	<td class="${data.zoneState}">
			                  		<c:choose>
			                  			<c:when test="${data.zoneState eq 'attention'}">
			                  				주의
			                  			</c:when>
			                  			<c:when test="${data.zoneState eq 'warning'}">
			                  				경고
			                  			</c:when>
			                  			<c:otherwise>
			                  				정상
			                  			</c:otherwise>
			                  		</c:choose>
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
</div>
<script src='<c:out value="${pageContext.request.contextPath}" />/resources/js/custom/dashboard/zone_list.js'></script>
