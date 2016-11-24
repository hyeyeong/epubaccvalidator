package kr.ac.sm.epubacccheck.report;

public class EPUBLocation
{
	private String filePath;
	private int lineNumber;
	private int columnNumber;

	public EPUBLocation(String path, int line, int column)
	{
		this.filePath = path;
		this.lineNumber = line;
		this.columnNumber = column;
	}

	public String getFilePath()
	{
		return filePath;
	}

	public int getLineNumber()
	{
		return lineNumber;
	}

	public int getColumnNumber()
	{
		return columnNumber;
	}
}
