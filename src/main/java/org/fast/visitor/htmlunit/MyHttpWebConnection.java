package org.fast.visitor.htmlunit;

import org.apache.http.impl.client.HttpClientBuilder;

import com.gargoylesoftware.htmlunit.HttpWebConnection;
import com.gargoylesoftware.htmlunit.WebClient;

public class MyHttpWebConnection extends HttpWebConnection{

	public MyHttpWebConnection(WebClient webClient) {
		super(webClient);
	}
	
	protected synchronized HttpClientBuilder getHttpClientBuilder() {
		HttpClientBuilder httpClientBuilder = super.getHttpClientBuilder();
		httpClientBuilder.setMaxConnTotal(1000);
		httpClientBuilder.setMaxConnPerRoute(100);
        return httpClientBuilder;
    }
}
