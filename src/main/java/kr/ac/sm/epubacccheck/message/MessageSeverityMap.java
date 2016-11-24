package kr.ac.sm.epubacccheck.message;

import java.util.HashMap;

public class MessageSeverityMap
{
	private static HashMap<MessageId, MessageSeverity> severities = new HashMap<MessageId, MessageSeverity>();
	
	public static void initMessageSeverities()
	{
		severities.put(MessageId.NAV_001, MessageSeverity.ERROR);
		severities.put(MessageId.NAV_001_W, MessageSeverity.WARNING);
		severities.put(MessageId.NAV_002_1, MessageSeverity.WARNING);
		severities.put(MessageId.NAV_003, MessageSeverity.WARNING);
		severities.put(MessageId.NAV_004, MessageSeverity.WARNING);
		severities.put(MessageId.NAV_005, MessageSeverity.WARNING);
		severities.put(MessageId.NAV_006, MessageSeverity.WARNING);
		severities.put(MessageId.EPUBTYPE_001, MessageSeverity.WARNING);
		severities.put(MessageId.EPUBTYPE_002, MessageSeverity.ERROR);
		severities.put(MessageId.OPF_001, MessageSeverity.ERROR);
		severities.put(MessageId.OPF_002, MessageSeverity.ERROR);
		severities.put(MessageId.OPF_002_W, MessageSeverity.WARNING);
		
		severities.put(MessageId.LANG_001, MessageSeverity.ERROR);
		severities.put(MessageId.LANG_002, MessageSeverity.WARNING);
		severities.put(MessageId.TITLE_001, MessageSeverity.ERROR);
		severities.put(MessageId.TITLE_002, MessageSeverity.WARNING);
		severities.put(MessageId.LINK_001, MessageSeverity.WARNING);
		severities.put(MessageId.STYLE_001, MessageSeverity.WARNING);
		severities.put(MessageId.STYLE_002, MessageSeverity.WARNING);
		severities.put(MessageId.STYLE_003, MessageSeverity.ERROR);
		severities.put(MessageId.TABLE_001, MessageSeverity.ERROR);
		severities.put(MessageId.TABLE_002, MessageSeverity.WARNING);
		severities.put(MessageId.TABLE_003, MessageSeverity.ERROR);
		severities.put(MessageId.IMG_001, MessageSeverity.ERROR);
		severities.put(MessageId.IMG_002, MessageSeverity.ERROR);
		severities.put(MessageId.IMG_003, MessageSeverity.ERROR);
		severities.put(MessageId.IMG_003_W, MessageSeverity.WARNING);
		severities.put(MessageId.MEDIA_001, MessageSeverity.WARNING);
		severities.put(MessageId.MEDIA_002, MessageSeverity.WARNING);
		severities.put(MessageId.MEDIA_001_W, MessageSeverity.WARNING);
		severities.put(MessageId.MEDIA_002_W, MessageSeverity.WARNING);
		severities.put(MessageId.JSARIA_001, MessageSeverity.ERROR);
		severities.put(MessageId.JSARIA_002, MessageSeverity.ERROR);
		severities.put(MessageId.JSARIA_003, MessageSeverity.WARNING);
		severities.put(MessageId.NOTE_001, MessageSeverity.ERROR);
		
		severities.put(MessageId.CSS_001, MessageSeverity.ERROR);
		severities.put(MessageId.CSS_002, MessageSeverity.ERROR);
		severities.put(MessageId.CSS_003, MessageSeverity.ERROR);
		severities.put(MessageId.CSS_004, MessageSeverity.ERROR);
		severities.put(MessageId.CSS_005, MessageSeverity.ERROR);
		severities.put(MessageId.CSS_005_W, MessageSeverity.WARNING);
		severities.put(MessageId.CSS_006, MessageSeverity.WARNING);
		severities.put(MessageId.CSS_007, MessageSeverity.ERROR);
		severities.put(MessageId.CSS_008, MessageSeverity.WARNING);
		severities.put(MessageId.CSS_009, MessageSeverity.ERROR);
		severities.put(MessageId.CSS_010, MessageSeverity.ERROR);
		severities.put(MessageId.CSS_011, MessageSeverity.ERROR);
		
		severities.put(MessageId.SVG_001, MessageSeverity.ERROR);
		severities.put(MessageId.SVG_002, MessageSeverity.ERROR);
		severities.put(MessageId.SMIL_001, MessageSeverity.ERROR);
		severities.put(MessageId.SMIL_002, MessageSeverity.WARNING);
	}
	
	public static MessageSeverity getMessgeSeverity(MessageId id)
	{
		return severities.get(id);
	}
}
