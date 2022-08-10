package kr.co.enitt.smartManagementSystem.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import kr.co.enitt.smartManagementSystem.service.CodeService;
import kr.co.enitt.smartManagementSystem.service.DasEventService;
import kr.co.enitt.smartManagementSystem.service.DashboardService;
import kr.co.enitt.smartManagementSystem.service.DtsEventService;
import kr.co.enitt.smartManagementSystem.service.DtsTempService;
import kr.co.enitt.smartManagementSystem.service.ZoneService;
import kr.co.enitt.smartManagementSystem.util.ObjectUtil;
import kr.co.enitt.smartManagementSystem.util.StringUtil;
import kr.co.enitt.smartManagementSystem.vo.CodeVO;
import kr.co.enitt.smartManagementSystem.vo.CommonVO;
import kr.co.enitt.smartManagementSystem.vo.DasEventVO;
import kr.co.enitt.smartManagementSystem.vo.DashboardVO;
import kr.co.enitt.smartManagementSystem.vo.DtsEventVO;
import kr.co.enitt.smartManagementSystem.vo.DtsTempVO;
import kr.co.enitt.smartManagementSystem.vo.ZoneVO;

/**
* @Project : smartManagementSystem
* @FileName : DashboardController.java
* @Author : ENITT_KEJ
* @Date : 2020. 11. 20. 
* @Description : 대시보드
* ===========================================================
* DATE                   AUTHOR                     NOTE
* -----------------------------------------------------------
* 2020. 11. 20.       ENITT      최초작성
*/
@Controller
public class DashboardController {

	@Resource(name = "dashboardService")
	private DashboardService dashboardService;
	
	@Resource(name = "dtsEventService")
	private DtsEventService dtsEventService;

	@Resource(name = "dtsTempService")
	private DtsTempService dtsTempService;
	
	@Resource(name = "dasEventService")
	private DasEventService dasEventService;
	
	@Resource(name = "zoneService")
	private ZoneService zoneService;
	
	@Resource(name = "codeService") 
	private CodeService codeService;
	
	/**
	  * @Method_Name : main
	  * @retuen :ModelAndView
	  * @Date : 2020. 11. 20.
	  * @Author : ENITT_KEJ
	  * @Method_Description : 대시보드 화면 호출
	  * ---------------------
	  * @변경이력
	  * 2020. 11. 20. ENITT_KEJ : 생성
	  */
	@RequestMapping("/dashboard") 
	public ModelAndView main(HttpServletResponse response, HttpServletRequest request, CommonVO vo) throws Exception {
		ModelAndView mav = new ModelAndView();
		request.setCharacterEncoding("UTF-8");
		  
		// 전력구 온도 현황 차트 검색 조건
		CommonVO commonVO = new CommonVO(); 
		commonVO.setParentCode("LINE");
		List<CodeVO> lineSearchList = codeService.getCodeList(commonVO);
		mav.addObject("lineSearchList", lineSearchList);
				
		mav.setViewName("dashboard/main");    
		return mav;
	}
	
	@RequestMapping("/dashboard/ajaxEventCnt")  
	public @ResponseBody Object ajaxEventCnt(HttpServletRequest request, CommonVO vo) throws Exception {
		request.setCharacterEncoding("UTF-8");
		HashMap<String,Object> result = new HashMap<String,Object>();
		
		vo.setNow(ObjectUtil.getNow("",0)); // 현재 시간
		String timeStr = ObjectUtil.getNow("SECOND", -4);
			  
		//온도 감지 이벤트 갯수
		vo.setDtsLastTime(timeStr);
		int dtsNewCnt = dtsEventService.getDtsEventListCnt(vo);
		result.put("dtsNewCnt", dtsNewCnt);
		
		vo.setDtsLastTime("");
		int dtsCnt = dtsEventService.getDtsEventListCnt(vo);
		result.put("dtsCnt", dtsCnt);
				
		//진동 감지 이벤트 갯수
		vo.setDasLastTime(timeStr);
		int dasNewCnt = dasEventService.getDasEventListCnt(vo);
		result.put("dasNewCnt", dasNewCnt);
		
		vo.setDasLastTime("");
		int dasCnt = dasEventService.getDasEventListCnt(vo);
		result.put("dasCnt", dasCnt);
		
		return result;
	}
	
