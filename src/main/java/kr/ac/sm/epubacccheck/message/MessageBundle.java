package kr.ac.sm.epubacccheck.message;

import java.util.Locale;
import java.util.ResourceBundle;

import kr.ac.sm.epubacccheck.util.UTF8Control;

public class MessageBundle
{
	private static final String BUNDLE_NAME = "MessageBundle"; //$NON-NLS-1$
	private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(BUNDLE_NAME, Locale.getDefault(), new UTF8Control());

	public static String getMessage(String key)
	{
		return RESOURCE_BUNDLE.getString(key);
	}
}

