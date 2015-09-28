package com.ZWPT.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.xmlpull.v1.XmlPullParserException;

import com.ZWPT.Utils.BaseAsyncTask;
import com.ZWPT.Utils.XmlUtil;
import com.ZWPT.activity.LoginActivity;
import com.ZWPT.data.entity.HandleResult;
import com.ZWPT.data.entity.Login_AppInfo;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

public abstract class CountsOfMainManagerService {
	public static final String TAG = "CountsOfMainManagerService";

	/**
	 * 获取主菜单界面各事项总数
	 * 
	 * @param paramActivity调用这个方法的activity
	 * @param paramInt
	 *            代表调用的是哪个功能，当调用下一个方法时，就知道做怎样的反应
	 * @param list传入的account
	 */
	public void getCounts(final Context paramActivity, final int paramInt,
			final List<Map<String, Object>> list) {
		final String url = paramActivity.getResources().getString(
				com.ZWPT.activity.R.string.endpoint);
		BaseAsyncTask loacl = new BaseAsyncTask(paramActivity, true) {
			String result = null;
			String result_id = null;
			List<Map<String, Object>> resultGroup = new ArrayList<Map<String, Object>>();
			List<Map<String, Object>> account = new ArrayList<Map<String, Object>>();

			@Override
			protected Integer doInBackground(Integer[] paramArrayOfInteger) {
				// 调用方法，到服务器上验证，第一个参数是要调用的服务器的方法，
				// 第二个参数是要传的参数，获取返回值
				try {
					result = downloadDB("getBizArchivesCount", url, list);
					result_id = downloadDB("getAcceptQtsl", url, list);
					if (result_id != null) {
						account = XmlUtil.getAcceptQtslXmlParse(result_id);
					}

					resultGroup = XmlUtil.CountsOfArchivesXmlParse(result);
				} catch (XmlPullParserException e) {
					// TODO 自动生成的 catch 块
					e.printStackTrace();
				} catch (IOException e) {
					// TODO 自动生成的 catch 块
					e.printStackTrace();
				}
				// 判断返回值，为空或者字符串“servicefail”返回-1.否则返回1
				if (result == null ||resultGroup == null || result.equals("error")) {
					return Integer.valueOf(-1);
				}
				return Integer.valueOf(1);
			}

			@Override
			protected void onPostExecute(Integer paramInteger) {
				super.onPostExecute(paramInteger);
				HandleResult handleResult = new HandleResult();
				if (paramInteger == 1) {// 获取到返回的信息
					handleResult.setGetCounts_archives_ResultSuccess("success");
					handleResult.setList_counts_archives(resultGroup);
				} else if (paramInteger == -1) {// 链接服务器失败
					handleResult.setGetCounts_archives_ResultSuccess("fail");
					Toast.makeText(paramActivity, "获取各事项总数失败！",
							Toast.LENGTH_LONG).show();
					return;
				}
				if (account != null && !result_id.equals("error")) {
					handleResult.setAccountID((String) account.get(0).get(
							"accountId"));
				}
				CountsOfMainManagerService.this.handlerLoginInfo(paramActivity,
						handleResult, paramInt);
			}

		};
		loacl.execute(1);
	}

	public abstract void handlerLoginInfo(Context context,
			HandleResult paramHandleResult, int paramInt);
}