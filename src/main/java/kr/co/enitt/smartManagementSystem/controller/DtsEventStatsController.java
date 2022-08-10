package kr.co.enitt.smartManagementSystem.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import kr.co.enitt.smartManagementSystem.service.CodeService;
import kr.co.enitt.smartManagementSystem.service.DtsEventService;
import kr.co.enitt.smartManagementSystem.service.DtsEventStatsService;
import kr.co.enitt.smartManagementSystem.service.ZoneService;
import kr.co.enitt.smartManagementSystem.util.DownloadUtil;
import kr.co.enitt.smartManagementSystem.util.ObjectUtil;
import kr.co.enitt.smartManagementSystem.util.StringUtil;
import kr.co.enitt.smartManagementSystem.vo.CodeVO;
import kr.co.enitt.smartManagementSystem.vo.CommonVO;
import kr.co.enitt.smartManagementSystem.vo.DtsEventStatsVO;
import kr.co.enitt.smartManagementSystem.vo.DtsEventVO;
import kr.co.enitt.smartManagementSystem.vo.ZoneVO;
 
/**
* @Project : smartManagementSystem
* @FileName : DtsEventStatsController.java
* @Author : KEJ 
* @Date : 2020. 9. 9. 
* @Description : 온도 감지 통계
* ===========================================================
* DATE                   AUTHOR                     NOTE
* -----------------------------------------------------------
* 2020. 9. 9.       KEJ      최초작성
*/
@RequestMapping("/dtsEventStats")
@Controller
public class DtsEventStatsController {
	
	@Resource(name = "dtsEventStatsService")
	private DtsEventStatsService dtsEventStatsService;
	
	@Resource(name = "dtsEventService")
	private DtsEventService dtsEventService;
	
	@Resource(name = "codeService")
	private CodeService codeService;
	
	@Resource(name = "zoneService")
	private ZoneService zoneService;
	
	
	/**
	  * @Method_Name : eventStats
	  * @retuen :ModelAndView
	  * @Date : 2020. 9. 3.
	  * @Author : KEJ
	  * @Method_Description : 온도 감지 통계
	  * ---------------------
	  * @변경이력
	  * 2020. 9. 3. KEJ : 생성
	  */
	@RequestMapping(value= {"","/"})
	public ModelAndView eventStats(HttpServletRequest request, CommonVO vo) throws Exception {
		ModelAndView mav = new ModelAndView();
		request.setCharacterEncoding("UTF-8");
		  
		// 검색조건 세팅
		setEventSetting(vo);
			
		// 일별
		List<DtsEventStatsVO> dayList = dtsEventStatsService.getEventStatsDayList(vo);
		mav.addObject("dayList", dayList);
		
		// 월별
		List<DtsEventStatsVO> monthList = dtsEventStatsService.getEventStatsMonthList(vo);
		mav.addObject("monthList", monthList);
		
		// 년별
		List<DtsEventStatsVO> yearList = dtsEventStatsService.getEventStatsYearList(vo);
		mav.addObject("yearList", yearList); 
	
		  
		// 검색(발생내용)
		CommonVO commonVO = new CommonVO();
		
		commonVO.setParentCode("LINE");
		List<CodeVO> lineSearchList = codeService.getCodeList(commonVO);
		mav.addObject("lineSearchList", lineSearchList); 
		
		commonVO.setParentCode("DTS_EVENT");
		List<CodeVO> eventSearchList = codeService.getCodeList(commonVO);
		mav.addObject("eventSearchList", eventSearchList);
		
		List<ZoneVO> zoneSearchList = zoneService.getZoneList(commonVO);
		mav.addObject("zoneSearchList", zoneSearchList);
		 
		if(ObjectUtil.isEmpty(vo.getMode())){
			vo.setMode("day");
		}
		
		mav.addObject("commonVO", vo);
		mav.setViewName("dtsEventStats/list");
		return mav;
	}
	
