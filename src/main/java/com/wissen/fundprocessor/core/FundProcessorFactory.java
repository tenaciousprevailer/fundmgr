package com.wissen.fundprocessor.core;

import java.util.HashMap;

import com.wissen.fundprocessor.core.processor.DivisionHeadProcessor;
import com.wissen.fundprocessor.core.processor.FundManagerProcessor;
import com.wissen.fundprocessor.core.processor.OperationsProcessor;
import com.wissen.fundprocessor.core.processor.ResearchAnalystProcessor;
import com.wissen.fundprocessor.core.processor.TraderFundRaiseProcessor;
import com.wissen.fundprocessor.ent.FundProcessingState;
import com.wissen.fundprocessor.pres.IFundStateProcessor;

public class FundProcessorFactory {

	private final HashMap<FundProcessingState, IFundStateProcessor> fundProcessorsMap;
	
	private FundProcessorFactory() {
		// initialize
		fundProcessorsMap = new HashMap<>();
		fundProcessorsMap.put(FundProcessingState.TRADER_RAISE_FUND, new TraderFundRaiseProcessor());
		fundProcessorsMap.put(FundProcessingState.RESEARCH_ANALYST_APPROVAL, new ResearchAnalystProcessor());
		fundProcessorsMap.put(FundProcessingState.FUND_MANAGER_APPROVAL, new FundManagerProcessor());
		fundProcessorsMap.put(FundProcessingState.DIVISION_HEAD_APPROVAL, new DivisionHeadProcessor());
		fundProcessorsMap.put(FundProcessingState.OPERATIONS_APPROVAL, new OperationsProcessor());
	}
	
	public static FundProcessorFactory getInstance() {
		return SingletonHelper.INSTANCE;
	}
	
	private static class SingletonHelper{
		private static final FundProcessorFactory INSTANCE = new FundProcessorFactory();
	}
	
	public IFundStateProcessor getFundProcessor(final FundProcessingState fundProcessState) {
		return fundProcessorsMap.get(fundProcessState);
	}

}
