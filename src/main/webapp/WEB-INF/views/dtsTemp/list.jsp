<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jstl/jstl.jsp"%>

<section class="content">  
	<div class="board_info">
		<form action="/dtsTemp"  name="search_form"  id="search_form" method="post">
        	<input type="hidden" name="pageNo" value="1"/>
			<input type="hidden" name="pageSize" value="<c:out value="${commonVO.pageSize}" />" />
			<input type="hidden" name="mode" value="list" />
			
			<div class="search_area">
				<div class="search_area_row">
					<label for="tempLine">전력선</label>
	    			<select name="tempLine" onChange="lineChange()" >
						<c:forEach var="data" items="${lineSearchList}" varStatus="status">
			            	<option value="<c:out value="${data.codeId}" />" <c:out value="${commonVO.tempLine eq data.codeId ? 'selected=selected':'' }" />>
			            		<c:out value="${data.codeName}" />
			            	</option>
				        </c:forEach>
					</select>
					<label for="tempDate">일자</label>
	                <input type="text" class="Wd120" name="tempDate"  value="<c:out value="${commonVO.tempDate}" />" />
	                <label for="tempSearchType">검색조건</label>
	                <input type="radio" name="tempSearchType" id="tempSearchType1" value="zoneType" <c:out value="${commonVO.tempSearchType eq 'zoneType' ? 'checked=checked':''}" /> />
	                <label for="tempSearchType1" class="Ml0"><span></span>중요지점</label>
	             	<input type="radio" name="tempSearchType" id="tempSearchType2" value="locationType" <c:out value="${commonVO.tempSearchType eq 'locationType' ? 'checked=checked':''}" />  />
	             	<label for="tempSearchType2" class="Ml0"><span></span>특정지점</label>
					<span class="DiNone" id="locationType">
						<label for="eventLocationStart"  class="Ml28">구간</label>
		                <input type="text" class="Wd50" name="tempLocationStart" value="<c:out value="${commonVO.tempLocationStart}" />" maxlength="4" onKeyUp="onlyNumber(this);" />
		                m  ~  
		                <input type="text" class="Wd50" name="tempLocationEnd" value="<c:out value="${commonVO.tempLocationEnd}" />"  maxlength="4" onKeyUp="onlyNumber(this);" />
		                m
					</span>
					<button type="button"  class="btn_search"  onclick="goSearch();">검색</button>
	            </div>
	            <div class="search_area_row DiNone" id="zoneType">
	              	<div class="zoneTypeDiv">
	              		<jsp:include page="./content/zone_type.jsp" />
	              	</div>
               	</div>
			</div>
	    </form>
	</div>
	
	<div class="BtnArea">
		<!-- <p class="page_info">
			※ 전체 구간의 데이터를 확인하려면 ‘전체 구간 다운로드’ 버튼으로 다운로드해주세요.
		</p> -->
		<button class="btn_down" type="button" onclick="excelDown();">다운로드</button>
		<!-- <button class="btn_down Wd150 Mr5"  type="button" onclick="excelAllDown();">전체 다운로드</button> -->
	</div>
	<div id="tempContainer" class="chartDiv" style=" max-height: 600px; height: 600px;"></div>
	
	<form action="/dtsTemp"  name="list_form" method="post">
        	<input type="hidden" name="pageNo" value="1"/>
			<input type="hidden" name="pageSize" value="<c:out value="${commonVO.pageSize}" />" />
			<input type="hidden" name="mode" value="list" />
			<input type="hidden" name="tempLine" value="<c:out value="${commonVO.tempLine}" />" />
			<input type="hidden" name="tempDate" value="<c:out value="${commonVO.tempDate}" />" />
			<input type="hidden" name="tempSearchType" value="<c:out value="${commonVO.tempSearchType}" />" />
			<input type="hidden" name="tempLocationStart" value="<c:out value="${commonVO.tempLocationStart}" />" />
			<input type="hidden" name="tempLocationEnd" value="<c:out value="${commonVO.tempLocationEnd}" />" />
			<input type="hidden" name="tempZoneCheck" value="<c:out value="${commonVO.tempZoneCheck}" />" />
	</form>
</section>
<script src="${pageContext.request.contextPath}/resources/js/custom/dtsTemp/dtsTemp.js"></script>