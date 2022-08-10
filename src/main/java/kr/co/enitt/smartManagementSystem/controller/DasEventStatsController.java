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
import kr.co.enitt.smartManagementSystem.service.DasEventService;
import kr.co.enitt.smartManagementSystem.service.DasEventStatsService;
import kr.co.enitt.smartManagementSystem.util.DownloadUtil;
import kr.co.enitt.smartManagementSystem.util.ObjectUtil;
import kr.co.enitt.smartManagementSystem.util.StringUtil;
import kr.co.enitt.smartManagementSystem.vo.CodeVO;
import kr.co.enitt.smartManagementSystem.vo.CommonVO;
import kr.co.enitt.smartManagementSystem.vo.DasEventStatsVO;
import kr.co.enitt.smartManagementSystem.vo.DasEventVO;
 
/**
* @Project : smartManagementSystem
* @FileName : DasEventStatsController.java
* @Author : KEJ 
* @Date : 2020. 9. 9. 
* @Description : 진동 감지  통계
* ===========================================================
* DATE                   AUTHOR                     NOTE
* -----------------------------------------------------------
* 2020. 9. 9.       KEJ      최초작성
*/
@RequestMapping("/dasEventStats")
@Controller
public class DasEventStatsController {
	
	@Resource(name = "dasEventStatsService")
	private DasEventStatsService dasEventStatsService;
	
	@Resource(name = "dasEventService")
	private DasEventService dasEventService;
	
	@Resource(name = "codeService")
	private CodeService codeService;
	
	
	/**
	  * @Method_Name : eventStats
	  * @retuen :ModelAndView
	  * @Date : 2020. 9. 3.
	  * @Author : KEJ
	  * @Method_Description : 진동 감지 통계
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
		HashMap<String, Object> dayData = dasEventStatsService.getEventStatsDayList(vo);
		mav.addObject("dayData", dayData);
		
		// 월별
		HashMap<String, Object> monthData = dasEventStatsService.getEventStatsMonthList(vo);
		mav.addObject("monthData", monthData);
		
		// 년별
		HashMap<String, Object> yearData = dasEventStatsService.getEventStatsYearList(vo);
		mav.addObject("yearData", yearData); 
		  
		// 검색(발생내용)
		CommonVO commonVO = new CommonVO();
		commonVO.setParentCode("DAS_EVENT");
		List<CodeVO> eventSearchList = codeService.getCodeList(commonVO);
		mav.addObject("eventSearchList", eventSearchList);
		
		commonVO.setParentCode("TUNNEL");
		List<CodeVO> tunnelSearchList = codeService.getCodeList(commonVO);
		mav.addObject("tunnelSearchList", tunnelSearchList); 
		 
		if(ObjectUtil.isEmpty(vo.getMode())){
			vo.setMode("day");
		}
		
		mav.addObject("commonVO", vo);
		mav.setViewName("dasEventStats/list");
		return mav;
	}
	
	/**
	  * @Method_Name : ajaxEventList
	  * @retuen :Object
	  * @Date : 2020. 9. 9.
	  * @Author : KEJ
	  * @Method_Description : 진동 감지 통계 차트
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
		HashMap<String, Object> dayData = dasEventStatsService.getEventStatsDayList(vo);
		result.put("dayData", dayData);
		
		// 월별
		HashMap<String, Object> monthData = dasEventStatsService.getEventStatsMonthList(vo);
		result.put("monthData", monthData);
		
		// 년별
		HashMap<String, Object> yearData = dasEventStatsService.getEventStatsYearList(vo);
		result.put("yearData", yearData); 
		
		return result;
	}
	
	/**
	  * @Method_Name : excelDay
	  * @retuen :void
	  * @Date : 2020. 9. 9.
	  * @Author : KEJ
	  * @Method_Description : 진동 감지 통계 일별 엑셀
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
		HashMap<String, Object> dayData = dasEventStatsService.getEventStatsDayList(vo);

		//엑셀 정보 입력
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("sheetName","진동 감지 통계_일별("+vo.getSearchDay()+")");
		map.put("fileName","진동 감지 통계_일별("+vo.getSearchDay()+")");

		DownloadUtil.ecxelDownStatus(request, response, map, dayData);
	} 
	
	/**
	  * @Method_Name : excelMonth
	  * @retuen :void
	  * @Date : 2020. 9. 9.
	  * @Author : KEJ
	  * @Method_Description : 진동 감지 통계 월별 엑셀
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
		HashMap<String, Object>  monthData = dasEventStatsService.getEventStatsMonthList(vo);
		
		//엑셀 정보 입력
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("sheetName","진동 감지 통계_월별("+vo.getSearchMonth()+")");
		map.put("fileName","진동 감지 통계_월별("+vo.getSearchMonth()+")");
		
		DownloadUtil.ecxelDownStatus(request, response, map, monthData);
	} 
	
	/**
	  * @Method_Name : excelYear
	  * @retuen :void
	  * @Date : 2020. 9. 9.
	  * @Author : KEJ
	  * @Method_Description : 진동 감지 통계 년별 엑셀
	  * ---------------------
	  * @변경이력
	  * 2020. 9. 9. KEJ : 생성
	  */
	@RequestMapping("/excel/year")  
	public void excelYear(HttpServletRequest request, HttpServletResponse response, CommonVO vo) throws Exception {
		request.setCharacterEncoding("UTF-8");
		// 검색조건 세팅
		setEventSetting(vo);

		//월별 리스트 검색
		HashMap<String, Object>  yearData = dasEventStatsService.getEventStatsYearList(vo);
		
		//엑셀 정보 입력
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("sheetName","진동 감지 통계_년별");
		map.put("fileName","진동 감지 통계_년별");
		
		DownloadUtil.ecxelDownStatus(request, response, map, yearData);
	} 
	
