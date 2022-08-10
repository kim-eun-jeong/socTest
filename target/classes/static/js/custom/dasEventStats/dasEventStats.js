$(document).ready(function(){
	tabSetting($("#mode").val());
	chartSetting();
	monthPicker("#searchDay");
	yearPicker("#searchMonth",$("#searchMonth").val());
	yearPicker("#searchYear",$("#searchYear").val());
	yearPicker("#searchYearEnd",$("#searchYearEnd").val());
	
	//레이어 팝업 백그라운드 클릭 시 팝업 닫기
	 $(document).mouseup(function (e){
		var container = $('.pop_overlay');
		if( container.has(e.target).length === 0){
			popClose('scatterPlot');
		}
	 });
});

function tabSetting(mode){
	$("#mode").val(mode);
	$("#statsDepth > li:eq(0)").attr("class", "");
	$("#statsDepth > li:eq(1)").attr("class", "");
	$("#statsDepth > li:eq(2)").attr("class", "");
	$("#eventDay").hide();
	$("#eventMonth").hide();
	$("#eventYear").hide();
	
	if(mode == "day"){	
		$("#statsDepth > li:eq(0)").attr("class", "selected");
		$("#eventDay").show();
	}else if(mode == "month"){
		$("#statsDepth > li:eq(1)").attr("class", "selected");
		$("#eventMonth").show();
	}else if(mode == "year"){
		$("#statsDepth > li:eq(2)").attr("class", "selected");
		$("#eventYear").show();
	}
}

function chartSetting(){
	var params = $("[name=day_form]").serialize();
	rAjaxCall.async("/dasEventStats/ajaxEventList", 'POST', params, function(r) {
		createChart(r.dayData, 'dayContainer', '일');
		createChart(r.monthData, 'monthContainer', '월'); 
		createChart(r.yearData, 'yearContainer', '년');	
	});
}

function createChart(data, containerNm, groupNm){
	var categories = data.categories;
	var series = data.data;
	var max = data.max;
	
	Highcharts.chart(containerNm, {
	    chart: {
            backgroundColor: '#293948'
            ,borderWidth: 0
            ,plotBackgroundColor: '#293948'
            ,plotShadow: false
            ,plotBorderWidth: 0
            ,style: {
                color: '#FFFFFF'
            }
	    	,type: 'column'
        }
        ,title: {text: ''}
	    ,xAxis: {
	        categories: categories
	        ,labels: {
                style: {
                    color: '#FFFFFF'
                }
            }
	    }
	    ,yAxis: {
	        min: 0
	        ,max: max
	        ,title: {text: ''}
	        ,labels: {
	            style: {
	                color: '#FFFFFF'
	            }
	        }
	    }
	    ,tooltip: {
	        headerFormat: '<b>{point.x}'+groupNm+'</b><br/>'
	        ,pointFormat: '{series.name}: {point.y}<br/>Total: {point.stackTotal}'
	    }
	    ,plotOptions: {
	        column: {
	            stacking: 'normal',
	            dataLabels: {
	                enabled: true
	            }
	        }
	    }
	    ,series: series
	    ,legend: {
	        layout: 'vertical'
	        ,align: 'right'
	        ,verticalAlign: 'top'
	        ,x: 0
	        ,y: 0
	        ,floating: true
	        ,borderWidth: 1
	        ,backgroundColor: Highcharts.defaultOptions.legend.backgroundColor || '#293948'
	        ,shadow: true
	        ,itemStyle: {
                   color: '#FFFFFF'
            }
		    ,itemHoverStyle: {
	        	color: '#FFFFFF'
	        }
	    }
	});
}

function excelDown(mode){
	$('[name='+mode+'_form]').attr('action','/dasEventStats/excel/'+mode);
	$('[name='+mode+'_form]').submit();
	$('[name='+mode+'_form]').attr('action','/dasEventStats');
}

