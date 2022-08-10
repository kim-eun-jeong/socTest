package kr.co.enitt.smartManagementSystem.service;

import java.util.List;

import kr.co.enitt.smartManagementSystem.vo.CommonVO;
import kr.co.enitt.smartManagementSystem.vo.TempVO;

public interface TempService {
	// 온도 설정 목록
	List<TempVO> getTempPagingList(CommonVO vo) throws Exception;
	List<TempVO> getTempList(CommonVO vo) throws Exception;
	int getTempListCnt(CommonVO vo) throws Exception;
		
	// 온도 설정 상세 정보
	TempVO getTempDetail(CommonVO vo) throws Exception;
	
	// 온도 설정 등록
	void createTemp(TempVO vo) throws Exception;
		
	// 온도 설정 정보 수정
	void updateTemp(TempVO vo) throws Exception;
	
	// 온도 설정 정보 삭제
	void deleteTemp(TempVO vo) throws Exception;

}