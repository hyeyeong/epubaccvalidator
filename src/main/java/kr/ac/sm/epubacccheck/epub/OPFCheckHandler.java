package kr.ac.sm.epubacccheck.epub;

import java.util.ArrayList;

import org.xml.sax.Attributes;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import kr.ac.sm.epubacccheck.message.MessageId;
import kr.ac.sm.epubacccheck.report.EPUBLocation;
import kr.ac.sm.epubacccheck.report.Report;
import kr.ac.sm.epubacccheck.util.EpubInfo;
import kr.ac.sm.epubacccheck.util.FileExt;

public class OPFCheckHandler extends DefaultHandler
{
	private Locator locator;
	private ArrayList<EpubFile> epubFileList;
	private EpubFile epubFile;
	
	private boolean hasDcLanguage = false;
	private boolean hasSpine = false;
	private boolean hasMediaOverlay = false;
	
	private int xhtmlFileCount = 0;
	private int metadataLine = 0;
	
	private String filePath;
	private Report report;
	
	public OPFCheckHandler()
	{
		this.epubFileList = new ArrayList<EpubFile>();
	}
	
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
	
	public void startDocument()
	{
		;
	}
	
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException
	{
		String value = null;
		epubFile = new EpubFile();
		
		if (qName.equals("metadata"))
		{
			metadataLine = locator.getLineNumber();
		}
		
		if (qName.equals("meta"))
		{
			if (attributes.getValue("property") != null)
			{
				if (attributes.getValue("property").equals("rendition:layout-pre-paginated"))
				{
					EpubInfo.isFixedLayout = true;
				}
			}
		}

		if (qName.equals("item"))
		{
			value = attributes.getValue("href");
			epubFile.setFilePath(value);

			if (value.contains(".css"))
			{
				epubFile.setExt(FileExt.CSS);
			}
			else if (value.contains(".svg"))
			{
				epubFile.setExt(FileExt.SVG);
			}
			else if (value.contains(".xhtml"))
			{
				if (attributes.getValue("properties") != null && attributes.getValue("properties").equals("nav"))
				{
					epubFile.setExt(FileExt.NAV);
				}
				else
				{
					xhtmlFileCount++;
					epubFile.setExt(FileExt.XHTML);
				}
			}
			else
			{
				epubFile.setExt(FileExt.NONE);
			}

			epubFileList.add(epubFile);

			if (attributes.getValue("media-overlay") != null)
			{
				hasMediaOverlay = true;
			}
		}

		if (qName.equals("item") && hasMediaOverlay)
		{
			if (attributes.getValue("media-type") == null)
			{
				report.addMessage(MessageId.SMIL_002_1, new EPUBLocation(filePath, locator.getLineNumber(), locator.getColumnNumber()));
			}
			else if (attributes.getValue("media-type").equals("") || attributes.getValue("media-type").isEmpty())
			{
				report.addMessage(MessageId.SMIL_001_W, new EPUBLocation(filePath, locator.getLineNumber(), locator.getColumnNumber()));
			}
		}
		
		// OPF-001
		if (qName.equals("dc:language"))
		{
			hasDcLanguage = true;
		}
		
		// OPF-002
		if (qName.equals("spine"))
		{
			hasSpine = true;
			if (attributes.getValue("toc") == null || attributes.getValue("toc").equals(""))
			{
				report.addMessage(MessageId.OPF_002_W, new EPUBLocation(filePath, locator.getLineNumber(), locator.getColumnNumber()));
			}
		}
	}
	
	public void endDocument()
	{
		// OPF-001
		if (!hasDcLanguage)
		{
			report.addMessage(MessageId.OPF_001, new EPUBLocation(filePath, metadataLine, 1));
		}
		
		// OPF-002
		if (!hasSpine)
		{
			report.addMessage(MessageId.OPF_002_W, new EPUBLocation(filePath, 1, 1));
		}
		
		EpubInfo.epubFileCount = xhtmlFileCount;
	}

	public ArrayList<EpubFile> getFileList()
	{
		return this.epubFileList;
	}
}
