package kr.co.enitt.smartManagementSystem.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.co.enitt.smartManagementSystem.vo.CommonVO;
import kr.co.enitt.smartManagementSystem.vo.DasEventStatsVO;

@Mapper
public interface DasEventStatsDAO{
	//DAS 이벤트 통계
	public List<DasEventStatsVO> getEventStatsDayList(CommonVO vo) throws Exception;
	public List<DasEventStatsVO> getEventStatsMonthList(CommonVO vo) throws Exception;
	public List<DasEventStatsVO> getEventStatsYearList(CommonVO vo) throws Exception;

}
