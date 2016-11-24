package kr.ac.sm.epubacccheck.xhtml;

import org.xml.sax.Attributes;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import kr.ac.sm.epubacccheck.message.MessageId;
import kr.ac.sm.epubacccheck.report.EPUBLocation;
import kr.ac.sm.epubacccheck.report.Report;

public class XHTMLAccessibilityHandler extends DefaultHandler 
{
	private Locator locator;
	
	private boolean isSection = false;
	private boolean isTable = false;
	private boolean isFigure = false;

	private boolean hasCaptionInTable = false;
	private boolean hasFigCaptionInFigure = false;

	private int headingCountInSection = 0;
	private int thCountInTable = 0;
	private int figCaptionCountInFigure = 0;
	
	private int startingElementLineNumber = 0;
	
	private Report report;
	private String filePath;

	public void setDocumentLocator(Locator locator)
	{
        this.locator = locator;
    }
	
	public void setReport(Report report)
	{
		this.report = report;
	}
	
	public void setFilePath(String path)
	{
		this.filePath = path;
	}

	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException
	{
		// LANG-001, EPUBTYPE-002
		if (qName.equals("html"))
		{
			if (attributes.getValue("xml:lang") == null && attributes.getValue("lang") == null)
			{
				report.addMessage(MessageId.LANG_001, new EPUBLocation(filePath, locator.getLineNumber(), locator.getColumnNumber()));
			}
			
			if (attributes.getValue("xmlns:epub") == null)
			{
				report.addMessage(MessageId.EPUBTYPE_002, new EPUBLocation(filePath, locator.getLineNumber(), locator.getColumnNumber()));
			}
		}
		
		// LANG-002
		if (qName.equals("body"))
		{
			if (attributes.getValue("xml:lang") == null && attributes.getValue("lang") == null)
			{
				report.addMessage(MessageId.LANG_002, new EPUBLocation(filePath, locator.getLineNumber(), locator.getColumnNumber()));
			}
		}
		
		// TITLE-001, TITLE-002, EPUBTYPE-001
		if (qName.equals("section"))
		{
			isSection = true;
			startingElementLineNumber = locator.getLineNumber();

			if (attributes.getValue("epub:type") == null || attributes.getValue("aria-label") == null || attributes.getValue("title") == null)
			{
				report.addMessage(MessageId.TITLE_002, new EPUBLocation(filePath, locator.getLineNumber(), locator.getColumnNumber()));
			}
		}

		if (isSection)
		{
			if (qName.equals("h1") || qName.equals("h2") || qName.equals("h3") || qName.equals("h4") || qName.equals("h5") || qName.equals("h6"))
			{
				headingCountInSection++;
			}
		}
		
		// LINK-001
		if (qName.equals("a"))
		{
			if (attributes.getValue("title") == null)
			{
				report.addMessage(MessageId.LINK_001, new EPUBLocation(filePath, locator.getLineNumber(), locator.getColumnNumber()));
			}
		}
		
		// STYLE-001
		if (qName.equals("i"))
		{
			report.addMessage(MessageId.STYLE_001, new EPUBLocation(filePath, locator.getLineNumber(), locator.getColumnNumber()));
		}
		
		// STYLE-002
		if (qName.equals("b"))
		{
			report.addMessage(MessageId.STYLE_002, new EPUBLocation(filePath, locator.getLineNumber(), locator.getColumnNumber()));
		}
		
		// STYLE-003 noise background: css -> if selector is div, span, body, p ... etc, background-image is error
		
		// TABLE-001, TABLE-002, TABLE-003
		if (qName.equals("table"))
		{
			isTable = true;
			startingElementLineNumber = locator.getLineNumber();
		}
		
		if (isTable)
		{
			if (qName.equals("caption"))
			{
				hasCaptionInTable = true;
			}
			
			if (qName.equals("th"))
			{
				thCountInTable++;
				if (attributes.getValue("scope") == null || attributes.getValue("scope").equals(""))
				{
					report.addMessage(MessageId.TABLE_002, new EPUBLocation(filePath, locator.getLineNumber(), locator.getColumnNumber()));
				}
			}
		}
		
		// IMG-001
		if (qName.equals("figure"))
		{
			isFigure = true;
			startingElementLineNumber = locator.getLineNumber();
		}
		
		if (isFigure)
		{
			if (qName.equals("figcaption"))
			{
				hasFigCaptionInFigure = true;
				figCaptionCountInFigure++;
			}
		}
		
		// IMG-002
		if (qName.equals("area"))
		{
			if (attributes.getValue("alt") == null || attributes.getValue("alt").isEmpty() || attributes.getValue("alt").equals(""))
			{
				report.addMessage(MessageId.IMG_002, new EPUBLocation(filePath, locator.getLineNumber(), locator.getColumnNumber()));
			}
		}
		
		// IMG-003, JSARIA-003
		if (qName.equals("img"))
		{
			if (attributes.getValue("alt") == null)
			{
				report.addMessage(MessageId.IMG_003, new EPUBLocation(filePath, locator.getLineNumber(), locator.getColumnNumber()));
			}
			
			if (attributes.getValue("alt").isEmpty() || attributes.getValue("alt").equals(""))
			{
				report.addMessage(MessageId.IMG_003_W, new EPUBLocation(filePath, locator.getLineNumber(), locator.getColumnNumber()));
			}
			
			if (attributes.getValue("aria-role") == null || attributes.getValue("aria-role").isEmpty() || attributes.getValue("aria-role").equals(""))
			{
				report.addMessage(MessageId.JSARIA_003, new EPUBLocation(filePath, locator.getLineNumber(), locator.getColumnNumber()));
			}
		}
		
		// MEDIA-001
		if (qName.equals("audio"))
		{
			if (attributes.getValue("controls") == null)
			{
				report.addMessage(MessageId.MEDIA_001, new EPUBLocation(filePath, locator.getLineNumber(), locator.getColumnNumber()));
			}
			else
			{
				if (attributes.getValue("controls").isEmpty() || !attributes.equals("controls"))
				{
					report.addMessage(MessageId.MEDIA_001_W, new EPUBLocation(filePath, locator.getLineNumber(), locator.getColumnNumber()));				}
			}
		}

		// MEDIA-002
		if (qName.equals("video"))
		{
			if (attributes.getValue("controls") == null)
			{
				report.addMessage(MessageId.MEDIA_002, new EPUBLocation(filePath, locator.getLineNumber(), locator.getColumnNumber()));
			}
			else
			{
				if (attributes.getValue("controls").isEmpty() || !attributes.equals("controls"))
				{
					report.addMessage(MessageId.MEDIA_002_W, new EPUBLocation(filePath, locator.getLineNumber(), locator.getColumnNumber()));
				}
			}
		}
		
		// JSARIA-001
		if (attributes.equals("aria-hidden") || attributes.equals("hidden"))
		{
			if (attributes.getValue("aria-hidden").equals("true") || attributes.getValue("hidden").equals("hidden"))
			{
				// warning
				report.addMessage(MessageId.JSARIA_001, new EPUBLocation(filePath, locator.getLineNumber(), locator.getColumnNumber()));
			}
		}

		// JSARIA-002 javascript
		
		// NOTE-001 (with CSS)
		if (qName.equals("sup"))
		{
			report.addMessage(MessageId.NOTE_001, new EPUBLocation(filePath, locator.getLineNumber(), locator.getColumnNumber()));
		}
		
		// CSS-001
		if (attributes.getValue("style") != null)
		{
			report.addMessage(MessageId.CSS_001, new EPUBLocation(filePath, locator.getLineNumber(), locator.getColumnNumber()));
		}
	}
	
	public void endElement(String uri, String localName, String qName) throws SAXException
	{
		if (qName.equals("section"))
		{
			if (headingCountInSection > 1)
			{
				report.addMessage(MessageId.TITLE_001, new EPUBLocation(filePath, startingElementLineNumber, 1));
			}
			
			isSection = false;
			headingCountInSection = 0;
		}
		
		// TABLE-001, TABLE-003
		if (qName.equals("table"))
		{
			if (thCountInTable == 0)
			{
				report.addMessage(MessageId.TABLE_001, new EPUBLocation(filePath, startingElementLineNumber, 1));
			}
			
			if (!hasCaptionInTable)
			{
				report.addMessage(MessageId.TABLE_003, new EPUBLocation(filePath, startingElementLineNumber, 1));
			}
			
			isTable = false;
			thCountInTable = 0;
		}
		
		// IMG-001
		if (qName.equals("figure"))
		{
			if (!hasFigCaptionInFigure || figCaptionCountInFigure > 1)
			{
				report.addMessage(MessageId.IMG_001, new EPUBLocation(filePath, startingElementLineNumber, 1));
			}
		}
	}
	
	public void endDocument()
	{
		System.out.println("xhtml validation complete");
	}
}