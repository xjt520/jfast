package org.fast.core.interceptor;

import org.fast.core.constant.R;

import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;
import com.jfinal.core.Controller;

/**
 * 全局拦截器 给jsp页面赋值唯一的timer
 */
public class RenderJspInterceptor  implements Interceptor  {

	@Override
	public void intercept(Invocation me) {
		Controller controller = me.getController();
		controller.setAttr(R.BaseController.timer, System.currentTimeMillis());
		me.invoke();
	}
}
