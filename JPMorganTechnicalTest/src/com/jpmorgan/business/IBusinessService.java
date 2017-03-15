package com.jpmorgan.business;

import java.util.List;
import java.util.Map;

import com.jpmorgan.model.MarketBean;

public interface IBusinessService {
	
	public Map<String,List<MarketBean>> getElementKey(List<MarketBean> listRow);
	
	public void printReport(Map<String,List<MarketBean>> mapEntity);
	
	public String getLineFormat(MarketBean bean);

}
