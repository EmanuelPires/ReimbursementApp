package com.models;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class Reimbursement {
	private int reimId;
	private BigDecimal amount;
	private Timestamp subTimeStamp;
	private Timestamp resTimeStamp;
	private String description;
	private int submitterId;
	private int resolverId;
	private int reimStatus;
	private int reimType;
	
	
	
	
	@Override
	public String toString() {
		return "Reimbursement [reimId=" + reimId + ", amount=" + amount + ", subTimeStamp=" + subTimeStamp
				+ ", resTimeStamp=" + resTimeStamp + ", description=" + description + ", submitterId=" + submitterId
				+ ", resolverId=" + resolverId + ", reimStatus=" + reimStatus + ", reimType=" + reimType + "]";
	}


	public Reimbursement(int reimId, BigDecimal amount, Timestamp subTimeStamp, Timestamp resTimeStamp, String description,
			int submitterId, int resolverId, int reimStatus, int reimType) {
		super();
		this.reimId = reimId;
		this.amount = amount;
		this.subTimeStamp = subTimeStamp;
		this.resTimeStamp = resTimeStamp;
		this.description = description;
		this.submitterId = submitterId;
		this.resolverId = resolverId;
		this.reimStatus = reimStatus;
		this.reimType = reimType;
	}
	

	public Reimbursement() {
		super();
	}




	public Reimbursement(BigDecimal reimAmount, String desc, int reimAuthor, int reimStatus, int reimType) {
		// TODO Auto-generated constructor stub
		super();
		this.amount=reimAmount;
		this.description=desc;
		this.submitterId=reimAuthor;
		this.reimStatus=reimStatus;
		this.reimType=reimType;
		
	}


	public int getReimId() {
		return reimId;
	}
	public void setReimId(int reimId) {
		this.reimId = reimId;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	public Timestamp getSubTimeStamp() {
		return subTimeStamp;
	}
	public void setSubTimeStamp(Timestamp subTimeStamp) {
		this.subTimeStamp = subTimeStamp;
	}
	public Timestamp getResTimeStamp() {
		return resTimeStamp;
	}
	public void setResTimeStamp(Timestamp resTimeStamp) {
		this.resTimeStamp = resTimeStamp;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getSubmitterId() {
		return submitterId;
	}
	public void setSubmitterId(int submitterId) {
		this.submitterId = submitterId;
	}
	public int getResolverId() {
		return resolverId;
	}
	public void setResolverId(int resolverId) {
		this.resolverId = resolverId;
	}
	public int getReimStatus() {
		return reimStatus;
	}
	public void setReimStatus(int reimStatus) {
		this.reimStatus = reimStatus;
	}
	public int getReimType() {
		return reimType;
	}
	public void setReimType(int reimType) {
		this.reimType = reimType;
	}

}
