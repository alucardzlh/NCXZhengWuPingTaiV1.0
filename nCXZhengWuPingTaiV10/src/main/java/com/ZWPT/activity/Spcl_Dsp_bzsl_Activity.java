package com.ZWPT.activity;

import java.util.ArrayList;
import java.util.Calendar;
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

@EActivity(R.layout.layout_spcl_dsp_bzsl)
public class Spcl_Dsp_bzsl_Activity extends BaseActivity {
	@Bean
	DataHelper dataHelper;
	@Pref
	InfoFile_ infoFile_;
	@ViewById
	TextView tvTitle_two_Name, edt_search_1, edt_search_2, edt_search_3,
			edt_search_4, edt_search_5, edt_search_6;
	@ViewById
	Button btn_list_1, btn_list_2;
	@ViewById
	RelativeLayout rl_title;

	@AfterViews
	void initView() {
		initTitle();
		tvTitle_two_Name.setText(infoFile_.serviceSubjectName().get());
		edt_search_1.setText(checkStringValue(infoFile_.shengqingrenname()
				.get()));
		edt_search_2.setText(checkStringValue(infoFile_.shengqingriqi().get()));
		edt_search_3.setText(checkStringValue(infoFile_.bz_sqr_phone().get()));
		edt_search_4.setText(checkStringValue(infoFile_.bz_slczy().get()));
		edt_search_5.setText(checkStringValue(infoFile_.bz_msg().get()));
		edt_search_6.setText(checkStringValue(infoFile_.bz_yy().get()));
		// Calendar calendar = Calendar.getInstance();
		// String time=calendar.get(Calendar.YEAR)+"-"+ //得到年
		// formatTime(calendar.get(Calendar.MONTH)+1)+"-"+//month加一 //月
		// formatTime(calendar.get(Calendar.DAY_OF_MONTH));
		// edt_search_5.setText("尊敬的"+infoFile_.shengqingrenname().get()+":您好！您于"+infoFile_.shengqingriqi().get()+"申办的"+infoFile_.serviceSubjectName().get()+"业务，需补正。请您携带相关材料及证件，到莲塘镇府前路居委会(南昌县莲塘镇团结中路50号)进行业务补正。详细信息请致电0791-85710000或登录到http://www.ncx.gov.cn/进行查询。感谢您的支持。("+time+")");
		btn_list_1.setOnClickListener(onClickListener);
		btn_list_2.setOnClickListener(onClickListener);
	}

	// private String formatTime(int t){
	// return t>=10? ""+t:"0"+t;//三元运算符 t>10时取 ""+t
	// }
	private String checkStringValue(String str) {
		if (str.equals("null") || str == null || str.equals("")) {
			str = "-";
		}
		return str;
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
				baseDetail_ManagerService.doCorrectAccept(
						Spcl_Dsp_bzsl_Activity.this, 6, list_1);
				break;
			case R.id.btn_list_2:
				Spcl_Dsp_bzsl_Activity.this
						.startActivity(new Intent(Spcl_Dsp_bzsl_Activity.this,
								Base_DetailActivity_.class));
				Spcl_Dsp_bzsl_Activity.this.finish();
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
			if (paramHandleResult.getBzjsSuccess().equals("success")) {
				Toast.makeText(Spcl_Dsp_bzsl_Activity.this, "补正受理成功！",
						Toast.LENGTH_LONG).show();
				Spcl_Dsp_bzsl_Activity.this.finish();
				Spcl_Dsp_bzsl_Activity.this
						.startActivity(new Intent(Spcl_Dsp_bzsl_Activity.this,
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
