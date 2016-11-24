package kr.ac.sm.epubacccheck.css;

import java.io.FileInputStream;
import java.io.InputStream;

import org.idpf.epubcheck.util.css.CssParser;
import org.idpf.epubcheck.util.css.CssSource;

import kr.ac.sm.epubacccheck.report.Report;
import kr.ac.sm.epubacccheck.util.FileChecker;

public class CSSChecker implements FileChecker
{
	public void check(String filePath, Report report)
	{
		// TODO Auto-generated method stub
        try
        {
    		InputStream inputStream = new FileInputStream(filePath);
            CssSource source = new CssSource(filePath, inputStream);
            CssParser parser = new CssParser();
            CSSAccessibilityHandler handler = new CSSAccessibilityHandler();
            handler.setFilePath(filePath);
    		handler.setReport(report);
            
			parser.parse(source, handler, handler);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
