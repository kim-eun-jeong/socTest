package kr.co.enitt.smartManagementSystem.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.enitt.smartManagementSystem.dao.DtsTempDAO;
import kr.co.enitt.smartManagementSystem.service.DtsTempService;
import kr.co.enitt.smartManagementSystem.vo.CommonVO;
import kr.co.enitt.smartManagementSystem.vo.DtsTempVO;

import java.util.List;

@Service("dtsTempService")
public class DtsTempServiceImpl implements DtsTempService{
	@Autowired
	private DtsTempDAO tempDAO;

	@Override
	public List<DtsTempVO> getTempList(CommonVO vo) throws Exception {
		List<DtsTempVO> list = tempDAO.getTempList(vo);
		return list;
	}
	
	@Override
	public List<DtsTempVO> getDtsTempList(CommonVO vo) throws Exception {
		List<DtsTempVO> list = tempDAO.getDtsTempList(vo);
		return list;
	}
	
}
