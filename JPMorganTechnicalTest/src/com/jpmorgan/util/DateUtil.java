package com.jpmorgan.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

import com.jpmorgan.ex.CustomException;

public class DateUtil {
	
	public static final String pattern = "dd-mmm-yyyy";

	/***
	 * 
	 * @param date
	 * @param days
	 * @return Date
	 */
	public static Date addDays(Date date, int days)
    {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, days); //minus number would decrement the days
        return cal.getTime();
    }
	
	
	/***
	 * 
	 * @param date
	 * @return Calendar
	 */
	public static Calendar getCalendarDate(Date date)
    {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        
        return cal;
    }
	
	
	/***
	 * 
	 * @param year
	 * @param month
	 * @param dayOfMonth
	 * @return boolean
	 * 
	 * 
	 */
	public static boolean isSunday(int year,int month,int dayOfMonth) {
		 GregorianCalendar gc = new GregorianCalendar(year, month, dayOfMonth);
		 boolean DAY = false;
		 
		int day = gc.get(Calendar.DAY_OF_WEEK);
        if (day == Calendar.SUNDAY) DAY = true;
        else DAY = false;
       
	  
	    return DAY; // non deve accadere
	}
	
	/**
	 * 
	 * @param year
	 * @param month
	 * @param dayOfMonth
	 * @return boolean
	 * 
	 * 
	 */
	public static boolean isSaturday(int year,int month,int dayOfMonth) {
		 GregorianCalendar gc = new GregorianCalendar(year, month, dayOfMonth);
		 boolean DAY = false;
		 
		 int day = gc.get(Calendar.DAY_OF_WEEK);
	        if (day == Calendar.SATURDAY){
	            DAY = true;
	        }else{
	            DAY = false;
	        }
	  
	    return DAY; // non deve accadere
	}
	
	/**
	 * 
	 * @param data
	 * @return Date : the elaboration date
	 */
	public static Date checkIsSaturdayOfSunday(Date data){
		Calendar cal= getCalendarDate(data);
	 
		int dayOfMonth=cal.DAY_OF_WEEK_IN_MONTH;
		int month=cal.MONTH;
		int year=cal.YEAR;
		
		if(isSaturday(year, month, dayOfMonth)){
			return addDays(data, 2);
		}else if(isSunday(  year,  month, dayOfMonth)){
		    return addDays(data, 1);
		}else{
		    return data;
		}
	}
	
	/***
	 * 
	 * @param date
	 * @return String date Format in String value by dateFormat pattern
	 */
	public static String changeDateToString(Date date){
		String sDate="";
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		sDate=sdf.format(date);
		
		return sDate;
	}
	
	/***
	 * 
	 * @param date
	 * @return String date Format in String value by dateFormat pattern
	 * @throws ParseException 
	 */
	public static Date changeStringToDate(String dateInString)    {
		SimpleDateFormat formatter = new SimpleDateFormat("dd-mm-yyyy");
        
        Date date =new Date();
        try {
             date = formatter.parse(dateInString);
            System.out.println(date);
            System.out.println(formatter.format(date));

        } catch (Exception e) {
            e.printStackTrace();
        } 
        
        return date;
	}
}
