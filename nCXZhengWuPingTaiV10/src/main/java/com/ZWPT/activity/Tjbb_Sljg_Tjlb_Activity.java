package com.ZWPT.activity;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ZWPT.Utils.DatePickDialog;
import com.ZWPT.data.Constants;
import com.ZWPT.data.InfoFile_;
import com.ZWPT.view.MyPopupWindow;
import com.googlecode.androidannotations.annotations.AfterViews;
import com.googlecode.androidannotations.annotations.EActivity;
import com.googlecode.androidannotations.annotations.ViewById;
import com.googlecode.androidannotations.annotations.sharedpreferences.Pref;

@EActivity(R.layout.layout_tjbb_sljgtj)
public class Tjbb_Sljg_Tjlb_Activity extends BaseActivity implements
		OnClickListener {

	@Pref
	InfoFile_ infoFile_;
	@ViewById
	ImageButton title_search;
	// @ViewById ListView lv_tjlb_tjqk_1,lv_tjlb_tjqk_2,lv_tjlb_tjqk_3;
	@ViewById
	RelativeLayout rl_title;
	@ViewById
	LinearLayout tjbb_tjqk, search_rll_all, search_rll_fenlei,
			search_rll_shenqingriqi, search_rll_shenqingriqi_to, tjlb_tjqk_ib1,
			tjlb_tjqk_ib2, tjlb_tjqk_ib3, ll_tjbb_tjqk_1, ll_tjbb_tjqk_2,
			ll_tjbb_tjqk_3;
	@ViewById
	Button btn_search;
	@ViewById
	TextView tv_search_1, tv_search_2, tv_search_3;
	@ViewById
	TextView yewuliang1, weibanjie1, banlichenggong1, banlishibai1,
			buyushouli1, chaoshi1, yifafang1, weifafang1, banjielv1;
	@ViewById
	TextView yewuliang2, weibanjie2, banlichenggong2, banlishibai2,
			buyushouli2, chaoshi2, yifafang2, weifafang2, banjielv2;
	@ViewById
	TextView yewuliang3, weibanjie3, banlichenggong3, banlishibai3,
			buyushouli3, chaoshi3, yifafang3, weifafang3, banjielv3;

	// private DatePickerDialog dialog;
	private PopupWindow pop;
	private View view;

	@AfterViews
	void initView() {
		initTitle();
		title_search.setVisibility(View.VISIBLE);
		title_search.setOnClickListener(this);
		tjlb_tjqk_ib1.setOnClickListener(this);
		tjlb_tjqk_ib2.setOnClickListener(this);
		tjlb_tjqk_ib3.setOnClickListener(this);
		search_rll_fenlei.setOnClickListener(this);
		search_rll_shenqingriqi.setOnClickListener(this);
		search_rll_shenqingriqi_to.setOnClickListener(this);
		btn_search.setOnClickListener(this);
		this.finishOthers();
		initMenu();
	}

	private void initTitle() {
		if (Constants.title.equals(context.getResources().getString(
				R.string.anshoulijigoutongji))) {
			rl_title.setBackgroundDrawable(getResources().getDrawable(
					R.drawable.tt_asljgtj));// 设置标题栏
			// List<String> oList=new ArrayList<String>();
			// oList.add("业务量：380");
			// oList.add("未办结  ：  13");
			// oList.add("办理成功  ：  340");
			// oList.add("办理失败  ：  7");
			// oList.add("不予受理  ：  20");
			// oList.add("超时  ：  11");
			// oList.add("已发放  ：  327");
			// oList.add("未发放  ：  5");
			// oList.add("办结率  ：  97%");
			// Tjbb_Tjqk_ListAdapter adapter=new Tjbb_Tjqk_ListAdapter(context,
			// oList);
			// lv_tjlb_tjqk_1.setAdapter(adapter);
			// lv_tjlb_tjqk_2.setAdapter(adapter);
			// lv_tjlb_tjqk_3.setAdapter(adapter);

		} else if (Constants.title.equals(context.getResources().getString(
				R.string.anfuwushixiangtongji))) {
			rl_title.setBackgroundDrawable(getResources().getDrawable(
					R.drawable.tt_afwsxtj));
		} else if (Constants.title.equals(context.getResources().getString(
				R.string.anyewubumentongji))) {
			rl_title.setBackgroundDrawable(getResources().getDrawable(
					R.drawable.tt_aywbmtj));
		}
	}

	public void initMenu() {
		WindowManager wm = (WindowManager) context
				.getSystemService(Context.WINDOW_SERVICE);
		int height = wm.getDefaultDisplay().getHeight();// 屏幕高度
		LayoutInflater inflater = (LayoutInflater) this
				.getSystemService(LAYOUT_INFLATER_SERVICE);
		view = inflater.inflate(R.layout.main_menu, null);
		pop = new PopupWindow(view, ViewGroup.LayoutParams.FILL_PARENT,
				height / 7);// 获取PopupWindow对象并设置窗体的大小
		// 点击popupWindow以外的地方或者返回键让其消失
		pop.setBackgroundDrawable(context.getResources().getDrawable(
				R.color.transparent));
		pop.setFocusable(false);
		pop.setOutsideTouchable(true);
		pop.setAnimationStyle(R.style.PopupAnimation);// 设置动画样式
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.title_search:
			if (search_rll_all.getVisibility() == View.VISIBLE) {
				Animation animation1 = AnimationUtils.loadAnimation(this,
						R.anim.location_pop_anim1);
				animation1.setAnimationListener(new ViewHidden());
				search_rll_all.startAnimation(animation1);
				tjbb_tjqk.startAnimation(animation1);

			} else {
				Animation animation = AnimationUtils.loadAnimation(this,
						R.anim.location_pop_anim);
				search_rll_all.startAnimation(animation);
				tjbb_tjqk.startAnimation(animation);
				search_rll_all.setVisibility(View.VISIBLE);
			}
			break;
		case R.id.btn_search:// 点击查询做相应的处理

			if (Constants.title.equals(context.getResources().getString(
					R.string.anfuwushixiangtongji))) {

			} else if (Constants.title.equals(context.getResources().getString(
					R.string.anshoulijigoutongji))) {
				// List<String> oList=new ArrayList<String>();
				// oList.add("业务量：380");
				// oList.add("未办结：13");
				// oList.add("办理成功：340");
				// oList.add("办理失败：7");
				// oList.add("不予受理：20");
				// oList.add("超时：11");
				// oList.add("已发放：327");
				// oList.add("未发放：5");
				// oList.add("办结率：97%");
				// Tjbb_Tjqk_ListAdapter adapter=new
				// Tjbb_Tjqk_ListAdapter(context, oList);
				// lv_tjlb_tjqk_1.setAdapter(adapter);
				if (ll_tjbb_tjqk_1.getVisibility() == View.GONE) {
					ll_tjbb_tjqk_1.setVisibility(View.VISIBLE);
				}
			} else if (Constants.title.equals(context.getResources().getString(
					R.string.anyewubumentongji))) {

			}
			break;
		case R.id.search_rll_fenlei:
			final List<String> list = new ArrayList<String>();
			list.add("111111111");
			list.add("222222222");
			list.add("111111111");
			list.add("222222222");
			list.add("111111111");
			list.add("222222222");
			new MyPopupWindow(Tjbb_Sljg_Tjlb_Activity.this, list,
					search_rll_fenlei, MyPopupWindow.BOTTOM) {
				@Override
				protected void doNext(int position) {
					tv_search_1.setText(list.get(position));
				}
			};
			break;
		case R.id.search_rll_shenqingriqi:
			DatePickDialog.showDateCheckDialog(context, tv_search_2, false);
			break;
		case R.id.search_rll_shenqingriqi_to:
			DatePickDialog.showDateCheckDialog(context, tv_search_3, false);
			break;
		case R.id.tjlb_tjqk_ib1:
			if (ll_tjbb_tjqk_1.getVisibility() == View.GONE) {
				ll_tjbb_tjqk_1.setVisibility(View.VISIBLE);
			} else {
				ll_tjbb_tjqk_1.setVisibility(View.GONE);
			}
			break;
		case R.id.tjlb_tjqk_ib2:
			if (ll_tjbb_tjqk_2.getVisibility() == View.GONE) {
				ll_tjbb_tjqk_2.setVisibility(View.VISIBLE);
			} else {
				ll_tjbb_tjqk_2.setVisibility(View.GONE);
			}
			break;
		case R.id.tjlb_tjqk_ib3:
			if (ll_tjbb_tjqk_3.getVisibility() == View.GONE) {
				ll_tjbb_tjqk_3.setVisibility(View.VISIBLE);
			} else {
				ll_tjbb_tjqk_3.setVisibility(View.GONE);
			}
			break;
		}

	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			exitBy2Click();
		}
		if (keyCode == KeyEvent.KEYCODE_MENU) {
			if (pop.isShowing()) {
				pop.dismiss();
			} else {
				pop.showAtLocation(view, Gravity.BOTTOM, 0, 0); // 设置窗体的位置
			}
		}
		return false;
	}

	private final class ViewHidden implements Animation.AnimationListener {

		public ViewHidden() {
		}

		public void onAnimationStart(Animation animation) {
		}

		public void onAnimationEnd(Animation animation) {
			search_rll_all.setVisibility(View.GONE);

		}

		public void onAnimationRepeat(Animation animation) {
		}
	}
}
