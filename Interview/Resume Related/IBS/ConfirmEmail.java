package com.bocadc.pbank.security;

import com.boc.pbank.core.LoginedAction;
import com.bocadc.ibs.login.UserInfo;
import com.bocadc.ibs.security.Consts4Security;
import com.bocusa.ibs.core.PbankUser;
import com.zix.jet.base.JetRequest;
import com.zix.jet.base.JetResponse;
import com.zix.jet.db.data.SqlUpdate;
import com.zix.jet.db.ext.SqlGearExt;
import com.zix.jet.exception.JetException;
import com.zix.jet.exception.JetInputException;
import com.zix.jet.exception.JetLoginException;

/**
 * @author Jingde
 *
 */
public class ConfirmEmail extends LoginedAction
{
	private static final String UPDATE_EMAIL = "update ibs_user set email=?, email_flag='1' where user_id=?";

	/* (non-Javadoc)
	 * @see com.zix.jet.execution.JetExecution#doExecution(com.zix.jet.base.JetRequest, com.zix.jet.base.JetResponse)
	 */
	public void doExecution(JetRequest req, JetResponse resp) throws JetException
	{
		PbankUser user = (PbankUser) req.getUser();
		UserInfo userInfo = (UserInfo)user.getPrivateData(Consts4Security.KEY_USER_INFO);
		if(userInfo == null)
			throw new JetLoginException("Invalid.Access.Logon.Again");

		String email = req.getParameter("email");
		String email2 = req.getParameter("email2");
		if (email.equalsIgnoreCase(email2) == false)
			throw new JetInputException("Different.Emails.Please.Input.Again");

		SqlUpdate su = new SqlUpdate(UPDATE_EMAIL, new String[] { email, user.getUserId() });
		SqlGearExt.getSqlGearExt().doUpdate(su);
		user.setEmail(email);
		userInfo.setEmailConfirmed(true);
	}

}
