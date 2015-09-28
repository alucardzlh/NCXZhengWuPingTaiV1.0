package com.ZWPT.view;

import java.util.List;

import com.ZWPT.activity.R;
import com.ZWPT.adapter.DropDownListAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.PopupWindow;

public class DropDownListPopupWindow {
	private List<String> oList;
	private Context context;
	private ImageButton ibutton;
	private LayoutInflater inflater;
	private PopupWindow popupWindow;
	private int NUM_OF_VISIBLE_LIST_ROWS = 5;

	public final static int TOP = 0, BOTTOM = 1;

	public DropDownListPopupWindow(Context context, List<String> oList,
			ImageButton ibutton) {
		this.context = context;
		this.oList = oList;
		this.inflater = LayoutInflater.from(context);
		this.ibutton = ibutton;

		showPopuWindow();
	}

	private void showPopuWindow() {
		View view = inflater.inflate(R.layout.drop_down_list_popwindow, null);
		ListView lvpop = (ListView) view.findViewById(R.id.lvDropDown);
		lvpop.setAdapter(new DropDownListAdapter(context, oList));
		lvpop.setOnItemClickListener(onItemClickListener);
		popupWindow = new PopupWindow(view);
		// 控制popupwindow的宽度和高度自适应
		lvpop.measure(View.MeasureSpec.UNSPECIFIED,
				View.MeasureSpec.UNSPECIFIED);
		popupWindow.setWidth(lvpop.getMeasuredWidth());
		popupWindow.setHeight((lvpop.getMeasuredHeight() + 20)
				* NUM_OF_VISIBLE_LIST_ROWS);
		popupWindow.setFocusable(true);
		popupWindow.setOutsideTouchable(true);
		popupWindow.setBackgroundDrawable(context.getResources().getDrawable(
				R.color.transparent));
		popupWindow.showAsDropDown(ibutton);

	}

	private OnItemClickListener onItemClickListener = new OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			popupWindow.dismiss();
			popupWindow = null;
			doNet(arg2);

		}
	};

	/**
	 * 用于对列表点击事件的其他操作
	 * 
	 * @param position
	 */
	protected void doNet(int position) {
	}

}
