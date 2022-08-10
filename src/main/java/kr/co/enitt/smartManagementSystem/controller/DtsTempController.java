package kr.co.enitt.smartManagementSystem.controller;

import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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
import kr.co.enitt.smartManagementSystem.service.DtsEventService;
import kr.co.enitt.smartManagementSystem.service.DtsTempService;
import kr.co.enitt.smartManagementSystem.service.ZoneService;
import kr.co.enitt.smartManagementSystem.util.DownloadUtil;
import kr.co.enitt.smartManagementSystem.util.ObjectUtil;
import kr.co.enitt.smartManagementSystem.vo.CodeVO;
import kr.co.enitt.smartManagementSystem.vo.CommonVO;
import kr.co.enitt.smartManagementSystem.vo.DtsTempVO;
import kr.co.enitt.smartManagementSystem.vo.ZoneVO;

/**
* @Project : WebApplicationExample
* @FileName : EventController.java
* @Author : KEJ
* @Date : 2020. 8. 26. 
* @Description : 온도 이력
* ===========================================================
* DATE                   AUTHOR                     NOTE
* -----------------------------------------------------------
* 2020. 8. 26.       KEJ      최초작성
*/
@RequestMapping("/dtsTemp")
@Controller
public class DtsTempController {
	
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
	  * @Method_Description : 온도 이력
	  * ---------------------
	  * @변경이력
	  * 2020. 8. 26. KEJ : 생성
	  */ 
	@RequestMapping(value= {"","/"})
	public ModelAndView list(HttpServletRequest request, CommonVO vo) throws Exception {
		ModelAndView mav = new ModelAndView();
		request.setCharacterEncoding("UTF-8");
		
		//검색 조건
		CommonVO commonVO = new CommonVO(); 
		
		
		//검색 조건(전력선)		
		commonVO.setParentCode("LINE");
		List<CodeVO> lineSearchList = codeService.getCodeList(commonVO);
		mav.addObject("lineSearchList", lineSearchList);
		if(ObjectUtil.isEmpty(vo.getTempLine())){
			if(ObjectUtil.isNotEmpty(lineSearchList)){
				vo.setTempLine(lineSearchList.get(0).getCodeId());
			}
		}
		
		//검색 조건(일자)
		if(ObjectUtil.isEmpty(vo.getTempDate())){
			SimpleDateFormat  time = new SimpleDateFormat("yyyy-MM-dd");
			vo.setTempDate(time.format(new Date()));
		}

		//검색 조건(검색조건)
		if(ObjectUtil.isEmpty(vo.getTempSearchType())){
			vo.setTempSearchType("zoneType");
			
		}

		//중요지점/특점지점
		commonVO.setZoneLine(vo.getTempLine());
		List<ZoneVO> zoneSearchList = zoneService.getZoneList(commonVO);
		mav.addObject("zoneSearchList", zoneSearchList);
		if("zoneType".equals(vo.getTempSearchType())){
			if(ObjectUtil.isEmpty(vo.getTempZoneCheck())){
				if(ObjectUtil.isNotEmpty(zoneSearchList)){
					vo.setTempZoneCheck(zoneSearchList.get(0).getZoneId());
				}
			}
		}else {
			if(ObjectUtil.isEmpty(vo.getTempLocationStart())) {
				vo.setTempLocationStart("1");
			}
			if(ObjectUtil.isEmpty(vo.getTempLocationEnd())) {
				vo.setTempLocationEnd("10");
			}
		}
		
		mav.addObject("commonVO", vo);
		mav.setViewName("dtsTemp/list");
		return mav;
	}
	
