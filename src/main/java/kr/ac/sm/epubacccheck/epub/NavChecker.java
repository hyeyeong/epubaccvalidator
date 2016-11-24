package kr.ac.sm.epubacccheck.epub;

import kr.ac.sm.epubacccheck.report.Report;
import kr.ac.sm.epubacccheck.util.FileChecker;
import kr.ac.sm.epubacccheck.util.XMLDocParser;

public class NavChecker implements FileChecker
{
	private NavCheckHandler handler;

	public void check(String filePath, Report report)
	{
		// TODO Auto-generated method stub
		handler = new NavCheckHandler();
		handler.setFilePath(filePath);
		handler.setReport(report);

		XMLDocParser parser = new XMLDocParser();
		parser.parse(filePath, handler);
	}
}