	@RequestMapping("/dashboard/content/dtsEvent")
	public ModelAndView ajaxDtsEvent(HttpServletRequest request, CommonVO vo) throws Exception {
		ModelAndView mav = new ModelAndView();
		request.setCharacterEncoding("UTF-8");
			
		//이벤트 목록		
		vo.setNow(ObjectUtil.getNow("",0)); // 현재 시간
		List<DtsEventVO> dtsEventList = dtsEventService.getDtsEventList(vo);
		mav.addObject("dtsEventList", dtsEventList);
		if(ObjectUtil.isNotEmpty(dtsEventList)) {
			//선택된게 없으면 무조건 첫번째 이벤트 띄게하기
			if(vo.getDashboard_dtsEventId() == null || vo.getDashboard_dtsEventId().equals("")) {
				vo.setDashboard_dtsEventId(dtsEventList.get(0).getDtsEventId());
			}
			//이벤트 최신값 찾기
			getUpdateTime(dtsEventList.get(0),"dtsEventTime",vo);
			vo.setDtsLastTime(dtsEventList.get(0).getDtsEventTime());
		}
	
		mav.addObject("commonVO", vo);
		
		mav.setViewName("dashboard/content/dashboard/dts_event");
		return mav;
	}
	
	@RequestMapping("/dashboard/content/dasEvent")
	public ModelAndView ajaxDasEvent(HttpServletRequest request, CommonVO vo) throws Exception {
		ModelAndView mav = new ModelAndView();
		request.setCharacterEncoding("UTF-8");
			
		//이벤트 목록		
		vo.setNow(ObjectUtil.getNow("",0)); // 현재 시간
		List<DasEventVO> dasEventList = dasEventService.getDasEventList(vo);
		mav.addObject("dasEventList", dasEventList);
		if(ObjectUtil.isNotEmpty(dasEventList)) {
			//선택된게 없으면 무조건 첫번째 이벤트 띄게하기
			if(vo.getDashboard_dasEventId() == null || vo.getDashboard_dasEventId().equals("")) {
				vo.setDashboard_dasEventId(dasEventList.get(0).getDasEventId());
			}
			//이벤트 최신값 찾기
			vo.setDashboard_order(getUpdateTime(dasEventList.get(0),"dasEventTime",vo));
			vo.setDasLastTime(dasEventList.get(0).getDasEventTime());
		}
		mav.addObject("commonVO", vo);
		mav.setViewName("dashboard/content/dashboard/das_event");
		return mav;
	}
	
	/**
	  * @Method_Name : ajaxEquipmenState
	  * @retuen :ModelAndView
	  * @Date : 2021. 1. 5.
	  * @Author : ENITT_KEJ
	  * @Method_Description : 대시보드 장비 상태
	  * ---------------------
	  * @변경이력
	  * 2021. 1. 5. ENITT_KEJ : 생성
	  */
	@RequestMapping("/dashboard/content/equipmentState")
	public ModelAndView ajaxEquipmenState(HttpServletRequest request, CommonVO vo) throws Exception {
		ModelAndView mav = new ModelAndView();
		request.setCharacterEncoding("UTF-8");
	
		//장비 상태
		vo.setNow(ObjectUtil.getNow("",0)); // 현재 시간
		List<DashboardVO> equipmentList = dashboardService.getEquipmentState(vo);
		mav.addObject("equipmentList", equipmentList);
		
		mav.setViewName("dashboard/content/dashboard/equipment_state");
		return mav;
	}
	
