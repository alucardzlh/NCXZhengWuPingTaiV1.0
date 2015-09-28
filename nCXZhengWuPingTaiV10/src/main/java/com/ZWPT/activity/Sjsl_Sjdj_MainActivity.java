package com.ZWPT.activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.RadioGroup.OnCheckedChangeListener;

import com.ZWPT.adapter.Sjsl_custom_ListAdapter;
import com.ZWPT.data.Constants;
import com.ZWPT.data.DataHelper;
import com.ZWPT.data.InfoFile_;
import com.ZWPT.data.entity.HandleResult;
import com.ZWPT.service.Sjdj_ListManagerService;
import com.googlecode.androidannotations.annotations.AfterViews;
import com.googlecode.androidannotations.annotations.Bean;
import com.googlecode.androidannotations.annotations.EActivity;
import com.googlecode.androidannotations.annotations.ViewById;
import com.googlecode.androidannotations.annotations.sharedpreferences.Pref;

/**
 * 收件登记主界面，包括一级列表界面和二级列表界面
 * 
 * @author Administrator
 * 
 */
@EActivity(R.layout.layout_sjdj_custom_list)
public class Sjsl_Sjdj_MainActivity extends BaseActivity {
	@Bean
	DataHelper dataHelper;
	@Pref
	InfoFile_ infoFile_;
	@ViewById
	RelativeLayout rl_title;
	@ViewById
	RadioGroup radiogroup;
	@ViewById
	RadioButton rb1, rb2, rb3;
	@ViewById
	ImageButton title_search;
	@ViewById
	ListView lv_base;
	@ViewById
	RelativeLayout top;
	@ViewById
	Button btn_sjdj_search;
	@ViewById
	EditText etSearch;
	@ViewById
	ImageView ivDeleteText;
	List<Map<String, Object>> resultGroup = null;// 从服务器获取到的事项列表
	List<String> list_name_first = null; // 从服务器获取到的一级事项name
	List<String> list_id_first = null; // 从服务器获取到的一级事项id
	List<String> list_name_second = null; // 从服务器获取到的二级事项name
	List<String> list_id_second = null; // 从服务器获取到的二级事项id
	List<String> list_serviceCode = null; // 从服务器获取到的二级事项服务码
	List<String> list_name_search = null; // 从服务器获取到的搜索结果name
	List<String> list_id_search = null; // 从服务器获取到的搜索结果id
	List<String> list_serviceCode_search = null; // 从服务器获取到的搜索结果事项码

	@AfterViews
	void initView() {
		rl_title.setBackgroundDrawable(getResources().getDrawable(
				R.drawable.tt_sjdj));
		title_search.setVisibility(View.VISIBLE);
		title_search.setOnClickListener(listner);
		ivDeleteText.setOnClickListener(listner);
		btn_sjdj_search.setOnClickListener(listner);
		radiogroup.setOnCheckedChangeListener(onCheckedChangeListener);
		lv_base.setOnItemClickListener(listener);
		etSearch.addTextChangedListener(textlistner);
		radiogroup.setVisibility(View.GONE);
		top.setVisibility(View.VISIBLE);
		this.finishOthers();
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Constants.SHOW_SJDJ_SECOND_LIST = false;
		Constants.SHOW_SJDJ_SEARCH_RESULT = false;
		initRb1Data();
	}

	private OnCheckedChangeListener onCheckedChangeListener = new OnCheckedChangeListener() {

		public void onCheckedChanged(RadioGroup group, int checkedId) {
			Constants.SHOW_SJDJ_SECOND_LIST = false;
			Constants.SHOW_SJDJ_SEARCH_RESULT = false;
			switch (checkedId) {
			case R.id.rb1:
				rb1.setBackgroundDrawable(getResources().getDrawable(
						R.drawable.btn_custom));
				rb2.setBackgroundDrawable(null);
				rb3.setBackgroundDrawable(null);

				if (list_name_first != null) {
					lv_base.setAdapter(new Sjsl_custom_ListAdapter(context,
							list_name_first));
					lv_base.setOnItemClickListener(listener);
				} else {
					initRb1Data();
				}
				break;
			case R.id.rb2:
				rb2.setBackgroundDrawable(getResources().getDrawable(
						R.drawable.btn_custom));
				rb1.setBackgroundDrawable(null);
				rb3.setBackgroundDrawable(null);
				break;
			case R.id.rb3:
				rb3.setBackgroundDrawable(getResources().getDrawable(
						R.drawable.btn_custom));
				rb2.setBackgroundDrawable(null);
				rb1.setBackgroundDrawable(null);
				break;
			}
		}

	};

