package kr.co.enitt.smartManagementSystem.service;

import java.util.List;

import kr.co.enitt.smartManagementSystem.vo.CommonVO;
import kr.co.enitt.smartManagementSystem.vo.DasEventVO;

public interface DasEventService {
	//이벤트 목록
	List<DasEventVO> getDasEventList(CommonVO vo) throws Exception;
	List<DasEventVO> getDasEventPagingList(CommonVO vo) throws Exception;
	int getDasEventListCnt(CommonVO vo) throws Exception;
	List<DasEventVO> getDasEventCntList(CommonVO vo) throws Exception;
	
	//이벤트 상세
	DasEventVO getDasEventDetail(CommonVO vo) throws Exception;
	
	//삭제할 이벤트 id 목록
	List<DasEventVO> getDeleteDasEventList(CommonVO vo) throws Exception;
	
	//삭제 이벤트 id : N > Y
	void setUpdateDasEvent(List<DasEventVO> list) throws Exception;
}