	/**
	  * @Method_Name : ajaxZone
	  * @retuen :ModelAndView
	  * @Date : 2021. 1. 5.
	  * @Author : ENITT_KEJ
	  * @Method_Description : 대시보드 중요지점
	  * ---------------------
	  * @변경이력
	  * 2021. 1. 5. ENITT_KEJ : 생성
	  */
	@RequestMapping("/dashboard/content/zone")
	public ModelAndView ajaxZone(HttpServletRequest request, CommonVO vo) throws Exception {
		ModelAndView mav = new ModelAndView();
		request.setCharacterEncoding("UTF-8");
			
		//중요지점
		List<ZoneVO> zoneList = zoneService.getZoneList(vo);
		mav.addObject("zoneList", zoneList);
		
		mav.setViewName("dashboard/content/dashboard/zone");
		return mav;
	}
	
	/**
	  * @Method_Name : dtsPopup
	  * @retuen :ModelAndView
	  * @Date : 2020. 11. 20.
	  * @Author : ENITT_KEJ
	  * @Method_Description : 온도감지 팝업 호출
	  * ---------------------
	  * @변경이력
	  * 2020. 11. 20. ENITT_KEJ : 생성
	  */
	@RequestMapping("/dashboard/content/dtsPopup")
	public ModelAndView dtsPopup(HttpServletRequest request, CommonVO vo) throws Exception {
		ModelAndView mav = new ModelAndView();
		request.setCharacterEncoding("UTF-8");
		
		DtsEventVO dtsEventVO = dtsEventService.getDtsEventDetail(vo);
		mav.addObject("dtsEventVO", dtsEventVO);
		
		mav.setViewName("dashboard/content/dashboard/dts_popup");
		return mav;
	}
	
	/**
	  * @Method_Name : dasPopup
	  * @retuen :ModelAndView
	  * @Date : 2020. 11. 20.
	  * @Author : ENITT_KEJ
	  * @Method_Description : 진동 감지 팝업 호출
	  * ---------------------
	  * @변경이력
	  * 2020. 11. 20. ENITT_KEJ : 생성
	  */
	@RequestMapping("/dashboard/content/dasPopup")
	public ModelAndView dasPopup(HttpServletRequest request, CommonVO vo) throws Exception {
		ModelAndView mav = new ModelAndView();
		request.setCharacterEncoding("UTF-8");
		
		DasEventVO dasEventVO = dasEventService.getDasEventDetail(vo);
		mav.addObject("dasEventVO", dasEventVO);
		
		mav.setViewName("dashboard/content/dashboard/das_popup");
		return mav;
	}
	
	/**
	  * @Method_Name : zonePopup
	  * @retuen :ModelAndView
	  * @Date : 2020. 11. 20.
	  * @Author : ENITT_KEJ
	  * @Method_Description : 전력구 중요지점 현황 팝업 호출
	  * ---------------------
	  * @변경이력
	  * 2020. 11. 20. ENITT_KEJ : 생성
	  */
	@RequestMapping("/dashboard/content/zonePopup")
	public ModelAndView zonePopup(HttpServletRequest request, CommonVO vo) throws Exception {
		ModelAndView mav = new ModelAndView();
		request.setCharacterEncoding("UTF-8");
		
		mav.setViewName("dashboard/content/dashboard/zone_popup");
		return mav;
	}
	
	/**
	  * @Method_Name : ajaxDtsEventList
	  * @retuen :ModelAndView
	  * @Date : 2020. 11. 20.
	  * @Author : ENITT_KEJ
	  * @Method_Description : 온도 감지 목록
	  * ---------------------
	  * @변경이력
	  * 2020. 11. 20. ENITT_KEJ : 생성
	  */
	@RequestMapping("/dashboard/content/dtsEventList")
	public ModelAndView ajaxDtsEventList(HttpServletRequest request, CommonVO vo) throws Exception {
		ModelAndView mav = new ModelAndView();
		request.setCharacterEncoding("UTF-8");
				
		//이벤트 목록		
		vo.setNow(ObjectUtil.getNow("",0)); // 현재 시간
		List<DtsEventVO> dtsEventList = dtsEventService.getDtsEventList(vo);
		mav.addObject("dtsEventList", dtsEventList);
		
		mav.setViewName("dashboard/content/dts_list");
		return mav;
	}
	
