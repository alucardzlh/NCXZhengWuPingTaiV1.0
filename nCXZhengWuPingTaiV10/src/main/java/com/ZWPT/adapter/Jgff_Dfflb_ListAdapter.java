package com.ZWPT.adapter;

import java.util.List;

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

import com.ZWPT.Utils.StringUtil;
import com.ZWPT.activity.Base_DetailActivity_;
import com.ZWPT.activity.Jgff_Dfflb_ff_Activity_;
import com.ZWPT.activity.Jgff_Dfflb_ff_dxtz_Activity_;
import com.ZWPT.activity.R;
import com.ZWPT.data.InfoFile_;
import com.ZWPT.data.entity.Jgff_Dfflb_List_Result;

/**
 * 待发放Adapter
 * 
 * @author yuhuihui
 * @date 2014-7-30
 */
public class Jgff_Dfflb_ListAdapter extends BaseAdapter {

	private LayoutInflater inflater;
	private Context context = null;
	private List<Jgff_Dfflb_List_Result> datas;
	private InfoFile_ infoFile_;

	public Jgff_Dfflb_ListAdapter(Context context,
			List<Jgff_Dfflb_List_Result> datas) {
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
					R.layout.layout_jgff_dfflb_list_item, null);

			holder.tvText_name = (TextView) convertView
					.findViewById(R.id.tv_list_item_1);
			holder.tvText_fwsxname = (TextView) convertView
					.findViewById(R.id.tv_list_item_2);
			holder.tvText_bjzt = (TextView) convertView
					.findViewById(R.id.tv_list_item_3);
			holder.tvText_banjie_date = (TextView) convertView
					.findViewById(R.id.tv_list_item_4);

			holder.btn_send = (Button) convertView
					.findViewById(R.id.btn_list_1);
			holder.btn_datail = (Button) convertView
					.findViewById(R.id.btn_list_2);
			holder.btn_send_msg = (Button) convertView
					.findViewById(R.id.btn_list_3);

			convertView.setTag(holder);

		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		holder.tvText_name.setText(datas.get(position).getApplyname());
		holder.tvText_fwsxname.setText(datas.get(position).getFwsx_name());
		if (datas.get(position).getBlzt_real().equals("52")
				|| datas.get(position).getBlzt_real().equals("92")) {
			holder.tvText_bjzt.setText("失败");
		} else {
			holder.tvText_bjzt.setText("成功");
		}
		holder.tvText_banjie_date.setText(StringUtil.getDateFormat(datas.get(
				position).getBanjie_date()));

		holder.btn_datail.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				String id = datas.get(position).getId();
				String subject_name = datas.get(position).getFwsx_name();
				String subject_id = datas.get(position).getFwsx_id();
				String ywlsh = datas.get(position).getYwlsh();
				// String banjiezhuangtai = datas.get(position).getBlzt_real();
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

		holder.btn_send.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				String id = datas.get(position).getId();
				String subject_name = datas.get(position).getFwsx_name();
				String subject_id = datas.get(position).getFwsx_id();
				String apply_name = datas.get(position).getApplyname();
				String apply_id = datas.get(position).getApply_id();
				String shenqing_riqi = datas.get(position).getShenqing_date();
				String banjieriqi = datas.get(position).getBanjie_date();
				String yewuliushuihao = datas.get(position).getYewuliushuihao();
				String banjiezhuangtai = datas.get(position).getBlzt();
				infoFile_.edit().dfflb_bjzt().put(banjiezhuangtai).apply();
				infoFile_.edit().serviceSubjectName().put(subject_name).apply();
				infoFile_.edit().serviceSubjectId().put(subject_id).apply();
				infoFile_.edit().bizArchivesId().put(id).apply();
				infoFile_.edit().shengqingrenname().put(apply_name).apply();
				infoFile_.edit().shengqingrenCardId().put(apply_id).apply();
				infoFile_.edit().banjieriqi().put(banjieriqi).apply();
				infoFile_.edit().shengqingriqi().put(shenqing_riqi).apply();
				infoFile_.edit().jgff_yewuliushuihao().put(yewuliushuihao)
						.apply();
				intent.setClass(context, Jgff_Dfflb_ff_Activity_.class);
				context.startActivity(intent);
			}
		});

		holder.btn_send_msg.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				String id = datas.get(position).getId();
				String subject_name = datas.get(position).getFwsx_name();
				String subject_id = datas.get(position).getFwsx_id();
				String apply_name = datas.get(position).getApplyname();
				String apply_id = datas.get(position).getApply_id();
				String shenqing_riqi = datas.get(position).getShenqing_date();
				String banjieriqi = datas.get(position).getBanjie_date();
				String yewuliushuihao = datas.get(position).getYewuliushuihao();
				String banjiezhuangtai = datas.get(position).getBlzt();
				infoFile_.edit().dfflb_bjzt().put(banjiezhuangtai).apply();
				infoFile_.edit().serviceSubjectName().put(subject_name).apply();
				infoFile_.edit().serviceSubjectId().put(subject_id).apply();
				infoFile_.edit().bizArchivesId().put(id).apply();
				infoFile_.edit().shengqingrenname().put(apply_name).apply();
				infoFile_.edit().shengqingrenCardId().put(apply_id).apply();
				infoFile_.edit().banjieriqi().put(banjieriqi).apply();
				infoFile_.edit().shengqingriqi().put(shenqing_riqi).apply();
				infoFile_.edit().jgff_yewuliushuihao().put(yewuliushuihao)
						.apply();
				infoFile_.edit().dxtz_beizhu().put("-").apply();
				intent.setClass(context, Jgff_Dfflb_ff_dxtz_Activity_.class);
				context.startActivity(intent);
			}
		});

		return convertView;
	}

	class ViewHolder {
		TextView tvText_name;
		TextView tvText_bjzt;
		TextView tvText_banjie_date;
		TextView tvText_fwsxname;
		Button btn_datail, btn_send, btn_send_msg;
		boolean flag;
	}

	public List<Jgff_Dfflb_List_Result> getHisInspectRecords() {
		return datas;
	}

	public void setNotifications(List<Jgff_Dfflb_List_Result> datas) {
		this.datas = datas;
	}

}
