package com.ZWPT.activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.Context;
import android.content.Intent;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ZWPT.data.Constants;
import com.ZWPT.data.DataHelper;
import com.ZWPT.data.InfoFile_;
import com.ZWPT.data.entity.HandleResult;
import com.ZWPT.service.BaseDetail_ManagerService;
import com.ZWPT.view.MyPopupWindow;
import com.googlecode.androidannotations.annotations.AfterViews;
import com.googlecode.androidannotations.annotations.Bean;
import com.googlecode.androidannotations.annotations.EActivity;
import com.googlecode.androidannotations.annotations.ViewById;
import com.googlecode.androidannotations.annotations.sharedpreferences.Pref;

@EActivity(R.layout.layout_spcl_dsp_thyw)
public class Spcl_Dsp_thyw_Activity extends BaseActivity {
	@Bean
	DataHelper dataHelper;
	@Pref
	InfoFile_ infoFile_;
	@ViewById
	TextView tvTitle_two_Name, edt_search_1, edt_search_2, tv_search_1;
	@ViewById
	LinearLayout search_rll_fenlei;
	@ViewById
	Button btn_list_1, btn_list_2;
	@ViewById
	RelativeLayout rl_title;

	@AfterViews
	void initView() {
		initTitle();
		tvTitle_two_Name.setText(infoFile_.serviceSubjectName().get());
		edt_search_1.setText(infoFile_.shengqingrenname().get());
		edt_search_2.setText(infoFile_.shengqingriqi().get());
		search_rll_fenlei.setOnClickListener(onClickListener);
		btn_list_1.setOnClickListener(onClickListener);
		btn_list_2.setOnClickListener(onClickListener);
	}

	public View.OnClickListener onClickListener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.btn_list_1:
				List<Map<String, Object>> list_1 = new ArrayList<Map<String, Object>>();
				Map<String, Object> oMap = new HashMap<String, Object>();
				oMap.put("bizArchivesId", infoFile_.bizArchivesId().get());
				oMap.put("userAccountId", infoFile_.infoUserId().get());
				if (!tv_search_1.getText().toString().equals("---请选择退回原因---")) {
					oMap.put("pauseReason", tv_search_1.getText().toString());
					list_1.add(oMap);
					baseDetail_ManagerService.doBizArchivesWithDrawal(
							Spcl_Dsp_thyw_Activity.this, 5, list_1);
				} else {
					showToastLong("请选择退回原因！");
				}
				break;
			case R.id.btn_list_2:
				Spcl_Dsp_thyw_Activity.this
						.startActivity(new Intent(Spcl_Dsp_thyw_Activity.this,
								Base_DetailActivity_.class));
				Spcl_Dsp_thyw_Activity.this.finish();
				break;
			case R.id.search_rll_fenlei:
				final List<String> list = new ArrayList<String>();
				list.add("申报材料不齐全");
				list.add("申报材料齐全且在期限内未补正");
				list.add("不符合有关产业政策及技术要求");
				list.add("申报项目不具备批准条件");
				new MyPopupWindow(Spcl_Dsp_thyw_Activity.this, list,
						search_rll_fenlei, MyPopupWindow.BOTTOM) {
					@Override
					protected void doNext(int position) {
						tv_search_1.setText(list.get(position));
					}
				};

				break;
			default:
				break;
			}
		}
	};

	private BaseDetail_ManagerService baseDetail_ManagerService = new BaseDetail_ManagerService() {

		@Override
		public void handlerBaseDetailInfo(Context context,
				HandleResult paramHandleResult, int paramInt) {
			if (paramHandleResult.getThywSuccess().equals("success")) {
				Toast.makeText(Spcl_Dsp_thyw_Activity.this, "退回业务成功！",
						Toast.LENGTH_LONG).show();
				infoFile_.edit().dfflb_bjzt().put("失败").apply();
				Spcl_Dsp_thyw_Activity.this
						.finishOthers(Base_DetailActivity_.class);
				Spcl_Dsp_thyw_Activity.this
						.startActivity(new Intent(Spcl_Dsp_thyw_Activity.this,
								Base_DetailActivity_.class));

			}

		}

	};

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			// exitBy2Click();
			Intent intent = new Intent();
			intent.setClass(this, Base_DetailActivity_.class);
			startActivity(intent);
			finish();
		}
		return false;
	}

	public void initTitle() {
		if (Constants.title.equals(context.getResources().getString(
				R.string.yushouliyewu))) {
			rl_title.setBackgroundDrawable(getResources().getDrawable(
					R.drawable.tt_yuslyw));
		}
		if (Constants.title.equals(context.getResources().getString(
				R.string.shoujiandengji))) {

			rl_title.setBackgroundDrawable(getResources().getDrawable(
					R.drawable.tt_sjdj));

		}
		if (Constants.title.equals(context.getResources().getString(
				R.string.yishouliyewu))) {

			rl_title.setBackgroundDrawable(getResources().getDrawable(
					R.drawable.tt_yslyw));

		}
		if (Constants.title.equals(context.getResources().getString(
				R.string.buyushouliyewu))) {

			rl_title.setBackgroundDrawable(getResources().getDrawable(
					R.drawable.tt_byslyw));

		}
		if (Constants.title.equals(context.getResources().getString(
				R.string.dailurubiaodan))) {

			rl_title.setBackgroundDrawable(getResources().getDrawable(
					R.drawable.tt_dllbd));

		}
		if (Constants.title.equals(context.getResources().getString(
				R.string.yilurubiaodan))) {

			rl_title.setBackgroundDrawable(getResources().getDrawable(
					R.drawable.tt_yllbd));

		}
		if (Constants.title.equals(context.getResources().getString(
				R.string.daishenpi))) {

			rl_title.setBackgroundDrawable(getResources().getDrawable(
					R.drawable.tt_dsp));

		}
		if (Constants.title.equals(context.getResources().getString(
				R.string.yishenpi))) {

			rl_title.setBackgroundDrawable(getResources().getDrawable(
					R.drawable.tt_ysp));

		}
		if (Constants.title.equals(context.getResources().getString(
				R.string.yituihui))) {

			rl_title.setBackgroundDrawable(getResources().getDrawable(
					R.drawable.tt_yth));

		}
		if (Constants.title.equals(context.getResources().getString(
				R.string.daifafangliebiao))) {

			rl_title.setBackgroundDrawable(getResources().getDrawable(
					R.drawable.tt_dfflb));

		}
		if (Constants.title.equals(context.getResources().getString(
				R.string.yifafangliebiao))) {

			rl_title.setBackgroundDrawable(getResources().getDrawable(
					R.drawable.tt_yfflb));

		}
		if (Constants.title.equals(context.getResources().getString(
				R.string.daoqiweibanjieliebiao))) {

			rl_title.setBackgroundDrawable(getResources().getDrawable(
					R.drawable.tt_dqwbjlb));

		}
		if (Constants.title.equals(context.getResources().getString(
				R.string.yewudanganchaxun))) {

			rl_title.setBackgroundDrawable(getResources().getDrawable(
					R.drawable.tt_ywdacx));

		}
	}
}
