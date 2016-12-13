package kr.ac.sm.epubacccheck.message;

import java.util.ArrayList;
import java.util.HashMap;

import kr.ac.sm.epubacccheck.report.EPUBLocation;

public class MessageLocationMap
{
	static HashMap<MessageId, ArrayList<EPUBLocation>> locations = new HashMap<MessageId, ArrayList<EPUBLocation>>();

	public static void initEPUBLocationList()
	{
		locations.put(MessageId.NAV_001, new ArrayList<EPUBLocation>());
		locations.put(MessageId.NAV_001_W, new ArrayList<EPUBLocation>());
		locations.put(MessageId.NAV_002_1, new ArrayList<EPUBLocation>());
		locations.put(MessageId.NAV_002_2, new ArrayList<EPUBLocation>());
		locations.put(MessageId.NAV_003, new ArrayList<EPUBLocation>());
		locations.put(MessageId.NAV_004, new ArrayList<EPUBLocation>());
		locations.put(MessageId.NAV_005, new ArrayList<EPUBLocation>());
		locations.put(MessageId.NAV_006, new ArrayList<EPUBLocation>());
		locations.put(MessageId.EPUBTYPE_001, new ArrayList<EPUBLocation>());
		locations.put(MessageId.EPUBTYPE_002, new ArrayList<EPUBLocation>());
		locations.put(MessageId.OPF_001, new ArrayList<EPUBLocation>());
		locations.put(MessageId.OPF_002, new ArrayList<EPUBLocation>());
		locations.put(MessageId.OPF_002_W, new ArrayList<EPUBLocation>());
		
		locations.put(MessageId.LANG_001, new ArrayList<EPUBLocation>());
		locations.put(MessageId.LANG_002, new ArrayList<EPUBLocation>());
		locations.put(MessageId.TITLE_001, new ArrayList<EPUBLocation>());
		locations.put(MessageId.TITLE_002, new ArrayList<EPUBLocation>());
		locations.put(MessageId.LINK_001, new ArrayList<EPUBLocation>());
		locations.put(MessageId.STYLE_001, new ArrayList<EPUBLocation>());
		locations.put(MessageId.STYLE_002, new ArrayList<EPUBLocation>());
		locations.put(MessageId.STYLE_003, new ArrayList<EPUBLocation>());
		locations.put(MessageId.TABLE_001, new ArrayList<EPUBLocation>());
		locations.put(MessageId.TABLE_002, new ArrayList<EPUBLocation>());
		locations.put(MessageId.TABLE_003, new ArrayList<EPUBLocation>());
		locations.put(MessageId.IMG_001, new ArrayList<EPUBLocation>());
		locations.put(MessageId.IMG_002, new ArrayList<EPUBLocation>());
		locations.put(MessageId.IMG_003, new ArrayList<EPUBLocation>());
		locations.put(MessageId.IMG_003_W, new ArrayList<EPUBLocation>());
		locations.put(MessageId.MEDIA_001, new ArrayList<EPUBLocation>());
		locations.put(MessageId.MEDIA_002, new ArrayList<EPUBLocation>());
		locations.put(MessageId.MEDIA_001_W, new ArrayList<EPUBLocation>());
		locations.put(MessageId.MEDIA_002_W, new ArrayList<EPUBLocation>());
		locations.put(MessageId.JSARIA_001, new ArrayList<EPUBLocation>());
		locations.put(MessageId.JSARIA_002, new ArrayList<EPUBLocation>());
		locations.put(MessageId.JSARIA_003, new ArrayList<EPUBLocation>());
		locations.put(MessageId.NOTE_001, new ArrayList<EPUBLocation>());
		
		locations.put(MessageId.CSS_001, new ArrayList<EPUBLocation>());
		locations.put(MessageId.CSS_002, new ArrayList<EPUBLocation>());
		locations.put(MessageId.CSS_003, new ArrayList<EPUBLocation>());
		locations.put(MessageId.CSS_004, new ArrayList<EPUBLocation>());
		locations.put(MessageId.CSS_005, new ArrayList<EPUBLocation>());
		locations.put(MessageId.CSS_005_W, new ArrayList<EPUBLocation>());
		locations.put(MessageId.CSS_006, new ArrayList<EPUBLocation>());
		locations.put(MessageId.CSS_007, new ArrayList<EPUBLocation>());
		locations.put(MessageId.CSS_008, new ArrayList<EPUBLocation>());
		locations.put(MessageId.CSS_009, new ArrayList<EPUBLocation>());
		locations.put(MessageId.CSS_010, new ArrayList<EPUBLocation>());
		locations.put(MessageId.CSS_011, new ArrayList<EPUBLocation>());
		
		locations.put(MessageId.SVG_001, new ArrayList<EPUBLocation>());
		locations.put(MessageId.SVG_002, new ArrayList<EPUBLocation>());
		locations.put(MessageId.SMIL_001, new ArrayList<EPUBLocation>());
		locations.put(MessageId.SMIL_001_W, new ArrayList<EPUBLocation>());
		locations.put(MessageId.SMIL_002_1, new ArrayList<EPUBLocation>());
		locations.put(MessageId.SMIL_002_2, new ArrayList<EPUBLocation>());
		locations.put(MessageId.SMIL_002_3, new ArrayList<EPUBLocation>());
	}

	public static EPUBLocation getEPUBLocation(MessageId messageId, int index)
	{
		return locations.get(messageId).get(index);
	}
	
	public static ArrayList<EPUBLocation> getEPUBLocationList(MessageId messageId)
	{
		return locations.get(messageId);
	}

	public static void addLocation(MessageId messageId, EPUBLocation location)
	{
		locations.get(messageId).add(location);	
	}
	
	public static int getCountOfLocationList(MessageId messageId)
	{
		return locations.get(messageId).size();
	}
}
