package com.ZWPT.Utils;

import java.io.IOException;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.MeasureSpec;
import android.widget.ListAdapter;
import android.widget.ListView;

public class CommUtil {

	// /**
	// * 更改权限
	// * @param permission权限
	// * @param path文件路径
	// * @author yuhuihui
	// * @date 2014-5-30
	// */
	// public static void chmod(String permission, String path) {
	// try {
	// String command = "chmod " + permission + " " + path;
	// Runtime runtime = Runtime.getRuntime();
	// runtime.exec(command);
	// } catch (IOException e) {
	// Log.e("CommUtil (chmod) --> ", e.toString());
	// }
	// }

	/**
	 * 打印默认info级别的日志
	 * 
	 * @param tag
	 *            标签
	 * @param info
	 *            信息
	 * @author yuhuihui
	 * @date 2014-5-30
	 */
	public static void log(String tag, String info) {
		// Log.i(tag, info);
	}

	/**
	 * 打印debug级别的日志
	 * 
	 * @param tag
	 *            标签
	 * @param info
	 *            信息
	 * @author yuhuihui
	 * @date 2014-5-30
	 */
	public static void logD(String tag, String info) {
		// Log.d(tag, info);
	}

	/**
	 * 打印error级别的日志
	 * 
	 * @param tag
	 *            标签
	 * @param info
	 *            信息
	 * @author yuhuihui
	 * @date 2014-5-30
	 */
	public static void logE(String tag, String info) {
		// Log.e(tag, info);
	}

	/**
	 * 判断网络是否连接
	 * 
	 * @author yuhuihui
	 * @param context
	 * @return
	 */
	public static boolean isNetworkOk(Context context) {
		ConnectivityManager connectivityManager = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo activeInfo = connectivityManager.getActiveNetworkInfo();
		if (activeInfo == null) {
			return false;
		} else {
			return activeInfo.isConnected();
		}
	}

	/**
	 * @param 获取
	 *            listView 的绝对高度
	 */
	public static void setListViewHeightBasedOnChildren(ListView listView) {

		ListAdapter listAdapter = listView.getAdapter();
		if (listAdapter == null) {
			return;
		}

		int totalHeight = 0;
		for (int i = 0; i < listAdapter.getCount(); i++) {
			View listItem = listAdapter.getView(i, null, listView);
			listItem.measure(
					MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED),
					MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED));
			// listItem.measure(0, 0);
			totalHeight += listItem.getMeasuredHeight();
		}

		ViewGroup.LayoutParams params = listView.getLayoutParams();
		params.height = totalHeight
				+ (listView.getDividerHeight() * (listAdapter.getCount() - 1));
		listView.setLayoutParams(params);
		listView.requestLayout();
	}
}
