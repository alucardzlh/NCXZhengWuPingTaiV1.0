package com.ZWPT.activity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.ZWPT.Utils.CommUtil;
import com.ZWPT.activity.Detail_sjdjFargment.Detail_sjdj_tjcl_ListAdapter.ViewHolder;
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
@EFragment(R.layout.fragment_detail_ztxx)
public class Detail_ztxxFragment extends BaseFragment {
	@Bean
	DataHelper dataHelper;
	@Pref
	InfoFile_ infoFile_;
	@ViewById
	TextView tvTitle_two_Name;

	@ViewById
	ListView lv_base;

	List<Map<String, Object>> mylist = null;

	@AfterViews
	void initView() {
		if(mylist == null){
			return;
		}
		tvTitle_two_Name.setText("历次暂停信息");
		lv_base.setAdapter(new Detail_ztxx_ListAdapter(mylist));
		CommUtil.setListViewHeightBasedOnChildren(lv_base);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO 自动生成的方法存根
		super.onCreate(savedInstanceState);
		Bundle bundle = new Bundle();
		bundle = this.getArguments();
		mylist = (List<Map<String, Object>>) bundle.getSerializable("list");
	}

	public class Detail_ztxx_ListAdapter extends BaseAdapter {
		private LayoutInflater inflater;
		private List<Map<String, Object>> datas;

		public Detail_ztxx_ListAdapter(List<Map<String, Object>> datas) {
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

		@SuppressWarnings("unchecked")
		public View getView(final int position, View convertView,
				ViewGroup parent) {
			final ViewHolder holder;
			if (convertView == null) {
				holder = new ViewHolder();
				convertView = inflater.inflate(
						R.layout.fragment_detail_ztxx_list_item, null);
				holder.tvText_name_1 = (TextView) convertView
						.findViewById(R.id.tv_list_item_1);
				holder.tvText_name_2 = (TextView) convertView
						.findViewById(R.id.tv_list_item_2);
				holder.tvText_name_3 = (TextView) convertView
						.findViewById(R.id.tv_list_item_3);
				holder.tvText_name_4 = (TextView) convertView
						.findViewById(R.id.tv_list_item_4);
				holder.tvText_name_5 = (TextView) convertView
						.findViewById(R.id.tv_list_item_5);
				holder.tvText_name_6 = (TextView) convertView
						.findViewById(R.id.tv_list_item_6);
				convertView.setTag(holder);
			} else {
				holder = (ViewHolder) convertView.getTag();
			}

			for (Map<String, Object> map : (List<Map<String, Object>>) datas
					.get(position).get("list_item_list")) {
				if (map.get("name").equals("暂停开始时间")) {
					checkMapValue(map);
					holder.tvText_name_1.setText((String) map.get("value"));
				} else if (map.get("name").equals("暂停业务操作员")) {
					checkMapValue(map);
					holder.tvText_name_2.setText((String) map.get("value"));
				} else if (map.get("name").equals("暂停截至时间")) {
					checkMapValue(map);
					holder.tvText_name_3.setText((String) map.get("value"));
				} else if (map.get("name").equals("开始业务操作员")) {
					checkMapValue(map);
					holder.tvText_name_4.setText((String) map.get("value"));
				} else if (map.get("name").equals("暂停原因")) {
					checkMapValue(map);
					holder.tvText_name_5.setText((String) map.get("value"));
				} else if (map.get("name").equals("暂停环节")) {
					checkMapValue(map);
					holder.tvText_name_6.setText((String) map.get("value"));
				}
			}
			return convertView;
		}

		class ViewHolder {
			TextView tvText_name_1;
			TextView tvText_name_2;
			TextView tvText_name_3;
			TextView tvText_name_4;
			TextView tvText_name_5;
			TextView tvText_name_6;
			boolean flag;
		}

		public List<Map<String, Object>> getHisInspectRecords() {
			return datas;
		}

		public void setNotifications(List<Map<String, Object>> datas) {
			this.datas = datas;
		}
	}

	private void checkMapValue(Map<String, Object> map) {
		if (map.get("value").equals("null") || map.get("value") == null
				|| map.get("value").equals("")) {
			map.put("value", "-");
		}
	}
}
