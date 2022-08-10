package kr.co.enitt.smartManagementSystem.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.co.enitt.smartManagementSystem.vo.CommonVO;
import kr.co.enitt.smartManagementSystem.vo.DtsEventStatsVO;

@Mapper
public interface DtsEventStatsDAO{
	//DTS 이벤트 통계
	public List<DtsEventStatsVO> getEventStatsDayList(CommonVO vo) throws Exception;
	public List<DtsEventStatsVO> getEventStatsMonthList(CommonVO vo) throws Exception;
	public List<DtsEventStatsVO> getEventStatsYearList(CommonVO vo) throws Exception;

}
