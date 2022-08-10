<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jstl/jstl.jsp"%>

<div class="board_info">
	<form action="/dtsEventStats" name="month_form" method="post">
		<input type="hidden" name="mode" value="month" />
		<input type="hidden" name="searchDay" value="${commonVO.searchDay}" />
		<input type="hidden" name="eventCodeDay" value="${commonVO.eventCodeDay}" />
		<input type="hidden" name="eventLineDay" value="${commonVO.eventLineDay}" />
		<input type="hidden" name="eventZoneDay" value="${commonVO.eventZoneDay}" />
		<input type="hidden" name="searchYear" value="${commonVO.searchYear}" />
		<input type="hidden" name="searchYearEnd" value="${commonVO.searchYearEnd}" />
		<input type="hidden" name="eventCodeYear" value="${commonVO.eventCodeYear}" />
		<input type="hidden" name="eventLineYear" value="${commonVO.eventLineYear}" />
		<input type="hidden" name="eventZoneYear" value="${commonVO.eventZoneYear}" />
		
		<div class="search_area">
			<div class="search_area_row">
    			<label class="Wd50 ClearWS" for="searchMonth">날짜</label>
                <input type="text" class="Wd80 yearPicker" name="searchMonth" id="searchMonth" value="<c:out value="${commonVO.searchMonth}" />">
    			<label for="eventLineMonth">전력선</label>
    			<select name="eventLineMonth">
    				<option value="">전체</option>
					<c:forEach var="data" items="${lineSearchList}" varStatus="status">
		            	<option value="<c:out value="${data.codeId}" />" <c:out value="${commonVO.eventLineMonth eq data.codeId ? 'selected=selected':'' }" />>
		            		<c:out value="${data.codeName}" />
		            	</option>
			        </c:forEach>
				</select>
				<label for="eventCodeMonth">발생단계</label>
    			<select name="eventCodeMonth">
    				<option value="">전체</option>
					<c:forEach var="data" items="${eventSearchList}" varStatus="status">
		            	<option value="<c:out value="${data.codeId}" />" <c:out value="${commonVO.eventCodeMonth eq data.codeId ? 'selected=selected':'' }" />>
		            		<c:out value="${data.codeName}" />
		            	</option>
			        </c:forEach>
				</select>
				<label for="eventZoneMonth">중요지점</label>
    			<select name="eventZoneMonth">
    				<option value="">전체</option>
					<c:forEach var="data" items="${zoneSearchList}" varStatus="status">
		            	<option value="<c:out value="${data.zoneId}" />" <c:out value="${commonVO.eventZoneMonth eq data.zoneId ? 'selected=selected':'' }" />>
		            		<c:out value="${data.zoneName}" />
		            	</option>
			        </c:forEach>
				</select>
				<button type="submit" class="btn_search" >검색</button>
			</div>
		</div>
    </form>
</div>
    
<div id="monthContainer" class="chartDiv"></div>
<c:if test="${monthList.size() > 0}">
	<button class="btn_down" type="button" onclick="excelDown('month');">다운로드</button>
