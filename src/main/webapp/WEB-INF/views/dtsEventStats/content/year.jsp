<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jstl/jstl.jsp"%>

<div class="board_info">
	<form action="/dtsEventStats" name="year_form" method="post">
		<input type="hidden" name="mode" value="year" />
		<input type="hidden" name="searchDay" value="${commonVO.searchDay}" />
		<input type="hidden" name="eventCodeDay" value="${commonVO.eventCodeDay}" />
		<input type="hidden" name="eventLineDay" value="${commonVO.eventLineDay}" />
		<input type="hidden" name="eventZoneDay" value="${commonVO.eventZoneDay}" />
		<input type="hidden" name="searchMonth" value="${commonVO.searchMonth}" />
		<input type="hidden" name="eventCodeMonth" value="${commonVO.eventCodeMonth}" />
		<input type="hidden" name="eventLineMonth" value="${commonVO.eventLineMonth}" />
		<input type="hidden" name="eventZoneMonth" value="${commonVO.eventZoneMonth}" />
		
		<div class="search_area">
			<div class="search_area_row">
    			<label class="Wd50 ClearWS" for="searchYear">날짜</label>
                <input type="text" class="Wd80 yearPicker" name="searchYear"  id="searchYear" value="<c:out value="${commonVO.searchYear}" />"> 
                &nbsp;&nbsp;&nbsp; ~ &nbsp;
    			<input type="text" class="Wd80 yearPicker" name="searchYearEnd" id="searchYearEnd"  value="<c:out value="${commonVO.searchYearEnd}" />">
    			<label for="eventLineYear">전력선</label>
    			<select name="eventLineYear">
    				<option value="">전체</option>
					<c:forEach var="data" items="${lineSearchList}" varStatus="status">
		            	<option value="<c:out value="${data.codeId}" />" <c:out value="${commonVO.eventLineYear eq data.codeId ? 'selected=selected':'' }" />>
		            		<c:out value="${data.codeName}" />
		            	</option>
			        </c:forEach>
				</select>
				<label for="eventCodeYear">발생단계</label>
    			<select name="eventCodeYear">
    				<option value="">전체</option>
					<c:forEach var="data" items="${eventSearchList}" varStatus="status">
		            	<option value="<c:out value="${data.codeId}" />" <c:out value="${commonVO.eventCodeYear eq data.codeId ? 'selected=selected':'' }" />>
		            		<c:out value="${data.codeName}" />
		            	</option>
			        </c:forEach>
				</select>
				<label for="eventZoneYear">중요지점</label>
    			<select name="eventZoneYear">
    				<option value="">전체</option>
					<c:forEach var="data" items="${zoneSearchList}" varStatus="status">
		            	<option value="<c:out value="${data.zoneId}" />" <c:out value="${commonVO.eventZoneYear eq data.zoneId ? 'selected=selected':'' }" />>
		            		<c:out value="${data.zoneName}" />
		            	</option>
			        </c:forEach>
				</select>
				<button type="submit"  class="btn_search" >검색</button>
			</div>
		</div>
    </form>
</div>
    
