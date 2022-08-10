package kr.co.enitt.smartManagementSystem.service;

import java.util.List;

import kr.co.enitt.smartManagementSystem.vo.CommonVO;
import kr.co.enitt.smartManagementSystem.vo.DtsEventStatsVO;

public interface DtsEventStatsService {
	//DTS 이벤트 통계
	List<DtsEventStatsVO> getEventStatsDayList(CommonVO vo) throws Exception;
	List<DtsEventStatsVO> getEventStatsMonthList(CommonVO vo) throws Exception;
	List<DtsEventStatsVO> getEventStatsYearList(CommonVO vo) throws Exception;

}
