package kr.co.enitt.smartManagementSystem.service;

import java.util.List;

import kr.co.enitt.smartManagementSystem.vo.MenuVO;

public interface MenuService {
	//메뉴 목록
	List<MenuVO> getMenuList(MenuVO vo) throws Exception;
	//메뉴 상세
	MenuVO getMenu(MenuVO vo) throws Exception;
}
