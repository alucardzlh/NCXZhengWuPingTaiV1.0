package com.ZWPT.activity;

import java.util.List;
import java.util.Map;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.ZWPT.Utils.CommUtil;
import com.ZWPT.data.DataHelper;
import com.ZWPT.data.InfoFile_;
import com.ZWPT.data.entity.Detail_Wxts_bean;
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
@EFragment(R.layout.fragment_detail_wxts)
public class Detail_wxtsFragment extends BaseFragment {
	@Bean
	DataHelper dataHelper;
	@Pref
	InfoFile_ infoFile_;
	@ViewById
	ListView lv_bltj_fuhe, lv_tjcl_fuhe;
	@ViewById
	TextView tv_name, tvInfo, tv_1, tv_2, tv_3;
	Detail_Wxts_bean detail_Wxts_bean = null;

	@AfterViews
	void initView() {
		if(detail_Wxts_bean == null){
			return;
		}
		tv_name.setText("尊敬的" + detail_Wxts_bean.getServiceTargetName() + ",");
		tvInfo.setText("您于  " + detail_Wxts_bean.getApplyDate() + "  申办的    ["
				+ detail_Wxts_bean.getServiceSubjectName()
				+ "]  未能办理，原因如下，特此告知。");
		lv_bltj_fuhe.setAdapter(new Detail_wxts_ListAdapter(detail_Wxts_bean
				.getConditionDataList()));
		lv_tjcl_fuhe.setAdapter(new Detail_wxts_ListAdapter(detail_Wxts_bean
				.getMaterialDataList()));
		CommUtil.setListViewHeightBasedOnChildren(lv_bltj_fuhe);
		CommUtil.setListViewHeightBasedOnChildren(lv_tjcl_fuhe);
		tv_1.setText("您可登录 http://haizhu.gz.gov.cn 查看办事状态或致电    "
				+ detail_Wxts_bean.getServiceOrgPhone() + " 咨询。");
		tv_2.setText("经办机构：" + detail_Wxts_bean.getServiceOrgName() + "。");
		tv_3.setText("机构地址：" + detail_Wxts_bean.getServiceOrgAddress() + "。");
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Bundle bundle = new Bundle();
		bundle = this.getArguments();
		detail_Wxts_bean = (Detail_Wxts_bean) bundle
				.getSerializable("Detail_Wxts_bean");
	}

	public class Detail_wxts_ListAdapter extends BaseAdapter {

		private LayoutInflater inflater;
		private List<Map<String, Object>> datas;

		public Detail_wxts_ListAdapter(List<Map<String, Object>> datas) {
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
						R.layout.sjdj_deny_bltj_tjcl_list_item, null);
				holder.tvText_name = (TextView) convertView
						.findViewById(R.id.tv_name);
				holder.iv = (ImageView) convertView.findViewById(R.id.iv_sjdj);
				convertView.setTag(holder);

			} else {
				holder = (ViewHolder) convertView.getTag();
			}

			holder.tvText_name
					.setText((String) datas.get(position).get("name"));
			if (datas.get(position).get("value").equals("true")) {
				holder.iv.setBackgroundDrawable(context.getResources()
						.getDrawable(R.drawable.icon_have));
			} else {
				holder.iv.setBackgroundDrawable(context.getResources()
						.getDrawable(R.drawable.have_no));
			}

			return convertView;
		}

		class ViewHolder {
			TextView tvText_name;
			ImageView iv;
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