<div id="yearContainer" class="chartDiv"></div>
<c:if test="${yearList.size() > 0}">
	<button class="btn_down" type="button" onclick="excelDown('year');">다운로드</button>
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
	          	<c:if test="${strUtil:getComparison(commonVO.eventLineYear,'LINE_01', false)}">
	          		<th colspan="<c:out value="${strUtil:getComparison(commonVO.eventCodeYear,'', false) ? '2' : ''}" />">
	          			<c:out value="${lineSearchList[1].codeName}" />
	          		</th>
	          	</c:if>
	          	<c:if test="${strUtil:getComparison(commonVO.eventLineYear,'LINE_02', false)}">
	          		<th colspan="<c:out value="${strUtil:getComparison(commonVO.eventCodeYear,'', false) ? '2' : ''}" />">
	          			<c:out value="${lineSearchList[1].codeName}" />
	          		</th>
	          	</c:if>
	          	<c:if test="${strUtil:getComparison(commonVO.eventLineYear,'LINE_03', false)}">
	          		<th colspan="<c:out value="${strUtil:getComparison(commonVO.eventCodeYear,'', false) ? '2' : ''}" />">
	          			<c:out value="${lineSearchList[2].codeName}" />
	          		</th>
	          	</c:if>
	          	<%-- <c:if test="${strUtil:getComparison(commonVO.eventLineYear,'LINE_04', false)}">
	          		<th colspan="<c:out value="${strUtil:getComparison(commonVO.eventCodeYear,'', false) ? '2' : ''}" />">
	          			<c:out value="${lineSearchList[3].codeName}" />
	          		</th>
	          	</c:if> --%>
	          		<th colspan="<c:out value="${strUtil:getComparison(commonVO.eventCodeYear,'', false) ? '2' : ''}" />">합계</th>
	          		<th rowspan="2" class="Wd100">분포도</th>  	
	        </tr>
	        <tr>
	        	<c:if test="${strUtil:getComparison(commonVO.eventLineYear,'LINE_01', false)}">
					<c:if test="${strUtil:getComparison(commonVO.eventCodeYear,'EVENT_01', false)}">
	          			<th>경고</th>
	          		</c:if>
	          		<c:if test="${strUtil:getComparison(commonVO.eventCodeYear,'EVENT_02', false)}">
		         		<th>심각</th>
		         	</c:if>
	          	</c:if>
	          	<c:if test="${strUtil:getComparison(commonVO.eventLineYear,'LINE_02', false)}">
					<c:if test="${strUtil:getComparison(commonVO.eventCodeYear,'EVENT_01', false)}">
	          			<th>경고</th>
	          		</c:if>
	          		<c:if test="${strUtil:getComparison(commonVO.eventCodeYear,'EVENT_02', false)}">
		         		<th>심각</th>
		         	</c:if>
	          	</c:if>
	          	<c:if test="${strUtil:getComparison(commonVO.eventLineYear,'LINE_03', false)}">
					<c:if test="${strUtil:getComparison(commonVO.eventCodeYear,'EVENT_01', false)}">
	          			<th>경고</th>
	          		</c:if>
	          		<c:if test="${strUtil:getComparison(commonVO.eventCodeYear,'EVENT_02', false)}">
		         		<th>심각</th>
		         	</c:if>
	          	</c:if>
	          		<c:if test="${strUtil:getComparison(commonVO.eventCodeYear,'EVENT_01', false)}">
	          			<th>경고</th>
	          		</c:if>
	          		<c:if test="${strUtil:getComparison(commonVO.eventCodeYear,'EVENT_02', false)}">
	         			<th>심각</th>	 	 	
	         		</c:if>	 	 		 	 	
	        </tr>
		</thead>
		<tbody>
			<c:choose>
	           	<c:when test="${yearList.size() > 0}">
	           		<c:set var="num" value="1"/>
					<c:forEach var="data" items="${yearList}" varStatus="status">
			        	<tr class="noData">
				        	<td><c:out value="${num}" /></td>
				            <td>
				            	<c:out value="${data.eventStatsTimeGroup}년" />
				            </td>
				            <c:if test="${strUtil:getComparison(commonVO.eventLineYear,'LINE_01', false)}">
				          		<c:if test="${strUtil:getComparison(commonVO.eventCodeYear,'EVENT_01', false)}">
				          			<td><c:out value="${data.eventStats0101}" /></td>
					         	</c:if>	
					         	<c:if test="${strUtil:getComparison(commonVO.eventCodeYear,'EVENT_02', false)}">
				          			<td><c:out value="${data.eventStats0102}" /></td>
								</c:if>
				          	</c:if>
				          	<c:if test="${strUtil:getComparison(commonVO.eventLineYear,'LINE_02', false)}">
				          		<c:if test="${strUtil:getComparison(commonVO.eventCodeYear,'EVENT_01', false)}">
				          			<td><c:out value="${data.eventStats0201}" /></td>
					         	</c:if>	
					         	<c:if test="${strUtil:getComparison(commonVO.eventCodeYear,'EVENT_02', false)}">
				          			<td><c:out value="${data.eventStats0202}" /></td>
								</c:if>
				          	</c:if>
				          	<c:if test="${strUtil:getComparison(commonVO.eventLineYear,'LINE_03', false)}">
				          		<c:if test="${strUtil:getComparison(commonVO.eventCodeYear,'EVENT_01', false)}">
				          			<td><c:out value="${data.eventStats0301}" /></td>
					         	</c:if>	
					         	<c:if test="${strUtil:getComparison(commonVO.eventCodeYear,'EVENT_02', false)}">
				          			<td><c:out value="${data.eventStats0302}" /></td>
								</c:if>
				          	</c:if>
				          	<%-- <c:if test="${strUtil:getComparison(commonVO.eventLineYear,'LINE_04', false)}">
				          		<c:if test="${strUtil:getComparison(commonVO.eventCodeYear,'EVENT_01', false)}">
				          			<td><c:out value="${data.eventStats0401}" /></td>
					         	</c:if>	
					         	<c:if test="${strUtil:getComparison(commonVO.eventCodeYear,'EVENT_02', false)}">
				          			<td><c:out value="${data.eventStats0402}" /></td>
								</c:if>
				          	</c:if> --%>
					          	<c:if test="${strUtil:getComparison(commonVO.eventCodeYear,'EVENT_01', false)}">
					          		<td><c:out value="${data.eventStatsTotal01}" /></td>
					          	</c:if>
					          	<c:if test="${strUtil:getComparison(commonVO.eventCodeYear,'EVENT_02', false)}">
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
				        <c:set var="lineCnt" value ="${strUtil:getComparison(commonVO.eventLineYear, '', false) ? 4  : 2 }" />
				        <c:set var="CodeCnt" value ="${strUtil:getComparison(commonVO.eventCodeYear, '', false) ? 2 : 1}"/>
				        <c:set var="noData" value ="${3 + (lineCnt * CodeCnt)}"/>
						<td colspan="<c:out value="${noData}" />" class="TxtC">데이터가 없습니다.</td>
					</tr>
	      		</c:otherwise>
	      	</c:choose>
		</tbody>
	</table>
</div>