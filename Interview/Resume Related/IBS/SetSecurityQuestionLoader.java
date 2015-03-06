package com.bocadc.pbank.security;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.boc.pbank.core.LoginedAction;
import com.boc.usa.ca.topic.TopicConf;
import com.bocusa.ibs.core.PbankUser;
import com.zix.jet.base.JetRequest;
import com.zix.jet.base.JetResponse;
import com.zix.jet.exception.JetException;

public class SetSecurityQuestionLoader extends LoginedAction
{
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void doExecution(JetRequest req, JetResponse resp) throws JetException
	{
		PbankUser user = (PbankUser) req.getUser();
		/*
		List list = TopicConf.getAllSecurityQuestionsList(user.getCountryCode());
		HashMap pageMap = new HashMap();
		pageMap.put("questionList", list);
		pageMap.put("birthday", user.getBirthday());
		List listAll = new ArrayList();
		for (int i = 0; i < TopicConf.userTopicNum; i++)
		{
			Map imap = new HashMap();
			imap.put("questions", list);
			listAll.add(imap);
		}
		pageMap.put("dataList", listAll);
		resp.setData(pageMap);
		*/
		
		Map toPage = new HashMap();
		toPage.put("topicNum", TopicConf.userTopicNum);

		List<Integer> choiceList = TopicConf.getUserAllQuestionsId(user.getUserId());
		if (choiceList == null || choiceList.size() < TopicConf.userTopicNum)
		{
			int[] choiceDefault = new int[] { 1, 5, 9, 13, 17 };
			choiceList = new ArrayList<Integer>();
			for (int i = 0; i < choiceDefault.length; i++)
				choiceList.add(new Integer(choiceDefault[i]));
		}
		
		List[] lists = new ArrayList[TopicConf.userTopicNum];
		for (int i = 0; i < lists.length; i++)
		{
			lists[i] = TopicConf.getAllSecurityQuestionsList(user.getCountryCode());
			for(int j = 0; j<lists[i].size(); j++)
			{
				Map imap = (Map)lists[i].get(j);
				imap.put("default", choiceList.get(i));
			}
			toPage.put("question" + (i+1), lists[i]);
		}
		resp.setData(toPage);
	}

}