	/**
	  * @Method_Name : ajaxEventList
	  * @retuen :Object
	  * @Date : 2020. 9. 9.
	  * @Author : KEJ
	  * @Method_Description : 온도 감지 통계 차트
	  * ---------------------
	  * @변경이력
	  * 2020. 9. 9. KEJ : 생성
	  */
	@RequestMapping("/ajaxEventList") 
	public @ResponseBody Object ajaxEventList(HttpServletRequest request, CommonVO vo) throws Exception {
		request.setCharacterEncoding("UTF-8");
		HashMap<String,Object> result = new HashMap<String,Object>();
		
		// 검색조건 세팅
		setEventSetting(vo);
		
		// 일별
		List<DtsEventStatsVO> dayList = dtsEventStatsService.getEventStatsDayList(vo);
		result.put("dayList", dayList);
		
		// 월별
		List<DtsEventStatsVO> monthList = dtsEventStatsService.getEventStatsMonthList(vo);
		result.put("monthList", monthList);
		
		// 년별
		List<DtsEventStatsVO> yearList = dtsEventStatsService.getEventStatsYearList(vo);
		result.put("yearList", yearList);
		
		// 검색(발생내용)
		CommonVO commonVO = new CommonVO();
		commonVO.setParentCode("LINE");
		List<CodeVO> lineSearchList = codeService.getCodeList(commonVO);
		result.put("lineSearchList", lineSearchList); 
		
		return result;
	}
	
	/**
	  * @Method_Name : excelDay
	  * @retuen :void
	  * @Date : 2020. 9. 9.
	  * @Author : KEJ
	  * @Method_Description : 온도 감지 통계 일별 엑셀
	  * ---------------------
	  * @변경이력
	  * 2020. 9. 9. KEJ : 생성
	  */
	@RequestMapping("/excel/day")  
	public void excelDay(HttpServletRequest request, HttpServletResponse response, CommonVO vo) throws Exception {
		request.setCharacterEncoding("UTF-8");
		// 검색조건 세팅
		setEventSetting(vo);
		
		//일별 리스트 검색
		List<DtsEventStatsVO> list = dtsEventStatsService.getEventStatsDayList(vo);
		
		//엑셀 정보 입력
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("sheetName","온도 감지 통계_일별("+vo.getSearchDay()+")");
		map.put("fileName","온도 감지 통계_일별("+vo.getSearchDay()+")");
		map.put("eventLine",ObjectUtil.toString(vo.getEventLineDay(),""));
		map.put("eventCode",ObjectUtil.toString(vo.getEventCodeDay(),""));
		map.put("eventGroup","일");
		
		excelSetting(request, response, list, map);
		
	} 
	
	/**
	  * @Method_Name : excelMonth
	  * @retuen :void
	  * @Date : 2020. 9. 9.
	  * @Author : KEJ
	  * @Method_Description : 온도 감지 통계 월별 엑셀
	  * ---------------------
	  * @변경이력
	  * 2020. 9. 9. KEJ : 생성
	  */
	@RequestMapping("/excel/month")  
	public void excelMonth(HttpServletRequest request, HttpServletResponse response, CommonVO vo) throws Exception {
		request.setCharacterEncoding("UTF-8");
		// 검색조건 세팅
		setEventSetting(vo);
		
		//월별 리스트 검색
		List<DtsEventStatsVO> list = dtsEventStatsService.getEventStatsMonthList(vo);
		
		//엑셀 정보 입력
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("sheetName","온도 감지 통계_월별("+vo.getSearchMonth()+")");
		map.put("fileName","온도 감지 통계_월별("+vo.getSearchMonth()+")");
		map.put("eventLine",ObjectUtil.toString(vo.getEventLineMonth(),""));
		map.put("eventCode",ObjectUtil.toString(vo.getEventCodeMonth(),""));
		map.put("eventGroup","월");
		
		excelSetting(request, response, list, map);
	} 
	
