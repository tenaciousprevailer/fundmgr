package com.wissen.fundprocessor.core.processor;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.wissen.fundprocessor.ent.Context;
import com.wissen.fundprocessor.pres.IFundStateProcessor;

public abstract class AbstractFundProcessor implements IFundStateProcessor {

	protected final Logger logger = LogManager.getLogger(this.getClass().getName());
	
	@Override
	public boolean process(Context context) {
		logger.info("approved");
		return true;
	}

}
