package com.wissen.fundprocessor;

import static com.wissen.fundprocessor.util.Constant.CONFG_FILE_NAME;
import static com.wissen.fundprocessor.util.Constant.FIRST_PROCESSING_STATE;
import static com.wissen.fundprocessor.util.Util.closeResource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.wissen.fundprocessor.core.FundStateController;
import com.wissen.fundprocessor.ent.Context;
import com.wissen.fundprocessor.ent.StateDetail;
import com.wissen.fundprocessor.pres.IStateExecutionHandler;

public class App {

	private static final Logger logger = LogManager.getLogger(App.class.getName());

	public static void main(String[] args) {

		AbstractApplicationContext applicationContext = null;
		try {
			// setting up spring
			applicationContext = new ClassPathXmlApplicationContext(CONFG_FILE_NAME);
			applicationContext.registerShutdownHook();

			StateDetail firstStateDetail = applicationContext.getBean(FIRST_PROCESSING_STATE, StateDetail.class);
			logger.info("State Detail:{}", firstStateDetail);
			
			Context context = new Context();
			
			IStateExecutionHandler controller = FundStateController.getInstance();
			boolean isFundApproved = controller.execute(firstStateDetail, context);
			logger.info("Final Fund Approval Status, isFundApproved:{}",isFundApproved);
		} catch (Exception e) {
			logger.error("Exception:", e);
		} finally {
			closeResource(applicationContext);
		}
	}

}
