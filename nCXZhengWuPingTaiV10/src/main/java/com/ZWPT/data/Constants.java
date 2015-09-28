package com.ZWPT.data;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.ZWPT.data.entity.Detail_Sjdj_bean;
import com.ZWPT.data.entity.Login_AppInfo;

public abstract class Constants {
	// 当前版本号
	public static String LOCAL_VERSION_CODE = "";
	public static String title = "main";
	public static boolean SHOWSEARCH_BUTTON = false;
	// 收件登记事项是否显示搜索结果列表
	public static boolean SHOW_SJDJ_SEARCH_RESULT = false;
	// 收件登记事项是否显示二级菜单列表
	public static boolean SHOW_SJDJ_SECOND_LIST = false;

	// 当前登录账号
	public static String account = "";
	// 当前左侧导航栏菜单list
	public static List<String> list_leftmenu = new ArrayList<String>();

	// 收件登记中提交材料列表
	public static List<Map<String, Object>> list_tjcl = new ArrayList<Map<String, Object>>();
	// 收件登记中提交材料列表
	public static List<Map<String, Object>> list_middle = new ArrayList<Map<String, Object>>();
	public static List<Login_AppInfo> list_appinfo = new ArrayList<Login_AppInfo>();
	// 下载的文件保存路径
	public static final String LOCAL_DOWNLOAD_DIR = "downLoad/ZWPT/";

	// 分页大小
	public static final int PAGE_SIZE_LARGE = 10, PAGE_SIZE_MEDIUM = 8,
			PAGE_SIZE_SMALL = 6;
	// 服务器类型，0电信，1移动，2联通
	public static final int SERVER_NULL = 0, SERVER_DIANXIN = 1,
			SERVER_YIDONG = 2, SERVER_LIANTONG = 3;
	public static int SERVER_TYPE = SERVER_NULL;

	public static boolean fromYsl = false;// 受理业务是否从预受理跳入受理

	public static List<Map<String, Object>> list_tjcl_for_save_uploaded_pictures = new ArrayList<Map<String, Object>>();
	
}
