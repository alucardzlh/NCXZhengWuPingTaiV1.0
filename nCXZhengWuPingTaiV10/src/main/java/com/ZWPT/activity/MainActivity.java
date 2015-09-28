package com.ZWPT.activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.ZWPT.data.Constants;
import com.ZWPT.data.DataHelper;
import com.ZWPT.data.InfoFile_;
import com.ZWPT.data.entity.HandleResult;
import com.ZWPT.service.CountsOfMainManagerService;
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
@EActivity(R.layout.layout_main_two)
public class MainActivity extends BaseActivity {

	@Bean
	DataHelper dataHelper;
	@Pref
	InfoFile_ infoFile_;
	@ViewById
	TextView num_1, num_2, num_3, num_4;
	@ViewById
	ImageButton main_yslzs, main_dspzs, main_dffzs, main_dqwbjzs;
	String num1 = "", num2 = "", num3 = "", num4 = "";
	List<Map<String, Object>> list = new ArrayList<Map<String, Object>>(); // 各事项数量列表

	@AfterViews
	void initView() {
		// initNums();
		main_yslzs.setOnClickListener(omClickListener);
		main_dspzs.setOnClickListener(omClickListener);
		main_dffzs.setOnClickListener(omClickListener);
		main_dqwbjzs.setOnClickListener(omClickListener);
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		initLeftMenu();
		List<Map<String, Object>> paramlist = new ArrayList<Map<String, Object>>();
		Map<String, Object> map_account = new HashMap<String, Object>();
		map_account.put("account", Constants.account);
		map_account.put("serviceSubjectId", "1");
		paramlist.add(map_account);
		mCountsOfMainManagerService.getCounts(MainActivity.this, 1, paramlist);
		// initNums();
	}

	public void initNums() {
		num1 = infoFile_.numOfYslzs().get(); // 从SharedPreferences获取预受理总数
		num2 = infoFile_.numOfDspzs().get(); // 从SharedPreferences获取待审批总数
		num3 = infoFile_.numOfDffzs().get(); // 从SharedPreferences获取待发放总数
		num4 = infoFile_.numOfDqwbjzs().get(); // 从SharedPreferences获取到期未办结总数
		num_1.setText(num1);
		num_2.setText(num2);
		num_3.setText(num3);
		num_4.setText(num4);
	}

	private OnClickListener omClickListener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.main_yslzs:
				// infoFile_.edit().numOfYslzs().put("0").apply();
				Constants.title = MainActivity.this.getResources().getString(
						R.string.yushouliyewu);
				startListActivity();
				break;
			case R.id.main_dspzs:
				// infoFile_.edit().numOfDspzs().put("0").apply();
				Constants.title = MainActivity.this.getResources().getString(
						R.string.daishenpi);
				startListActivity();
				break;
			case R.id.main_dffzs:
				// infoFile_.edit().numOfDffzs().put("0").apply();
				Constants.title = MainActivity.this.getResources().getString(
						R.string.daifafangliebiao);
				startListActivity();
				break;
			case R.id.main_dqwbjzs:
				// infoFile_.edit().numOfDqwbjzs().put("0").apply();
				Constants.title = MainActivity.this.getResources().getString(
						R.string.daoqiweibanjieliebiao);
				startListActivity();
				break;
			default:
				break;
			}
		}
	};

	public void startListActivity() {
		Intent Intent = new Intent();
		Intent.setClass(context, Base_ListActivity_.class);
		startActivity(Intent);
		this.finish();
	}

	private Handler callbackHandler = new Handler() {
		public void handleMessage(android.os.Message msg) {
		};
	};

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			// exitBy2Click();
			this.finishOthers();
			Intent Intent = new Intent();
			Intent.setClass(context, First_Main_Activity_.class);
			startActivity(Intent);
			this.finish();
		}
		return true;
	}

	private CountsOfMainManagerService mCountsOfMainManagerService = new CountsOfMainManagerService() {

		@Override
		public void handlerLoginInfo(Context context,
				HandleResult paramHandleResult, int paramInt) {
			switch (paramInt) {
			case 1:
				if (paramHandleResult.getGetCounts_archives_ResultSuccess()
						.equals("success")) {
					list = paramHandleResult.getList_counts_archives();
					for (Map<String, Object> map : list) {
						if (map.get("name").equals("预受理")) {
							infoFile_.edit().numOfYslzs()
									.put((String) map.get("count")).apply();
						} else if (map.get("name").equals("待审批")) {
							infoFile_.edit().numOfDspzs()
									.put((String) map.get("count")).apply();
						} else if (map.get("name").equals("待发放")) {
							infoFile_.edit().numOfDffzs()
									.put((String) map.get("count")).apply();
						} else if (map.get("name").equals("到期未办结")) {
							infoFile_.edit().numOfDqwbjzs()
									.put((String) map.get("count")).apply();
						}

					}
					initNums();

				}
				if (paramHandleResult.getAccountID() != null) {
					infoFile_.edit().infoUserId()
							.put(paramHandleResult.getAccountID()).apply();// 获取用户ID
				}
				break;

			default:
				break;
			}

		}
	};

}
