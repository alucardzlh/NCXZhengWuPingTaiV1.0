package com.ZWPT.activity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.ZWPT.Utils.CommUtil;
import com.ZWPT.data.DataHelper;
import com.ZWPT.data.InfoFile_;
import com.googlecode.androidannotations.annotations.AfterViews;
import com.googlecode.androidannotations.annotations.Bean;
import com.googlecode.androidannotations.annotations.EFragment;
import com.googlecode.androidannotations.annotations.ViewById;
import com.googlecode.androidannotations.annotations.sharedpreferences.Pref;

/**
 * 附件查看
 * 
 * @author durenchong
 * @date 2014-06-18
 */
@EFragment(R.layout.fragment_detail_fjck)
public class Detail_fjckFragment extends BaseFragment {
	@Bean
	DataHelper dataHelper;
	@Pref
	InfoFile_ infoFile_;
	@ViewById
	ListView lv_base;
	List<Map<String, Object>> mylist = null;

	@AfterViews
	void initView() {
		if(mylist == null){
			return;
		}
		lv_base.setAdapter(new Detail_Fjck_ListAdapter(context, mylist));
		CommUtil.setListViewHeightBasedOnChildren(lv_base);
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO 自动生成的方法存根
		super.onCreate(savedInstanceState);
		Bundle bundle = new Bundle();
		bundle = this.getArguments();
		mylist = (List<Map<String, Object>>) bundle.getSerializable("list");

	}

	public class Detail_Fjck_ListAdapter extends BaseAdapter {

		private LayoutInflater inflater;
		private List<Map<String, Object>> datas;

		public Detail_Fjck_ListAdapter(Context context,
				List<Map<String, Object>> datas) {
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
						R.layout.fragment_detail_fjck_item, null);
				holder.tvText_name = (TextView) convertView
						.findViewById(R.id.tv_name);
				holder.ib = (ImageButton) convertView
						.findViewById(R.id.iv_fjck);
				holder.tv_no = (TextView) convertView
						.findViewById(R.id.tv_fjck);
				convertView.setTag(holder);

			} else {
				holder = (ViewHolder) convertView.getTag();
			}

			holder.tvText_name
					.setText((String) datas.get(position).get("name"));
			if (datas.get(position).get("url").equals("null")) {
				holder.ib.setVisibility(View.GONE);
				holder.tv_no.setVisibility(View.VISIBLE);
			} else {
				holder.ib.setVisibility(View.VISIBLE);
				holder.tv_no.setVisibility(View.GONE);
			}
			holder.ib.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					Intent intent = new Intent();
					intent.setAction("android.intent.action.VIEW");
					Uri content_url = Uri.parse((String) datas.get(position)
							.get("url"));
					intent.setData(content_url);
					startActivity(intent);
				}
			});
			return convertView;
		}

		class ViewHolder {
			TextView tvText_name;
			ImageButton ib;
			TextView tv_no;
			boolean flag;
		}

		public List<Map<String, Object>> getHisInspectRecords() {
			return datas;
		}

		public void setNotifications(List<Map<String, Object>> datas) {
			this.datas = datas;
		}

	}
}
