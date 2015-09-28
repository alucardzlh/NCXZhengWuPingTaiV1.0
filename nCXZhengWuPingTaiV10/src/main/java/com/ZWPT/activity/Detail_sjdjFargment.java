package com.ZWPT.activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.ZWPT.Utils.CommUtil;
import com.ZWPT.data.Constants;
import com.ZWPT.data.DataHelper;
import com.ZWPT.data.InfoFile_;
import com.ZWPT.data.entity.Detail_Sjdj_bean;
import com.googlecode.androidannotations.annotations.AfterViews;
import com.googlecode.androidannotations.annotations.Bean;
import com.googlecode.androidannotations.annotations.EFragment;
import com.googlecode.androidannotations.annotations.ViewById;
import com.googlecode.androidannotations.annotations.sharedpreferences.Pref;

/**
 * 滚动标题栏-收件登记
 * 
 * @author durenchong
 * @date 2014-06-18
 */
@EFragment(R.layout.fragment_detail_sjdj)
public class Detail_sjdjFargment extends BaseFragment {
	@Bean
	DataHelper dataHelper;
	@Pref
	InfoFile_ InfoFile_;
	// 登记信息
	@ViewById
	TextView tv_sjdj_djxx_1, tv_sjdj_djxx_2, tv_sjdj_djxx_3, tv_sjdj_djxx_4,
			tv_sjdj_djxx_5, tv_sjdj_djxx_6, name_1, name_2, name_3, name_4,
			name_5, name_6, dzzz, sfsq;
	@ViewById
	LinearLayout ll_6, ll_sjdj_tjcl_checkbox;
	// 办理条件情况 提交材料情况
	@ViewById
	ListView lv_detail_sjdj_tjcl, lv_detail_sjdj_bltj,
			lv_detail_sjdj_tjcl_checks;

	@ViewById
	Button btn_check_tiaojian;

	ListView lv_sjdj_shouli_tjcl_checkbox;
	AlertDialog dialog;

	Detail_Sjdj_bean detail_Sjdj_bean = null;

	@AfterViews
	void initView() {
		if(detail_Sjdj_bean == null){
			return;
		}
		if (Constants.title.equals(Detail_sjdjFargment.this.getResources()
				.getString(R.string.yushouliyewu))) {
			name_1.setText("网上预受理号");
			name_3.setText("联系号码");
			name_4.setText("电子邮箱");
			name_5.setText("申请日期");
			ll_6.setVisibility(View.GONE);
			dzzz.setVisibility(View.GONE);
			sfsq.setVisibility(View.GONE);
			tv_sjdj_djxx_1.setText(checkMapValue(detail_Sjdj_bean
					.getSerialNumber_ysl()));
			tv_sjdj_djxx_2.setText(checkMapValue(detail_Sjdj_bean
					.getServiceTargetName()));
			tv_sjdj_djxx_3.setText(checkMapValue(detail_Sjdj_bean
					.getServiceTargetPhone()));
			tv_sjdj_djxx_4.setText(checkMapValue(detail_Sjdj_bean
					.getServiceTargetEmail()));
			tv_sjdj_djxx_5.setText(checkMapValue(detail_Sjdj_bean
					.getApplyDate_ysl()));
		} else {
			tv_sjdj_djxx_1.setText(checkMapValue(detail_Sjdj_bean
					.getSerialNumber()));
			tv_sjdj_djxx_2.setText(checkMapValue(detail_Sjdj_bean
					.getServiceTargetName()));
			tv_sjdj_djxx_3.setText(checkMapValue(detail_Sjdj_bean
					.getApplyDate()));
			tv_sjdj_djxx_4.setText(checkMapValue(detail_Sjdj_bean
					.getServiceOrgName()));
			tv_sjdj_djxx_5.setText(checkMapValue(detail_Sjdj_bean
					.getHandleDate()));
			tv_sjdj_djxx_6.setText(checkMapValue(detail_Sjdj_bean
					.getOperatorName()));
		}

		lv_detail_sjdj_bltj.setAdapter(new Detail_sjdj_bltj_ListAdapter(
				detail_Sjdj_bean.getList_bltj()));
		CommUtil.setListViewHeightBasedOnChildren(lv_detail_sjdj_bltj);
		lv_detail_sjdj_tjcl.setAdapter(new Detail_sjdj_tjcl_ListAdapter(
				detail_Sjdj_bean.getList_blcl()));
		CommUtil.setListViewHeightBasedOnChildren(lv_detail_sjdj_tjcl);
		if (detail_Sjdj_bean.getList_blfztj() == null
				|| detail_Sjdj_bean.getList_blfztj().size() == 0) {
			ll_sjdj_tjcl_checkbox.setVisibility(View.GONE);
		} else {
			ll_sjdj_tjcl_checkbox.setVisibility(View.VISIBLE);
		}
		btn_check_tiaojian.setOnClickListener(OnClickListener);
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO 自动生成的方法存根
		super.onCreate(savedInstanceState);
		Bundle bundle = new Bundle();
		bundle = this.getArguments();
		detail_Sjdj_bean = (Detail_Sjdj_bean) bundle
				.getSerializable("Detail_Sjdj_bean");
	}

