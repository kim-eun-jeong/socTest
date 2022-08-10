<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jstl/jstl.jsp"%>

<section class="dashboard_content">
 	<div class="dashboard_row">
		<div class="left_box"  id="dashboardDiv">
			<jsp:include page="./content/dashboard.jsp" />
		</div>
		<div class="right_box">
			<div class="row_box">
				<div class="row_box_header">
					<div class="stab_dth">
				        <ul id="statsDepth">
				          <li onClick="tabSetting('dts','dts')" class="selected"><span class="tab_dts">온도감지</span></li>
				          <li onClick="tabSetting('das','das')"><span class="tab_das">진동감지</span></li>
				        </ul>
				   </div>
				</div>
				<div class="row_box_body" id="dtsListDiv">
					<jsp:include page="./content/dts_list.jsp" />
				</div>
				<div class="row_box_body DiNone" id="dasListDiv">
					<jsp:include page="./content/das_list.jsp" />
				</div>
			</div>
			<div class="row_box"  id="zoneDiv">
				<jsp:include page="./content/zone_list.jsp" />
			</div>
		</div>
	</div>
	<div class="dashboard_row">
		<div class="full_box">
			<div class="row_box">
				<jsp:include page="./content/temp.jsp" />
			</div>
		</div>
	</div>
</section>
<script src='<c:out value="${pageContext.request.contextPath}" />/resources/js/custom/dashboard/main.js'></script>