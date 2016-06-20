package index.controller;

import condition.ConditionController;
import index.model.Category;
import org.fast.core.plugin.route.RequestMapping;

import java.util.List;

/**
 * Created by xjt520 on 15/9/23.
 */
@RequestMapping(controllerKey = "/category",viewPath="/category")
public class CategoryController extends ConditionController<Category> {

    public void select(){
        List<Category> list = Category.me.findAll();
        renderJson(list);
    }

}
