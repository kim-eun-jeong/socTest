package kr.co.enitt.smartManagementSystem.service;

import java.util.List;

import kr.co.enitt.smartManagementSystem.vo.CommonVO;
import kr.co.enitt.smartManagementSystem.vo.ZoneVO;

public interface ZoneService {
	// 지점 목록
	List<ZoneVO> getZonePagingList(CommonVO vo) throws Exception;
	List<ZoneVO> getZoneList(CommonVO vo) throws Exception;
	int getZoneListCnt(CommonVO vo) throws Exception;
		
	// 지점 상세 정보
	ZoneVO getZoneDetail(CommonVO vo) throws Exception;
	
	// 지점 등록
	void createZone(ZoneVO vo) throws Exception;
		
	// 지점 정보 수정
	void updateZone(ZoneVO vo) throws Exception;
	
	// 지점 정보 삭제
	void deleteZone(ZoneVO vo) throws Exception;

}