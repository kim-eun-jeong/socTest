$(document).ready(function(){
	//DTS 이벤트 아이콘 마우스 오버 시 팝업 호출
	$(".dts_event > ul > li").on('mouseover', function () {
		var idx = $(this).index();
		$(this).addClass("on");
		$("#dtsTable > tbody > tr:eq("+idx+")").addClass("on");
		var fnc = $(this).attr("onclick").replace( "dts_popup", "dts_focus");
		eval(fnc);
	});
	
	//DTS 이벤트 아이콘 마우스 아웃 시 팝업 닫기
	$(".dts_event > ul > li").on('mouseleave', function () {
		var idx = $(this).index();
		$(this).removeClass("on");
		$("#dtsTable > tbody > tr:eq("+idx+")").removeClass("on");
		var fnc = $(this).attr("onclick").replace( "dts_popup", "dts_focus_out");
		eval(fnc);
		$(".dts_popup_focus").addClass("DiNone");
		$('.dts_popup_focus').html("");
	});
	
	//DAS 이벤트 아이콘 마우스 오버 시 팝업 호출
	$(".das_event > ul > li").on('mouseover', function () {
		var idx = $(this).index();
		$(this).addClass("on");
		$("#dasTable > tbody > tr:eq("+idx+")").addClass("on");
		var fnc = $(this).attr("onclick").replace( "das_popup", "das_focus");
		eval(fnc);
	});
	
	//DAS 이벤트 아이콘 마우스 아웃 시 팝업 닫기
	$(".das_event > ul > li").on('mouseleave', function () {
		var idx = $(this).index();
		$(this).removeClass("on");
		$("#dasTable > tbody > tr:eq("+idx+")").removeClass("on");
		var fnc = $(this).attr("onclick").replace( "das_popup", "das_focus_out");
		eval(fnc);
	});
	
	//중요지점 아이콘 마우스 오버 시 팝업 호출
	$(".zone > ul > li").on('mouseover', function () {
		var fnc = $(this).attr("onclick").replace( "zone_popup", "zone_focus");
		eval(fnc);
	});
	
	//중요지점 아이콘 마우스 아웃 시 팝업 닫기
	$(".zone > ul > li").on('mouseleave', function () {
		var fnc = $(this).attr("onclick").replace( "zone_popup", "zone_focus_out");
		eval(fnc);
		$(".zone_popup_focus").addClass("DiNone");
		$('.zone_popup_focus').html("");
	});
});
