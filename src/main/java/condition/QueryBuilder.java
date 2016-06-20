package condition;

import com.jfinal.kit.StrKit;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xjt520 on 15/12/25.
 */
public class QueryBuilder {

    public static Query build(Condition condition,String table){
        return build(condition,table,"");
    }

    public static Query build(List<Condition> conditions,String table){
        return build(conditions,table,"");
    }

    public static Query build(Condition condition,String table,String select){
        if ( null == condition || StrKit.isBlank(table) )
            return null;
        List<Condition> conditions = new ArrayList<Condition>();
        conditions.add(condition);
        return build(conditions,table,select);
    }

    public static Query build(List<Condition> conditions,String table,String select){
        if ( null == conditions || conditions.size() == 0 || StrKit.isBlank(table) )
            return null;
        Query query = dynamic(conditions);
        if (null != query)
            query.setSql(null == select?"":select + from(table) + where() + query.getSql());
        return query;
    }

    private static Query dynamic(List<Condition> conditions) {
        List<Object> paramList = new ArrayList<Object>();
        DynamicBuilder dynamicBuilder = new DynamicBuilder();
        for (Condition condition : conditions) {
            String newOperator = condition.getNewOperator();
            Object newValue = condition.getNewValue();
            //空值处理
            if (null == newValue || newValue.toString().trim().equals("")){
                return null;
            }
            if (condition instanceof SingleFieldCondition){
                String field = condition.getField();
                parse(true, field, newValue, newOperator, dynamicBuilder, paramList);
            }else{
                MultiFieldCondition multiFieldCondition = (MultiFieldCondition)condition;
                List<String> fields = multiFieldCondition.getFields();
                for (String field : fields){
                    parse(false, field, newValue, newOperator, dynamicBuilder, paramList);
                }
            }
        }
        dynamicBuilder.orderBy(conditions.get(0).getOrderBy());
        return new Query(dynamicBuilder.toString(),paramList);
    }

    private static void parse(boolean and,String field,Object newValue,String newOperator,DynamicBuilder dynamicBuilder,List<Object> paramList){
        if (newOperator.equals("is null") || newOperator.equals("is not null")){
            if (and)
                dynamicBuilder.and(field, "", newOperator);
            else
                dynamicBuilder.or(field, "", newOperator);
        }else if (newOperator.equals("in") || newOperator.equals("not in")){
            String tempStr = "";
            tempStr += "(";
            if (newValue instanceof String[]){
                for (String str : (String[])newValue){
                    tempStr += "?,";
                    paramList.add(str);
                }
            }
            if (newValue instanceof List){
                for (Object obj : (List)newValue){
                    tempStr += "?,";
                    paramList.add(obj);
                }
            }
            tempStr = tempStr.substring(0,tempStr.lastIndexOf(",")) + ")";
            if (and)
                dynamicBuilder.and(field, tempStr, newOperator);
            else
                dynamicBuilder.or(field, tempStr, newOperator);
        }else {
            paramList.add(newValue);
            if (and)
                dynamicBuilder.and(field, "?", newOperator);
            else
                dynamicBuilder.or(field, "?", newOperator);

        }
    }

    private static String where(){
        return " where ";
    }

    private static String from(String table){
        return " from "+table;
    }

}
