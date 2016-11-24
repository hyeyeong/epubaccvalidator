package kr.ac.sm.epubacccheck.css;

import java.util.List;

import org.idpf.epubcheck.util.css.CssContentHandler;
import org.idpf.epubcheck.util.css.CssErrorHandler;
import org.idpf.epubcheck.util.css.CssExceptions.CssException;
import org.idpf.epubcheck.util.css.CssGrammar;
import org.idpf.epubcheck.util.css.CssGrammar.CssAtRule;
import org.idpf.epubcheck.util.css.CssGrammar.CssDeclaration;
import org.idpf.epubcheck.util.css.CssGrammar.CssSelector;

import kr.ac.sm.epubacccheck.message.MessageId;
import kr.ac.sm.epubacccheck.report.EPUBLocation;
import kr.ac.sm.epubacccheck.report.Report;

public class CSSAccessibilityHandler implements CssContentHandler, CssErrorHandler
{
	private boolean hasVisibility = false;
	private String filePath;
	private Report report;

	public void error(CssException e) throws CssException
	{
		// TODO Auto-generated method stub
		e.printStackTrace();
	}
	
	public void setReport(Report report)
	{
		this.report = report;
	}
	
	public void setFilePath(String path)
	{
		this.filePath = path;
	}

	public void startDocument()
	{
		// TODO Auto-generated method stub
	}

	public void startAtRule(CssAtRule atRule)
	{
		// TODO Auto-generated method stub
	}

	public void endAtRule(String name)
	{
		// TODO Auto-generated method stub
	}

	public void selectors(List<CssSelector> selectors)
	{
		// TODO Auto-generated method stub
	}

	public void endSelectors(List<CssSelector> selectors)
	{
		// TODO Auto-generated method stub
	}

	public void declaration(CssDeclaration declaration)
	{
		// TODO Auto-generated method stub
		String cssAttribute = declaration.getName().get();

		// CSS-002
		if (cssAttribute.equals("cursor"))
		{
			report.addMessage(MessageId.CSS_002, new EPUBLocation(filePath, declaration.getLocation().getLine(), declaration.getLocation().getColumn()));
		}
		
		// CSS-003
		if (cssAttribute.equals("overflow"))
		{
			for (CssGrammar.CssConstruct cssc : declaration.getComponents())
			{
				if (cssc.toCssString() == null || "hidden".equals(cssc.toCssString()))
				{
					report.addMessage(MessageId.CSS_003, new EPUBLocation(filePath, declaration.getLocation().getLine(), declaration.getLocation().getColumn()));
				}
			}
		}
		
		// CSS-004
		if (cssAttribute.equals("width"))
		{
			for (CssGrammar.CssConstruct cssc : declaration.getComponents())
			{
				if (cssc.toCssString() == null || "0px".equals(cssc.toCssString()))
				{
					report.addMessage(MessageId.CSS_004, new EPUBLocation(filePath, declaration.getLocation().getLine(), declaration.getLocation().getColumn()));
				}
			}
		}
		
		// CSS-005
		if (cssAttribute.equals("background-image"))
		{
			for (CssGrammar.CssConstruct cssc : declaration.getComponents())
			{
				if (cssc.toCssString() == null || cssc.toCssString().equals("") || cssc.toCssString().equals("url('')") || cssc.toCssString().equals("url(\"\")") || cssc.toCssString().equals("none"))
				{
					report.addMessage(MessageId.CSS_005, new EPUBLocation(filePath, declaration.getLocation().getLine(), declaration.getLocation().getColumn()));
				}
			}
		}
		
		// CSS-006
		if (cssAttribute.equals("font-size"))
		{
			for (CssGrammar.CssConstruct cssc : declaration.getComponents())
			{
				if (cssc.toCssString() == null || cssc.toCssString().contains("pt") || cssc.toCssString().contains("px") || cssc.toCssString().contains("x-small"))
				{
					report.addMessage(MessageId.CSS_006, new EPUBLocation(filePath, declaration.getLocation().getLine(), declaration.getLocation().getColumn()));
				}
			}
		}
		
		// CSS-007
		if (cssAttribute.equals("text-align"))
		{
			for (CssGrammar.CssConstruct cssc : declaration.getComponents())
			{
				if (cssc.toCssString() == null || cssc.toCssString().equals("justify"))
				{
					report.addMessage(MessageId.CSS_007, new EPUBLocation(filePath, declaration.getLocation().getLine(), declaration.getLocation().getColumn()));
				}
			}
		}
		
		// CSS-008 sup - with NOTE-001
		// CSS-009 fixed layout

		// CSS-010 background-color contrast - STYLE-003
		if (cssAttribute.equals("background-color"))
		{
			// WCAG20 contrast ratio
			;
		}
		
		// CSS-011
		if (cssAttribute.equals("visibility") || cssAttribute.equals("display"))
		{
			hasVisibility = true;
			
			for (CssGrammar.CssConstruct cssc : declaration.getComponents())
			{
				if (cssc.toCssString() == null || cssc.toCssString().equals("hidden") || cssc.toCssString().equals("none"))
				{
					report.addMessage(MessageId.CSS_011, new EPUBLocation(filePath, cssc.getLocation().getLine(), declaration.getLocation().getColumn()));
				}
			}
		}
	}
	
	public void endDocument()
	{
		// TODO Auto-generated method stub
		if (!hasVisibility)
		{
			//System.out.println(x);
		}
	}
}
