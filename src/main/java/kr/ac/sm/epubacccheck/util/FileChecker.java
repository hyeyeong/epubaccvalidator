package kr.ac.sm.epubacccheck.util;

import kr.ac.sm.epubacccheck.report.Report;

public interface FileChecker
{
	public void check(String filePath, Report report);
}
