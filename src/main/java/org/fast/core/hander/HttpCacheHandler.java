package org.fast.core.hander;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jfinal.handler.Handler;
/**
 * 统一HttpCacheHandler处理
 */
public class HttpCacheHandler extends Handler {
	
	// 默认MAX_AGE：一个月
	private static final long MAX_AGE =  1L;// ConfigUtil.getToLong("max_age");

	@Override
	public void handle(String target, HttpServletRequest request,
	        HttpServletResponse response, boolean[] isHandled) {
		
	    // 最后修改时间
	    long ims = request.getDateHeader("If-Modified-Since");
	    long now = System.currentTimeMillis();
	    // 进一步ETag文件MD5比较"If-None-Match" eq "ETag" 
	     String token = request.getHeader("If-None-Match");
	    // 如果header头没有过期
	    if(ims + MAX_AGE > now){
	        isHandled[0] = true;
	        response.setStatus(HttpServletResponse.SC_NOT_MODIFIED);
	        return;
	    }
		next.handle(target, request, response, isHandled);
	    // 去除对页面的缓存，造成后台进不去
	    // 验证是否静态文件 静态文件缓存30天
	    if(target.indexOf(".") > 0 || target.endsWith("/combo")){
	        // 新生成http cache头信息
//	        response.setHeader("ETag", StrKit.getTonken());
	        response.setHeader("Cache-Control", "max-age=" + MAX_AGE);
	        response.addDateHeader("Expires", now + MAX_AGE);
	        response.addDateHeader("Last-Modified", now);
	    }
	}
}
