package index.model;

import condition.Condition;
import condition.ConditionModel;
import org.fast.core.kit.ValidateUtil;
import org.fast.core.kit.encoder.md5.MD5;

import java.util.ArrayList;
import java.util.List;

/**
 * 用户表
 * Created by xjt520 on 15/9/23.
 */
public class User extends ConditionModel<User> {
    public static final User me = new User();

    public User checkUser(String loginName, String password) {
        Condition condition = Condition.parseCondition("account,string,eq");
        condition.setValue(loginName);
        //Condition condition2 = Condition.parseCondition("password,string,eq");
        //condition2.setValue(password);
        List<Condition> conditions = new ArrayList<Condition>();
        conditions.add(condition);
        //conditions.add(condition2);
        List<User> users = find(conditions);
        if (ValidateUtil.isValid(users)){
            User user = users.get(0);
            if (user.getStr("password").equals(MD5.once(password))){
                return user;
            }
            return null;
        }
        return null;
    }
}