	/**
	  * @Method_Name : ajaxZoneTypeList
	  * @retuen :ModelAndView
	  * @Date : 2021. 1. 5.
	  * @Author : ENITT_KEJ
	  * @Method_Description : 감색조건 중 중요지점 목록 가져오기
	  * ---------------------
	  * @변경이력
	  * 2021. 1. 5. ENITT_KEJ : 생성
	  */
	@RequestMapping("/content/zoneType")
	public ModelAndView ajaxZoneTypeList(HttpServletRequest request, CommonVO vo) throws Exception {
		ModelAndView mav = new ModelAndView();
		request.setCharacterEncoding("UTF-8");
		
		//중요구간 목록
		vo.setZoneLine(vo.getTempLine());
		List<ZoneVO> zoneSearchList = zoneService.getZoneList(vo);
		mav.addObject("zoneSearchList", zoneSearchList);
		
		mav.setViewName("dtsTemp/content/zone_type");
		mav.addObject("commonVO", vo);
		return mav;
	}
	
	
	/**
	  * @Method_Name : ajaxTempChart
	  * @retuen :Object
	  * @Date : 2021. 1. 5.
	  * @Author : ENITT_KEJ
	  * @Method_Description : 온도 이력 차트
	  * ---------------------
	  * @변경이력
	  * 2021. 1. 5. ENITT_KEJ : 생성
	  */
	@RequestMapping("/ajaxTempChart")  
	public @ResponseBody Object ajaxTempChart(HttpServletRequest request, CommonVO vo) throws Exception {
		request.setCharacterEncoding("UTF-8");
		
		//검색 조건
		CommonVO commonVO = new CommonVO(); 
				
		//검색 조건(전력선)		
		commonVO.setParentCode("LINE");
		List<CodeVO> lineSearchList = codeService.getCodeList(commonVO);
		if(ObjectUtil.isEmpty(vo.getTempLine())){
			if(ObjectUtil.isNotEmpty(lineSearchList)){
				vo.setTempLine(lineSearchList.get(0).getCodeId());
			}
		}
		
		//검색 조건(일자)
		if(ObjectUtil.isEmpty(vo.getTempDate())){
			SimpleDateFormat  time = new SimpleDateFormat("yyyy-MM-dd");
			vo.setTempDate(time.format(new Date()));
		}
		
		//검색 조건(검색조건)
		if(ObjectUtil.isEmpty(vo.getTempSearchType())){
			vo.setTempSearchType("zoneType");
			
		}
		
		commonVO.setZoneLine(vo.getTempLine());
		List<ZoneVO> zoneSearchList = zoneService.getZoneList(commonVO);
		String[] searchData = null; 
		String searchDataStr = ""; 
		List<ZoneVO> zoneList = new ArrayList<ZoneVO>();
		if("zoneType".equals(vo.getTempSearchType())){
			if(ObjectUtil.isEmpty(vo.getTempZoneCheck())){
				if(ObjectUtil.isNotEmpty(zoneSearchList)){
					vo.setTempZoneCheck(zoneSearchList.get(0).getZoneId());
				}
			}

			if(ObjectUtil.isNotEmpty(vo.getTempZoneCheck())) {
				searchDataStr = vo.getTempZoneCheck();
				searchData = vo.getTempZoneCheck().split(",");
				
				CommonVO common = new CommonVO(); 
				common.setZoneIdStr(searchDataStr);
				zoneList = new ArrayList<ZoneVO>();
				zoneList = zoneService.getZoneList(common);
			}
			
		}else {
			if(ObjectUtil.isEmpty(vo.getTempLocationStart())) {
				vo.setTempLocationStart("1");
			}
			if(ObjectUtil.isEmpty(vo.getTempLocationEnd())) {
				vo.setTempLocationEnd("10");
			}
			int start = Integer.parseInt(vo.getTempLocationStart());
			int end = Integer.parseInt(vo.getTempLocationEnd());
			for(int i = start; i <= end; i++) {
				searchDataStr += i;
				if(i < end) {
					searchDataStr +=	",";
				}
			}
			searchData = searchDataStr.split(",");
		}
		
		//이벤트 목록		
		vo.setTempHistory("Y"); //최신 데이터가 제일 마지막으로
		List<DtsTempVO> list = dtsTempService.getDtsTempList(vo);
		List<HashMap<String,Object>> datalist = new ArrayList<HashMap<String,Object>>();
		
		if(ObjectUtil.isNotEmpty(searchData)) {
			int value;
			Object[] data = new Object[2];
			HashMap<String,Object> map = new HashMap<String,Object>();
			List<Object> dataArray = new ArrayList<Object>();
			String[] dataValue = null;
			for(int i = 0; i<searchData.length; i++) {
				map = new HashMap<String,Object>();  
				dataArray = new ArrayList<Object>();
				if("zoneType".equals(vo.getTempSearchType())){
					value = zoneList.get(i).getZoneStart();
					map.put("name", zoneList.get(i).getZoneName());
				}else{
					value = Integer.parseInt(searchData[i]);
					map.put("name", value+"m");
				}
				for(int j = 0; j < list.size(); j++) {
					data = new Object[2];
					data[0]= ObjectUtil.getUTC(list.get(j).getDtsTempTime()); 
					//data[1] = ObjectUtil.getTemp(list.get(j).getDtsTempTemp(), value);
					dataValue =list.get(j).getDtsTempTemp().split(",");
					data[1] = Float.parseFloat(String.format("%.1f",Float.parseFloat(dataValue[value])));
					dataArray.add(data);
				}
				
				map.put("data",dataArray);
				datalist.add(map);
			}
		}
		
		HashMap<String,Object> result = new HashMap<String,Object>();
		result.put("list",datalist);
		
		//차트 설정 값 세팅
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date day = dateFormat.parse(vo.getTempDate()+" 00:00:00");
		Calendar cal = Calendar.getInstance();
		cal.setTime(day);
		result.put("minDate",ObjectUtil.getUTC(dateFormat.format(cal.getTime())));
		day = dateFormat.parse(vo.getTempDate()+" 23:59:59");
		cal.setTime(day);
		result.put("maxDate",ObjectUtil.getUTC(dateFormat.format(cal.getTime())));
		
		//온도 단계 설정
		List<DtsTempVO> tempList = dtsTempService.getTempList(vo);
		result.put("tempList",tempList);
		
		return result;
	}
	
