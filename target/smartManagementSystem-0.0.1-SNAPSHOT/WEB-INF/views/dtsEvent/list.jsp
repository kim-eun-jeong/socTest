<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jstl/jstl.jsp"%>

<section class="content">  
	<div class="board_info">
		<form action="/dtsEvent" id="searchForm" name="search_form" method="post">
        	<input type="hidden" name="pageNo" value="1"/>
			<input type="hidden" name="pageSize" value="<c:out value="${commonVO.pageSize}" />" />
			<input type="hidden" name="dtsEventId" value="" />
			<input type="hidden" name="mode" value="list" />
			
			<div class="search_area">
				<div class="search_area_row">
	                <label for="eventTimeStart">시간</label>
	                <input type="text" class="Wd120" name="eventTimeStart" id="timeStart" value="<c:out value="${commonVO.eventTimeStart}" />" />
	                 &nbsp;&nbsp;&nbsp; ~ &nbsp;
	               	<input type="text" class="Wd120" name="eventTimeEnd" id ="timeEnd" value="<c:out value="${commonVO.eventTimeEnd}" />" />
	            	<label for="eventCode">발생단계</label>
	    			<select name="eventCode">
	    				<option value="">전체</option>
						<c:forEach var="data" items="${eventSearchList}" varStatus="status">
			            	<option value="<c:out value="${data.codeId}" />" <c:out value="${commonVO.eventCode eq data.codeId ? 'selected=selected':'' }" />>
			            		<c:out value="${data.codeName}" />
			            	</option>
				        </c:forEach>
					</select>
					<label for="eventLocationStart">구간</label>
	                STA.<input type="text" class="Wd50" name="eventLocationStart" value="<c:out value="${commonVO.eventLocationStart}" />" maxlength="4" onKeyUp="onlyNumber(this);" />
	                  ~  
	                STA.<input type="text" class="Wd50" name="eventLocationEnd" value="<c:out value="${commonVO.eventLocationEnd}" />"  maxlength="4" onKeyUp="onlyNumber(this);" />
	                
	                <label for="eventZone">중요지점</label>
	    			<select name="eventZone">
	    				<option value="">전체</option>
						<c:forEach var="data" items="${zoneSearchList}" varStatus="status">
			            	<option value="<c:out value="${data.zoneId}" />" <c:out value="${commonVO.eventZone eq data.zoneId ? 'selected=selected':'' }" />>
			            		<c:out value="${data.zoneName}" />
			            	</option>
				        </c:forEach>
					</select>
					<label for="eventLine">전력선</label>
	    			<select name="eventLine">
	    				<option value="">전체</option>
						<c:forEach var="data" items="${lineSearchList}" varStatus="status">
			            	<option value="<c:out value="${data.codeId}" />" <c:out value="${commonVO.eventLine eq data.codeId ? 'selected=selected':'' }" />>
			            		<c:out value="${data.codeName}" />
			            	</option>
				        </c:forEach>
					</select>
					<button type="button"  class="btn_search" onclick="goSearch();">검색</button>
	            </div>
			</div>
	    </form>
	</div>
	
	<p class="page_info">
		전체 <strong><fmt:formatNumber type="number" maxFractionDigits="3" value="${commonVO.totalCount}" /></strong>건,
		현재 페이지 <strong><fmt:formatNumber type="number" maxFractionDigits="3" value="${commonVO.pageNo}" /></strong>/<strong><fmt:formatNumber type="number" maxFractionDigits="3" value="${commonVO.finalPageNo}" /></strong>
	</p>
	<c:if test="${commonVO.totalCount > 0}">
		<button class="btn_down" type="button" onclick="excelDown();">다운로드</button>
	</c:if>
	
	<table class="tstyle_list">
		<caption>DTS 이벤트 목록 입니다.</caption>
		<colgroup>
        	<col width="5%">
			<col width="*">
			<col width="15%">
			<col width="15%">
			<col width="15%">
			<col width="15%">
			<col width="15%">
		</colgroup>
		<thead>
			<tr>
				<th>NO</th>
	          	<th>시간</th>
	            <th>발생단계</th>
	            <th>구간</th>
	            <th>중요지점</th>
	            <th>전력선</th>
	            <th>온도(℃)</th>
			</tr>
		</thead>
		<tbody>
			<c:choose>
            	<c:when test="${commonVO.totalCount > 0}">
					<c:set var="num" value ="${commonVO.totalCount - ((commonVO.pageNo - 1) * commonVO.pageSize) }"/>          			
					<c:forEach var="data" items="${dtsEventList}" varStatus="status">
		               	<tr onClick="goDetail('<c:out value="${data.dtsEventId}" />')">
		               		<td><c:out value="${num}"/></td>
		                	<td><c:out value="${data.dtsEventTime}" /></td>
		                  	<td>
		                  		<c:choose>
		                  			<c:when test="${data.dtsEventCode eq 'EVENT_01'}">
		                  				<p class="attention"><c:out value="${data.dtsEventCodeNm}" /></p>
		                  			</c:when>
		                  			<c:otherwise>
		                  				<p class="warning"><c:out value="${data.dtsEventCodeNm}" /></p>
		                  			</c:otherwise>
		                  		</c:choose>
		                  		
		                  	</td>
		                  	<td><c:out value="${data.dtsEventLocationStr}" /></td>
		                  	<td><c:out value="${objUtil:toString(data.dtsEventZoneNm,'-')}" /></td>
		                  	<td><c:out value="${data.dtsEventLineNm}" /></td>
		                  	<td><fmt:formatNumber value="${data.dtsEventTemp}" pattern="0.0"/></td>
		                </tr>
		                <c:set var="num" value ="${num-1}"/>
					</c:forEach>
				</c:when>
				<c:otherwise>
					<tr class="noData">
						<td colspan="7" class="TxtC">데이터가 없습니다.</td>
					</tr>
				</c:otherwise>
			</c:choose>
		</tbody>
	</table>	
	<div class="board_pager Mt10">
		<c:if test="${commonVO.totalCount > 0}">
             ${commonVO.pageHtml}
        </c:if>
	</div>	
</section>

<form action="/dtsEvent" name="list_form" method="post">
	<input type="hidden" name="pageNo" value="<c:out value="${commonVO.pageNo}" />" />
	<input type="hidden" name="pageSize" value="<c:out value="${commonVO.pageSize}" />" />
	<input type="hidden" name="eventTimeStart" value="<c:out value="${commonVO.eventTimeStart}" />" />
	<input type="hidden" name="eventTimeEnd" value="<c:out value="${commonVO.eventTimeEnd}" />" />
	<input type="hidden" name="eventCode" value="<c:out value="${commonVO.eventCode}" />" />
	<input type="hidden" name="eventLocationStart" value="<c:out value="${commonVO.eventLocationStart}" />" />
	<input type="hidden" name="eventLocationEnd" value="<c:out value="${commonVO.eventLocationEnd}" />" />
	<input type="hidden" name="eventZone" value="<c:out value="${commonVO.eventZone}" />" />
	<input type="hidden" name="eventLine" value="<c:out value="${commonVO.eventLine}" />" />
	<input type="hidden" name="dtsEventId" value="" />
	<input type="hidden" name="mode" value="list" />
	<input type="hidden" name="dashboard" value="<c:out value="${commonVO.dashboard}" />" />
</form>


<script src="${pageContext.request.contextPath}/resources/js/custom/dtsEvent/dtsEvent.js"></script>