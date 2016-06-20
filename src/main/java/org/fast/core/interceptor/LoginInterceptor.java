package org.fast.core.interceptor;

import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;
import com.jfinal.core.Controller;
import index.model.User;
import org.fast.core.constant.R;

/**
 * 全局拦截器 登陆拦截器
 */
public class LoginInterceptor implements Interceptor  {

	@Override
	public void intercept(Invocation me) {
		Controller controller = me.getController();
		Object obj = controller.getSessionAttr("LOGIN_USER");
		if (obj == null){
			controller.redirect("/login");
		}else {
			me.invoke();
			//User user = (User) obj;
			//if (user.getStr("type").equals("0")){//管理员
			//	me.invoke();
			//}else {
			//	controller.redirect("/mfq");
			//}

		}
	}
}
