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
	rAjaxCall.asyncH("/temp/content/list", 'POST', params, function(data) {
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
		"tempId" : id
	};
	rAjaxCall.asyncH("/temp/content/detail", 'POST', param, function(data) {
		$('#detailDiv').html("");
		$('#detailDiv').html(data);
	});	
}

function goSave(){
	var tempId = $("[name=tempId]").val();
	var tempName = $("[name=tempName]").val();
	var tempValue = $("[name=tempValue]").val();
	var tempColor = $("[name=tempColor]").val();
	var tempOrder = $("[name=tempOrder]").val();
	
	if(tempName == "" || tempName == null){
		alert("단계명을 입력해주세요.");
		$("[name=tempName]").focus();
		return;
	}
	if(tempValue == "" || tempValue == null){
		alert("온도℃를 입력해주세요.");
		$("[name=tempValue]").focus();
		return;
	}
	if(tempColor == "" || tempColor == null){
		alert("색깔을 입력해주세요.");
		$("[name=tempColor]").focus();
		return;
	}
	if(tempOrder == "" || tempOrder == null){
		alert("순서를 입력해주세요.");
		$("[name=tempOrder]").focus();
		return;
	}
	
	if(confirm("저장하시겠습니까 ?")){
		var params = $("[name=detail_form]").serialize();
		if(tempId == "" || tempId == null){
			rAjaxCall.async("/temp/ajaxCreateTemp", 'POST', params, function(r) {
				if(r.result){
					alert(r.content);
					getList();
				}else{
					alert(r.content);
				}
			});
		}else{
			rAjaxCall.async("/temp/ajaxUpdateTemp", 'POST', params, function(r) {
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
			"tempId" : id
	};
	if(confirm("삭제하시겠습니까 ?")){
		rAjaxCall.async("/temp/ajaxDeleteTemp", 'POST', params, function(r) {
			if(r.result){
				alert(r.content);
				getList("delete");
			}else{
				alert(r.content);
				getList("insert");
			}
		});
	}
}