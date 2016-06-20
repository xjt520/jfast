package org.fast.visitor.httpclient.imp;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jfinal.log.Log;
import org.apache.commons.httpclient.Cookie;
import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethodBase;
import org.apache.commons.httpclient.HttpState;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.MultiThreadedHttpConnectionManager;
import org.apache.commons.httpclient.UsernamePasswordCredentials;
import org.apache.commons.httpclient.auth.AuthPolicy;
import org.apache.commons.httpclient.auth.AuthScope;
import org.apache.commons.httpclient.cookie.CookiePolicy;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.fast.visitor.constant.R;
import org.fast.visitor.httpclient.HttpVisitor;

public class HttpVisitorImpl implements HttpVisitor {
	
    private static final Log logger = Log.getLog(HttpVisitorImpl.class);
    
    HttpClient client;
    private String responseCharset = R.Charset.UTF_8;
    private String requestCharset = R.Charset.ISO_8859_1;
    int timeout = 30000;
    HttpState httpState = new HttpState();
    boolean authEnabled = false;
    List<String> authPrefs = null;
    private String proxyHost;
    private int proxyPort;
    private UsernamePasswordCredentials proxyUserPassword;
    private Map<String, String> headerMap = new HashMap<String, String>();

    public HttpVisitorImpl(){
    	
    }
    
    public void setCommonheader(){
		headerMap.put("Accept", "text/html, application/xhtml+xml, */*");
		headerMap.put("Accept-Language", "zh-CN,en-US;q=0.5");
		headerMap.put("User-Agent", "Mozilla/5.0 (compatible; MSIE 9.0; Windows NT 6.1; Trident/5.0)");
		headerMap.put("Accept-Encoding", "gzip, deflate");
		headerMap.put("Connection", "Keep-Alive");
    }
    
    public void setProxy(String proxyHost, int proxyPort, String userName, String passwrod) {
        this.proxyHost = proxyHost;
        this.proxyPort = proxyPort;
        if (userName != null) {
            proxyUserPassword = new UsernamePasswordCredentials(userName, passwrod);
        }
    }

    /**
     * @param host
     * @param port
     * @param realm
     * @param schema
     * @param username
     * @param password
     */
    public void setBasicAuth(String host, int port, String realm, String schema, String username, String password) {
        httpState.setCredentials(new AuthScope("www.verisign.com", 443, realm, schema), new UsernamePasswordCredentials(username, password));
        authEnabled = true;
    }

    public void setBasicAuth(String host, int port, String username, String password) {
        setBasicAuth(host, port, username, password);
    }

    public void setAlternateAuth(String host, int port, String username, String password, List<String> schemaList) {
        httpState.setCredentials(new AuthScope("www.verisign.com", 443), new UsernamePasswordCredentials(username, password));
        authEnabled = true;

        client.getParams().setParameter(AuthPolicy.AUTH_SCHEME_PRIORITY, schemaList);
    }


    public String getRequestCharset() {
        return requestCharset;
    }

    public void setRequestCharset(String requestCharset) {
        this.requestCharset = requestCharset;
    }

