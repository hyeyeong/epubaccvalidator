package kr.ac.sm.epubacccheck.message;

public enum MessageSeverity
{
	ERROR ("ERROR"),
	WARNING ("WARNING");
	
	private final String name;
	
	MessageSeverity(String feature)
	{
		this.name = feature;
	}
	
	public String toString()
	{
		return name;
	}
}
