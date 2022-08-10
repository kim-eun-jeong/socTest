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
import kr.co.enitt.smartManagementSystem.service.DtsTempService;
import kr.co.enitt.smartManagementSystem.service.ZoneService;
import kr.co.enitt.smartManagementSystem.util.DownloadUtil;
import kr.co.enitt.smartManagementSystem.util.ObjectUtil;
import kr.co.enitt.smartManagementSystem.vo.CodeVO;
import kr.co.enitt.smartManagementSystem.vo.CommonVO;
import kr.co.enitt.smartManagementSystem.vo.DtsEventVO;
import kr.co.enitt.smartManagementSystem.vo.DtsTempVO;
import kr.co.enitt.smartManagementSystem.vo.ZoneVO;

/**
* @Project : smartManagementSystem
* @FileName : DtsEventController.java
* @Author : ENITT_KEJ
* @Date : 2020. 8. 26. 
* @Description : DTS 이벤트 관리
* ===========================================================
* DATE                   AUTHOR                     NOTE
* -----------------------------------------------------------
* 2020. 8. 26.       ENITT_KEJ      최초작성
*/
@RequestMapping("/dtsEvent")
@Controller
public class DtsEventController {
	
	@Resource(name = "dtsEventService")
	private DtsEventService dtsEventService;
	
	@Resource(name = "dtsTempService")
	private DtsTempService dtsTempService;

	@Resource(name = "codeService")
	private CodeService codeService;
	
	@Resource(name = "zoneService")
	private ZoneService zoneService;
	
	DownloadUtil downloadUtil;
	
	/**
	  * @Method_Name : list
	  * @retuen :ModelAndView
	  * @Date : 2020. 8. 26.
	  * @Author : KEJ
	  * @Method_Description : 온도 감지 이력 목록 
	  * ---------------------
	  * @변경이력
	  * 2020. 8. 26. KEJ : 생성
	  */ 
	@RequestMapping(value= {"","/"})
	public ModelAndView list(HttpServletRequest request, CommonVO vo) throws Exception {
		ModelAndView mav = new ModelAndView();
		request.setCharacterEncoding("UTF-8");
		
		if(ObjectUtil.isNotEmpty(vo.getDashboard())) {
			vo.setNow(ObjectUtil.getNow("",0)); // 현재 시간
		}
		//이벤트 목록		
		int Cnt = dtsEventService.getDtsEventListCnt(vo);
		vo.setTotalCount(Cnt);
		List<DtsEventVO> list = dtsEventService.getDtsEventPagingList(vo);
		mav.addObject("dtsEventList", list);
		
		//검색 조건
		CommonVO commonVO = new CommonVO(); 
		
		commonVO.setParentCode("DTS_EVENT");
		List<CodeVO> eventSearchList = codeService.getCodeList(commonVO);
		mav.addObject("eventSearchList", eventSearchList);
		
		commonVO.setParentCode("LINE");
		List<CodeVO> lineSearchList = codeService.getCodeList(commonVO);
		mav.addObject("lineSearchList", lineSearchList);

		List<ZoneVO> zoneSearchList = zoneService.getZoneList(commonVO);
		mav.addObject("zoneSearchList", zoneSearchList);
		
		mav.addObject("commonVO", vo);
		mav.setViewName("dtsEvent/list");
		return mav;
	}
	
	/**
	  * @Method_Name : excelList
	  * @retuen :ModelAndView
	  * @Date : 2020. 9. 9.
	  * @Author : KEJ
	  * @Method_Description : 온도 감지 이력 목록 엑셀
	  * --------------------- 
	  * @변경이력
	  * 2020. 9. 9. KEJ : 생성
	  */
	@RequestMapping("/excel/list")  
	public void excelList(HttpServletRequest request, HttpServletResponse response, CommonVO vo) throws Exception {
		request.setCharacterEncoding("UTF-8");

		if(ObjectUtil.isNotEmpty(vo.getDashboard())) {
			vo.setNow(ObjectUtil.getNow("",0)); // 현재 시간
		}
		
		List<DtsEventVO> list = dtsEventService.getDtsEventList(vo);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("sheetName","온도감지 목록");
		map.put("fileName","온도감지 목록");
		map.put("title","시간,발생단계,구간,중요지점,전력선,온도(℃)");
		map.put("cell","dtsEventTime,dtsEventCodeNm,dtsEventLocationStr,dtsEventZoneNm,dtsEventLineNm,dtsEventTemp");
		
		DownloadUtil.ecxelDown(request, response, map, list,"Y");
	} 
	
