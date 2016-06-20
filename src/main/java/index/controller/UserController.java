package index.controller;

import condition.ConditionController;
import index.model.User;
import org.fast.core.plugin.route.RequestMapping;

/**
 * Created by xjt520 on 15/9/23.
 */
@RequestMapping(controllerKey = "/user",viewPath="/user")
public class UserController extends ConditionController<User> {

}
