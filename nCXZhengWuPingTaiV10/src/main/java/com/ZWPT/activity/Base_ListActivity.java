package com.ZWPT.activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.RelativeLayout;

import com.ZWPT.Utils.DatePickDialog;
import com.ZWPT.Utils.StringUtil;
import com.ZWPT.adapter.Bdlr_Dlrbd_ListAdapter;
import com.ZWPT.adapter.Bdlr_ylrbd_ListAdapter;
import com.ZWPT.adapter.Jgff_Dfflb_ListAdapter;
import com.ZWPT.adapter.Jgff_Dqwbjlb_ListAdapter;
import com.ZWPT.adapter.Jgff_Yfflb_ListAdapter;
import com.ZWPT.adapter.Sjsl_Byuslyw_ListAdapter;
import com.ZWPT.adapter.Sjsl_Yslyw_ListAdapter;
import com.ZWPT.adapter.Spcl_Dsp_ListAdapter;
import com.ZWPT.adapter.Spcl_Ysp_ListAdapter;
import com.ZWPT.adapter.Spcl_Yth_ListAdapter;
import com.ZWPT.adapter.Wssq_Yslyw_ListAdapter;
import com.ZWPT.adapter.Ywda_Ywdacx_ListAdapter;
import com.ZWPT.data.Constants;
import com.ZWPT.data.DataHelper;
import com.ZWPT.data.InfoFile_;
import com.ZWPT.data.entity.Bdlr_Dlrbd_List_Result;
import com.ZWPT.data.entity.Bdlr_Ylrbd_List_Result;
import com.ZWPT.data.entity.HandleResult;
import com.ZWPT.data.entity.Jgff_Dfflb_List_Result;
import com.ZWPT.data.entity.Jgff_Dqwbjlb_List_Result;
import com.ZWPT.data.entity.Jgff_Yfflb_List_Result;
import com.ZWPT.data.entity.Sjsl_Byuslyw_List_Result;
import com.ZWPT.data.entity.Sjsl_Yislyw_List_Result;
import com.ZWPT.data.entity.Spcl_Dsp_List_Result;
import com.ZWPT.data.entity.Spcl_Ysp_List_Result;
import com.ZWPT.data.entity.Spcl_Yth_List_Result;
import com.ZWPT.data.entity.Wssq_Yslyw_List_Result;
import com.ZWPT.data.entity.Ywda_Ywdacx_List_Result;
import com.ZWPT.service.ShowListInfoService;
import com.ZWPT.view.MyPopupWindow;
import com.googlecode.androidannotations.annotations.AfterViews;
import com.googlecode.androidannotations.annotations.Bean;
import com.googlecode.androidannotations.annotations.EActivity;
import com.googlecode.androidannotations.annotations.ViewById;
import com.googlecode.androidannotations.annotations.sharedpreferences.Pref;

/**
 * 列表综合activity
 * 
 * @author yuhuihui
 * @data 2014-6-5
 */
