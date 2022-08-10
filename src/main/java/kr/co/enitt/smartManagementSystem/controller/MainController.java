package kr.co.enitt.smartManagementSystem.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.co.enitt.smartManagementSystem.service.CodeService;
import kr.co.enitt.smartManagementSystem.service.DasEventService;
import kr.co.enitt.smartManagementSystem.service.DtsEventService;
import kr.co.enitt.smartManagementSystem.util.ObjectUtil;
import kr.co.enitt.smartManagementSystem.vo.CodeVO;
import kr.co.enitt.smartManagementSystem.vo.CommonVO;
import kr.co.enitt.smartManagementSystem.vo.DasEventVO;
import kr.co.enitt.smartManagementSystem.vo.DtsEventVO;

/**
* @Project : smartManagementSystem
* @FileName : MainController.java
* @Author : KEJ
* @Date : 2020. 9. 1. 
* @Description :
* ===========================================================
* DATE                   AUTHOR                     NOTE
* -----------------------------------------------------------
* 2020. 9. 1.       KEJ      최초작성
*/
@RequestMapping("/main")
@Controller
public class MainController {
	
	@Resource(name = "dtsEventService")
	private DtsEventService dtsEventService;
	
	@Resource(name = "dasEventService")
	private DasEventService dasEventService;

	@Resource(name = "codeService")
	private CodeService codeService;
	
	/**
	  * @Method_Name : ajaxEventStatCnt
	  * @retuen :ModelAndView
	  * @Date : 2020. 9. 1.
	  * @Author : KEJ
	  * @Method_Description : 이벤트 발생 갯수
	  * ---------------------
	  * @변경이력
	  * 2020. 9. 1. KEJ : 생성
	  */
	@RequestMapping("/ajaxEventCnt")
	public @ResponseBody Object ajaxEventStatCnt(HttpServletRequest request) throws Exception {
		request.setCharacterEncoding("UTF-8"); 
		Map<String,Object> result = new HashMap<String,Object>();
		CommonVO vo = new CommonVO();
		
		//24시간 적용
		vo.setDashboard("Y");
		vo.setNow(ObjectUtil.getNow("",0)); // 현재 시간
		
		//DTS 이벤트 갯수
		List<DtsEventVO>  dtsList = dtsEventService.getDtsEventCntList(vo);
		result.put("dtsEventCntList", dtsList);
		
		
		//DAS 이벤트 갯수
		List<DasEventVO>  dasList = dasEventService.getDasEventCntList(vo);
		result.put("dasEventCntList", dasList);
		
		return result; 
	}
	
}
