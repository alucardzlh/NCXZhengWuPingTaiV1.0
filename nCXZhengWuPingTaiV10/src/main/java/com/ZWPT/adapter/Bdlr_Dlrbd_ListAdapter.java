package com.ZWPT.adapter;

import java.util.List;

import com.ZWPT.Utils.StringUtil;
import com.ZWPT.activity.Base_DetailActivity_;
import com.ZWPT.activity.Bdlr_lrbd_luru_Activity_;
import com.ZWPT.activity.R;
import com.ZWPT.data.InfoFile_;
import com.ZWPT.data.entity.Bdlr_Dlrbd_List_Result;
import com.ZWPT.data.entity.Sjsl_Yislyw_List_Result;
import com.ZWPT.data.entity.Wssq_Yslyw_List_Result;

import android.content.Context;
import android.content.Intent;
import android.sax.StartElementListener;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

/**
 * 待录入表单Adapter
 * 
 * @author yuhuihui
 * @date 2014-7-30
 */
public class Bdlr_Dlrbd_ListAdapter extends BaseAdapter {

	private LayoutInflater inflater;
	private Context context = null;
	private List<Bdlr_Dlrbd_List_Result> datas;
	private InfoFile_ infoFile_;

	public Bdlr_Dlrbd_ListAdapter(Context context,
			List<Bdlr_Dlrbd_List_Result> datas) {
		this.datas = datas;
		inflater = LayoutInflater.from(context);
		this.context = context;
		this.infoFile_ = new InfoFile_(context);
	}

	/*
	 * public Drawable getDrawableByText(int num,boolean flag,int width) {
	 * Bitmap localBitmap1 = null; localBitmap1 =
	 * Bitmap.createBitmap(72,76,Bitmap.Config.ARGB_8888); Canvas localCanvas1 =
	 * new Canvas(localBitmap1); Paint paint = new Paint();
	 * 
	 * if(flag) { paint.setColor(Color.rgb(0xdb, 0x8b, 0x23)); } else
	 * paint.setColor(Color.rgb(0x95, 0xa6, 0x8f));
	 * paint.setTextAlign(Align.CENTER);
	 * paint.setTextSize((int)(20.0*width/240.0));
	 * localCanvas1.drawText(""+num,35,37+(int)(8.0*width/240.0),paint); return
	 * new BitmapDrawable(localBitmap1); }
	 */
	/*
	 * public void setState(ViewHolder holder,int state,int position,int width)
	 * { if(state ==0 ) {
	 * holder.imgState.setBackgroundDrawable(getDrawableByText
	 * (position+1,false,width)); } if(state ==1 ) {
	 * holder.imgState.setBackgroundDrawable
	 * (getDrawableByText(position+1,true,width)); } if(state ==2 ) {
	 * holder.imgState.setBackgroundResource(R.drawable.rightico); } }
	 */
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
					R.layout.layout_bdlr_dlrbd_list_item, null);
			holder.tvText_name = (TextView) convertView
					.findViewById(R.id.tv_list_item_1);
			holder.tvText_fwsxname = (TextView) convertView
					.findViewById(R.id.tv_list_item_2);
			holder.tvText_shenqing_date = (TextView) convertView
					.findViewById(R.id.tv_list_item_4);
			holder.btn_datail = (Button) convertView
					.findViewById(R.id.btn_list_1);

			holder.btn_write_in = (Button) convertView
					.findViewById(R.id.btn_list_3);

			convertView.setTag(holder);

		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		String one = datas.get(position).getApplyname();
		String two = datas.get(position).getFwsx_name();
		String three = datas.get(position).getShenqing_date();
		holder.tvText_name.setText(one);
		holder.tvText_fwsxname.setText(two);
		holder.tvText_shenqing_date.setText(StringUtil.getDateFormat(three));
		holder.btn_datail.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO 自动生成的方法存根
				Intent intent = new Intent();
				String id = datas.get(position).getId();
				String subject_name = datas.get(position).getFwsx_name();
				String subject_id = datas.get(position).getFwsx_id();
				String ywlsh = datas.get(position).getYwlsh();
				infoFile_.edit().baselist_yewuliushuihao().put(ywlsh).apply();
				infoFile_.edit().serviceSubjectName().put(subject_name).apply();
				infoFile_.edit().serviceSubjectId().put(subject_id).apply();
				infoFile_.edit().bizArchivesId().put(id).apply();
				intent.setClass(context, Base_DetailActivity_.class);
				context.startActivity(intent);
			}
		});
		holder.btn_write_in.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO 自动生成的方法存根
				Intent intent = new Intent(context,
						Bdlr_lrbd_luru_Activity_.class);
				String id = datas.get(position).getId();
				String subject_name = datas.get(position).getFwsx_name();
				String subject_id = datas.get(position).getFwsx_id();
				String ywlsh = datas.get(position).getYwlsh();
				String apply_name = datas.get(position).getApplyname();
				String apply_id = datas.get(position).getApplyid();
				infoFile_.edit().shengqingrenname().put(apply_name).apply();
				infoFile_.edit().shengqingrenCardId().put(apply_id).apply();
				infoFile_.edit().serviceSubjectName().put(subject_name).apply();
				infoFile_.edit().serviceSubjectId().put(subject_id).apply();
				infoFile_.edit().bizArchivesId().put(id).apply();
				infoFile_.edit().baselist_yewuliushuihao().put(ywlsh).apply();
				context.startActivity(intent);

			}
		});
		/*
		 * if(datas.get(position).lendNum!=null) {
		 * if(Integer.parseInt(datas.get(position).lendNum) >0) {
		 * holder.btn_yj.setVisibility(View.GONE); } }
		 */
		/*
		 * boolean isFind = false;
		 * 
		 * String barCode = ""; Log.i(Constants.datacurrent.size() + "",
		 * "HHHHH"); if (Constants.datacurrent.size() == 0)
		 * holder.btn_yj.setVisibility(View.GONE); else { for (int i = 0; i <
		 * Constants.datacurrent.size(); i++) {
		 * 
		 * if (datas.get(position).marcRecNo.equals(Constants.datacurrent
		 * .get(i).marcRecNo)) { isFind = true; barCode =
		 * Constants.datacurrent.get(i).barCode; break; } } if (isFind) {
		 * holder.btn_yj.setText("续借"); } else { holder.btn_yj.setText("预约"); }
		 * final boolean isFindF = isFind; final String barCodeF = barCode;
		 */

		return convertView;
	}

	class ViewHolder {
		TextView tvText_name;
		TextView tvText_shenqing_date;
		TextView tvText_fwsxname;
		Button btn_datail, btn_write_in;
		boolean flag;
	}

	public List<Bdlr_Dlrbd_List_Result> getHisInspectRecords() {
		return datas;
	}

	public void setNotifications(List<Bdlr_Dlrbd_List_Result> datas) {
		this.datas = datas;
	}

}
