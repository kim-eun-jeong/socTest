package kr.co.enitt.smartManagementSystem.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.co.enitt.smartManagementSystem.vo.CommonVO;
import kr.co.enitt.smartManagementSystem.vo.ZoneVO;

@Mapper
public interface ZoneDAO{
	//지점 목록
	public List<ZoneVO> getZonePagingList(CommonVO vo) throws Exception;
	public List<ZoneVO> getZoneList(CommonVO vo) throws Exception;
	public int getZoneListCnt(CommonVO vo) throws Exception;
	
	//지점 상세 정보
	public ZoneVO getZoneDetail(CommonVO vo) throws Exception;
	
	//지점 정보 등록
	public void createZone(ZoneVO vo);
	
	//지점 정보 수정
	public void updateZone(ZoneVO vo);
	
	//지점 정보 삭제
	public void deleteZone(ZoneVO vo);
	
	 
}