	/**
	  * @Method_Name : ajaxDasEventList
	  * @retuen :ModelAndView
	  * @Date : 2020. 11. 20.
	  * @Author : ENITT_KEJ
	  * @Method_Description : 진동 감지 목록
	  * ---------------------
	  * @변경이력
	  * 2020. 11. 20. ENITT_KEJ : 생성
	  */
	@RequestMapping("/dashboard/content/dasEventList")
	public ModelAndView ajaxDasEventList(HttpServletRequest request, CommonVO vo) throws Exception {
		ModelAndView mav = new ModelAndView();
		request.setCharacterEncoding("UTF-8");
			
		//이벤트 목록		
		vo.setNow(ObjectUtil.getNow("",0)); // 현재 시간
		List<DasEventVO> dasEventList = dasEventService.getDasEventList(vo);
		mav.addObject("dasEventList", dasEventList);
		
		mav.setViewName("dashboard/content/das_list");
		return mav;
	}
	
	/**
	  * @Method_Name : ajaxDtsZoneList
	  * @retuen :ModelAndView
	  * @Date : 2020. 11. 20.
	  * @Author : ENITT_KEJ
	  * @Method_Description : 전력구 중요지점 현황 목록
	  * ---------------------
	  * @변경이력
	  * 2020. 11. 20. ENITT_KEJ : 생성
	  */
	@RequestMapping("/dashboard/content/dtsZoneList")
	public ModelAndView ajaxDtsZoneList(HttpServletRequest request, CommonVO vo) throws Exception {
		ModelAndView mav = new ModelAndView();
		request.setCharacterEncoding("UTF-8");
		
		//온도 목록		
		vo.setTempLimit("Y");
		vo.setTempLine("LINE_01");
		List<DtsTempVO> line1 = dtsTempService.getDtsTempList(vo);
		if(line1.size() > 0) {//최신 업데이트 시간 찾기
			getUpdateTime(line1.get(0),"dtsTempTime",vo); 
		}
		vo.setTempLine("LINE_02");
		List<DtsTempVO> line2 = dtsTempService.getDtsTempList(vo);
		if(line2.size() > 0) {//최신 업데이트 시간 찾기
			getUpdateTime(line2.get(0),"dtsTempTime",vo); 
		}
		vo.setTempLine("LINE_03");
		List<DtsTempVO> line3 = dtsTempService.getDtsTempList(vo);
		if(line3.size() > 0) {//최신 업데이트 시간 찾기
			getUpdateTime(line3.get(0),"dtsTempTime",vo); 
		}
		
		
		//온도 이벤트 단계 목록
		List<DtsTempVO> tempList = dtsTempService.getTempList(vo);
		Float attention = null;
		Float warning = null;
		for(int i = 0; i < tempList.size(); i++) {
			if(i == 0) {
				attention = Float.parseFloat(tempList.get(i).getTempValue()); //주의(EVENT_01)
			}else {
				warning = Float.parseFloat(tempList.get(i).getTempValue()); //경고(EVENT_02)
			}
		}
		
		//각 전력선별로 중요지점 온도 값 구하기
		//중요지점
		List<ZoneVO> zoneList = zoneService.getZoneList(vo);
		List<HashMap<String,Object>> result = new ArrayList<HashMap<String,Object>>();
		String zoneLine = "";
		for(int i = 0; i < zoneList.size(); i++) {
			zoneLine = zoneList.get(i).getZoneLine();
			if(zoneLine == null || zoneLine.length() < 7) {
				if(ObjectUtil.isNotEmpty(line1)) {
					result.add(getZoneData(zoneList.get(i), line1.get(0), i, attention, warning));
				}
				if(ObjectUtil.isNotEmpty(line2)) {
					result.add(getZoneData(zoneList.get(i), line2.get(0), i, attention, warning));
				}
				if(ObjectUtil.isNotEmpty(line3)) {
					result.add(getZoneData(zoneList.get(i), line3.get(0), i, attention, warning));
				}
			}else if(zoneLine.equals("LINE_01")) {
				if(ObjectUtil.isNotEmpty(line1)) {
					result.add(getZoneData(zoneList.get(i), line1.get(0), i, attention, warning));
				}
			}else if(zoneLine.equals("LINE_02")) {
				if(ObjectUtil.isNotEmpty(line2)) {
					result.add(getZoneData(zoneList.get(i), line2.get(0), i, attention, warning));
				}
			}else if(zoneLine.equals("LINE_03")) {
				if(ObjectUtil.isNotEmpty(line3)) {
					result.add(getZoneData(zoneList.get(i), line3.get(0), i, attention, warning));
				}
			}
	
		}
		mav.addObject("dtsZoneList", result);
		
		mav.addObject("commonVO", vo);
		mav.setViewName("dashboard/content/zone_list");
		return mav;
	}
	
