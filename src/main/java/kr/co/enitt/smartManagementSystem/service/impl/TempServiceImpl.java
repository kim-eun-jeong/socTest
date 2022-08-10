package kr.co.enitt.smartManagementSystem.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.enitt.smartManagementSystem.dao.TempDAO;
import kr.co.enitt.smartManagementSystem.service.TempService;
import kr.co.enitt.smartManagementSystem.vo.CommonVO;
import kr.co.enitt.smartManagementSystem.vo.TempVO;

@Service("tempService")
public class TempServiceImpl implements TempService{
	@Autowired
	private TempDAO tempDAO;
	
	/**
	  * @Method_Name : getTempList
	  * @Method_Description : 온도 설정 목록
	  * ---------------------
	  * @변경이력
	  * 2020. 9. 9. KEJ : 생성
	  */
	@Override
	public List<TempVO> getTempPagingList(CommonVO vo) throws Exception {
		List<TempVO> list = tempDAO.getTempPagingList(vo);
		return list;
	}
	@Override
	public List<TempVO> getTempList(CommonVO vo) throws Exception {
		List<TempVO> list = tempDAO.getTempList(vo);
		return list;
	}
	public int getTempListCnt(CommonVO vo) throws Exception {
		int cnt = tempDAO.getTempListCnt(vo);
		return cnt;
	}
	
	/**
	  * @Method_Name : getTempDetail
	  * @Method_Description : 온도 설정 상세 정보
	  * ---------------------
	  * @변경이력
	  * 2020. 9. 9. KEJ : 생성
	  */
	@Override
	public TempVO getTempDetail(CommonVO vo) throws Exception {
		TempVO detail = tempDAO.getTempDetail(vo);
		return detail;
	}

	/**
	  * @Method_Name : createTemp
	  * @Method_Description : 온도 설정정보 생성
	  * ---------------------
	  * @변경이력
	  * 2020. 9. 9. KEJ : 생성 
	  */
	@Override
	public void createTemp(TempVO vo) throws Exception {
		tempDAO.createTemp(vo);
	}
	
	/**
	  * @Method_Name : updateTemp
	  * @Method_Description : 온도 설정정보 수정
	  * ---------------------
	  * @변경이력
	  * 2020. 9. 9. KEJ : 생성
	  */
	@Override
	public void updateTemp(TempVO vo) throws Exception {
		tempDAO.updateTemp(vo);
	}

	/**
	  * @Method_Name : updateTemp
	  * @Method_Description : 온도 설정정보 삭제
	  * ---------------------
	  * @변경이력
	  * 2020. 9. 9. KEJ : 생성
	  */
	@Override
	public void deleteTemp(TempVO vo) throws Exception {
		tempDAO.deleteTemp(vo);
	}
	
	
}
