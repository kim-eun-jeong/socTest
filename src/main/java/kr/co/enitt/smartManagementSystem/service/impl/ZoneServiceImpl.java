package kr.co.enitt.smartManagementSystem.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.enitt.smartManagementSystem.dao.ZoneDAO;
import kr.co.enitt.smartManagementSystem.service.ZoneService;
import kr.co.enitt.smartManagementSystem.vo.CommonVO;
import kr.co.enitt.smartManagementSystem.vo.ZoneVO;

@Service("zoneService")
public class ZoneServiceImpl implements ZoneService{
	@Autowired
	private ZoneDAO zoneDAO;
	
	/**
	  * @Method_Name : getZoneList
	  * @Method_Description : 지점 목록
	  * ---------------------
	  * @변경이력
	  * 2020. 9. 9. KEJ : 생성
	  */
	@Override
	public List<ZoneVO> getZonePagingList(CommonVO vo) throws Exception {
		List<ZoneVO> list = zoneDAO.getZonePagingList(vo);
		return list;
	}
	@Override
	public List<ZoneVO> getZoneList(CommonVO vo) throws Exception {
		List<ZoneVO> list = zoneDAO.getZoneList(vo);
		return list;
	}
	public int getZoneListCnt(CommonVO vo) throws Exception {
		int cnt = zoneDAO.getZoneListCnt(vo);
		return cnt;
	} 
	
	/**
	  * @Method_Name : getZoneDetail
	  * @Method_Description : 지점 상세 정보
	  * ---------------------
	  * @변경이력
	  * 2020. 9. 9. KEJ : 생성
	  */
	@Override
	public ZoneVO getZoneDetail(CommonVO vo) throws Exception {
		ZoneVO detail = zoneDAO.getZoneDetail(vo);
		return detail;
	}

	/**
	  * @Method_Name : createZone
	  * @Method_Description : 지점정보 생성
	  * ---------------------
	  * @변경이력
	  * 2020. 9. 9. KEJ : 생성
	  */
	@Override
	public void createZone(ZoneVO vo) throws Exception {
		zoneDAO.createZone(vo);
	}
	
	/**
	  * @Method_Name : updateZone
	  * @Method_Description : 지점정보 수정
	  * ---------------------
	  * @변경이력
	  * 2020. 9. 9. KEJ : 생성
	  */
	@Override
	public void updateZone(ZoneVO vo) throws Exception {
		zoneDAO.updateZone(vo);
	}

	/**
	  * @Method_Name : updateZone
	  * @Method_Description : 지점정보 삭제
	  * ---------------------
	  * @변경이력
	  * 2020. 9. 9. KEJ : 생성
	  */
	@Override
	public void deleteZone(ZoneVO vo) throws Exception {
		zoneDAO.deleteZone(vo);
	}
	
	
}
