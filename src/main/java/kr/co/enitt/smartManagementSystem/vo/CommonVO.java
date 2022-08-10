package kr.co.enitt.smartManagementSystem.vo;

import java.util.List;
import java.util.Map;

public class CommonVO {
	
	private String menuId;
	private String mode;
	
	//검색
	private String searchType; //검색타입
	private String searchWord; //검색어
	
	//결과
	private Boolean result; // -- 성공 : true, 실패 : false
	private String content; // -- 알람창 내용
	private Object value; // -- 결과값
	private Map<String, Object> valueMap; // -- 결과값
	 
	//페이징 처리
	private String method = "goPage"; // -- 호출 메소드명
	private String pageHtml; // -- 페이지 html
	private int startNum; // -- limit 시작번호
	private int pageSize = 10; // -- 페이지당 보여지는 게시글 수
	private int firstPageNo = 1; // -- 첫번째 페이지 번호
	private int prevPageNo; // -- 이전 페이지 번호
	private int startPageNo; // -- 현재 페이지 기준 시작 페이지
	private int pageNo = 1; // -- 현재 페이지
	private int endPageNo; // -- 현재 페이지 기준 마지막 페이지
	private int nextPageNo; // -- 다음 페이지
	private int finalPageNo; // -- 마지막 페이지 번호
	private int pageCount = 10; // -- 보여지는 페이지 수
	private int totalCount= 0; // -- 총 게시글수
	
	//공통코드 
	private String codeId;
	private String codeName;
	private String parentCode;
	
	//지점 설정
	private String zoneId;
	private String zoneLine;
	private String zoneIdStr;
	
	//온도 단계  설정
	private String tempId;
	
	//이벤트이력
	private String eventTimeStart;
	private String eventTimeEnd;
	private String eventLocationStart; 
	private String eventLocationEnd;
	private String eventCode;
	private String eventZone;
	private String eventLine;
	private String eventTunnel;
	private String dtsEventId;
	private String dasEventId;
	

	//온도
	private String tempTunnel;
	private String tempLocation;
	private String tempTime;
	private String tempLine;
	private String tempDate;
	private String tempSearchType;
	private String tempLocationStart; 
	private String tempLocationEnd;
	private String tempZoneCheck;
	private String tempLimit;
	private String tempHistory;
	
	//통계
	private String searchDay;
	private String searchDayFirst;
	private String searchDayLast;
	private String eventCodeDay;
	private String eventLineDay;
	private String eventZoneDay;
	private String eventTunnelDay;
	
	private String searchMonth;
	private String eventCodeMonth;
	private String eventLineMonth;
	private String eventZoneMonth;
	private String eventTunnelMonth;
	
	private String searchYear;
	private String searchYearEnd;
	private String eventCodeYear;
	private String eventLineYear;
	private String eventZoneYear;
	private String eventTunnelYear;
	
	
	
	//통계
	private String searchTime;
	private String eventCodeTime; 
	private String trainDirectionTime;
	private String trainDirectionDay;
	private String trainDirectionMonth;
	private String trainDirectionYear;
	private String scatterTime; 
	

	private String id; 					//아이디
	private String password;			//비밀번호
	private String newPassword;
	private String passwordChangeYn;
	
	private String dashboard;
	private String dashboard_zoneId;
	private String dashboard_dtsEventId;
	private String dashboard_dasEventId;
	private String dashboard_updateTime = "";
	private Boolean dashboard_order;
	private String checkDtsId;
	private String checkDasId;
	private String dtsLastTime;
	private String dasLastTime;
	private String dasEventDeleteYn;
	private String dtsEventDeleteYn;

	private String now;
	private String nowAgo12H;
	
	private Object resultList;
	public Object getResultList() {
		return resultList;
	}
	public void setResultList(Object resultList) {
		this.resultList = resultList;
	}

	private String _csrf;
	public String get_csrf() {
		return _csrf;
	}
	public void set_csrf(String _csrf) {
		this._csrf = _csrf;
	}
	
	
	
