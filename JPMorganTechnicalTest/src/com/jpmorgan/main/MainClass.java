package com.jpmorgan.main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jpmorgan.business.BusinessService;
import com.jpmorgan.ex.CustomException;
import com.jpmorgan.model.MarketBean;
import com.jpmorgan.util.DateUtil;

public class MainClass {

	/**
	 * @param args
	 */
	public static void main(String[] args)    {
		// TODO Auto-generated method stub
		System.out.println("");
		BusinessService bs=null;
		List<MarketBean> listRow =new ArrayList<MarketBean>();
		
		try {
			listRow = populate();
			bs = new BusinessService(listRow);
			
			Map<String,List<MarketBean>> mapEntity = new HashMap<String,List<MarketBean>>();
			if(!listRow.isEmpty()) mapEntity = bs.getElementKey(listRow);
			if(null!=mapEntity) bs.printReport(mapEntity);
			
		} catch (CustomException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	/***
	 * 
	 * @return
	 * @throws CustomException
	 */
	public static List<MarketBean> populate() throws CustomException{
		
		List<MarketBean> listRow =new ArrayList<MarketBean>();
		MarketBean bean = new MarketBean();
		bean.setEntity("foo");
		 
		bean.setAgreedFx(0.50);
		bean.setBuyAndSell("B");
		bean.setCurrency("SGP");
		bean.setInstructionDate(DateUtil.changeStringToDate("01-01-2016"));
		bean.setUnits(160);
		bean.setPriceUnit(90.25);
		listRow.add(bean);
		
		MarketBean bean1 = new MarketBean();
		bean1.setEntity("foo");
		bean1.setAgreedFx(0.40);
		bean1.setBuyAndSell("B");
		bean1.setCurrency("SGP");
		bean1.setInstructionDate(DateUtil.changeStringToDate("03-01-2016"));
		bean1.setUnits(100);
		bean1.setPriceUnit(60.25);
		listRow.add(bean1);
		
		MarketBean bean2 = new MarketBean();
		bean2.setEntity("bar");
		bean2.setAgreedFx(0.40);
		bean2.setBuyAndSell("B");
		bean2.setCurrency("SGP");
		bean2.setInstructionDate(DateUtil.changeStringToDate("03-01-2016"));
		bean2.setUnits(100);
		bean2.setPriceUnit(90.25);
		listRow.add(bean2);
		
		MarketBean bean3 = new MarketBean();
		bean3.setEntity("bar");
		bean3.setAgreedFx(0.22);
		bean3.setBuyAndSell("S");
		bean3.setCurrency("AED");
		bean3.setInstructionDate(DateUtil.changeStringToDate("07-01-2016"));
		bean3.setPriceUnit(150.5);
		bean3.setUnits(450);
		
		MarketBean bean4 = new MarketBean();
		bean4.setEntity("bar");
		bean4.setAgreedFx(0.22);
		bean4.setBuyAndSell("S");
		bean4.setCurrency("AED");
		bean4.setInstructionDate(DateUtil.changeStringToDate("08-01-2016"));
		bean4.setPriceUnit(150.5);
		bean4.setUnits(250);
		
		listRow.add(bean4);
		
		return listRow;
	}

}
