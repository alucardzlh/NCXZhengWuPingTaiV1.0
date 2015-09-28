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
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ZWPT.data.Constants;
import com.ZWPT.data.DataHelper;
import com.ZWPT.data.InfoFile_;
import com.ZWPT.data.entity.HandleResult;
import com.ZWPT.service.BaseDetail_ManagerService;
import com.googlecode.androidannotations.annotations.AfterViews;
import com.googlecode.androidannotations.annotations.Bean;
import com.googlecode.androidannotations.annotations.EActivity;
import com.googlecode.androidannotations.annotations.ViewById;
import com.googlecode.androidannotations.annotations.sharedpreferences.Pref;

@EActivity(R.layout.layout_spcl_dsp_ksyw)
public class Spcl_Dsp_ksyw_Activity extends BaseActivity {
	@Bean
	DataHelper dataHelper;
	@Pref
	InfoFile_ infoFile_;
	@ViewById
	TextView tvTitle_two_Name, edt_search_1, edt_search_2, edt_search_3,
			edt_search_4;
	@ViewById
	Button btn_list_1, btn_list_2;
	@ViewById
	RelativeLayout rl_title;

	@AfterViews
	void initView() {
		initTitle();
		if (infoFile_.serviceSubjectName().get() != null) {
			tvTitle_two_Name.setText(infoFile_.serviceSubjectName().get());
		}

		if (infoFile_.shengqingrenname().get() != null) {
			edt_search_1.setText(infoFile_.shengqingrenname().get());
		}

		if (infoFile_.shengqingriqi().get() != null) {
			edt_search_2.setText(infoFile_.shengqingriqi().get());
		}

		if (infoFile_.ZTYWkaishishijian().get() != null) {
			edt_search_3.setText(infoFile_.ZTYWkaishishijian().get());
		}

		if (infoFile_.ZTYWyuanyin().get() != null) {
			edt_search_4.setText(infoFile_.ZTYWyuanyin().get());
		}

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
				oMap.put("userName", infoFile_.infoUsername().get());
				list_1.add(oMap);
				baseDetail_ManagerService.doStartBizArchives(
						Spcl_Dsp_ksyw_Activity.this, 3, list_1);

				break;

			case R.id.btn_list_2:
				Spcl_Dsp_ksyw_Activity.this.finish();
				Spcl_Dsp_ksyw_Activity.this
						.startActivity(new Intent(Spcl_Dsp_ksyw_Activity.this,
								Base_DetailActivity_.class));
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
			if (paramHandleResult.getKsywSuccess().equals("success")) {
				Toast.makeText(Spcl_Dsp_ksyw_Activity.this, "开始业务成功！",
						Toast.LENGTH_LONG).show();
				Spcl_Dsp_ksyw_Activity.this.finishOthers();
				Spcl_Dsp_ksyw_Activity.this
						.startActivity(new Intent(Spcl_Dsp_ksyw_Activity.this,
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
