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
import android.widget.EditText;
import android.widget.LinearLayout;
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

@EActivity(R.layout.layout_yslyw_deny)
public class Wssq_Yslyw_Deny_Activity extends BaseActivity {
	@Bean
	DataHelper dataHelper;
	@Pref
	InfoFile_ infoFile_;
	@ViewById
	LinearLayout ll_detail_btn_1;
	@ViewById
	TextView tvTitle_two_Name, tv_list_item_1, tv_list_item_2, tv_list_item_3,
			tv_list_item_4, tv_list_item_5;
	@ViewById
	EditText edt_search_1;
	@ViewById
	Button btn_FanHui, btn_ShouLi, btn_BuShouLi;

	@AfterViews
	void initView() {
		ll_detail_btn_1.setVisibility(View.VISIBLE);
		btn_ShouLi.setVisibility(View.GONE);
		btn_FanHui.setText("返回");
		btn_BuShouLi.setText("完成处理");
		btn_FanHui.setOnClickListener(OnClickListener);
		btn_BuShouLi.setOnClickListener(OnClickListener);
		tvTitle_two_Name.setText(infoFile_.serviceSubjectName().get());
		tv_list_item_1.setText(infoFile_.wssq_wsyslh().get());
		tv_list_item_2.setText(infoFile_.shengqingrenname().get());
		tv_list_item_3.setText("-");
		tv_list_item_4.setText("-");
		tv_list_item_5.setText(infoFile_.shengqingriqi().get());
	}

	private OnClickListener OnClickListener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.btn_FanHui:
				Intent intent = new Intent();
				intent.setClass(Wssq_Yslyw_Deny_Activity.this,
						Base_DetailActivity_.class);
				startActivity(intent);
				Wssq_Yslyw_Deny_Activity.this.finish();
				break;
			case R.id.btn_BuShouLi:
				if (edt_search_1.getText().toString().equals("")) {
					Toast.makeText(Wssq_Yslyw_Deny_Activity.this,
							"不予受理原因不能为空！", Toast.LENGTH_LONG).show();
				} else {
					List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
					Map<String, Object> oMap = new HashMap<String, Object>();
					oMap.put("userAccountId", infoFile_.infoUserId().get());
					String id = infoFile_.bizArchivesId().get();
					oMap.put("bizArchivesId", id);
					oMap.put("handleSuggestion", edt_search_1.getText()
							.toString());
					list.add(oMap);
					baseDetail_ManagerService.noAccept(context, 1, list);
				}

				break;

			default:
				break;
			}

		}
	};

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			// exitBy2Click();
			Intent intent = new Intent();
			intent.setClass(this, Base_ListActivity_.class);
			startActivity(intent);
			finish();
		}
		return false;
	}

	private BaseDetail_ManagerService baseDetail_ManagerService = new BaseDetail_ManagerService() {

		@Override
		public void handlerBaseDetailInfo(Context context,
				HandleResult paramHandleResult, int paramInt) {
			switch (paramInt) {
			case 1:
				if (paramHandleResult.getDenySuccess().equals("success")) {
					Toast.makeText(Wssq_Yslyw_Deny_Activity.this, "提交成功！",
							Toast.LENGTH_LONG).show();
					Intent intent1 = new Intent();
					intent1.setClass(Wssq_Yslyw_Deny_Activity.this,
							Base_ListActivity_.class);
					startActivity(intent1);
					Wssq_Yslyw_Deny_Activity.this.finish();
				}
				break;
			default:
				break;
			}
		}
	};
}
