package org.fast.core.kit;

import javax.servlet.http.HttpServletRequest;

import com.jfinal.kit.StrKit;

public class ServletKit {
	
    public static String getIp(HttpServletRequest request) {
        String remoteAddr = request.getHeader("X-Real-IP");
        if (remoteAddr == null) {
            remoteAddr = request.getHeader("X-Forwarded-For");
        }
        if (remoteAddr == null) {
            remoteAddr = request.getRemoteAddr();
        }
        return remoteAddr;
    }

    public static String getUrl(HttpServletRequest request) {
        String url = request.getRequestURL().toString();
        String parmas = request.getQueryString();
        if (StrKit.notBlank(parmas)) {
            url = url + "?" + parmas;
        }
        return url;
    }
}
