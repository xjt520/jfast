package org.fast.core.kit;

import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.jfinal.log.Log;

public class JsonKit {
	
	protected static final Log logger = Log.getLog(JsonKit.class);

//	public static final Object parse(String text); // 把JSON文本parse为JSONObject或者JSONArray 
//	public static final JSONObject parseObject(String text)； // 把JSON文本parse成JSONObject    
//	public static final T parseObject(String text, Class clazz); // 把JSON文本parse为JavaBean 
//	public static final JSONArray parseArray(String text); // 把JSON文本parse成JSONArray 
//	public static final List parseArray(String text, Class clazz); //把JSON文本parse成JavaBean集合 
//	public static final String toJSONString(Object object); // 将JavaBean序列化为JSON文本 
//	public static final String toJSONString(Object object, boolean prettyFormat); // 将JavaBean序列化为带格式的JSON文本 
//	public static final Object toJSON(Object javaObject); 将JavaBean转换为JSONObject或者JSONArray。
	
	
	public static Map<String,String> toMap(String data){
		try {
			Map<String,String> map = JSON.parseObject(data,new TypeReference<Map<String, String>>() {});
			return map;
		} catch (Exception e) {
			logger.info(e.getMessage());
			return null;
		}
	}

	public static Map<String,Object> toMapObj(String data){
		try {
			Map<String,Object> map = JSON.parseObject(data,new TypeReference<Map<String, Object>>() {});
			return map;
		} catch (Exception e) {
			logger.info(e.getMessage());
			return null;
		}
	}
	
	public static <T> Object toBean(String data,Class<T> clazz){
		T t = null;
		try {
			t = JSON.parseObject(data,clazz);
			return t;
		} catch (Exception e) {
			logger.info(e.getMessage());
			return null;
		}
	}
	
	public static <T> List<T> toList(String data,Class<T> clazz){
		List<T> tList = null;
		try {
			tList = JSON.parseArray(data,clazz);
			return tList;
		} catch (Exception e) {
			logger.info(e.getMessage());
			return null;
		}
	}
	
	public static String data = "{'rescode': '00','datalist': [{'id': '1','stageName': '三期','stageValue': '3','typeCoding': '03'}]}";
	public static String data2 = "[{'id': '1','stageName': '三期','stageValue': '3','typeCoding': '03'}]";
	public static void main(String[] args) {
		System.out.println(toMap(data));
		System.out.println(toList(data2,String.class));
		System.out.println(toBean(data,Map.class));
	}
}
