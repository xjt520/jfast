package index.controller;

import condition.ConditionController;
import index.model.Index;
import org.fast.core.plugin.route.RequestMapping;

/**
 * Created by xjt520 on 15/9/23.
 */
@RequestMapping(controllerKey = "/")
public class IndexController extends ConditionController<Index> {

    public void index(){
        renderJsp("main.jsp");
    }

    public void main(){
        renderJsp("main.jsp");
    }

}
