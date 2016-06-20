package run;

import com.alibaba.druid.filter.stat.StatFilter;
import com.alibaba.druid.wall.WallFilter;
import com.jfinal.config.Constants;
import com.jfinal.config.Handlers;
import com.jfinal.config.Interceptors;
import com.jfinal.config.JFinalConfig;
import com.jfinal.config.Plugins;
import com.jfinal.config.Routes;
import com.jfinal.core.JFinal;
import com.jfinal.ext.handler.ContextPathHandler;
import com.jfinal.kit.PropKit;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.druid.DruidPlugin;
import com.jfinal.render.ViewType;
import index.model.*;
import org.fast.core.constant.R;
import org.fast.core.hander.URLOptimizeHandler;
import org.fast.core.hander.ViewSkipModeHandler;
import org.fast.core.interceptor.LoginInterceptor;
import org.fast.core.interceptor.RenderJspInterceptor;
import org.fast.core.plugin.route.AutoBindRoutes;

/**
 * API引导式配置
 */
public class RunConfig extends JFinalConfig {
	
	/**
	 * 配置常量
	 */
	public void configConstant(Constants me) {
		PropKit.use("jdbc.properties");
		me.setDevMode(PropKit.getBoolean("devMode", false));
		me.setBaseViewPath("/WEB-INF/pages");
		me.setViewType(ViewType.JSP);
	}
	
	/**
	 * 配置路由
	 */
	public void configRoute(Routes me) {
		me.add(new AutoBindRoutes());
	}
	
	/**
	 * 配置插件
	 */
	public void configPlugin(Plugins me) {
		DruidPlugin main = new DruidPlugin(PropKit.get("jdbcUrl"), PropKit.get("user"), PropKit.get("password").trim());
		main.addFilter(new StatFilter());
		WallFilter wall = new WallFilter();
		wall.setDbType(R.DbType.mysql);
		main.addFilter(wall);
		me.add(main);

		ActiveRecordPlugin arp = new ActiveRecordPlugin(main);
		arp.setShowSql(true);
		me.add(arp);

//		arp.addMapping("user", User.class);
		arp.addMapping("category", Category.class);
		arp.addMapping("posts", Posts.class);
		//me.add(new EhCachePlugin());
	}
	
	/**
	 * 配置全局拦截器
	 */
	public void configInterceptor(Interceptors me) {
		me.addGlobalActionInterceptor(new RenderJspInterceptor());
//		me.addGlobalActionInterceptor(new LoginInterceptor());
	}
	
	/**
	 * 配置处理器
	 */
	public void configHandler(Handlers me) {
		me.add(new ContextPathHandler("contextPath"));
		me.add(new ViewSkipModeHandler(R.ViewSkipMode.type_tab));
		me.add(new URLOptimizeHandler());
		
	}

	public static void main(String[] args) {
		JFinal.start("WebRoot", 8080, "/", 5);
	}

	@Override
	public void afterJFinalStart() {
		//Down.fetch();
	}
}
