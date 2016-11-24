package kr.ac.sm.epubacccheck.report;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import kr.ac.sm.epubacccheck.message.Message;
import kr.ac.sm.epubacccheck.message.MessageId;
import kr.ac.sm.epubacccheck.message.MessageLocationMap;

public class ReportWriter
{
	private List<Message> messages;
	private HashMap<String, List<Message>> messageMap;

	public ReportWriter()
	{
		messages = new ArrayList<Message>();
		messageMap = new LinkedHashMap<String, List<Message>>();
	}
	
	public void writeReport(String filePath)
	{
		File reportFile = new File(filePath);
		ObjectMapper mapper = new ObjectMapper();
		ArrayList<MessageId> messageIdList = new ArrayList<MessageId>();
		
		for (MessageId messageId : MessageId.values())
		{
			List<EPUBLocation> locations = MessageLocationMap.getEPUBLocationList(messageId);
			if (locations.size() != 0)
			{
				messageIdList.add(messageId);
			}
		}
		
		for (int i = 0; i < messageIdList.size(); i++)
		{
			Message message = new Message(messageIdList.get(i));
			messages.add(message);
		}

		messageMap.put("message", messages);
		
		try {
			mapper.writerWithDefaultPrettyPrinter().writeValue(reportFile, messageMap);
		} catch (JsonGenerationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