	/**
	  * @Method_Name : excelList
	  * @retuen :void
	  * @Date : 2021. 1. 5.
	  * @Author : ENITT_KEJ
	  * @Method_Description : 온도 이력 엑셀 
	  * ---------------------
	  * @변경이력
	  * 2021. 1. 5. ENITT_KEJ : 생성
	  */
	@RequestMapping("/excel/list")  
	public void excelList(HttpServletRequest request, HttpServletResponse response, CommonVO vo) throws Exception {
		request.setCharacterEncoding("UTF-8");
		
		String[] seriesData = null; 
		String searchDataStr = ""; 
		if("zoneType".equals(vo.getTempSearchType())){
			seriesData = vo.getTempZoneCheck().split(",");
			searchDataStr = vo.getTempZoneCheck();
		}else {
			int start = Integer.parseInt(vo.getTempLocationStart());
			int end = Integer.parseInt(vo.getTempLocationEnd());
			for(int i = start; i <= end; i++) {
				searchDataStr += i;
				if(i < end) {
					searchDataStr +=	",";
				}
			}
			seriesData = searchDataStr.split(",");
		}
		
		List<DtsTempVO> list = dtsTempService.getDtsTempList(vo);
		
		List<HashMap<String,Object>> resultList = new ArrayList<HashMap<String,Object>>();
		HashMap<String,Object> data = new HashMap<String,Object>();
		String title = "시간";
		String cell = "time";
		
		if("zoneType".equals(vo.getTempSearchType())){
			CommonVO common = new CommonVO(); 
			common.setZoneIdStr(searchDataStr);
			List<ZoneVO> zoneList = zoneService.getZoneList(common);
			
			int num;
			String[] dataValue = null;
			for(DtsTempVO tempVO : list) {
				data = new HashMap<String,Object>();
				title = "시간";
				cell = "time";
				data.put("time",tempVO.getDtsTempTime());
				dataValue = tempVO.getDtsTempTemp().split(",");
				for(int i = 0; i < seriesData.length; i++) {
					num = zoneList.get(i).getZoneStart() ;

					data.put(zoneList.get(i).getZoneId(), String.format("%.1f",Float.parseFloat(dataValue[num])));
					title += ","+zoneList.get(i).getZoneName();
					cell += ","+zoneList.get(i).getZoneId();
				}
				resultList.add(data);
			}
		}else {
			int num;
			String[] dataValue = null;
			for(DtsTempVO tempVO : list) {
				data = new HashMap<String,Object>();
				title = "시간";
				cell = "time";
				data.put("time",tempVO.getDtsTempTime());
				dataValue = tempVO.getDtsTempTemp().split(",");
				for(int i = 0; i < seriesData.length; i++) {
					num = Integer.parseInt(seriesData[i]) ;
					data.put(seriesData[i]+"m", String.format("%.1f",Float.parseFloat(dataValue[num])));
					//data.put( seriesData[i]+"m", ObjectUtil.getTemp(tempVO.getDtsTempTemp(), num));
					title += ","+seriesData[i]+"m";
					cell += ","+seriesData[i]+"m";
				}
				resultList.add(data);
			}
		}

		HashMap<String,Object> map = new HashMap<String,Object>();
		map.put("sheetName","온도이력("+vo.getTempDate()+")");
		map.put("fileName","온도이력("+vo.getTempDate()+")");
		map.put("title",title);
		map.put("cell",cell);
		
		if(resultList.size() > 0) {
			DownloadUtil.ecxelDown(request, response, map, resultList,"N");
		}else {
			response.setContentType("text/html; charset=UTF-8");
		    response.setCharacterEncoding("UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<!DOCTYPE html><html lang=\"ko\"><head><title></title></head><body>");
			out.println("<script>alert('데이터가 없습니다.');window.history.go(-1);</script>");
			out.println("</body></html>");
			out.close();
		}
	} 
	
