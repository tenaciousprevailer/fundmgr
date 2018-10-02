package com.wissen.fundprocessor.core;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.wissen.fundprocessor.ent.Context;
import com.wissen.fundprocessor.ent.StateDetail;
import com.wissen.fundprocessor.pres.IStateExecutionHandler;


public class ParallelExecutionHandler implements IStateExecutionHandler{
	private static final Logger logger = LogManager.getLogger(ParallelExecutionHandler.class.getName());

	public boolean execute(final StateDetail stateDetail, final Context context) {
		logger.debug("Going to execute task in parallel mode:{}", stateDetail);
		boolean isFundApproved = false;
		ExecutorService executorService = Executors.newCachedThreadPool();
		List<StateDetail> statesForParallelExecution = stateDetail.getStatesToExecute();
		FundProcessor task = null;
		Collection<Future<Boolean>> futureApprovalStatusList = new ArrayList<>();
		for(StateDetail currentState : statesForParallelExecution) {
			task = new FundProcessor(currentState, context);
			logger.debug("Going to submit task in parallel mode:{}", currentState);
			Future<Boolean> isFundApprovedFuture = executorService.submit(task);
			futureApprovalStatusList.add(isFundApprovedFuture);
		}
		
		for(Future<Boolean> futureApprovalStatus : futureApprovalStatusList) {
			try {
				isFundApproved = futureApprovalStatus.get();
			} catch (InterruptedException | ExecutionException e) {
				logger.error("Exception", e);
				isFundApproved = false;
			}
			if(!isFundApproved) {
				logger.info("Fund approval Rejected");
				break;
			}
		}
		executorService.shutdown();
		return isFundApproved;
	}
	

	private class FundProcessor implements Callable<Boolean>{

		private StateDetail stateDetail;
		private Context context;

		public FundProcessor(StateDetail stateDetail, Context context) {
			this.stateDetail = stateDetail;
			this.context = context;
		}

		@Override
		public Boolean call() throws Exception {
			FundStateController controller = FundStateController.getInstance();
			return controller.execute(stateDetail, context);
		}

	}

}

