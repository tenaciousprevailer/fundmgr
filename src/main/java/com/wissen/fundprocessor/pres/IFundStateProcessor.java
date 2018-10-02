package com.wissen.fundprocessor.pres;

import com.wissen.fundprocessor.ent.Context;

/**
 * interface to process the fund.
 * It has to be implemented by all the approvers (processors).
 * @author tenacious
 *
 */
public interface IFundStateProcessor {

	/**
	 * api which each approver has to implement and 
	 * approve/reject the fund as per their evaluation
	 * 
	 * @param context
	 * @return true if fund is approved
	 */
	public boolean process(final Context context);
	
}
