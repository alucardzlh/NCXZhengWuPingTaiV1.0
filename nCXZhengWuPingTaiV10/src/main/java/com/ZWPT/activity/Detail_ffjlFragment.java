package com.ZWPT.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ZWPT.Utils.StringUtil;
import com.ZWPT.data.DataHelper;
import com.ZWPT.data.InfoFile_;
import com.ZWPT.data.entity.Detail_Ffjl_bean;
import com.ZWPT.data.entity.Detail_Sjdj_bean;
import com.googlecode.androidannotations.annotations.AfterViews;
import com.googlecode.androidannotations.annotations.Bean;
import com.googlecode.androidannotations.annotations.EFragment;
import com.googlecode.androidannotations.annotations.ViewById;
import com.googlecode.androidannotations.annotations.sharedpreferences.Pref;

/**
 * 滚动标题栏-发放记录
 * 
 * @author durenchong
 * @date 2014-06-18
 */
@EFragment(R.layout.fragment_detail_ffjl)
public class Detail_ffjlFragment extends BaseFragment {
	@Bean
	DataHelper dataHelper;
	@Pref
	InfoFile_ infoFile_;
	// 发放记录信息
	@ViewById
	TextView tv_list_item_1, tv_list_item_2, tv_list_item_3, tv_list_item_4,
			tv_list_item_5, tv_list_item_6;
	// 短息发送详情
	@ViewById
	TextView tv_list_item_dx_1, tv_list_item_dx_2, tv_list_item_dx_3,
			tv_list_item_dx_4, tv_list_item_dx_5;
	@ViewById
	LinearLayout dxxq;
	Detail_Ffjl_bean detail_Ffjl_bean = null;

	@AfterViews
	void initView() {
		if(detail_Ffjl_bean == null){
			return;
		}
		tv_list_item_1.setText(StringUtil.checkStringValue(detail_Ffjl_bean
				.getBjlsh()));
		tv_list_item_2.setText(StringUtil.checkStringValue(detail_Ffjl_bean
				.getSqrxm()));
		tv_list_item_3.setText(StringUtil.checkStringValue(detail_Ffjl_bean
				.getFfr()));
		tv_list_item_4.setText(StringUtil.checkStringValue(detail_Ffjl_bean
				.getFfsj()));
		tv_list_item_5.setText(StringUtil.checkStringValue(detail_Ffjl_bean
				.getFfjg()));
		tv_list_item_6.setText(StringUtil.checkStringValue(detail_Ffjl_bean
				.getQsr()));
		tv_list_item_dx_1.setText(StringUtil.checkStringValue(detail_Ffjl_bean
				.getJsr()));
		tv_list_item_dx_2.setText(StringUtil.checkStringValue(detail_Ffjl_bean
				.getJshm()));
		tv_list_item_dx_3.setText(StringUtil.checkStringValue(detail_Ffjl_bean
				.getFsr()));
		tv_list_item_dx_4.setText(StringUtil.checkStringValue(detail_Ffjl_bean
				.getFssj()));
		tv_list_item_dx_5.setText(StringUtil.checkStringValue(detail_Ffjl_bean
				.getDxnr()));
		if (detail_Ffjl_bean.getJsr() == null) {
			dxxq.setVisibility(View.GONE);
		}
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO 自动生成的方法存根
		super.onCreate(savedInstanceState);
		Bundle bundle = new Bundle();
		bundle = this.getArguments();
		detail_Ffjl_bean = (Detail_Ffjl_bean) bundle
				.getSerializable("Detail_Ffjl_bean");
	}

}
