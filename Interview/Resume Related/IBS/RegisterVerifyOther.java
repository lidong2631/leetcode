package com.boc.pbank.register;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.boc.pbank.core.NoLoginedAction;
import com.zix.jet.base.JetRequest;
import com.zix.jet.base.JetResponse;
import com.zix.jet.exception.JetException;
import com.zix.jet.util.JetPropertiesReader;

/**
 * RegisterVerifyOther.java: 
 * 
 * @author Jingde Wang
 * rewrited on 2009-06-25 in New York
 * 
 */
// step 4
public class RegisterVerifyOther extends NoLoginedAction
{
	private static Logger logger = Logger.getLogger(RegisterVerifyOther.class);
	private static final String SSN = "SSN";

	@SuppressWarnings("unchecked")
	public void doExecution(JetRequest req, JetResponse resp) throws JetException
	{
		Map lastData = null;
		int stepMask = 0;
		try
		{
			lastData = (Map) req.getSavedData();
			stepMask = ((Integer) lastData.get("stepMask")).intValue();
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

		if ((stepMask & RegisterHelper.MASK_STEP_3) == 0)
			RegisterHelper.gotoFirstStep(req, resp, RegisterHelper.ILLEGAL_ACCESS);

		stepMask |= RegisterHelper.MASK_STEP_4;

		String ssn1 = req.getParameter("ssn1");
		String ssn2 = req.getParameter("ssn2");
		String ssn3 = req.getParameter("ssn3");
		String month = req.getParameter("month");
		String day = req.getParameter("day");
		String year = req.getParameter("year");

		String ssnInput = ssn1 + "-" + ssn2 + "-" + ssn3;
		String dobInput = year + month + day;

		JetPropertiesReader jpr = new JetPropertiesReader(lastData);
		String branch = jpr.getStringValue("branch");
		
		String idType = jpr.getStringValue("ID_TYPE").trim().toUpperCase();
		String ssnHost = jpr.getStringValue("ID_NUMBER").trim();
		String dobHost = jpr.getStringValue("CU_DOB").trim();

		String customerNo = jpr.getStringValue("CUSTOMER_NO").trim();

		if (idType.equals(SSN) && ssnHost.equals(ssnInput) && dobHost.equals(dobInput))
		{
			// nothing to do
			logger.debug("it's " + customerNo);
		}
		else
		{
			idType = jpr.getStringValue("JH_ID_TYPE").trim().toUpperCase();
			ssnHost = jpr.getStringValue("JH_ID_NUMBER").trim();
			dobHost = jpr.getStringValue("JH_DOB").trim();

			if (idType.equals(SSN) && ssnHost.equals(ssnInput) && dobHost.equals(dobInput))
			{
				customerNo = jpr.getStringValue("JOINT_HOLDER").trim();
				logger.debug("it's " + customerNo);
			}
			else
			{
				boolean hasMoreJointHolder = new Boolean(jpr.getStringValue("hasMoreJointHolder")).booleanValue();
				if (hasMoreJointHolder)
				{
					List holders = (List) jpr.get("JointHolderList");
					boolean isCorrect = false;
					for (int i = 0; holders != null && i < holders.size(); i++)
					{
						Map aHolder = (Map)holders.get(i);
						JetPropertiesReader aJpr = new JetPropertiesReader(aHolder);
						idType = jpr.getStringValue("JH_ID_TYPE").trim().toUpperCase();
						ssnHost = aJpr.getStringValue("JH_ID_NUMBER").trim();
						dobHost = aJpr.getStringValue("JH_DOB").trim();
						if (idType.equals(SSN) && ssnHost.equals(ssnInput) && dobHost.equals(dobInput))
						{
							customerNo = aJpr.getStringValue("JOINT_HOLDER").trim();
							isCorrect = true;
							logger.debug("it's " + customerNo);
							break;
						}
					}
					if(isCorrect == false)stepMask |= RegisterHelper.MASK_SSN_ERROR;
				}
				else
				{
					stepMask |= RegisterHelper.MASK_SSN_ERROR;
				}
			}

		}

		Map toNextStep = new HashMap();
		toNextStep.put("customer_no", customerNo);
		toNextStep.put("branch", branch);
		toNextStep.put("stepMask", new Integer(stepMask));

		resp.setSavedData(toNextStep);
	}

}