	/**
	  * @Method_Name : getZoneData
	  * @retuen :HashMap<String,Object>
	  * @Date : 2020. 11. 20.
	  * @Author : ENITT_KEJ
	  * @Method_Description : 중요지점 데이터 + 각 전력선의 중요지점 온도 
	  * ---------------------
	  * @변경이력
	  * 2020. 11. 20. ENITT_KEJ : 생성
	  */
	public HashMap<String,Object> getZoneData(ZoneVO zone, DtsTempVO line, int i, Float attention, Float warning) {
		HashMap<String,Object> map = new HashMap<String,Object>();
		
		map.put("zoneId", zone.getZoneId());
		map.put("zoneIndex", i);
		map.put("zoneTypeNm", zone.getZoneTypeNm());
		map.put("zoneNm", zone.getZoneName());
		map.put("zoneLocation", zone.getZoneStart());
		map.put("zoneLocationStr", getStation(zone.getZoneStart()));
		map.put("zoneLocationEnd", zone.getZoneEnd());
		map.put("zoneLocationEndStr", getStation(zone.getZoneEnd()));
		map.put("zoneLine", line.getDtsTempLine());
		map.put("zoneLineNm", line.getDtsTempLineNm());
		Float temp = (float) 0;
		int totalCnt = zone.getZoneEnd()-zone.getZoneStart()+1;
		for(i = zone.getZoneStart();  i <= zone.getZoneEnd(); i++) {
			temp += getTempValue(line.getDtsTempTemp(),i);
		}
		temp = temp/totalCnt;
		map.put("zoneTemp",String.format("%.1f",temp));
		map.put("zoneState",getTempState(temp, attention, warning));
		
		return map;
	}
	
	public String getStation(int location) {
		String result = "";
		int station = (int) (Math.floor((location+(6*20+11))/20));
		int Remainder = (location+(6*20+11))%20;
		result =  "STA."+station+"+"+Remainder+"m";
		return result;
	}
	
	/**
	  * @Method_Name : getTempValue
	  * @retuen :Float
	  * @Date : 2020. 11. 20.
	  * @Author : ENITT_KEJ
	  * @Method_Description : 통으로 들어온 온도 배열로 나누기
	  * ---------------------
	  * @변경이력
	  * 2020. 11. 20. ENITT_KEJ : 생성
	  */
	public Float getTempValue(String temp, int zoneLocation) {
		String[] tempArray = temp.split(",");
		int location = zoneLocation-1;
		Float result = null;
		if(tempArray.length >= location) {
			result = Float.parseFloat(tempArray[location]);
		}
		return result;
	}
	
	/**
	  * @Method_Name : getTempState
	  * @retuen :String
	  * @Date : 2020. 11. 20.
	  * @Author : ENITT_KEJ
	  * @Method_Description : 온도에 따른 상태값 보내기
	  * ---------------------
	  * @변경이력
	  * 2020. 11. 20. ENITT_KEJ : 생성
	  */
	public String getTempState(Float temp, Float attention, Float warning) {
		String result = "";
		if(attention <= temp && warning > temp) {
			result = "attention";
		}else if (warning <= temp) {
			result = "warning";
		}else {
			result = "normal";
		}
		return result;
	}
	
