package kr.ac.sm.epubacccheck.svg;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class SVGAccessibilityHandler extends DefaultHandler
{
	private boolean hasTitle = false;
	private boolean hasDesc = false;

	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException
	{
		if (qName.equals("svg"))
		{
			if (attributes.getValue("xml:lang") == null)
			{
				System.out.println("error: need xml:lang attribute");
			}
		}
		
		if (qName.equals("title"))
		{
			hasTitle = true;
		}
		
		if (qName.equals("desc"))
		{
			hasDesc = true;
		}
		
		if (!hasTitle && !hasDesc)
		{
			System.out.println("error: need title and desc tag");
		}
	}
}