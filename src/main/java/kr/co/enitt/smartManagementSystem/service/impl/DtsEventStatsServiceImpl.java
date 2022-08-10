package kr.co.enitt.smartManagementSystem.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.enitt.smartManagementSystem.dao.DtsEventStatsDAO;
import kr.co.enitt.smartManagementSystem.service.DtsEventStatsService;
import kr.co.enitt.smartManagementSystem.util.ObjectUtil;
import kr.co.enitt.smartManagementSystem.vo.CommonVO;
import kr.co.enitt.smartManagementSystem.vo.DtsEventStatsVO;

@Service("dtsEventStatsService")
public class DtsEventStatsServiceImpl implements DtsEventStatsService{
	@Autowired
	private DtsEventStatsDAO eventstatsDAO;

	/**
	  * @Method_Name : getEventStatsDayList, getEventStatsMonthList, getEventStatsYearList
	  * @Method_Description : 선로 이벤트 통계
	  * ---------------------
	  * @변경이력
	  * 2020. 9. 9. KEJ : 생성
	  */
	@Override
	public List<DtsEventStatsVO> getEventStatsDayList(CommonVO vo) throws Exception {
		List<DtsEventStatsVO> list = new ArrayList<DtsEventStatsVO>();
		list = eventstatsDAO.getEventStatsDayList(vo);
		
		List<DtsEventStatsVO> result = dateSetting(list, 1, vo.getSearchDay()); 
		return result;
	}
	@Override
	public List<DtsEventStatsVO> getEventStatsMonthList(CommonVO vo) throws Exception {
		List<DtsEventStatsVO> list = new ArrayList<DtsEventStatsVO>();
		list = eventstatsDAO.getEventStatsMonthList(vo);
		
		List<DtsEventStatsVO> result = dateSetting(list,1, vo.getSearchMonth()); 
		return result;
	}
	@Override
	public List<DtsEventStatsVO> getEventStatsYearList(CommonVO vo) throws Exception {
		List<DtsEventStatsVO> list = new ArrayList<DtsEventStatsVO>();
		list = eventstatsDAO.getEventStatsYearList(vo);
		
		List<DtsEventStatsVO> result = dateSetting(list, ObjectUtil.toInt(vo.getSearchYear(),0), vo.getSearchYearEnd()); 
		return result;
	}
	
	public List<DtsEventStatsVO> dateSetting(List<DtsEventStatsVO> list, int startCnt , String searchDate) {
		int lastDay = 0;
		String eventStatsTime = "";
		if(searchDate.length() == 7) {
			Calendar cal = Calendar.getInstance();
			String[] searchDay = searchDate.split("-");
			int year = ObjectUtil.toInt(searchDay[0],0);
			int month =ObjectUtil.toInt(searchDay[1],0)-1;
			cal.set(year,month,1);
			lastDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
			eventStatsTime = searchDate+"-";
		}else {
			if(startCnt > 1) {
				lastDay = ObjectUtil.toInt(searchDate,0);
			}else {
				eventStatsTime = searchDate+"-";
				lastDay = 12;
			}
		}
		
		String group = "";
		String eventTimeGroup = "";
		String eventLine = "";
		String eventCode = "";
		int eventCount = 0;
		int eventStatsTotal01 = 0;
		int eventStatsTotal02 = 0;
		
		
		List<DtsEventStatsVO> result = new ArrayList<DtsEventStatsVO>();
		DtsEventStatsVO map = new DtsEventStatsVO();
		for(int i = startCnt; i <= lastDay; i++) {
			map = new DtsEventStatsVO();
			eventStatsTotal01 = 0;
			eventStatsTotal02 = 0;
			
			if(i < 10){
				group = "0"+i;
			}else {
				group = ""+i;
			}
			map.setEventStatsTimeGroup(group);
			map.setEventStatsTime(eventStatsTime+group);
			
			for(int j = 0; j < list.size(); j++) {
				eventTimeGroup = list.get(j).getEventTimeGroup();
				eventLine = list.get(j).getEventLine();
				eventCode =  list.get(j).getEventCode();
				eventCount =  list.get(j).getEventCount();

				if(group.equals(eventTimeGroup)) {
					if("LINE_01".equals(eventLine)){
						if("EVENT_01".equals(eventCode)){
							map.setEventStats0101(eventCount);
							eventStatsTotal01 += eventCount;
						}else if("EVENT_02".equals(eventCode)){
							map.setEventStats0102(eventCount);
							eventStatsTotal02 += eventCount;
						}
					}else if("LINE_02".equals(eventLine)) {
						if("EVENT_01".equals(eventCode)){
							map.setEventStats0201(eventCount);
							eventStatsTotal01 += eventCount;
						}else if("EVENT_02".equals(eventCode)){
							map.setEventStats0202(eventCount);
							eventStatsTotal02 += eventCount;
						}
					}else if("LINE_03".equals(eventLine)) {
						if("EVENT_01".equals(eventCode)){
							map.setEventStats0301(eventCount);
							eventStatsTotal01 += eventCount;
						}else if("EVENT_02".equals(eventCode)){
							map.setEventStats0302(eventCount);
							eventStatsTotal02 += eventCount;
						}
					}else if("LINE_04".equals(eventLine)) {
						if("EVENT_01".equals(eventCode)){
							map.setEventStats0401(eventCount);
							eventStatsTotal01 += eventCount;
						}else if("EVENT_02".equals(eventCode)){
							map.setEventStats0402(eventCount);
							eventStatsTotal02 += eventCount;
						}
					}
				}
			}
			map.setEventStatsTotal01(eventStatsTotal01);
			map.setEventStatsTotal02(eventStatsTotal02);
			result.add(map);
		}
		return result;
	}

}
