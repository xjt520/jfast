package org.fast.core.hander;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.fast.core.constant.R;

import com.jfinal.handler.Handler;
import com.jfinal.kit.StrKit;
/**
 * 增、改、查
 * 设置跳转页面的时候是弹出框 还是 新标签页
 */
public class ViewSkipModeHandler extends Handler {
	
	private String viewSkipMode;
	
	public ViewSkipModeHandler() {
		viewSkipMode = R.ViewSkipMode.type_tab;
	}
	
	public ViewSkipModeHandler(String viewSkipMode) {
		if (StrKit.isBlank(viewSkipMode))
			throw new IllegalArgumentException("viewSkipMode can not be blank.");
		this.viewSkipMode = viewSkipMode;
	}
	
	public void handle(String target, HttpServletRequest request, HttpServletResponse response, boolean[] isHandled) {
		request.setAttribute(R.ViewSkipMode.viewSkipMode, viewSkipMode);
		next.handle(target, request, response, isHandled);
	}
}
