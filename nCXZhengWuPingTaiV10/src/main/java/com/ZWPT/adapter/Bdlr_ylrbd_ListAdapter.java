package com.ZWPT.adapter;

import java.util.List;

import com.ZWPT.Utils.StringUtil;
import com.ZWPT.activity.Base_DetailActivity_;
import com.ZWPT.activity.R;
import com.ZWPT.data.InfoFile_;
import com.ZWPT.data.entity.Bdlr_Ylrbd_List_Result;
import com.ZWPT.data.entity.Sjsl_Yislyw_List_Result;
import com.ZWPT.data.entity.Wssq_Yslyw_List_Result;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

/**
 * 预录入表单Adapter
 * 
 * @author yuhuihui
 * @date 2014-7-30
 */
public class Bdlr_ylrbd_ListAdapter extends BaseAdapter {

	private LayoutInflater inflater;
	private Context context = null;
	private List<Bdlr_Ylrbd_List_Result> datas;
	private InfoFile_ infoFile_;

	public Bdlr_ylrbd_ListAdapter(Context context,
			List<Bdlr_Ylrbd_List_Result> datas) {
		this.datas = datas;
		inflater = LayoutInflater.from(context);
		this.context = context;
		this.infoFile_ = new InfoFile_(context);
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
					R.layout.layout_bdlr_ylrbd_list_item, null);
			// if(position==(getCount()-1)) {
			// ImageView iv_lastView = (ImageView)
			// convertView.findViewById(R.id.imageView2);
			// iv_lastView.setVisibility(View.GONE);
			// }
			// if(position%2==1) {
			// convertView.setBackgroundResource(R.color.app_bg);
			// title.setBackgroundResource(R.color.item_odd_bg);
			// }

			holder.tvText_name = (TextView) convertView
					.findViewById(R.id.tv_list_item_1);
			holder.tvText_fwsxname = (TextView) convertView
					.findViewById(R.id.tv_list_item_2);
			holder.tvText_shenqing_date = (TextView) convertView
					.findViewById(R.id.tv_list_item_4);
			holder.btn_datail = (Button) convertView
					.findViewById(R.id.btn_list_2);

			convertView.setTag(holder);

		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		holder.tvText_name.setText(datas.get(position).getApplyname());
		holder.tvText_fwsxname.setText(datas.get(position).getFwsx_name());
		holder.tvText_shenqing_date.setText(StringUtil.getDateFormat(datas.get(
				position).getShenqing_date()));

		holder.btn_datail.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				String id = datas.get(position).getId();
				String subject_name = datas.get(position).getFwsx_name();
				String subject_id = datas.get(position).getFwsx_id();
				String ywlsh = datas.get(position).getYwlsh();
				// String banjiezhuangtai = datas.get(position).getBlzt();
				if (datas.get(position).getBlzt_real().equals("52")
						|| datas.get(position).getBlzt_real().equals("92")) {
					infoFile_.edit().dfflb_bjzt().put("失败").apply();
				} else {
					infoFile_.edit().dfflb_bjzt().put("成功").apply();
				}
				infoFile_.edit().baselist_yewuliushuihao().put(ywlsh).apply();
				infoFile_.edit().serviceSubjectName().put(subject_name).apply();
				infoFile_.edit().serviceSubjectId().put(subject_id).apply();
				infoFile_.edit().bizArchivesId().put(id).apply();
				intent.setClass(context, Base_DetailActivity_.class);
				context.startActivity(intent);
			}
		});

		return convertView;
	}

	class ViewHolder {
		TextView tvText_name;
		TextView tvText_shenqing_date;
		TextView tvText_fwsxname;
		Button btn_datail;
		boolean flag;
	}

	public List<Bdlr_Ylrbd_List_Result> getHisInspectRecords() {
		return datas;
	}

	public void setNotifications(List<Bdlr_Ylrbd_List_Result> datas) {
		this.datas = datas;
	}

}
