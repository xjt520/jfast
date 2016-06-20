package org.fast.core.hander;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jfinal.handler.Handler;

/**
 * 
 * 静态文件目录设置
 * 来自 final-common
 * <br>解决伪静态后的，静态文件中的html文件不能被访问
 * <pre>
 * me.add(new StaticHandler("/js", "/css"));
 * </pre>
 */
public class StaticHandler extends Handler {

	// 需要排除的目录...
	public final String[] dirs;

	public StaticHandler(String... dirs) {
		this.dirs = dirs;
	}

	@Override
	public void handle(String target, HttpServletRequest request,
			HttpServletResponse response, boolean[] isHandled) {

		// 判定是否要排除
		boolean needExclude = false;
		for (String dir : dirs) {
			if (target.startsWith(dir)) {
				needExclude = true;
				break;
			}
		}
		if (needExclude) {
			return;
		}
		next.handle(target, request, response, isHandled);
	}

}
