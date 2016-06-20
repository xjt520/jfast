package index.controller;

import condition.ConditionController;
import index.model.Posts;
import org.fast.core.constant.R;
import org.fast.core.plugin.route.RequestMapping;

import java.util.Date;

/**
 * Created by xjt520 on 15/9/23.
 */
@RequestMapping(controllerKey = "/posts",viewPath="/posts")
public class PostsController extends ConditionController<Posts> {

    public void save() {
        Posts posts = getModel(Posts.class);
        posts.set("postDate",new Date());
        posts.set("postModified",new Date());
        boolean result = posts.save();
        renderJson(result ? jsonMap(true, R.Msg.SAVE_SUCCESS) : jsonMap(false, R.Msg.SAVE_FAILURE));
    }

    public void modify(){
        Posts posts = getModel(Posts.class);
        posts.set("postModified",new Date());
        boolean result = posts.update();
        renderJson(result ? jsonMap(true, R.Msg.SAVE_SUCCESS) : jsonMap(false, R.Msg.SAVE_FAILURE));
    }
}
