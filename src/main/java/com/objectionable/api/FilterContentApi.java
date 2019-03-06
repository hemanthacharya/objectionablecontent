package com.objectionable.api;

import com.objectionable.domain.Content;
import com.objectionable.domain.Response;

public interface FilterContentApi {
	
	public Response check(Content content);

}
