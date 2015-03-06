package com.boc.pbank.register;

import org.apache.log4j.Logger;

import com.boc.pbank.core.NoLoginedAction;
import com.zix.jet.base.JetRequest;
import com.zix.jet.base.JetResponse;
import com.zix.jet.exception.JetException;

// step 2
public class RegisterLoader extends NoLoginedAction
{
	private static Logger logger = Logger.getLogger(RegisterLoader.class);
	
	public void doExecution(JetRequest req, JetResponse resp) throws JetException
	{
		int stepMask = 0;
		try
		{
			stepMask = ((Integer) req.getSavedData()).intValue();
			logger.debug("stepMask=" + stepMask);
		}
		catch (Exception e)
		{
			RegisterHelper.gotoFirstStep(req, resp, RegisterHelper.ILLEGAL_ACCESS);
		}
		if ((stepMask & RegisterHelper.MASK_STEP_1) == 0)
			RegisterHelper.gotoFirstStep(req, resp, RegisterHelper.ILLEGAL_ACCESS);
		
		String id = req.getParameter("iagree");
		if(id==null || id.equals(req.getSessionId()) == false)
			RegisterHelper.gotoFirstStep(req, resp, RegisterHelper.ILLEGAL_ACCESS);
		
		stepMask |= RegisterHelper.MASK_STEP_2;
		resp.setSavedData(new Integer(stepMask));
	}
}
