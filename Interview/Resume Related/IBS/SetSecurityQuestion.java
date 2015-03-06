package com.bocadc.pbank.security;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.boc.pbank.core.LoginedAction;
import com.boc.pbank.utils.Utils;
import com.boc.usa.ca.topic.TopicConf;
import com.bocadc.ibs.inform.ConfTxns;
import com.bocadc.ibs.inform.InformHelper;
import com.bocadc.ibs.inform.hbm.InformDefId;
import com.bocadc.ibs.login.CookieUtils;
import com.bocadc.ibs.login.UserInfo;
import com.bocadc.ibs.security.Consts4Security;
import com.bocusa.ibs.core.PbankUser;
import com.bocusa.ibs.utils.EmailSender;
import com.bocusa.ibs.utils.EmailTemplate;
import com.bocusa.ibs.utils.UserHelper;
import com.zix.jet.base.JetRequest;
import com.zix.jet.base.JetResponse;
import com.zix.jet.db.data.SqlUpdate;
import com.zix.jet.db.ext.SqlGearExtA;
import com.zix.jet.email.SimpleHtmlEmail;
import com.zix.jet.email.SimpleMailUsingHTML;
import com.zix.jet.exception.JetException;
import com.zix.jet.exception.JetInputException;
import com.zix.jet.exception.JetLoginException;
import com.zix.jet.util.JetTimestamp;
import com.zix.jet.util.StringUtils;

/**
 * SetSecurityQuestion.java:
 * 
 * @author Jingde Wang
 * created on 2011-03-30 in New York
 *
 */

public class SetSecurityQuestion extends LoginedAction
{
	private static Logger logger = Logger.getLogger(SetSecurityQuestion.class.getName());

