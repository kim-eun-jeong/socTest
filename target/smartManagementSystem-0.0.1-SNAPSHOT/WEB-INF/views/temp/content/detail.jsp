<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jstl/jstl.jsp"%>

<form action="/temp" name="detail_form" method="post">
	<input type="hidden" name="tempId" value="<c:out value="${detail.tempId}" />" />
	<table class="tstyle_write">
		<caption>온도 단계 상세 정보</caption>
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
				<th>단계명<span>*</span></th>
	             <td>
	             	<input type="text" name="tempName" value="<c:out value="${detail.tempName}" />"/>
	             </td>
	       	</tr>
	       	<tr>
				<th>온도℃<span>*</span></th>
	             <td>
	             	<input type="text" name="tempValue" value="<c:out value="${detail.tempValue}" />" />
	             </td>
	       	</tr>
	       	<tr>
				<th>색깔<span>*</span></th>
	             <td>
	             	<input type="text" name="tempColor" value="<c:out value="${detail.tempColor}" />" />
	             </td>
	       	</tr>
	       	<tr>
				<th>순서<span>*</span></th>
	             <td>
	             	<input type="text" name="tempOrder" value="<c:out value="${detail.tempOrder}" />" maxlength="2" onKeyUp="onlyNumber(this);"  />
	             </td>
	       	</tr>
		</tbody>
	</table>
</form>

<p class="BtnArea">
	<button type="button" class="btn" onclick="goSave();">저장</button>
	<c:if test="${detail.tempId ne '' and detail.tempId ne null }" >
		<button type="button" class="btn Mr5" onclick="goDelete('<c:out value="${detail.tempId}" />');">삭제</button>
	</c:if>
</p>
