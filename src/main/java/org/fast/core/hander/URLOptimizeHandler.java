package org.fast.core.hander;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jfinal.handler.Handler;

/**
 * 对连接地址的优化，避免报错，JFinal url //的将会报错
 * 来自 final-common
 */
public class URLOptimizeHandler extends Handler {

	@Override
	public void handle(String target, HttpServletRequest request,
			HttpServletResponse response, boolean[] isHandled) {

//		对于 ///x//x/x.html 路径的优化
		target = target.replaceAll("/{2,}", "/");

		next.handle(target, request, response, isHandled);
	}

}