	private static final String UPDATE_SECURITY_OPTION = "update ibs_user set security_option='1' where user_id=? ";
	private static final String SAVE_ANSWERS = "insert into user_topic_answer(user_id,topic_id,answer,topic_content,country_code) values (?,?,?,?,?)";

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void doExecution(JetRequest req, JetResponse resp) throws JetException
	{
		PbankUser user = (PbankUser) req.getUser();
		UserInfo userInfo = (UserInfo) user.getPrivateData(Consts4Security.KEY_USER_INFO);
		if (userInfo == null)
			throw new JetLoginException("Invalid.Access.Logon.Again");
		/*
		String[] unitStr = req.getParameterValues("topicId");
		String[] topicIds = new String[unitStr.length];
		String[] topicContents = new String[unitStr.length];

		Map<String, String> topicIdMap = new HashMap<String, String>();
		for (int i = 0; i < unitStr.length; i++)
		{
			String unit = unitStr[i];
			String[] tmp = StringUtils.split(unit, '|');
			topicIds[i] = tmp[0];
			topicContents[i] = tmp[1];

			topicIdMap.put(String.valueOf(tmp[0]), "");
		}

		if (topicIdMap.keySet().size() < TopicConf.userTopicNum)
			throw new JetInputException("Answer.different.questions.please");

		String[] answers = req.getParameterValues("answer");

		List updateContainer = new ArrayList();
		SqlUpdate su0 = new SqlUpdate(UPDATE_SECURITY_OPTION, new String[] { user.getUserId() });
		updateContainer.add(su0);
		for (int i = 0; i < answers.length; i++)
		{
			if (answers[i].trim().length() == 0)
				continue;
			SqlUpdate su = new SqlUpdate(SAVE_ANSWERS, new Object[] { user.getUserId(), topicIds[i], answers[i], topicContents[i],
					user.getCountryCode() });
			updateContainer.add(su);
		}
		if (updateContainer.size() - 1 < TopicConf.userTopicNum)
			throw new JetInputException("NEED.MORE.ANSWER");

		SqlUpdate suDelete = new SqlUpdate("delete from user_topic_answer where user_id=?", new String[] { user.getUserId() });
		SqlGearExt.getSqlGearExt().doUpdate(suDelete);

		SqlUpdate[] sus = new SqlUpdate[updateContainer.size()];
		for (int i = 0; i < updateContainer.size(); i++)
		{
			sus[i] = (SqlUpdate) updateContainer.get(i);
		}
		SqlGearExtA.getSqlGearExtA().doUpdates(sus);
		*/

		int questionsNum = TopicConf.userTopicNum;
		String[] unitStr = new String[questionsNum];
		String[] topicIds = new String[questionsNum];
		String[] topicContents = new String[questionsNum];
		String[] answers = new String[questionsNum];
		boolean[] needUpdate = new boolean[questionsNum];

		boolean isNCBUser = "NC".equals(user.getCountryCode());
		String surName = user.getSurname();
		String firstName = user.getFirstname();
		if(surName == null || surName.trim().length() == 0)
			surName = null;
		if(firstName == null || firstName.trim().length() == 0)
			firstName = null;
		
		//LIDONGFU ADD :巴西分行提出任何2个答案不能一样；加入这个检查；
		Map<String, String> answerMap = new HashMap<String, String>();
		for (int i = 0; i < questionsNum; i++)
		{
			int itemId = i + 1;
			unitStr[i] = req.getParameter("topicId" + itemId);
			needUpdate[i] = req.getParameter("checkbox" + itemId) == null;
			answers[i] = req.getParameter("answer" + itemId);
			if (needUpdate[i] && (answers[i] == null || answers[i].length() < 1))
				throw new JetInputException("An empty answer cannot be accepted.");

			if(isNCBUser)
			{
				if(needUpdate[i] && (answers[i].trim().length() < 5))
					throw new JetInputException("The minimum length of an answer is 5.");
				
				if(surName != null)
				{
					if(answers[i].toUpperCase().indexOf(surName.toUpperCase()) >= 0)
						throw new JetInputException("Your surname can't be involved in the answers.");
				}
				if(firstName != null)
				{
					if(answers[i].toUpperCase().indexOf(firstName.toUpperCase()) >= 0)
						throw new JetInputException("Your firstname can't be involved in the answers.");
				}
				if(answers[i].toUpperCase().indexOf(user.getPassword().toUpperCase()) >= 0)
					throw new JetInputException("Your password can't be involved in the answers.");
				
				if(answers[i].toUpperCase().indexOf(user.getUserId().toUpperCase()) >= 0)
					throw new JetInputException("Your login ID can't be involved in the answers.");
					
			}
			
			//add for answerMap
			String key = answers[i].replaceAll("\\s*", "").toLowerCase();//去掉空格，转换为小写；
			answerMap.put(key, "");
			///
		}

		if (user.isBrazilUser() || isNCBUser)
		{
			if (answerMap.keySet().size() < TopicConf.userTopicNum)//do check.
				throw new JetInputException("Answer.different.content.please");
		}

		Map<String, String> topicIdMap = new HashMap<String, String>();
		for (int i = 0; i < questionsNum; i++)
		{
			String unit = unitStr[i];
			String[] tmp = StringUtils.split(unit, '|');
			topicIds[i] = tmp[0];
			//topicContents[i] = tmp[1];
			topicContents[i] = TopicConf.getQuestionContentById(tmp[0], user.getCountryCode());
			topicIdMap.put(String.valueOf(tmp[0]), "");
		}

		if (topicIdMap.keySet().size() < TopicConf.userTopicNum)
			throw new JetInputException("Answer.different.questions.please");

		List updateContainer = new ArrayList();
		SqlUpdate su0 = new SqlUpdate(UPDATE_SECURITY_OPTION, new String[] { user.getUserId() });
		updateContainer.add(su0);

		List<Integer> choiceList = TopicConf.getUserAllQuestionsId(user.getUserId());

		SqlUpdate suDelete;
		if (userInfo.isSecurityQuestionsSetted() == false)
		{
			suDelete = new SqlUpdate("delete from user_topic_answer where user_id=?", new String[] { user.getUserId() });
			SqlGearExtA.getSqlGearExtA().doUpdate(suDelete);
		}

		for (int i = 0; i < questionsNum; i++)
		{
			if (answers[i].trim().length() == 0)
				continue;
			if (userInfo.isSecurityQuestionsSetted() && needUpdate[i] == false)
				continue;

			if (userInfo.isSecurityQuestionsSetted())
			{
				suDelete = new SqlUpdate("delete from user_topic_answer where user_id=? and topic_id in(?, ?)", new Object[] { user.getUserId(), topicIds[i], choiceList.get(i) });
				SqlGearExtA.getSqlGearExtA().doUpdate(suDelete);
			}

			SqlUpdate suInsert = new SqlUpdate(SAVE_ANSWERS, new Object[] { user.getUserId(), topicIds[i], answers[i], topicContents[i], user.getCountryCode() });
			updateContainer.add(suInsert);
		}

		SqlUpdate[] sus = new SqlUpdate[updateContainer.size()];
		for (int i = 0; i < updateContainer.size(); i++)
		{
			sus[i] = (SqlUpdate) updateContainer.get(i);
		}
		SqlGearExtA.getSqlGearExtA().doUpdates(sus);

		user.setSecurityOption("1");
		userInfo.setSecurityOption("1");
		// check cookie
		String userCookie = req.getCookieValue(CookieUtils.getUniqueCookieName(userInfo));
		if (userCookie == null)
			userInfo.setComputerRegistered(false);
		else
		{
			String digest = Utils.oneWayHash(userInfo.getClearText());
			userInfo.setComputerRegistered(digest.equals(userCookie));
		}

		// added by Jingde Wang on 2011-04-03 in NY for all countries.
		try
		{
			// user.getCountryCode() is added in the following function call. 2011-04-03
			EmailTemplate et = EmailTemplate.get(user.getCountryCode(), EmailTemplate.SECURITY_QUESTIONS_CHANGED);

			String subject = et.getSubject();
			String body = et.getBody();
			SimpleHtmlEmail mail = new SimpleMailUsingHTML();
			mail.setTo(user.getEmail());
			mail.setSubject(subject);
			String userName = UserHelper.getUserName(user.getUserId());
			JetTimestamp jtsNow = new JetTimestamp();
			body = body.replaceAll("USERNAME", userName);

			if (user.isBrazilUser())
				body = body.replaceAll("DATE", jtsNow.toString("dd/MM/yyyy"));
			else
				body = body.replaceAll("DATE", jtsNow.toString("yyyy-MM-dd"));

			body = body.replaceAll("TIME", jtsNow.toString("HH:mm:ss"));
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
			idid.setTxName(ConfTxns.SECURITY_QUESTIONS);
			InformHelper.sendConfirmationText(idid, null, hbSession);
		}

	}

}
