package kr.ac.sm.epubacccheck.epub;

import kr.ac.sm.epubacccheck.util.FileExt;

public class EpubFile
{
	private String path;
	private FileExt ext;
	
	public void setFilePath(String filePath)
	{
		this.path = filePath;
	}
	
	public void setExt(FileExt ext)
	{
		this.ext = ext;
	}
	
	public String getFilePath()
	{
		return this.path;
	}
	
	public FileExt getFileExt()
	{
		return this.ext;
	}
}