</c:if>
<div class="statsTable">
	<table class="tstyle_list3">
		<caption>선로 이벤트 통계 월별 목록입니다.</caption>
		<colgroup>
	       	<col width="4%">
			<col width="*">
		</colgroup>
		<thead>
			<tr>
					<th rowspan="2">NO</th>
	          		<th rowspan="2">날짜</th>
	          	<c:if test="${strUtil:getComparison(commonVO.eventLineMonth,'LINE_01', false)}">
	          		<th colspan="<c:out value="${strUtil:getComparison(commonVO.eventCodeMonth,'', false) ? '2' : ''}" />">
	          			<c:out value="${lineSearchList[0].codeName}" />
	          		</th>
	          	</c:if>
	          	<c:if test="${strUtil:getComparison(commonVO.eventLineMonth,'LINE_02', false)}">
	          		<th colspan="<c:out value="${strUtil:getComparison(commonVO.eventCodeMonth,'', false) ? '2' : ''}" />">
	          			<c:out value="${lineSearchList[1].codeName}" />
	          		</th>
	          	</c:if>
	          	<c:if test="${strUtil:getComparison(commonVO.eventLineMonth,'LINE_03', false)}">
	          		<th colspan="<c:out value="${strUtil:getComparison(commonVO.eventCodeMonth,'', false) ? '2' : ''}" />">
	          			<c:out value="${lineSearchList[2].codeName}" />
	          		</th>
	          	</c:if>
	          	<%-- <c:if test="${strUtil:getComparison(commonVO.eventLineMonth,'LINE_04', false)}">
	          		<th colspan="<c:out value="${strUtil:getComparison(commonVO.eventCodeMonth,'', false) ? '2' : ''}" />">
	          			<c:out value="${lineSearchList[3].codeName}" />
	          		</th>
	          	</c:if> --%>
	          		<th colspan="<c:out value="${strUtil:getComparison(commonVO.eventCodeMonth,'', false) ? '2' : ''}" />">합계</th>
	          		<th rowspan="2" class="Wd100">분포도</th>  	
	        </tr>
	        <tr>
	        	
	          	<c:if test="${strUtil:getComparison(commonVO.eventLineMonth,'LINE_01', false)}">
	          		<c:if test="${strUtil:getComparison(commonVO.eventCodeMonth,'EVENT_01', false)}">
	          			<th>경고</th>
	          		</c:if>
	          		<c:if test="${strUtil:getComparison(commonVO.eventCodeMonth,'EVENT_02', false)}">
		         		<th>심각</th>
		         	</c:if>
	          	</c:if>
	          	<c:if test="${strUtil:getComparison(commonVO.eventLineMonth,'LINE_02', false)}">
	          		<c:if test="${strUtil:getComparison(commonVO.eventCodeMonth,'EVENT_01', false)}">
	          			<th>경고</th>
	          		</c:if>
	          		<c:if test="${strUtil:getComparison(commonVO.eventCodeMonth,'EVENT_02', false)}">
		         		<th>심각</th>
		         	</c:if>
	          	</c:if>
	          	<c:if test="${strUtil:getComparison(commonVO.eventLineMonth,'LINE_03', false)}">
	          		<c:if test="${strUtil:getComparison(commonVO.eventCodeMonth,'EVENT_01', false)}">
	          			<th>경고</th>
	          		</c:if>
	          		<c:if test="${strUtil:getComparison(commonVO.eventCodeMonth,'EVENT_02', false)}">
		         		<th>심각</th>
		         	</c:if>
	          	</c:if>
	          		<c:if test="${strUtil:getComparison(commonVO.eventCodeMonth,'EVENT_01', false)}">
	          			<th>경고</th>
	          		</c:if>
	          		<c:if test="${strUtil:getComparison(commonVO.eventCodeMonth,'EVENT_02', false)}">
	         			<th>심각</th>	 	 	
	         		</c:if>	 	 	
	        </tr>
		</thead>
		<tbody>
			<c:choose>
	           	<c:when test="${monthList.size() > 0}">
	           		<c:set var="num" value="1"/>
					<c:forEach var="data" items="${monthList}" varStatus="status">
			        	<tr class="noData">
				        	<td><c:out value="${num}" /></td>
				            <td>
				            	<c:out value="${data.eventStatsTimeGroup}월" />
				            </td>
				            <c:if test="${strUtil:getComparison(commonVO.eventLineMonth,'LINE_01', false)}">
				          		<c:if test="${strUtil:getComparison(commonVO.eventCodeMonth,'EVENT_01', false)}">
				          			<td><c:out value="${data.eventStats0101}" /></td>
					         	</c:if>	
					         	<c:if test="${strUtil:getComparison(commonVO.eventCodeMonth,'EVENT_02', false)}">
				          			<td><c:out value="${data.eventStats0102}" /></td>
								</c:if>
				          	</c:if>
				          	<c:if test="${strUtil:getComparison(commonVO.eventLineMonth,'LINE_02', false)}">
				          		<c:if test="${strUtil:getComparison(commonVO.eventCodeMonth,'EVENT_01', false)}">
				          			<td><c:out value="${data.eventStats0201}" /></td>
					         	</c:if>	
					         	<c:if test="${strUtil:getComparison(commonVO.eventCodeMonth,'EVENT_02', false)}">
				          			<td><c:out value="${data.eventStats0202}" /></td>
								</c:if>
				          	</c:if>
				          	<c:if test="${strUtil:getComparison(commonVO.eventLineMonth,'LINE_03', false)}">
				          		<c:if test="${strUtil:getComparison(commonVO.eventCodeMonth,'EVENT_01', false)}">
				          			<td><c:out value="${data.eventStats0301}" /></td>
					         	</c:if>	
					         	<c:if test="${strUtil:getComparison(commonVO.eventCodeMonth,'EVENT_02', false)}">
				          			<td><c:out value="${data.eventStats0302}" /></td>
								</c:if>
				          	</c:if>
				          	<%-- <c:if test="${strUtil:getComparison(commonVO.eventLineMonth,'LINE_04', false)}">
				          		<c:if test="${strUtil:getComparison(commonVO.eventCodeMonth,'EVENT_01', false)}">
				          			<td><c:out value="${data.eventStats0401}" /></td>
					         	</c:if>	
					         	<c:if test="${strUtil:getComparison(commonVO.eventCodeMonth,'EVENT_02', false)}">
				          			<td><c:out value="${data.eventStats0402}" /></td>
								</c:if>
				          	</c:if> --%>
					          	<c:if test="${strUtil:getComparison(commonVO.eventCodeMonth,'EVENT_01', false)}">
					          		<td><c:out value="${data.eventStatsTotal01}" /></td>
					          	</c:if>
					          	<c:if test="${strUtil:getComparison(commonVO.eventCodeMonth,'EVENT_02', false)}">
					          		<td><c:out value="${data.eventStatsTotal02}" /></td>
					          	</c:if>
				            	<td>
				            		<span onClick="scatterPlot('<c:out value="${data.eventStatsTime}" />')">상세보기</span>
				            	</td>
				        </tr>
				        <c:set var="num" value ="${num+1}"/>
					</c:forEach>
	       		</c:when>
	       		<c:otherwise>
	       			<tr class="noData">
				        <c:set var="lineCnt" value ="${strUtil:getComparison(commonVO.eventLineMonth, '', false) ? 4  : 2 }" />
				        <c:set var="CodeCnt" value ="${strUtil:getComparison(commonVO.eventCodeMonth, '', false) ? 2 : 1}"/>
				        <c:set var="noData" value ="${3 + (lineCnt * CodeCnt)}"/>
						<td colspan="<c:out value="${noData}" />" class="TxtC">데이터가 없습니다.</td>
					</tr>
	       		</c:otherwise>
	       	</c:choose>
		</tbody>
	</table>
</div>