    public String getResponseCharset() {
        return responseCharset;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

    public void init() {
        client = new HttpClient(new MultiThreadedHttpConnectionManager());
        client.getHttpConnectionManager().getParams().setConnectionTimeout(timeout);
        client.getParams().setCookiePolicy(CookiePolicy.RFC_2109);
        client.setState(httpState);
//        Protocol.registerProtocol("https", new Protocol("https", new MySSLSocketFactory(), 443));
        if (authPrefs != null) {
            client.getParams().setParameter(AuthPolicy.AUTH_SCHEME_PRIORITY, authPrefs);
        }
        if (proxyHost != null) {
            client.getHostConfiguration().setProxy(proxyHost, proxyPort);
            if (proxyUserPassword != null) {
                httpState.setProxyCredentials(new AuthScope(proxyHost, proxyPort), proxyUserPassword);
            }
        }
    }

    public String getUrl(String url, Map<String, ?> parameter) {
        try {
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
            GetMethod get = new GetMethod(sb.toString());
            addHeader(get, headerMap);
            return execute(get);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    private void appendParameter(StringBuffer sb, String key, Object value) throws UnsupportedEncodingException {
        sb.append("&");
        sb.append(URLEncoder.encode(key, requestCharset));
        sb.append("=");
        sb.append(URLEncoder.encode(value.toString(), requestCharset));
    }

    public String postUrl(String url, Map<String, ?> parameter) {
        PostMethod post = new PostMethod(url);
        if (parameter != null) {
            for (String key : parameter.keySet()) {
                Object value = parameter.get(key);
                if (value.getClass().isArray()) {
                    Object[] arrayValue = (Object[]) value;
                    for (Object o : arrayValue) {
                        post.addParameter(key, o.toString());
                    }
                } else {
                    post.setParameter(key, value.toString());
                }
            }
        }
        addHeader(post, headerMap);
        return execute(post);
    }
    
    public HttpMethodBase loadUrl(int method,String url,Map<String, ?> parameter){
    	HttpMethodBase httpMethod = null ;
    	switch (method) {
			case R.HttpMethod.GET:
				StringBuffer sb = new StringBuffer(url);
				try {
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
				} catch (UnsupportedEncodingException e) {
					logger.debug( "结果返回失败，原因：{"+e.getMessage()+"}");
		            throw new RuntimeException(e);
				}
				httpMethod = new GetMethod(sb.toString());
				break;
			case R.HttpMethod.POST:
				httpMethod = new PostMethod(url);
				if (parameter != null) {
		            for (String key : parameter.keySet()) {
		                Object value = parameter.get(key);
		                if (value.getClass().isArray()) {
		                    Object[] arrayValue = (Object[]) value;
		                    for (Object o : arrayValue) {
		                    	((PostMethod) httpMethod).addParameter(key, o.toString());
		                    }
		                } else {
		                	((PostMethod) httpMethod).setParameter(key, value.toString());
		                }
		            }
		        }
				break;
			default:
				httpMethod = new GetMethod(url);
				break;
		}
    	addHeader(httpMethod, headerMap);
    	
    	if (client == null) {
            init();
        }
    	httpMethod.setDoAuthentication(authEnabled);
        try {
			int iGetResultCode = client.executeMethod(httpMethod);
			 if (iGetResultCode == HttpStatus.SC_OK) {
	                logger.debug( "结果成功返回。");
	                return httpMethod;
	            }
	            logger.error("结果返回失败，原因：{"+httpMethod.getStatusLine().toString()+"}");
	            throw new RuntimeException(httpMethod.getStatusLine().toString());
		} catch (Exception e) {
			logger.debug( "结果返回失败，原因：{"+e.getMessage()+"}");
            throw new RuntimeException(e);
		}finally{
//			调用此方法 需要手动关闭Connection
//			httpMethod.releaseConnection();
		}
    }

    String execute(HttpMethodBase method) {
        try {
            if (client == null) {
                init();
            }
            logger.debug( "正在访问地址:{"+method.getURI().toString()+"}");
            if (!requestCharset.equals("ISO-8859-1")) {
                method.addRequestHeader("Content-Type", "text/html; charset=" + requestCharset);
            }
            method.setDoAuthentication(authEnabled);
            int iGetResultCode = client.executeMethod(method);
            if (iGetResultCode == HttpStatus.SC_OK) {
                logger.debug( "结果成功返回。");
                String strGetResponseBody = new String(method.getResponseBody(), responseCharset);
                return strGetResponseBody;
            }
            logger.error("结果返回失败，原因：{"+method.getStatusLine().toString()+"}");
            throw new RuntimeException(method.getStatusLine().toString());
        } catch (Exception e) {
        	logger.debug( "结果返回失败，原因：{"+e.getMessage()+"}");
            throw new RuntimeException(e);
        } finally {
            method.releaseConnection();
        }
    }
    
    public void setResponseCharset(String charset) {
        this.responseCharset = charset;
    }

    public HttpClient getHttpClient() {
        return client;
    }

    public void addCookie(Cookie cookie) {
        httpState.addCookie(cookie);
    }

    public Cookie[] getCookies() {
        return httpState.getCookies();
    }

    public String postSoap(String url, String soapAction, String xmlEntiry) {
        PostMethod post = new PostMethod(url);
        try {
            RequestEntity entity = new StringRequestEntity(xmlEntiry, null, null);
            post.setRequestEntity(entity);
            if (soapAction != null) {
                post.setRequestHeader("SOAPAction", soapAction);
            }
            addHeader(post, headerMap);
            return execute(post);
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            post.releaseConnection();
        }
    }

    public String postSoap(String url, String xmlEntiry) {
        return postSoap(url, null, xmlEntiry);
    }

    public String postXml(String url, String xmlEntiry) {
        PostMethod post = new PostMethod(url);
        try {
            post.setDoAuthentication(authEnabled);
            RequestEntity entity = new StringRequestEntity(xmlEntiry, null, null);
            post.setRequestEntity(entity);
            addHeader(post, headerMap);
            return execute(post);
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            post.releaseConnection();
        }
    }

    public void setHeaderMap(Map<String, String> headerMap) {
        this.headerMap.putAll(headerMap);
    }

    public HttpVisitor setHeader(String key, String value) {
        headerMap.put(key, value);
        return this;
    }

    private void addHeader(HttpMethodBase method, Map<String, String> header) {
        if (header != null) {
            for (String key : header.keySet()) {
                Header h = new Header(key, header.get(key));
                method.addRequestHeader(h);
            }
        }
    }

    public void addCookie(Cookie[] cookies) {
        httpState.addCookies(cookies);
    }

    public HttpState getHttpState() {
        return client.getState();
    }
}
