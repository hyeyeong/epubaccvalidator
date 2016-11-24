package kr.ac.sm.epubacccheck.epub;

import java.util.ArrayList;

import kr.ac.sm.epubacccheck.report.Report;
import kr.ac.sm.epubacccheck.util.FileChecker;
import kr.ac.sm.epubacccheck.util.XMLDocParser;

public class OPFChecker implements FileChecker
{
	private OPFCheckHandler handler;

	public void check(String filePath, Report report)
	{
		// TODO Auto-generated method stub
		handler = new OPFCheckHandler();
		handler.setFilePath(filePath);
		handler.setReport(report);
		
		XMLDocParser parser = new XMLDocParser();
		parser.parse(filePath, handler);
	}
	
	public ArrayList<EpubFile> getEpubFileList()
	{
		return handler.getFileList();
	}
}
