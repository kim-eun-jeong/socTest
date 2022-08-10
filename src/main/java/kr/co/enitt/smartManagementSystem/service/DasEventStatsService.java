package kr.co.enitt.smartManagementSystem.service;

import java.util.HashMap;
import java.util.List;

import kr.co.enitt.smartManagementSystem.vo.CommonVO;
import kr.co.enitt.smartManagementSystem.vo.DasEventStatsVO;

public interface DasEventStatsService {
	//DAS 이벤트 통계
	HashMap<String, Object> getEventStatsDayList(CommonVO vo) throws Exception;
	HashMap<String, Object> getEventStatsMonthList(CommonVO vo) throws Exception;
	HashMap<String, Object> getEventStatsYearList(CommonVO vo) throws Exception;

}
