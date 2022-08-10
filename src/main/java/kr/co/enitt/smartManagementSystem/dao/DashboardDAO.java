package kr.co.enitt.smartManagementSystem.dao;

import org.apache.ibatis.annotations.Mapper;

import kr.co.enitt.smartManagementSystem.vo.CommonVO;
import kr.co.enitt.smartManagementSystem.vo.DashboardVO;

import java.util.List;

@Mapper
public interface DashboardDAO{
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
	public List<DashboardVO> getEquipmentState(CommonVO vo) throws Exception;
}
