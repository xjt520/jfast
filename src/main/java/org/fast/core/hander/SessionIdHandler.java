package org.fast.core.hander;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jfinal.handler.Handler;

/**
 * 对于没有cookie的时候会传递url会带上sessionId导致action跳入404
 * 来自 final-common
 * <url>/sign_in;jsessionid=7ba49c313a84295770fecbd01e86f116166sc5feg5yhzwis9zayzx492</url>
 */
public class SessionIdHandler extends Handler {

	@Override
	public void handle(String target, HttpServletRequest request,
			HttpServletResponse response, boolean[] isHandled) {
		// L.cm update by 2014-08-06 更精准的判断
		boolean isFromURL = request.isRequestedSessionIdFromURL();
		if (isFromURL) {
			target = target.substring(0, target.indexOf(';'));
		}
		next.handle(target, request, response, isHandled);
	}

}