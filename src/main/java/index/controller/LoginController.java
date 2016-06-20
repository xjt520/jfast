package index.controller;

import com.jfinal.aop.Clear;
import condition.Condition;
import condition.ConditionController;
import index.model.User;
import org.fast.core.interceptor.LoginInterceptor;
import org.fast.core.kit.encoder.md5.MD5;
import org.fast.core.plugin.route.RequestMapping;

import java.util.Date;

/**
 * Created by xjt520 on 15/9/23.
 */
@Clear
@RequestMapping(controllerKey = "/login",viewPath="/")
public class LoginController extends ConditionController<User> {

    public void index(){
        renderJsp("login.jsp");
    }

    public void doLogin(){
        String loginName = getPara("loginName");
        String password = getPara("password");
        User user = User.me.checkUser(loginName,password);
        if (user == null){
            setAttr("login_error","用户名密码错误");
            keepPara();
            renderJsp("login.jsp");
        }else {
            setSessionAttr("LOGIN_USER",user);
            redirect("/");
        }
    }

    public void doRegist(){
        User user = (User) getConditionModel();

        Condition condition = Condition.parseCondition("loginName,string,eq");
        condition.setValue(user.getStr("loginName"));
        User user1 = User.me.findFirst(condition);
        if (user1 != null){
            renderJson("{\"code\":\"201\"}");//用户名存在
        }else {
            user.set("createTime",new Date());
            user.set("type","1");
            user.set("locked","0");
            user.set("password", MD5.once(user.getStr("password")));
            boolean flag = user.save();
            if(flag){
                renderJson("{\"code\":\"200\"}");
            }else {
                renderJson("{\"code\":\"202\"}");//注册失败
            }
        }
    }

}
