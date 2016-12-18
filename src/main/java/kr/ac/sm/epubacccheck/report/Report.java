package kr.ac.sm.epubacccheck.report;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import kr.ac.sm.epubacccheck.message.CustomMessageHandler;
import kr.ac.sm.epubacccheck.message.MessageId;
import kr.ac.sm.epubacccheck.message.MessageLocationMap;

public class Report
{
	private ReportWriter reportWriter;
	private String reportPath;
	
	public Report()
	{
		reportPath = getDate() + ".json";
		reportWriter = new ReportWriter();
	}

	public void addMessage(MessageId messageId, EPUBLocation location)
	{
		MessageLocationMap.addLocation(messageId, location);
	}
	
	public void addMessage(MessageId messageId, String customMessage, EPUBLocation location)
	{
		MessageLocationMap.addLocation(messageId, location);
		System.out.println("custom message: " + customMessage);
		CustomMessageHandler.addCustomMessage(messageId, customMessage);
	}
	
	public void createReport()
	{
		reportWriter.writeReport(reportPath);
	}

	public void setReportPath(String reportPath)
	{
		this.reportPath = reportPath;
	}

	public String getReportPath()
	{
		return reportPath;
	}

	private String getDate()
	{
		SimpleDateFormat formatter = new SimpleDateFormat ( "yyMMdd_HHmmss", Locale.KOREA ); 
		Date currentDate = new Date ( ); 
		return formatter.format (currentDate); 
	}
}
