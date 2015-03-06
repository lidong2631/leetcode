package com.bocadc.pbank.security;

import java.util.List;

import org.apache.log4j.Logger;

import com.boc.pbank.core.LoginedAction;
import com.bocadc.ibs.inform.ConfTxns;
import com.bocadc.ibs.inform.InformHelper;
import com.bocadc.ibs.inform.hbm.InformDefId;
import com.bocadc.ibs.login.UserInfo;
import com.bocadc.ibs.login.UserSignIn;
import com.bocadc.ibs.security.Consts4Security;
import com.bocadc.ibs.security.SiteKey;
import com.bocadc.ibs.security.SiteKeyPool;
import com.bocusa.ibs.core.PbankUser;
import com.bocusa.ibs.utils.EmailSender;
import com.bocusa.ibs.utils.EmailTemplate;
import com.bocusa.ibs.utils.UserHelper;
import com.zix.jet.base.JetRequest;
import com.zix.jet.base.JetResponse;
import com.zix.jet.db.data.SqlQuery;
import com.zix.jet.db.data.SqlUpdate;
import com.zix.jet.db.ext.SqlGearExt;
import com.zix.jet.db.ext.SqlGearExtA;
import com.zix.jet.email.SimpleHtmlEmail;
import com.zix.jet.email.SimpleMail2UsingHTML;
import com.zix.jet.email.SimpleMailUsingHTML;
import com.zix.jet.exception.JetException;
import com.zix.jet.exception.JetInputException;
import com.zix.jet.exception.JetLoginException;
import com.zix.jet.util.JetTimestamp;
import com.zix.jet.util.StringUtils;

/**
 * @author Jingde Wang
 *
 */
public class SetSiteKey extends LoginedAction
{
	private static Logger logger = Logger.getLogger(SetSiteKey.class.getName());
	
	private static final String CHECK_USER_SITE_KEY = "select user_id from user_site_key where user_id=?";
	private static final String UPDATE_USER_SITE_KEY = "update user_site_key set welcome_info=?,key_id=?,update_time=? where user_id=?";
	private static final String INSERT_USER_SITE_KEY = "insert into user_site_key(welcome_info,key_id,update_time,user_id)values(?,?,?,?)";
	private static final String UPDATE_SITE_KEY_FLAG = "update ibs_user set sitekey_flag='1' where user_id=?";
	/* (non-Javadoc)
	 * @see com.zix.jet.execution.JetExecution#doExecution(com.zix.jet.base.JetRequest, com.zix.jet.base.JetResponse)
	 */
	@SuppressWarnings("unchecked")
	public void doExecution(JetRequest req, JetResponse resp) throws JetException
	{
		PbankUser user = (PbankUser) req.getUser();
		UserInfo userInfo = (UserInfo) user.getPrivateData(Consts4Security.KEY_USER_INFO);
		List<SiteKey> pagePool = (List<SiteKey>) user.getPrivateData(Consts4Security.KEY_PAGESITEKEYS);
		if (userInfo == null || pagePool == null)
			throw new JetLoginException("ABNORMAL.ACCESS");
		String sitekey = req.getParameter("sitekey");
		if(StringUtils.isInteger(sitekey))
		{
			int iSitekey = new Integer(sitekey).intValue();
			if (iSitekey >= pagePool.size())
				throw new JetLoginException("ABNORMAL.ACCESS");
			userInfo.setSiteKey(pagePool.get(iSitekey));
		}
		else
		{
			if(userInfo.isSiteKeySetted() == false)
				throw new JetInputException("A site key must be selected.");
		}
		
		String welcomeInfo = req.getParameter("welcomeInfo");
		userInfo.setWelcomeInfo(welcomeInfo);
		
		SqlQuery sq = new SqlQuery(CHECK_USER_SITE_KEY, new String[]{user.getUserId()});
		SqlUpdate sus[] = new SqlUpdate[2];
		String now = new JetTimestamp().toString("yyyy-MM-dd HH:mm:ss");
		String[] args = new String[]{welcomeInfo, userInfo.getSiteKey().getId(), now, user.getUserId()};
		if(SqlGearExt.getSqlGearExt().doSingleValueQuery(sq) == null)
		{
			sus[0] = new SqlUpdate(INSERT_USER_SITE_KEY, args);
		}
		else
		{
			sus[0] = new SqlUpdate(UPDATE_USER_SITE_KEY, args);
		}
		sus[1] = new SqlUpdate(UPDATE_SITE_KEY_FLAG, new String[]{user.getUserId()});
		
		SqlGearExtA.getSqlGearExtA().doUpdates(sus);
		
		UserSignIn.prepareSiteKey(userInfo);
		userInfo.setSiteKeySetted(true);
		
		// added by Jingde Wang on 2011-04-03 in NY for all countries.
		try
		{
			// user.getCountryCode() is added in the following function call. 2011-04-03
			//String useTemplate = user.isBrazilUser() ? EmailTemplate.BR_SITE_KEY_CHANGED : EmailTemplate.SITE_KEY_CHANGED;
			EmailTemplate et = EmailTemplate.get(user.getCountryCode(), EmailTemplate.SITE_KEY_CHANGED);
			String userName = UserHelper.getUserName(user);
			JetTimestamp jtsNow = new JetTimestamp();
			
			String subject = et.getSubject();
			String body = et.getBody();
			body = body.replace("USERNAME", userName);
			
			SimpleHtmlEmail mail; 
			if(user.isUsaUser())
			{
				SimpleMail2UsingHTML mail2 = new SimpleMail2UsingHTML();
				mail2.setTo(user.getEmail());
				mail2.setSubject(subject);
				body = body.replace("TIMEDATE", jtsNow.toString("HH:mm, MM/dd/yyyy"));
				body = body.replace("PHRASE", userInfo.getWelcomeInfo());
				mail2.setCid("image");
				mail2.setImagePath(SiteKeyPool.SITEKEY_DIR + userInfo.getSiteKey().getImageName());
				mail = mail2;
			}
			else
			{
				mail = new SimpleMailUsingHTML();
				mail.setTo(user.getEmail());
				mail.setSubject(subject);
				body = body.replace("DATE", jtsNow.toString("MM/dd/yyyy"));
				body = body.replace("TIME", jtsNow.toString("HH:mm"));
			}
			mail.setContent(body);
			new Thread(new EmailSender(mail)).start();
		}
		catch (Exception e)
		{
			logger.error(e, e);
		}
		
		if(user.isUsaUser())
		{
			InformDefId idid = new InformDefId();
			idid.setCustomerNo(user.getCustomerNo());
			idid.setUserId(user.getUserId());
			idid.setTxName(ConfTxns.SET_WELCOME_IMAGE);
			InformHelper.sendConfirmationText(idid, null, hbSession);
		}

	}
}
