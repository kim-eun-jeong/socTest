package kr.co.enitt.smartManagementSystem.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.enitt.smartManagementSystem.dao.DasEventDAO;
import kr.co.enitt.smartManagementSystem.service.DasEventService;
import kr.co.enitt.smartManagementSystem.vo.CommonVO;
import kr.co.enitt.smartManagementSystem.vo.DasEventVO;

import java.util.List;

@Service("dasEventService")
public class DasEventServiceImpl implements DasEventService{
	@Autowired
	private DasEventDAO eventDAO;

	/**
	  * @Method_Name : getEventList
	  * @Method_Description : 이벤트 목록
	  * ---------------------
	  * @변경이력
	  * 2020. 8. 25. KEJ : 생성
	  */
	@Override
	public List<DasEventVO> getDasEventList(CommonVO vo) throws Exception {
		List<DasEventVO> list = eventDAO.getDasEventList(vo);
		return list;
	}
	@Override
	public List<DasEventVO> getDasEventPagingList(CommonVO vo) throws Exception {
		List<DasEventVO> list = eventDAO.getDasEventPagingList(vo);
		return list;
	}
	@Override
	public int getDasEventListCnt(CommonVO vo) throws Exception {
		return eventDAO.getDasEventListCnt(vo);
	}

	@Override
	public List<DasEventVO> getDasEventCntList(CommonVO vo) throws Exception {
		List<DasEventVO> list = eventDAO.getDasEventCntList(vo);
		return list;
	}
	
	@Override
	public DasEventVO getDasEventDetail(CommonVO vo) throws Exception {
		DasEventVO detail = eventDAO.getDasEventDetail(vo);
		return detail;
	}
	@Override
	public List<DasEventVO> getDeleteDasEventList(CommonVO vo) throws Exception {
		return eventDAO.getDeleteDasEventList(vo);
	}
	@Override
	public void setUpdateDasEvent(List<DasEventVO> list) throws Exception {
		eventDAO.setUpdateDasEvent(list);
	}	
}