	/**
	  * @Method_Name : setEventSetting
	  * @retuen :void
	  * @Date : 2020. 9. 9.
	  * @Author : KEJ
	  * @Method_Description : 진동 감지 통계 검색조건 세팅
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
		//발생구
		if(ObjectUtil.isEmpty(vo.getEventTunnelDay())){
			vo.setEventTunnelDay("TUNNEL_01");
		}
		
		/** 년별 **/
		//검색 조건(날짜)
		if(ObjectUtil.isEmpty(vo.getSearchMonth())){
			SimpleDateFormat  Month = new SimpleDateFormat("yyyy");
			vo.setSearchMonth(Month.format(new Date()));
		}
		//발생구
		if(ObjectUtil.isEmpty(vo.getEventTunnelMonth())){
			vo.setEventTunnelMonth("TUNNEL_01");
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
		//발생구
		if(ObjectUtil.isEmpty(vo.getEventTunnelYear())){
			vo.setEventTunnelYear("TUNNEL_01");
		}
	}
	
	/**
	  * @Method_Name : scatterPlot
	  * @retuen :ModelAndView
	  * @Date : 2021. 1. 5.
	  * @Author : ENITT_KEJ
	  * @Method_Description : 진동 감지 통계 상세 분포도 팝업 호출
	  * ---------------------
	  * @변경이력
	  * 2021. 1. 5. ENITT_KEJ : 생성
	  */
	@RequestMapping("/content/scatterPlot")
	public ModelAndView scatterPlot(HttpServletRequest request, CommonVO vo) throws Exception {
		ModelAndView mav = new ModelAndView();
		request.setCharacterEncoding("UTF-8");
		
		List<DasEventVO> dasEventList = dasEventService.getDasEventList(vo);
		mav.addObject("dasEventList", dasEventList);
		
		mav.setViewName("dasEventStats/content/scatterPlot");
		return mav;
	}
	
	/**
	  * @Method_Name : ajaxScatterPlot
	  * @retuen :Object
	  * @Date : 2021. 1. 5.
	  * @Author : ENITT_KEJ
	  * @Method_Description : 진동 감지 통계 상세 분포도 차트
	  * ---------------------
	  * @변경이력
	  * 2021. 1. 5. ENITT_KEJ : 생성
	  */
	@RequestMapping("/ajaxScatterPlot") 
	public @ResponseBody Object ajaxScatterPlot(HttpServletRequest request, CommonVO vo) throws Exception {
		request.setCharacterEncoding("UTF-8");
		HashMap<String, Object> result = new HashMap<String, Object>();
		
		List<DasEventVO> dasEventList = dasEventService.getDasEventList(vo);
		result.put("dasEventList", dasEventList);
		
		// 검색(발생내용)
		CommonVO commonVO = new CommonVO();
		commonVO.setParentCode("DAS_EVENT");
		List<CodeVO> eventSearchList = codeService.getCodeList(commonVO);
		result.put("eventSearchList", eventSearchList);
				
				
		return result;
	}
}
