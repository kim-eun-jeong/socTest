package kr.co.enitt.smartManagementSystem.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import kr.co.enitt.smartManagementSystem.service.CodeService;
import kr.co.enitt.smartManagementSystem.service.DasEventService;
import kr.co.enitt.smartManagementSystem.util.DownloadUtil;
import kr.co.enitt.smartManagementSystem.util.ObjectUtil;
import kr.co.enitt.smartManagementSystem.vo.CodeVO;
import kr.co.enitt.smartManagementSystem.vo.CommonVO;
import kr.co.enitt.smartManagementSystem.vo.DasEventVO;

/**
* @Project : smartManagementSystem
* @FileName : DasEventController.java
* @Author : KEJ
* @Date : 2020. 8. 26. 
* @Description : 진동 감지 이력
* ===========================================================
* DATE                   AUTHOR                     NOTE
* -----------------------------------------------------------
* 2020. 8. 26.       KEJ      최초작성
*/
@RequestMapping("/dasEvent")
@Controller
public class DasEventController {
	
	@Resource(name = "dasEventService")
	private DasEventService dasEventService;
	
	@Resource(name = "codeService")
	private CodeService codeService;
	  
	DownloadUtil downloadUtil;
	
	/**
	  * @Method_Name : list
	  * @retuen :ModelAndView
	  * @Date : 2020. 8. 26.
	  * @Author : KEJ
	  * @Method_Description : 진동 감지 이력 목록 
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
		
		//목록		
		int Cnt = dasEventService.getDasEventListCnt(vo);
		vo.setTotalCount(Cnt);
		List<DasEventVO> list = dasEventService.getDasEventPagingList(vo);
		mav.addObject("dasEventList", list);
		
		//검색 조건
		CommonVO commonVO = new CommonVO(); 
		
		commonVO.setParentCode("DAS_EVENT");
		List<CodeVO> eventSearchList = codeService.getCodeList(commonVO);
		mav.addObject("eventSearchList", eventSearchList);
		
		commonVO.setParentCode("TUNNEL");
		List<CodeVO> tunnelSearchList = codeService.getCodeList(commonVO);
		mav.addObject("tunnelSearchList", tunnelSearchList);

		mav.addObject("commonVO", vo);
		mav.setViewName("dasEvent/list");
		return mav;
	}
	
	/**
	  * @Method_Name : excelList
	  * @retuen :ModelAndView
	  * @Date : 2020. 9. 9.
	  * @Author : KEJ
	  * @Method_Description : 진동 감지 이력 목록 엑셀
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
		
		List<DasEventVO> list = dasEventService.getDasEventList(vo);
		Map<String, Object> map = new HashMap<String, Object>(); 
		map.put("sheetName","진동감지 목록");
		map.put("fileName","진동감지 목록");
		map.put("title","시간,발생구,구간,발생내용");
		map.put("cell","dasEventTime,dasEventTunnelNm,dasEventLocationStr,dasEventCodeNm");
		
		DownloadUtil.ecxelDown(request, response, map, list,"Y");
	} 
}
