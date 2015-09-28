package com.ZWPT.activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.ZWPT.Utils.StringUtil;
import com.ZWPT.data.Constants;
import com.ZWPT.data.DataHelper;
import com.ZWPT.data.InfoFile_;
import com.ZWPT.data.entity.HandleResult;
import com.ZWPT.service.LoginManagerService;
import com.ZWPT.sliding.SlidingMenu;
import com.ZWPT.view.LoadingDialog;
import com.googlecode.androidannotations.annotations.AfterViews;
import com.googlecode.androidannotations.annotations.Bean;
import com.googlecode.androidannotations.annotations.ViewById;
import com.googlecode.androidannotations.annotations.sharedpreferences.Pref;
import com.googlecode.androidannotations.annotations.EActivity;
import com.googlecode.androidannotations.annotations.NoTitle;
import com.umeng.update.UmengUpdateAgent;

/***
 * 
 * @author rjh 登录activity
 * 
 */
@NoTitle
@EActivity(R.layout.layout_login_main)
public class LoginActivity extends Activity {
	@Bean
	DataHelper dataHelper;
	@Pref
	InfoFile_ infoFile_;
	@ViewById
	EditText edt_login_1, edt_login_2;
	@ViewById
	CheckBox cb_login;
	@ViewById
	Button btn_login;
	@ViewById
	TextView tv_version;
	protected static ActivityApp app;

	@AfterViews
	void initView() {
		boolean exit = getIntent().getBooleanExtra("exit", false);
		if (app == null) {
			app = (ActivityApp) getApplicationContext();
			// app.addActivity(this);
		}
		if (exit) {
			finish();
		} else {
			btn_login.setOnClickListener(onClickListener);
			// getSlidingMenu().setTouchModeAbove(SlidingMenu.TOUCHMODE_NONE);
			initLogin();
		}
		try {
			//
			tv_version.setTextColor(Color.BLACK);
			tv_version.setText("version：" + getVersionName() + "    ");
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}

	private void initLogin() {
		if (infoFile_.isAutoLogin().get()) {
			edt_login_1.setText(infoFile_.infoUsername().get());
			edt_login_2.setText(infoFile_.infoPassword().get());
			cb_login.setChecked(true);
		}
	}

	private LoginManagerService mloginmanagerservice = new LoginManagerService() {

		@Override
		public void handlerLoginInfo(Context context,
				HandleResult paramHandleResult, int paramInt) {
			switch (paramInt) {
			case 1:
				if (paramHandleResult.getLoginSuccess().equals("success")) {
					Toast.makeText(LoginActivity.this, "登录成功！",
							Toast.LENGTH_LONG).show();

					Intent mIntent = new Intent();

					mIntent.setClass(LoginActivity.this,
							First_Main_Activity_.class);

					startActivity(mIntent);
					finish();
				} else if (paramHandleResult.getLoginSuccess().equals("fail")) {
					Toast.makeText(LoginActivity.this, "用户名或者密码错误!！",
							Toast.LENGTH_LONG).show();
				}

				break;
			case 2:
				break;
			default:
				break;
			}
		}
	};

	private OnClickListener onClickListener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.btn_login:
				List<Map<String, Object>> paramlist = new ArrayList<Map<String, Object>>();
				Map<String, Object> map_account = new HashMap<String, Object>();
				if (StringUtil.isBlank(edt_login_1.getText().toString())
						|| StringUtil.isBlank(edt_login_2.getText().toString())) {
					Toast.makeText(LoginActivity.this, "用户名和密码不能为空！",
							Toast.LENGTH_LONG).show();
					return;
				} else {
					if (cb_login.isChecked()) {
						infoFile_.edit().isAutoLogin().put(true).apply();
						infoFile_.edit().infoUsername()
								.put(edt_login_1.getText().toString()).apply();
						infoFile_.edit().infoPassword()
								.put(edt_login_2.getText().toString()).apply();
					} else {
						infoFile_.edit().isAutoLogin().put(false).apply();
					}
					map_account
							.put("account", edt_login_1.getText().toString());
					map_account.put("password", edt_login_2.getText()
							.toString());
					paramlist.add(map_account);
					mloginmanagerservice
							.login(LoginActivity.this, 1, paramlist);
				}

				break;

			default:
				break;
			}
		}
	};

	private Handler callbackHandler = new Handler() {
		public void handleMessage(android.os.Message msg) {

		};
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// String re_login = "false";
		// re_login = getIntent().getExtras().getString("re_login");
		if (getIntent().getStringExtra("re_login") != null
				&& getIntent().getStringExtra("re_login").equals("true")) {
			infoFile_.edit().infoUsername().put("").apply();
			infoFile_.edit().infoPassword().put("").apply();
		}
		UmengUpdateAgent.setUpdateOnlyWifi(false);
		UmengUpdateAgent.update(this);
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
			this.finish();
		}
	}

	private String getVersionName() throws Exception {
		// 获取packagemanager的实例
		PackageManager packageManager = getPackageManager();
		// getPackageName()是你当前类的包名，0代表是获取版本信息
		PackageInfo packInfo = packageManager.getPackageInfo(getPackageName(),
				0);
		String version = packInfo.versionName;
		return version;
	}

}