	/**
	  * @Method_Name : excelAllList
	  * @retuen :void
	  * @Date : 2021. 1. 5.
	  * @Author : ENITT_KEJ
	  * @Method_Description : 온도 이력 전체 엑셀
	  * ---------------------
	  * @변경이력
	  * 2021. 1. 5. ENITT_KEJ : 생성
	  */
	@RequestMapping("/excel/allList")  
	public void excelAllList(HttpServletRequest request, HttpServletResponse response, CommonVO vo) throws Exception {
		request.setCharacterEncoding("UTF-8");
		List<DtsTempVO> list = dtsTempService.getDtsTempList(vo);

		List<HashMap<String,Object>> resultList = new ArrayList<HashMap<String,Object>>();
		HashMap<String,Object> data = new HashMap<String,Object>();
		String title = "시간";
		String cell = "time";
		
		if("zoneType".equals(vo.getTempSearchType())){
			//검색 조건
			CommonVO commonVO = new CommonVO(); 
			commonVO.setZoneLine(vo.getTempLine());
			List<ZoneVO> zoneSearchList = zoneService.getZoneList(commonVO);
			int num;
			String[] dataValue = null;
			for(DtsTempVO tempVO : list) {
				data = new HashMap<String,Object>();
				title = "시간";
				cell = "time";
				data.put("time",tempVO.getDtsTempTime());
				dataValue = tempVO.getDtsTempTemp().split(",");
				for(int i = 0; i < zoneSearchList.size(); i++) {
					num = zoneSearchList.get(i).getZoneStart();
					//data.put(zoneSearchList.get(i).getZoneId(), ObjectUtil.getTemp(tempVO.getDtsTempTemp(), num));
					data.put(zoneSearchList.get(i).getZoneId(),String.format("%.1f",Float.parseFloat(dataValue[num])));
					title += ","+zoneSearchList.get(i).getZoneName();
					cell += ","+zoneSearchList.get(i).getZoneId();
				}
				resultList.add(data);
			}
		}else {
			String[] dataValue = null;
			for(DtsTempVO tempVO : list) {
				data = new HashMap<String,Object>();
				title = "시간";
				cell = "time";
				data.put("time",tempVO.getDtsTempTime());
				dataValue = tempVO.getDtsTempTemp().split(",");
				for(int i = 0; i < dataValue.length; i++) {
					data.put( i+"m",dataValue[i]);
					title += ","+i+"m";
					cell += ","+i+"m";
				}
				resultList.add(data);
			}
		}

		HashMap<String,Object> map = new HashMap<String,Object>();
		map.put("sheetName","온도이력_전체("+vo.getTempDate()+")");
		map.put("fileName","온도이력_전체("+vo.getTempDate()+")");
		map.put("title",title);
		map.put("cell",cell);
		
		if(resultList.size() > 0) {
			DownloadUtil.ecxelDown(request, response, map, resultList,"N");
		}else {
			response.setContentType("text/html; charset=UTF-8");
		    response.setCharacterEncoding("UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<!DOCTYPE html><html lang=\"ko\"><head><title></title></head><body>");
			out.println("<script>alert('데이터가 없습니다.');window.history.go(-1);</script>");
			out.println("</body></html>");
			out.close();
		}
	} 
	
	
}
