package condition;

import com.jfinal.plugin.activerecord.*;

import java.util.List;

/**
 * ConditionModel
 * @param <M>
 */
public class ConditionModel<M extends ConditionModel> extends Model<M> {

	/**
	 * page query
	 */
	public Page<M> paginate(int pageNumber, int pageSize, Condition condition) {
		Query query = QueryBuilder.build(condition,getTableName());
		if (null == query)
			return paginate(pageNumber,pageSize,"select * ","from "+getTableName());
		else
			return paginate(pageNumber,pageSize,"select * ",buildSql(query),query.getParamList().toArray());
	}

	/**
	 * page query
	 */
	public Page<M> paginate(int pageNumber, int pageSize,List<Condition> conditions) {
		Query query = QueryBuilder.build(conditions, getTableName());
		if (null == query)
			return paginate(pageNumber,pageSize,"select * ","from "+getTableName());
		else
			return paginate(pageNumber,pageSize,"select * ",buildSql(query),query.getParamList().toArray());
	}

	/**
	 * page query by select
	 * "select id,name,title"
	 */
	public Page<M> paginate(int pageNumber, int pageSize, Condition condition,String select) {
		Query query = QueryBuilder.build(condition, getTableName());
		if (null == query)
			return paginate(pageNumber,pageSize,select," from "+getTableName());
		else
			return paginate(pageNumber,pageSize,select,buildSql(query),query.getParamList().toArray());
	}

	/**
	 * page query by select
	 * "select id,name,title"
	 */
	public Page<M> paginate(int pageNumber, int pageSize,List<Condition> conditions,String select) {
		Query query = QueryBuilder.build(conditions, getTableName());
		if (null == query)
			return paginate(pageNumber,pageSize,select," from "+getTableName());
		else
			return paginate(pageNumber,pageSize,select,buildSql(query),query.getParamList().toArray());
	}

	/**
	 * find all
	 */
	public List<M> findAll() {
		return find("select * from "+ getTableName()) ;
	}

	/**
	 * find by conditions
	 */
	public List<M> find(List<Condition> conditions) {
		Query query = QueryBuilder.build(conditions, getTableName(),"select *");
		if (null == query)
			return find("select * from " + getTableName());
		else
			return find(buildSql(query), query.getParamList().toArray());
	}

	/**
	 * find by conditions by select
	 * "select id,name,title"
	 */
	public List<M> find(List<Condition> conditions,String select) {
		Query query = QueryBuilder.build(conditions, getTableName(),select);
		if (null == query)
			return find(select + " from " + getTableName());
		else
			return find(buildSql(query), query.getParamList().toArray());
	}

	/**
	 * find by condition
	 */
	public List<M> find(Condition condition) {
		Query query = QueryBuilder.build(condition, getTableName(), "select *");
		if (null == query)
			return find("select * from " + getTableName());
		else
			return find(buildSql(query), query.getParamList().toArray());
	}

	/**
	 * find by condition by select
	 * "select id,name,title"
	 */
	public List<M> find(Condition condition,String select) {
		Query query = QueryBuilder.build(condition, getTableName(), select);
		if (null == query)
			return find(select + " from " + getTableName());
		else
			return find(buildSql(query), query.getParamList().toArray());
	}

	/**
	 * find first by condition
	 */
	public M findFirst(Condition condition) {
		List<M> list = find(condition);
		return null != list && list.size() > 0 ? list.get(0) : null;
	}


	/**
	 * find first by conditions
	 */
	public M findFirst(List<Condition> conditions) {
		List<M> list = find(conditions);
		return null != list && list.size() > 0 ? list.get(0) : null;
	}

	/**
	 * delete by condition
	 */
	public boolean delete(Condition condition){
		Query query = QueryBuilder.build(condition, getTableName(), "delete");
		if (null == query)
			return false;
		else
			return Db.update(query.getSql(), query.getParamList().toArray()) >= 0;
	}

	/**
	 * delete by conditions
	 */
	public boolean delete(List<Condition> conditions){
		Query query = QueryBuilder.build(conditions, getTableName(), "delete");
		if (null == query)
			return false;
		else
			return Db.update(query.getSql(), query.getParamList().toArray()) >= 0;
	}

	/**
	 * count by conditions
	 */
	public long count(List<Condition> conditions){
		Query query = QueryBuilder.build(conditions, getTableName(), "select count(*)");
		if (null == query)
			return Db.queryLong("select count(*) from " + getTableName());
		else
			return Db.queryLong(query.getSql(), query.getParamList().toArray());
	}

	/**
	 * count by condition
	 */
	public long count(Condition condition){
		Query query = QueryBuilder.build(condition, getTableName(), "select count(*)");
		if (null == query)
			return Db.queryLong("select count(*) from " + getTableName());
		else
			return Db.queryLong(query.getSql(), query.getParamList().toArray());
	}

    /**
     * OrderBy: Condition's orderBy is the first level ,if not set,will add Annotation order by
     */
    private String buildSql(Query query){
        String sql = query.getSql();
        if (!sql.contains("order by") && getClass().isAnnotationPresent(OrderBy.class)){
            sql += " order by " + getClass().getAnnotation(OrderBy.class).value();
        }
        return sql;
    }

    private String getTableName() {
        return getTable().getName();
    }

    private Table getTable() {
        return TableMapping.me().getTable(getUsefulClass());
    }

    private Class<? extends Model> getUsefulClass(){
        Class c = getClass();
        return c.getName().indexOf("EnhancerByCGLIB") == -1 ? c : c.getSuperclass();
    }

}
