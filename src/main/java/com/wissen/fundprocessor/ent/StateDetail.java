package com.wissen.fundprocessor.ent;

import java.util.List;

public class StateDetail {

	private StateExecutionType executionType;
	private FundProcessingState fundProcessingState;
	private List<StateDetail> statesToExecute;
	private StateDetail nextState;

	public FundProcessingState getFundProcessingState() {
		return fundProcessingState;
	}

	public void setExecutionType(StateExecutionType executionType) {
		this.executionType = executionType;
	}

	public void setFundProcessingState(FundProcessingState fundProcessingState) {
		this.fundProcessingState = fundProcessingState;
	}

	public List<StateDetail> getStatesToExecute() {
		return statesToExecute;
	}

	public void setStatesToExecute(List<StateDetail> statesToExecute) {
		this.statesToExecute = statesToExecute;
	}

	public StateExecutionType getExecutionType() {
		return executionType;
	}

	public StateDetail getNextState() {
		return nextState;
	}

	public void setNextState(StateDetail nextState) {
		this.nextState = nextState;
	}

	@Override
	public String toString() {
		return String.format("StateDetail [executionType=%s, fundProcessingState=%s, statesToExecute=%s]",
				executionType, fundProcessingState, statesToExecute);
	}


}
