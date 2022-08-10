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
	rAjaxCall.async("/dtsEventStats/ajaxEventList", 'POST', params, function(r) {
		var eventLine = $('[name=eventLineDay]').val();
		var eventCode = $('[name=eventCodeDay]').val();
		createChart(r.dayList, eventLine, eventCode, 'dayContainer', '일', r.lineSearchList);
		
		eventLine = $('[name=eventLineMonth]').val();
		eventCode = $('[name=eventCodeMonth]').val();
		createChart(r.monthList, eventLine, eventCode, 'monthContainer', '월', r.lineSearchList);
		
		eventLine = $('[name=eventLineYear]').val();
		eventCode = $('[name=eventCodeYear]').val();
		createChart(r.yearList, eventLine, eventCode, 'yearContainer', '년', r.lineSearchList);	
	});
}

function createChart(list, eventLine, eventCode, containerNm, groupNm, lineList){
	var list =  list;
	var categories = new Array();
	var event0101 = new Array();
	var event0102 = new Array();
	var event0201 = new Array();
	var event0202 = new Array();
	var event0301 = new Array();
	var event0302 = new Array();
	var series = new Array();
	var max = 10;
	var totalcnt = 0;
	
	for(var i = 0; i < list.length; i++ ){
		categories.push(list[i].eventStatsTimeGroup+groupNm);
		event0101.push(parseInt(list[i].eventStats0101) <= 0 ? null : parseInt(list[i].eventStats0101));
		event0102.push(parseInt(list[i].eventStats0102) <= 0 ? null : parseInt(list[i].eventStats0102));
		event0201.push(parseInt(list[i].eventStats0201) <= 0 ? null : parseInt(list[i].eventStats0201));
		event0202.push(parseInt(list[i].eventStats0202) <= 0 ? null : parseInt(list[i].eventStats0202));
		event0301.push(parseInt(list[i].eventStats0301) <= 0 ? null : parseInt(list[i].eventStats0301));
		event0302.push(parseInt(list[i].eventStats0302) <= 0 ? null : parseInt(list[i].eventStats0302));
		totalcnt = Number(list[i].eventStatsTotal01)+Number(list[i].eventStatsTotal02);
		if(totalcnt > max){
			max = totalcnt;
		}
	}
	if(list.length == 0){
		event0101.push(0);
		event0102.push(0);
		event0201.push(0);
		event0202.push(0);
		event0301.push(0);
		event0302.push(0);
	}

	if(getComparison(eventLine,"LINE_01", false)){
		if(getComparison(eventCode,"EVENT_01", false)){
			series.push({
		        name: lineList[0].codeName+'(주의)',
		        data: event0101
		    });
		}
		if(getComparison(eventCode,"EVENT_02", false)){
			series.push({
		        name: lineList[0].codeName+'(경고)',
		        data: event0102
		    });
		}
	}
	if(getComparison(eventLine,"LINE_02", false)){
		if(getComparison(eventCode,"EVENT_01", false)){
			series.push({
		        name: lineList[1].codeName+'(주의)',
		        data: event0201
		    });
		}
		if(getComparison(eventCode,"EVENT_02", false)){
			series.push({
		        name: lineList[1].codeName+'(경고)',
		        data: event0202
		    });
		}
	}
	if(getComparison(eventLine,"LINE_03", false)){
		if(getComparison(eventCode,"EVENT_01", false)){
			series.push({
		        name: lineList[2].codeName+'(주의)',
		        data: event0301
		    });
		}
		if(getComparison(eventCode,"EVENT_02", false)){
			series.push({
		        name: lineList[2].codeName+'(경고)',
		        data: event0302
		    });
		}
	}
	
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
	        headerFormat: '<b>{point.x}</b><br/>'
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
	$('[name='+mode+'_form]').attr('action','/dtsEventStats/excel/'+mode);
	$('[name='+mode+'_form]').submit();
	$('[name='+mode+'_form]').attr('action','/dtsEventStats');
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
		format = '{value:%H:%M}';
		tickAmount = 24;
		tickInterval = 3600 * 1000;
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
		format = '{value:%m}';
		tickAmount = 12;
		tickInterval = 365 * 3600 * 1000;
		minTickInterval =365 * 3600 * 1000;
		min = Date.UTC(time.substr(0,4),0-1,1,0,0,0);
		max = Date.UTC(time.substr(0,4),11,31,59,59);

		val = Number(time.substr(0,4));
		before = val-1;	
		after = val+1;
	}else{
		return;
	}
	
	var mode = $("#mode").val();
	var eventLine = "";
	var eventCode = ""; 
	var eventZone = ""; 
	if(mode == "day"){
		eventLine = $('[name='+mode+'_form] [name=eventLineDay]').val();
		eventCode = $('[name='+mode+'_form] [name=eventCodeDay]').val();
		eventZone = $('[name='+mode+'_form] [name=eventZoneDay]').val();
	}else if(mode == "month"){
		eventLine = $('[name='+mode+'_form] [name=eventLineMonth]').val();
		eventCode = $('[name='+mode+'_form] [name=eventCodeMonth]').val();
		eventZone = $('[name='+mode+'_form] [name=eventZoneMonth]').val();
	}else if(mode == "year"){
		eventLine = $('[name='+mode+'_form] [name=eventLineYear]').val();
		eventCode = $('[name='+mode+'_form] [name=eventCodeYear]').val();
		eventZone = $('[name='+mode+'_form] [name=eventZoneYear]').val();
	}
	
	var params = {
		"eventTimeStart" : eventTimeStart
		,"eventTimeEnd" : eventTimeEnd
		,"eventLine" : eventLine
		,"eventCode" : eventCode
		,"eventZone" : eventZone
	}
	
	rAjaxCall.asyncH("/dtsEventStats/content/scatterPlot", 'POST', params, function(data) {
		$('#scatterPlot').html("");
		$('#scatterPlot').html(data);
		$("#scatterPlot").show();
		$("#scatterTitle").text(time);
		$("#timeBefore").attr("onClick","scatterPlot('"+before+"')");
		$("#timeAfter").attr("onClick","scatterPlot('"+after+"')");
		
		rAjaxCall.async("/dtsEventStats/ajaxScatterPlot", 'POST', params, function(r) {
			var lineList =  r.lineSearchList;
			var list =  r.dtsEventList;
			var event0101 = new Array();
			var event0102 = new Array();
			var event0201 = new Array();
			var event0202 = new Array();
			var event0301 = new Array();
			var event0302 = new Array();
			
			if(list.length == 0){
				event0101.push(null);
				event0102.push(null);
				event0201.push(null);
				event0202.push(null);
				event0301.push(null);
				event0302.push(null);
			}
			
			var location;
			var date; 
			var year; 
			var month; 
			var day; 
			var hour; 
			var minute; 
			var second; 
			
			for(var i = 0; i < list.length; i++ ){
				location 	= parseFloat(list[i].dtsEventLocation);
				year 		= Number(list[i].dtsEventTimeYear);
				month		= Number(list[i].dtsEventTimeMonth)-1;
				day 		= Number(list[i].dtsEventTimeDay);
				hour 	  	= Number(list[i].dtsEventTimeHour);
				minute 	= Number(list[i].dtsEventTimeMinute);
				second 	= Number(list[i].dtsEventTimeSecond);

				date = Date.UTC(year, month, day,  hour,  minute, second);
				if(list[i].dtsEventLine == "LINE_01"){
					if(list[i].dtsEventCode == "EVENT_01"){
						event0101.push([location, date]);
					}
					if(list[i].dtsEventCode == "EVENT_02"){
						event0102.push([location, date]);
					}
				}
				if(list[i].dtsEventLine == "LINE_02"){
					if(list[i].dtsEventCode == "EVENT_01"){
						event0201.push([location, date]);
					}
					if(list[i].dtsEventCode == "EVENT_02"){
						event0202.push([location, date]);
					}
				}
				if(list[i].dtsEventLine == "LINE_03"){
					if(list[i].dtsEventCode == "EVENT_01"){
						event0301.push([location, date]);
					}
					if(list[i].dtsEventCode == "EVENT_02"){
						event0302.push([location, date]);
					}
				}
			}
			
			
			series = new Array();
			series.push({
		        name: lineList[0].codeName+'(주의)',
		        data: event0101
		    });
			series.push({
		        name: lineList[0].codeName+'(경고)',
		        data: event0102
		    });
			series.push({
		        name: lineList[1].codeName+'(주의)',
		        data: event0201
		    });
			series.push({
		        name: lineList[1].codeName+'(경고)',
		        data: event0202
		    });
			series.push({
		        name: lineList[2].codeName+'(주의)',
		        data: event0301
		    });
			series.push({
		        name: lineList[2].codeName+'(경고)',
		        data: event0302
		    });
			
			Highcharts.chart('scatterContainer', {
			    chart: {
			        type: 'scatter',
			        zoomType: 'xy'
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
			        ,title: {
			            text: '시간'
			        }
			    }
			    /*,tooltip: {
			        headerFormat: '<b>{series.name}</b><br>',
			        pointFormat: ' 시간 : {point.y:%Y-%m-%d %H:%M:%S} <br>구간(m) : {point.x}'
			    }*/
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