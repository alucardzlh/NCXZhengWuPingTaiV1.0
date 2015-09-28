package com.ZWPT.Utils;

import android.os.AsyncTask;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.xmlpull.v1.XmlPullParserException;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Handler;
import android.os.Message;

import com.ZWPT.view.LoadingDialog;

public class BaseAsyncTask extends AsyncTask<Integer, Integer, Integer> {
	private static final int SHOW_BAR = 4097;
	public static final int UPDATE_FAIL = -1;
	public static final int UPDATE_SUCCEED = 1;
	private Handler asyncHandler = new Handler() {
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			switch (msg.what) {
			case SHOW_BAR:
				if (LD != null && context != null) {
					// if (((BaseActivity) context).isShowDialog()) {
					LD.show();
					// ((BaseActivity) context).setOnCloseProgressDialog(LD);
					// } else {
					// LD.cancel();
					// }
				}
				break;
			}
		}
	};
	private Context context;
	private LoadingDialog LD;
	protected String resultGroup;
	private boolean showBar = false;
	private int showTime = 1000;
	public static String str_url;

	public BaseAsyncTask(Context paramContext) {
		this.context = paramContext;

	}

	public BaseAsyncTask(Context paramContext, boolean paramBoolean) {
		this.context = paramContext;
		this.showBar = paramBoolean;
	}

	private void closeBar() {
		if (this.LD != null) {
			this.LD.dismiss();
			this.LD = null;
			this.context = null;
		}
	}

	public static String downloadDB(Context context, String methodName,
			String endPoint, List<Map<String, Object>> paramList)
			throws XmlPullParserException, IOException {
		String result = HttpUtil.getXmlData(context, methodName, endPoint,
				paramList);
		// List<Map<String, Object>> list = XmlUtil.XmlParse(result);
		// if ((result == null) ) {
		// return null;
		// }
		// for(int i=0;i<list.size();i++){
		// Log.e("rjh-------------------->>>>>>>>", list.get(i).toString());
		// }

		return result;

	}

	private void setDownLoadProgress() {
		this.LD = new LoadingDialog(context, "", false);
		this.LD.setTitle("正在交互数据");
		this.LD.setMessage("请稍候...");
		this.LD.setCanceledOnTouchOutside(false);
		this.LD.setOnCancelListener(new DialogInterface.OnCancelListener() {
			public void onCancel(DialogInterface paramDialogInterface) {

			}
		});
		// this.LD.setProgressStyle(0);
		this.asyncHandler.sendEmptyMessageDelayed(SHOW_BAR, this.showTime);
	}

	protected Integer doInBackground(Integer[] paramArrayOfInteger) {
		return 1;
	}

	public String downloadDB(String methodName, String endPoint,
			List<Map<String, Object>> paramList) throws XmlPullParserException,
			IOException {
		return downloadDB(this.context, methodName, endPoint, paramList);
	}

	protected void onPostExecute(Integer paramInteger) {
		super.onPostExecute(paramInteger);
		if (this.showBar)
			closeBar();
	}

	protected void onPreExecute() {
		super.onPreExecute();
		if (this.showBar) {
			setDownLoadProgress();
		}
	}

	public int getShowTime() {
		return this.showTime;
	}

	public boolean isShowBar() {
		return this.showBar;
	}

	public void setShowBar(boolean paramBoolean) {
		this.showBar = paramBoolean;
	}

	public void setShowTime(int paramInt) {
		this.showTime = paramInt;
	}
}
