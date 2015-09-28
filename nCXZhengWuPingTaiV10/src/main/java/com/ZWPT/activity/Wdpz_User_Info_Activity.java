package com.ZWPT.activity;

import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.ViewAnimator;

import com.ZWPT.data.Constants;
import com.ZWPT.data.InfoFile_;
import com.googlecode.androidannotations.annotations.AfterViews;
import com.googlecode.androidannotations.annotations.EActivity;
import com.googlecode.androidannotations.annotations.ViewById;
import com.googlecode.androidannotations.annotations.sharedpreferences.Pref;

/**
 * 我的配置
 * 
 * @author yuhuihui
 * @date 2014-6-25
 * 
 */
@EActivity(R.layout.layout_wdpz_info)
public class Wdpz_User_Info_Activity extends BaseActivity implements
		OnClickListener {
	@Pref
	InfoFile_ infoFile_;
	@ViewById
	RelativeLayout rl_title;// 标题栏
	// @ViewById ImageButton title_search;//搜索按钮
	@ViewById
	TextView tvTitle_two_Name;// 二级标题的显示文本
	@ViewById
	LinearLayout ll_wdpz_xgmm;// 修改密码的布局id
	@ViewById
	EditText edt_oldpsd, edt_newpsd, edt_arginpsd;// 修改密码
	@ViewById
	Button btn_update, btn_cancel, btn_update_userInfo, btn_update_info,
			btn_cancel_info;
	@ViewById
	TextView tv_wdpz_info_1, tv_wdpz_info_2, tv_wdpz_info_3, tv_wdpz_info_4,
			tv_wdpz_info_5, tv_wdpz_info_6, tv_wdpz_info_7, tv_wdpz_info_8,
			tv_wdpz_info_9, tv_wdpz_info_10, tv_wdpz_info_11;
	@ViewById
	ViewAnimator va_wdpz_info;// 动画类
	@ViewById
	EditText edt_usernumberinfo, edt_danwei, edt_username, edt_IDnumber,
			edt_gongzuobumen, edt_job, edt_contanct_number, edt_moblie_phone,
			edt_email, edt_fax_phone, edt_address;// 修改用户信息的编辑框

	@AfterViews
	void initView() {
		initTitle();
		btn_cancel.setOnClickListener(this);
		btn_cancel_info.setOnClickListener(this);
		btn_update.setOnClickListener(this);
		btn_update_info.setOnClickListener(this);
		btn_update_userInfo.setOnClickListener(this);
		this.finishOthers();
	}

	private void initTitle() {

		if (Constants.title.equals(context.getResources().getString(
				R.string.xiugaimima))) {
			rl_title.setBackgroundDrawable(context.getResources().getDrawable(
					R.drawable.tt_xgmm));
			if (va_wdpz_info.getVisibility() == View.VISIBLE) {
				va_wdpz_info.setVisibility(View.GONE);
			}
			if (ll_wdpz_xgmm.getVisibility() == View.GONE) {
				ll_wdpz_xgmm.setVisibility(View.VISIBLE);
			}
			tvTitle_two_Name.setText("修改密码");
		} else if (Constants.title.equals(context.getResources().getString(
				R.string.gerenxinxi))) {
			rl_title.setBackgroundDrawable(context.getResources().getDrawable(
					R.drawable.tt_grxx));
			if (ll_wdpz_xgmm.getVisibility() == View.VISIBLE) {
				ll_wdpz_xgmm.setVisibility(View.GONE);
			}
			if (va_wdpz_info.getVisibility() == View.GONE) {
				va_wdpz_info.setVisibility(View.VISIBLE);
			}
			tvTitle_two_Name.setText("用户信息" + "[" + "暂无" + "]");
		}
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_cancel:
			// 清空数据，不插入InfoFile
			break;
		case R.id.btn_cancel_info:
			showPreviousView();
			break;
		case R.id.btn_update:
			// 清空数据，将新密码插入InfoFile
			break;
		case R.id.btn_update_info:
			// 对用户输入的数据进行判断是否为空
			// 插入InfoFlie
			break;
		case R.id.btn_update_userInfo:
			showNextView();
			break;
		default:
			break;
		}
	}

	private void showNextView() {
		switch (va_wdpz_info.getDisplayedChild()) {
		case 0:
			va_wdpz_info.setInAnimation(context, R.anim.i_slide_in_left);
			va_wdpz_info.setOutAnimation(context, R.anim.i_slide_out_left);
			va_wdpz_info.showNext();
			tvTitle_two_Name.setText("修改个人信息");
			break;
		default:
			break;
		}
	}

	private void showPreviousView() {
		switch (va_wdpz_info.getDisplayedChild()) {
		case 1:
			va_wdpz_info.setInAnimation(context, R.anim.i_slide_in_right);
			va_wdpz_info.setOutAnimation(context, R.anim.i_slide_out_right);
			va_wdpz_info.showPrevious();
			tvTitle_two_Name.setText("用户信息" + "[" + "暂无" + "]");
			break;
		default:
			break;
		}
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			exitBy2Click();
		}
		return false;
	}
}
