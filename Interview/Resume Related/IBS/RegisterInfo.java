package com.boc.pbank.register;

import java.sql.Timestamp;
import java.util.Map;

import org.apache.log4j.Logger;

import com.boc.pbank.core.NoLoginedAction;
import com.boc.pbank.utils.PasswordHelper;
import com.boc.pbank.utils.Utils;
import com.bocusa.ibs.core.AccessOptionsHelper;
import com.bocusa.ibs.core.PasswordHistory;
import com.bocusa.ibs.utils.AppHelper;
import com.bocusa.ibs.utils.UserHelper;
import com.zix.jet.base.JetRequest;
import com.zix.jet.base.JetResponse;
import com.zix.jet.db.data.SqlQuery;
import com.zix.jet.db.data.SqlUpdate;
import com.zix.jet.db.ext.SqlGearExt;
import com.zix.jet.db.ext.SqlGearExtA;
import com.zix.jet.exception.JetException;
import com.zix.jet.exception.JetInputException;
import com.zix.jet.util.JetPropertiesReader;
import com.zix.jet.util.JetTimestamp;
import com.zix.jet.util.Tidy;

/*
 * RegisterInfo.java: step 5 for user register
 * 
 * modified by Jingde Wang on 2011-04-09 in Beijing
 * 		save the open_date for both IBS_USER and CIF
 */
public class RegisterInfo extends NoLoginedAction
{
	private static Logger logger = Logger.getLogger(RegisterInfo.class.getName());

	private static final String INS_USER = "insert into ibs_user (branch_no,user_id,customer_no,password,firstname,email,status,last_pwd_date,open_date) values(?,?,?,?,?,?,0,?,?)";
	private static final String INS_CIF = "insert into cif (customer_no,branch_no,mobile,tel_no,email,cif_type,cif_status,open_date ) values(?,?,?,?,?,?,'0',?)";
	private static final String SEL_CIF = "select * from cif where branch_no=? and customer_no=?";

	@SuppressWarnings("rawtypes")
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

		if ((stepMask & RegisterHelper.MASK_STEP_4) == 0)
			RegisterHelper.gotoFirstStep(req, resp, RegisterHelper.ILLEGAL_ACCESS);

		stepMask |= RegisterHelper.MASK_STEP_5;

		if ((stepMask & RegisterHelper.MASK_ACCOUNT_ERROR) > 0)
			RegisterHelper.gotoFirstStep(req, resp, RegisterHelper.INVALID_INFO);

		if ((stepMask & RegisterHelper.MASK_SSN_ERROR) > 0)
			RegisterHelper.gotoFirstStep(req, resp, RegisterHelper.INVALID_INFO);

		String userId = req.getParameter("userid");
		String userName = req.getParameter("username");
		String password = req.getParameter("password");
		String password1 = req.getParameter("password1");
		String telephone = req.getParameter("telephone");
		String mobilenum = req.getParameter("mobilenum");
		String email = Tidy.filter(req.getParameter("email"));
		String email1 = Tidy.filter(req.getParameter("email1"));

		if (UserHelper.isUniqueUserId(userId, AppHelper.PBANK) == false)
			throw new JetInputException("The User ID has existed, please change to another one!");
		
		UserHelper.validateUserName(userName);

		if (password.equals(password1) == false) throw new JetInputException("ModifyPassword.DIFFERENT_PASSWORD");

		if (PasswordHelper.getPasswordStrength(password) < PasswordHelper.PWD_GOOD)
			throw new JetInputException("Password.Strength.Too.Low");


		if (email.length() < 3 || !email.equals(email1)) throw new JetInputException("Invalid email address.");

		JetPropertiesReader jpr = new JetPropertiesReader(lastData);
		String customerNo = jpr.getStringValue("customer_no").trim();
		String branchNo = jpr.getStringValue("branch").trim();

		SqlQuery sq = new SqlQuery(SEL_CIF, new Object[] { branchNo, customerNo });
		Map hm = SqlGearExt.getSqlGearExt().queryHashMap(sq);
		if (hm != null && !hm.isEmpty()) RegisterHelper.gotoFirstStep(req, resp, RegisterHelper.USER_EXISTS);

		try
		{
			Timestamp tsNow = new JetTimestamp().toTimestamp();
			
			SqlUpdate[] sqls = new SqlUpdate[2];

			Object[] args = new Object[] { customerNo, branchNo, mobilenum, telephone, email, "P", tsNow };
			sqls[0] = new SqlUpdate(INS_CIF, args);
			password = Utils.oneWayHash(userId + Utils.oneWayHash(password));
			args = new Object[] { branchNo, userId, customerNo, password, userName, email,
					new JetTimestamp().toString("yyyyMMdd"), tsNow };
			sqls[1] = new SqlUpdate(INS_USER, args);

			SqlGearExtA.getSqlGearExtA().doUpdates(sqls);

			// default show all the accounts and CD / loans
			AccessOptionsHelper.initAccessOptions(userId, branchNo, customerNo);

			PasswordHistory.getHistory(userId).setHistory(password);

		}
		catch (Exception e)
		{
			logger.debug(e, e);
			RegisterHelper.gotoFirstStep(req, resp, RegisterHelper.SYSTEM_ERROR);
		}

	}

}
