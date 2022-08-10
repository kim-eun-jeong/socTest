package kr.co.enitt.smartManagementSystem.dao;

import org.apache.ibatis.annotations.Mapper;

import kr.co.enitt.smartManagementSystem.vo.CommonVO;
import kr.co.enitt.smartManagementSystem.vo.DtsEventVO;
import kr.co.enitt.smartManagementSystem.vo.DtsTempVO;

import java.util.List;

@Mapper
public interface DtsTempDAO{ 
	//온도 단계 목록 
	public List<DtsTempVO> getTempList(CommonVO vo) throws Exception;
	
	//온도 목록 
	public List<DtsTempVO> getDtsTempList(CommonVO vo) throws Exception;
}
