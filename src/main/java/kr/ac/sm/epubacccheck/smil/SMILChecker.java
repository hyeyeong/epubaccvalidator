package kr.ac.sm.epubacccheck.smil;

import kr.ac.sm.epubacccheck.report.Report;
import kr.ac.sm.epubacccheck.util.FileChecker;
import kr.ac.sm.epubacccheck.util.XMLDocParser;

public class SMILChecker implements FileChecker
{
	public void check(String filePath, Report report)
	{
		// TODO Auto-generated method stub
		SMILAccessibilityHandler svgHandler = new SMILAccessibilityHandler();
		XMLDocParser parser = new XMLDocParser();
		parser.parse(filePath, svgHandler);
	}
}
