package kr.ac.sm.epubacccheck.epub;

import org.xml.sax.Attributes;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import kr.ac.sm.epubacccheck.message.MessageBundle;
import kr.ac.sm.epubacccheck.message.MessageId;
import kr.ac.sm.epubacccheck.report.EPUBLocation;
import kr.ac.sm.epubacccheck.report.Report;
import kr.ac.sm.epubacccheck.util.EpubInfo;

public class NavCheckHandler extends DefaultHandler
{
	private Locator locator;
	
	private boolean isNav = false;
	private boolean isLiInNav = false;
	
	private boolean hasNav = false;
	private boolean hasATag = false;
	private boolean hasSpanTag = false;
	private boolean hasLoI = false;
	private boolean hasLoT = false;
	private boolean hasLoV = false;
	private boolean hasLoA = false;

	private int liCountInNav = 0;
	private int navLineNumber = 0;
	
	private Report report;
	private String filePath;
	private String fileCountMessage;
	
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
		// NAV-001
		if (qName.equals("nav") && attributes.getValue("epub:type").equals("toc"))
		{
			hasNav = true;
			isNav = true;
			navLineNumber = locator.getLineNumber();
		}
		
		// NAV-002
		if (isNav)
		{
			if (qName.equals("li"))
			{
				isLiInNav = true;
				liCountInNav++;
			}
			
			if (isLiInNav)
			{
				if (qName.equals("a"))
				{
					hasATag = true;
				}
				else if (qName.equals("span"))
				{
					hasSpanTag = true;
					report.addMessage(MessageId.NAV_002_2, new EPUBLocation(filePath, locator.getLineNumber(), locator.getColumnNumber()));
				}
			}
		}
		
		// NAV-003
		if (qName.equals("nav") && attributes.getValue("epub:type").equals("loi"))
		{
			hasLoI = true;
		}
		
		// NAV-004
		if (qName.equals("nav") && attributes.getValue("epub:type").equals("lot"))
		{
			hasLoT = true;
		}
		
		// NAV-005
		if (qName.equals("nav") && attributes.getValue("epub:type").equals("lov"))
		{
			hasLoV = true;
		}
		
		// NAV-006
		if (qName.equals("nav") && attributes.getValue("epub:type").equals("loa"))
		{
			hasLoA = true;
		}
	}
	
	public void endElement(String uri, String localName, String qName)
	{
		if (qName.equals("li"))
		{
			isLiInNav = false;
			
			if (!hasATag && !hasSpanTag)
			{
				report.addMessage(MessageId.NAV_002_1, new EPUBLocation(filePath, navLineNumber, 1));
			}
		}
		
		if (qName.equals("nav"))
		{
			isNav = false;
		}
	}
	
	public void endDocument()
	{	
		if (!hasNav)
		{
			report.addMessage(MessageId.NAV_001, new EPUBLocation(filePath, 1, 1));
		}
		
		if (liCountInNav != EpubInfo.epubFileCount)
		{
			makeEpubFileCountMessage();
			report.addMessage(MessageId.NAV_001_W, fileCountMessage, new EPUBLocation(filePath, navLineNumber, 1));
		}
		
		if (!hasLoI)
		{
			report.addMessage(MessageId.NAV_003, new EPUBLocation(filePath, 1, 1));
		}
		
		if (!hasLoT)
		{
			report.addMessage(MessageId.NAV_004, new EPUBLocation(filePath, 1, 1));
		}
		
		if (!hasLoV)
		{
			report.addMessage(MessageId.NAV_005, new EPUBLocation(filePath, 1, 1));
		}
		
		if (!hasLoA)
		{
			report.addMessage(MessageId.NAV_006, new EPUBLocation(filePath, 1, 1));
		}
	}
	
	private void makeEpubFileCountMessage()
	{
		String originMessage = MessageBundle.getMessage(MessageId.NAV_001_W.toString());
		fileCountMessage =  new StringBuilder().append(originMessage)
								.append(" File Count in nav.xhtml: ")
								.append(liCountInNav)
								.append(" / File Count in EPUB: ")
								.append(EpubInfo.epubFileCount)
								.toString();
	}
}
