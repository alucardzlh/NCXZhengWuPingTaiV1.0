package com.ZWPT.adapter;

import java.util.List;

import com.ZWPT.activity.R;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

public class Priture_Show_GridView_Adapter extends BaseAdapter {
	private Context context;
	private List<Bitmap> oBitmaps;
	private LayoutInflater inflater;

	public Priture_Show_GridView_Adapter(Context context, List<Bitmap> oBitmaps) {
		this.context = context;
		this.inflater = LayoutInflater.from(context);
		this.oBitmaps = oBitmaps;
	}

	@Override
	public int getCount() {
		return oBitmaps.size();
	}

	@Override
	public Object getItem(int position) {
		return oBitmaps.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		if (convertView == null) {
			holder = new ViewHolder();
			convertView = inflater.inflate(R.layout.item_priture_show, null);
			holder.iv_priture_show = (ImageView) convertView
					.findViewById(R.id.iv_priture_show);

			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		holder.iv_priture_show.setImageBitmap(oBitmaps.get(position));

		return convertView;
	}

	class ViewHolder {
		ImageView iv_priture_show;
	}
}
