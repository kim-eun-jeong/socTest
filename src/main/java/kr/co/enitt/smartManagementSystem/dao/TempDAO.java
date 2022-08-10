package kr.co.enitt.smartManagementSystem.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.co.enitt.smartManagementSystem.vo.CommonVO;
import kr.co.enitt.smartManagementSystem.vo.TempVO;

@Mapper
public interface TempDAO{
	//온도 설정 목록
	public List<TempVO> getTempPagingList(CommonVO vo) throws Exception;
	public List<TempVO> getTempList(CommonVO vo) throws Exception;
	public int getTempListCnt(CommonVO vo) throws Exception;
	
	//온도 설정 상세 정보
	public TempVO getTempDetail(CommonVO vo) throws Exception;
	
	//온도 설정 정보 등록
	public void createTemp(TempVO vo);
	
	//온도 설정 정보 수정
	public void updateTemp(TempVO vo);
	
	//온도 설정 정보 삭제
	public void deleteTemp(TempVO vo);
	
	 
}
