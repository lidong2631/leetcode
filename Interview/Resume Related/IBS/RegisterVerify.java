package com.boc.pbank.register;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.log4j.Logger;

import com.boc.pbank.core.NoLoginedAction;
import com.zix.jet.base.JetRequest;
import com.zix.jet.base.JetResponse;
import com.zix.jet.exception.JetException;
import com.zix.jet.exception.JetInputException;
import com.zix.jet.util.Tidy;
import com.zix.jet.xutils.channel_http_ext.JetRequest4Servlet;
import com.bocadc.ibs.security.Consts4Security;
import com.bocusa.t24.PacketHelper;

/**
 * RegisterVerify.java: 
 * 
 * @author Jingde Wang
 * rewrited on 2009-06-25 in New York
 * 
 */
// step 3
public class RegisterVerify extends NoLoginedAction
{
	private static Logger logger = Logger.getLogger(RegisterVerify.class);

	@SuppressWarnings("unchecked")
	public void doExecution(JetRequest req, JetResponse resp) throws JetException
	{
		int stepMask = 0;
		try
		{
			stepMask = ((Integer) req.getSavedData()).intValue();
		}
		catch (Exception e)
		{
			RegisterHelper.gotoFirstStep(req, resp, RegisterHelper.ILLEGAL_ACCESS);
		}

		logger.debug("stepMask=" + stepMask);

		if ((stepMask & RegisterHelper.MASK_STEP_1) == 0)
			RegisterHelper.gotoFirstStep(req, resp, RegisterHelper.ILLEGAL_ACCESS);

		if ((stepMask & RegisterHelper.MASK_STEP_2) == 0)
			RegisterHelper.gotoFirstStep(req, resp, RegisterHelper.ILLEGAL_ACCESS);

		stepMask |= RegisterHelper.MASK_STEP_3;
		
		Map sessionMap = ((JetRequest4Servlet) req).getSessionHash();
		String vcSession = (String) sessionMap.get(Consts4Security.SESSION_VERIFICATION_CODE);
		if (vcSession == null)
			throw new JetInputException(Consts4Security.INVALID_VERIFICATION_CODE);
		String vcPage = req.getParameter(Consts4Security.PAGE_VERIFICATION_CODE);
		if (vcPage == null || vcSession.equals(vcPage.toUpperCase()) == false)
			throw new JetInputException(Consts4Security.INVALID_VERIFICATION_CODE);
		((JetRequest4Servlet) req).removeSessionValue(Consts4Security.SESSION_VERIFICATION_CODE);

		String accountNo = Tidy.filter(req.getParameter("accountNo"));
		String orgCode = req.getParameter("orgCode");
		String cdNo = Tidy.filter(req.getParameter("cdNo"));

		Properties params = new Properties();
		
		if (accountNo.length() > 0 && cdNo.length() > 0)
			throw new JetInputException("Only one account number can be accepted, please try again.");

		if (accountNo.length() == 0 && cdNo.length() == 0)
			throw new JetInputException("You must input an account number, please try again.");

		if (accountNo.length() > 0)
		{
			params.put(PacketHelper.FLD_ACCT_NO, accountNo);
		}
		else
		{
			params.put(PacketHelper.FLD_ACCT_NO, cdNo);
		}

		params.put(PacketHelper.KEY_TXN_NAME, PacketHelper.TXN_REGISTER_USER_ONLINE);
		params.put(PacketHelper.KEY_BRANCH_NO, orgCode);

		Map toNextStep = new HashMap();
		try
		{
			Map map = com.bocusa.t24.Transport.sendAndReceive(params);
			HashMap hm = (HashMap) map.get("ResponseValues");
			//			String right_ssn = (String) hm.get("ID_NUMBER");
			//			String right_birthday = hm.get("CU_DOB").toString().trim();
			//			String customer_no = hm.get("CUSTOMER_NO").toString().trim();
			//			toNextStep.put("right_ssn", right_ssn);
			//			toNextStep.put("right_birthday", right_birthday);
			//			toNextStep.put("customer_no", customer_no);
			toNextStep.putAll(hm);
			toNextStep.put("branch", orgCode);
		}
		catch (Exception e)
		{
			logger.warn(e, e);
			stepMask |= RegisterHelper.MASK_ACCOUNT_ERROR;
		}
		toNextStep.put("stepMask", new Integer(stepMask));
		resp.setSavedData(toNextStep);
	}

}
