package com.ZWPT.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.xmlpull.v1.XmlPullParserException;

import com.ZWPT.Utils.BaseAsyncTask;
import com.ZWPT.Utils.XmlUtil;
import com.ZWPT.data.Constants;
import com.ZWPT.data.entity.HandleResult;
import com.ZWPT.data.entity.Login_AppInfo;
import com.ZWPT.data.entity.Login_Info;

import android.content.Context;
import android.widget.Toast;

public abstract class LoginManagerService {
	public static final String TAG = "LoginManagerService";

	/**
	 * 登录方法，通过用户名和密码到服务器验证，是否登录成功，否则失败
	 * 
	 * @param paramActivity调用这个方法的activity
	 * @param paramInt
	 *            代表调用的是哪个功能，当调用下一个方法时，就知道做怎样的反应
	 * @param list传入的用户名和密码
	 */
	public void login(final Context paramActivity, final int paramInt,
			final List<Map<String, Object>> list) {
		final String url = "http://117.40.244.134:8081/plat/Service/SignonSrv?wsdl";
		BaseAsyncTask loacl = new BaseAsyncTask(paramActivity, true) {
			String result = null;
			Login_Info resultGroup = new Login_Info();

			@Override
			protected Integer doInBackground(Integer[] paramArrayOfInteger) {
				// 调用方法，到服务器上验证，第一个参数是要调用的服务器的方法，
				// 第二个参数是要传的参数，获取返回值
				try {
					result = downloadDB("getAPPLogin", url, list);
					if (result.equals("error") || result == null) {
						resultGroup = null;
					} else {
						resultGroup = XmlUtil.LoginXmlParse(result);
					}

				} catch (XmlPullParserException e) {
					// TODO 自动生成的 catch 块
					e.printStackTrace();
				} catch (IOException e) {
					// TODO 自动生成的 catch 块
					e.printStackTrace();
				}
				// 判断返回值，为空或者字符串“servicefail”返回-1.否则返回1
				if (result == null ||resultGroup == null) {
					return Integer.valueOf(-1);
				}
				return Integer.valueOf(1);
			}

			@Override
			protected void onPostExecute(Integer paramInteger) {
				super.onPostExecute(paramInteger);
				HandleResult handleResult = new HandleResult();
				if (paramInteger == 1) {// 获取到返回的信息
					List<Login_AppInfo> list = new ArrayList<Login_AppInfo>();
					if (resultGroup.getMap_login_status().get("LOGINSTATUS")
							.equals("true")) {
						handleResult.setLoginSuccess("success");
						for (int i = 0; i < resultGroup.getList_app().size(); i++) {

							Login_AppInfo info = new Login_AppInfo();
							info.setName((String) resultGroup.getList_app()
									.get(i).get("NAME"));
							info.setAccount((String) resultGroup.getList_app()
									.get(i).get("ACCOUNT"));
							list.add(info);

						}
						Constants.list_appinfo = list;
					} else {
						handleResult.setLoginSuccess("fail");
					}
					handleResult.setList(list);
				} else if (paramInteger == -1) {// 链接服务器失败
					Toast.makeText(paramActivity, "连接服务器失败！", Toast.LENGTH_LONG)
							.show();
					return;
				}
				LoginManagerService.this.handlerLoginInfo(paramActivity,
						handleResult, paramInt);
			}

		};
		loacl.execute(1);
	}

	public abstract void handlerLoginInfo(Context context,
			HandleResult paramHandleResult, int paramInt);
}