@EActivity(R.layout.layout_base_list)
public class Base_ListActivity extends BaseActivity implements
		View.OnClickListener {
	@Bean
	DataHelper dataHelper;
	@Pref
	InfoFile_ infoFile_;
	@ViewById
	RelativeLayout rl_title;
	@ViewById
	LinearLayout search_rll_fenlei, search_rll_all, search_rll_shenqingriqi,
			search_rll_shenqingriqi_to, search_rll_banjianriqi,
			search_rll_banjianriqi_to;
	@ViewById
	ImageButton title_search;
	@ViewById
	LinearLayout ll_search;
	@ViewById
	Button btn_search;
	@ViewById
	TextView tv_search_1, tv_search_2, tv_search_3, tv_search_4, tv_search_5;
	@ViewById
	ListView lv_base;
	@ViewById
	EditText edt_search_1, edt_search_2, edt_search_3, edt_search_4;

	private boolean isClickSelect;// 是否点击查询

	ProgressBar pbMore;
	TextView tvMore;
	private int indexPager = 0;// 当前页数
	private boolean isMore = true;
	private int lastItem;// 最后一项的下标
	private int Count = 0;// 已加载的项数

	private List<Wssq_Yslyw_List_Result> Yslyw_list;// 预受理业务
	private List<Sjsl_Yislyw_List_Result> Yislyw_list;// 已受理业务
	private List<Sjsl_Byuslyw_List_Result> Byuslyw_list;// 不予受理业务
	private List<Bdlr_Dlrbd_List_Result> Dlrbd_list;// 待录入表单
	private List<Bdlr_Ylrbd_List_Result> Ylrbd_list;// 已录入表单
	private List<Spcl_Dsp_List_Result> Dsp_list;// 待审批
	private List<Spcl_Ysp_List_Result> Ysp_list;// 已审批
	private List<Spcl_Yth_List_Result> Yth_list;// 已退回
	private List<Jgff_Dfflb_List_Result> Dfflb_list;// 待发放
	private List<Jgff_Yfflb_List_Result> Yfflb_list;// 已发放
	private List<Jgff_Dqwbjlb_List_Result> Dqwbjlb_list;// 到期未办结
	private List<Ywda_Ywdacx_List_Result> Ywdacx_list;// 业务档案查询

	private Wssq_Yslyw_ListAdapter yslyw_myAdapter;// 预受理适配器
	private Sjsl_Yslyw_ListAdapter Yslyw_myadapter;// 已受理适配器
	private Sjsl_Byuslyw_ListAdapter Byuslyw_myAdapter;// 不予受理适配器
	private Bdlr_Dlrbd_ListAdapter Dlrbd_myadapter;// 待录入表单适配器
	private Bdlr_ylrbd_ListAdapter ylrbd_myadapter;// 已录入表单适配器
	private Spcl_Dsp_ListAdapter Dsp_myadapter;// 待审批适配器
	private Spcl_Ysp_ListAdapter Ysp_myaAdapter;// 已审批适配器
	private Spcl_Yth_ListAdapter Yth_myadapter;// 已退回适配器
	private Jgff_Dfflb_ListAdapter Dfflb_myadapter;// 待发放适配器
	private Jgff_Yfflb_ListAdapter Yfflb_myadapter;// 已发放适配器
	private Jgff_Dqwbjlb_ListAdapter Dqwbjlb_myAdapter;// 到期未办结适配器
	private Ywda_Ywdacx_ListAdapter Ywdacx_myadapter;// 业务档案查询适配器

	PopupWindow pop; // 菜单menu
	View view; // 菜单menu显示的视图
	private boolean isLastRow = false;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO 自动生成的方法存根
		super.onCreate(savedInstanceState);
		initTitle(false);

	}

	@AfterViews
	void initView() {
		initTitle(true);
		ll_search.setVisibility(View.VISIBLE);
		ll_search.setOnClickListener(this);
		title_search.setOnClickListener(this);
		btn_search.setOnClickListener(this);
		search_rll_fenlei.setOnClickListener(this);
		search_rll_shenqingriqi.setOnClickListener(this);
		search_rll_shenqingriqi_to.setOnClickListener(this);
		search_rll_banjianriqi.setOnClickListener(this);
		search_rll_banjianriqi_to.setOnClickListener(this);
		lv_base.setOnScrollListener(new ScrollListener());
		this.finishOthers();
	}

	@Override
	public void onClick(View v) {
		String serviceTargetId = edt_search_2.getText().toString();// 身份证号
		String serialNumber = edt_search_4.getText().toString();// 流水号
		String serviceTargetName = edt_search_1.getText().toString();// 申请人姓名
		String serviceSubjectName = edt_search_3.getText().toString();// 事项名称
		String applyDateStart = tv_search_2.getText().toString();// 申请开始日期
		String applyDateEnd = tv_search_3.getText().toString();// 申请结束日期
		switch (v.getId()) {
		case R.id.ll_search:
			if (search_rll_all.getVisibility() == View.VISIBLE) {
				Animation animation1 = AnimationUtils.loadAnimation(this,
						R.anim.location_pop_anim1);
				animation1.setAnimationListener(new ViewHidden());
				search_rll_all.startAnimation(animation1);
				lv_base.startAnimation(animation1);

			} else {
				Animation animation = AnimationUtils.loadAnimation(this,
						R.anim.location_pop_anim);
				search_rll_all.startAnimation(animation);
				lv_base.startAnimation(animation);
				search_rll_all.setVisibility(View.VISIBLE);
			}
			break;
		case R.id.title_search:
			if (search_rll_all.getVisibility() == View.VISIBLE) {
				Animation animation1 = AnimationUtils.loadAnimation(this,
						R.anim.location_pop_anim1);
				animation1.setAnimationListener(new ViewHidden());
				search_rll_all.startAnimation(animation1);
				lv_base.startAnimation(animation1);

			} else {
				Animation animation = AnimationUtils.loadAnimation(this,
						R.anim.location_pop_anim);
				search_rll_all.startAnimation(animation);
				lv_base.startAnimation(animation);
				search_rll_all.setVisibility(View.VISIBLE);
			}
			break;
		case R.id.btn_search:
			String info = StringUtil.isNotTrimBlank(serviceTargetId) ? StringUtil
					.IDCardValidate(serviceTargetId) : "";
			if (info.trim().length() > 0) {
				showToastLong(info);
			} else {
				isClickSelect = true;
				indexPager = 0;
				Count = 0;
				isMore = true;
				if (Constants.title.equals(context.getResources().getString(
						R.string.yushouliyewu))) {
					if (Yslyw_list != null) {
						Yslyw_list.clear();
					}
					// 清楚预受理list
				}
				if (Constants.title.equals(context.getResources().getString(
						R.string.yishouliyewu))) {
					if (Yislyw_list != null) {
						Yislyw_list.clear();
					}
				}
				if (Constants.title.equals(context.getResources().getString(
						R.string.buyushouliyewu))) {
					if (Byuslyw_list != null) {
						Byuslyw_list.clear();
					}
				}
				if (Constants.title.equals(context.getResources().getString(
						R.string.dailurubiaodan))) {
					if (Dlrbd_list != null) {
						Dlrbd_list.clear();
					}
				}
				if (Constants.title.equals(context.getResources().getString(
						R.string.yilurubiaodan))) {
					if (Ylrbd_list != null) {
						Ylrbd_list.clear();
					}
				}
				if (Constants.title.equals(context.getResources().getString(
						R.string.daishenpi))) {
					if (Dsp_list != null) {
						Dsp_list.clear();
					}
				}
				if (Constants.title.equals(context.getResources().getString(
						R.string.yishenpi))) {
					if (Ysp_list != null) {
						Ysp_list.clear();
					}
				}
				if (Constants.title.equals(context.getResources().getString(
						R.string.yituihui))) {
					if (Yth_list != null) {
						Yth_list.clear();
					}
				}
				if (Constants.title.equals(context.getResources().getString(
						R.string.daifafangliebiao))) {
					if (Dfflb_list != null) {
						Dfflb_list.clear();
					}
				}
				if (Constants.title.equals(context.getResources().getString(
						R.string.yifafangliebiao))) {
					if (Yfflb_list != null) {
						Yfflb_list.clear();
					}
				}
				if (Constants.title.equals(context.getResources().getString(
						R.string.daoqiweibanjieliebiao))) {
					if (Dqwbjlb_list != null) {
						Dqwbjlb_list.clear();
					}
				}
				if (Constants.title.equals(context.getResources().getString(
						R.string.yewudanganchaxun))) {
					if (Ywdacx_list != null) {
						Ywdacx_list.clear();
					}
				}
				getInfoRecoder(serviceTargetId, serialNumber,
						serviceTargetName, serviceSubjectName, applyDateStart,
						applyDateEnd);
			}
			break;
		case R.id.search_rll_fenlei:
			final List<String> list = new ArrayList<String>();
			list.add("即办件");
			list.add("承诺件");
			new MyPopupWindow(Base_ListActivity.this, list, search_rll_fenlei,
					MyPopupWindow.BOTTOM) {
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
		case R.id.search_rll_banjianriqi:
			DatePickDialog.showDateCheckDialog(context, tv_search_4, false);
			break;
		case R.id.search_rll_banjianriqi_to:
			DatePickDialog.showDateCheckDialog(context, tv_search_5, false);
			break;
		}
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		ll_search.setVisibility(View.GONE);
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			// exitBy2Click();
			Intent intent = new Intent();
			intent.setClass(this, MainActivity_.class);
			startActivity(intent);
			finish();
		}
		return false;
	}

	private void addFoot() {
		LayoutInflater inflater = LayoutInflater.from(context);
		LinearLayout llMore = (LinearLayout) inflater.inflate(
				R.layout.more_data_bar, null);
		pbMore = (ProgressBar) llMore.findViewById(R.id.pbMore);
		tvMore = (TextView) llMore.findViewById(R.id.tvMore);
		lv_base.addFooterView(llMore);
	}

	/**
	 * 通过参数进行模糊查询数据
	 * 
	 * @param serviceTargetId
	 * @param serialNumber
	 * @param serviceTargetName
	 * @param serviceSubjectName
	 * @param applyDateStart
	 * @param applyDateEnd
	 */
	public void getInfoRecoder(String serviceTargetId, String serialNumber,
			String serviceTargetName, String serviceSubjectName,
			String applyDateStart, String applyDateEnd) {
		if (Count > 0) {
			pbMore.setVisibility(View.VISIBLE);
			tvMore.setText("数据正在加载，请稍候...");
		}
		if (Constants.title.equals(context.getResources().getString(
				R.string.yushouliyewu))) {
			loadService(Constants.account, String.valueOf(0),
					String.valueOf(indexPager),
					String.valueOf(Constants.PAGE_SIZE_LARGE),
					StringUtil.isTrimBlank(serviceTargetId) ? null
							: serviceTargetId,
					StringUtil.isTrimBlank(serialNumber) ? null : serialNumber,
					StringUtil.isTrimBlank(serviceTargetName) ? null
							: serviceTargetName,
					StringUtil.isTrimBlank(serviceSubjectName) ? null
							: serviceSubjectName,
					StringUtil.isTrimBlank(applyDateStart) ? null
							: applyDateStart,
					StringUtil.isTrimBlank(applyDateEnd) ? null : applyDateEnd,
					1);
		}
		if (Constants.title.equals(context.getResources().getString(
				R.string.yishouliyewu))) {

			loadService(Constants.account, String.valueOf(1),
					String.valueOf(indexPager),
					String.valueOf(Constants.PAGE_SIZE_LARGE),
					StringUtil.isTrimBlank(serviceTargetId) ? null
							: serviceTargetId,
					StringUtil.isTrimBlank(serialNumber) ? null : serialNumber,
					StringUtil.isTrimBlank(serviceTargetName) ? null
							: serviceTargetName,
					StringUtil.isTrimBlank(serviceSubjectName) ? null
							: serviceSubjectName,
					StringUtil.isTrimBlank(applyDateStart) ? null
							: applyDateStart,
					StringUtil.isTrimBlank(applyDateEnd) ? null : applyDateEnd,
					2);

		}
		if (Constants.title.equals(context.getResources().getString(
				R.string.buyushouliyewu))) {
			loadService(Constants.account, String.valueOf(2),
					String.valueOf(indexPager),
					String.valueOf(Constants.PAGE_SIZE_LARGE),
					StringUtil.isTrimBlank(serviceTargetId) ? null
							: serviceTargetId,
					StringUtil.isTrimBlank(serialNumber) ? null : serialNumber,
					StringUtil.isTrimBlank(serviceTargetName) ? null
							: serviceTargetName,
					StringUtil.isTrimBlank(serviceSubjectName) ? null
							: serviceSubjectName,
					StringUtil.isTrimBlank(applyDateStart) ? null
							: applyDateStart,
					StringUtil.isTrimBlank(applyDateEnd) ? null : applyDateEnd,
					3);
		}
		if (Constants.title.equals(context.getResources().getString(
				R.string.dailurubiaodan))) {
			loadService(Constants.account, String.valueOf(3),
					String.valueOf(indexPager),
					String.valueOf(Constants.PAGE_SIZE_LARGE),
					StringUtil.isTrimBlank(serviceTargetId) ? null
							: serviceTargetId,
					StringUtil.isTrimBlank(serialNumber) ? null : serialNumber,
					StringUtil.isTrimBlank(serviceTargetName) ? null
							: serviceTargetName,
					StringUtil.isTrimBlank(serviceSubjectName) ? null
							: serviceSubjectName,
					StringUtil.isTrimBlank(applyDateStart) ? null
							: applyDateStart,
					StringUtil.isTrimBlank(applyDateEnd) ? null : applyDateEnd,
					4);
		}
		if (Constants.title.equals(context.getResources().getString(
				R.string.yilurubiaodan))) {
			loadService(Constants.account, String.valueOf(4),
					String.valueOf(indexPager),
					String.valueOf(Constants.PAGE_SIZE_LARGE),
					StringUtil.isTrimBlank(serviceTargetId) ? null
							: serviceTargetId,
					StringUtil.isTrimBlank(serialNumber) ? null : serialNumber,
					StringUtil.isTrimBlank(serviceTargetName) ? null
							: serviceTargetName,
					StringUtil.isTrimBlank(serviceSubjectName) ? null
							: serviceSubjectName,
					StringUtil.isTrimBlank(applyDateStart) ? null
							: applyDateStart,
					StringUtil.isTrimBlank(applyDateEnd) ? null : applyDateEnd,
					5);
		}
		if (Constants.title.equals(context.getResources().getString(
				R.string.daishenpi))) {
			loadService(Constants.account, String.valueOf(5),
					String.valueOf(indexPager),
					String.valueOf(Constants.PAGE_SIZE_LARGE),
					StringUtil.isTrimBlank(serviceTargetId) ? null
							: serviceTargetId,
					StringUtil.isTrimBlank(serialNumber) ? null : serialNumber,
					StringUtil.isTrimBlank(serviceTargetName) ? null
							: serviceTargetName,
					StringUtil.isTrimBlank(serviceSubjectName) ? null
							: serviceSubjectName,
					StringUtil.isTrimBlank(applyDateStart) ? null
							: applyDateStart,
					StringUtil.isTrimBlank(applyDateEnd) ? null : applyDateEnd,
					6);
		}
		if (Constants.title.equals(context.getResources().getString(
				R.string.yishenpi))) {
			loadService(Constants.account, String.valueOf(6),
					String.valueOf(indexPager),
					String.valueOf(Constants.PAGE_SIZE_LARGE),
					StringUtil.isTrimBlank(serviceTargetId) ? null
							: serviceTargetId,
					StringUtil.isTrimBlank(serialNumber) ? null : serialNumber,
					StringUtil.isTrimBlank(serviceTargetName) ? null
							: serviceTargetName,
					StringUtil.isTrimBlank(serviceSubjectName) ? null
							: serviceSubjectName,
					StringUtil.isTrimBlank(applyDateStart) ? null
							: applyDateStart,
					StringUtil.isTrimBlank(applyDateEnd) ? null : applyDateEnd,
					7);
		}
		if (Constants.title.equals(context.getResources().getString(
				R.string.yituihui))) {
			loadService(Constants.account, String.valueOf(7),
					String.valueOf(indexPager),
					String.valueOf(Constants.PAGE_SIZE_LARGE),
					StringUtil.isTrimBlank(serviceTargetId) ? null
							: serviceTargetId,
					StringUtil.isTrimBlank(serialNumber) ? null : serialNumber,
					StringUtil.isTrimBlank(serviceTargetName) ? null
							: serviceTargetName,
					StringUtil.isTrimBlank(serviceSubjectName) ? null
							: serviceSubjectName,
					StringUtil.isTrimBlank(applyDateStart) ? null
							: applyDateStart,
					StringUtil.isTrimBlank(applyDateEnd) ? null : applyDateEnd,
					8);
		}
		if (Constants.title.equals(context.getResources().getString(
				R.string.daifafangliebiao))) {
			loadService(Constants.account, String.valueOf(8),
					String.valueOf(indexPager),
					String.valueOf(Constants.PAGE_SIZE_LARGE),
					StringUtil.isTrimBlank(serviceTargetId) ? null
							: serviceTargetId,
					StringUtil.isTrimBlank(serialNumber) ? null : serialNumber,
					StringUtil.isTrimBlank(serviceTargetName) ? null
							: serviceTargetName,
					StringUtil.isTrimBlank(serviceSubjectName) ? null
							: serviceSubjectName,
					StringUtil.isTrimBlank(applyDateStart) ? null
							: applyDateStart,
					StringUtil.isTrimBlank(applyDateEnd) ? null : applyDateEnd,
					9);
		}
		if (Constants.title.equals(context.getResources().getString(
				R.string.yifafangliebiao))) {
			loadService(Constants.account, String.valueOf(9),
					String.valueOf(indexPager),
					String.valueOf(Constants.PAGE_SIZE_LARGE),
					StringUtil.isTrimBlank(serviceTargetId) ? null
							: serviceTargetId,
					StringUtil.isTrimBlank(serialNumber) ? null : serialNumber,
					StringUtil.isTrimBlank(serviceTargetName) ? null
							: serviceTargetName,
					StringUtil.isTrimBlank(serviceSubjectName) ? null
							: serviceSubjectName,
					StringUtil.isTrimBlank(applyDateStart) ? null
							: applyDateStart,
					StringUtil.isTrimBlank(applyDateEnd) ? null : applyDateEnd,
					10);
		}
		if (Constants.title.equals(context.getResources().getString(
				R.string.daoqiweibanjieliebiao))) {
			loadService(Constants.account, String.valueOf(10),
					String.valueOf(indexPager),
					String.valueOf(Constants.PAGE_SIZE_LARGE),
					StringUtil.isTrimBlank(serviceTargetId) ? null
							: serviceTargetId,
					StringUtil.isTrimBlank(serialNumber) ? null : serialNumber,
					StringUtil.isTrimBlank(serviceTargetName) ? null
							: serviceTargetName,
					StringUtil.isTrimBlank(serviceSubjectName) ? null
							: serviceSubjectName,
					StringUtil.isTrimBlank(applyDateStart) ? null
							: applyDateStart,
					StringUtil.isTrimBlank(applyDateEnd) ? null : applyDateEnd,
					11);
		}
		if (Constants.title.equals(context.getResources().getString(
				R.string.yewudanganchaxun))) {
			loadService(Constants.account, String.valueOf(11),
					String.valueOf(indexPager),
					String.valueOf(Constants.PAGE_SIZE_LARGE),
					StringUtil.isTrimBlank(serviceTargetId) ? null
							: serviceTargetId,
					StringUtil.isTrimBlank(serialNumber) ? null : serialNumber,
					StringUtil.isTrimBlank(serviceTargetName) ? null
							: serviceTargetName,
					StringUtil.isTrimBlank(serviceSubjectName) ? null
							: serviceSubjectName,
					StringUtil.isTrimBlank(applyDateStart) ? null
							: applyDateStart,
					StringUtil.isTrimBlank(applyDateEnd) ? null : applyDateEnd,
					12);
		}
		indexPager += 10;
	}

	private ShowListInfoService showListInfoService = new ShowListInfoService() {

		@Override
		public void handlerShowListInfo(Context context,
				HandleResult paramHandleResult, int paramInt) {
			loadfinish = true; // 加载完成
			switch (paramInt) {
			case 1:// 预受理
				if (paramHandleResult.getGetWssq_Yslyw_List_InfoSuccess()
						.equals("success")) {
					if (lv_base.getAdapter() == null) {
						Yslyw_list = new ArrayList<Wssq_Yslyw_List_Result>();
						yslyw_myAdapter = new Wssq_Yslyw_ListAdapter(context,
								Yslyw_list);

						addFoot();
						lv_base.setAdapter(yslyw_myAdapter);
					}

					int size = paramHandleResult.getWssq_Yslyw_List_Results()
							.size();
					Count += size;
					Yslyw_list.addAll(paramHandleResult
							.getWssq_Yslyw_List_Results());
					yslyw_myAdapter.notifyDataSetChanged();
					tishiInfo(size, Count);
				} else {
					if (yslyw_myAdapter == null) {
						showToastLong("目前没有预受理信息");
					} else {
						isMore = false;
						if (isClickSelect
								&& paramHandleResult
										.getWssq_Yslyw_List_Results().size() < 1
								&& lv_base.getAdapter().getCount() != 0) {
							Yslyw_list.clear();
							yslyw_myAdapter.notifyDataSetChanged();

						}
						tvMore.setText("共 " + Count + "  条记录");
						pbMore.setVisibility(View.GONE);
					}
				}
				break;
			case 2:// 已受理
				if (paramHandleResult.getGetSjslYislywListInfoSuccess().equals(
						"success")) {
					if (lv_base.getAdapter() == null) {
						Yislyw_list = new ArrayList<Sjsl_Yislyw_List_Result>();
						Yslyw_myadapter = new Sjsl_Yslyw_ListAdapter(context,
								Yislyw_list);

						addFoot();
						lv_base.setAdapter(Yslyw_myadapter);
					}
					int size = paramHandleResult.getSjsl_Yislyw_List_Results()
							.size();
					Count += size;
					Yislyw_list.addAll(paramHandleResult
							.getSjsl_Yislyw_List_Results());// 把记录全部添加到集合当中
					Yslyw_myadapter.notifyDataSetChanged();// 刷新listview
					tishiInfo(size, Count);
				} else {
					if (Yslyw_myadapter == null) {
						showToastLong("目前没有已受理信息");
					} else {
						isMore = false;
						if (isClickSelect
								&& paramHandleResult
										.getSjsl_Yislyw_List_Results().size() < 1
								&& lv_base.getAdapter().getCount() != 0) {
							Yislyw_list.clear();
							Yslyw_myadapter.notifyDataSetChanged();
						}
						tvMore.setText("共 " + Count + "  条记录");
						pbMore.setVisibility(View.GONE);
					}

				}
				break;
			case 3:// 不予受理
				if (paramHandleResult.getGetSjsl_Byuslyw_List_InfoSuccess()
						.equals("success")) {
					if (lv_base.getAdapter() == null) {
						Byuslyw_list = new ArrayList<Sjsl_Byuslyw_List_Result>();
						Byuslyw_myAdapter = new Sjsl_Byuslyw_ListAdapter(
								context, Byuslyw_list);

						addFoot();
						lv_base.setAdapter(Byuslyw_myAdapter);
					}
					int size = paramHandleResult.getSjsl_Byuslyw_List_Results()
							.size();
					Count += size;
					Byuslyw_list.addAll(paramHandleResult
							.getSjsl_Byuslyw_List_Results());// 把记录全部添加到集合当中
					Byuslyw_myAdapter.notifyDataSetChanged();// 刷新listview
					tishiInfo(size, Count);

				} else {
					if (Byuslyw_myAdapter == null) {
						showToastLong("目前没有不予受理信息");
					} else {
						isMore = false;
						if (isClickSelect
								&& paramHandleResult
										.getSjsl_Byuslyw_List_Results().size() < 1
								&& lv_base.getAdapter().getCount() != 0) {
							Byuslyw_list.clear();
							Byuslyw_myAdapter.notifyDataSetChanged();
						}
						tvMore.setText("共 " + Count + "  条记录");
						pbMore.setVisibility(View.GONE);
					}
				}
				break;
			case 4:// 待录入
				if (paramHandleResult.getGetBdlr_Dlrbd_List_InfoSuccess()
						.equals("success")) {
					if (lv_base.getAdapter() == null) {
						Dlrbd_list = new ArrayList<Bdlr_Dlrbd_List_Result>();
						Dlrbd_myadapter = new Bdlr_Dlrbd_ListAdapter(context,
								Dlrbd_list);

						addFoot();
						lv_base.setAdapter(Dlrbd_myadapter);
					}

					int size = paramHandleResult.getBdlr_Dlrbd_List_Results()
							.size();
					Count += size;
					Dlrbd_list.addAll(paramHandleResult
							.getBdlr_Dlrbd_List_Results());
					Dlrbd_myadapter.notifyDataSetChanged();
					tishiInfo(size, Count);
				} else {
					if (Dlrbd_myadapter == null) {
						showToastLong("目前没有待录入表单信息");
					} else {
						isMore = false;
						if (isClickSelect
								&& paramHandleResult
										.getBdlr_Dlrbd_List_Results().size() < 1
								&& lv_base.getAdapter().getCount() != 0) {
							Dlrbd_list.clear();
							Dlrbd_myadapter.notifyDataSetChanged();
						}
						tvMore.setText("共 " + Count + "  条记录");
						pbMore.setVisibility(View.GONE);
					}
				}
				break;
			case 5:// 已录入
				if (paramHandleResult.getGetBdlr_Ylrbd_List_InfoSuccess()
						.equals("success")) {
					if (lv_base.getAdapter() == null) {
						Ylrbd_list = new ArrayList<Bdlr_Ylrbd_List_Result>();
						ylrbd_myadapter = new Bdlr_ylrbd_ListAdapter(context,
								Ylrbd_list);

						addFoot();
						lv_base.setAdapter(ylrbd_myadapter);
					}
					int size = paramHandleResult.getBdlr_Ylrbd_List_Results()
							.size();
					Count += size;
					Ylrbd_list.addAll(paramHandleResult
							.getBdlr_Ylrbd_List_Results());
					ylrbd_myadapter.notifyDataSetChanged();
					tishiInfo(size, Count);
				} else {
					if (ylrbd_myadapter == null) {
						showToastLong("目前没有已录入表单信息");
					} else {
						isMore = false;
						if (isClickSelect
								&& paramHandleResult
										.getBdlr_Ylrbd_List_Results().size() < 1
								&& lv_base.getAdapter().getCount() != 0) {
							Ylrbd_list.clear();
							ylrbd_myadapter.notifyDataSetChanged();
						}
						tvMore.setText("共 " + Count + "  条记录");
						pbMore.setVisibility(View.GONE);
					}
				}
				break;
			case 6:// 待审批
				if (paramHandleResult.getGetSpcl_Dsp_List_InfoSuccess().equals(
						"success")) {
					if (lv_base.getAdapter() == null) {
						Dsp_list = new ArrayList<Spcl_Dsp_List_Result>();
						Dsp_myadapter = new Spcl_Dsp_ListAdapter(context,
								Dsp_list);

						addFoot();
						lv_base.setAdapter(Dsp_myadapter);
					}
					int size = paramHandleResult.getSpcl_Dsp_List_Results()
							.size();
					Count += size;
					Dsp_list.addAll(paramHandleResult
							.getSpcl_Dsp_List_Results());
					Dsp_myadapter.notifyDataSetChanged();
					tishiInfo(size, Count);
				} else {
					if (Dsp_myadapter == null) {
						showToastLong("目前没有待审批信息");
					} else {
						isMore = false;
						if (isClickSelect
								&& paramHandleResult.getSpcl_Dsp_List_Results()
										.size() < 1
								&& lv_base.getAdapter().getCount() != 0) {
							Dsp_list.clear();
							Dsp_myadapter.notifyDataSetChanged();
						}
						tvMore.setText("共 " + Count + "  条记录");
						pbMore.setVisibility(View.GONE);
					}
				}
				break;
			case 7:// 已审批
				if (paramHandleResult.getGetSpcl_Ysp_List_InfoSuccess().equals(
						"success")) {
					if (lv_base.getAdapter() == null) {
						Ysp_list = new ArrayList<Spcl_Ysp_List_Result>();
						Ysp_myaAdapter = new Spcl_Ysp_ListAdapter(context,
								Ysp_list);

						addFoot();
						lv_base.setAdapter(Ysp_myaAdapter);
					}
					int size = paramHandleResult.getSpcl_Ysp_List_Results()
							.size();
					Count += size;
					Ysp_list.addAll(paramHandleResult
							.getSpcl_Ysp_List_Results());
					Ysp_myaAdapter.notifyDataSetChanged();
					tishiInfo(size, Count);
				} else {
					if (Ysp_myaAdapter == null) {
						showToastLong("目前没有已审批信息");
					} else {
						isMore = false;
						if (isClickSelect
								&& paramHandleResult.getSpcl_Ysp_List_Results()
										.size() < 1
								&& lv_base.getAdapter().getCount() != 0) {
							Ysp_list.clear();
							Ysp_myaAdapter.notifyDataSetChanged();
						}
						tvMore.setText("共 " + Count + "  条记录");
						pbMore.setVisibility(View.GONE);
					}
				}
				break;
			case 8:// 已退回
				if (paramHandleResult.getGetSpcl_Yth_List_InfoSuccess().equals(
						"success")) {
					if (lv_base.getAdapter() == null) {
						Yth_list = new ArrayList<Spcl_Yth_List_Result>();
						Yth_myadapter = new Spcl_Yth_ListAdapter(context,
								Yth_list);

						addFoot();
						lv_base.setAdapter(Yth_myadapter);
					}
					int size = paramHandleResult.getSpcl_Yth_List_Results()
							.size();
					Count += size;
					Yth_list.addAll(paramHandleResult
							.getSpcl_Yth_List_Results());
					Yth_myadapter.notifyDataSetChanged();
					tishiInfo(size, Count);
				} else {
					if (Yth_myadapter == null) {
						showToastLong("目前没有已退回信息");
					} else {
						isMore = false;
						if (isClickSelect
								&& paramHandleResult.getSpcl_Yth_List_Results()
										.size() < 1
								&& lv_base.getAdapter().getCount() != 0) {
							Yth_list.clear();
							Yth_myadapter.notifyDataSetChanged();
						}
						tvMore.setText("共 " + Count + "  条记录");
						pbMore.setVisibility(View.GONE);
					}
				}
				break;
			case 9:// 待发放
				if (paramHandleResult.getGetJgff_Dfflb_List_InfoSuccess()
						.equals("success")) {
					if (lv_base.getAdapter() == null) {
						Dfflb_list = new ArrayList<Jgff_Dfflb_List_Result>();
						Dfflb_myadapter = new Jgff_Dfflb_ListAdapter(context,
								Dfflb_list);

						addFoot();
						lv_base.setAdapter(Dfflb_myadapter);
					}
					int size = paramHandleResult.getJgff_Dfflb_List_Results()
							.size();
					Count += size;
					Dfflb_list.addAll(paramHandleResult
							.getJgff_Dfflb_List_Results());
					Dfflb_myadapter.notifyDataSetChanged();
					tishiInfo(size, Count);
				} else {
					if (Dfflb_myadapter == null) {
						showToastLong("目前没有待发放信息");
					} else {
						isMore = false;
						if (isClickSelect
								&& paramHandleResult
										.getJgff_Dfflb_List_Results().size() < 1
								&& lv_base.getAdapter().getCount() != 0) {
							Dfflb_list.clear();
							Dfflb_myadapter.notifyDataSetChanged();
						}
						tvMore.setText("共 " + Count + "  条记录");
						pbMore.setVisibility(View.GONE);
					}
				}
				break;
			case 10:// 已发放
				if (paramHandleResult.getGetJgff_Yfflb_List_InfoSuccess()
						.equals("success")) {
					if (lv_base.getAdapter() == null) {
						Yfflb_list = new ArrayList<Jgff_Yfflb_List_Result>();
						Yfflb_myadapter = new Jgff_Yfflb_ListAdapter(context,
								Yfflb_list);

						addFoot();
						lv_base.setAdapter(Yfflb_myadapter);
					}
					int size = paramHandleResult.getJgff_Yfflb_List_Results()
							.size();
					Count += size;
					Yfflb_list.addAll(paramHandleResult
							.getJgff_Yfflb_List_Results());
					Yfflb_myadapter.notifyDataSetChanged();
					tishiInfo(size, Count);
				} else {
					if (Yfflb_myadapter == null) {
						showToastLong("目前没有已发放信息");
					} else {
						isMore = false;
						if (isClickSelect
								&& paramHandleResult
										.getJgff_Yfflb_List_Results().size() < 1
								&& lv_base.getAdapter().getCount() != 0) {
							Yfflb_list.clear();
							Yfflb_myadapter.notifyDataSetChanged();
						}
						tvMore.setText("共 " + Count + "  条记录");
						pbMore.setVisibility(View.GONE);
					}
				}
				break;
			case 11:// 到期未办结
				if (paramHandleResult.getGetJgff_Dqwbjlb_List_InfoSuccess()
						.equals("success")) {
					if (lv_base.getAdapter() == null) {
						Dqwbjlb_list = new ArrayList<Jgff_Dqwbjlb_List_Result>();
						Dqwbjlb_myAdapter = new Jgff_Dqwbjlb_ListAdapter(
								context, Dqwbjlb_list);

						addFoot();
						lv_base.setAdapter(Dqwbjlb_myAdapter);
					}
					int size = paramHandleResult.getJgff_Dqwbjlb_List_Results()
							.size();
					Count += size;
					Dqwbjlb_list.addAll(paramHandleResult
							.getJgff_Dqwbjlb_List_Results());
					Dqwbjlb_myAdapter.notifyDataSetChanged();
					tishiInfo(size, Count);
				} else {
					if (Dqwbjlb_myAdapter == null) {
						showToastLong("目前没有到期未办结信息");
					} else {
						isMore = false;
						if (isClickSelect
								&& paramHandleResult
										.getJgff_Dqwbjlb_List_Results().size() < 1
								&& lv_base.getAdapter().getCount() != 0) {
							Dqwbjlb_list.clear();
							Dqwbjlb_myAdapter.notifyDataSetChanged();
						}
						tvMore.setText("共 " + Count + "  条记录");
						pbMore.setVisibility(View.GONE);
					}
				}
				break;
			case 12:// 业务档案
				if (paramHandleResult.getGetYwda_Ywdacx_List_InfoSuccess()
						.equals("success")) {
					if (lv_base.getAdapter() == null) {
						Ywdacx_list = new ArrayList<Ywda_Ywdacx_List_Result>();
						Ywdacx_myadapter = new Ywda_Ywdacx_ListAdapter(context,
								Ywdacx_list);

						addFoot();
						lv_base.setAdapter(Ywdacx_myadapter);
					}
					int size = paramHandleResult.getYwda_Ywdacx_List_Results()
							.size();
					Count += size;
					Ywdacx_list.addAll(paramHandleResult
							.getYwda_Ywdacx_List_Results());
					Ywdacx_myadapter.notifyDataSetChanged();
					tishiInfo(size, Count);
				} else {
					if (Ywdacx_myadapter == null) {
						showToastLong("目前没有业务档案查询信息");
					} else {
						isMore = false;
						if (isClickSelect
								&& paramHandleResult
										.getYwda_Ywdacx_List_Results().size() < 1
								&& lv_base.getAdapter().getCount() != 0) {
							Ywdacx_list.clear();
							Ywdacx_myadapter.notifyDataSetChanged();
						}
						tvMore.setText("共 " + Count + "  条记录");
						pbMore.setVisibility(View.GONE);
					}
				}
				break;
			}
		}
	};

	public void tishiInfo(int size1, int count1) {
		if (size1 < Constants.PAGE_SIZE_LARGE) {// 当后台加载的数据小于十条的时候
			isMore = false;// 表示没满
		} else {
			isMore = true;// 否则表示满了
		}
		tvMore.setText(size1 < Constants.PAGE_SIZE_LARGE ? "共 " + count1
				+ " 条记录" : "更多记录...");
		pbMore.setVisibility(View.GONE);// 隐藏圆形进度条
	}

	/**
	 * 启动异步线程调用接口
	 * 
	 * @param account
	 *            用户登录账号
	 * @param type
	 *            类型 0 预受理,1 已受理,2 不予受理,3 待录入表单,4 已录入表单,5 待审批, 6 已审批,7 已退回,8
	 *            待发放,9 已发放,10 到期未办结,11 业务档案
	 * @param pageSize
	 *            分页的页数
	 * @param pageCount
	 *            每页的数量
	 * @param serviceTargetId
	 *            身份证号
	 * @param serialNumber
	 *            流水号
	 * @param serviceTargetName
	 *            申请人姓名
	 * @param serviceSubjectName
	 *            事项名称
	 * @param applyDateStart
	 *            申请开始日期
	 * @param applyDateEnd
	 *            申请结束日期
	 * @param paramInt
	 *            判断当前回调参数
	 */
	private void loadService(String account, String type, String pageSize,
			String pageCount, String serviceTargetId, String serialNumber,
			String serviceTargetName, String serviceSubjectName,
			String applyDateStart, String applyDateEnd, int paramInt) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> oMap = new HashMap<String, Object>();
		oMap.put("account", account);
		oMap.put("type", type);
		oMap.put("pageSize", pageSize);
		oMap.put("pageCount", pageCount);
		oMap.put("serviceTargetId", serviceTargetId);
		oMap.put("serialNumber", serialNumber);
		oMap.put("serviceTargetName", serviceTargetName);
		oMap.put("serviceSubjectName", serviceSubjectName);
		oMap.put("applyDateStart", applyDateStart);
		oMap.put("applyDateEnd", applyDateEnd);
		list.add(oMap);
		showListInfoService.getListInfo(context, paramInt, list);
	}

	public void initTitle(boolean flay) {
		if (Constants.title.equals(context.getResources().getString(
				R.string.yushouliyewu))) {

			if (flay) {
				rl_title.setBackgroundDrawable(getResources().getDrawable(
						R.drawable.tt_yuslyw));
			} else {
				// 调用接口返回数据
				loadService(Constants.account, String.valueOf(0),
						String.valueOf(indexPager),
						String.valueOf(Constants.PAGE_SIZE_LARGE), null, null,
						null, null, null, null, 1);
				indexPager += 10;
			}

		}
		if (Constants.title.equals(context.getResources().getString(
				R.string.shoujiandengji))) {
			if (flay) {
				rl_title.setBackgroundDrawable(getResources().getDrawable(
						R.drawable.tt_sjdj));
			}
		}
		if (Constants.title.equals(context.getResources().getString(
				R.string.yishouliyewu))) {
			if (flay) {
				rl_title.setBackgroundDrawable(getResources().getDrawable(
						R.drawable.tt_yslyw));
			} else {

				// 调用接口返回数据
				loadService(Constants.account, String.valueOf(1),
						String.valueOf(indexPager),
						String.valueOf(Constants.PAGE_SIZE_LARGE), null, null,
						null, null, null, null, 2);
				indexPager += 10;
			}
		}
		if (Constants.title.equals(context.getResources().getString(
				R.string.buyushouliyewu))) {
			if (flay) {
				rl_title.setBackgroundDrawable(getResources().getDrawable(
						R.drawable.tt_byslyw));
			} else {

				// 调用接口返回数据
				loadService(Constants.account, String.valueOf(2),
						String.valueOf(indexPager),
						String.valueOf(Constants.PAGE_SIZE_LARGE), null, null,
						null, null, null, null, 3);
				indexPager += 10;
			}
		}
		if (Constants.title.equals(context.getResources().getString(
				R.string.dailurubiaodan))) {
			if (flay) {
				rl_title.setBackgroundDrawable(getResources().getDrawable(
						R.drawable.tt_dllbd));
			} else {
				// 调用接口返回数据
				loadService(Constants.account, String.valueOf(3),
						String.valueOf(indexPager),
						String.valueOf(Constants.PAGE_SIZE_LARGE), null, null,
						null, null, null, null, 4);
				indexPager += 10;
			}
		}
		if (Constants.title.equals(context.getResources().getString(
				R.string.yilurubiaodan))) {
			if (flay) {
				rl_title.setBackgroundDrawable(getResources().getDrawable(
						R.drawable.tt_yllbd));
			} else {
				// 调用接口返回数据
				loadService(Constants.account, String.valueOf(4),
						String.valueOf(indexPager),
						String.valueOf(Constants.PAGE_SIZE_LARGE), null, null,
						null, null, null, null, 5);
				indexPager += 10;
			}
		}
		if (Constants.title.equals(context.getResources().getString(
				R.string.daishenpi))) {
			if (flay) {
				rl_title.setBackgroundDrawable(getResources().getDrawable(
						R.drawable.tt_dsp));
			} else {
				// 调用接口
				loadService(Constants.account, String.valueOf(5),
						String.valueOf(indexPager),
						String.valueOf(Constants.PAGE_SIZE_LARGE), null, null,
						null, null, null, null, 6);
				indexPager += 10;
			}

		}
		if (Constants.title.equals(context.getResources().getString(
				R.string.yishenpi))) {
			if (flay) {
				rl_title.setBackgroundDrawable(getResources().getDrawable(
						R.drawable.tt_ysp));
			} else {
				// 调用接口
				loadService(Constants.account, String.valueOf(6),
						String.valueOf(indexPager),
						String.valueOf(Constants.PAGE_SIZE_LARGE), null, null,
						null, null, null, null, 7);
				indexPager += 10;
			}

		}
		if (Constants.title.equals(context.getResources().getString(
				R.string.yituihui))) {
			if (flay) {
				rl_title.setBackgroundDrawable(getResources().getDrawable(
						R.drawable.tt_yth));
			} else {
				// 调用接口
				loadService(Constants.account, String.valueOf(7),
						String.valueOf(indexPager),
						String.valueOf(Constants.PAGE_SIZE_LARGE), null, null,
						null, null, null, null, 8);
				indexPager += 10;
			}

		}
		if (Constants.title.equals(context.getResources().getString(
				R.string.daifafangliebiao))) {
			if (flay) {
				rl_title.setBackgroundDrawable(getResources().getDrawable(
						R.drawable.tt_dfflb));
			} else {
				// 调用接口
				loadService(Constants.account, String.valueOf(8),
						String.valueOf(indexPager),
						String.valueOf(Constants.PAGE_SIZE_LARGE), null, null,
						null, null, null, null, 9);
				indexPager += 10;
			}

		}
		if (Constants.title.equals(context.getResources().getString(
				R.string.yifafangliebiao))) {
			if (flay) {
				rl_title.setBackgroundDrawable(getResources().getDrawable(
						R.drawable.tt_yfflb));
			} else {
				// 调用接口
				loadService(Constants.account, String.valueOf(9),
						String.valueOf(indexPager),
						String.valueOf(Constants.PAGE_SIZE_LARGE), null, null,
						null, null, null, null, 10);
				indexPager += 10;
			}

		}
		if (Constants.title.equals(context.getResources().getString(
				R.string.daoqiweibanjieliebiao))) {
			if (flay) {
				rl_title.setBackgroundDrawable(getResources().getDrawable(
						R.drawable.tt_dqwbjlb));
			} else {
				// 调用接口
				loadService(Constants.account, String.valueOf(10),
						String.valueOf(indexPager),
						String.valueOf(Constants.PAGE_SIZE_LARGE), null, null,
						null, null, null, null, 11);
				indexPager += 10;
			}

		}
		if (Constants.title.equals(context.getResources().getString(
				R.string.yewudanganchaxun))) {
			if (flay) {
				rl_title.setBackgroundDrawable(getResources().getDrawable(
						R.drawable.tt_ywdacx));
			} else {
				// 调用接口
				loadService(Constants.account, String.valueOf(11),
						String.valueOf(indexPager),
						String.valueOf(Constants.PAGE_SIZE_LARGE), null, null,
						null, null, null, null, 12);
				indexPager += 10;
			}

		}
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

	private boolean loadfinish = true; // 指示数据是否加载完成

	private final class ScrollListener implements OnScrollListener {
		private int number = 10; // 每次获取多少条数据

		@Override
		public void onScroll(AbsListView view, int firstVisibleItem,
				int visibleItemCount, int totalItemCount) {

			final int loadtotal = totalItemCount;
			int lastItemid = lv_base.getLastVisiblePosition(); // 获取当前屏幕最后Item的ID
			if ((lastItemid + 1) == totalItemCount) { // 达到数据的最后一条记录

				if (totalItemCount > 0) {
					// 当前页
					int currentpage = totalItemCount % number == 0 ? totalItemCount
							/ number
							: totalItemCount / number + 1;
					int nextpage = currentpage + 1; // 下一页
					if (loadfinish && isMore) {
						loadfinish = false;

						String serviceTargetId = isClickSelect ? edt_search_2
								.getText().toString() : null;// 身份证号
						String serialNumber = isClickSelect ? edt_search_4
								.getText().toString() : null;// 流水号
						String serviceTargetName = isClickSelect ? edt_search_1
								.getText().toString() : null;// 申请人姓名
						String serviceSubjectName = isClickSelect ? edt_search_3
								.getText().toString() : null;// 事项名称
						String applyDateStart = isClickSelect ? tv_search_2
								.getText().toString() : null;// 申请开始日期
						String applyDateEnd = isClickSelect ? tv_search_3
								.getText().toString() : null;// 申请结束日期
						if (Constants.title.equals(context.getResources()
								.getString(R.string.yushouliyewu))) {
							getInfoRecoder(serviceTargetId, serialNumber,
									serviceTargetName, serviceSubjectName,
									applyDateStart, applyDateEnd);// 重新获取六个参数
						}
						if (Constants.title.equals(context.getResources()
								.getString(R.string.yishouliyewu))) {// scrollState
																		// 0停止滚动
																		// 1屏幕滚动且用户使用的触碰或手指还在屏幕上
																		// 2屏幕产生惯性滑动时
							getInfoRecoder(serviceTargetId, serialNumber,
									serviceTargetName, serviceSubjectName,
									applyDateStart, applyDateEnd);// 重新获取六个参数
						}
						if (Constants.title.equals(context.getResources()
								.getString(R.string.buyushouliyewu))) {
							getInfoRecoder(serviceTargetId, serialNumber,
									serviceTargetName, serviceSubjectName,
									applyDateStart, applyDateEnd);// 重新获取六个参数
						}
						if (Constants.title.equals(context.getResources()
								.getString(R.string.dailurubiaodan))) {
							getInfoRecoder(serviceTargetId, serialNumber,
									serviceTargetName, serviceSubjectName,
									applyDateStart, applyDateEnd);// 重新获取六个参数
						}
						if (Constants.title.equals(context.getResources()
								.getString(R.string.yilurubiaodan))) {
							getInfoRecoder(serviceTargetId, serialNumber,
									serviceTargetName, serviceSubjectName,
									applyDateStart, applyDateEnd);// 重新获取六个参数
						}
						if (Constants.title.equals(context.getResources()
								.getString(R.string.daishenpi))) {
							getInfoRecoder(serviceTargetId, serialNumber,
									serviceTargetName, serviceSubjectName,
									applyDateStart, applyDateEnd);// 重新获取六个参数
						}
						if (Constants.title.equals(context.getResources()
								.getString(R.string.yishenpi))) {
							getInfoRecoder(serviceTargetId, serialNumber,
									serviceTargetName, serviceSubjectName,
									applyDateStart, applyDateEnd);// 重新获取六个参数
						}
						if (Constants.title.equals(context.getResources()
								.getString(R.string.yituihui))) {
							getInfoRecoder(serviceTargetId, serialNumber,
									serviceTargetName, serviceSubjectName,
									applyDateStart, applyDateEnd);// 重新获取六个参数
						}
						if (Constants.title.equals(context.getResources()
								.getString(R.string.daifafangliebiao))) {
							getInfoRecoder(serviceTargetId, serialNumber,
									serviceTargetName, serviceSubjectName,
									applyDateStart, applyDateEnd);// 重新获取六个参数
						}
						if (Constants.title.equals(context.getResources()
								.getString(R.string.yifafangliebiao))) {
							getInfoRecoder(serviceTargetId, serialNumber,
									serviceTargetName, serviceSubjectName,
									applyDateStart, applyDateEnd);// 重新获取六个参数
						}
						if (Constants.title.equals(context.getResources()
								.getString(R.string.daoqiweibanjieliebiao))) {
							getInfoRecoder(serviceTargetId, serialNumber,
									serviceTargetName, serviceSubjectName,
									applyDateStart, applyDateEnd);// 重新获取六个参数
						}
						if (Constants.title.equals(context.getResources()
								.getString(R.string.yewudanganchaxun))) {
							getInfoRecoder(serviceTargetId, serialNumber,
									serviceTargetName, serviceSubjectName,
									applyDateStart, applyDateEnd);// 重新获取六个参数
						}
					}
				}
			}
		}

		@Override
		public void onScrollStateChanged(AbsListView view, int scrollState) {
		}
	}
}
