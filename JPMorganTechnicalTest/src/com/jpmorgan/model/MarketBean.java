package com.jpmorgan.model;

import java.io.Serializable;
import java.util.Date;

public class MarketBean implements Serializable{
	
	public MarketBean() {}
	
	String entity ;
	public String getEntity() {
		return entity;
	}
	public String getBuyAndSell() {
		return buyAndSell;
	}
	public double getAgreedFx() {
		return agreedFx;
	}
	public String getCurrency() {
		return currency;
	}
	public Date getInstructionDate() {
		return instructionDate;
	}
	public Date getSettlementDate() {
		return settlementDate;
	}
	public int getUnits() {
		return units;
	}
	public double  getPriceUnit() {
		return priceUnit;
	}
	public void setEntity(String entity) {
		this.entity = entity;
	}
	public void setBuyAndSell(String buyAndSell) {
		this.buyAndSell = buyAndSell;
	}
	public void setAgreedFx(double agreedFx) {
		this.agreedFx = agreedFx;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public void setInstructionDate(Date instructionDate) {
		this.instructionDate = instructionDate;
	}
	public void setSettlementDate(Date settlementDate) {
		this.settlementDate = settlementDate;
	}
	
	public void setUnits(int units) {
		this.units = units;
	}
	public void setPriceUnit(double  priceUnit) {
		this.priceUnit = priceUnit;
	}
	
	public double getOutgoingRank() {
		return outgoingRank;
	}
	public void setOutgoingRank(double outgoingRank) {
		this.outgoingRank = outgoingRank;
	}

	private String buyAndSell;
	private double agreedFx;
	private String currency;
	private Date instructionDate;
	private Date settlementDate;
	private int units ;
	private double  priceUnit;
	private double outgoingRank ;
	
	 
	

}
