package kr.co.enitt.smartManagementSystem.service;

import kr.co.enitt.smartManagementSystem.vo.CommonVO;
import kr.co.enitt.smartManagementSystem.vo.MemberVO;

public interface LoginService {
	
	MemberVO ajaxLogin(CommonVO vo) throws Exception;
}
