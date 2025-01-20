package com.training.model;
import org.springframework.stereotype.Component;

@Component
public class Visitor {
	  private int Visitor_id;
	  private String VisitorName;
	  private String mobilNumber;
	  private String purpose;
	  
	  
	  public Visitor()
	  {
		  
	  }
public Visitor(int visitor_id, String visitorName, String mobilNumber, String purpose) {
		super();
		Visitor_id = visitor_id;
		VisitorName = visitorName;
		this.mobilNumber = mobilNumber;
		this.purpose = purpose;
	}
//
//	public Visitor(int Visitor_id, String customerName, String mobilNumber, String purpose) {
//		super();
//		this.Visitor_id = Visitor_id;
//		this.CustomerName = customerName;
//		this.mobilNumber = mobilNumber;
//		this.purpose = purpose;
//	}
//
//	public int getCustomer_id() {
//		return Visitor_id;
//	}
//
//	public void setCustomer_id(int Visitor_id) {
//		this.Visitor_id = Visitor_id;
//	}
//
//	public String getCustomerName() {
//		return CustomerName;
//	}
//
//	public void setCustomerName(String customerName) {
//		CustomerName = customerName;
//	}
//
//	public String getMobilNumber() {
//		return mobilNumber;
//	}
//
//	public void setMobilNumber(String mobilNumber) {
//		this.mobilNumber = mobilNumber;
//	}
//
//	public String getPurpose() {
//		return purpose;
//	}
//
//	public void setPurpose(String purpose) {
//		this.purpose = purpose;
//	}


	public int getVisitor_id() {
		return Visitor_id;
	}


	public void setVisitor_id(int visitor_id) {
		Visitor_id = visitor_id;
	}


	public String getVisitorName() {
		return VisitorName;
	}


	public void setVisitorName(String visitorName) {
		VisitorName = visitorName;
	}


	public String getMobilNumber() {
		return mobilNumber;
	}


	public void setMobilNumber(String mobilNumber) {
		this.mobilNumber = mobilNumber;
	}


	public String getPurpose() {
		return purpose;
	}


	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}
}
