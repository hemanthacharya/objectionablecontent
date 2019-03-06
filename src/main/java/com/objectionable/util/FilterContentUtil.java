package com.objectionable.util;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class FilterContentUtil {
	
	private Map<String, String> regexTypeMaps;

	public FilterContentUtil() {
		
		regexTypeMaps = new HashMap<String, String>();
		regexTypeMaps.put("Adult", "(.*sex.*|.*fuck.*|.*rape.*|.*drug.*|.*molest.*)");
		regexTypeMaps.put("Racist", "(.*nigger.*)");
		regexTypeMaps.put("TerrorOrWar", "(.*terror.*|.*explosion.*|.*suicide.*)");
		
	}
	
	public Map<String, String> getRegexTypeMaps() {
		return regexTypeMaps;
	}

}
