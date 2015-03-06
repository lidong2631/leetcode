/**
 * 
 */
package com.boc.pbank.register;

import java.util.HashMap;
import java.util.Map;

import com.zix.jet.base.JetRequest;
import com.zix.jet.base.JetResponse;
import com.zix.jet.exception.JetException;

/**
 * @author jingde
 *
 */
public class RegisterHelper
{
	public static final int MASK_STEP_1 = 1;
	public static final int MASK_STEP_2 = 2;
	public static final int MASK_STEP_3 = 4;
	public static final int MASK_STEP_4 = 8;
	public static final int MASK_STEP_5 = 16;
	public static final int MASK_STEP_6 = 32;

	public static final int MASK_ACCOUNT_ERROR = 128;
	public static final int MASK_SSN_ERROR = 256;
	public static final int MASK_OTHER_ERROR = 512;
	
	public static final String ILLEGAL_ACCESS = "";
	public static final String INVALID_INFO = "Invalid information submitted, please try again.";
	public static final String USER_EXISTS = "You had registered the IBS.";
	public static final String SYSTEM_ERROR = "System busy, please try later.";
	
	public static void setFirstStepFlag(JetResponse resp)
	{
		resp.setSavedData(new Integer(RegisterHelper.MASK_STEP_1));
	}
	
	public static void gotoFirstStep(JetRequest req, JetResponse resp, String errorMessage) throws JetException
	{
		setFirstStepFlag(resp);
		Map<String, String> toPage = new HashMap<String, String>();
		toPage.put("iagree", req.getSessionId());
		resp.setData(toPage);
		throw new JetException(errorMessage);
	}
	
}
