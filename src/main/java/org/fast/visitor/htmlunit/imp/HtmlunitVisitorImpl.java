package org.fast.visitor.htmlunit.imp;

import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.CollectingAlertHandler;
import com.gargoylesoftware.htmlunit.HttpMethod;
import com.gargoylesoftware.htmlunit.NicelyResynchronizingAjaxController;
import com.gargoylesoftware.htmlunit.Page;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.WebRequest;
import com.gargoylesoftware.htmlunit.util.NameValuePair;
import com.google.common.collect.Lists;
import org.fast.visitor.constant.R;
import org.fast.visitor.htmlunit.HttpVisitor;
import org.fast.visitor.htmlunit.MyHttpWebConnection;

public class HtmlunitVisitorImpl implements HttpVisitor {

	private WebClient webClient = null;
	private String responseCharset = R.Charset.UTF_8;
    private String requestCharset = R.Charset.ISO_8859_1;
    private int timeout = 30000;//超时时间
    private int retryTimes = 3;//重试次数
    private int sleepMills = 1000;//休眠时间
    private boolean jsEnabled = true;
    private Map<String, String> headerMap = new HashMap<String, String>();
	
	@Override
	public void init() {
		webClient = new WebClient(BrowserVersion.INTERNET_EXPLORER_11);
		webClient.setWebConnection(new MyHttpWebConnection(webClient));
		webClient.getCache().clear();
		webClient.getCookieManager().clearCookies();
		webClient.getOptions().setJavaScriptEnabled(jsEnabled);
		webClient.setJavaScriptTimeout(60*1000);
		webClient.getOptions().setCssEnabled(false);
		webClient.getOptions().setActiveXNative(false);
		webClient.getOptions().setPopupBlockerEnabled(false);
		webClient.getOptions().setRedirectEnabled(true);
		webClient.getOptions().setTimeout(timeout);
		webClient.getOptions().setDoNotTrackEnabled(true);
		webClient.getCookieManager().setCookiesEnabled(true);
		webClient.getOptions().setThrowExceptionOnFailingStatusCode(true);
		webClient.getOptions().setThrowExceptionOnScriptError(false);
		webClient.getOptions().setUseInsecureSSL(true);
		webClient.setAjaxController(new NicelyResynchronizingAjaxController());
		webClient.setAlertHandler(new CollectingAlertHandler());
	}
	
    public void setCommonheader(){
		headerMap.put("Accept", "text/html, application/xhtml+xml, */*");
		headerMap.put("Accept-Language", "zh-CN,en-US;q=0.5");
		headerMap.put("User-Agent", "Mozilla/5.0 (compatible; MSIE 9.0; Windows NT 6.1; Trident/5.0)");
		headerMap.put("Accept-Encoding", "gzip, deflate");
//		headerMap.put("Host", "");
		headerMap.put("Connection", "Keep-Alive");
    }

	@Override
	public void setProxy(String proxyHost, int proxyPort, String userName,String passwrod) {
		getWebClient().getCredentialsProvider().setCredentials(new AuthScope(proxyHost,proxyPort),new UsernamePasswordCredentials(userName,passwrod));
	}

	@Override
	public void setBasicAuth(String host, int port, String realm,String schema, String username, String password) {

	}

	@Override
	public void setBasicAuth(String host, int port, String username,String password) {

	}

	@Override
	public void setAlternateAuth(String host, int port, String username,String password, List<String> schemaList) {

	}

	@Override
	public void setResponseCharset(String charset) {
		this.responseCharset = charset;
	}

	@Override
	public void setRequestCharset(String requestCharset) {
		this.requestCharset = requestCharset;
	}

	public String getResponseCharset() {
		return responseCharset;
	}

	public String getRequestCharset() {
		return requestCharset;
	}

	@Override
	public void setTimeout(int timeout) {
		webClient.getOptions().setTimeout(timeout);
		this.timeout = timeout;
	}
	
	public boolean isJsEnabled() {
		return jsEnabled;
	}

	public void setJsEnabled(boolean jsEnabled) {
		getWebClient().getOptions().setJavaScriptEnabled(jsEnabled);
		this.jsEnabled = jsEnabled;
	}

	@Override
	public Page getUrl(String url, Map<String, ?> parameter) throws Exception {
		//添加参数
		StringBuffer sb = new StringBuffer(url);
        if (parameter != null) {
         	sb.append(url.indexOf('?') < 0 ? "?" : "");
             for (String key : parameter.keySet()) {
                 Object value = parameter.get(key);
                 if (value.getClass().isArray()) {
                     Object[] arrayValue = (Object[]) value;
                     for (Object o : arrayValue) {
                         appendParameter(sb, key, o);
                     }
                 } else {
                     appendParameter(sb, key, value);
                 }
             }
        }
        
		Page page = null;
		int i = 0;
		while(page == null && i < retryTimes){
			try{
				if(this.getWebClient() == null){
					init();
				}
				//添加header
				addHeader();
				page = this.getWebClient().getPage(sb.toString());
			}catch(Exception e){
				if(i == retryTimes-1){
					throw new Exception(e);
				}
				Thread.sleep(sleepMills);
			}finally{
				i++;
			}
		}
		return page;
	}
	
	private void addHeader() {
		Map<String, String> header = getHeaderMap();
		if (header != null && !header.isEmpty()) {
            for (String key : header.keySet()) {
            	this.getWebClient().addRequestHeader(key, header.get(key));
            }
        }
	}

	private void appendParameter(StringBuffer sb, String key, Object value) throws Exception {
        sb.append("&");
        sb.append(URLEncoder.encode(key, requestCharset));
        sb.append("=");
        sb.append(URLEncoder.encode(value.toString(), requestCharset));
    }

	@Override
	public Page postUrl(String url, Map<String, ?> parameter) throws Exception {
		Page page = null;
		WebRequest request = null;
		try {
			request = new WebRequest(new URL(url),HttpMethod.POST);
		} catch (MalformedURLException e) {
			throw new Exception(e);
		}
		Map<String, String> header = getHeaderMap();
		if (header != null && !header.isEmpty()) {
			request.getAdditionalHeaders().putAll(header);
        }
		if(parameter != null && !parameter.isEmpty()){
			List<NameValuePair> paramList = Lists.newArrayList();
			for (String key : parameter.keySet()) {
				paramList.add(new NameValuePair(key, header.get(key)));
	        }
			request.setRequestParameters(paramList);
		}
		int i = 0;
		while(page == null && i < retryTimes){
			try{
				if(this.getWebClient() == null){
					init();
				}
				page = this.getWebClient().getPage(request);
			}catch(Exception e){
				if(i == retryTimes-1){
					throw new Exception(e);
				}
				Thread.sleep(sleepMills);
			}finally{
				i++;
			}
		}
		return page;
	}
	
	public int getRetryTimes() {
		return retryTimes;
	}

	public void setRetryTimes(int retryTimes) {
		this.retryTimes = retryTimes;
	}

	public int getSleepMills() {
		return sleepMills;
	}

	public void setSleepMills(int sleepMills) {
		this.sleepMills = sleepMills;
	}

	public int getTimeout() {
		return timeout;
	}

	public Map<String, String> getHeaderMap() {
		return headerMap;
	}

	@Override
	public WebClient getWebClient() {
		if(webClient == null){
			init();
		}
		return webClient;
	}

	@Override
	public void setHeaderMap(Map<String, String> header) {
		this.headerMap.putAll(headerMap);
	}

	@Override
	public HttpVisitor setHeader(String key, String value) {
		headerMap.put(key, value);
        return this;
	}

}
