package kr.ac.sm.epubacccheck.svg;

import kr.ac.sm.epubacccheck.report.Report;
import kr.ac.sm.epubacccheck.util.FileChecker;
import kr.ac.sm.epubacccheck.util.XMLDocParser;

public class SVGChecker implements FileChecker
{
	public void check(String filePath, Report report)
	{
		// TODO Auto-generated method stub
		SVGAccessibilityHandler svgHandler = new SVGAccessibilityHandler();
		XMLDocParser parser = new XMLDocParser();
		parser.parse(filePath, svgHandler);
	}
}
