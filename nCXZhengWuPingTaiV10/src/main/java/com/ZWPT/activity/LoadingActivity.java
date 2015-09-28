package com.ZWPT.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.provider.Settings;
import android.view.KeyEvent;
import android.view.WindowManager;
import android.widget.Toast;

import com.ZWPT.data.DataHelper;
import com.ZWPT.data.InfoFile_;
import com.ZWPT.service.NetWorkReceiver;
import com.ZWPT.sliding.SlidingMenu;
import com.ZWPT.view.AlertDialog;
import com.googlecode.androidannotations.annotations.AfterInject;
import com.googlecode.androidannotations.annotations.Bean;
import com.googlecode.androidannotations.annotations.EActivity;
import com.googlecode.androidannotations.annotations.NoTitle;
import com.googlecode.androidannotations.annotations.sharedpreferences.Pref;

@NoTitle
@EActivity(R.layout.layout_loading)
public class LoadingActivity extends Activity {
	@Bean
	DataHelper dataHelper;
	@Pref
	InfoFile_ infoFile;
	static final int LOADING = 0;
	protected static ActivityApp app;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO 自动生成的方法存根
		super.onCreate(savedInstanceState);
		// this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
		// WindowManager.LayoutParams.FLAG_FULLSCREEN);
		if (app == null) {
			app = (ActivityApp) getApplicationContext();
		}
		// getSlidingMenu().setTouchModeAbove(SlidingMenu.TOUCHMODE_NONE);

	}

	@AfterInject
	void init() {
		// app.addActivity(this);
		loading.start();
	}

	/**
	 * 开启异步线程的目的就是不让界面一闪而过，线程中不做任何操作，仅仅睡一秒钟
	 */
	private Thread loading = new Thread() {
		@Override
		public void run() {
			try {

				Thread.sleep(2000);
				Message msg = callbackHandler.obtainMessage();
				msg.what = LOADING;
				callbackHandler.sendMessage(msg);
			} catch (InterruptedException e) {
			}
		}
	};

	private Handler callbackHandler = new Handler() {
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case LOADING:
				if (checkNetwork()) {// 1、判断网络是否连接
					// 判断用户是否勾选记住密码,如果记住密码则跳转到主界面，如果没有记住密码则跳转至登入界面
					direct();
				} else {
					showNetworkDialog();// 显示网络没有连接
				}
				break;
			}
		};
	};

	/**
	 * 跳转界面
	 * 
	 * @author yuhuihui
	 * @date 2014-5-30
	 */
	private void direct() {
		// 初始化dataHelper
		dataHelper.setCallbackHandler(callbackHandler);
		// 启动广播接收者来判断网络连接状态以及是否是wifi连接
		NetWorkReceiver networkReceiver = NetWorkReceiver.getInstance(app);
		// 初始化为电信服务器（固定的服务器）
		networkReceiver.initServer(NetWorkReceiver.ServerType.dianXin);
		// 注册网络状态监听器
		IntentFilter filter = new IntentFilter();
		filter.addAction(android.net.ConnectivityManager.CONNECTIVITY_ACTION);
		app.registerReceiver(networkReceiver, filter);

		// 判断完网络连接状态后，判断用户是否记住过登陆密码
		// boolean isAutoLogin = infoFile.isAutoLogin().get();// 读取用户自动登录状态
		// if (isAutoLogin) {
		// dataHelper.login(infoFile.infoUsername().get(),
		// infoFile.infoPassword().get());
		// } else {
		Intent mIntent = new Intent();

		mIntent.setClass(this, LoginActivity_.class);

		startActivity(mIntent);
		finish();
		// }

		// getSlidingMenu().setTouchModeAbove(SlidingMenu.TOUCHMODE_MARGIN);
	}

	/**
	 * 检查网络状态
	 * 
	 * @return 是否成功连接
	 * @author yuhuihui
	 * @date 2014-5-30
	 */
	public boolean checkNetwork() {
		ConnectivityManager connectivityManager = (ConnectivityManager) LoadingActivity.this
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo NetworkInfo = connectivityManager.getActiveNetworkInfo();
		if (NetworkInfo != null && NetworkInfo.isConnected()) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 网络配置提醒
	 * 
	 * @author yuhuihui
	 * @date 2014-5-30
	 */
	public void showNetworkDialog() {
		// showToastLong("网络未连接！请连接网络！");
		AlertDialog dialog = new AlertDialog(LoadingActivity.this);
		dialog.setTitle("当前无可用网络").setMessage("是否跳转到网络设置界面？")
				.setEnsureText("确定").setCancelText("取消")
				.setOnClickListener(new AlertDialog.OnClickAdapter() {
					@Override
					public void onEnsureClick(AlertDialog dialog) {
						startActivity(new Intent(
								Settings.ACTION_WIRELESS_SETTINGS));// 跳转到网络配置界面
						finish();
					}

					@Override
					public void onCancelClick(AlertDialog dialog) {
						finish();
					}
				}).cancelable(false).show();
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			exitBy2Click();
		}
		return false;
	}

	private boolean isExit = false;

	/**
	 * 双击退出
	 */
	public void exitBy2Click() {
		if (!isExit) {
			isExit = true; // 准备退出
			Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();

			new Thread(new Runnable() {

				@Override
				public void run() {
					SystemClock.sleep(2000);
					isExit = false; // 取消退出
				}
			}).start();
		} else {
			app.finishAll();
		}
	}
}