	public class Detail_sjdj_bltj_ListAdapter extends BaseAdapter {
		private LayoutInflater inflater;
		private List<String> datas;

		public Detail_sjdj_bltj_ListAdapter(List<String> datas) {
			this.datas = datas;
			inflater = LayoutInflater.from(context);
		}

		public int getCount() {
			return datas.size();
		}

		public Object getItem(int position) {
			return datas.get(position);
		}

		public long getItemId(int position) {
			return position;
		}

		public View getView(final int position, View convertView,
				ViewGroup parent) {
			final ViewHolder holder;
			if (convertView == null) {
				holder = new ViewHolder();
				convertView = inflater.inflate(
						R.layout.fragment_detail_sjdj_bltjqk_item, null);
				holder.tvText_name = (TextView) convertView
						.findViewById(R.id.tv_detail_sjdj_bltjqk);
				convertView.setTag(holder);
			} else {
				holder = (ViewHolder) convertView.getTag();
			}
			holder.tvText_name.setText(datas.get(position));
			holder.tvText_name.setMovementMethod(ScrollingMovementMethod.getInstance()); 
//			holder.tvText_name.setOnClickListener(new OnClickListener() {
//				
//				@Override
//				public void onClick(View v) {
//					Toast.makeText(context, (String) datas.get(position), Toast.LENGTH_SHORT).show();
//				}
//			});
			
			
			return convertView;
		}

		class ViewHolder {
			TextView tvText_name;
			boolean flag;
		}

		public List<String> getHisInspectRecords() {
			return datas;
		}

		public void setNotifications(List<String> datas) {
			this.datas = datas;
		}
	}

	public class Detail_sjdj_tjcl_ListAdapter extends BaseAdapter {
		private LayoutInflater inflater;
		private List<Map<String, Object>> datas;

		public Detail_sjdj_tjcl_ListAdapter(List<Map<String, Object>> datas) {
			this.datas = datas;
			inflater = LayoutInflater.from(context);
		}

		public int getCount() {
			return datas.size();
		}

		public Object getItem(int position) {
			return datas.get(position);
		}

		public long getItemId(int position) {
			return position;
		}

