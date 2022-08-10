$(document).ready(function(){
	//DTS 이벤트 테이블 마우스 오버 시 팝업 호출
	$("#dtsTable > tbody > tr").on('mouseover', function () {
		if(!$(this).hasClass("noData")){
			var idx = $(this).index();
			$(this).addClass("on");
			$(".dts_event > ul > li:eq("+idx+")").addClass("on");
			var fnc = $(this).attr("onclick").replace( "dts_popup", "dts_focus");
			eval(fnc);
		}
	});
	
	//DTS 이벤트 테이블 마우스 아웃 시 팝업 닫기
	$("#dtsTable > tbody > tr").on('mouseleave', function () {
		if(!$(this).hasClass("noData")){
			var idx = $(this).index();
			$(this).removeClass("on");
			$(".dts_event > ul > li:eq("+idx+")").removeClass("on");
			var fnc = $(this).attr("onclick").replace( "dts_popup", "dts_focus_out");
			eval(fnc);
		}
	});
	
	//마우스가 테이블 떠났을때 포커스 팝업 소스 지우기
	$("#dtsTable > tbody").on('mouseleave', function () {
		$(".dts_popup_focus").addClass("DiNone");
		$('.dts_popup_focus').html("");
	});
});