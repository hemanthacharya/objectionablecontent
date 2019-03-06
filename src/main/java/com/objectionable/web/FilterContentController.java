package com.objectionable.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.objectionable.api.FilterContentApi;
import com.objectionable.api.FilterContentServiceApi;
import com.objectionable.domain.Content;
import com.objectionable.domain.Response;

@RestController
@RequestMapping("/content")
public class FilterContentController implements FilterContentApi {
	
	 
	 @Autowired
	 FilterContentServiceApi filterContentService;

	 @RequestMapping(value = "/check", method = RequestMethod.POST)
	 public Response check(@RequestBody Content content) {
		 
		 return filterContentService.filterContent(content);
	  
	 }	 
	 
}
