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
import kr.ac.sm.epubacccheck.util.EpubInfo;

public class CSSAccessibilityHandler implements CssContentHandler, CssErrorHandler
{
	private boolean hasVisibility = false;
	private String filePath;
	private Report report;
	private double[] yb;
	private double[] yd;
	private double[] luminances;
	
	private boolean hasL1 = false;
	

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
		luminances = new double[2];
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
		yb = new double[3];
		yd = new double[3];

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
		
		// CSS-005, CSS-009
		if (cssAttribute.equals("background-image"))
		{
			if (EpubInfo.isFixedLayout)
			{
				// check attribute value
				for (CssGrammar.CssConstruct cssc : declaration.getComponents())
				{
					if (cssc.toCssString().equals("") || cssc.toCssString().equals("url('')") || cssc.toCssString().equals("url(\"\")") || cssc.toCssString().equals("none"))
					{
						report.addMessage(MessageId.CSS_005_W, new EPUBLocation(filePath, declaration.getLocation().getLine(), declaration.getLocation().getColumn()));
					}
				}
			}
			else
			{
				// warning : css 005 - use carefully bg-image
				report.addMessage(MessageId.CSS_005, new EPUBLocation(filePath, declaration.getLocation().getLine(), declaration.getLocation().getColumn()));
				report.addMessage(MessageId.CSS_009, new EPUBLocation(filePath, declaration.getLocation().getLine(), declaration.getLocation().getColumn()));
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

		// CSS-010 background-color contrast - STYLE-003
		if (cssAttribute.equals("background-color"))
		{
			// WCAG20 contrast ratio
			for (CssGrammar.CssConstruct cssc : declaration.getComponents())
			{
				System.out.println(cssc.toString());
				if (cssc.toString().contains("#"))
				{
					int codeIndex = cssc.toString().indexOf("#");
					
					String tempCode = cssc.toString().substring(codeIndex + 1, codeIndex + 3);
					yb[0] = Integer.parseInt(tempCode, 16);
					
					tempCode = cssc.toString().substring(codeIndex + 3, codeIndex + 5);
					yb[1] = Integer.parseInt(tempCode, 16);
					
					tempCode = cssc.toString().substring(codeIndex + 5, codeIndex + 7);
					yb[2] = Integer.parseInt(tempCode, 16);
					
					calculateContrastRatio(yb);
				}
			}
		}
		
		if (cssAttribute.equals("color"))
		{
			for (CssGrammar.CssConstruct cssc : declaration.getComponents())
			{
				System.out.println(cssc.toString());
				if (cssc.toString().contains("#"))
				{
					int codeIndex = cssc.toString().indexOf("#");
					
					String tempCode = cssc.toString().substring(codeIndex + 1, codeIndex + 3);
					yd[0] = Integer.parseInt(tempCode, 16);
					
					tempCode = cssc.toString().substring(codeIndex + 3, codeIndex + 5);
					yd[1] = Integer.parseInt(tempCode, 16);
					
					tempCode = cssc.toString().substring(codeIndex + 5, codeIndex + 7);
					yd[2] = Integer.parseInt(tempCode, 16);
					
					System.out.println(yd[0] + " " + yd[1] + " " + yd[2]);
					
					calculateContrastRatio(yd);
				}
			}
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
	
	private void calculateContrastRatio(double[] colorcode)
	{
		double[] sRGB = new double[3];
		double[] RGB = new double[3];
		double luminance = 0.0;
		double ratio = 0.0;
		
		for (int i = 0; i < 3; i++)
		{
			sRGB[i] = Math.floor(colorcode[i] / 255 * 10000d) / 10000d;
			
			if (sRGB[i] <= 0.03928)
			{
				RGB[i] = sRGB[i] / 12.92;
			}
			else
			{
				RGB[i] = Math.floor(Math.pow((sRGB[i] + 0.055) / 1.055, 2.4) * 10000d) / 10000d;
			}
			
			System.out.println("srgb: " + sRGB[i] + " / rgb: " + colorcode[i] + " / RGB: " + RGB[i]);
		}
		
		luminance = Math.floor(((0.2126 * RGB[0]) + (0.7152 * RGB[1]) + (0.0722 * RGB[2])) * 10000d) / 10000d;
		System.out.println("luminance: " + luminance);
		
		if (!hasL1)
		{
			luminances[0] = luminance;
			hasL1 = true;
		}
		else
		{
			luminances[1] = luminance;
		}
		
		if (luminances[0] > luminances[1])
		{
			ratio = (luminances[0] + 0.05) / (luminances[1] + 0.05);
		}
		else
		{
			ratio = (luminances[1] + 0.05) / (luminances[0] + 0.05);
		}

		System.out.println("ratio: " + ratio);
		hasL1 = false;
	}
}
