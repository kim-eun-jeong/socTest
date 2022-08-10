package kr.co.enitt.smartManagementSystem.service;

import java.util.List;

import kr.co.enitt.smartManagementSystem.vo.CommonVO;
import kr.co.enitt.smartManagementSystem.vo.DtsEventVO;
import kr.co.enitt.smartManagementSystem.vo.DtsTempVO;

public interface DtsTempService {
	//온도 단계 목록
	List<DtsTempVO> getTempList(CommonVO vo) throws Exception;
	
	//온도 목록
	List<DtsTempVO> getDtsTempList(CommonVO vo) throws Exception;

}
 