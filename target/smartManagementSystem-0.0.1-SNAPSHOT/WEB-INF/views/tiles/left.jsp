<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jstl/jstl.jsp"%>

<div class="leftTitle">
</div>

<nav id="gnb">
	<ul>
    	<li class="<c:out value="${menuVO.menuTitle eq 'dashboard' ? 'selected' : ''}" />" onclick="location.href='/dashboard' ">
			<span class="dashboard">대시보드</span> 
		</li>
		<li class="<c:out value="${menuVO.menuTitle eq 'dtsTemp' ? 'selected' : ''}" />" onclick="location.href='/dtsTemp' ">
			<span class="temp">온도 이력</span> 
		</li>
		<li class="<c:out value="${menuVO.menuTitle eq 'dtsEvent' or menuVO.menuTitle eq 'dasEvent' ? 'selected' : ''}" /> <c:out value="${menuVO.menuTitle eq 'dtsEvent' or menuVO.menuTitle eq 'dasEvent' ? 'on' : 'off'}" />">
			<span class="event">감지 이력</span> 
			<ul class="<c:out value="${menuVO.menuTitle eq 'dtsEvent' or menuVO.menuTitle eq 'dasEvent' ? 'DiBI' : ''}" />">
	            <li class="<c:out value="${menuVO.menuTitle eq 'dtsEvent' ? 'selected' : ''}" />" onclick="location.href='/dtsEvent' ">
	            	<span>온도감지</span>
	            </li>
	            <li class="<c:out value="${menuVO.menuTitle eq 'dasEvent' ? 'selected' : ''}" />" onclick="location.href='/dasEvent' ">
	            	<span>진동감지</span>
	            </li>
	        </ul>
		</li>
		<li class="<c:out value="${menuVO.menuTitle eq 'dtsEventStats' or menuVO.menuTitle eq 'dasEventStats' ? 'selected' : ''}" /> <c:out value="${menuVO.menuTitle eq 'dtsEventStats' or menuVO.menuTitle eq 'dasEventStats' ? 'on' : 'off'}" />" >
			<span class="stats">감지 통계</span> 
			<ul class="<c:out value="${menuVO.menuTitle eq 'dtsEventStats' or menuVO.menuTitle eq 'dasEventStats' ? 'DiBI' : ''}" />">
	         	<li class="<c:out value="${menuVO.menuTitle eq 'dtsEventStats' ? 'selected' : ''}" />" onclick="location.href='/dtsEventStats' ">
	            	<span>온도감지</span>
	            </li>
	            <li class="<c:out value="${menuVO.menuTitle eq 'dasEventStats' ? 'selected' : ''}" />" onclick="location.href='/dasEventStats' ">
	            	<span>진동감지</span>
	            </li>
	        </ul>
		</li>
		<%-- <li class="<c:out value="${menuVO.menuTitle eq 'zone' or menuVO.menuTitle eq 'temp' ? 'selected' : ''}" />  <c:out value="${menuVO.menuTitle eq 'zone' or menuVO.menuTitle eq 'temp' ? 'on' : 'off'}" />">
			<span class="setting">설정</span> 
			<ul class="<c:out value="${menuVO.menuTitle eq 'zone' or menuVO.menuTitle eq 'temp' ? 'DiBI' : ''}" />">
	            <li class="<c:out value="${menuVO.menuTitle eq 'zone' ? 'selected' : ''}" />" onclick="location.href='/zone' ">
	            	<span>중요지점 설정</span>
	            </li>
	             <li class="<c:out value="${menuVO.menuTitle eq 'temp' ? 'selected' : ''}" />" onclick="location.href='/temp' ">
	            	<span>온도 단계 설정</span>
	            </li>
	        </ul>
		</li> --%>
		<c:if test="${loginAuth eq 'AUTH_00'}">
			<li class="<c:out value="${menuVO.menuTitle eq 'member' ? 'selected' : ''}" />"  onclick="location.href='/member' ">
				<span class="member">사용자 관리</span> 
			</li>
		</c:if>
	</ul>
</nav>

<ul class="left-footer">
	<!-- <li><img alt="enitt" src="/resources/css/lib/images/enittW.png" /></li> 
	<li>Copyright 2020 ENITT Co., Ltd. All right reserved</li> -->
</ul>