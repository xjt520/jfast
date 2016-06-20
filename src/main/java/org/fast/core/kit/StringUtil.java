package org.fast.core.kit;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtil {
	/**
	 * 判断是否未数值类型,不包含小数
	 */
	public static boolean isNumber(String str){
		//不为空进行判断
		if(ValidateUtil.isValid(str)){
			for(int i = str.length() ; --i > 0;){
				if(!Character.isDigit(str.charAt(i))){
					return false; 
				}else{
					return true;
				}
			}
		//如果为空则返回false
		}else{
			return false;
		}
		return false;
	}
	
	/**
	 * 判断两字符串是否相等
	 */
	public static boolean eq(String st,String str) {
		
		if(getValue(st).trim() == getValue(str).trim() || getValue(st).trim().equals(getValue(str).trim())) return true;
		return false;
	}
	
	/**
	 * 判断三字符串是否相等
	 */
	public static boolean eq(String s1,String s2,String s3) {
		if(eq(s1,s2)){
			if(eq(s1, s3)){
				return true;
			}else{
				return false;
			}
		}else{
			return false;
		}
	}
	
	
	/**
	 * 获取值，如果为空返还空字符串
	 */
	public static String getValue(String str){
		return str == null ? "" : str;
	}
	/**
	 * 获取值，如果为空返还空字符串
	 */
	public static String getValue(Object str){
		return str == null ? "" : str.toString();
	}
	/**
	 * 获取值，如果为空返还Null
	 */
	public static String getValueNull(Object str){
		return str == null ? null : str.toString();
	}
	
	/**
	 * 将字符串拆分成数组,按照指定的字符拆分
	 */
	public static String[] str2Arr(String str,String tag){
		if(ValidateUtil.isValid(str)){
			return str.split(tag);
//			return StringUtils.split(str,tag);
		}
		return null ;
	}
	
	/**
	 * 将字符串拆分成数组,按照指定的字符拆分
	 * 按照 _ 拆分
	 */
	public static String[] str2Arr(String str){
		if(ValidateUtil.isValid(str)){
			Pattern p4underline = Pattern.compile("((?:[^_]|(?:__)|(?:_-))+)");
			Matcher m = p4underline.matcher(str);
			List<String> resultUnderline = new ArrayList<String>();
			while(m.find()){
				resultUnderline.add(m.group(0).replace("__", "_").trim());
			}
			return resultUnderline.toArray(new String[]{});
		}
		return null ;
	}

	/**
	 * 判断数组中是否含有指定的字符串
	 */
	public static boolean contains(String[] values, String index) {
		if(ValidateUtil.isValid(values)){
			for(String s : values){
				if(s.equals(index)){
					return true ;
				}
			}
		}
		return false;
	}

	/**
	 * 将数组转成字符串,采用","号分各个
	 */
	public static String arr2Str(Object[] arr) {
		if(ValidateUtil.isValid(arr)){
			StringBuffer buffer = new StringBuffer();
			for(Object s : arr){
				buffer.append(s + ",");
			}
			String tmp = buffer.toString();
			return tmp.substring(0,tmp.length() - 1);
		}
		return null;
	}
	
	/**
	 * 取得描述串
	 */
	public static String getDescString(String str){
		if(ValidateUtil.isValid(str) && str.length() > 50){
			return str.substring(0, 50);
		}
		return str ;
	}

	/**
	 * 是否是Double
	 * 
	 * @param nm
	 * @return
	 * @throws Exception
	 */
	public static boolean isDouble(String str) {
		if (ValidateUtil.isValid(str)) {
			try {
				Double.parseDouble(str);
				return true;
			} catch (Exception ex) {
				return false;
			}
		}
		return false;
	}
	
	/**
	 * 将字符串转换为double  
	 * @param str
	 * @return
	 */
	public static double doubleValue(String str) {
		if (ValidateUtil.isValid(str)) {
			try {
				return Double.parseDouble(str);
			} catch (Exception ex) {
				return 0;
			}
		}
		return 0;
	}

}
