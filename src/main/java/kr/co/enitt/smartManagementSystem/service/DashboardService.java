package kr.co.enitt.smartManagementSystem.service;

import java.util.List;

import kr.co.enitt.smartManagementSystem.vo.CommonVO;
import kr.co.enitt.smartManagementSystem.vo.DashboardVO;

public interface DashboardService {
	/**
	  * @Method_Name : getEquipmentState
	  * @retuen :List<DashboardVO>
	  * @Date : 2020. 11. 20.
	  * @Author : ENITT_KEJ
	  * @Method_Description : 장비상태 목록
	  * ---------------------
	  * @변경이력
	  * 2020. 11. 20. ENITT_KEJ : 생성
	  */
	List<DashboardVO> getEquipmentState(CommonVO vo) throws Exception;

}
