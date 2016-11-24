package kr.ac.sm.epubacccheck.util;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class XMLDocParser
{
	public void parse(String filePath, DefaultHandler handler)
	{
		File docFile = new File(filePath);
		SAXParser saxParser;
		try {
			SAXParserFactory factory = SAXParserFactory.newInstance();
			saxParser = factory.newSAXParser();
			saxParser.parse(docFile, handler);
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			//System.out.println("ParserConfigurationException error");
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			//System.out.println("SAXException error");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println("File not found");
		}
	}
}