	/**
	  * @Method_Name : detail
	  * @retuen :ModelAndView
	  * @Date : 2021. 1. 5.
	  * @Author : ENITT_KEJ
	  * @Method_Description : 온도 감지 이력 상세 정보
	  * ---------------------
	  * @변경이력
	  * 2021. 1. 5. ENITT_KEJ : 생성
	  */
	@RequestMapping(value= {"","/"}, params="mode=detail")  
	public ModelAndView detail(HttpServletRequest request, CommonVO vo) throws Exception {
		ModelAndView mav = new ModelAndView();
		request.setCharacterEncoding("UTF-8");
		
		DtsEventVO detail = dtsEventService.getDtsEventDetail(vo);
		mav.addObject("detail", detail);
		
		mav.addObject("commonVO", vo);
		mav.setViewName("dtsEvent/detail"); 
		return mav;
	} 
	
	
	/**
	  * @Method_Name : ajaxDtsEventChart
	  * @retuen :Object
	  * @Date : 2021. 1. 5.
	  * @Author : ENITT_KEJ
	  * @Method_Description : 온도 감지 이력 상세 차트
	  * ---------------------
	  * @변경이력
	  * 2021. 1. 5. ENITT_KEJ : 생성
	  */
	@RequestMapping("/ajaxDtsEventChart") 
	public @ResponseBody Object ajaxDtsEventChart(HttpServletRequest request, CommonVO vo) throws Exception {
		HashMap<String,Object> result = new HashMap<String,Object>();
		
		DtsEventVO detail = dtsEventService.getDtsEventDetail(vo);
		result.put("detail",detail);
		
		if(ObjectUtil.isNotEmpty(detail)) {
			int location = ObjectUtil.toInt(detail.getDtsEventLocation(),0);
			String tunnel = ObjectUtil.toString(detail.getDtsEventTunnel(),"");
			String line = ObjectUtil.toString(detail.getDtsEventLine(),"");
			String time = ObjectUtil.toString(detail.getDtsEventTime(),"");
			vo.setTempTunnel(tunnel);
			vo.setTempLine(line);
			vo.setTempTime(time); 
			
			List<DtsTempVO> dtsTempList = dtsTempService.getDtsTempList(vo);
			
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date day = dateFormat.parse(time);
			Calendar cal = Calendar.getInstance();
			cal.setTime(day);
			cal.add(Calendar.MINUTE, -30); //이벤트 30분 전
			result.put("minDate", ObjectUtil.getUTC(dateFormat.format(cal.getTime())));
			cal.add(Calendar.MINUTE, 60); //이벤트 30분 후
			result.put("maxDate", ObjectUtil.getUTC(dateFormat.format(cal.getTime())));
			
			List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
			Map<String,Object> map = new HashMap<String,Object>();
			for(int j = 0; j < dtsTempList.size(); j++) {
				map = new HashMap<String,Object>();
				String[] temp = dtsTempList.get(j).getDtsTempTemp().split(",");
				map.put("temp",temp[location]);  
				map.put("time", ObjectUtil.getUTC(dtsTempList.get(j).getDtsTempTime()));
				list.add(map);
			}
			
			result.put("dtsTempList",list);
			
			//온도 단계 설정
			List<DtsTempVO> tempList = dtsTempService.getTempList(vo);
			result.put("tempList",tempList);
		}
		
		return result;
	}
	
}