function scatterPlot(time){
	var eventTimeStart = "";
	var eventTimeEnd = "";
	var tickInterval;
	var format;
	var tickAmount;
	var minTickInterval;
	var min;
	var max;
	var before;
	var after;
	var val;
	
	if(time.length == 10){
		eventTimeStart = time;
		eventTimeEnd = time;
		//하이차트 속성
		tickInterval = 3600 * 1000;
		format = '{value:%H:%M}';
		tickAmount = 24;
		minTickInterval = 3600 * 1000;
		min = Date.UTC(time.substr(0,4),Number(time.substr(5,2))-1,time.substr(8,2),0,0,0);
		max = Date.UTC(time.substr(0,4),Number(time.substr(5,2))-1,time.substr(8,2),23,59,59);
		
		val = Number(time.substr(8,2));
		before = val-1;	
		after = val+1;
		val = Number(time.substr(5,2));
		if(before < 10){
			if(before == 0){
				before = "";
			}else{
				before = time.substr(0,8)+"0"+before;
			}
		}else{
			before = time.substr(0,8)+before;
		}
		
		if(after < 10){
			after = time.substr(0,8)+"0"+after;
		}else if(after > 28){
			if(val == 2){
				after = "";
			}else if(val == 12 || val == 1 || val == 3 || val == 5 || val == 7 || val == 8 || val == 10){
				 if(after > 31){
					 after = "";
				 }else{
					 after = time.substr(0,8)+after;
				 }
			}else{
				 if(after > 30){
					 after = "";
				 }else{
					 after = time.substr(0,8)+after;
				 }
			}
		}else{
			after = time.substr(0,8)+after;
		}
		
	}else if(time.length == 7){
		var timeArray = time.split("-");
		var lastDay = ( new Date( timeArray[0], timeArray[1], 0) ).getDate();
		eventTimeStart = time+"-01";
		eventTimeEnd = time+"-"+lastDay;
		//하이차트 속성
		tickInterval = lastDay * 3600 * 1000;
		format = '{value:%d}';
		tickAmount = lastDay;
		minTickInterval = lastDay * 3600 * 1000;
		min = Date.UTC(time.substr(0,4),Number(time.substr(5,2))-1,1,0,0,0);
		max = Date.UTC(time.substr(0,4),Number(time.substr(5,2))-1,lastDay,23,59,59);
		
		val = Number(time.substr(5,2));
		before = val-1;	
		after = val+1;
		if(before < 10){
			if(before == 0){
				before = "";
			}else{
				before = time.substr(0,5)+"0"+before;
			}
		}else{
			before = time.substr(0,5)+before;
		}
		
		if(after < 10){
			after = time.substr(0,5)+"0"+after;
		}else if(after > 12){
			after = "";
		}else{
			after = time.substr(0,5)+after;
		}
		
	}else if(time.length == 4){
		eventTimeStart = time+"-01-01";
		eventTimeEnd = time+"-12-31";
		//하이차트 속성
		tickInterval = 365 * 3600 * 1000;
		format = '{value:%m}';
		tickAmount = 12;
		minTickInterval = 365 * 3600 * 1000;
		min = Date.UTC(time.substr(0,4),0-1,1,0,0,0);
		max = Date.UTC(time.substr(0,4),11,31,59,59);
		
		val = Number(time.substr(0,4));
		before = val-1;	
		after = val+1;
		
		
	}else{
		return;
	}
	
	var mode = $("#mode").val();
	var eventTunnel = "";
	var eventCode = ""; 
	if(mode == "day"){
		eventTunnel = $('[name='+mode+'_form] [name=eventTunnelDay]').val();
		eventCode = $('[name='+mode+'_form] [name=eventCodeDay]').val();
	}else if(mode == "month"){
		eventTunnel = $('[name='+mode+'_form] [name=eventTunnelMonth]').val();
		eventCode = $('[name='+mode+'_form] [name=eventCodeMonth]').val();
	}else if(mode == "year"){
		eventTunnel = $('[name='+mode+'_form] [name=eventTunnelYear]').val();
		eventCode = $('[name='+mode+'_form] [name=eventCodeYear]').val();
	}
	
	var params = {
		"eventTimeStart" : eventTimeStart
		,"eventTimeEnd" : eventTimeEnd
		,"eventTunnel" : eventTunnel
		,"eventCode" : eventCode
	}
	
	rAjaxCall.asyncH("/dasEventStats/content/scatterPlot", 'POST', params, function(data) {
		$('#scatterPlot').html("");
		$('#scatterPlot').html(data);
		$("#scatterPlot").show();
		$("#scatterTitle").text(time);
		$("#timeBefore").attr("onClick","scatterPlot('"+before+"')");
		$("#timeAfter").attr("onClick","scatterPlot('"+after+"')");
		
		rAjaxCall.async("/dasEventStats/ajaxScatterPlot", 'POST', params, function(r) {
			var eventList =  r.eventSearchList;
			var list =  r.dasEventList;
			var event01 = new Array();
			var event02 = new Array();
			var event03 = new Array();
			var event04 = new Array();
			
			if(list.length == 0){
				event01.push(null);
				event02.push(null);
				event03.push(null);
				event04.push(null);
			}

			for(var i = 0; i < list.length; i++ ){
				var date = list[i].dasEventTime;
				date = Date.UTC(date.substr(0,4),Number(date.substr(5,2))-1,date.substr(8,2),date.substr(11,2),date.substr(14,2),date.substr(17,2));
				
				if(list[i].dasEventCode == "EVENT_01"){
					event01.push([parseFloat(list[i].dasEventLocation),date]);
					//event01.push([parseFloat(list[i].dasEventLocation),Date.parse(list[i].dasEventTime)]);
				}
				if(list[i].dasEventCode == "EVENT_02"){
					event02.push([parseFloat(list[i].dasEventLocation),date]);
				}
				if(list[i].dasEventCode == "EVENT_03"){
					event03.push([parseFloat(list[i].dasEventLocation),date]);
				}
				if(list[i].dasEventCode == "EVENT_04"){
					event04.push([parseFloat(list[i].dasEventLocation),date]);
				}
				
			}
			
			series = new Array();
			
			if(eventList.length > 0){
				series.push({
			        name: eventList[0].codeName,
			        data: event01
			    });
			}
			if(eventList.length > 1){
				series.push({
			        name: eventList[1].codeName,
			        data: event02
			    });
			}
			if(eventList.length > 3){
				series.push({
			        name: eventList[2].codeName,
			        data: event03
			    });
			}
			if(eventList.length > 4){
				series.push({
			        name: eventList[3].codeName,
			        data: event04
			    });
			}
			Highcharts.chart('scatterContainer', {
			    chart: {
			        type: 'scatter'
			        ,zoomType: 'xy'
			    }
			    ,title: { text: ''}
			    ,xAxis: {
			        title: {
			            enabled: true
			            ,text: '구간(m)'
			        }
			        ,startOnTick: true
			        ,endOnTick: true
			        ,showLastLabel: true
			        ,min:0
			        ,max:1235
			    }
			    ,yAxis: {
			        type: 'datetime'
		        	,labels: {
		        		format: format
				    }
			    	,tickAmount: tickAmount//축에 그릴 눈금의 양입니다
			        ,tickInterval: tickInterval //축 단위의 눈금 간격입니다
			        ,minTickInterval: minTickInterval // 축 값에 허용되는 최소 눈금 간격입니다
			        ,min:min
			        ,max:max
			        ,endOnTick: false
			        ,startOnTick: false
			        ,title: {
			            text: '시간'
			        }
			    }
			    ,tooltip: {
			    	shared: true, 
			    	formatter: function() {
			    		 var station = Math.floor((Number(this.x)+(6*20+11))/20);
			    		 var Remainder = (Number(this.x)+(6*20+11))%20;
			    		 var str = '';
			    		 str += '<b>'+series[this.colorIndex].name+'</b>'
			    		 str += '<br/>구간 : STA.'+station+'+'+Remainder+'m ('+this.x+'m)';
				    	 str += '<br/>시간 : ' + ' : '+ rCommon.dateUTCStr(this.y);
			    		 return str;
			    	}
		        }
			    ,series: series
			    
			});
		});
	});	
}