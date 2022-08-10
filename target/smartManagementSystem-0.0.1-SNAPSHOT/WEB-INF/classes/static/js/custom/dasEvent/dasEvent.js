$(document).ready(function(){
	var mode = $("[name=mode]").val();
	if(mode == "list"){
		$("#timeStart").datepicker(); 
		$("#timeEnd").datepicker(); 
		
	}else if(mode == "detail"){
	}
});

//list
	function goSearch(){
		var eventLocationStart = $("#searchForm [name=eventLocationStart]").val();
		var eventLocationEnd = $("#searchForm [name=eventLocationEnd]").val();
		if(eventLocationStart != null && eventLocationStart != '' && eventLocationEnd != null && eventLocationEnd != ''){
			if(Number(eventLocationStart) > Number(eventLocationEnd)){
				alert("시작 위치는 종료 위치보다 클 수 없습니다.");
				$("#searchForm [name=eventLocationStart]").val('');
				return;
			}
		}

		var eventTimeStart = new Date($("#searchForm [name=eventTimeStart]").val());
		var eventTimeEnd = new Date($("#searchForm [name=eventTimeEnd]").val());
		if(eventTimeStart > eventTimeEnd){
			alert("시간의 시작 시간은 종료 시간보다 클 수 없습니다.");
			$("#searchForm [name=eventTimeStart]").val('');
			return;
		}
		
		$('[name=search_form]').submit();
	}
	
	function goPage(page) {
		$('[name=pageNo]').val(page);
		$('[name=list_form]').submit();
	}
	
	function excelDown(){
		$('[name=list_form]').attr('action','/dasEvent/excel/list');
		$('[name=list_form]').submit();
		$('[name=list_form]').attr('action','/dasEvent');
	}