<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jstl/jstl.jsp"%>

<div class="board_info">
	<form action="/zone" id="searchForm" name="search_form" method="post">
       	<input type="hidden" name="pageNo" value="1" />
		<input type="hidden" name="pageSize" value="<c:out value="${commonVO.pageSize}" />" />
		<input type="hidden" name="mode" value="list" />
		
		<div class="search_area">
              	<div class="search_area_row">
                <select name="searchType">
                	<option value="">전체</option>
                	<option value="TYPE" <c:out value="${commonVO.searchType eq 'TYPE' ? 'selected':''}" />>구분</option>
                	<option value="NAME" <c:out value="${commonVO.searchType eq 'NAME' ? 'selected':''}" />>지점명</option>
                	<option value="LINE" <c:out value="${commonVO.searchType eq 'LINE' ? 'selected':''}" />>전력선</option>
				</select>
				<input type="text" class="Wd150" name="searchWord" value="<c:out value="${commonVO.searchWord}" />" maxlength="15" />
                
				<button type="submit"  class="btn_search">검색</button>
			</div>
		</div>
    </form>
</div>

<p class="page_info">
	전체 <strong><fmt:formatNumber type="number" maxFractionDigits="3" value="${commonVO.totalCount}" /></strong>건,
	현재 페이지 <strong><fmt:formatNumber type="number" maxFractionDigits="3" value="${commonVO.pageNo}" /></strong>/<strong><fmt:formatNumber type="number" maxFractionDigits="3" value="${commonVO.finalPageNo}" /></strong>
</p>
<button class="btn" type="button" onclick="getDetail('no','no');">추가</button>
<table class="tstyle_list" id="listTable">
	<caption>지점 관리 목록 입니다.</caption>
	<colgroup>
       	<col width="5%">
		<col width="15%">
		<col width="*">
		<col width="15%">
		<col width="15%">
		<col width="15%">
	</colgroup>
	<thead>
		<tr>
			<th>NO</th>
          	<th>구분</th>
            <th>지점명</th>
            <th>전력선</th>
            <th>시작구간(m)</th>
            <th>종료구간(m)</th>
            <th>순서</th>
		</tr>
	</thead>
	<tbody>
		<c:choose>
           	<c:when test="${commonVO.totalCount > 0}">
				<c:set var="num" value ="${commonVO.totalCount - ((commonVO.pageNo - 1) * commonVO.pageSize) }"/> 
				<c:set var="cnt" value ="0"/>         			
				<c:forEach var="data" items="${zoneList}" varStatus="status">
	               	<tr onClick="getDetail('<c:out value="${data.zoneId}" />','${cnt}')">
	               		<td><c:out value="${num}"/></td>
	                	<td><c:out value="${data.zoneTypeNm}" /></td>
	                	<td>
	                		<c:choose>
	                			<c:when test="${data.zoneLineNm ne null and data.zoneLineNm ne '' }">
	                				<c:out value="${data.zoneLineNm}" />
	                			</c:when>
	                			<c:otherwise>
	                				-
	                			</c:otherwise>
	                		</c:choose>
	                	</td>
	                  	<td><c:out value="${data.zoneName}" /></td>
	                  	<td><c:out value="${data.zoneStart}" /></td>
	                  	<td><c:out value="${data.zoneEnd}" /></td>
	                  	<td><c:out value="${data.zoneOrder}" /></td>
	                </tr>
	                <c:set var="num" value ="${num-1}"/>
	                <c:set var="cnt" value ="${cnt+1}"/>   
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

<form action="/zone" name="list_form" method="post">
	<input type="hidden" name="pageNo" value="<c:out value="${commonVO.pageNo}" />" />
	<input type="hidden" name="pageSize" value="<c:out value="${commonVO.pageSize}" />" />
	<input type="hidden" name="searchType" value="<c:out value="${commonVO.searchType}" />" />
	<input type="hidden" name="searchWord" value="<c:out value="${commonVO.searchWord}" />" />
	<input type="hidden" name="mode" value="list" />
</form>