	private void initRb1Data() {
		mSjdj_ListManagerService.getFirstList(context, 1);
	}

	private OnItemClickListener listener = new AdapterView.OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			if (Constants.SHOW_SJDJ_SECOND_LIST) {
				Constants.fromYsl = false;
				Intent mIntent = new Intent();

				mIntent.setClass(Sjsl_Sjdj_MainActivity.this,
						Sjsl_Sjdj_shouli_Activity_.class);
				// mIntent.putExtra("serviceSubjectId",
				// list_id_second.get(arg2));
				// mIntent.putExtra("serviceSubjectName",
				// list_name_second.get(arg2));
				infoFile_.edit().serviceSubjectId()
						.put(list_id_second.get(arg2)).serviceSubjectName()
						.put(list_name_second.get(arg2)).apply();

				startActivity(mIntent);
				// finish();
			} else if (Constants.SHOW_SJDJ_SEARCH_RESULT) {
				Constants.fromYsl = false;
				Intent mIntent = new Intent();

				mIntent.setClass(Sjsl_Sjdj_MainActivity.this,
						Sjsl_Sjdj_shouli_Activity_.class);
				// mIntent.putExtra("serviceSubjectId",
				// list_id_search.get(arg2));
				// mIntent.putExtra("serviceSubjectName",
				// list_name_search.get(arg2));
				infoFile_.edit().serviceSubjectId()
						.put(list_id_search.get(arg2)).serviceSubjectName()
						.put(list_name_search.get(arg2)).apply();
				startActivity(mIntent);
			}

