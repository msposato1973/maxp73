package com.jpmorgan.util;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import com.jpmorgan.constant.JPMConstant;
import com.jpmorgan.model.MarketBean;

public class UtilityTrak {
	 
	public static final Date midAugust;
	public static final Date cristmas;
	public static final Date endofYear;
	

	/* A work week starts Monday and ends Friday -  List of working days if currency  trade is different from AED and SA*/
	public static final List<Integer> daylist ;
	
	/* the work week starts Sunday and ends Thursday - List of working days if currency trade is AED or SA*/
	public static final List<Integer> daylistSarAed ;
	
	/*  List of working days assigned valid in the both case*/
	public static List<Integer> daylistcheck ;
	
 
	//I create lists of days to compare and types of holidays where it needs to be worked
	//I added this control, to compare that the InstructionDate  does not coincide with these days of celebrations
	static {
		Calendar now = Calendar.getInstance();
		int currentYear = now.get(Calendar.YEAR);
		
		cristmas = new GregorianCalendar(currentYear, 12, 25).getTime();
		midAugust = new GregorianCalendar(currentYear, 8, 15).getTime();
		endofYear = new GregorianCalendar(currentYear, 12, 31).getTime();
		
		daylist = new ArrayList<Integer>();
		daylist.add(Calendar.MONDAY);
        daylist.add(Calendar.TUESDAY);
        daylist.add(Calendar.WEDNESDAY);
        daylist.add(Calendar.THURSDAY);
        daylist.add(Calendar.FRIDAY);
        
        daylistSarAed = new ArrayList<Integer>();
        daylistSarAed.add(Calendar.SUNDAY);
        daylistSarAed.add(Calendar.MONDAY);
        daylistSarAed.add(Calendar.TUESDAY);
        daylistSarAed.add(Calendar.WEDNESDAY);
        daylistSarAed.add(Calendar.THURSDAY);
	}
	
	
	 
	
	
	/**
	 * 
	 * @param year
	 * @param month
	 * @param dayOfMonth
	 * @param currency
	 * @return
	 * 
	 * check the type of currency and the septum of the interval of days valid list.
	 * If an instructed settlement date falls on a weekend, then the settlement date should be changed to the next working day
	 */
	public  boolean isWeekEnd(int year,int month,int dayOfMonth,String currency) {
		 GregorianCalendar gc = new GregorianCalendar(year, month, dayOfMonth);
		 boolean DAY = false;
		 daylistcheck = new ArrayList<Integer>();
			
		 if(currency.equalsIgnoreCase(JPMConstant.AD)||currency.equalsIgnoreCase(JPMConstant.SR)) daylistcheck = daylistSarAed ; 
		 else daylistcheck = daylist;
		 
		 int day = gc.get(Calendar.DAY_OF_WEEK);
         if (daylistcheck.contains(day)) DAY = false;
         else DAY = true;
          
	  
	    return DAY; // it's not happener
	  }
	
	 
	
	/***
	 * 
	 * @param bean
	 * @return double  trade
	 * 
	 * USD amount of a trade = Price per unit * Units * Agreed Fx
	 */
	public double useParAmmont(MarketBean bean){
		 
		double  trade = bean.getAgreedFx() * (bean.getUnits() * bean.getPriceUnit()  ) ;
		
		return trade;
	}

 
	/***
	 * 
	 * @param listRow
	 * @return double  topAmounts
	 * 
	 * Method to find the top PriceUnit, can be called on a list of records,
	 * used only in the preliminary phase of analysis, when it is no longer used,
	 */
   
	public   double topAmount(List<MarketBean> listRow){
		double  topAmounts=0.0;
		
		for (MarketBean bean: listRow) {
			bean.setOutgoingRank(useParAmmont(bean));
			if(Double.compare(topAmounts, bean.getPriceUnit())>0)  topAmounts = bean.getPriceUnit();
		}
	 
		 
	
	return topAmounts;
	}

	//Method to find the top topAmounts, can be called on a list of records
	public  void topRankAmount(double  topAmounts, List<MarketBean> listRow){
		    MarketBean beanFind = new MarketBean();
			for (MarketBean bean: listRow) {
				if(Double.compare(topAmounts, bean.getPriceUnit())>0) beanFind = bean;
			}
	}

	 
 
	/***
	 * 
	 * @param date1
	 * @return boolean
	 * 
	 * Conduct an audit that the date does not fall on a public holiday,
	 */
	public static boolean daysDiff( Date date1 ) {
		 
		if(cristmas.compareTo(date1)>0) return true;
		else if(midAugust.compareTo(date1)>0) return true;
		else if(endofYear.compareTo(date1)>0) return true;
		else return false;
        
    }
	
	/***
	 * 
	 * @param instructionDate
	 * @param currency
	 * @return
	 * 
	 * Method where all the controls are carried out on the date that does not coincide with the feast of weeks and holidays or days
	 */
	public  Date setSettlementDate(Date instructionDate,String currency){
		 
		Date settlementDate = new Date();
		settlementDate = DateUtil.addDays(instructionDate, 1);
		
		
		Calendar cal = DateUtil.getCalendarDate(settlementDate);
		int year = cal.YEAR ;
		int month = cal.MONTH;
		int dayOfMonth = cal.DAY_OF_WEEK;
		
			if((cristmas.compareTo(settlementDate)==0) || (endofYear.compareTo(settlementDate)==0)){
				settlementDate = DateUtil.addDays(settlementDate, 2);
			}else if(midAugust.compareTo(settlementDate)==0) {
				settlementDate = DateUtil.addDays(settlementDate, 1);
			}else {
				if(isWeekEnd(year, month, dayOfMonth, currency )) 
					settlementDate = DateUtil.addDays(settlementDate, 1);
				else 
					settlementDate = DateUtil.addDays(settlementDate, 2);
			
		   }
			 
			settlementDate = DateUtil.checkIsSaturdayOfSunday(settlementDate);
		
		return settlementDate;
		
	}
	
	/***
	 * 
	 * @param entity
	 * @param listRow
	 * @return
	 * 
	 * I return the sublist of items for entity
	 */
	public List<MarketBean> getSubListParEntity(String entity,List<MarketBean> listRow){
		List<MarketBean> subList=new ArrayList<MarketBean>();
		
		for (MarketBean bean: listRow) {
			if(entity.equalsIgnoreCase(bean.getEntity())) {
				bean.setOutgoingRank(useParAmmont(bean));
				bean.setSettlementDate(setSettlementDate(bean.getInstructionDate(), entity));
				subList.add(bean);
			}
		}
		
		
		subList = new ModelComparator(subList).getStudList();
		 
		return subList;
	}
	 

}
