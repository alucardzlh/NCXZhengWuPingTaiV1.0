package com.ZWPT.adapter;

import java.util.List;

import com.ZWPT.activity.Base_DetailActivity_;
import com.ZWPT.activity.R;
import com.ZWPT.activity.Sjsl_Sjdj_shouli_Activity_;
import com.ZWPT.activity.Wssq_Yslyw_Deny_Activity_;
import com.ZWPT.data.Constants;
import com.ZWPT.data.InfoFile_;
import com.ZWPT.data.entity.Wssq_Yslyw_List_Result;
import com.ZWPT.view.SampleListFragment;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

/**
 * 预受理Adapter
 * 
 * @author yuhuihui
 * @date 2014-7-30
 */
public class Wssq_Yslyw_ListAdapter extends BaseAdapter {

	private LayoutInflater inflater;
	private Context context = null;
	private List<Wssq_Yslyw_List_Result> datas;
	private InfoFile_ infoFile_;

	public Wssq_Yslyw_ListAdapter(Context context,
			List<Wssq_Yslyw_List_Result> datas) {
		this.datas = datas;
		inflater = LayoutInflater.from(context);
		this.context = context;
		infoFile_ = new InfoFile_(context);

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

	public View getView(final int position, View convertView, ViewGroup parent) {
		final ViewHolder holder;
		if (convertView == null) {
			holder = new ViewHolder();
			convertView = inflater.inflate(
					R.layout.layout_wssq_yslyw_list_item, null);
			holder.tvText_name = (TextView) convertView
					.findViewById(R.id.tv_list_item_1);
			holder.tvText_idnum = (TextView) convertView
					.findViewById(R.id.tv_list_item_2);

			holder.tvText_wsyslnum = (TextView) convertView
					.findViewById(R.id.tv_list_item_3);
			holder.tvText_fwsxname = (TextView) convertView
					.findViewById(R.id.tv_list_item_4);
			holder.btn_datail = (Button) convertView
					.findViewById(R.id.btn_list_1);
			holder.btn_deny = (Button) convertView
					.findViewById(R.id.btn_list_2);
			holder.btn_accept = (Button) convertView
					.findViewById(R.id.btn_list_3);

			convertView.setTag(holder);

		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		holder.tvText_name.setText(datas.get(position).getApplyname());
		holder.tvText_idnum.setText(datas.get(position).getIdnum());
		holder.tvText_wsyslnum.setText(datas.get(position).getWsysl_num());
		holder.tvText_fwsxname.setText(datas.get(position).getFwsx_name());
		holder.btn_accept.setVisibility(View.INVISIBLE);
		holder.btn_deny.setVisibility(View.INVISIBLE);
		holder.btn_accept.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Constants.SHOWSEARCH_BUTTON = false;
				Constants.fromYsl = true;
				Intent mIntent = new Intent();
				String id = datas.get(position).getId();
				String subject_name = datas.get(position).getFwsx_name();
				String subject_id = datas.get(position).getFwsx_id();
				String apply_name = datas.get(position).getApplyname();
				String apply_id = datas.get(position).getIdnum();
				String wssq_wsyslh = datas.get(position).getWsysl_num();
				String shenqingriqi = datas.get(position).getWssq_sqrq();
				infoFile_.edit().serviceSubjectName().put(subject_name).apply();
				infoFile_.edit().serviceSubjectId().put(subject_id).apply();
				infoFile_.edit().bizArchivesId().put(id).apply();
				infoFile_.edit().shengqingrenname().put(apply_name).apply();
				infoFile_.edit().yslyw_shengqingrenCardId().put(apply_id)
						.apply();
				infoFile_.edit().shengqingrenCardId().put(apply_id).apply();
				infoFile_.edit().wssq_wsyslh().put(wssq_wsyslh).apply();
				infoFile_.edit().shengqingriqi().put(shenqingriqi).apply();
				mIntent.setClass(context, Sjsl_Sjdj_shouli_Activity_.class);

				context.startActivity(mIntent);
			}
		});
		holder.btn_deny.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Constants.SHOWSEARCH_BUTTON = false;
				Constants.fromYsl = true;
				Intent mIntent = new Intent();
				String id = datas.get(position).getId();
				String subject_name = datas.get(position).getFwsx_name();
				String subject_id = datas.get(position).getFwsx_id();
				String apply_name = datas.get(position).getApplyname();
				String apply_id = datas.get(position).getIdnum();
				String wssq_wsyslh = datas.get(position).getWsysl_num();
				String shenqingriqi = datas.get(position).getWssq_sqrq();
				infoFile_.edit().serviceSubjectName().put(subject_name).apply();
				infoFile_.edit().serviceSubjectId().put(subject_id).apply();
				infoFile_.edit().bizArchivesId().put(id).apply();
				infoFile_.edit().shengqingrenname().put(apply_name).apply();
				infoFile_.edit().yslyw_shengqingrenCardId().put(apply_id)
						.apply();
				infoFile_.edit().shengqingrenCardId().put(apply_id).apply();
				infoFile_.edit().wssq_wsyslh().put(wssq_wsyslh).apply();
				infoFile_.edit().shengqingriqi().put(shenqingriqi).apply();
				mIntent.setClass(context, Wssq_Yslyw_Deny_Activity_.class);

				context.startActivity(mIntent);
			}
		});
		holder.btn_datail.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Constants.SHOWSEARCH_BUTTON = false;
				Constants.fromYsl = true;
				String id = datas.get(position).getId();
				String subject_name = datas.get(position).getFwsx_name();
				String subject_id = datas.get(position).getFwsx_id();
				String apply_name = datas.get(position).getApplyname();
				String apply_id = datas.get(position).getIdnum();
				String wssq_wsyslh = datas.get(position).getWsysl_num();
				String shenqingriqi = datas.get(position).getWssq_sqrq();
				infoFile_.edit().serviceSubjectName().put(subject_name).apply();
				infoFile_.edit().serviceSubjectId().put(subject_id).apply();
				infoFile_.edit().bizArchivesId().put(id).apply();
				infoFile_.edit().shengqingrenname().put(apply_name).apply();
				infoFile_.edit().yslyw_shengqingrenCardId().put(apply_id)
						.apply();
				infoFile_.edit().shengqingrenCardId().put(apply_id).apply();
				infoFile_.edit().wssq_wsyslh().put(wssq_wsyslh).apply();
				infoFile_.edit().shengqingriqi().put(shenqingriqi).apply();
				infoFile_.edit().baselist_yewuliushuihao()
						.put(datas.get(position).getWsysl_num()).apply();
				Intent mIntent = new Intent();

				mIntent.setClass(context, Base_DetailActivity_.class);

				context.startActivity(mIntent);

			}
		});

		return convertView;
	}

	class ViewHolder {
		TextView tvText_name;
		TextView tvText_idnum;
		TextView tvText_wsyslnum;
		TextView tvText_fwsxname;
		Button btn_datail, btn_deny, btn_accept;
		boolean flag;
	}

	public List<Wssq_Yslyw_List_Result> getHisInspectRecords() {
		return datas;
	}

	public void setNotifications(List<Wssq_Yslyw_List_Result> datas) {
		this.datas = datas;
	}

}
