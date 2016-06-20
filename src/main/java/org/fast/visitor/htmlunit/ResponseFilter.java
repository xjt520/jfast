package org.fast.visitor.htmlunit;

import java.io.IOException;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.WebRequest;
import com.gargoylesoftware.htmlunit.WebResponse;
import com.gargoylesoftware.htmlunit.util.FalsifyingWebConnection;

public class ResponseFilter extends  FalsifyingWebConnection{
	
	private String[] excludeFileNames;
	
	public ResponseFilter(WebClient webClient) throws IllegalArgumentException {
		super(webClient);
	}
	
	public ResponseFilter(WebClient webClient , String[] excludeFileNames) throws IllegalArgumentException {
		super(webClient);
		this.excludeFileNames = excludeFileNames;
	}
	
	public String[] getExcludeFileNames() {
		return excludeFileNames;
	}

	public void setExcludeFileNames(String[] excludeFileNames) {
		this.excludeFileNames = excludeFileNames;
	}
	
	public void resetFilter(){
		this.excludeFileNames = null;
	}
	@Override
	public WebResponse getResponse(WebRequest request) throws IOException {
		String url = request.getUrl().toString();
		if(excludeFileNames != null){
			for(String excludeFileName: excludeFileNames){
				if(url.endsWith(excludeFileName)){
					return createWebResponse(request, "", "*/*");
				}
			}
		}
		return super.getResponse(request);
	}

}
