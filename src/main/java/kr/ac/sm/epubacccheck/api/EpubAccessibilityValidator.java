package kr.ac.sm.epubacccheck.api;

import java.util.ArrayList;

import kr.ac.sm.epubacccheck.css.CSSChecker;
import kr.ac.sm.epubacccheck.epub.EpubFile;
import kr.ac.sm.epubacccheck.epub.NavChecker;
import kr.ac.sm.epubacccheck.epub.OPFChecker;
import kr.ac.sm.epubacccheck.message.MessageLocationMap;
import kr.ac.sm.epubacccheck.message.MessageSeverityMap;
import kr.ac.sm.epubacccheck.report.Report;
import kr.ac.sm.epubacccheck.svg.SVGChecker;
import kr.ac.sm.epubacccheck.util.FileExt;
import kr.ac.sm.epubacccheck.xhtml.XHTMLChecker;

public class EpubAccessibilityValidator 
{
	private String filePath;
	private Report report;

	public EpubAccessibilityValidator()
	{
		MessageLocationMap.initEPUBLocationList();
		MessageSeverityMap.initMessageSeverities();
		this.report = new Report();
	}

	public void validate(String path)
	{
		this.filePath = path;
		FileExt fileExtension = getFileExtension(path);
		
		// input - OPF: validating all files
		if (fileExtension == FileExt.OPF)
		{
			runOPFCheck();
		}
		else // input - unit file
		{
			runCheck(fileExtension);
		}

		report.createReport();
	}

	public void validate(String path, String reportPath)
	{
		report.setReportPath(reportPath);
		validate(path);
	}
	
	private void runOPFCheck()
	{
		OPFChecker opfChecker = new OPFChecker();
		ArrayList<EpubFile> epubFileList = new ArrayList<EpubFile>();
		
		opfChecker.check(filePath, report);
		epubFileList = opfChecker.getEpubFileList();
		
		for (int i = 0; i < epubFileList.size(); i++)
		{
			EpubFile file = epubFileList.get(i);
			filePath = file.getFilePath();
			
			runCheck(file.getFileExt());
		}
	}
	
	// execute validation
	private void runCheck(FileExt fileExt)
	{
		switch (fileExt)
		{
			case XHTML:
				checkXHTML();
			break;
			
			case CSS:
				checkCSS();
			break;
			
			case SVG:
				checkSVG();
			break;
			
			case NAV:
				checkNav();
			break;
			
			default:
			break;
		}
	}
	
	private void checkXHTML()
	{
		new XHTMLChecker().check(filePath, report);
	}
	
	private void checkCSS()
	{
		new CSSChecker().check(filePath, report);
	}
	
	private void checkSVG()
	{
		new SVGChecker().check(filePath, report);
	}
	
	private void checkNav()
	{
		new NavChecker().check(filePath, report);
	}
	
	private FileExt getFileExtension(String path)
	{
		String[] splitted = path.split("\\.");
		String ext = splitted[splitted.length - 1];

		if (ext.equals("xhtml"))
			return FileExt.XHTML;
		else if (ext.equals("css"))
			return FileExt.CSS;
		else if (ext.equals("svg"))
			return FileExt.SVG;
		else if (ext.equals("opf"))
			return FileExt.OPF;
		else
			return FileExt.NONE;
	}
}