$(document).ready(function(){
	var mode = $("[name=mode]").val();
	if(mode == "list"){
		$("#timeStart").datepicker(); 
		$("#timeEnd").datepicker(); 
	}else if(mode == "detail"){
		ChartSetting();
	}
});

//list
	//검색
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
	
	//페이지 이동
	function goPage(page) {
		$('[name=pageNo]').val(page);
		$('[name=list_form]').submit();
	}
	
	//엑셀 다운
	function excelDown(){
		$('[name=list_form]').attr('action','/dtsEvent/excel/list');
		$('[name=list_form]').submit();
		$('[name=list_form]').attr('action','/dtsEvent');
	}
	
	//상세화면 이동
	function goDetail(id){
		rCommon.nameSetData('dtsEventId',id);
		rCommon.nameSetData('mode',"detail");
		$('[name=list_form]').submit();
	}
	
//detail
	//목록 이동
	function goList() {
		rCommon.nameSetData('dtsEventId','');
		rCommon.nameSetData('mode',"list");
		$('[name=detail_form]').submit();
	}
	
	//온도 추이 차트 세팅
	function ChartSetting(){
		var param = {
			"dtsEventId" : $('[name=dtsEventId]').val()
		}
		rAjaxCall.async("/dtsEvent/ajaxDtsEventChart", 'POST', param, function(r) {
			var detail = r.detail; //이벤트 상세 정보
			var list = r.dtsTempList; //이벤트 1시간 동안의 온도  데이터 
			
			//차트 데이터
			var series = new Array();
			var datas = new Array();
			var temp;
			var date; 
			for(var i = 0; i< list.length; i++){
				date = list[i].time
				temp = parseFloat(list[i].temp);
				datas.push([date, temp]);
			}
			
			var series ={ name: detail.dtsEventLocation+'m',data : datas}
			
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
			
			//차트 세팅
			Highcharts.chart('eventContainer', {
				chart: {
		            backgroundColor: '#293948'
		            ,borderWidth: 0
		            ,plotBackgroundColor: '#293948'
		            ,plotShadow: false
		            ,plotBorderWidth: 0
		            ,style: {
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
				,subtitle: { text: '' }
				,yAxis: {
			        title: {
			            text: '온도(℃)'
			            ,style: {
			            	color: '#FFFFFF'
			            }
			        }
			        , plotLines: plotLines
			        , min : 0
			        , max: 60
			        ,endOnTick: true			
			        ,labels: {
		                style: {
		                    color: '#FFFFFF'
		                }
		            }
			    },
			    xAxis: {
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
		            ,tickAmount: 6//축에 그릴 눈금의 양입니다
			        ,tickInterval: 60000 * 10 //축 단위의 눈금 간격입니다
			        ,minTickInterval: 60000 * 10 // 축 값에 허용되는 최소 눈금 간격입니다
			        ,min:min
			        ,max:max
			        ,endOnTick: true			   
//		            ,plotBands: [{ // visualize the weekend
//		            	from: 25,
//		            	to: 35,
//		            	color: '#4F6070'
//		            }]
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
			    ,series: [series]
			});
		});
	}