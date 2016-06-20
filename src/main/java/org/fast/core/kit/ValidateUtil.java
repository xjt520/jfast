package org.fast.core.kit;

import java.util.Collection;
import java.util.Map;

public class ValidateUtil {

	/**
	 * 判断字符串的有效性 
	 */
	public static boolean isValid(String str){
		if(str == null || "".equals(str.trim())){
			return false ;
		}
		return true ;
	}
	/**
	 * 判断字符串的有效性   多个字符串
	 */
	public static boolean isValid(String... s){
		for(String str : s){
			if(str == null || "".equals(str.trim())){
				return false ;
			}
		}
		return true;
	}
	
	/**
	 * 判断集合有效性
	 */
	@SuppressWarnings("rawtypes")
	public static boolean isValid(Collection col){
		if(col == null || col.isEmpty()){
			return false ;
		}
		return true ;
	}
	
	@SuppressWarnings("rawtypes")
	public static boolean isValid(Map map){
		if(map == null || map.isEmpty()){
			return false ;
		}
		return true ;
	}

	/**
	 * 判断是否数组有效
	 */
	public static boolean isValid(Object[] arr) {
		if(arr == null || arr.length == 0){
			return false;
		}
		return true ;
	}
	
	/**
	 * 判断是否实体有效
	 */
	public static boolean isValid(Object obj) {
		if(obj == null){
			return false;
		}
		return true ;
	}
	
}
