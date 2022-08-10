$(document).ready(function(){
	//중요지점 테이블 마우스 오버 시 팝업 호출
	$("#zoneTable > tbody > tr").on('mouseover', function () {
		if(!$(this).hasClass("noData")){
			var fnc = $(this).attr("onclick").replace( "zone_popup", "zone_focus");
			eval(fnc);
		}
	});
	
	//중요지점 테이블 마우스 아웃 시 팝업 호출
	$("#zoneTable > tbody > tr").on('mouseleave', function () {
		if(!$(this).hasClass("noData")){
			var fnc = $(this).attr("onclick").replace( "zone_popup", "zone_focus_out");
			eval(fnc);
		}
	});
	
	//마우스가 테이블 떠났을때 포커스 팝업 소스 지우기
	$("#zoneTable > tbody").on('mouseleave', function () {
		$(".zone_popup_focus").addClass("DiNone");
		$('.zone_popup_focus').html("");
	});
});