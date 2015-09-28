package com.ZWPT.adapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.w3c.dom.Text;

import com.ZWPT.Utils.BdlrAddressHandle;
import com.ZWPT.Utils.DatePickDialog;
import com.ZWPT.Utils.StringUtil;
import com.ZWPT.activity.R;
import com.ZWPT.view.MyPopupWindow;

import android.app.Activity;
import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Bdlr_Dlrbd_Luru_FormAdapter extends BaseAdapter {

	private LayoutInflater inflater;
	private Context context = null;
	private List<Map<String, Object>> datas;

	public Bdlr_Dlrbd_Luru_FormAdapter(Context context,
			List<Map<String, Object>> datas) {
		this.datas = datas;
		inflater = LayoutInflater.from(context);
		this.context = context;

	}

	@Override
	public int getCount() {
		return datas.size();
	}

	@Override
	public Object getItem(int position) {
		return datas.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		ViewHolder viewHolder = null;
		// if (convertView==null) {
		viewHolder = new ViewHolder();
		convertView = inflater.inflate(R.layout.bdlr_luru_item, null);
		viewHolder.edt_bdlr_shurukuang = (EditText) convertView
				.findViewById(R.id.edt_bdlr_shurukuang);
		viewHolder.iv_bdlr_shurukuang = (ImageView) convertView
				.findViewById(R.id.iv_bdlr_shurukuang);
		viewHolder.iv_bdlr_springs_chioce_kuang = (ImageView) convertView
				.findViewById(R.id.iv_bdlr_springs_chioce_kuang);
		viewHolder.iv_bdlr_time_chioce_kuang = (ImageView) convertView
				.findViewById(R.id.iv_bdlr_time_chioce_kuang);
		viewHolder.iv_more_chioce_kuang = (ImageView) convertView
				.findViewById(R.id.iv_more_chioce_kuang);
		viewHolder.ll_bdlr_shurukuang = (LinearLayout) convertView
				.findViewById(R.id.ll_bdlr_shurukuang);
		viewHolder.ll_bdlr_springs_chioce_kuang = (LinearLayout) convertView
				.findViewById(R.id.ll_bdlr_springs_chioce_kuang);
		viewHolder.ll_bdlr_springs_chioce_kuang_fenlei = (LinearLayout) convertView
				.findViewById(R.id.ll_bdlr_springs_chioce_kuang_fenlei);
		viewHolder.ll_bdlr_time_chioce_kuang = (LinearLayout) convertView
				.findViewById(R.id.ll_bdlr_time_chioce_kuang);
		viewHolder.ll_bdlr_time_chioce_kuang_shenqingriqi = (LinearLayout) convertView
				.findViewById(R.id.ll_bdlr_time_chioce_kuang_shenqingriqi);
		viewHolder.ll_more_chioce_kuang = (LinearLayout) convertView
				.findViewById(R.id.ll_more_chioce_kuang);
		viewHolder.ll_more_chioce_kuang_address = (LinearLayout) convertView
				.findViewById(R.id.ll_more_chioce_kuang_address);
		viewHolder.ll_more_chioce_kuang_address2 = (LinearLayout) convertView
				.findViewById(R.id.ll_more_chioce_kuang_address2);
		viewHolder.tv_bdlr_shurukuang = (TextView) convertView
				.findViewById(R.id.tv_bdlr_shurukuang);
		viewHolder.tv_bdlr_springs_chioce_kuang = (TextView) convertView
				.findViewById(R.id.tv_bdlr_springs_chioce_kuang);
		viewHolder.tv_bdlr_springs_chioce_kuang_fenlei = (TextView) convertView
				.findViewById(R.id.tv_bdlr_springs_chioce_kuang_fenlei);
		viewHolder.tv_bdlr_time_chioce_kuang = (TextView) convertView
				.findViewById(R.id.tv_bdlr_time_chioce_kuang);
		viewHolder.tv_bdlr_time_chioce_kuang_shenqingriqi = (TextView) convertView
				.findViewById(R.id.tv_bdlr_time_chioce_kuang_shenqingriqi);
		viewHolder.tv_more_chioce_kuang = (TextView) convertView
				.findViewById(R.id.tv_more_chioce_kuang);
		viewHolder.tv_more_chioce_kuang_address = (TextView) convertView
				.findViewById(R.id.tv_more_chioce_kuang_address);
		viewHolder.tv_more_chioce_kuang_address2 = (TextView) convertView
				.findViewById(R.id.tv_more_chioce_kuang_address2);

		// viewHolder.ll_address_2=(LinearLayout)
		// convertView.findViewById(R.id.ll_address_2);

		convertView.setTag(viewHolder);

		// }else {
		// viewHolder=(ViewHolder) convertView.getTag();
		// }
		final LinearLayout ll_more_chioce_kuang_address_ = viewHolder.ll_more_chioce_kuang_address;
		final LinearLayout ll_more_chioce_kuang_address2_ = viewHolder.ll_more_chioce_kuang_address2;
		final LinearLayout ll_bdlr_springs_chioce_kuang_fenlei_ = viewHolder.ll_bdlr_springs_chioce_kuang_fenlei;
		final TextView tv_more_chioce_kuang_address_ = viewHolder.tv_more_chioce_kuang_address;
		final TextView tv_more_chioce_kuang_address2_ = viewHolder.tv_more_chioce_kuang_address2;
		final TextView tv_bdlr_springs_chioce_kuang_fenlei_ = viewHolder.tv_bdlr_springs_chioce_kuang_fenlei;
		final TextView tv_bdlr_time_chioce_kuang_shenqingriqi_ = viewHolder.tv_bdlr_time_chioce_kuang_shenqingriqi;
		final EditText edt_bdlr_shurukuang_ = viewHolder.edt_bdlr_shurukuang;

		// viewHolder.ll_address_2.setVisibility(View.GONE);

		if (datas.get(position).get("controlType").equals("TEXTBOX")) {

			viewHolder.ll_bdlr_shurukuang.setVisibility(View.VISIBLE);
			viewHolder.ll_more_chioce_kuang.setVisibility(View.GONE);
			viewHolder.ll_bdlr_springs_chioce_kuang.setVisibility(View.GONE);
			viewHolder.ll_bdlr_time_chioce_kuang.setVisibility(View.GONE);

			viewHolder.tv_bdlr_shurukuang.setText((String) datas.get(position)
					.get("name"));
			// final String
			// info=viewHolder.edt_bdlr_shurukuang.getText().toString();
			if (datas.get(position).get("value").equals("null")) {
				viewHolder.edt_bdlr_shurukuang.setText("-");
			} else {
				viewHolder.edt_bdlr_shurukuang.setText((String) datas.get(
						position).get("value"));
			}
			viewHolder.edt_bdlr_shurukuang.setEnabled(false);

			if (datas.get(position).get("nonEmpty").equals("false")) {
				viewHolder.iv_bdlr_shurukuang.setVisibility(View.VISIBLE);
			}

		} else if (datas.get(position).get("controlType").equals("ADDRESS")) {

			viewHolder.ll_bdlr_shurukuang.setVisibility(View.GONE);
			viewHolder.ll_more_chioce_kuang.setVisibility(View.VISIBLE);
			viewHolder.ll_bdlr_springs_chioce_kuang.setVisibility(View.GONE);
			viewHolder.ll_bdlr_time_chioce_kuang.setVisibility(View.GONE);

			viewHolder.tv_more_chioce_kuang.setText((String) datas
					.get(position).get("name"));
			if (datas.get(position).get("value").equals("null")) {
				tv_more_chioce_kuang_address_.setText("-");
			} else {
				tv_more_chioce_kuang_address_.setText((String) datas.get(
						position).get("value"));
			}

			if (datas.get(position).get("nonEmpty").equals("false")) {

				viewHolder.iv_more_chioce_kuang.setVisibility(View.VISIBLE);
			}

		} else if (datas.get(position).get("controlType")
				.equals("DROPDOWN_LIST")) {

			viewHolder.ll_bdlr_shurukuang.setVisibility(View.GONE);
			viewHolder.ll_more_chioce_kuang.setVisibility(View.GONE);
			viewHolder.ll_bdlr_springs_chioce_kuang.setVisibility(View.VISIBLE);
			viewHolder.ll_bdlr_time_chioce_kuang.setVisibility(View.GONE);

			viewHolder.tv_bdlr_springs_chioce_kuang.setText((String) datas.get(
					position).get("name"));
			if (datas.get(position).get("value").equals("null")) {
				tv_bdlr_springs_chioce_kuang_fenlei_.setText("-");
			} else {
				tv_bdlr_springs_chioce_kuang_fenlei_.setText((String) datas
						.get(position).get("value"));
			}

			if (datas.get(position).get("nonEmpty").equals("false")) {
				viewHolder.iv_bdlr_springs_chioce_kuang
						.setVisibility(View.VISIBLE);
			}

		} else if (datas.get(position).get("controlType").equals("DATEFIELD")) {

			viewHolder.ll_bdlr_shurukuang.setVisibility(View.GONE);
			viewHolder.ll_more_chioce_kuang.setVisibility(View.GONE);
			viewHolder.ll_bdlr_springs_chioce_kuang.setVisibility(View.GONE);
			viewHolder.ll_bdlr_time_chioce_kuang.setVisibility(View.VISIBLE);

			viewHolder.tv_bdlr_time_chioce_kuang.setText((String) datas.get(
					position).get("name"));
			if (datas.get(position).get("value").equals("null")) {
				viewHolder.tv_bdlr_time_chioce_kuang_shenqingriqi.setText("-");
			} else {
				viewHolder.tv_bdlr_time_chioce_kuang_shenqingriqi
						.setText((String) datas.get(position).get("value"));
			}
			if (datas.get(position).get("nonEmpty").equals("false")) {
				viewHolder.iv_bdlr_time_chioce_kuang
						.setVisibility(View.VISIBLE);
			}

		}
		return convertView;
	}

	class ViewHolder {
		LinearLayout ll_bdlr_shurukuang, ll_more_chioce_kuang,
				ll_more_chioce_kuang_address, ll_more_chioce_kuang_address2,
				ll_bdlr_springs_chioce_kuang,
				ll_bdlr_springs_chioce_kuang_fenlei, ll_bdlr_time_chioce_kuang,
				ll_bdlr_time_chioce_kuang_shenqingriqi, ll_address_2;
		TextView tv_bdlr_shurukuang, tv_more_chioce_kuang,
				tv_more_chioce_kuang_address, tv_more_chioce_kuang_address2,
				tv_bdlr_springs_chioce_kuang,
				tv_bdlr_springs_chioce_kuang_fenlei, tv_bdlr_time_chioce_kuang,
				tv_bdlr_time_chioce_kuang_shenqingriqi;
		ImageView iv_bdlr_shurukuang, iv_more_chioce_kuang,
				iv_bdlr_springs_chioce_kuang, iv_bdlr_time_chioce_kuang;
		EditText edt_bdlr_shurukuang;
	}

	public List<Map<String, Object>> getHisInspectRecords() {
		return datas;
	}

	public void setNotifications(List<Map<String, Object>> datas) {
		this.datas = datas;
	}
}
