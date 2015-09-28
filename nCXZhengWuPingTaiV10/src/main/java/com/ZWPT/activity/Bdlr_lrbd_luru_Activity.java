package com.ZWPT.activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ZWPT.Utils.StringUtil;
import com.ZWPT.adapter.Bdlr_Dlrbd_Luru_Form_Adapter;
import com.ZWPT.adapter.Bdlr_Dlrbd_Luru_Form_Adapter.MyCallBack;
import com.ZWPT.data.Constants;
import com.ZWPT.data.InfoFile_;
import com.ZWPT.data.entity.HandleResult;
import com.ZWPT.service.BdlrManagerService;
import com.ZWPT.view.FormFillView;
import com.googlecode.androidannotations.annotations.AfterViews;
import com.googlecode.androidannotations.annotations.EActivity;
import com.googlecode.androidannotations.annotations.ViewById;
import com.googlecode.androidannotations.annotations.sharedpreferences.Pref;

/**
 * 表单录入
 * 
 * @author yuhuihui
 * @date 2014-6-25
 * 
 */
@EActivity(R.layout.layout_dlrbd_lrbd_luru)
public class Bdlr_lrbd_luru_Activity extends BaseActivity implements
		OnClickListener {
	@Pref
	InfoFile_ infoFile_;
	@ViewById
	RelativeLayout rl_title;// 标题栏
	// @ViewById ImageButton title_search;//搜索按钮
	@ViewById
	TextView tvTitle_two_Name;// 二级标题的显示文本
	// @ViewById
	// ListView lv_base;
	@ViewById
	LinearLayout ll_form;
	@ViewById
	Button btn_list_2, btn_list_3;
	List<Map<String, Object>> list;
	Bdlr_Dlrbd_Luru_Form_Adapter adapter;

	String shenqingrenName = "";
	String shenqingrenID = "";

	@AfterViews
	void initView() {
		rl_title.setBackgroundDrawable(getResources().getDrawable(
				R.drawable.tt_dllbd));
		tvTitle_two_Name.setText(infoFile_.serviceSubjectName().get());
		btn_list_2.setOnClickListener(onclick);
		btn_list_3.setOnClickListener(onclick);

		if (infoFile_.serviceSubjectName().get() != null) {
			shenqingrenName = infoFile_.shengqingrenname().get();
		}
		if (infoFile_.serviceSubjectId().get() != null) {
			shenqingrenID = infoFile_.shengqingrenCardId().get();
		}
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO 自动生成的方法存根
		super.onCreate(savedInstanceState);
		List<Map<String, Object>> paramlist = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("serviceSubjectName",
				StringUtil.replaceSpace(infoFile_.serviceSubjectName().get()));
		paramlist.add(map);
		blBdlrManagerService.getServiceSubjectForm(this, 1, paramlist);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {

		default:
			break;
		}
	}

	MyCallBack callBack = new MyCallBack() {

		@Override
		public void callback(String s, String key, int position) {
			// TODO 自动生成的方法存根
			list.get(position).put(key, s);
			// adapter.notifyDataSetChanged();
		}
	};

	private BdlrManagerService blBdlrManagerService = new BdlrManagerService() {

		@Override
		public void handlerLoginInfo(Context context,
				HandleResult paramHandleResult, int paramInt) {
			switch (paramInt) {
			case 1:
				if (paramHandleResult.getGetBdlr_getServiceSubjectFormSuccess()
						.equals("success")) {
					list = new ArrayList<Map<String, Object>>();
					list = paramHandleResult.getList_bdlr_form();
					// adapter = new Bdlr_Dlrbd_Luru_Form_Adapter(context, list,
					// callBack);
					// lv_base.setAdapter(adapter);
					FormFillView fillView = new FormFillView(context, list,
							callBack, shenqingrenName, shenqingrenID);
					ll_form.addView(fillView.getView());
					// CommUtil.setListViewHeightBasedOnChildren(lv_base);
				}

				break;
			case 2:
				if (paramHandleResult.getGetSubmitServiceSubjectFormSuccess()
						.equals("success")) {
					String str = "";
					if (paramHandleResult.getSubmitBdlrFormContent().contains(
							"更新成功")) {
						str = paramHandleResult.getSubmitBdlrFormContent()
								.replace("更新成功:", "");
					}
					Toast.makeText(Bdlr_lrbd_luru_Activity.this, "录入成功！",
							Toast.LENGTH_LONG).show();
					Intent intent = new Intent();
					intent.setClass(Bdlr_lrbd_luru_Activity.this,
							Base_DetailActivity_.class);
					infoFile_.edit().bizArchivesId().put(str).apply();
					infoFile_.edit().bdlr_ywlsh()
							.put(infoFile_.baselist_yewuliushuihao().get())
							.apply();
					Constants.title = context.getResources().getString(
							R.string.yilurubiaodan);
					Bdlr_lrbd_luru_Activity.this.startActivity(intent);
					Bdlr_lrbd_luru_Activity.this.finish();
				}
				break;
			default:
				break;
			}
		}
	};

	public View.OnClickListener onclick = new OnClickListener() {

		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.btn_list_2:
				Bdlr_lrbd_luru_Activity.this
						.startActivity(new Intent(Bdlr_lrbd_luru_Activity.this,
								Base_ListActivity_.class));
				Bdlr_lrbd_luru_Activity.this.finish();
				break;
			case R.id.btn_list_3:
				StringBuffer str = new StringBuffer();
				String str_ywlsh = infoFile_.baselist_yewuliushuihao().get();
				str.append("<?xml version='1.0' encoding='utf-8'?>"
						+ "<case><ZsoftInfo><Account><![CDATA["
						+ infoFile_.infoUsername().get().replace(" ", "")
						+ "]]></Account><Ywlsh><![CDATA["
						+ str_ywlsh.replace(" ", "")
						+ "]]></Ywlsh><Ywbd><![CDATA[{");
				for (int i = 0; i < list.size(); i++) {
					// str.append(list.get(i).get("name")+":");
					if (list.get(i).get("nonEmpty").equals("false")) {
						if (list.get(i).get("name").toString().contains("身份证号")) {
							// EditText edt = (EditText) lv_base.getChildAt(i)
							// .findViewById(R.id.edt_job);
							String s = (String) list.get(i).get("value");
							if (!StringUtil.IDCardValidate(s).equals("")) {
								Toast.makeText(Bdlr_lrbd_luru_Activity.this,
										StringUtil.IDCardValidate(s),
										Toast.LENGTH_LONG).show();
								return;
							}
						}
						if (list.get(i).get("controlType").toString()
								.equals("DATEFIELD")) {
							// TextView tv = (TextView) lv_base.getChildAt(i)
							// .findViewById(R.id.tv_search_2);
							String s = (String) list.get(i).get("value");
							if (StringUtil.isBlank(s)) {
								Toast.makeText(Bdlr_lrbd_luru_Activity.this,
										list.get(i).get("name") + "不能为空！",
										Toast.LENGTH_LONG).show();
								return;
							} else {
								if (i == 0) {
									str.append(list.get(i).get("columnName")
											+ "=" + s);
								} else {
									str.append(","
											+ list.get(i).get("columnName")
											+ "=" + s);
								}
							}
						} else if (list.get(i).get("controlType").toString()
								.equals("TEXTBOX")) {
							// EditText edt = (EditText) lv_base.getChildAt(i)
							// .findViewById(R.id.edt_job);
							String s = (String) list.get(i).get("value");
							if (StringUtil.isBlank(s)) {
								Toast.makeText(Bdlr_lrbd_luru_Activity.this,
										list.get(i).get("name") + "不能为空！",
										Toast.LENGTH_LONG).show();
								return;
							} else {
								if (i == 0) {
									str.append(list.get(i).get("columnName")
											+ "=" + s);
								} else {
									str.append(","
											+ list.get(i).get("columnName")
											+ "=" + s);
								}
							}
						} else if (list.get(i).get("controlType")
								.equals("ADDRESS")) {
							// TextView tv = (TextView) lv_base.getChildAt(i)
							// .findViewById(R.id.tv_search_2);
							String s = (String) list.get(i).get("codeBText");
							if (StringUtil.isBlank(s)) {
								Toast.makeText(Bdlr_lrbd_luru_Activity.this,
										list.get(i).get("name") + "不能为空！",
										Toast.LENGTH_LONG).show();
								return;
							} else {
								if (i == 0) {
									str.append(list.get(i).get("columnName")
											+ "=" + list.get(i).get("code"));
								} else {
									str.append(","
											+ list.get(i).get("columnName")
											+ "=" + list.get(i).get("code"));
								}
							}
						} else if (list.get(i).get("controlType")
								.equals("DROPDOWN_LIST")) {
							// TextView tv = (TextView) lv_base.getChildAt(i)
							// .findViewById(R.id.tv_search_1);
							String s = (String) list.get(i).get("value");
							if (StringUtil.isBlank(s) || s.equals("---请选择---")) {
								Toast.makeText(Bdlr_lrbd_luru_Activity.this,
										list.get(i).get("name") + "不能为空！",
										Toast.LENGTH_LONG).show();
								return;
							} else {
								if (i == 0) {
									str.append(list.get(i).get("columnName")
											+ "=" + s);
								} else {
									str.append(","
											+ list.get(i).get("columnName")
											+ "=" + s);
								}

							}
							Log.e("1111111111" + "ccc" + i, s);
						}
					} else {
						if (list.get(i).get("controlType").equals("DATEFIELD")) {
							// TextView tv = (TextView) lv_base.getChildAt(i)
							// .findViewById(R.id.tv_search_2);
							String s = (String) list.get(i).get("value");
							if (i == 0) {
								str.append(list.get(i).get("columnName") + "="
										+ s);
							} else {
								str.append("," + list.get(i).get("columnName")
										+ "=" + s);
							}
						} else if (list.get(i).get("controlType")
								.equals("TEXTBOX")) {
							// EditText edt = (EditText) lv_base.getChildAt(i)
							// .findViewById(R.id.edt_job);
							String s = (String) list.get(i).get("value");
							if (i == 0) {
								str.append(list.get(i).get("columnName") + "="
										+ s);
							} else {
								str.append("," + list.get(i).get("columnName")
										+ "=" + s);
							}
						} else if (list.get(i).get("controlType")
								.equals("ADDRESS")) {
							// TextView tv = (TextView) lv_base.getChildAt(i)
							// .findViewById(R.id.tv_search_2);
							if (i == 0) {
								str.append(list.get(i).get("columnName") + "="
										+ list.get(i).get("code"));
							} else {
								str.append("," + list.get(i).get("columnName")
										+ "=" + list.get(i).get("code"));
							}
						} else if (list.get(i).get("controlType")
								.equals("DROPDOWN_LIST")) {
							// TextView tv = (TextView) lv_base.getChildAt(i)
							// .findViewById(R.id.tv_search_1);
							String s = (String) list.get(i).get("value");
							if (i == 0) {
								str.append(list.get(i).get("columnName") + "="
										+ s);
							} else {
								str.append("," + list.get(i).get("columnName")
										+ "=" + s);
							}
						}
					}

				}
				str.append("}]]>" + "</Ywbd></ZsoftInfo></case>");
				List<Map<String, Object>> paramlist = new ArrayList<Map<String, Object>>();
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("xml", str.toString());
				paramlist.add(map);
				blBdlrManagerService.SubmitServiceSubjectForm(
						Bdlr_lrbd_luru_Activity.this, 2, paramlist);
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
			this.startActivity(new Intent(this, Base_ListActivity_.class));
			this.finish();
		}
		return false;
	}
}
