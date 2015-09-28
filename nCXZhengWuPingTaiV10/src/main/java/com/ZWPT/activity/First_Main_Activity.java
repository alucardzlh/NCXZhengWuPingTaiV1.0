package com.ZWPT.activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.ZWPT.data.Constants;
import com.ZWPT.data.DataHelper;
import com.ZWPT.data.InfoFile_;
import com.ZWPT.data.entity.HandleResult;
import com.ZWPT.data.entity.Login_AppInfo;
import com.ZWPT.service.CountsOfMainManagerService;
import com.ZWPT.view.SampleListFragment;
import com.googlecode.androidannotations.annotations.AfterViews;
import com.googlecode.androidannotations.annotations.Bean;
import com.googlecode.androidannotations.annotations.EActivity;
import com.googlecode.androidannotations.annotations.NoTitle;
import com.googlecode.androidannotations.annotations.ViewById;
import com.googlecode.androidannotations.annotations.sharedpreferences.Pref;

/***
 * 
 * @author rjh 主菜单activity
 * 
 */
@NoTitle
@EActivity(R.layout.layout_main)
public class First_Main_Activity extends Activity {

	@Bean
	DataHelper dataHelper;
	@Pref
	InfoFile_ infoFile_;
	@ViewById
	ImageButton icon1, icon2, icon3, icon4, icon5;
	PopupWindow pop; // 菜单menu
	View view; // 菜单menu显示的视图

	@AfterViews
	void initView() {
		icon1.setOnClickListener(onClickListener);
		icon2.setOnClickListener(onClickListener);
		icon3.setOnClickListener(onClickListener);
		icon4.setOnClickListener(onClickListener);
		icon5.setOnClickListener(onClickListener);
		showBottomMenu();
	}

	private OnClickListener onClickListener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.icon1:
				Toast.makeText(First_Main_Activity.this, "暂未开通！",
						Toast.LENGTH_SHORT).show();
				break;
			case R.id.icon2:
				startZWPTActivity();
				break;
			case R.id.icon3:
				Toast.makeText(First_Main_Activity.this, "暂未开通！",
						Toast.LENGTH_SHORT).show();
				break;
			case R.id.icon4:
				Toast.makeText(First_Main_Activity.this, "暂未开通！",
						Toast.LENGTH_SHORT).show();
				break;
			case R.id.icon5:
				// Toast.makeText(First_Main_Activity.this, "暂未开通！",
				// Toast.LENGTH_LONG).show();
				break;
			default:
				break;
			}
		}
	};

	public void startZWPTActivity() {
		for (Login_AppInfo appinfo : Constants.list_appinfo) {
			if (appinfo.getName().equals("政务服务系统")) {
				Constants.account = appinfo.getAccount();
				Log.e("login_account", Constants.account);
			}
		}
		Intent Intent = new Intent();
		Intent.setClass(this, MainActivity_.class);
		startActivity(Intent);
		this.finish();
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			if (pop.isShowing()) {
				pop.dismiss();
			} else {
				pop.showAtLocation(view, Gravity.BOTTOM, 0, 0); // 设置窗体的位置
			}
		}
		if (keyCode == KeyEvent.KEYCODE_MENU) {
			if (pop.isShowing()) {
				pop.dismiss();
			} else {
				pop.showAtLocation(view, Gravity.BOTTOM, 0, 0); // 设置窗体的位置
			}
		}

		return true;
	}

	private void showBottomMenu() {
		WindowManager wm = (WindowManager) this
				.getSystemService(Context.WINDOW_SERVICE);
		LinearLayout ll1, ll2;
		int height = wm.getDefaultDisplay().getHeight();// 屏幕高度
		LayoutInflater inflater = (LayoutInflater) this
				.getSystemService(LAYOUT_INFLATER_SERVICE);
		view = inflater.inflate(R.layout.main_menu, null);
		ll1 = (LinearLayout) view.findViewById(R.id.main_menu_01);
		ll2 = (LinearLayout) view.findViewById(R.id.main_menu_02);
		pop = new PopupWindow(view, ViewGroup.LayoutParams.FILL_PARENT,
				ViewGroup.LayoutParams.WRAP_CONTENT);// 获取PopupWindow对象并设置窗体的大小
		// 点击popupWindow以外的地方或者返回键让其消失
		// pop.setBackgroundDrawable(context.getResources().getDrawable(
		// R.color.transparent));
		pop.setFocusable(false);
		pop.setOutsideTouchable(true);
		pop.setAnimationStyle(R.style.PopupAnimation);// 设置动画样式

		ll1.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent();

				intent.setClass(First_Main_Activity.this, LoginActivity_.class);
				intent.putExtra("re_login", "true");
				startActivity(intent);
				First_Main_Activity.this.finish();
			}
		});
		ll2.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				First_Main_Activity.this.finish();
			}
		});
	}

}
