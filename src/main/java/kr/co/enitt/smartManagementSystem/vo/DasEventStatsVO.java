package kr.co.enitt.smartManagementSystem.vo;

public class DasEventStatsVO {
	private String eventStatsTime;
	private String eventStatsTimeGroup;
	private int eventStats01 = 0;
	private int eventStats02 = 0;
	private int eventStats03 = 0;
	private int eventStats04 = 0;
	private int eventStatsTotal = 0;
	

	private String eventTime;
	private String eventTimeGroup;
	

	private String EventDate;
	private String eventDateGroup;
	private String eventCode;
	private String eventCodeName;
	private String eventLine;
	private String eventLineName;
	private int eventCount;
	private Integer eventCountNull;
	
	
	
	public String getEventDate() {
		return EventDate;
	}
	public void setEventDate(String eventDate) {
		EventDate = eventDate;
	}
	public String getEventDateGroup() {
		return eventDateGroup;
	}
	public void setEventDateGroup(String eventDateGroup) {
		this.eventDateGroup = eventDateGroup;
	}
	public String getEventCodeName() {
		return eventCodeName;
	}
	public void setEventCodeName(String eventCodeName) {
		this.eventCodeName = eventCodeName;
	}
	public String getEventLineName() {
		return eventLineName;
	}
	public void setEventLineName(String eventLineName) {
		this.eventLineName = eventLineName;
	}
	public Integer getEventCountNull() {
		return eventCountNull;
	}
	public void setEventCountNull(Integer eventCountNull) {
		this.eventCountNull = eventCountNull;
	}
	public String getEventStatsTime() {
		return eventStatsTime;
	}
	public void setEventStatsTime(String eventStatsTime) {
		this.eventStatsTime = eventStatsTime;
	}
	public String getEventStatsTimeGroup() {
		return eventStatsTimeGroup;
	}
	public void setEventStatsTimeGroup(String eventStatsTimeGroup) {
		this.eventStatsTimeGroup = eventStatsTimeGroup;
	}
	
	public int getEventStats01() {
		return eventStats01;
	}
	public void setEventStats01(int eventStats01) {
		this.eventStats01 = eventStats01;
	}
	public int getEventStats02() {
		return eventStats02;
	}
	public void setEventStats02(int eventStats02) {
		this.eventStats02 = eventStats02;
	}
	public int getEventStats03() {
		return eventStats03;
	}
	public void setEventStats03(int eventStats03) {
		this.eventStats03 = eventStats03;
	}
	public int getEventStats04() {
		return eventStats04;
	}
	public void setEventStats04(int eventStats04) {
		this.eventStats04 = eventStats04;
	}
	public int getEventStatsTotal() {
		return eventStatsTotal;
	}
	public void setEventStatsTotal(int eventStatsTotal) {
		this.eventStatsTotal = eventStatsTotal;
	}
	public String getEventTime() {
		return eventTime;
	}
	public void setEventTime(String eventTime) {
		this.eventTime = eventTime;
	}
	public String getEventTimeGroup() {
		return eventTimeGroup;
	}
	public void setEventTimeGroup(String eventTimeGroup) {
		this.eventTimeGroup = eventTimeGroup;
	}
	public String getEventLine() {
		return eventLine;
	}
	public void setEventLine(String eventLine) {
		this.eventLine = eventLine;
	}
	public String getEventCode() {
		return eventCode;
	}
	public void setEventCode(String eventCode) {
		this.eventCode = eventCode;
	}
	public int getEventCount() {
		return eventCount;
	}
	public void setEventCount(int eventCount) {
		this.eventCount = eventCount;
	}
	
	
}