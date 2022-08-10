package kr.co.enitt.smartManagementSystem.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import kr.co.enitt.smartManagementSystem.service.CodeService;
import kr.co.enitt.smartManagementSystem.service.ZoneService;
import kr.co.enitt.smartManagementSystem.vo.CodeVO;
import kr.co.enitt.smartManagementSystem.vo.CommonVO;
import kr.co.enitt.smartManagementSystem.vo.ZoneVO;


/**
* @Project : smartManagementSystem
* @FileName : ZoneController.java
* @Author : ENITT_KEJ
* @Date : 2021. 1. 5. 
* @Description : 중요지점 설정
* ===========================================================
* DATE                   AUTHOR                     NOTE
* -----------------------------------------------------------
* 2021. 1. 5.       ENITT_KEJ      최초작성
*/
@RequestMapping("/zone")
@Controller
public class ZoneController { 
	
	@Resource(name = "zoneService")
	private ZoneService zoneService;
	 
	@Resource(name = "codeService")
	private CodeService codeService;
	
	@RequestMapping(value= {"","/"})
	public ModelAndView zone(HttpServletRequest request, CommonVO vo) throws Exception {
		ModelAndView mav = new ModelAndView();
		request.setCharacterEncoding("UTF-8");
		
		int cnt = zoneService.getZoneListCnt(vo);
		vo.setTotalCount(cnt);
		List<ZoneVO> list = zoneService.getZonePagingList(vo);
		mav.addObject("zoneList", list);
		
		//검색 조건
		CommonVO commonVO = new CommonVO();
		commonVO.setParentCode("ZONE_TYPE");
		List<CodeVO> zoneTypeList = codeService.getCodeList(commonVO);
		mav.addObject("zoneTypeList", zoneTypeList);
				
		commonVO.setParentCode("LINE");
		List<CodeVO> lineSearchList = codeService.getCodeList(commonVO);
		mav.addObject("lineSearchList", lineSearchList);
				
		mav.addObject("commonVO", vo);
		mav.setViewName("zone/zone");
		return mav;
	}
	
	@RequestMapping("/content/list")
	public ModelAndView list(HttpServletRequest request, CommonVO vo) throws Exception {
		ModelAndView mav = new ModelAndView();
		request.setCharacterEncoding("UTF-8");
		  
		int cnt = zoneService.getZoneListCnt(vo);
		vo.setTotalCount(cnt);
		List<ZoneVO> list = zoneService.getZonePagingList(vo);
		mav.addObject("zoneList", list);
				
		mav.setViewName("zone/content/list");
		return mav;
	}
	
	@RequestMapping("/content/detail")
	public ModelAndView detail(HttpServletRequest request, CommonVO vo) throws Exception {
		ModelAndView mav = new ModelAndView();
		request.setCharacterEncoding("UTF-8");
		  
		ZoneVO detail = zoneService.getZoneDetail(vo);
		mav.addObject("detail", detail);
		
		//검색 조건
		CommonVO commonVO = new CommonVO();
		commonVO.setParentCode("ZONE_TYPE");
		List<CodeVO> zoneTypeList = codeService.getCodeList(commonVO);
		mav.addObject("zoneTypeList", zoneTypeList);
				
		commonVO.setParentCode("LINE");
		List<CodeVO> lineSearchList = codeService.getCodeList(commonVO);
		mav.addObject("lineSearchList", lineSearchList);
		
		mav.setViewName("zone/content/detail");
		return mav;
	}
	
	@RequestMapping("/ajaxCreateZone")
	public @ResponseBody Object saveZone(HttpServletRequest request, ZoneVO vo) throws Exception {
		request.setCharacterEncoding("UTF-8");
		CommonVO commonVO = new CommonVO();
		try {
			// 지점 정보 등록
			HttpSession session = request.getSession(true);
			vo.setRegisterId((String)session.getAttribute("loginId"));
			vo.setRegisterIp((String)session.getAttribute("loginIp"));
			zoneService.createZone(vo);
			
			commonVO.setResult(true);
			commonVO.setContent("정상적으로 저장 되었습니다.");
			
		} catch (Exception e) {
			commonVO.setContent("오류가 발생했습니다.");
			commonVO.setResult(false);
		}
		
		return commonVO;  
	}
	
	@RequestMapping("/ajaxUpdateZone") 
	public @ResponseBody Object ajaxUpdateZone(HttpServletRequest request, ZoneVO vo) throws Exception {
		request.setCharacterEncoding("UTF-8");
		CommonVO commonVO = new CommonVO();
		try {
			// 지점 정보 수정
			HttpSession session = request.getSession(true);
			vo.setUpdateId((String)session.getAttribute("loginId"));
			vo.setUpdateIp((String)session.getAttribute("loginIp"));
			zoneService.updateZone(vo);

			commonVO.setResult(true);
			commonVO.setContent("정상적으로 수정되었습니다.");
		} catch (Exception e) {
			commonVO.setContent("오류가 발생했습니다.");
		}
		
		return commonVO;    
	} 
	
	@RequestMapping("/ajaxDeleteZone") 
	public @ResponseBody Object ajaxDeleteZone(HttpServletRequest request, ZoneVO vo) throws Exception {
		request.setCharacterEncoding("UTF-8");
		CommonVO commonVO = new CommonVO();
		try {
			zoneService.deleteZone(vo);

			commonVO.setResult(true);
			commonVO.setContent("정상적으로 삭제되었습니다.");
		} catch (Exception e) {
			commonVO.setContent("오류가 발생했습니다.");
		}
		
		return commonVO;    
	} 
	
}