	public String getDtsEventDeleteYn() {
		return dtsEventDeleteYn;
	}
	public void setDtsEventDeleteYn(String dtsEventDeleteYn) {
		this.dtsEventDeleteYn = dtsEventDeleteYn;
	}
	public String getDasEventDeleteYn() {
		return dasEventDeleteYn;
	}
	public void setDasEventDeleteYn(String dasEventDeleteYn) {
		this.dasEventDeleteYn = dasEventDeleteYn;
	}
	public String getNowAgo12H() {
		return nowAgo12H;
	}
	public void setNowAgo12H(String nowAgo12H) {
		this.nowAgo12H = nowAgo12H;
	}
	public String getSearchDayFirst() {
		return searchDayFirst;
	}
	public void setSearchDayFirst(String searchDayFirst) {
		this.searchDayFirst = searchDayFirst;
	}
	public String getSearchDayLast() {
		return searchDayLast;
	}
	public void setSearchDayLast(String searchDayLast) {
		this.searchDayLast = searchDayLast;
	}
	public String getNow() {
		return now;
	}
	public void setNow(String now) {
		this.now = now;
	}
	public String getDtsLastTime() {
		return dtsLastTime;
	}
	public void setDtsLastTime(String dtsLastTime) {
		this.dtsLastTime = dtsLastTime;
	}
	public String getDasLastTime() {
		return dasLastTime;
	}
	public void setDasLastTime(String dasLastTime) {
		this.dasLastTime = dasLastTime;
	}
	public String getZoneIdStr() {
		return zoneIdStr;
	}
	public void setZoneIdStr(String zoneIdStr) {
		this.zoneIdStr = zoneIdStr;
	}
	public String getZoneLine() {
		return zoneLine;
	}
	public void setZoneLine(String zoneLine) {
		this.zoneLine = zoneLine;
	}
	public Boolean getDashboard_order() {
		return dashboard_order;
	}
	public void setDashboard_order(Boolean dashboard_order) {
		this.dashboard_order = dashboard_order;
	}
	public String getTempHistory() {
		return tempHistory;
	}
	public void setTempHistory(String tempHistory) {
		this.tempHistory = tempHistory;
	}
	public String getCheckDtsId() {
		return checkDtsId;
	}
	public void setCheckDtsId(String checkDtsId) {
		this.checkDtsId = checkDtsId;
	}
	public String getCheckDasId() {
		return checkDasId;
	}
	public void setCheckDasId(String checkDasId) {
		this.checkDasId = checkDasId;
	}
	public String getDashboard_updateTime() {
		return dashboard_updateTime;
	}
	public void setDashboard_updateTime(String dashboard_updateTime) {
		this.dashboard_updateTime = dashboard_updateTime;
	}
	public String getDashboard_zoneId() {
		return dashboard_zoneId;
	}
	public void setDashboard_zoneId(String dashboard_zoneId) {
		this.dashboard_zoneId = dashboard_zoneId;
	}
	public String getDashboard_dtsEventId() {
		return dashboard_dtsEventId;
	}
	public void setDashboard_dtsEventId(String dashboard_dtsEventId) {
		this.dashboard_dtsEventId = dashboard_dtsEventId;
	}
	public String getDashboard_dasEventId() {
		return dashboard_dasEventId;
	}
	public void setDashboard_dasEventId(String dashboard_dasEventId) {
		this.dashboard_dasEventId = dashboard_dasEventId;
	}
	public String getDasEventId() {
		return dasEventId;
	}
	public void setDasEventId(String dasEventId) {
		this.dasEventId = dasEventId;
	}
	public String getTempLimit() {
		return tempLimit;
	}
	public void setTempLimit(String tempLimit) {
		this.tempLimit = tempLimit;
	}
	public String getEventTunnelDay() {
		return eventTunnelDay;
	}
	public void setEventTunnelDay(String eventTunnelDay) {
		this.eventTunnelDay = eventTunnelDay;
	}
	public String getEventTunnelMonth() {
		return eventTunnelMonth;
	}
	public void setEventTunnelMonth(String eventTunnelMonth) {
		this.eventTunnelMonth = eventTunnelMonth;
	}
	public String getEventTunnelYear() {
		return eventTunnelYear;
	}
	public void setEventTunnelYear(String eventTunnelYear) {
		this.eventTunnelYear = eventTunnelYear;
	}
	public String getTempDate() {
		return tempDate;
	}
	public void setTempDate(String tempDate) {
		this.tempDate = tempDate;
	}
	public String getTempSearchType() {
		return tempSearchType;
	}
	public void setTempSearchType(String tempSearchType) {
		this.tempSearchType = tempSearchType;
	}
	public String getTempLocationStart() {
		return tempLocationStart;
	}
	public void setTempLocationStart(String tempLocationStart) {
		this.tempLocationStart = tempLocationStart;
	}
	public String getTempLocationEnd() {
		return tempLocationEnd;
	}
	public void setTempLocationEnd(String tempLocationEnd) {
		this.tempLocationEnd = tempLocationEnd;
	}
	public String getTempZoneCheck() {
		return tempZoneCheck;
	}
	public void setTempZoneCheck(String tempZoneCheck) {
		this.tempZoneCheck = tempZoneCheck;
	}
	public String getTempTunnel() {
		return tempTunnel;
	}
	public void setTempTunnel(String tempTunnel) {
		this.tempTunnel = tempTunnel;
	}
	public String getTempId() {
		return tempId;
	}
	public void setTempId(String tempId) {
		this.tempId = tempId;
	}
	public String getZoneId() {
		return zoneId;
	}
	public void setZoneId(String zoneId) {
		this.zoneId = zoneId;
	}
	public String getTempTime() {
		return tempTime;
	}
	public void setTempTime(String tempTime) {
		this.tempTime = tempTime;
	}
	public String getTempLocation() {
		return tempLocation;
	}
	public void setTempLocation(String tempLocation) {
		this.tempLocation = tempLocation;
	}
	public String getTempLine() {
		return tempLine;
	}
	public void setTempLine(String tempLine) {
		this.tempLine = tempLine;
	}
	public String getDtsEventId() {
		return dtsEventId;
	}
	public void setDtsEventId(String dtsEventId) {
		this.dtsEventId = dtsEventId;
	}
	public String getEventLineDay() {
		return eventLineDay;
	}
	public void setEventLineDay(String eventLineDay) {
		this.eventLineDay = eventLineDay;
	}
	public String getEventLineMonth() {
		return eventLineMonth;
	}
	public void setEventLineMonth(String eventLineMonth) {
		this.eventLineMonth = eventLineMonth;
	}
	public String getEventLineYear() {
		return eventLineYear;
	}
	public void setEventLineYear(String eventLineYear) {
		this.eventLineYear = eventLineYear;
	}
	public String getEventZoneDay() {
		return eventZoneDay;
	}
	public void setEventZoneDay(String eventZoneDay) {
		this.eventZoneDay = eventZoneDay;
	}
	public String getEventZoneMonth() {
		return eventZoneMonth;
	}
	public void setEventZoneMonth(String eventZoneMonth) {
		this.eventZoneMonth = eventZoneMonth;
	}
	public String getEventZoneYear() {
		return eventZoneYear;
	}
	public void setEventZoneYear(String eventZoneYear) {
		this.eventZoneYear = eventZoneYear;
	}
	public String getEventTunnel() {
		return eventTunnel;
	}
	public void setEventTunnel(String eventTunnel) {
		this.eventTunnel = eventTunnel;
	}
	public String getEventZone() {
		return eventZone;
	}
	public void setEventZone(String eventZone) {
		this.eventZone = eventZone;
	}
	public String getEventLine() {
		return eventLine;
	}
	public void setEventLine(String eventLine) {
		this.eventLine = eventLine;
	}
	public String getScatterTime() {
		return scatterTime;
	}
	public void setScatterTime(String scatterTime) {
		this.scatterTime = scatterTime;
	}
	public String getPasswordChangeYn() {
		return passwordChangeYn;
	}
	public void setPasswordChangeYn(String passwordChangeYn) {
		this.passwordChangeYn = passwordChangeYn;
	}
	public String getSearchYearEnd() {
		return searchYearEnd;
	}
	public void setSearchYearEnd(String searchYearEnd) {
		this.searchYearEnd = searchYearEnd;
	}
	public String getTrainDirectionTime() {
		return trainDirectionTime;
	}
	public void setTrainDirectionTime(String trainDirectionTime) {
		this.trainDirectionTime = trainDirectionTime;
	}
	public String getSearchTime() {
		return searchTime;
	}
	public void setSearchTime(String searchTime) {
		this.searchTime = searchTime;
	}
	public String getEventCodeTime() {
		return eventCodeTime;
	}
	public void setEventCodeTime(String eventCodeTime) {
		this.eventCodeTime = eventCodeTime;
	}
	public String getDashboard() {
		return dashboard;
	}
	public void setDashboard(String dashboard) {
		this.dashboard = dashboard;
	}
	public String getNewPassword() {
		return newPassword;
	}
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getTrainDirectionDay() {
		return trainDirectionDay;
	}
	public void setTrainDirectionDay(String trainDirectionDay) {
		this.trainDirectionDay = trainDirectionDay;
	}
	public String getTrainDirectionMonth() {
		return trainDirectionMonth;
	}
	public void setTrainDirectionMonth(String trainDirectionMonth) {
		this.trainDirectionMonth = trainDirectionMonth;
	}
	public String getTrainDirectionYear() {
		return trainDirectionYear;
	}
	public void setTrainDirectionYear(String trainDirectionYear) {
		this.trainDirectionYear = trainDirectionYear;
	}
	public String getEventCodeDay() {
		return eventCodeDay;
	}
	public void setEventCodeDay(String eventCodeDay) {
		this.eventCodeDay = eventCodeDay;
	}
	public String getEventCodeMonth() {
		return eventCodeMonth;
	}
	public void setEventCodeMonth(String eventCodeMonth) {
		this.eventCodeMonth = eventCodeMonth;
	}
	public String getEventCodeYear() {
		return eventCodeYear;
	}
	public void setEventCodeYear(String eventCodeYear) {
		this.eventCodeYear = eventCodeYear;
	}
	public String getSearchDay() {
		return searchDay;
	}
	public void setSearchDay(String searchDay) {
		this.searchDay = searchDay;
	}
	public String getSearchMonth() {
		return searchMonth;
	}
	public void setSearchMonth(String searchMonth) {
		this.searchMonth = searchMonth;
	}
	public String getSearchYear() {
		return searchYear;
	}
	public void setSearchYear(String searchYear) {
		this.searchYear = searchYear;
	}
	public String getEventTimeStart() {
		return eventTimeStart;
	}
	public void setEventTimeStart(String eventTimeStart) {
		this.eventTimeStart = eventTimeStart;
	}
	public String getEventTimeEnd() {
		return eventTimeEnd;
	}
	public void setEventTimeEnd(String eventTimeEnd) {
		this.eventTimeEnd = eventTimeEnd;
	}
	public String getEventLocationStart() {
		return eventLocationStart;
	}
	public void setEventLocationStart(String eventLocationStart) {
		this.eventLocationStart = eventLocationStart;
	}
	public String getEventLocationEnd() {
		return eventLocationEnd;
	}
	public void setEventLocationEnd(String eventLocationEnd) {
		this.eventLocationEnd = eventLocationEnd;
	}
	public String getMenuId() {
		return menuId;
	}
	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}
	public String getMode() {
		return mode;
	}
	public void setMode(String mode) {
		this.mode = mode;
	}
	public String getSearchType() {
		return searchType;
	}
	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}
	public String getSearchWord() {
		return searchWord;
	}
	public void setSearchWord(String searchWord) {
		this.searchWord = searchWord;
	}
	public Boolean getResult() {
		return result;
	}
	public void setResult(Boolean result) {
		this.result = result;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Object getValue() {
		return value;
	}
	public void setValue(Object value) {
		this.value = value;
	}
	public Map<String, Object> getValueMap() {
		return valueMap;
	}
	public void setValueMap(Map<String, Object> valueMap) {
		this.valueMap = valueMap;
	}
	public String getCodeId() {
		return codeId;
	}
	public void setCodeId(String codeId) {
		this.codeId = codeId;
	}
	public String getCodeName() {
		return codeName;
	}
	public void setCodeName(String codeName) {
		this.codeName = codeName;
	}
	public String getParentCode() {
		return parentCode;
	}
	public void setParentCode(String parentCode) {
		this.parentCode = parentCode;
	}
	public String getEventCode() {
		return eventCode;
	}
	public void setEventCode(String eventCode) {
		this.eventCode = eventCode;
	}
	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getPageHtml() {
		return pageHtml;
	}

	public void setPageHtml(String pageHtml) {
		this.pageHtml = pageHtml;
	}

	public int getStartNum() {
		return startNum;
	}

	public void setStartNum(int startNum) {
		this.startNum = startNum;
	}

	public int getFirstPageNo() {
		return firstPageNo;
	}

	public void setFirstPageNo(int firstPageNo) {
		this.firstPageNo = firstPageNo;
	}

	public int getPrevPageNo() {
		return prevPageNo;
	}

	public void setPrevPageNo(int prevPageNo) {
		this.prevPageNo = prevPageNo;
	}

	public int getStartPageNo() {
		return startPageNo;
	}

	public void setStartPageNo(int startPageNo) {
		this.startPageNo = startPageNo;
	}

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public int getEndPageNo() {
		return endPageNo;
	}

	public void setEndPageNo(int endPageNo) {
		this.endPageNo = endPageNo;
	}

	public int getNextPageNo() {
		return nextPageNo;
	}

	public void setNextPageNo(int nextPageNo) {
		this.nextPageNo = nextPageNo;
	}

	public int getFinalPageNo() {
		return finalPageNo;
	} 

	public void setFinalPageNo(int finalPageNo) {
		this.finalPageNo = finalPageNo;
	}

	public int getPageCount() {
		return pageCount;
	}

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}

	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
		makePaging();
		pageHtml();
	}
	
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	
	private void makePaging() {
		// -- 전체 글수가 없을 경우
		if (totalCount == 0) {
			return;
		} 
		
		int finalPage = (totalCount + (pageSize - 1)) / pageSize;
		if (pageNo > finalPage) {
			this.pageNo = finalPage;
		}

		int startPage = ((pageNo - 1) / pageCount) * pageCount + 1;
		int endPage = startPage + pageCount - 1;
		if (endPage > finalPage) {
			endPage = finalPage;
		}

		if (startPage == 1) {
			this.prevPageNo = 1;
		} else {
			this.prevPageNo = startPage - 1;
		}

		if (endPage == finalPage) {
			this.nextPageNo = endPage;
		} else {
			this.nextPageNo = endPage + 1;
		}

		this.startPageNo = startPage;
		this.endPageNo = endPage;
		this.finalPageNo = finalPage;
		this.startNum = (pageNo - 1) * pageSize;
	}
	
	private void pageHtml() {
		StringBuilder html = new StringBuilder();

		
		html.append("<span class=\"inner\">");
		html.append("<a class=\"pageFirst\" href=\"javascript:" + method + "(" + firstPageNo + ")\">" + "<img src=\"/resources/css/lib/images/btn_page_first.gif\" alt=\"첫페이지\" />" + "</a>");
		html.append("<a class=\"pagePrev\" href=\"javascript:" + method + "(" + prevPageNo + ")\">" + "<img src=\"/resources/css/lib/images/btn_page_pre.gif\" alt=\"이전페이지\" />" + "</a>");
		
		for (int i = startPageNo; i <= endPageNo; i++) {
			if (i == pageNo) {
				html.append("<a class=\"pageNow\" href=\"#\">" + i + "</a>");
			} else {
				html.append("<a class=\"pageNone\" href=\"javascript:" + method + "(" + i + ")\">" + i + "</a>");
			}
		}
		
		html.append("<a class=\"pageNex\" href=\"javascript:" + method + "(" + nextPageNo + ")\">" + "<img src=\"/resources/css/lib/images/btn_page_next.gif\" alt=\"다음페이지\" />" + "</a>");
		html.append("<a class=\"pageLast\" href=\"javascript:" + method + "(" + finalPageNo + ")\">" + "<img src=\"/resources/css/lib/images/btn_page_last.gif\" alt=\"마지막페이지\" />" + "</a>");
		html.append("</span>");

		this.pageHtml = html.toString();		
		
		
	}
}