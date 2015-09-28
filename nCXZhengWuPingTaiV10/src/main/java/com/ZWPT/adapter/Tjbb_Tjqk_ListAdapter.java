package com.ZWPT.adapter;

import java.util.List;

import com.ZWPT.activity.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class Tjbb_Tjqk_ListAdapter extends BaseAdapter {
	private Context context;
	private List<String> oList;
	private LayoutInflater inflater;

	public Tjbb_Tjqk_ListAdapter(Context context, List<String> olist) {
		this.context = context;
		this.inflater = LayoutInflater.from(context);
		this.oList = olist;
	}

	@Override
	public int getCount() {
		return oList.size();
	}

	@Override
	public Object getItem(int position) {
		return oList.get(position);
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
			convertView = inflater.inflate(R.layout.layout_tjbb_tjqk_list_item,
					null);
			holder.tv_tjbb_tjqk_item = (TextView) convertView
					.findViewById(R.id.tv_tjbb_tjqk_item);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		holder.tv_tjbb_tjqk_item.setText(oList.get(position));
		return convertView;
	}

	class ViewHolder {
		TextView tv_tjbb_tjqk_item;
	}
}
