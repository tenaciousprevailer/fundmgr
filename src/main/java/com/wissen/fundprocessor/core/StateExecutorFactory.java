package com.wissen.fundprocessor.core;

import java.util.HashMap;

import com.wissen.fundprocessor.ent.StateExecutionType;
import com.wissen.fundprocessor.pres.IStateExecutionHandler;

public class StateExecutorFactory {

	private StateExecutorFactory() {}
	
	private static HashMap<StateExecutionType, IStateExecutionHandler> stateHandlerMap;
	
	static {
		stateHandlerMap = new HashMap<>();
		stateHandlerMap.put(StateExecutionType.EXECUTE_STATE, new StateExecutionHandler());
		stateHandlerMap.put(StateExecutionType.EXECUTE_STATES_IN_PARALLEL, new ParallelExecutionHandler());
	}
	
	public static IStateExecutionHandler getExecutionHandler(StateExecutionType executionType) {
		return stateHandlerMap.get(executionType);
	}
}
