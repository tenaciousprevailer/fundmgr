package com.wissen.fundprocessor.core;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.wissen.fundprocessor.ent.Context;
import com.wissen.fundprocessor.ent.StateDetail;
import com.wissen.fundprocessor.pres.IStateExecutionHandler;

public class FundStateController implements IStateExecutionHandler{

	private static final Logger logger = LogManager.getLogger(FundStateController.class.getName());
	
	private FundStateController() { }
	
	public static FundStateController getInstance() {
		return SingletonHelper.INSTANCE;
	}
	
	private static final class SingletonHelper{
		private static final FundStateController INSTANCE = new FundStateController();
	}
	
	@Override
	public boolean execute(StateDetail stateDetail, Context context) {

		logger.debug("Going to process for the fund approval");
		boolean isFundApproved = false;
		IStateExecutionHandler executor = new StateExecutor();
		StateDetail currentStateDetail = stateDetail;
		while (currentStateDetail != null) {
			logger.debug("Current State Detail:{}", currentStateDetail);
			isFundApproved = executor.execute(currentStateDetail, context);
			logger.debug("Current State:{}, approved:{}", currentStateDetail, isFundApproved);
			if (isFundApproved) {
				currentStateDetail = currentStateDetail.getNextState();
				logger.debug("Current State fund application approved, next Approver details: {}", currentStateDetail);
			} else {
				logger.info("Current State fund application rejected");
				break;
			}
		}
		logger.debug("Fund Process Status, isFundApproved:{}", isFundApproved);
		return isFundApproved;
	}

}
