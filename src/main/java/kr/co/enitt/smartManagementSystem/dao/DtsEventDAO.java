package kr.co.enitt.smartManagementSystem.dao;

import org.apache.ibatis.annotations.Mapper;

import kr.co.enitt.smartManagementSystem.vo.CommonVO;
import kr.co.enitt.smartManagementSystem.vo.DtsEventVO;

import java.util.List;

@Mapper
public interface DtsEventDAO{
	//이벤트 목록
	public List<DtsEventVO> getDtsEventList(CommonVO vo) throws Exception;
	public List<DtsEventVO> getDtsEventPagingList(CommonVO vo) throws Exception;
	public int getDtsEventListCnt(CommonVO vo) throws Exception;
	public List<DtsEventVO> getDtsEventCntList(CommonVO vo) throws Exception;
	 
	//이벤트 상세 
	public DtsEventVO getDtsEventDetail(CommonVO vo) throws Exception;
	
	public List<DtsEventVO> getDeleteDtsEventList(CommonVO vo) throws Exception;
	public void setUpdateDtsEvent(List<DtsEventVO> list) throws Exception;
}
