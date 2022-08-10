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
import kr.co.enitt.smartManagementSystem.service.TempService;
import kr.co.enitt.smartManagementSystem.vo.CommonVO;
import kr.co.enitt.smartManagementSystem.vo.TempVO;


/**
* @Project : smartManagementSystem
* @FileName : TempController.java
* @Author : ENITT_KEJ
* @Date : 2021. 1. 5. 
* @Description : 온도 이벤트 설정
* ===========================================================
* DATE                   AUTHOR                     NOTE
* -----------------------------------------------------------
* 2021. 1. 5.       ENITT_KEJ      최초작성
*/
@RequestMapping("/temp")
@Controller
public class TempController { 
	
	@Resource(name = "tempService")
	private TempService tempService;
	 
	@Resource(name = "codeService")
	private CodeService codeService;
	
	@RequestMapping(value= {"","/"})
	public ModelAndView temp(HttpServletRequest request, CommonVO vo) throws Exception {
		ModelAndView mav = new ModelAndView();
		request.setCharacterEncoding("UTF-8");
		
		int cnt = tempService.getTempListCnt(vo);
		vo.setTotalCount(cnt);
		List<TempVO> list = tempService.getTempPagingList(vo);
		mav.addObject("tempList", list);
		
		mav.addObject("commonVO", vo);
		mav.setViewName("temp/temp");
		return mav;
	}
	
	@RequestMapping("/content/list")
	public ModelAndView list(HttpServletRequest request, CommonVO vo) throws Exception {
		ModelAndView mav = new ModelAndView();
		request.setCharacterEncoding("UTF-8");
		  
		int cnt = tempService.getTempListCnt(vo);
		vo.setTotalCount(cnt);
		List<TempVO> list = tempService.getTempPagingList(vo);
		mav.addObject("tempList", list);
				
		mav.setViewName("temp/content/list");
		return mav;
	}
	
	@RequestMapping("/content/detail")
	public ModelAndView detail(HttpServletRequest request, CommonVO vo) throws Exception {
		ModelAndView mav = new ModelAndView();
		request.setCharacterEncoding("UTF-8");
		  
		TempVO detail = tempService.getTempDetail(vo);
		mav.addObject("detail", detail);
		
		mav.setViewName("temp/content/detail");
		return mav;
	}
	
	@RequestMapping("/ajaxCreateTemp")
	public @ResponseBody Object saveTemp(HttpServletRequest request, TempVO vo) throws Exception {
		request.setCharacterEncoding("UTF-8");
		CommonVO commonVO = new CommonVO();
		try {
			// 지점 정보 등록
			HttpSession session = request.getSession(true);
			vo.setRegisterId((String)session.getAttribute("loginId"));
			vo.setRegisterIp((String)session.getAttribute("loginIp"));
			tempService.createTemp(vo);
			
			commonVO.setResult(true);
			commonVO.setContent("정상적으로 저장 되었습니다.");
			
		} catch (Exception e) {
			commonVO.setContent("오류가 발생했습니다.");
			commonVO.setResult(false);
		}
		
		return commonVO;  
	}
	
	@RequestMapping("/ajaxUpdateTemp") 
	public @ResponseBody Object ajaxUpdateTemp(HttpServletRequest request, TempVO vo) throws Exception {
		request.setCharacterEncoding("UTF-8");
		CommonVO commonVO = new CommonVO();
		try {
			// 지점 정보 수정
			HttpSession session = request.getSession(true);
			vo.setUpdateId((String)session.getAttribute("loginId"));
			vo.setUpdateIp((String)session.getAttribute("loginIp"));
			tempService.updateTemp(vo);

			commonVO.setResult(true);
			commonVO.setContent("정상적으로 수정되었습니다.");
		} catch (Exception e) {
			commonVO.setContent("오류가 발생했습니다.");
		}
		
		return commonVO;    
	} 
	
	@RequestMapping("/ajaxDeleteTemp") 
	public @ResponseBody Object ajaxDeleteTemp(HttpServletRequest request, TempVO vo) throws Exception {
		request.setCharacterEncoding("UTF-8");
		CommonVO commonVO = new CommonVO();
		try {
			tempService.deleteTemp(vo);

			commonVO.setResult(true);
			commonVO.setContent("정상적으로 삭제되었습니다.");
		} catch (Exception e) {
			commonVO.setContent("오류가 발생했습니다.");
		}
		
		return commonVO;    
	} 
	
}
