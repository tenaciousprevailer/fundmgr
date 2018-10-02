package com.wissen.fundprocessor.pres;

import com.wissen.fundprocessor.ent.Context;
import com.wissen.fundprocessor.ent.StateDetail;

public interface IStateExecutionHandler {

	public boolean execute(final StateDetail stateDetail, final Context context);
	
}
