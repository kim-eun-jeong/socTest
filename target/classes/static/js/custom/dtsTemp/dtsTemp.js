$(document).ready(function(){
	$("#search_form [name=tempDate]").datepicker(); 
	
	ChartSetting();

	//검색조건
	searchTypeChage($('#search_form [name=tempSearchType]:checked').val());
	$('#search_form [name=tempSearchType]').change(function(){
		var val = this.value;
		searchTypeChage(val);
	});
	$('#search_form [name=tempZoneCheck]').change(function(){
	});
	
	
});

	function searchTypeChage(val){
		//구간 초기화
		if(val == "locationType"){
			$("input:checkbox[name=tempZoneCheck]").prop("checked",false);
			$("#locationType").show();
			$("#zoneType").hide();
		}else if(val == "zoneType"){
			$("#search_form [name=tempLocationStart]").val("");
			$("#search_form [name=tempLocationEnd]").val("");
			$("#locationType").hide();
			$("#zoneType").show();
		}
	}
	
	//전력선 변경 시 중요구간 변경
	function lineChange(){
		var param =$("[name=search_form]").serialize();
		rAjaxCall.asyncH("/dtsTemp/content/zoneType", 'POST', param, function(data) {
			$('.zoneTypeDiv').html("");
			$('.zoneTypeDiv').html(data);
		});	
	} 
	
	//엑셀 다운
	function excelDown(){
		$('[name=list_form]').attr('action','/dtsTemp/excel/list');
		$('[name=list_form]').submit();
		$('[name=list_form]').attr('action','/dtsTemp');
	}
	//엑셀 전체 다운
	function excelAllDown(){
		$('[name=list_form]').attr('action','/dtsTemp/excel/allList');
		$('[name=list_form]').submit();
		$('[name=list_form]').attr('action','/dtsTemp');
	}
	
	function goSearch(){
		var tempLine = $("#search_form [name=tempLine]").val();
		var tempDate = $("#search_form [name=tempDate]").val();
		var tempSearchType = $("#search_form [name=tempSearchType]:checked").val();
		var tempLocationStart = $("#search_form [name=tempLocationStart]").val();
		var tempLocationEnd = $("#search_form [name=tempLocationEnd]").val();
		var tempZoneCheck = $("#search_form [name=tempZoneCheck]:checked").val();
		
		if(tempLine == null || tempLine == ''){
			alert("전력선을 선택해주세요.");
			$("#search_form [name=tempLine]").focus();
			return;
		}
		
		if(tempDate == null || tempDate == ''){
			alert("일자를 선택해주세요.");
			$("#search_form [name=tempDate]").focus();
			return;
		}
		
		if(tempSearchType == null || tempSearchType == ''){
			alert("검색조건을 선택해주세요.");
			$("#search_form [name=tempSearchType]").focus();
			return;
		}
		
		if(tempSearchType == "zoneType"){
			if(tempZoneCheck == null || tempZoneCheck == ''){
				alert("중요지점을 선택해주세요.");
				$("#search_form [name=tempZoneCheck]").focus();
				return;
			}
		}else{
			if(tempLocationStart == null || tempLocationStart == ''){
				alert("구간 시작점을 입력해주세요.");
				$("#search_form [name=tempLocationStart]").focus();
				return;
			}
			if(tempLocationEnd == null || tempLocationEnd == ''){
				alert("구간 종료점을 입력해주세요.");
				$("#search_form [name=tempLocationEnd]").focus();
				return;
			}
			if(Number(tempLocationStart) > Number(tempLocationEnd)){
				alert("시작 위치는 종료 위치보다 클 수 없습니다.");
				$("#search_form [name=tempLocationStart]").val('');
				return;
			}
			if(Number(tempLocationEnd)-Number(tempLocationStart)  > 19){
				alert("20개 미만으로 검색해주세요");
				$("#search_form [name=tempLocationStart]").val('');
				return;
			}
		}
		
		$('[name=search_form]').submit();
	}

	function ChartSetting(){
		var param = $("[name=search_form]").serialize();
		rAjaxCall.async("/dtsTemp/ajaxTempChart", 'POST', param, function(r) {
			
			var series = new Array();
			var list = r.list;
			for(var i = 0; i < list.length; i++){
				series.push({
					 name: list[i].name,
				     data: list[i].data
				});
			}
			if(series.length == 0){
				series.push({
					 name: ' ',
				     data: null
				});
			}
			
			//차트 x축 최소, 최대 시간 지정
			var min = r.minDate;
			var max = r.maxDate;
			
			//차트 온도 단계 설정
			var tempList = r.tempList;
			var plotLines = new Array();
			for (var i = 0; i < tempList.length; i++){
				var plotLine = {
			            color: tempList[i].tempColor,
			            dashStyle: 'dash',
			            width: 2,
			            value: tempList[i].tempValue,
			            label: {
			                align: 'left',
			                style: {
			                    fontStyle: 'italic'
			                },
			                x: -10
			            },
			            zIndex: 3
			    }
				plotLines.push(plotLine);
			}
			
			Highcharts.chart('tempContainer', {
				chart: {
		            backgroundColor: '#293948',
		            borderWidth: 0,
		            plotBackgroundColor: '#293948',
		            plotShadow: false,
		            plotBorderWidth: 0,
		            style: {
		                fontFamily: 'sans-serif',
		                color: '#fff'
		            }
					,zoomType: 'xy'
					,resetZoomButton: {
			            position: {
			                align: 'right', // by default
			                verticalAlign: 'top', // by default
			                x: -100,
			                y: 0
			            }
			        }
		        }
			    ,title: { text: ''}
			    ,subtitle: {text: ''}
			    ,yAxis: {
			        title: {
			            text: '온도(℃)'
		            	,style: {
			            	color: '#FFFFFF'
			            }
			        }
			        ,plotLines : plotLines
			        ,min:0
			        ,max:60
			        ,labels: {
		                style: {
		                    color: '#FFFFFF'
		                }
		            }
			    }
			    ,xAxis: {
			    	type: 'datetime'
		    		,title: {
			            text: '시간'
		            	,style: {
		                    color: '#FFFFFF'
		                }
				    }
				    ,labels: {
		        		format: "{value:%H:%M}"
	        			,style: {
		                    color: '#FFFFFF'
		                }
				    }
				    ,tickAmount: 24//축에 그릴 눈금의 양입니다
			        ,tickInterval: 60000 * 60 //축 단위의 눈금 간격입니다
			        ,minTickInterval: 60000 * 60 // 축 값에 허용되는 최소 눈금 간격입니다
			        ,min:min
			        ,max:max
			    }
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
			    ,tooltip: {
			        headerFormat: '<b>{series.name}</b><br>',
			        pointFormat: ' 시간 : {point.x:%Y-%m-%d %H:%M:%S} <br>온도(℃) : {point.y}'
			    }
			    ,responsive: {
			        rules: [{
			            condition: {
			                maxWidth: 500
			            }
			            ,chartOptions: {
			                legend: {
			                    layout: 'horizontal'
			                    ,align: 'center'
			                    ,verticalAlign: 'bottom'
			                }
			            }
			        }]
			    }
			    ,series: series
			});
			
		});
	}