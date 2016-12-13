package kr.ac.sm.epubacccheck.smil;

import org.xml.sax.Attributes;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import kr.ac.sm.epubacccheck.message.MessageId;
import kr.ac.sm.epubacccheck.report.EPUBLocation;
import kr.ac.sm.epubacccheck.report.Report;

public class SMILAccessibilityHandler extends DefaultHandler
{
	private Locator locator;

	private boolean hasList = false;
	
	private String filePath;
	private Report report;
	
	public void setDocumentLocator(Locator locator)
	{
        this.locator = locator;
    }
	
	public void setFilePath(String path)
	{
		this.filePath = path;
	}
	
	public void setReport(Report report)
	{
		this.report = report;
	}

	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException
	{
		if (qName.equals("seq"))
		{
			if (attributes.getValue("epub:type") == null)
			{
				report.addMessage(MessageId.SMIL_002_1, new EPUBLocation(filePath, locator.getLineNumber(), locator.getColumnNumber()));
			}
			else if (attributes.getValue("epub:type").equals("list"))
			{
				hasList = true;
			}
		}
		
		if (qName.equals("par"))
		{
			if (hasList)
			{
				if (attributes.getValue("epub:type") == null)
				{
					report.addMessage(MessageId.SMIL_002_2, new EPUBLocation(filePath, locator.getLineNumber(), locator.getColumnNumber()));
				}
				else if (attributes.getValue("epub:type").equals("") || attributes.getValue("epub:type").isEmpty())
				{
					report.addMessage(MessageId.SMIL_002_3, new EPUBLocation(filePath, locator.getLineNumber(), locator.getColumnNumber()));
				}
			}
		}
	}
}