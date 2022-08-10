package kr.co.enitt.smartManagementSystem.vo;

public class DasEventVO {
	private String dasEventId;
	private String dasEventCode;
	private String dasEventCodeNm;
	private String dasEventTime;
	private String dasEventUpdateTime;
	private String dasEventTimeYear;
	private String dasEventTimeMonth;
	private String dasEventTimeDay;
	private String dasEventTimeHour;
	private String dasEventTimeMinute;
	private String dasEventTimeSecond;
	private int dasEventLocation;
	private String dasEventLocationStr;
	private String dasEventTunnel;
	private String dasEventTunnelNm;
	private String dasEventZone;
	private String dasEventZoneNm;
	private String dasEventState;
	private String dasEventRegisterDate;
	private String dasEventDeleteYn;

	private String codeId;
	private String codeName;
	private int eventCnt;
	
	

	public String getDasEventDeleteYn() {
		return dasEventDeleteYn;
	}
	public void setDasEventDeleteYn(String dasEventDeleteYn) {
		this.dasEventDeleteYn = dasEventDeleteYn;
	}
	public String getDasEventLocationStr() {
		return dasEventLocationStr;
	}
	public void setDasEventLocationStr(String dasEventLocationStr) {
		this.dasEventLocationStr = dasEventLocationStr;
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
	public int getEventCnt() {
		return eventCnt;
	}
	public void setEventCnt(int eventCnt) {
		this.eventCnt = eventCnt;
	}
	public String getDasEventUpdateTime() {
		return dasEventUpdateTime;
	}
	public void setDasEventUpdateTime(String dasEventUpdateTime) {
		this.dasEventUpdateTime = dasEventUpdateTime;
	}
	public String getDasEventTimeYear() {
		return dasEventTimeYear;
	}
	public void setDasEventTimeYear(String dasEventTimeYear) {
		this.dasEventTimeYear = dasEventTimeYear;
	}
	public String getDasEventTimeMonth() {
		return dasEventTimeMonth;
	}
	public void setDasEventTimeMonth(String dasEventTimeMonth) {
		this.dasEventTimeMonth = dasEventTimeMonth;
	}
	public String getDasEventTimeDay() {
		return dasEventTimeDay;
	}
	public void setDasEventTimeDay(String dasEventTimeDay) {
		this.dasEventTimeDay = dasEventTimeDay;
	}
	public String getDasEventTimeHour() {
		return dasEventTimeHour;
	}
	public void setDasEventTimeHour(String dasEventTimeHour) {
		this.dasEventTimeHour = dasEventTimeHour;
	}
	public String getDasEventTimeMinute() {
		return dasEventTimeMinute;
	}
	public void setDasEventTimeMinute(String dasEventTimeMinute) {
		this.dasEventTimeMinute = dasEventTimeMinute;
	}
	public String getDasEventTimeSecond() {
		return dasEventTimeSecond;
	}
	public void setDasEventTimeSecond(String dasEventTimeSecond) {
		this.dasEventTimeSecond = dasEventTimeSecond;
	}
	public String getDasEventId() {
		return dasEventId;
	}
	public void setDasEventId(String dasEventId) {
		this.dasEventId = dasEventId;
	}
	public String getDasEventCode() {
		return dasEventCode;
	}
	public void setDasEventCode(String dasEventCode) {
		this.dasEventCode = dasEventCode;
	}
	public String getDasEventCodeNm() {
		return dasEventCodeNm;
	}
	public void setDasEventCodeNm(String dasEventCodeNm) {
		this.dasEventCodeNm = dasEventCodeNm;
	}
	public String getDasEventTime() {
		return dasEventTime;
	}
	public void setDasEventTime(String dasEventTime) {
		this.dasEventTime = dasEventTime;
	}
	public int getDasEventLocation() {
		return dasEventLocation;
	}
	public void setDasEventLocation(int dasEventLocation) {
		this.dasEventLocation = dasEventLocation;
	}
	public String getDasEventTunnel() {
		return dasEventTunnel;
	}
	public void setDasEventTunnel(String dasEventTunnel) {
		this.dasEventTunnel = dasEventTunnel;
	}
	public String getDasEventTunnelNm() {
		return dasEventTunnelNm;
	}
	public void setDasEventTunnelNm(String dasEventTunnelNm) {
		this.dasEventTunnelNm = dasEventTunnelNm;
	}
	public String getDasEventZone() {
		return dasEventZone;
	}
	public void setDasEventZone(String dasEventZone) {
		this.dasEventZone = dasEventZone;
	}
	public String getDasEventZoneNm() {
		return dasEventZoneNm;
	}
	public void setDasEventZoneNm(String dasEventZoneNm) {
		this.dasEventZoneNm = dasEventZoneNm;
	}
	public String getDasEventState() {
		return dasEventState;
	}
	public void setDasEventState(String dasEventState) {
		this.dasEventState = dasEventState;
	}
	public String getDasEventRegisterDate() {
		return dasEventRegisterDate;
	}
	public void setDasEventRegisterDate(String dasEventRegisterDate) {
		this.dasEventRegisterDate = dasEventRegisterDate;
	}
	
	@Override
	public String toString() {
		return "DasEventVO [dasEventId=" + dasEventId + ", dasEventDeleteYn="
				+ dasEventDeleteYn + "]";
	}
	
	
}