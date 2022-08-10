package kr.co.enitt.smartManagementSystem.vo;

public class TempVO {
	private String tempId;
	private String tempName;
	private String tempValue;
	private String tempColor;
	private int tempOrder;
	private String registerDate;
	private String registerId;
	private String registerIp;
	private String updateDate;
	private String updateId;
	private String updateIp;
	private String message; 
	private boolean result;
	
	public String getTempId() {
		return tempId;
	}
	public void setTempId(String tempId) {
		this.tempId = tempId;
	}
	public String getTempName() {
		return tempName;
	}
	public void setTempName(String tempName) {
		this.tempName = tempName;
	}
	public String getTempValue() {
		return tempValue;
	}
	public void setTempValue(String tempValue) {
		this.tempValue = tempValue;
	}
	public String getTempColor() {
		return tempColor;
	}
	public void setTempColor(String tempColor) {
		this.tempColor = tempColor;
	}
	
	public int getTempOrder() {
		return tempOrder;
	}
	public void setTempOrder(int tempOrder) {
		this.tempOrder = tempOrder;
	}
	public String getRegisterDate() {
		return registerDate;
	}
	public void setRegisterDate(String registerDate) {
		this.registerDate = registerDate;
	}
	public String getRegisterId() {
		return registerId;
	}
	public void setRegisterId(String registerId) {
		this.registerId = registerId;
	}
	public String getRegisterIp() {
		return registerIp;
	}
	public void setRegisterIp(String registerIp) {
		this.registerIp = registerIp;
	}
	public String getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}
	public String getUpdateId() {
		return updateId;
	}
	public void setUpdateId(String updateId) {
		this.updateId = updateId;
	}
	public String getUpdateIp() {
		return updateIp;
	}
	public void setUpdateIp(String updateIp) {
		this.updateIp = updateIp;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public boolean isResult() {
		return result;
	}
	public void setResult(boolean result) {
		this.result = result;
	}
	
	
}