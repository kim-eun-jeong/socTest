$(document).ready(function(){
	 var getDetailStr = $('#listTable > tbody > tr').eq(0).attr("onClick");
	 if(typeof getDetailStr != "undefined"){
		 eval(getDetailStr,0);
	}
	 
});
function goPage(page) {
	$('[name=pageNo]').val(page);
	$('[name=list_form]').submit();
}

function getList(mode){
	params = $("[name=list_form]").serialize();
	rAjaxCall.asyncH("/zone/content/list", 'POST', params, function(data) {
		var cnt =$('#listTable > tbody ').children(".selected").index();
		
		$('#listDiv').html("");
		$('#listDiv').html(data);
		
		if(mode == "update"){
			var getDetailStr = $('#listTable > tbody > tr').eq(cnt).attr("onClick");
			 if(typeof getDetailStr != "undefined"){
				 eval(getDetailStr,0);
			}
		}else if(mode == "delete"){
			var getDetailStr = $('#listTable > tbody > tr').eq(0).attr("onClick");
			 if(typeof getDetailStr != "undefined"){
				 eval(getDetailStr,0);
			}else{
				getDetail('no','no');
			}
		}else{
			var getDetailStr = $('#listTable > tbody > tr').eq(0).attr("onClick");
			 if(typeof getDetailStr != "undefined"){
				 eval(getDetailStr,0);
			}
		}
	});	
}
function getDetail(id,num){
	var tr = $("#listTable > tbody > tr");
	tr.removeClass("selected");
	if(num != "no"){
		tr.eq(num).attr("class","selected");
	}
	var param = {
		"zoneId" : id
	};
	rAjaxCall.asyncH("/zone/content/detail", 'POST', param, function(data) {
		$('#detailDiv').html("");
		$('#detailDiv').html(data);
	});	
}

function goSave(){
	var zoneId = $("[name=zoneId]").val();
	var zoneType = $("[name=zoneType]").val();
	var zoneName = $("[name=zoneName]").val();
	var zoneStart = $("[name=zoneStart]").val();
	var zoneEnd = $("[name=zoneEnd]").val();
	var zoneOrder = $("[name=zoneOrder]").val();
	
	if(zoneType == "" || zoneType == null){
		alert("구분을 입력해주세요.");
		$("[name=zoneType]").focus();
		return;
	}
	if(zoneName == "" || zoneName == null){
		alert("지점명을 입력해주세요.");
		$("[name=zoneName]").focus();
		return;
	}
	if(zoneStart == "" || zoneStart == null){
		alert("시작 구간을 입력해주세요.");
		$("[name=zoneStart]").focus();
		return;
	}
	if(zoneEnd == "" || zoneEnd == null){
		alert("종료 구간을 입력해주세요.");
		$("[name=zoneEnd]").focus();
		return;
	}
	if(Number(zoneStart) > Number(zoneEnd)){
		alert("시간 구간은 종료 구간보다 클 수 없습니다.");
		$("[name=zoneStart]").val("");
		$("[name=zoneStart]").focus();
		return;
	}
	if(zoneOrder == "" || zoneOrder == null){
		alert("순서를 입력해주세요.");
		$("[name=zoneOrder]").focus();
		return;
	}
	
	if(confirm("저장하시겠습니까 ?")){
		var params = $("[name=detail_form]").serialize();
		if(zoneId == "" || zoneId == null){
			rAjaxCall.async("/zone/ajaxCreateZone", 'POST', params, function(r) {
				if(r.result){
					alert(r.content);
					getList();
				}else{
					alert(r.content);
				}
			});
		}else{
			rAjaxCall.async("/zone/ajaxUpdateZone", 'POST', params, function(r) {
				if(r.result){
					alert(r.content);
					getList("update");
				}else{
					alert(r.content);
				}
			});
		}
	}
}
function goDelete(id){
	var params = {
			"zoneId" : id
	};
	if(confirm("삭제하시겠습니까 ?")){
		rAjaxCall.async("/zone/ajaxDeleteZone", 'POST', params, function(r) {
			if(r.result){
				alert(r.content);
				getList("delete");
			}else{
				alert(r.content);
			}
		});
	}
}