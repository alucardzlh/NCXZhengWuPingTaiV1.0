package com.ZWPT.view;

import com.ZWPT.activity.R;
import com.ZWPT.data.Constants;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

/**
 * 抽取公共查询项
 * 
 * @author Administrator
 * 
 */
public class CustomSearchView extends LinearLayout {

	private Context context;
	public LinearLayout search_rll1, search_rll2, search_rll3, search_rll4,
			search_rll5, search_rll6, search_rll7, search_rll8, search_rll9;

	public CustomSearchView(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.context = context;
		init();
	}

	private void init() {
		LayoutInflater inflater = LayoutInflater.from(context);
		inflater.inflate(R.layout.custom_search_view, this);
		initView();
		initTitle();
	}

	private void initTitle() {
		if (Constants.title.equals(context.getResources().getString(
				R.string.yushouliyewu))) {
			search_rll1.setVisibility(View.VISIBLE);
			search_rll2.setVisibility(View.VISIBLE);
			search_rll3.setVisibility(View.VISIBLE);
			search_rll4.setVisibility(View.GONE);
			search_rll5.setVisibility(View.VISIBLE);
			search_rll6.setVisibility(View.GONE);
			search_rll7.setVisibility(View.GONE);
			search_rll8.setVisibility(View.GONE);
			search_rll9.setVisibility(View.GONE);
		}
		if (Constants.title.equals(context.getResources().getString(
				R.string.shoujiandengji))) {

		}
		if (Constants.title.equals(context.getResources().getString(
				R.string.yishouliyewu))) {
			search_rll1.setVisibility(View.VISIBLE);
			search_rll2.setVisibility(View.VISIBLE);
			search_rll3.setVisibility(View.VISIBLE);
			search_rll4.setVisibility(View.VISIBLE);
			search_rll5.setVisibility(View.VISIBLE);
			search_rll6.setVisibility(View.VISIBLE);
			search_rll7.setVisibility(View.VISIBLE);
			search_rll8.setVisibility(View.VISIBLE);
			search_rll9.setVisibility(View.VISIBLE);
		}
		if (Constants.title.equals(context.getResources().getString(
				R.string.buyushouliyewu))) {
			search_rll1.setVisibility(View.VISIBLE);
			search_rll2.setVisibility(View.VISIBLE);
			search_rll3.setVisibility(View.VISIBLE);
			search_rll4.setVisibility(View.VISIBLE);
			search_rll5.setVisibility(View.VISIBLE);
			search_rll6.setVisibility(View.VISIBLE);
			search_rll7.setVisibility(View.VISIBLE);
			search_rll8.setVisibility(View.GONE);
			search_rll9.setVisibility(View.GONE);
		}
		if (Constants.title.equals(context.getResources().getString(
				R.string.dailurubiaodan))) {
			search_rll1.setVisibility(View.VISIBLE);
			search_rll2.setVisibility(View.VISIBLE);
			search_rll3.setVisibility(View.VISIBLE);
			search_rll4.setVisibility(View.VISIBLE);
			search_rll5.setVisibility(View.VISIBLE);
			search_rll6.setVisibility(View.VISIBLE);
			search_rll7.setVisibility(View.VISIBLE);
			search_rll8.setVisibility(View.GONE);
			search_rll9.setVisibility(View.GONE);
		}
		if (Constants.title.equals(context.getResources().getString(
				R.string.yilurubiaodan))) {
			search_rll1.setVisibility(View.VISIBLE);
			search_rll2.setVisibility(View.VISIBLE);
			search_rll3.setVisibility(View.VISIBLE);
			search_rll4.setVisibility(View.VISIBLE);
			search_rll5.setVisibility(View.VISIBLE);
			search_rll6.setVisibility(View.VISIBLE);
			search_rll7.setVisibility(View.VISIBLE);
			search_rll8.setVisibility(View.GONE);
			search_rll9.setVisibility(View.GONE);
		}
		if (Constants.title.equals(context.getResources().getString(
				R.string.daishenpi))) {
			search_rll1.setVisibility(View.VISIBLE);
			search_rll2.setVisibility(View.VISIBLE);
			search_rll3.setVisibility(View.VISIBLE);
			search_rll4.setVisibility(View.VISIBLE);
			search_rll5.setVisibility(View.VISIBLE);
			search_rll6.setVisibility(View.VISIBLE);
			search_rll7.setVisibility(View.VISIBLE);
			search_rll8.setVisibility(View.GONE);
			search_rll9.setVisibility(View.GONE);
		}
		if (Constants.title.equals(context.getResources().getString(
				R.string.yishenpi))) {
			search_rll1.setVisibility(View.VISIBLE);
			search_rll2.setVisibility(View.VISIBLE);
			search_rll3.setVisibility(View.VISIBLE);
			search_rll4.setVisibility(View.VISIBLE);
			search_rll5.setVisibility(View.VISIBLE);
			search_rll6.setVisibility(View.VISIBLE);
			search_rll7.setVisibility(View.VISIBLE);
			search_rll8.setVisibility(View.GONE);
			search_rll9.setVisibility(View.GONE);
		}
		if (Constants.title.equals(context.getResources().getString(
				R.string.yituihui))) {
			search_rll1.setVisibility(View.VISIBLE);
			search_rll2.setVisibility(View.VISIBLE);
			search_rll3.setVisibility(View.VISIBLE);
			search_rll4.setVisibility(View.VISIBLE);
			search_rll5.setVisibility(View.VISIBLE);
			search_rll6.setVisibility(View.VISIBLE);
			search_rll7.setVisibility(View.VISIBLE);
			search_rll8.setVisibility(View.GONE);
			search_rll9.setVisibility(View.GONE);
		}
		if (Constants.title.equals(context.getResources().getString(
				R.string.daifafangliebiao))) {
			search_rll1.setVisibility(View.VISIBLE);
			search_rll2.setVisibility(View.VISIBLE);
			search_rll3.setVisibility(View.VISIBLE);
			search_rll4.setVisibility(View.VISIBLE);
			search_rll5.setVisibility(View.VISIBLE);
			search_rll6.setVisibility(View.VISIBLE);
			search_rll7.setVisibility(View.VISIBLE);
			search_rll8.setVisibility(View.VISIBLE);
			search_rll9.setVisibility(View.VISIBLE);
		}
		if (Constants.title.equals(context.getResources().getString(
				R.string.yifafangliebiao))) {
			search_rll1.setVisibility(View.VISIBLE);
			search_rll2.setVisibility(View.VISIBLE);
			search_rll3.setVisibility(View.VISIBLE);
			search_rll4.setVisibility(View.VISIBLE);
			search_rll5.setVisibility(View.VISIBLE);
			search_rll6.setVisibility(View.VISIBLE);
			search_rll7.setVisibility(View.VISIBLE);
			search_rll8.setVisibility(View.VISIBLE);
			search_rll9.setVisibility(View.VISIBLE);
		}
		if (Constants.title.equals(context.getResources().getString(
				R.string.daoqiweibanjieliebiao))) {
			search_rll1.setVisibility(View.VISIBLE);
			search_rll2.setVisibility(View.VISIBLE);
			search_rll3.setVisibility(View.VISIBLE);
			search_rll4.setVisibility(View.VISIBLE);
			search_rll5.setVisibility(View.VISIBLE);
			search_rll6.setVisibility(View.VISIBLE);
			search_rll7.setVisibility(View.VISIBLE);
			search_rll8.setVisibility(View.GONE);
			search_rll9.setVisibility(View.GONE);
		}
		if (Constants.title.equals(context.getResources().getString(
				R.string.yewudanganchaxun))) {
			search_rll1.setVisibility(View.VISIBLE);
			search_rll2.setVisibility(View.VISIBLE);
			search_rll3.setVisibility(View.VISIBLE);
			search_rll4.setVisibility(View.VISIBLE);
			search_rll5.setVisibility(View.VISIBLE);
			search_rll6.setVisibility(View.VISIBLE);
			search_rll7.setVisibility(View.VISIBLE);
			search_rll8.setVisibility(View.GONE);
			search_rll9.setVisibility(View.GONE);
		}
		if (Constants.title.equals(context.getResources().getString(
				R.string.anshoulijigoutongji))) {
			search_rll1.setVisibility(View.GONE);
			search_rll2.setVisibility(View.GONE);
			search_rll3.setVisibility(View.GONE);
			search_rll4.setVisibility(View.GONE);
			search_rll5.setVisibility(View.VISIBLE);
			search_rll6.setVisibility(View.VISIBLE);
			search_rll7.setVisibility(View.VISIBLE);
			search_rll8.setVisibility(View.GONE);
			search_rll9.setVisibility(View.GONE);
		}
	}

	private void initView() {
		search_rll1 = (LinearLayout) findViewById(R.id.search_rll1);
		search_rll2 = (LinearLayout) findViewById(R.id.search_rll2);
		search_rll3 = (LinearLayout) findViewById(R.id.search_rll3);
		search_rll4 = (LinearLayout) findViewById(R.id.search_rll4);
		search_rll5 = (LinearLayout) findViewById(R.id.search_rll5);
		search_rll6 = (LinearLayout) findViewById(R.id.search_rll6);
		search_rll7 = (LinearLayout) findViewById(R.id.search_rll7);
		search_rll8 = (LinearLayout) findViewById(R.id.search_rll8);
		search_rll9 = (LinearLayout) findViewById(R.id.search_rll9);
	}

}
