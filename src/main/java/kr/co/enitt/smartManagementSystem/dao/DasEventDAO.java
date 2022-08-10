package kr.co.enitt.smartManagementSystem.dao;

import org.apache.ibatis.annotations.Mapper;

import kr.co.enitt.smartManagementSystem.vo.CommonVO;
import kr.co.enitt.smartManagementSystem.vo.DasEventVO;

import java.util.List;

@Mapper
public interface DasEventDAO{
	//이벤트 목록
	public List<DasEventVO> getDasEventList(CommonVO vo) throws Exception;
	public List<DasEventVO> getDasEventPagingList(CommonVO vo) throws Exception;
	public int getDasEventListCnt(CommonVO vo) throws Exception;
	public List<DasEventVO> getDasEventCntList(CommonVO vo) throws Exception;
	
	//이벤트 상세
	public DasEventVO getDasEventDetail(CommonVO vo) throws Exception;
	
	//삭제할 이벤트 id 목록
	public List<DasEventVO> getDeleteDasEventList(CommonVO vo) throws Exception;
	
	public void setUpdateDasEvent(List<DasEventVO> list) throws Exception;
	
}