	/**
	  * @Method_Name : ajaxTempChart
	  * @retuen :Object
	  * @Date : 2020. 11. 20.
	  * @Author : ENITT_KEJ
	  * @Method_Description : 전력구 온도 현황 차트 데이터
	  * ---------------------
	  * @변경이력
	  * 2020. 11. 20. ENITT_KEJ : 생성
	  */
	@RequestMapping("/dashboard/ajaxTempChart")  
	public @ResponseBody Object ajaxTempChart(HttpServletRequest request, CommonVO vo) throws Exception {
		request.setCharacterEncoding("UTF-8");
		HashMap<String,Object> result = new HashMap<String,Object>();
		
		List<HashMap<String,Object>> dataList = new ArrayList<HashMap<String,Object>>();
		
		//최근값 1개만 뽑아오기
		vo.setTempLimit("Y");
		String searchType = vo.getSearchType();
		List<DtsTempVO> dtsTempList = new ArrayList<DtsTempVO>();
		List<String> colorList = new ArrayList<String>();
		
		if(StringUtil.getComparison(searchType,"LINE_01", false)){
			vo.setTempLine("LINE_01");
			dtsTempList = dtsTempService.getDtsTempList(vo);	
			tempChartSetting(dtsTempList, dataList, vo);
			if(dtsTempList.size() > 0){
				getUpdateTime(dtsTempList.get(0),"dtsTempTime",vo);
			}
			colorList.add("#00FFC5");
		}
		if(StringUtil.getComparison(searchType,"LINE_02", false)){	
			vo.setTempLine("LINE_02");
			dtsTempList = dtsTempService.getDtsTempList(vo);	
			tempChartSetting(dtsTempList, dataList, vo);
			if(dtsTempList.size() > 0){
				getUpdateTime(dtsTempList.get(0),"dtsTempTime",vo);
			}
			colorList.add("#0072ff");
		}
		if(StringUtil.getComparison(searchType,"LINE_03", false)){		
			vo.setTempLine("LINE_03");
			dtsTempList = dtsTempService.getDtsTempList(vo);	
			tempChartSetting(dtsTempList, dataList, vo);
			if(dtsTempList.size() > 0){
				getUpdateTime(dtsTempList.get(0),"dtsTempTime",vo);
			}
			colorList.add("#FF00EE");
		}
		result.put("dataList",dataList);
		result.put("colorList",colorList);
		
		//업데이트 시간
		result.put("dashboard_updateTime",vo.getDashboard_updateTime());
		
		//온도 이벤트 단계 목록
		List<DtsTempVO> tempList = dtsTempService.getTempList(vo);
		result.put("tempList",tempList);
		
		
		return result;
	}
	
