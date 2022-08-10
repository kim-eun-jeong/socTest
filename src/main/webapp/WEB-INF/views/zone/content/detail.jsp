<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jstl/jstl.jsp"%>

<form action="/zone" name="detail_form" method="post">
	<input type="hidden" name="zoneId" value="<c:out value="${detail.zoneId}" />" />
	<table class="tstyle_write">
		<caption>지점 상세 정보</caption>
		<colgroup>
			<col width="30%">
			<col width="*">
		</colgroup>
		<thead>
			<tr>
				<th scope="col" colspan="2">
					<p class="title">상세 정보</p>
				</th>
			</tr>
		</thead>
		<tbody>
	       	<tr>
				<th>구분<span>*</span></th>
	             <td>
	             	<select name="zoneType" class="Wd110">
	             		<option value="">선택</option>
	             		<c:forEach var="data" items="${zoneTypeList}" varStatus="status">
			            	<option value="<c:out value="${data.codeId}" />" <c:out value="${detail.zoneType eq data.codeId ? 'selected=selected':'' }" />>
			            		<c:out value="${data.codeName}" />
			            	</option>
				        </c:forEach>
	                </select>
	             </td>
	       	</tr>
	       	<tr>
				<th>지점명<span>*</span></th>
	             <td>
	             	<input type="text" name="zoneName" value="<c:out value="${detail.zoneName}" />" />
	             </td>
	       	</tr>
	       	<tr>
				<th>전력선</th>
	             <td>
	             	<select name="zoneLine" class="Wd110">
	             		<option value="">선택안함</option>
	             		<c:forEach var="data" items="${lineSearchList}" varStatus="status">
			            	<option value="<c:out value="${data.codeId}" />" <c:out value="${detail.zoneLine eq data.codeId ? 'selected=selected':'' }" />>
			            		<c:out value="${data.codeName}" />
			            	</option>
				        </c:forEach>
	                </select>
	             </td>
	       	</tr>
	       	<tr>
				<th>시작구간<span>*</span></th>
	             <td>
	             	<input type="text" name="zoneStart" value="<c:out value="${detail.zoneStart}" />"  maxlength="4" onKeyUp="onlyNumber(this);" />
	             </td>
	       	</tr>
	       	<tr>
				<th>종료구간<span>*</span></th>
	             <td>
	             	<input type="text" name="zoneEnd" value="<c:out value="${detail.zoneEnd}" />"  maxlength="4" onKeyUp="onlyNumber(this);" />
	             </td>
	       	</tr>
	       	<tr>
				<th>순서<span>*</span></th>
	             <td>
	             	<input type="text" name="zoneOrder" value="<c:out value="${detail.zoneOrder}" />" maxlength="2" onKeyUp="onlyNumber(this);"  />
	             </td>
	       	</tr>
		</tbody>
	</table>
</form>

<p class="BtnArea">
	<button type="button" class="btn" onclick="goSave();">저장</button>
	<c:if test="${detail.zoneId ne '' and detail.zoneId ne null }" >
		<button type="button" class="btn Mr5" onclick="goDelete('<c:out value="${detail.zoneId}" />');">삭제</button>
	</c:if>
</p>