		public View getView(final int position, View convertView,
				ViewGroup parent) {
			final ViewHolder holder;
			if (convertView == null) {
				holder = new ViewHolder();
				convertView = inflater.inflate(
						R.layout.fragment_detail_sjdj_tjclqk_item, null);
				holder.tvText_name = (TextView) convertView
						.findViewById(R.id.tv_detail_sjdj_tjcl_1);
				holder.tvText_name_2 = (TextView) convertView
						.findViewById(R.id.tv_detail_sjdj_tjcl_2);
				holder.ib = (ImageButton) convertView
						.findViewById(R.id.ib_detail_sjdj_dzzz);
				convertView.setTag(holder);
			} else {
				holder = (ViewHolder) convertView.getTag();
			}
			if (datas.get(position).get("yscl").equals("true")) {
				holder.tvText_name_2.setText("可选择收取");
			} else if (datas.get(position).get("yscl").equals("false")) {
				holder.tvText_name_2.setText("不收取");
			}
			holder.tvText_name
					.setText((String) datas.get(position).get("name"));
			// holder.tvText_name_2.setText((String)datas.get(position).get("yscl"));
			holder.tvText_name.setMovementMethod(ScrollingMovementMethod.getInstance()); 
//			holder.tvText_name.setOnClickListener(new OnClickListener() {
//				
//				@Override
//				public void onClick(View v) {
//					Toast.makeText(context, (String) datas.get(position).get("name"), Toast.LENGTH_SHORT).show();
//				}
//			});
			
			holder.ib.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					if (((String) datas.get(position).get("imgUrl")).trim()
							.length() > 0) {
						Intent intent = new Intent(context,
								PritureShowActivity_.class);
						intent.putExtra("pritureUrl",
								(String) datas.get(position).get("imgUrl"));
						intent.putExtra("postinfoname",
								(String) datas.get(position).get("name"));
						startActivity(intent);
					}
				}
			});
			if (Constants.title.equals(Detail_sjdjFargment.this.getResources()
					.getString(R.string.yushouliyewu))) {
				holder.tvText_name_2.setVisibility(View.INVISIBLE);
				holder.ib.setVisibility(View.INVISIBLE);
			}
			if (datas.get(position).get("imgUrl").equals("")) {
				holder.ib.setVisibility(View.INVISIBLE);
			}
			return convertView;
		}

		class ViewHolder {
			TextView tvText_name;
			TextView tvText_name_2;
			ImageButton ib;
			boolean flag;
		}

		public List<Map<String, Object>> getHisInspectRecords() {
			return datas;
		}

		public void setNotifications(List<Map<String, Object>> datas) {
			this.datas = datas;
		}
	}

	private String checkMapValue(String str) {
		if (str == null ||str.contains("null") ||  str.equals("")) {
			str = "-";
		}
		return str;
	}

	public void showTJCL_Dialog() {
		dialog = new AlertDialog.Builder(context).create();
		dialog.show();
		Window window = dialog.getWindow();
		window.setContentView(R.layout.sjdj_shouli_tjcl_checkbox_dialog);
		LayoutInflater inflater = LayoutInflater.from(context);

		View convertView = inflater.inflate(
				R.layout.sjdj_shouli_tjcl_checkbox_dialog, null);
		lv_sjdj_shouli_tjcl_checkbox = (ListView) window
				.findViewById(R.id.lv_sjdj_shouli_tjcl_checkbox);
		Button btn_ensure = (Button) window.findViewById(R.id.btnEnsure);
		Button btn_cancle = (Button) window.findViewById(R.id.btn_cancel);
		btn_ensure.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				dialog.cancel();
			}
		});
		btn_cancle.setVisibility(View.GONE);
		for (int i = 0; i < detail_Sjdj_bean.getList_blfztj().size(); i++) {
			Map<String, Object> map_middle = new HashMap<String, Object>();
			map_middle.put("rg_id", i);
			map_middle.put("list_tjcl", new ArrayList<Map<String, Object>>());
			Constants.list_middle.add(map_middle);
		}
		lv_sjdj_shouli_tjcl_checkbox
				.setAdapter(new Deatil_Sjdj_tjcl_check_dialog_ListAdapter(
						context, detail_Sjdj_bean.getList_blfztj()));
	}

	public class Deatil_Sjdj_tjcl_check_dialog_ListAdapter extends BaseAdapter {

		private LayoutInflater inflater;
		private Context context = null;
		private List<Map<String, Object>> datas;
		int sizeofrb = 0;

		public Deatil_Sjdj_tjcl_check_dialog_ListAdapter(Context context,
				List<Map<String, Object>> datas) {
			this.datas = datas;
			inflater = LayoutInflater.from(context);
			this.context = context;

		}

		public int getCount() {

			return datas.size();
		}

		public Object getItem(int position) {
			return datas.get(position);
		}

		public long getItemId(int position) {
			return position;
		}

		public View getView(final int position, View convertView,
				ViewGroup parent) {
			final ViewHolder holder;
			// if (convertView == null) {
			holder = new ViewHolder();

			convertView = inflater.inflate(
					R.layout.sjdj_shouli_tjcl_dialog_list_item, null);
			holder.tvText_name = (TextView) convertView
					.findViewById(R.id.tv_sjdj_shouli_bltj_item);
			holder.rg = (RadioGroup) convertView
					.findViewById(R.id.rg_sjdj_tjcl_dialog_rg);

			convertView.setTag(holder);

			// } else {
			//
			// holder = (ViewHolder) convertView.getTag();
			// }
			holder.tvText_name
					.setText((String) datas.get(position).get("name"));
			sizeofrb = ((List<String>) datas.get(position).get("list_second"))
					.size();
			for (Integer i = 0; i < sizeofrb; i++) {
				RadioButton rb = new RadioButton(context);
				rb.setId(i);
				// rb.setButtonDrawable(context.getResources().getDrawable(R.drawable.radiobutton_style));
				rb.setTextColor(context.getResources().getColor(R.color.black));
				rb.setText((String) (((List<String>) (datas.get(position)
						.get("list_second"))).get(i)));
				if (((String) datas.get(position).get("checked")).equals(i + 1
						+ "")) {
					rb.setChecked(true);
				}
				rb.setClickable(false);
				holder.rg.addView(rb, LinearLayout.LayoutParams.FILL_PARENT,
						LinearLayout.LayoutParams.WRAP_CONTENT);
			}

			holder.rg.setClickable(false);

			return convertView;
		}

		class ViewHolder {
			TextView tvText_name;
			RadioGroup rg;
			boolean flag;
		}

		public List<Map<String, Object>> getHisInspectRecords() {
			return datas;
		}

		public void setNotifications(List<Map<String, Object>> datas) {
			this.datas = datas;
		}

	}

	private OnClickListener OnClickListener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			showTJCL_Dialog();

		}
	};

}