	/**
	  * @Method_Name : tempChartSetting
	  * @retuen :List<HashMap<String,Object>>
	  * @Date : 2020. 11. 20.
	  * @Author : ENITT_KEJ
	  * @Method_Description : 차트 데이터 세팅
	  * ---------------------
	  * @변경이력
	  * 2020. 11. 20. ENITT_KEJ : 생성
	  */
	public List<HashMap<String,Object>> tempChartSetting(List<DtsTempVO> dtsTempList, List<HashMap<String,Object>> dataList, CommonVO vo) throws Exception{
		HashMap<String,Object> map = new HashMap<String,Object>();
		String lineNm = "";
		String temp = "";
		String line = "";
		String time = "";
		if(ObjectUtil.isNotEmpty(dtsTempList)) {
			map = new HashMap<String,Object>();
			line = dtsTempList.get(0).getDtsTempLine();
			lineNm = dtsTempList.get(0).getDtsTempLineNm();
			time = dtsTempList.get(0).getDtsTempTime();
			temp = dtsTempList.get(0).getDtsTempTemp().replaceAll(" ", "");
			String[] strings = temp.split(","); 
			List<Float> nums = new ArrayList<Float>();
			String num;
			float tempVal = 0;
			float min = 100;
			float max = 0;
			for(int j = 0; j < strings.length; j++) {
				tempVal = Float.parseFloat(String.format("%.1f",Float.parseFloat(strings[j]))); 
				if(min > tempVal) {
					min = tempVal;
				}
				if(max < tempVal) {
					max = tempVal;
				}
				nums.add(tempVal);
			}
			
			map.put("line", line);
			map.put("lineNm", lineNm);
			map.put("temp", nums);
			map.put("time", time);
			map.put("tempMin", min);
			map.put("tempMax", max);
			if(min >= 35 && min < 40 ) {
				map.put("tempMinStatus", "attention");
			}else if(min >= 40) {
				map.put("tempMinStatus", "warning");
			}else {
				map.put("tempMinStatus", "normal");
			}
			
			if(max >= 35 && max < 40 ) {
				map.put("tempMaxStatus", "attention");
			}else if(max >= 40) {
				map.put("tempMaxStatus", "warning");
			}else {
				map.put("tempMaxStatus", "normal");
			}
		}else {
			//라인 정보
			vo.setParentCode("LINE");
			vo.setCodeId(vo.getTempLine());
			List<CodeVO> lineList = codeService.getCodeList(vo);
			if(ObjectUtil.isNotEmpty(lineList)) {
				map = new HashMap<String,Object>();
				map.put("line", lineList.get(0).getCodeId());
				map.put("lineNm", lineList.get(0).getCodeName());
				map.put("tempMin", "0");
				map.put("tempMax", "0");
			}
		}
			
		dataList.add(map);
		
		return dataList;
	}
	
	/**
	  * @Method_Name : getUpdateTime
	  * @retuen :boolean
	  * @Date : 2020. 11. 20.
	  * @Author : ENITT_KEJ
	  * @Method_Description : 최신 업데이트 시간 가져오기
	  * ---------------------
	  * @변경이력
	  * 2020. 11. 20. ENITT_KEJ : 생성
	  */
	public boolean getUpdateTime(Object obj, String str, CommonVO vo) throws ParseException{
		boolean result = false;
		if(ObjectUtil.isNotEmpty(obj)) {
			String time = ObjectUtil.getMapValue(obj,str);
			//업데이트 시간 최신 값
			if(ObjectUtil.isNotEmpty(vo.getDashboard_updateTime())) {
				SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				 Date d1 = f.parse(time);
				 Date d2 = f.parse(vo.getDashboard_updateTime());

				 if(d1.compareTo(d2) >= 0) {
					 vo.setDashboard_updateTime(f.format(d1));
					 result = true;
				 }
			}else {
				vo.setDashboard_updateTime(time);
			}
		}
		return result;
		
	}
	
	/**
	  * @Method_Name : ajaxDeleteAllEvent
	  * @retuen : Object
	  * @Date : 2022. 05. 16.
	  * @Author : ENITT_KHR
	  * @Method_Description : 대시보드 모든 알림 삭제 (화면상에서만)
	  * ---------------------
	  * @변경이력
	  * 2022. 05. 16. ENITT_KHR : 생성
	  */
	@RequestMapping("/dashboard/ajaxDeleteAllEvent")
	public @ResponseBody Object ajaxDeleteAllEvent(CommonVO vo) throws Exception {	
		List<DasEventVO> dasEventList = new ArrayList<DasEventVO>();
		List<DtsEventVO> dtsEventList = new ArrayList<DtsEventVO>();
		
		try {
			
			//DAS event 삭제
			dasEventList = dasEventService.getDeleteDasEventList(vo);
			if(dasEventList.size() != 0) {
				dasEventService.setUpdateDasEvent(dasEventList);
			}
			
			//DTS event 삭제
			dtsEventList = dtsEventService.getDeleteDtsEventList(vo);
			if(dtsEventList.size() != 0) {
				dtsEventService.setUpdateDtsEvent(dtsEventList);
			}
			
			vo.setContent("모든 알림이 삭제되었습니다.");
			vo.setResult(true);
		} catch(Exception e) { 
			vo.setContent("오류가 발생했습니다.");
			vo.setResult(false);
		}
		return vo;
	}
}
