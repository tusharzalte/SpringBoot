package com.training.model;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
@Entity
@Table(name = "batchvisitors")
public class Visitor {
	@Id
	private long visitorId;
	
	private String visitorName;
	private String mobileNumber;
	private String purpose;
	
	
	public Visitor() {
	}
	
	public Visitor(long visitorId, String visitorName, String mobileNumber, String purpose) {
		super();
		this.visitorId = visitorId;
		this.visitorName = visitorName;
		this.mobileNumber = mobileNumber;
		this.purpose = purpose;
	}
	public long getVisitorId() {
		return visitorId;
	}
	public void setVisitorId(int visitorId) {
		this.visitorId = visitorId;
	}
	public String getVisitorName() {
		return visitorName;
	}
	public void setVisitorName(String visitorName) {
		this.visitorName = visitorName;
	}
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public String getPurpose() {
		return purpose;
	}
	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}
	@Override
	public String toString() {
		return "Visitor [visitorId=" + visitorId + ", visitorName=" + visitorName + ", mobileNumber=" + mobileNumber
				+ ", purpose=" + purpose + "]";
	}
}