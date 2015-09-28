package com.ZWPT.adapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ZWPT.Utils.BdlrAddressHandle;
import com.ZWPT.Utils.DatePickDialog;
import com.ZWPT.Utils.StringUtil;
import com.ZWPT.activity.R;
import com.ZWPT.view.MyPopupWindow;

/**
 * 通知提醒列表的数据Adapter
 * 
 * @author Goven
 * @date 2012-12-27
 */
public class Detail_Ywbd_FormAdapter extends BaseAdapter {
	private LayoutInflater inflater;
	private Context context = null;
	private List<Map<String, Object>> datas = new ArrayList<Map<String, Object>>();

	public Detail_Ywbd_FormAdapter(Context context,
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
		if (convertView == null) {
			viewHolder = new ViewHolder();
			convertView = inflater.inflate(R.layout.bdlr_luru_item, null);
			viewHolder.edt_bdlr_shurukuang = (EditText) convertView
					.findViewById(R.id.edt_bdlr_shurukuang);
			viewHolder.edt_bdlr_shurukuang.setEnabled(false);
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
			viewHolder.ll_addr_gone = (LinearLayout) convertView
					.findViewById(R.id.ll_addr_gone);
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

			convertView.setTag(viewHolder);

		} else {
			viewHolder = (ViewHolder) convertView.getTag();
			// viewHolder.reset();
		}
		if (datas.get(position).get("controlType").equals("TEXTBOX")) {

			viewHolder.ll_bdlr_shurukuang.setVisibility(View.VISIBLE);
			viewHolder.ll_more_chioce_kuang.setVisibility(View.GONE);
			viewHolder.ll_bdlr_springs_chioce_kuang.setVisibility(View.GONE);
			viewHolder.ll_bdlr_time_chioce_kuang.setVisibility(View.GONE);

			viewHolder.tv_bdlr_shurukuang.setText((String) datas.get(position)
					.get("name"));
			viewHolder.edt_bdlr_shurukuang.setFocusable(true);
			viewHolder.edt_bdlr_shurukuang.setFocusableInTouchMode(true);
			final EditText edt_bdlr_shurukuang = viewHolder.edt_bdlr_shurukuang;
			try {
				edt_bdlr_shurukuang.setText(datas.get(position).get("value")
						.toString());
				Log.e("", "inset position : " + position + " content : "
						+ datas.get(position).get("value").toString());
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}

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

			final LinearLayout ll_more_chioce_kuang_address = viewHolder.ll_more_chioce_kuang_address;
			final LinearLayout ll_more_chioce_kuang_address2 = viewHolder.ll_more_chioce_kuang_address2;
			final TextView tv_more_chioce_kuang_address = viewHolder.tv_more_chioce_kuang_address;
			final TextView tv_more_chioce_kuang_address2 = viewHolder.tv_more_chioce_kuang_address2;
			viewHolder.ll_addr_gone.setVisibility(View.GONE);
			try {
				tv_more_chioce_kuang_address.setText((String) datas.get(
						position).get("value"));
				Log.e("", "" + (String) datas.get(position).get("codeAText"));
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			try {
				// tv_more_chioce_kuang_address2.setText((String) datas.get(
				// position).get("code"));
				Log.e("", "" + (String) datas.get(position).get("codeBText"));
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
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
			final LinearLayout ll_bdlr_springs_chioce_kuang_fenlei = viewHolder.ll_bdlr_springs_chioce_kuang_fenlei;
			final TextView tv_bdlr_springs_chioce_kuang_fenlei = viewHolder.tv_bdlr_springs_chioce_kuang_fenlei;
			try {
				tv_bdlr_springs_chioce_kuang_fenlei.setText((String) datas.get(
						position).get("value"));
				Log.e("", "" + (String) datas.get(position).get("value"));
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
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
			try {
				viewHolder.tv_bdlr_time_chioce_kuang_shenqingriqi
						.setText((String) datas.get(position).get("value"));
				Log.e("", "" + (String) datas.get(position).get("value"));
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			final TextView tv_bdlr_time_chioce_kuang_shenqingriqi = viewHolder.tv_bdlr_time_chioce_kuang_shenqingriqi;
			final android.content.DialogInterface.OnClickListener clickListener = new android.content.DialogInterface.OnClickListener() {

				@Override
				public void onClick(DialogInterface arg0, int arg1) {
					// TODO 自动生成的方法存根
					// datas.get(position).put(
					// "value",
					// tv_bdlr_time_chioce_kuang_shenqingriqi.getText()
					// .toString());
				}
			};

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
				ll_bdlr_time_chioce_kuang_shenqingriqi, ll_addr_gone;
		TextView tv_bdlr_shurukuang, tv_more_chioce_kuang,
				tv_more_chioce_kuang_address, tv_more_chioce_kuang_address2,
				tv_bdlr_springs_chioce_kuang,
				tv_bdlr_springs_chioce_kuang_fenlei, tv_bdlr_time_chioce_kuang,
				tv_bdlr_time_chioce_kuang_shenqingriqi;
		ImageView iv_bdlr_shurukuang, iv_more_chioce_kuang,
				iv_bdlr_springs_chioce_kuang, iv_bdlr_time_chioce_kuang;
		EditText edt_bdlr_shurukuang;

		public void reset() {
			tv_bdlr_shurukuang.setText("");
			tv_more_chioce_kuang.setText("");
			tv_more_chioce_kuang_address.setText("");
			tv_more_chioce_kuang_address2.setText("");
			tv_bdlr_springs_chioce_kuang.setText("");
			tv_bdlr_springs_chioce_kuang_fenlei.setText("");
			tv_bdlr_time_chioce_kuang.setText("");
			tv_bdlr_time_chioce_kuang_shenqingriqi.setText("");
			edt_bdlr_shurukuang.setText("");
		}
	}

	public List<Map<String, Object>> getHisInspectRecords() {
		return datas;
	}

	public void setNotifications(List<Map<String, Object>> datas) {
		this.datas = datas;
	}
}
