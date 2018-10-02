package com.wissen.fundprocessor.core;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.wissen.fundprocessor.ent.Context;
import com.wissen.fundprocessor.ent.FundProcessingState;
import com.wissen.fundprocessor.ent.StateDetail;
import com.wissen.fundprocessor.pres.IFundStateProcessor;
import com.wissen.fundprocessor.pres.IStateExecutionHandler;

public class StateExecutionHandler implements IStateExecutionHandler{
	private static final Logger logger = LogManager.getLogger(StateExecutionHandler.class.getName());
	
	public boolean execute(StateDetail stateDetail, Context context) {
		logger.info("Going to execute task:{}", stateDetail);
		FundProcessingState fundProcessingState = stateDetail.getFundProcessingState();
		IFundStateProcessor processor = FundProcessorFactory.getInstance().getFundProcessor(fundProcessingState);
		logger.info("Fund Processor:{}", processor);
		return processor.process(context);
	}

}
