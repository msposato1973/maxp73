package com.jpmorgan.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.jpmorgan.model.MarketBean;

public class ModelComparator  {
	
	public List<MarketBean> studList=new  ArrayList<MarketBean>();
	
	
	public List<MarketBean> getStudList() {
		return studList;
	}

	public void setStudList(List<MarketBean> studList) {
		this.studList = studList;
	}
	
	/**
	 * 
	 * @param studList
	 *  - I check the list of items not ordered the manufacturer to manufacturer
	 *  - Add the list as an instance member, 
	 * -  Called the method that performs the sort on the list of items
	 */
	public ModelComparator(List<MarketBean> studList ){
		setStudList(studList);
		setModelComparator();
	}

	public ModelComparator(){}
	
	
	 
	public void setModelComparator(){
		
		Collections.sort(this.studList, new Comparator<MarketBean>(){
		    public int compare(MarketBean s1, MarketBean s2) {
		        return Double.valueOf(s1.getOutgoingRank()).compareTo(s2.getOutgoingRank());
		    }
		});
		
		
	}
	
	/**
	 * 
	 * @param studList
	 * @return List<MarketBean> order List by OutgoingRank properties;
	 * 
	 */
	public List<MarketBean> setModelComparator(List<MarketBean> studList){
		
		Collections.sort(studList, new Comparator<MarketBean>(){
		    public int compare(MarketBean s1, MarketBean s2) {
		        return Double.valueOf(s1.getOutgoingRank()).compareTo(s2.getOutgoingRank());
		    }
		});
		
		return studList;
	}
	

}
