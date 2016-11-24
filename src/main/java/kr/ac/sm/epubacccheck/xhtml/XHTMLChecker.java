package kr.ac.sm.epubacccheck.xhtml;

import kr.ac.sm.epubacccheck.report.Report;
import kr.ac.sm.epubacccheck.util.FileChecker;
import kr.ac.sm.epubacccheck.util.XMLDocParser;

public class XHTMLChecker implements FileChecker
{
	public void check(String filePath, Report report)
	{
		// TODO Auto-generated method stub
		XHTMLAccessibilityHandler handler = new XHTMLAccessibilityHandler();
		handler.setReport(report);
		handler.setFilePath(filePath);
		XMLDocParser parser = new XMLDocParser();
		parser.parse(filePath, handler);
	}
}
