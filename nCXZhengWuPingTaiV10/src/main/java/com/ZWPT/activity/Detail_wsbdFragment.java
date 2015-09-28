package com.ZWPT.activity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ZWPT.data.DataHelper;
import com.ZWPT.data.InfoFile_;
import com.ZWPT.view.FormFillView;
import com.googlecode.androidannotations.annotations.AfterViews;
import com.googlecode.androidannotations.annotations.Bean;
import com.googlecode.androidannotations.annotations.EFragment;
import com.googlecode.androidannotations.annotations.ViewById;
import com.googlecode.androidannotations.annotations.sharedpreferences.Pref;

/**
 * 网上表单
 * 
 * @author durenchong
 * @date 201-06-18
 */
@EFragment(R.layout.fragment_detail_wsbd)
public class Detail_wsbdFragment extends BaseFragment {
	@Bean
	DataHelper dataHelper;
	@Pref
	InfoFile_ infoFile_;
	@ViewById
	TextView tv_list_item_2, tv_list_item_1;
	// @ViewById
	// ListView lv_base;
	@ViewById
	LinearLayout ll_form;
	List<Map<String, Object>> mylist = null;

	@AfterViews
	void initView() {
		
		tv_list_item_2.setText(infoFile_.shengqingrenname().get());
		tv_list_item_1.setText(infoFile_.wssq_wsyslh().get());
		// lv_base.setAdapter(new Detail_wsbd_FormAdapter(context,
		// mylist));
		// CommUtil.setListViewHeightBasedOnChildren(lv_base);
		if(mylist == null){
			return;
		}
		FormFillView fillView = new FormFillView(context, mylist, null, false);
		ll_form.addView(fillView.getView());

	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO 自动生成的方法存根
		super.onCreate(savedInstanceState);
		Bundle bundle = new Bundle();
		bundle = this.getArguments();
		mylist = (List<Map<String, Object>>) bundle.getSerializable("list");
	}

	public class Detail_wsbd_FormAdapter extends BaseAdapter {

		private LayoutInflater inflater;
		private Context context = null;
		private List<Map<String, Object>> datas;

		public Detail_wsbd_FormAdapter(Context context,
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

			holder = new ViewHolder();
			convertView = inflater.inflate(R.layout.bdlr_luru_edittext, null);
			holder.tv_name_edit = (TextView) convertView
					.findViewById(R.id.tv_beizhu);
			holder.edt_text = (EditText) convertView.findViewById(R.id.edt_job);
			convertView.setTag(holder);
			holder.tv_name_edit.setText((String) datas.get(position)
					.get("name"));
			holder.edt_text.setText(checkMapValue((String) datas.get(position)
					.get("value")));
			holder.edt_text.setEnabled(false);
			holder.edt_text.setClickable(false);
			if (datas.get(position).get("nonEmpty").equals("false")) {
				holder.iv_icon = (ImageView) convertView
						.findViewById(R.id.iv_icon1);
				holder.iv_icon.setVisibility(View.VISIBLE);
			}
			return convertView;
		}

		class ViewHolder {
			TextView tv_name_edit;
			TextView tv_name_spinner;
			TextView tv_spinner;
			TextView tv_name_date;
			TextView tv_name_date_tv;
			ImageView iv_icon;
			EditText edt_text;
			LinearLayout ll_spinner;
			LinearLayout ll_date;
		}

		public List<Map<String, Object>> getHisInspectRecords() {
			return datas;
		}

		public void setNotifications(List<Map<String, Object>> datas) {
			this.datas = datas;
		}

	}

	private String checkMapValue(String str) {
		if (str.equals("null") || str == null || str.equals("")) {
			str = "-";
		}
		return str;
	}
}
