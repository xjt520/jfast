package condition;

import com.google.common.collect.Maps;
import com.jfinal.core.Controller;
import com.jfinal.kit.StrKit;
import com.jfinal.plugin.activerecord.Page;
import org.fast.core.constant.R;

import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * ConditionController
 */
public class ConditionController<T extends ConditionModel> extends Controller {

	private Class<T> clazz;

	private Integer page = new Integer(1);//页码 PageNo
	private Integer rows = new Integer(20);//页数 PageSize

	public Integer getPageNo() {
		return getParaToInt("page",page);
	}

	public Integer getPageSize() {
		return getParaToInt("rows",rows);
	}

	public void renderJsonPage(Page<T> page) {
		super.renderJson(new JsonPage<T>(page));
	}

    /**
     * init clazz
     */
	public ConditionController() {
		try {
			ParameterizedType type = (ParameterizedType) this.getClass().getGenericSuperclass();
			clazz = (Class<T>) type.getActualTypeArguments()[0];
		} catch (Exception e) {
            throw new RuntimeException("ConditionController model init exception !!!");
		}
	}

	/**
	 * query model
	 */
	protected ConditionModel getConditionModel(){
		return getModel(clazz);
	}

	/**
	 * bindConditions for request
	 */
	protected List<Condition> bindConditions() {
		List<Condition> conditions = new ArrayList<Condition>();
		Map<String, String[]> map = getRequest().getParameterMap();
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

	/*************************common*********************************/

	/**
	 * 跳转主页面
	 */
	public void index() {
		renderJsp("index.jsp");
	}

	/**
	 * 列表 dataGrid POST
	 */
	public void list() {
		renderJsonPage(getConditionModel().paginate(getPageNo(), getPageSize(), bindConditions()));
	}

	/**
	 * 跳转新增页面 GET
	 */
	public void add() {
		setAttr("lastTimer", getPara("timer"));
		renderJsp("add.jsp");
	}

	/**
	 * 跳转修改页面 GET
	 */
	public void update() {
		setAttr("lastTimer", getPara("timer"));
		setAttr(StrKit.firstCharToLowerCase(clazz.getSimpleName()), getConditionModel().findById(getPara("id")));
		renderJsp("update.jsp");
	}

	/**
	 * 查看详情页面 GET
	 */
	public void view() {
		setAttr("lastTimer", getPara("timer"));
		setAttr(StrKit.firstCharToLowerCase(clazz.getSimpleName()), getConditionModel().findById(getPara("id")));
		renderJsp("view.jsp");
	}

	/**
	 * 删除 POST
	 */
	public void delete() {
		Condition condition = Condition.parseCondition("id,integer,in");
		condition.setValue(getPara("ids"));
		boolean flag = getConditionModel().delete(condition);
		renderJson(flag ? jsonMap(true, R.Msg.DELETE_SUCCESS) : jsonMap(false, R.Msg.DELETE_FAILURE));
	}

	/**
	 * 是否有id值,用于判断是插入或者更新操作
	 * @return
	 */
	protected boolean hasIdValue(ConditionModel conditionModel){
		return null != conditionModel.get("id");
	}

	/**
	 * 查询重复
	 * userName,string,eq
	 */
	public void checkExist(){
		renderText(getConditionModel().count(bindConditions()) > 0 ? "true" : "false" );
	}

	/**
	 * 通用返回JsonMap
	 */
	public Map<String, Object> jsonMap(boolean flag,String msg){
		Map<String, Object> resultMap = Maps.newHashMap();//可以继续添加属性
		resultMap.put(R.Key_IsSuccess, flag+"");
		resultMap.put(R.Key_Msg, msg);
		return resultMap;
	}
}
