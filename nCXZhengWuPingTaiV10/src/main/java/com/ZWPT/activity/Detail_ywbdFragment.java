package com.ZWPT.activity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.ZWPT.adapter.Detail_Ywbd_FormAdapter;
import com.ZWPT.data.DataHelper;
import com.ZWPT.data.InfoFile_;
import com.ZWPT.view.FormFillView;
import com.googlecode.androidannotations.annotations.AfterViews;
import com.googlecode.androidannotations.annotations.Bean;
import com.googlecode.androidannotations.annotations.EFragment;
import com.googlecode.androidannotations.annotations.ViewById;
import com.googlecode.androidannotations.annotations.sharedpreferences.Pref;

/**
 * 业务表单
 * 
 * @author durenchong
 * @date 2014-06-18
 */
@EFragment(R.layout.fragment_detail_ywbd)
public class Detail_ywbdFragment extends BaseFragment {
	@Bean
	DataHelper dataHelper;
	@Pref
	InfoFile_ infoFile_;
	@ViewById
	TextView tv_list_item_1, tv_list_item_2, tv_bd;
	// @ViewById
	// ListView lv_base;
	@ViewById
	LinearLayout ll_detail_bd, ll_form;

	List<Map<String, Object>> mylist = null;

	@AfterViews
	void initView() {
		if(mylist == null){
			return;
		}
		tv_list_item_1.setText(infoFile_.baselist_yewuliushuihao().get());
		tv_list_item_2.setText(infoFile_.shengqingrenname().get());
		// lv_base.setAdapter(new Detail_Ywbd_FormAdapter(context,
		// mylist));
		FormFillView fillView = new FormFillView(context, mylist, null, false);
		ll_form.addView(fillView.getView());
		// CommUtil.setListViewHeightBasedOnChildren(lv_base);
		if (mylist.size() == 0) {
			ll_detail_bd.setVisibility(View.GONE);
			tv_bd.setText("表单信息未录入！");
			tv_bd.setVisibility(View.VISIBLE);
		}
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO 自动生成的方法存根
		super.onCreate(savedInstanceState);
		Bundle bundle = new Bundle();
		bundle = this.getArguments();
		mylist = (List<Map<String, Object>>) bundle.getSerializable("list");

	}
}
