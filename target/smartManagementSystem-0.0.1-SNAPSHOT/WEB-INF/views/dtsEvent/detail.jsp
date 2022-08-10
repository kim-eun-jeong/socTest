<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jstl/jstl.jsp"%>

<section class="content">
	<form action="/dtsEvent" name="detail_form" method="post">
		<input type="hidden" name="pageNo" value="<c:out value="${commonVO.pageNo}" />" />
		<input type="hidden" name="pageSize" value="<c:out value="${commonVO.pageSize}" />" />
		<input type="hidden" name="eventTimeStart" value="<c:out value="${commonVO.eventTimeStart}" />" />
		<input type="hidden" name="eventTimeEnd" value="<c:out value="${commonVO.eventTimeEnd}" />" />
		<input type="hidden" name="eventCode" value="<c:out value="${commonVO.eventCode}" />" />
		<input type="hidden" name="eventLocationStart" value="<c:out value="${commonVO.eventLocationStart}" />" />
		<input type="hidden" name="eventLocationEnd" value="<c:out value="${commonVO.eventLocationEnd}" />" />
		<input type="hidden" name="eventZone" value="<c:out value="${commonVO.eventZone}" />" />
		<input type="hidden" name="eventLine" value="<c:out value="${commonVO.eventLine}" />" />
		<input type="hidden" name="dtsEventId" value="<c:out value="${commonVO.dtsEventId}" />" />
		<input type="hidden" name="mode" value="detail" />
	</form>
	<table class="tstyle_view">
		<caption>DTS 이벤트 상세정보</caption>
		<colgroup>
			<col width="15%">
			<col width="35%">
			<col width="15%">
			<col width="35%">
		</colgroup>
		<thead>
			<tr>
				<th scope="col" colspan="4">
					<p class="title">상세정보</p>
				</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<th>시간</th>
              	<td>
              		<c:out value="${detail.dtsEventTime}" />
              	</td>
            	<th>발생단계</th>
              	<td>
              		<c:out value="${detail.dtsEventCodeNm}" />
              	</td>
        	</tr>
			<tr>
				<th>구간</th>
              	<td>
              		<c:out value="${detail.dtsEventLocationStr}" />
              	</td>
            	<th>중요지점</th>
              	<td>
              		<c:out value="${detail.dtsEventZoneNm}" />
              	</td>
        	</tr>		
        	
			<tr>
				<th>전력선</th>
              	<td>
              		<c:out value="${detail.dtsEventLineNm}" />
              	</td>
            	<th>온도℃</th>
              	<td>
              		<fmt:formatNumber value="${detail.dtsEventTemp}" pattern="0.0"/>
              	</td>
        	</tr>		
        	
		</tbody>
	</table>
	<div id="eventContainer" class="chartDiv"></div>
	<span class="note text-gray">※ 해당 그래프는 발생된 이벤트의 앞, 뒤로 30분씩 온도 추이를 나타냅니다.</span>
	<p class="BtnArea">
		<button class="btn_list" type="button" onclick="goList(); return false;">목록</button>
	</p>
</section>

<script src="${pageContext.request.contextPath}/resources/js/custom/dtsEvent/dtsEvent.js"></script>