			else {
				Constants.SHOW_SJDJ_SECOND_LIST = true;
				Constants.SHOW_SJDJ_SEARCH_RESULT = false;
				List<Map<String, Object>> list_second_canshu = new ArrayList<Map<String, Object>>();
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("account", Constants.account);
				map.put("adminOrgId", list_id_first.get(arg2));
				list_second_canshu.add(map);
				mSjdj_ListManagerService.getSecondList(context, 2,
						list_second_canshu);

			}

		}

	};

	private Sjdj_ListManagerService mSjdj_ListManagerService = new Sjdj_ListManagerService() {

		@Override
		public void handlerLoginInfo(Context context,
				HandleResult paramHandleResult, int paramInt) {
			switch (paramInt) {
			case 1:
				if (paramHandleResult.getGetSjdj_First_ListSuccess().equals(
						"success")) {
					resultGroup = new ArrayList<Map<String, Object>>();
					list_name_first = new ArrayList<String>();
					list_id_first = new ArrayList<String>();
					resultGroup = paramHandleResult.getList_sjdj_first();
					for (int i = 0; i < resultGroup.size(); i++) {
						list_name_first.add((String) resultGroup.get(i).get(
								"name"));
						list_id_first
								.add((String) resultGroup.get(i).get("id"));
					}
					lv_base.setAdapter(new Sjsl_custom_ListAdapter(context,
							list_name_first));
					lv_base.setOnItemClickListener(listener);
				} else if (paramHandleResult.getGetSjdj_First_ListSuccess()
						.equals("fail")) {
				}

				break;
			case 2:
				if (paramHandleResult.getGetSjdj_Second_ListSuccess().equals(
						"success")) {
					resultGroup = new ArrayList<Map<String, Object>>();
					list_name_second = new ArrayList<String>();
					list_id_second = new ArrayList<String>();
					list_serviceCode = new ArrayList<String>();
					resultGroup = paramHandleResult.getList_sjdj_second();
					for (int i = 0; i < resultGroup.size(); i++) {
						list_name_second.add((String) resultGroup.get(i).get(
								"name"));
						list_id_second.add((String) resultGroup.get(i)
								.get("id"));
						list_serviceCode.add((String) resultGroup.get(i).get(
								"adminOrgId"));
					}
					lv_base.setAdapter(new Sjsl_custom_ListAdapter(context,
							list_name_second));
					lv_base.setOnItemClickListener(listener);
				} else if (paramHandleResult.getGetSjdj_First_ListSuccess()
						.equals("fail")) {
				}
				break;
			case 3:
				if (paramHandleResult.getGetSjdj_Search_ResultSuccess().equals(
						"success")) {
					resultGroup = new ArrayList<Map<String, Object>>();
					list_name_search = new ArrayList<String>();
					list_id_search = new ArrayList<String>();
					list_serviceCode_search = new ArrayList<String>();
					resultGroup = paramHandleResult
							.getList_sjdj_search_result();
					for (int i = 0; i < resultGroup.size(); i++) {
						list_name_search.add((String) resultGroup.get(i).get(
								"name"));
						list_id_search.add((String) resultGroup.get(i)
								.get("id"));
						list_serviceCode_search.add((String) resultGroup.get(i)
								.get("adminOrgId"));
					}

					lv_base.setAdapter(new Sjsl_custom_ListAdapter(context,
							list_name_search));
					lv_base.setOnItemClickListener(listener);

				} else {

				}
				break;
			default:
				break;
			}
		}
	};

	private OnClickListener listner = new OnClickListener() {

		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.title_search:
				if (top.getVisibility() == View.GONE) {
					Animation animation = AnimationUtils.loadAnimation(
							Sjsl_Sjdj_MainActivity.this,
							R.anim.animation_sjdj_search);
					animation.setAnimationListener(new AnimationListener() {
						@Override
						public void onAnimationStart(Animation animation) {
						}

						@Override
						public void onAnimationRepeat(Animation animation) {
						}

						@Override
						public void onAnimationEnd(Animation animation) {
							top.setVisibility(View.VISIBLE);
						}
					});
					Animation animation1 = AnimationUtils.loadAnimation(
							Sjsl_Sjdj_MainActivity.this,
							R.anim.animation_sjdj_search1);
					animation1.setAnimationListener(new AnimationListener() {
						@Override
						public void onAnimationStart(Animation animation) {
						}

						@Override
						public void onAnimationRepeat(Animation animation) {
						}

						@Override
						public void onAnimationEnd(Animation animation) {
							radiogroup.setVisibility(View.GONE);
						}
					});
					radiogroup.startAnimation(animation1);
					top.startAnimation(animation);

				} else {
					Animation animation = AnimationUtils.loadAnimation(
							Sjsl_Sjdj_MainActivity.this,
							R.anim.animation_sjdj_search);
					animation.setAnimationListener(new AnimationListener() {
						@Override
						public void onAnimationStart(Animation animation) {
						}

						@Override
						public void onAnimationRepeat(Animation animation) {
						}

						@Override
						public void onAnimationEnd(Animation animation) {
							radiogroup.setVisibility(View.VISIBLE);
						}
					});
					Animation animation1 = AnimationUtils.loadAnimation(
							Sjsl_Sjdj_MainActivity.this,
							R.anim.animation_sjdj_search1);
					animation1.setAnimationListener(new AnimationListener() {
						@Override
						public void onAnimationStart(Animation animation) {
						}

						@Override
						public void onAnimationRepeat(Animation animation) {
						}

						@Override
						public void onAnimationEnd(Animation animation) {
							top.setVisibility(View.GONE);
						}
					});
					radiogroup.startAnimation(animation);
					top.startAnimation(animation1);
				}
				break;
			case R.id.btn_sjdj_search:
				Constants.SHOW_SJDJ_SECOND_LIST = false;
				Constants.SHOW_SJDJ_SEARCH_RESULT = true;
				List<Map<String, Object>> list_search_canshu = new ArrayList<Map<String, Object>>();
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("account", Constants.account);
				map.put("content", etSearch.getText().toString());
				list_search_canshu.add(map);
				mSjdj_ListManagerService.getSearchResult(context, 3,
						list_search_canshu);

				break;
			case R.id.ivDeleteText:
				etSearch.setText("");
				break;
			default:
				break;
			}

		}
	};

	private TextWatcher textlistner = new TextWatcher() {

		@Override
		public void onTextChanged(CharSequence s, int start, int before,
				int count) {
		}

		@Override
		public void beforeTextChanged(CharSequence s, int start, int count,
				int after) {
		}

		@Override
		public void afterTextChanged(Editable s) {
			if (s.length() == 0) {
				ivDeleteText.setVisibility(View.GONE);
			} else {
				ivDeleteText.setVisibility(View.VISIBLE);
			}
		}
	};

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			if (Constants.SHOW_SJDJ_SECOND_LIST
					|| Constants.SHOW_SJDJ_SEARCH_RESULT) {
				lv_base.setAdapter(new Sjsl_custom_ListAdapter(context,
						list_name_first));
				lv_base.setOnItemClickListener(listener);
				Constants.SHOW_SJDJ_SECOND_LIST = false;
				Constants.SHOW_SJDJ_SEARCH_RESULT = false;
			} else {

				exitBy2Click();
			}
		}
		return false;
	}
}
