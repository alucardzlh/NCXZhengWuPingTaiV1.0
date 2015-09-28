package com.ZWPT.adapter;

import java.util.List;

import com.ZWPT.activity.R;
import com.ZWPT.data.entity.Sjsl_Byuslyw_List_Result;
import com.ZWPT.data.entity.Sjsl_Byuslyw_List_Result;
import com.ZWPT.data.entity.Wssq_Yslyw_List_Result;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * 通知提醒列表的数据Adapter
 * 
 * @author Goven
 * @date 2012-12-27
 */
public class Sjsl_custom_ListAdapter extends BaseAdapter {

	private LayoutInflater inflater;
	private Context context = null;
	private List<String> datas;

	public Sjsl_custom_ListAdapter(Context context, List<String> datas) {
		this.datas = datas;
		inflater = LayoutInflater.from(context);
		this.context = context;

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
					R.layout.layout_sjdj_custom_list_item_1, null);
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
					.findViewById(R.id.tv_sjdj_item);
			holder.iv = (ImageView) convertView.findViewById(R.id.iv_sjdj);
			convertView.setTag(holder);

		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		holder.tvText_name.setText(datas.get(position));

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
		ImageView iv;
		boolean flag;
	}

	public List<String> getHisInspectRecords() {
		return datas;
	}

	public void setNotifications(List<String> datas) {
		this.datas = datas;
	}

}
