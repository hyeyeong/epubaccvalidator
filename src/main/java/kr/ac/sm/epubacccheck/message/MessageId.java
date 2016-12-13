package kr.ac.sm.epubacccheck.message;

public enum MessageId
{
	// MESSAGE IDs FOR OPF / EPUBTYPE / NAV
	NAV_001			("NAV-001"),
	NAV_001_W		("NAV-001-W"), // ID for warning
	NAV_002_1		("NAV-002-1"),
	NAV_002_2		("NAV-002-2"),
	NAV_003			("NAV-003"),
	NAV_004			("NAV-004"),
	NAV_005			("NAV-005"),
	NAV_006			("NAV-006"),
	EPUBTYPE_001	("EPUBTYPE-001"),
	EPUBTYPE_002	("EPUBTYPE-002"),
	OPF_001			("OPF-001"),
	OPF_002			("OPF-002"),
	OPF_002_W		("OPF-002-W"), // ID for warning
	
	// MESSAGE IDs FOR XHTML
	LANG_001		("LANG-001"),
	LANG_002		("LANG-002"),
	TITLE_001		("TITLE-001"),
	TITLE_002		("TITLE-002"),
	LINK_001		("LINK-001"),
	STYLE_001		("STYLE-001"),
	STYLE_002		("STYLE-002"),
	STYLE_003		("STYLE-003"),
	TABLE_001		("TABLE-001"),
	TABLE_002		("TABLE-002"),
	TABLE_003		("TABLE-003"),
	IMG_001			("IMG-001"),
	IMG_002			("IMG-002"),
	IMG_003			("IMG-003"),
	IMG_003_W		("IMG-003-W"),
	MEDIA_001		("MEDIA-001"),
	MEDIA_001_W		("MEDIA-001"),
	MEDIA_002		("MEDIA-002"),
	MEDIA_002_W		("MEDIA-002"),
	JSARIA_001		("JSARIA-001"),
	JSARIA_002		("JSARIA-002"),
	JSARIA_003		("JSARIA-003"),
	NOTE_001		("NOTE-001"),
	
	// MESSAGE IDs FOR CSS
	CSS_001			("CSS-001"),
	CSS_002			("CSS-002"),
	CSS_003			("CSS-003"),
	CSS_004			("CSS-004"),
	CSS_005			("CSS-005"),
	CSS_005_W		("CSS-005-W"), // ID for warning
	CSS_006			("CSS-006"),
	CSS_007			("CSS-007"),
	CSS_008			("CSS-008"),
	CSS_009			("CSS-009"),
	CSS_010			("CSS-010"),
	CSS_011			("CSS-011"),
	
	// MESSAGE IDs FOR SVG, SMIL
	SVG_001			("SVG-001"),
	SVG_002			("SVG-002"),
	SMIL_001		("SMIL-001"),
	SMIL_001_W		("SMIL-001-W"),
	SMIL_002_1		("SMIL-002-1"),
	SMIL_002_2		("SMIL-002-2"),
	SMIL_002_3		("SMIL-002-3");

	private final String messageId;
	
	MessageId(String feature)
	{
		this.messageId = feature;
	}
	
	public String toString()
	{
		return messageId;
	}
}
