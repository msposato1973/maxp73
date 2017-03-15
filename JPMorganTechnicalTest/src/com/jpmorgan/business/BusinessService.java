package com.jpmorgan.business;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.jpmorgan.ex.CustomException;
import com.jpmorgan.model.MarketBean;
import com.jpmorgan.util.*;


public class BusinessService implements IBusinessService{
	
	public List<MarketBean> listRow =new ArrayList<MarketBean> ();
	

	public BusinessService(List<MarketBean> listRow){
		this.listRow = listRow;
	}
	
	
	
	/***
	 * @param List,
	 * @return Map
	 * 
	 */
	
	public Map<String,List<MarketBean>> getElementKey(List<MarketBean> listRow){
		
		String keyEntity = "";
		UtilityTrak utilTrank=new UtilityTrak();
		List<MarketBean> listRowValue=null;
		Map<String,List<MarketBean>> mapEntity = new HashMap<String,List<MarketBean>>();
		
		for(MarketBean bean:listRow){
			keyEntity = bean.getEntity();
			if(!mapEntity.containsKey(keyEntity)){
				listRowValue = new ArrayList<MarketBean> ();
				listRowValue = utilTrank.getSubListParEntity(keyEntity, listRow);
				if(null!=listRowValue && !listRowValue.isEmpty() && listRowValue.size()>0) {
					mapEntity.put(keyEntity, listRowValue);
				}
			}
			
		}
		
		return mapEntity;
		
	}



	/**
	 * @param Map
	 * 
	 */
	@Override
	public void printReport(Map<String, List<MarketBean>> mapEntity) {
		Iterator<String> iter=mapEntity.keySet().iterator();
		System.out.println("Entity   |   Buy/Sell |   AgreedFx |   Currency |   InstructionDate | SettlementDate   |   Units |   Price per unit");
		while(iter.hasNext()){
			List<MarketBean> list =(List<MarketBean>) mapEntity.get(iter.next());mapEntity.get(iter.next());
			MarketBean bean = (MarketBean)list.get(0);
			System.out.println(getLineFormat(bean));
		}
	}



	
/**
 * @return String
 * 
 * Description: I create the print formatting of the report row.
 */

	@Override
	public String getLineFormat(MarketBean bean) {
		
		StringBuilder lineFormat = new StringBuilder(bean.getEntity() + " | ") ;
					  lineFormat.append(bean.getBuyAndSell() + " | ");  
					  lineFormat.append(bean.getAgreedFx()   + " | ");
					  lineFormat.append(bean.getCurrency()	 + " | ");
					  lineFormat.append(DateUtil.changeDateToString(bean.getInstructionDate()) + " | ");
					  lineFormat.append(DateUtil.changeDateToString(bean.getSettlementDate())  + " | ");
					  lineFormat.append(bean.getUnits()	     + " | ");
					  lineFormat.append(bean.getPriceUnit()  + " | ")  ;
		
		
		return lineFormat.toString();
	}
	
}
