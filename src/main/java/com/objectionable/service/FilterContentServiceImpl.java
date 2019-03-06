package com.objectionable.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Pattern;

import org.springframework.stereotype.Service;

import com.objectionable.api.FilterContentServiceApi;
import com.objectionable.domain.Content;
import com.objectionable.domain.Response;
import com.objectionable.util.FilterContentUtil;

@Service
public class FilterContentServiceImpl implements FilterContentServiceApi {

	public Response filterContent(Content content) {
		
		Response response = new Response();
		FilterContentUtil filterUtil = new FilterContentUtil();
		String inp = content.getText();
		
		Map<String, String> regexs = filterUtil.getRegexTypeMaps();
		response.setContentType(new ArrayList<String>());
		
		for (Entry<String, String> entry : regexs.entrySet()) {
			
			if (Pattern.matches(entry.getValue(), inp)) {
				
				response.getContentType().add(entry.getKey());
				response.setOffensive(true);
			}
			
		}
		
		
		return response;
	}

}
