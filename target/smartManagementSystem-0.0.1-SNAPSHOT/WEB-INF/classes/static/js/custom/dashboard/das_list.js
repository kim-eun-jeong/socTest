$(document).ready(function(){
	//DAS 이벤트 테이블 마우스 오버 시 팝업 호출
	$("#dasTable > tbody > tr").on('mouseover', function () {
		if(!$(this).hasClass("noData")){
			var idx = $(this).index();
			$(this).addClass("on");
			$(".das_event > ul > li:eq("+idx+")").addClass("on");
			var fnc = $(this).attr("onclick").replace( "das_popup", "das_focus");
			eval(fnc);
		}
	});
	
	//DAS 이벤트 테이블 마우스 아웃 시 팝업 닫기
	$("#dasTable > tbody > tr").on('mouseleave', function () {
		if(!$(this).hasClass("noData")){
			var idx = $(this).index();
			$(this).removeClass("on");
			$(".das_event > ul > li:eq("+idx+")").removeClass("on");
			var fnc = $(this).attr("onclick").replace( "das_popup", "das_focus_out");
			eval(fnc);
		}
	});
});