package com.ZWPT.activity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
import android.widget.Toast;

import com.ZWPT.Utils.CommUtil;
import com.ZWPT.Utils.StringUtil;
import com.ZWPT.data.DataHelper;
import com.ZWPT.data.InfoFile_;
import com.ZWPT.data.entity.Detail_Sjdj_bean;
import com.googlecode.androidannotations.annotations.AfterViews;
import com.googlecode.androidannotations.annotations.Bean;
import com.googlecode.androidannotations.annotations.EFragment;
import com.googlecode.androidannotations.annotations.ViewById;
import com.googlecode.androidannotations.annotations.sharedpreferences.Pref;

/**
 * 滚动标题栏-电子材料
 * 
 * @author durenchong
 * @date 2014-06-18
 */
@EFragment(R.layout.fragment_detail_dzcl)
public class Detail_dzclFragment extends BaseFragment {
	@Bean
	DataHelper dataHelper;
	@Pref
	InfoFile_ infoFile_;
	@ViewById
	TextView tv_list_item_1, tv_list_item_2, tv_list_item_3, tv_list_item_4,
			tv_list_item_5, tv_list_item_6;
	@ViewById
	ListView listV_dzcl_spgccl;

	Detail_Sjdj_bean detail_Sjdj_bean = null;

	@AfterViews
	void initView() {
		if(detail_Sjdj_bean == null){
			return;
		}
		tv_list_item_1.setText(StringUtil.checkStringValue(detail_Sjdj_bean
				.getSerialNumber()));
		tv_list_item_2.setText(StringUtil.checkStringValue(detail_Sjdj_bean
				.getServiceTargetName()));
		tv_list_item_3.setText(StringUtil.checkStringValue(detail_Sjdj_bean
				.getApplyDate()));
		tv_list_item_4.setText(StringUtil.checkStringValue(detail_Sjdj_bean
				.getServiceOrgName()));
		tv_list_item_5.setText(StringUtil.checkStringValue(detail_Sjdj_bean
				.getHandleDate()));
		tv_list_item_6.setText(StringUtil.checkStringValue(detail_Sjdj_bean
				.getOperatorName()));
		listV_dzcl_spgccl.setAdapter(new Detail_dzcl_spgccl_ListAdapter(
				detail_Sjdj_bean.getList_blcl()));
		CommUtil.setListViewHeightBasedOnChildren(listV_dzcl_spgccl);
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

	public class Detail_dzcl_spgccl_ListAdapter extends BaseAdapter {
		private LayoutInflater inflater;
		private List<Map<String, Object>> datas;

		public Detail_dzcl_spgccl_ListAdapter(List<Map<String, Object>> datas) {
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
						R.layout.fragment_detail_dzcl_spgccl_list_item, null);
				holder.tvText_name = (TextView) convertView
						.findViewById(R.id.tv_detail_dzcl_spcgcl);
				holder.ib = (ImageButton) convertView
						.findViewById(R.id.btn_detail_dzcl_spcgcl);
				convertView.setTag(holder);
			} else {
				holder = (ViewHolder) convertView.getTag();
			}
			holder.tvText_name
					.setText((String) datas.get(position).get("name"));
			if (datas.get(position).get("imgUrl").equals("")) {
				holder.ib.setVisibility(View.GONE);
			}
			holder.ib.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					// showToastShort((String)datas.get(position).get("imgUrl"));
					if (((String) datas.get(position).get("imgUrl")).trim()
							.length() > 0) {
						Intent intent = new Intent(context,
								PritureShowActivity_.class);
						intent.putExtra("pritureUrl",
								(String) datas.get(position).get("imgUrl"));
						intent.putExtra("postinfoname",
								(String) datas.get(position).get("name"));
						// intent.setAction("android.intent.action.VIEW");
						// Uri content_url =
						// Uri.parse("http://117.40.244.134:8081/images/personCert/20140701138590.jpg");
						// intent.setData(content_url);
						startActivity(intent);
					}

				}
			});

			holder.tvText_name.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
			//	Toast.makeText(context, (String) datas.get(position).get("name"), Toast.LENGTH_SHORT).show();
			}
		});
			
			return convertView;
		}

		class ViewHolder {
			TextView tvText_name;
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

}
