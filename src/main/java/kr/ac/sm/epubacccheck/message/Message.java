package kr.ac.sm.epubacccheck.message;

import java.util.ArrayList;
import java.util.List;

import kr.ac.sm.epubacccheck.report.EPUBLocation;

public class Message
{
	private MessageId messageId;
	private MessageSeverity severity;
	private String message;
	private List<EPUBLocation> locations;

	public Message(MessageId messageId)
	{
		locations = new ArrayList<EPUBLocation>();
		
		this.messageId = messageId;
		this.severity = MessageSeverityMap.getMessgeSeverity(messageId);
		this.locations = MessageLocationMap.getEPUBLocationList(messageId);
		this.message = getBundleMessage(messageId.toString());
	}

	public MessageId getMessageId()
	{
		return messageId;
	}

	public MessageSeverity getSeverity()
	{
		return severity;
	}
	
	public EPUBLocation getLocation(int index)
	{
		return locations.get(index);
	}
	
	public List<EPUBLocation> getLocations()
	{
		return locations;
	}
	
	public int getLineNumber(int index)
	{
		return locations.get(index).getLineNumber();
	}

	public String getMessage()
	{
		return message;
	}

	private String getBundleMessage(String key)
	{
		return MessageBundle.getMessage(key);
	}
}
