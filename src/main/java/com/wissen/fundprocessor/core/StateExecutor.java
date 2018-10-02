package com.wissen.fundprocessor.core;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.wissen.fundprocessor.ent.Context;
import com.wissen.fundprocessor.ent.StateDetail;
import com.wissen.fundprocessor.ent.StateExecutionType;
import com.wissen.fundprocessor.pres.IStateExecutionHandler;

public class StateExecutor implements IStateExecutionHandler{
	private static final Logger logger = LogManager.getLogger(StateExecutor.class.getName());
	
	public boolean execute(StateDetail stateDetail, Context context) {
		logger.debug("Going to execute task:{}", stateDetail);
		StateExecutionType executionType = stateDetail.getExecutionType();
		IStateExecutionHandler executionHandler = StateExecutorFactory.getExecutionHandler(executionType);
		logger.debug("Execution handler:{}", executionHandler);
		return executionHandler.execute(stateDetail,context);
	}
}
