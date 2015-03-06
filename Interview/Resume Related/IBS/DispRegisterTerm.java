package com.boc.pbank.register;

import java.util.HashMap;
import java.util.Map;

import com.boc.pbank.core.NoLoginedAction;
import com.zix.jet.base.JetRequest;
import com.zix.jet.base.JetResponse;
import com.zix.jet.exception.JetException;

// step 1
public class DispRegisterTerm extends NoLoginedAction
{
	public void doExecution(JetRequest req, JetResponse resp) throws JetException
	{
		RegisterHelper.setFirstStepFlag(resp);
		Map<String, String> toPage = new HashMap<String, String>();
		toPage.put("iagree", req.getSessionId());
		resp.setData(toPage);
	}
}
