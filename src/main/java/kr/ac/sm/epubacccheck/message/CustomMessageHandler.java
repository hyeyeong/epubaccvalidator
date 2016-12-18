package kr.ac.sm.epubacccheck.message;

import java.util.ArrayList;

public class CustomMessageHandler {
	public static ArrayList<String> customMessageList = new ArrayList<String>();
	public static ArrayList<MessageId> customMessageIdList = new ArrayList<MessageId>();
	
	public static void addCustomMessage(MessageId messageId, String customMessage)
	{
		customMessageList.add(customMessage);
		customMessageIdList.add(messageId);
	}

	public static ArrayList<String> getCustomMessages(MessageId messageId)
	{
		ArrayList<String> messages = new ArrayList<String>();
		
		for (int i = 0; i < customMessageIdList.size(); i++)
		{
			if (customMessageIdList.get(i).equals(messageId))
			{
				messages.add(customMessageList.get(i));
			}
		}
		
		return messages;
	}

	public static ArrayList<MessageId> getCustomMessageIdList()
	{
		return customMessageIdList;
	}
}
