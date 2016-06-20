package org.fast.core.constant;

public class R {
	
	public static final String Key_IsSuccess = "isSuccess" ;
	public static final String Key_Code = "code" ;
	public static final String Key_Msg = "msg" ;
	/***********Table Prefix******************/
	public static final String PREFIX = "fast_";
	
	public class ViewSkipMode{
		public static final String viewSkipMode = "viewSkipMode";
		public static final String type_tab = "tab";
		public static final String type_dialog = "dialog";
	}
	
	public class BaseController{
		public static final String timer = "timer";
		public static final String last_timer = "lastTimer";
		public static final String login_user = "LOGIN_USER";
		public static final String ids = "ids";
		public static final String ids_split = ",";
		
		public static final String jsp_index = "index.jsp";
		public static final String jsp_add = "add.jsp";
		public static final String jsp_update = "update.jsp";
		public static final String jsp_view = "view.jsp";
	}
	
	public class Msg {
		public static final String FAILURE = "失败！" ;
		public static final String SUCCESS = "成功！" ;
		public static final String SAVE_FAILURE = "保存失败！" ;
		public static final String SAVE_SUCCESS = "保存成功！" ;
		public static final String DELETE_FAILURE = "删除失败！" ;
		public static final String DELETE_SUCCESS = "删除成功！" ;
		
		public static final String Repeat = "重复数据！" ;
		public static final String Required = "必填项！" ;
	}
	
	public class Db {
		public static final String durex = "fast" ;//权限管理模块 使用的数据库
		public static final String main = "main" ;
		public static final String fast = "fast" ;
	}
	
	public class DbType {
		public static final String mysql = "mysql" ;
		public static final String oracle = "oracle" ;
		public static final String sqlite = "sqlite" ;
	}
	
	public class Status {
		public static final String QY = "1" ;//启用
		public static final String JY = "0" ;//禁用
	}
}
