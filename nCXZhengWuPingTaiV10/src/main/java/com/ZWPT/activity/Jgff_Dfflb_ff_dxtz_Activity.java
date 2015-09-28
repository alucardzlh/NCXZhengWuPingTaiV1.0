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
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ZWPT.data.DataHelper;
import com.ZWPT.data.InfoFile_;
import com.ZWPT.data.entity.HandleResult;
import com.ZWPT.service.BaseDetail_ManagerService;
import com.googlecode.androidannotations.annotations.AfterViews;
import com.googlecode.androidannotations.annotations.Bean;
import com.googlecode.androidannotations.annotations.EActivity;
import com.googlecode.androidannotations.annotations.ViewById;
import com.googlecode.androidannotations.annotations.sharedpreferences.Pref;

@EActivity(R.layout.layout_jgff_dfflb_ff_dxtz)
public class Jgff_Dfflb_ff_dxtz_Activity extends BaseActivity {
	@Bean
	DataHelper dataHelper;
	@Pref
	InfoFile_ infoFile_;
	@ViewById
	TextView tvTitle_two_Name, tv_list_item_1, tv_list_item_2, tv_list_item_3,
			tv_list_item_4;
	@ViewById
	EditText edt_search_1, edt_search_2;
	@ViewById
	Button btn_list_1, btn_list_2;
	@ViewById
	CheckBox cb_jgff;
	@ViewById
	RadioGroup rg;
	@ViewById
	RadioButton first, second;
	@ViewById
	RelativeLayout rl_title;
	String sendDelivery = "2";

	@AfterViews
	void initView() {
		rl_title.setBackgroundDrawable(getResources().getDrawable(
				R.drawable.tt_dfflb));
		tvTitle_two_Name.setText(infoFile_.serviceSubjectName().get());
		if (infoFile_.shengqingrenname().get() != null
				&& !infoFile_.shengqingrenname().get().equals("")
				&& infoFile_.shengqingriqi().get() != null
				&& !infoFile_.shengqingriqi().get().equals("")
				&& infoFile_.banjieriqi().get() != null
				&& !infoFile_.banjieriqi().get().equals("")) {
			tv_list_item_1.setText(infoFile_.shengqingrenname().get());
			tv_list_item_2.setText(infoFile_.shengqingriqi().get());
			tv_list_item_3.setText(infoFile_.banjieriqi().get());
		}
		tv_list_item_4.setText(infoFile_.dxtz_beizhu().get());
		Calendar calendar = Calendar.getInstance();
		String time = calendar.get(Calendar.YEAR) + "-" + // 得到年
				formatTime(calendar.get(Calendar.MONTH) + 1) + "-" + // month加一
																		// //月
				formatTime(calendar.get(Calendar.DAY_OF_MONTH));
		edt_search_1.setText(infoFile_.serviceTargetPhone().get());
		edt_search_2
				.setText("尊敬的"
						+ infoFile_.shengqingrenname().get()
						+ ":您好！很抱歉的通知您，您于"
						+ infoFile_.shengqingriqi().get()
						+ "申办的【"
						+ infoFile_.serviceSubjectName().get()
						+ "】业务，办理失败。失败原因是：“"
						+ "请您携带相关材料及证件，到莲塘镇府前路居委会(南昌县莲塘镇团结中路50号)进行业务补正。详细信息请致电0791-85710000或登录到http://www.ncx.gov.cn/进行查询。感谢您的支持。("
						+ time + ")");
		btn_list_1.setOnClickListener(onClickListener);
		btn_list_2.setOnClickListener(onClickListener);
		if (infoFile_.dfflb_bjzt().get().equals("成功")) {
			first.setChecked(true);
		} else {
			second.setChecked(true);
		}
		second.setClickable(false);
		first.setClickable(false);
		cb_jgff.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				if (isChecked) {
					sendDelivery = "1";
				} else {
					sendDelivery = "2";
				}

			}

		});

	}

	private String formatTime(int t) {
		return t >= 10 ? "" + t : "0" + t;// 三元运算符 t>10时取 ""+t
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
				oMap.put("messageContent", edt_search_2.getText().toString());
				oMap.put("sendDelivery", sendDelivery);
				oMap.put("serviceTargetPhone", edt_search_1.getText()
						.toString());
				list_1.add(oMap);
				baseDetail_ManagerService.doSendSM(
						Jgff_Dfflb_ff_dxtz_Activity.this, 7, list_1);
				break;
			case R.id.btn_list_2:
				Jgff_Dfflb_ff_dxtz_Activity.this.finish();
				Jgff_Dfflb_ff_dxtz_Activity.this.startActivity(new Intent(
						Jgff_Dfflb_ff_dxtz_Activity.this,
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
			if (paramHandleResult.getFsdxSuccess().equals("success")) {
				Toast.makeText(Jgff_Dfflb_ff_dxtz_Activity.this, "发送成功！",
						Toast.LENGTH_LONG).show();
				Jgff_Dfflb_ff_dxtz_Activity.this.finishOthers();
				Jgff_Dfflb_ff_dxtz_Activity.this.startActivity(new Intent(
						Jgff_Dfflb_ff_dxtz_Activity.this,
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
}
