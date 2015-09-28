package com.ZWPT.adapter;

import java.util.List;

import com.ZWPT.activity.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class DropDownListAdapter extends BaseAdapter {

	private LayoutInflater inflater;
	private List<String> oList;

	public DropDownListAdapter(Context context, List<String> oList) {
		this.inflater = LayoutInflater.from(context);
		this.oList = oList;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return oList.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return oList.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		if (convertView == null) {
			holder = new ViewHolder();
			convertView = inflater.inflate(R.layout.item_drop_down, null);
			holder.tv_drop_down_name = (TextView) convertView
					.findViewById(R.id.tv_drop_down_name);

			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		holder.tv_drop_down_name.setText(oList.get(position));
		return convertView;
	}

	class ViewHolder {
		TextView tv_drop_down_name;
	}
}
