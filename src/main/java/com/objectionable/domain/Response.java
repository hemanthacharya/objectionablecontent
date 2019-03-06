package com.objectionable.domain;

import java.util.List;

public class Response {
	
	private List<String> contentType;
	private boolean isOffensive;
	
	public List<String> getContentType() {
		return contentType;
	}
	public void setContentType(List<String> contentType) {
		this.contentType = contentType;
	}
	public boolean isOffensive() {
		return isOffensive;
	}
	public void setOffensive(boolean isOffensive) {
		this.isOffensive = isOffensive;
	}
}
