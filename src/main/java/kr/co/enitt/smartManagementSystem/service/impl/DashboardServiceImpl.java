package kr.co.enitt.smartManagementSystem.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.enitt.smartManagementSystem.dao.DashboardDAO;
import kr.co.enitt.smartManagementSystem.service.DashboardService;
import kr.co.enitt.smartManagementSystem.vo.CommonVO;
import kr.co.enitt.smartManagementSystem.vo.DashboardVO;

import java.util.List;

@Service("dashboardService")
public class DashboardServiceImpl implements DashboardService{
	@Autowired
	private DashboardDAO dashboardDAO;

	/**
	  * @Method_Name : getEquipmentState
	  * @Method_Description : 장비상태 목록
	  * ---------------------
	  * @변경이력
	  * 2020. 11. 20. ENITT_KEJ : 생성
	  */
	@Override
	public List<DashboardVO> getEquipmentState(CommonVO vo) throws Exception {
		return dashboardDAO.getEquipmentState(vo);
	}

	
}
