package com.bocadc.pbank.security;

import java.util.List;

import org.apache.log4j.Logger;

import com.bocadc.ibs.security.Consts4Security;
import com.bocadc.ibs.security.SiteKey;
import com.bocadc.ibs.security.SiteKeyPool;
import com.bocusa.ibs.core.PbankUser;
import com.zix.jet.base.JetRequest;
import com.zix.jet.base.JetResponse;
import com.zix.jet.exception.JetException;
import com.zix.jet.execution.NonLoginedExecution;

/**
 * ChangePageSiteKeys.java: 
 * @author Jingde Wang
 * created on 2011-3-31 in NY
 * version 0.9
 */
public class ChangePageSiteKeys extends NonLoginedExecution
{
	private static Logger logger = Logger.getLogger(ChangePageSiteKeys.class.getName());
	
	/* (non-Javadoc)
	 * @see com.zix.jet.execution.JetExecution#doExecution(com.zix.jet.base.JetRequest, com.zix.jet.base.JetResponse)
	 */
	@SuppressWarnings("unchecked")
	public void doExecution(JetRequest req, JetResponse resp) throws JetException
	{
		try
		{
			PbankUser user = (PbankUser) req.getUser();
			List<SiteKey> myPool = (List<SiteKey>) user.getPrivateData(Consts4Security.KEY_SITEKEYPOOL);
			int index = ((Integer) user.getPrivateData(Consts4Security.KEY_SITEKEYINDEX)).intValue();
			String operation = req.getParameter("operation");
			if ("previous".equals(operation))
				index--;
			else
				index++;

			if (index < 1)
				index = SiteKeyPool.getMaxPages();
			else if (index > SiteKeyPool.getMaxPages())
				index = 1;
			user.setPrivateData(Consts4Security.KEY_SITEKEYINDEX, new Integer(index));
			user.setPrivateData(Consts4Security.KEY_PAGESITEKEYS, SiteKeyPool.getToPagePool(myPool, index));
		}
		catch (Exception e)
		{
			logger.error(e, e);
		}
	}

	public void doCheck(JetRequest req, JetResponse resp) throws JetException
	{
	}

	public void doBeforeExecution(JetRequest req, JetResponse resp) throws JetException
	{
	}

	public void doAfterExecution(JetRequest req, JetResponse resp) throws JetException
	{
	}

	public void doException(JetRequest req, JetResponse resp) throws JetException
	{
	}

	public void doFinal(JetRequest req, JetResponse resp) throws JetException
	{
	}

}
