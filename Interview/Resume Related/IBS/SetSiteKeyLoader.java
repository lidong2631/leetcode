package com.bocadc.pbank.security;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.boc.pbank.core.LoginedAction;
import com.bocadc.ibs.login.UserInfo;
import com.bocadc.ibs.security.Consts4Security;
import com.bocadc.ibs.security.SiteKey;
import com.bocadc.ibs.security.SiteKeyPool;
import com.bocusa.ibs.core.PbankUser;
import com.zix.jet.base.JetRequest;
import com.zix.jet.base.JetResponse;
import com.zix.jet.exception.JetException;

/**
 * SetSiteKeyLoader.java: 
 * @author Jingde Wang
 * created on 2011-3-31 in NY
 * version 0.9
 */
public class SetSiteKeyLoader extends LoginedAction
{

	/* (non-Javadoc)
	 * @see com.zix.jet.execution.JetExecution#doExecution(com.zix.jet.base.JetRequest, com.zix.jet.base.JetResponse)
	 */
	public void doExecution(JetRequest req, JetResponse resp) throws JetException
	{
		PbankUser user = (PbankUser)req.getUser();
		List<SiteKey> myPool = SiteKeyPool.getRandomPool();
		user.setPrivateData(Consts4Security.KEY_SITEKEYPOOL, myPool);
		user.setPrivateData(Consts4Security.KEY_SITEKEYINDEX, new Integer(1));
		user.setPrivateData(Consts4Security.KEY_PAGESITEKEYS, SiteKeyPool.getToPagePool(myPool, 1));
		Map<String, String> toPage = new HashMap<String, String>();
		UserInfo userInfo = (UserInfo)user.getPrivateData(Consts4Security.KEY_USER_INFO);
		toPage.put("welcomeInfo", userInfo.getWelcomeInfo());
		resp.setData(toPage);
	}

}
