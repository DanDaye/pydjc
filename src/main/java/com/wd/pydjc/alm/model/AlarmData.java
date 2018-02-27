package com.wd.pydjc.alm.model;

import java.math.BigDecimal;
import java.util.Date;

import com.wd.pydjc.sys.model.BaseEntity;

public class AlarmData extends BaseEntity<Long> {

	private static final long serialVersionUID = -6525908145032868837L;

	private Long deviceId;
	private Long measPointId;
	private Long alarmTypeId;
	private String content;
	private BigDecimal alarmValue;
	private String status;
	private Date beginTime;
	private Date endTime;
	private String cfmFlag;
	private Long customerId;
	private String rdpNo;
	private String deviceAddr;
	
	private String deviceName;
	private String measPointName;
	private String alarmTypeName;
	
	public Long getDeviceId() {
		return deviceId;
	}
	public void setDeviceId(Long deviceId) {
		this.deviceId = deviceId;
	}
	public Long getMeasPointId() {
		return measPointId;
	}
	public void setMeasPointId(Long measPointId) {
		this.measPointId = measPointId;
	}
	public Long getAlarmTypeId() {
		return alarmTypeId;
	}
	public void setAlarmTypeId(Long alarmTypeId) {
		this.alarmTypeId = alarmTypeId;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public BigDecimal getAlarmValue() {
		return alarmValue;
	}
	public void setAlarmValue(BigDecimal alarmValue) {
		this.alarmValue = alarmValue;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Date getBeginTime() {
		return beginTime;
	}
	public void setBeginTime(Date beginTime) {
		this.beginTime = beginTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public String getCfmFlag() {
		return cfmFlag;
	}
	public void setCfmFlag(String cfmFlag) {
		this.cfmFlag = cfmFlag;
	}
	public Long getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}
	public String getRdpNo() {
		return rdpNo;
	}
	public void setRdpNo(String rdpNo) {
		this.rdpNo = rdpNo;
	}
	public String getDeviceAddr() {
		return deviceAddr;
	}
	public void setDeviceAddr(String deviceAddr) {
		this.deviceAddr = deviceAddr;
	}
	public String getDeviceName() {
		return deviceName;
	}
	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}
	public String getMeasPointName() {
		return measPointName;
	}
	public void setMeasPointName(String measPointName) {
		this.measPointName = measPointName;
	}
	public String getAlarmTypeName() {
		return alarmTypeName;
	}
	public void setAlarmTypeName(String alarmTypeName) {
		this.alarmTypeName = alarmTypeName;
	}
	
	
}