	/**
	  * @Method_Name : excelYear
	  * @retuen :void
	  * @Date : 2020. 9. 9.
	  * @Author : KEJ
	  * @Method_Description : 온도 감지 통계 년별 엑셀
	  * ---------------------
	  * @변경이력
	  * 2020. 9. 9. KEJ : 생성
	  */
	@RequestMapping("/excel/year")  
	public void excelYear(HttpServletRequest request, HttpServletResponse response, CommonVO vo) throws Exception {
		request.setCharacterEncoding("UTF-8");
		// 검색조건 세팅
		setEventSetting(vo);
		
		//년별 리스트 검색
		List<DtsEventStatsVO> list = dtsEventStatsService.getEventStatsYearList(vo);
				
		//엑셀 정보 입력
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("sheetName","온도 감지 통계_년별");
		map.put("fileName","온도 감지 통계_년별");
		map.put("eventLine",ObjectUtil.toString(vo.getEventLineYear(),""));
		map.put("eventCode",ObjectUtil.toString(vo.getEventCodeYear(),""));
		map.put("eventGroup","년");
		
		excelSetting(request, response, list, map);
	} 
	
	public void excelSetting(HttpServletRequest request, HttpServletResponse response, List<DtsEventStatsVO> list, Map<String, Object> map ) throws Exception {
		Map<String, Object> excelMap = new HashMap<String, Object>();
		excelMap.put("sheetName",ObjectUtil.toString(map.get("sheetName"),""));
		excelMap.put("fileName",ObjectUtil.toString(map.get("fileName"),""));
		
		String eventLine = ObjectUtil.toString(map.get("eventLine"),"");
		String eventCode = ObjectUtil.toString(map.get("eventCode"),"");

		List<String> headerList = new ArrayList<String>();
		List<Integer> headerCellList = new ArrayList<Integer>();
		List<String> titleList = new ArrayList<String>();
		List<String> cellList = new ArrayList<String>();
		int cellCnt = 0;
		headerList.add("날짜");
		headerCellList.add(0);
		titleList.add( ObjectUtil.toString(map.get("eventGroup"),""));
		cellList.add("eventStatsTimeGroup");
		
		// 검색(발생내용)
		CommonVO commonVO = new CommonVO();
		commonVO.setParentCode("LINE");
		List<CodeVO> lineSearchList = codeService.getCodeList(commonVO);
				
		if(StringUtil.getComparison(eventLine,"LINE_01", false)){
			headerList.add(lineSearchList.get(0).getCodeName());
			if(StringUtil.getComparison(eventCode,"EVENT_01", false)){
				titleList.add("경고");
				cellList.add("eventStats0101");
				cellCnt++;
			}
			if(StringUtil.getComparison(eventCode,"EVENT_02", false)){
				titleList.add("심각");
				cellList.add("eventStats0102");
				cellCnt++;
			}
			headerCellList.add(cellCnt);
		}
		if(StringUtil.getComparison(eventLine,"LINE_02", false)){
			headerList.add(lineSearchList.get(1).getCodeName());
			if(StringUtil.getComparison(eventCode,"EVENT_01", false)){
				titleList.add("경고");
				cellList.add("eventStats0201");
				cellCnt++;
			}
			if(StringUtil.getComparison(eventCode,"EVENT_02", false)){
				titleList.add("심각");
				cellList.add("eventStats0202");
				cellCnt++;
			}
			headerCellList.add(cellCnt);
		}
		if(StringUtil.getComparison(eventLine,"LINE_03", false)){
			headerList.add(lineSearchList.get(2).getCodeName());
			if(StringUtil.getComparison(eventCode,"EVENT_01", false)){
				titleList.add("경고");
				cellList.add("eventStats0301");
				cellCnt++;
			}
			if(StringUtil.getComparison(eventCode,"EVENT_02", false)){
				titleList.add("심각");
				cellList.add("eventStats0302");
				cellCnt++;
			}
			headerCellList.add(cellCnt);
		}
		
		headerList.add("합계");
		if(StringUtil.getComparison(eventCode,"EVENT_01", false)){
			titleList.add("경고");
			cellList.add("eventStatsTotal01");
			cellCnt++;
		}
		if(StringUtil.getComparison(eventCode,"EVENT_02", false)){
			titleList.add("심각");
			cellList.add("eventStatsTotal02");
			cellCnt++;
		}
		headerCellList.add(cellCnt);

		excelMap.put("headerList",headerList);
		excelMap.put("headerCellList",headerCellList);
		excelMap.put("titleList",titleList);
		excelMap.put("cellList",cellList);
		
		DownloadUtil.ecxelDownList(request, response, excelMap, list);
	}
	
