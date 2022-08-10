$(document).ready(function(){
	//온도와 장비 상태는 1분 마다 갱신
	fncTempSetting();
	setInterval(fncTempSetting, 60000);
	
	//이벤트는 발생되었을때 갱신
	fncEventCheck();
	setInterval(fncEventCheck, 3000);
});
    //온도와 장비 상태는 1분 마다 갱신
	var dashboard_zoneId = "";
	var dashboard_dtsEventId = "";
	var dashboard_dasEventId = "";
	var dashboard_dtsEventCnt = 0;
	var dashboard_dasEventCnt = 0;
	
	function fncDeleteAllEvent() {
		var now = rCommon.dateStr();
		var nowAgo12H = rCommon.dateStr_ago12H();
		var param = {
			"now" : now,
			"nowAgo12H" : nowAgo12H
		};
		
		rAjaxCall.async("/dashboard/ajaxDeleteAllEvent", 'POST', param, function(r) {
			alert(r.content);
			fncEventCheck(); 
		});	
	}
	
	function fncTempSetting(){
		equipmentState(); //대시보드 장비 상태
		fncTempChartSetting(); //전력구 온도 현황 차트
	}
	
	//이벤트는 발생되었을때 갱신
	function fncEventCheck(){
		var param = {
			"dashboard":"Y"
			,"dasLastTime" : $("#dasLastTime").val()
			,"dtsLastTime" : $("#dtsLastTime").val()
		}
		
		rAjaxCall.async("/dashboard/ajaxEventCnt", 'POST', param, function(data) {
			
			if(data.dtsNewCnt > 0){
				dtsEvent();
				//fncTempChartSetting(); //전력구 온도 현황 차트
				//dtsZoneList(); // 전력구 중요지점 현황 목록
			}else{
				if(data.dtsCnt == 0){
					tabSetting('das');	
					$('.dts_event').html("");
					$('.dts_popup').html("");
					$(".dts_popup").addClass("DiNone");
					$('.dts_popup_focus').html("");
					$(".dts_popup_focus").addClass("DiNone");
					$("#dtsTable > tbody > tr").remove();
					$("#dtsTable > tbody").append("<tr class='noData'><td colspan='5' class='TxtC'>데이터가 없습니다.</td></tr>");
				}else{
					if(dashboard_dtsEventCnt != data.dtsCnt){
						dtsEvent();
						//fncTempChartSetting(); //전력구 온도 현황 차트
						//dtsZoneList(); // 전력구 중요지점 현황 목록
					}
				}
			}
			dashboard_dtsEventCnt = data.dtsCnt;
			
			if(data.dasNewCnt > 0){
				dasEvent();
			}else{
				if(data.dasCnt == 0){
					tabSetting('dts');	
					$('.das_event').html("");
					$('.das_popup').html("");
					$(".das_popup").addClass("DiNone");
					$('.das_popup_focus').html("");
					$(".das_popup_focus").addClass("DiNone");
					$("#dasTable > tbody > tr").remove();
					$("#dasTable > tbody").append("<tr class='noData'><td colspan='4' class='TxtC'>데이터가 없습니다.</td></tr>");
				}else{
					if(dashboard_dasEventCnt != data.dasCnt){
						dasEvent();
					}
				}
			}
			dashboard_dasEventCnt = data.dasCnt;
		});
	}
	
	// 대시보드 이미지 function Start //
		// 대시보드 이미지 > 아이콘 Start //
			//장비 상태
			function equipmentState(){
				var param = {};
				rAjaxCall.asyncH("/dashboard/content/equipmentState", 'POST', param, function(data) {
					$('.equipment').html("");
					$('.equipment').html(data);
				});	
			}
			
			//중요 지점
			function zone(){
				var param = {};
				rAjaxCall.asyncH("/dashboard/content/zone", 'POST', param, function(data) {
					$('.zone').html("");
					$('.zone').html(data);
					
					var zone_fnc = $(".zone > ul").children(".selected").attr("onclick");
					if(typeof zone_fnc == "string"){
						eval(zone_fnc);
					}
				});	
			}
			
			//온도 감지 이벤트
			function dtsEvent(){
				var selectDts = dashboard_dtsEventId;
				var param = {
					"dashboard" : "Y"
					,"dashboard_dtsEventId" : selectDts
				};
				rAjaxCall.asyncH("/dashboard/content/dtsEvent", 'POST', param, function(data) {
					$('.dts_event').html("");
					$('.dts_event').html(data);
					
					param = {
							"dashboard":"Y"
					};
					rAjaxCall.asyncH("/dashboard/content/dtsEventList", 'POST', param, function(data) {
						$('#dtsListDiv').html("");
						$('#dtsListDiv').html(data);
						
						fncTempChartSetting(); //전력구 온도 현황 차트
						
						var dts_fnc = $(".dts_event > ul").children(".selected").attr("onclick"); //DTS 이벤트
						if(typeof dts_fnc == "string"){
							eval(dts_fnc);
						}
						
						var zone_fnc = $(".zone > ul").children(".selected").attr("onclick");
						if(typeof zone_fnc == "string"){
							eval(zone_fnc);
						}
						
						dashboard_dtsEventId = selectDts;
					});	
				});	
			}
			
			//진동 감지 이벤트
			function dasEvent(){
				var selectDas = dashboard_dasEventId;
				var param = {
					"dashboard" : "Y"
					,"dashboard_dasEventId" : selectDas
					,"dashboard_updateTime" : $("#dtsLastTime").val()
				};
				rAjaxCall.asyncH("/dashboard/content/dasEvent", 'POST', param, function(data) {
					$('.das_event').html("");
					$('.das_event').html(data);
					
					param = {
							"dashboard":"Y"
					};
					rAjaxCall.asyncH("/dashboard/content/dasEventList", 'POST', param, function(data) {
						$('#dasListDiv').html("");
						$('#dasListDiv').html(data);
						
						var dts_fnc = $(".dts_event > ul").children(".selected").attr("onclick"); //DTS 이벤트
						var das_fnc = $(".das_event > ul").children(".selected").attr("onclick"); //DAS 이벤트
						var das_new_fnc = $(".das_event > ul").children(".new").attr("onclick"); //DAS가 DTS보다 최신값인지 확인
						if(typeof das_fnc == "string"){
							eval(das_fnc);
						}
						//최신 값이 온도 감지 일 경우 탭 변경 
						if(typeof das_new_fnc != "string"){
							if(dashboard_dtsEventCnt > 0){
								tabSetting('dts');	
							}
						}
						dashboard_dasEventId = selectDas;
					});	
				});	
			}
		// 대시보드 이미지 > 아이콘 End //
		
		// 대시보드 이미지 > 팝업 Start //
			// 중요 지점
			function zone_popup(zoneId, zoneLine, idx){
				if(zoneLine == null || zoneLine.length < 7){
					zoneLine = "LINE_01"
				}
				var tableIndex = $("#zoneTable > tbody > tr."+zoneId+"_"+zoneLine+"_"+idx).index();
				var tr = $("#zoneTable > tbody > tr:eq("+tableIndex+")");
				var li = $(".zone > ul > li:eq("+idx+")");
				var closeYn = tr.hasClass("selected");
				
				if(closeYn){
					dashboard_dtsEventId = "";
					$(".dts_popup").addClass("DiNone");
					
					dashboard_zoneId = "";
					$(".zone_popup").addClass("DiNone");
					tr.removeClass("selected");
					li.removeClass("selected");
				}else{
					dashboard_dtsEventId = "";
					$(".dts_popup").addClass("DiNone");
					$("#dtsTable > tbody > tr ").removeClass("selected");
					$(".dts_event > ul > li").removeClass("selected");
					
					dashboard_zoneId = zoneId;
					$(".zone_popup").removeClass("DiNone");
					$("#zoneTable > tbody > tr ").removeClass("selected");
					tr.addClass("selected");
					$(".zone > ul > li").removeClass("selected");
					li.addClass("selected");
					
					var param = {"zoneId" : zoneId}
					rAjaxCall.asyncH("/dashboard/content/zonePopup", 'POST', param, function(data) {
						$('.zone_popup').html("");
						$('.zone_popup').html(data);
						
						var zoneTypeNm = tr.children("td:eq(0)").text();
						var zoneState = tr.children("td:eq(1)").text();
						var zoneLocationEnd = tr.children("td:eq(2)").text();
						var zoneNm = tr.children("td:eq(3)").text();
						var zoneLocation = tr.children("td:eq(4)").text();
						var zoneLineNm = tr.children("td:eq(5)").text();
						var zoneTemp = tr.children("td:eq(6)").text();
						var zoneStateNm = tr.children("td:eq(7)").text();
						
						$(".zone_popup").removeClass("DiNone");
						$(".zone_popup > div").text(zoneNm);
						$(".zone_popup > ul > li:eq(0)").append(zoneTypeNm);
						$(".zone_popup > ul > li:eq(1)").append(zoneLocation);
						$(".zone_popup > ul > li:eq(2)").append(zoneLocationEnd);
						$(".zone_popup > ul > li:eq(3)").append(zoneLineNm);
						$(".zone_popup > ul > li:eq(4) > p").append(zoneTemp+" ℃ /"+zoneStateNm);
						$(".zone_popup > ul > li:eq(4) > p").addClass(zoneState);
						//$(".zone_popup > ul > li:eq(5) > p").append(zoneStateNm);
						//$(".zone_popup > ul > li:eq(5) > p").addClass(zoneState);
					});	
				}
			}
			function zone_focus(zoneId, zoneLine, idx){
				if(zoneLine == null || zoneLine.length < 7){
					zoneLine = "LINE_01"
				}
				var tableIndex = $("#zoneTable > tbody > tr."+zoneId+"_"+zoneLine+"_"+idx).index();
				var tr = $("#zoneTable > tbody > tr:eq("+tableIndex+")");
				var li = $(".zone > ul > li:eq("+idx+")");
				tr.addClass("on");
				li.addClass("on");
				
				var param = {"zoneId" : zoneId}
				rAjaxCall.asyncH("/dashboard/content/zonePopup", 'POST', param, function(data) {
					$(".zone_popup_focus").removeClass("DiNone");
					$('.zone_popup_focus').html("");
					$('.zone_popup_focus').html(data);
					
					var zoneTypeNm = tr.children("td:eq(0)").text();
					var zoneState = tr.children("td:eq(1)").text();
					var zoneLocationEnd = tr.children("td:eq(2)").text();
					var zoneNm = tr.children("td:eq(3)").text();
					var zoneLocation = tr.children("td:eq(4)").text();
					var zoneLineNm = tr.children("td:eq(5)").text();
					var zoneTemp = tr.children("td:eq(6)").text();
					var zoneStateNm = tr.children("td:eq(7)").text();
					
					$(".zone_popup_focus").removeClass("DiNone");
					$(".zone_popup_focus > div").text(zoneNm);
					$(".zone_popup_focus > ul > li:eq(0)").append(zoneTypeNm);
					$(".zone_popup_focus > ul > li:eq(1)").append(zoneLocation);
					$(".zone_popup_focus > ul > li:eq(2)").append(zoneLocationEnd);
					$(".zone_popup_focus > ul > li:eq(3)").append(zoneLineNm);
					$(".zone_popup_focus > ul > li:eq(4) > p").append(zoneTemp+" ℃ /"+zoneStateNm);
					$(".zone_popup_focus > ul > li:eq(4) > p").addClass(zoneState);
					//$(".zone_popup_focus > ul > li:eq(5) > p").append(zoneStateNm);
					//$(".zone_popup_focus > ul > li:eq(5) > p").addClass(zoneState);
				});	
			}
			function zone_focus_out(zoneId, zoneLine, idx){
				if(zoneLine == null || zoneLine.length < 7){
					zoneLine = "LINE_01"
				}
				var tableIndex = $("#zoneTable > tbody > tr."+zoneId+"_"+zoneLine+"_"+idx).index();
				var tr = $("#zoneTable > tbody > tr:eq("+tableIndex+")");
				var li = $(".zone > ul > li:eq("+idx+")");
				tr.removeClass("on");
				li.removeClass("on");
				//$(".zone_popup_focus").addClass("DiNone");
				//$('.zone_popup_focus').html("")
			}
			
			
			
			// DTS 이벤트
			function dts_popup(dtsId,idx){
				var closeYn = $("#dtsTable > tbody > tr:eq("+idx+")").hasClass("selected");
				if(closeYn){
					dashboard_zoneId = "";
					$(".dts_popup").addClass("DiNone");
					
					dashboard_dtsEventId = "";
					$(".zone_popup").addClass("DiNone");
					$("#dtsTable > tbody > tr:eq("+idx+")").removeClass("selected");
					$(".dts_event > ul > li:eq("+idx+")").removeClass("selected");
	
					//확인한 이벤트 쿠키에 넣기
					//var checkDtsId = getCookie("checkDtsId");
					//checkDtsId = checkDtsId+","+dtsId;
					//setCookie('checkDtsId', checkDtsId, '1');
				}else{
					dashboard_zoneId = "";
					$(".zone_popup").addClass("DiNone");
					$(".zone > ul > li").removeClass("selected");
					$("#zoneTable > tbody > tr").removeClass("selected");
					
					dashboard_dtsEventId = dtsId;
					tabSetting('dts');
					$(".dts_popup").removeClass("DiNone");
					$("#dtsTable > tbody > tr ").removeClass("selected");
					$("#dtsTable > tbody > tr:eq("+idx+")").addClass("selected");
					$(".dts_event > ul > li").removeClass("selected");
					$(".dts_event > ul > li:eq("+idx+")").addClass("selected");
					
					var param = {"dtsEventId" : dtsId}
					rAjaxCall.asyncH("/dashboard/content/dtsPopup", 'POST', param, function(data) {
						$('.dts_popup').html("");
						$('.dts_popup').html(data);
					});	
				}
			}
			function dts_focus(dtsId, idx){
				var param = {"dtsEventId" : dtsId}
				rAjaxCall.asyncH("/dashboard/content/dtsPopup", 'POST', param, function(data) {
					$(".dts_popup_focus").removeClass("DiNone");
					$('.dts_popup_focus').html("");
					$('.dts_popup_focus').html(data);
				});	
			}
			function dts_focus_out(dtsId, idx){
				/*$(".dts_popup_focus").addClass("DiNone");
				$('.dts_popup_focus').html("")*/
			}
			
			
			// DAS 이벤트
			function das_popup(dasId,idx){
				var closeYn = $("#dasTable > tbody > tr:eq("+idx+")").hasClass("selected");
				if(closeYn){
					dashboard_dasEventId = "";
					
					$(".das_popup").addClass("DiNone");
					$("#dasTable > tbody > tr:eq("+idx+")").removeClass("selected");
					$(".das_event > ul > li:eq("+idx+")").removeClass("selected");
					
					//확인한 이벤트 쿠키에 넣기
					//var checkDasId = getCookie("checkDasId");
					//checkDasId = checkDasId+","+dasId;
					//setCookie('checkDasId', checkDasId, '1');
				}else{
					dashboard_dasEventId = dasId;
					
					tabSetting('das');
					$("#dasTable > tbody > tr ").removeClass("selected");
					$("#dasTable > tbody > tr:eq("+idx+")").addClass("selected");
					$(".das_popup").removeClass("DiNone");
					
					$(".das_event > ul > li").removeClass("selected");
					$(".das_event > ul > li:eq("+idx+")").addClass("selected");
					
					var param = {"dasEventId" : dasId}
					rAjaxCall.asyncH("/dashboard/content/dasPopup", 'POST', param, function(data) {
						$('.das_popup').html("");
						$('.das_popup').html(data);
					});	
				}
			}
			function das_focus(dasId, idx){
				var param = {"dasEventId" : dasId}
				rAjaxCall.asyncH("/dashboard/content/dasPopup", 'POST', param, function(data) {
					$(".das_popup_focus").removeClass("DiNone");
					$('.das_popup_focus').html("");
					$('.das_popup_focus').html(data);
				});	
			}
			function das_focus_out(dasId, idx){
				$(".das_popup_focus").addClass("DiNone");
				$('.das_popup_focus').html("")
			}
		//  대시보드 이미지 > 팝업 End //
		
	// 대시보드 이미지 function End //

	
			
	// 온도 감지, 진동감지 function Start 
		// 탭 선택 시 변경
		var selectTab = ""; //사용자가 직접 선택한 탭
		function tabSetting(mode, select){
			$("#statsDepth > li").removeClass("selected");
			$("#dtsListDiv").hide();
			$("#dasListDiv").hide();
			
			if(typeof select == "string"){
				selectTab = mode;
			}
			if(selectTab != ""){
				mode = selectTab;
			}
			
			if(mode == "dts"){	
				$("#statsDepth > li:eq(0)").addClass("selected");
				$("#dtsListDiv").show();
			}else if(mode == "das"){
				$("#statsDepth > li:eq(1)").addClass("selected");
				$("#dasListDiv").show();
			}
		}
		
		// 온도 감지 목록
		function dtsEventList(){
			var param = {
					"dashboard":"Y"
			};
			rAjaxCall.asyncH("/dashboard/content/dtsEventList", 'POST', param, function(data) {
				$('#dtsListDiv').html("");
				$('#dtsListDiv').html(data);
			});	
		}
		
		// 진동 감지 목록
		function dasEventList(){
			var param = {
					"dashboard":"Y"
			};
			rAjaxCall.asyncH("/dashboard/content/dasEventList", 'POST', param, function(data) {
				$('#dasListDiv').html("");
				$('#dasListDiv').html(data);
			});	
		}
	// 온도 감지, 진동감지 function End //		
			
			
			
   // 전력구 중요지점 현황 목록 function Start //
	function dtsZoneList(){
		var param = {
				"tempLimit":"Y"
		};
		rAjaxCall.asyncH("/dashboard/content/dtsZoneList", 'POST', param, function(data) {
			$('#zoneDiv').html("");
			$('#zoneDiv').html(data);
			
			/*var zone_fnc = $(".zone > ul").children(".selected").attr("onclick");
			if(typeof zone_fnc == "string"){
				eval(zone_fnc);
			}*/
			zone(); //대시보드 중요지점
		});	
	}
	// 전력구 중요지점 현황 목록 function End //
	
	
	
	// 전력구 온도현황 차트 function Start //
	function fncTempChartSetting(){
		//차트 내 줌인이 되어있는지 확인
		var zoomYn = $("[aria-label='Reset zoom']").length;
		
		if(zoomYn > 0){
			dtsZoneList(); //전력구 중요지점 현황 목록
		}else{
			var param = {
					"searchType": $("#chartSearchType").val()
			};
			rAjaxCall.async("/dashboard/ajaxTempChart", 'POST', param, function(r) {
				//업데이트 시간 
				$("#trmp_updateTime").text("업데이트 : "+r.dashboard_updateTime);
				//데이터
				var series = new Array();
				var dataList = r.dataList;
				var colorList = r.colorList;
				$(".temp_popup > ul").html("");
				for(var i = 0; i < dataList.length; i++){
					$(".temp_popup > ul").append("<li><span>"+dataList[i].lineNm+" : </span><span class='"+dataList[i].tempMinStatus+"'>"+dataList[i].tempMin+"℃</span> / <span class='"+dataList[i].tempMaxStatus+"'>"+dataList[i].tempMax+"℃ </span> </li>")
					series.push({
						 name: dataList[i].lineNm,
					     data: dataList[i].temp
					});
				}
				// 발생단계 라인
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
					,exporting : {
						enabled : false
					}
			        ,colors: colorList
				    ,title: { text: ''}
				    ,subtitle: { text: '' }
				    ,yAxis: {
				        title: {
				        	text: '온도(℃)'
				            ,style: {
				            	color: '#FFFFFF'
				            }
				        }
				    	,plotLines: plotLines
				    	,min:0
				    	,max:45
				    	,labels: {
			                style: {
			                    color: '#FFFFFF'
			                }
			            }
				    }
				    ,xAxis: {
				    	title : {
				    		text : '위치(m)'
				        	,style: {
			                    color: '#FFFFFF'
			                }
				        }
				        ,accessibility: {
				            rangeDescription: 'Range: 0 to 1235'
				        }
				        ,labels: {
			                style: {
			                    color: '#FFFFFF'
			                }
			            }
				    }
				    ,legend: {
				        layout: 'vertical'
				        ,align: 'right'
				        ,verticalAlign: 'top'
				        ,x: 0
				        ,y: 0
				        ,floating: true
				        ,borderWidth: 1
				        ,backgroundColor:Highcharts.defaultOptions.legend.backgroundColor || '#293948'
				        ,shadow: true
				        ,itemStyle: {
				        	color: '#FFFFFF'
			            }
				    	,itemHoverStyle: {
				        	color: '#FFFFFF'
			            }
				    }
				    ,plotOptions: {
				        series: {
				            label: {
				                connectorAllowed: false
				            }
				            ,pointStart: 0
				            ,pointEnd: 1235
				        }
				    }
				   /* ,tooltip: {
				        headerFormat: '<b>{series.name}</b><br>',
				        pointFormat:'구간(m) : {point.x} <br>온도(℃) : {point.y}'
				    }*/
				   ,tooltip: {
				    	shared: true, 
				    	formatter: function() {
				    		 var station = Math.floor((Number(this.x)+(6*20+11))/20);
				    		 var Remainder = (Number(this.x)+(6*20+11))%20;
				    		 var sta = '<b>STA.'+station+'+'+Remainder+'m ('+this.x+'m)</b>';
				    		 var str = '';
				    		 str += sta;
				    		 $.each(this.points, function(i, point) {
				    		 if(point.y !== 0)
				    			 str += '<br/>' + point.series.name +' : '+ point.y+' (℃)';
				    		 });
				    		 return str;
				    	}

			        }
				    ,series: series
				});
				dtsZoneList(); //전력구 중요지점 현황 목록
			});
			
		}
	}
	// 전력구 온도현황 차트 function End //