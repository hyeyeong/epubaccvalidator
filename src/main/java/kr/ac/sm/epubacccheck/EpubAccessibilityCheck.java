package kr.ac.sm.epubacccheck;

import kr.ac.sm.epubacccheck.api.EpubAccessibilityValidator;

public class EpubAccessibilityCheck
{
	public static void main(String[] args)
	{
		String filePath = "test.css";
		EpubAccessibilityValidator validator = new EpubAccessibilityValidator();
		validator.validate(filePath);
	}
}