	/**
	  * @Method_Name : setEventSetting
	  * @retuen :void
	  * @Date : 2020. 9. 9.
	  * @Author : KEJ
	  * @Method_Description : 온도 감지 통계 검색조건 세팅
	  * ---------------------
	  * @변경이력
	  * 2020. 9. 9. KEJ : 생성
	  */
	public void setEventSetting(CommonVO vo) {
		
		/** 일별 **/
		//검색 조건(날짜)
		if(ObjectUtil.isEmpty(vo.getSearchDay())){
			SimpleDateFormat  day = new SimpleDateFormat("yyyy-MM");
			vo.setSearchDay(day.format(new Date()));
		}
		
		/** 년별 **/
		//검색 조건(날짜)
		if(ObjectUtil.isEmpty(vo.getSearchMonth())){
			SimpleDateFormat  Month = new SimpleDateFormat("yyyy");
			vo.setSearchMonth(Month.format(new Date()));
		}
		
		/** 년별 **/
		//검색 조건(날짜)
		if(ObjectUtil.isEmpty(vo.getSearchYear())){
			Calendar beforeYear = Calendar.getInstance();
			SimpleDateFormat  year = new SimpleDateFormat("yyyy");
			vo.setSearchYearEnd(year.format(beforeYear.getTime())); 
			
			beforeYear.add(Calendar.YEAR , -4);
			vo.setSearchYear(year.format(beforeYear.getTime())); 
			
		} 
	}
	
	/**
	  * @Method_Name : scatterPlot
	  * @retuen :ModelAndView
	  * @Date : 2021. 1. 5.
	  * @Author : ENITT_KEJ
	  * @Method_Description : 온도 감지 통계 상세 분포도 팝업 호출
	  * ---------------------
	  * @변경이력
	  * 2021. 1. 5. ENITT_KEJ : 생성
	  */
	@RequestMapping("/content/scatterPlot")
	public ModelAndView scatterPlot(HttpServletRequest request, CommonVO vo) throws Exception {
		ModelAndView mav = new ModelAndView();
		request.setCharacterEncoding("UTF-8");
		
		List<DtsEventVO> dtsEventList = dtsEventService.getDtsEventList(vo);
		mav.addObject("dtsEventList", dtsEventList);
		
		mav.setViewName("dtsEventStats/content/scatterPlot");
		return mav;
	}
	
	/**
	  * @Method_Name : ajaxScatterPlot
	  * @retuen :Object
	  * @Date : 2021. 1. 5.
	  * @Author : ENITT_KEJ
	  * @Method_Description : 온도 감지 통계 상세 분포도 차트
	  * ---------------------
	  * @변경이력
	  * 2021. 1. 5. ENITT_KEJ : 생성
	  */
	@RequestMapping("/ajaxScatterPlot") 
	public @ResponseBody Object ajaxScatterPlot(HttpServletRequest request, CommonVO vo) throws Exception {
		request.setCharacterEncoding("UTF-8");
		HashMap<String,Object> result = new HashMap<String,Object>();
		
		List<DtsEventVO> dtsEventList = dtsEventService.getDtsEventList(vo);
		result.put("dtsEventList", dtsEventList);
		
		// 검색(발생내용)
		CommonVO commonVO = new CommonVO();
		commonVO.setParentCode("LINE");
		List<CodeVO> lineSearchList = codeService.getCodeList(commonVO);
		result.put("lineSearchList", lineSearchList); 
		return result;
	}
}
