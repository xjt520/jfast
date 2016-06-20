package condition;

import com.jfinal.kit.StrKit;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by xjt520 on 16/1/4.
 */
public class ConditionKit {

    public static List<Condition> bind(HttpServletRequest request){
        List<Condition> conditions = new ArrayList<Condition>();
        Map<String, String[]> map = request.getParameterMap();
        for (String key : map.keySet()) {
            if (StrKit.notBlank(key)) {
                String value = map.get(key)[0];
                if(StrKit.isBlank(value)){
                    continue;
                }
                if ("page".equalsIgnoreCase(key)) {
                    continue;
                }
                if ("rows".equalsIgnoreCase(key)) {
                    continue;
                }
                if ("order".equalsIgnoreCase(key)) {
                    continue;
                }
                if ("sort".equalsIgnoreCase(key)) {
                    continue;
                }
                Condition condition = Condition.parseCondition(key);
                condition.setValue(value);
                conditions.add(condition);
            }
        }
        return conditions;
    }

}
