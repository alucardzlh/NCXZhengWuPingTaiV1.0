package com.ZWPT.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.ZWPT.Utils.BaseAsyncTask;
import com.ZWPT.data.entity.HandleResult;
import com.ZWPT.data.entity.Login_AppInfo;

import android.content.Context;
import android.widget.Toast;

public abstract class AppUpdataVerssionService {
	public static final String TAG = "AppUpdataVerssionService";

	/**
	 * 获取服务端版本信息
	 * 
	 * @param parmasActivity
	 *            操作的activity对象
	 * @param parmasInt
	 *            指定回调的操作
	 * @param oList
	 *            入参
	 */
	public void updataVerssion(final Context parmasActivity,
			final int parmasInt, final List<Map<String, Object>> oList) {
		final String url = "";// 调用的接口地址

		BaseAsyncTask loacl = new BaseAsyncTask(parmasActivity, false) {
			String result = null;
			List<Map<String, Object>> resultGroup = new ArrayList<Map<String, Object>>();

			@Override
			protected Integer doInBackground(Integer[] paramArrayOfInteger) {
				try {
					result = downloadDB("", url, oList);// 第一个参数接口的方法名，第二个参数接口地址，第三个参数入参信息

					resultGroup = null;// 传入result，如果是xml的格式进行pull解析，如果是json进行json解析
				} catch (Exception e) {
					e.printStackTrace();
				}
				// 判断返回值，为空或者字符串“servicefail”返回-1.否则返回1
				if (resultGroup == null) {
					return Integer.valueOf(-1);
				}
				return Integer.valueOf(1);
			}

			@Override
			protected void onPostExecute(Integer paramInteger) {
				HandleResult handleResult = new HandleResult();
				if (paramInteger == 1) {// 获取到返回的信息
					List<Login_AppInfo> list = new ArrayList<Login_AppInfo>();
					// if (resultGroup.get(0).get("LOGINSTATUS").equals("true"))
					// {
					// handleResult.setLoginSuccess("success");
					// for (int i = 1; i < resultGroup.size(); i++) {
					//
					// Login_AppInfo info = new Login_AppInfo();
					// info.setName((String) resultGroup.get(i)
					// .get("NAME"));
					// info.setAccount((String) resultGroup.get(i).get(
					// "ACCOUNT"));
					// list.add(info);
					//
					// }
					// } else {
					// handleResult.setLoginSuccess("fail");
					// }
					handleResult.setList(list);
				} else if (paramInteger == -1) {// 链接服务器失败

					Toast.makeText(parmasActivity, "连接服务器失败！",
							Toast.LENGTH_LONG).show();
					return;
				}
				AppUpdataVerssionService.this.handlerVerssionInfo(
						parmasActivity, handleResult, parmasInt);
				super.onPostExecute(paramInteger);
			}
		};
		loacl.execute(1);
	}

	public abstract void handlerVerssionInfo(Context context,
			HandleResult paramHandleResult, int paramInt);
}
