package kr.co.enitt.smartManagementSystem.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.enitt.smartManagementSystem.dao.DtsEventDAO;
import kr.co.enitt.smartManagementSystem.service.DtsEventService;
import kr.co.enitt.smartManagementSystem.vo.CommonVO;
import kr.co.enitt.smartManagementSystem.vo.DtsEventVO;

import java.util.List;

@Service("dtsEventService")
public class DtsEventServiceImpl implements DtsEventService{
	@Autowired
	private DtsEventDAO eventDAO;

	/**
	  * @Method_Name : getEventList
	  * @Method_Description : 이벤트 목록
	  * ---------------------
	  * @변경이력
	  * 2020. 8. 25. KEJ : 생성
	  */
	@Override
	public List<DtsEventVO> getDtsEventList(CommonVO vo) throws Exception {
		List<DtsEventVO> list = eventDAO.getDtsEventList(vo);
		return list;
	}
	@Override
	public List<DtsEventVO> getDtsEventPagingList(CommonVO vo) throws Exception {
		List<DtsEventVO> list = eventDAO.getDtsEventPagingList(vo);
		return list;
	}
	@Override
	public int getDtsEventListCnt(CommonVO vo) throws Exception {
		return eventDAO.getDtsEventListCnt(vo);
	}
	@Override
	public List<DtsEventVO> getDtsEventCntList(CommonVO vo) throws Exception {
		List<DtsEventVO> list = eventDAO.getDtsEventCntList(vo);
		return list;
	}
	
	/**
	  * @Method_Name : getEventDetail
	  * @Method_Description : 이벤트 상세
	  * ---------------------
	  * @변경이력
	  * 2020. 8. 25. KEJ : 생성
	  */
	
	@Override
	public DtsEventVO getDtsEventDetail(CommonVO vo) throws Exception {
		DtsEventVO detail = eventDAO.getDtsEventDetail(vo);
		return detail;
	}
	
	@Override
	public List<DtsEventVO> getDeleteDtsEventList(CommonVO vo) throws Exception {
		return eventDAO.getDeleteDtsEventList(vo);
	}
	
	@Override
	public void setUpdateDtsEvent(List<DtsEventVO> list) throws Exception {
		eventDAO.setUpdateDtsEvent(list);
	}
	
}
