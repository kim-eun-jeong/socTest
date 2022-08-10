package kr.co.enitt.smartManagementSystem.service;

import java.util.List;

import kr.co.enitt.smartManagementSystem.vo.CommonVO;
import kr.co.enitt.smartManagementSystem.vo.DtsEventVO;

public interface DtsEventService {
	//이벤트 목록
	List<DtsEventVO> getDtsEventList(CommonVO vo) throws Exception;
	List<DtsEventVO> getDtsEventPagingList(CommonVO vo) throws Exception;
	int getDtsEventListCnt(CommonVO vo) throws Exception;
	List<DtsEventVO> getDtsEventCntList(CommonVO vo) throws Exception;
	
	//이벤트 상세
	DtsEventVO getDtsEventDetail(CommonVO vo) throws Exception;

	List<DtsEventVO> getDeleteDtsEventList(CommonVO vo) throws Exception;
	void setUpdateDtsEvent(List<DtsEventVO> list) throws Exception;
}
