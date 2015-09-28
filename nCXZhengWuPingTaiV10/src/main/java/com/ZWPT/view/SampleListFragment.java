package com.ZWPT.view;

import java.util.ArrayList;
import java.util.List;

import com.ZWPT.activity.Base_ListActivity_;
import com.ZWPT.activity.LoginActivity_;
import com.ZWPT.activity.R;
import com.ZWPT.activity.Sjsl_Sjdj_MainActivity_;
import com.ZWPT.activity.Tjbb_Sljg_Tjlb_Activity_;
import com.ZWPT.activity.Wdpz_User_Info_Activity_;
import com.ZWPT.data.Constants;
import com.ZWPT.data.InfoFile_;
import com.googlecode.androidannotations.annotations.EActivity;
import com.googlecode.androidannotations.annotations.NoTitle;
import com.googlecode.androidannotations.annotations.sharedpreferences.Pref;
import com.umeng.update.UmengUpdateAgent;
import com.umeng.update.UmengUpdateListener;
import com.umeng.update.UpdateResponse;
import com.umeng.update.UpdateStatus;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * @author yangyu 功能描述：列表Fragment，用来显示滑动菜单打开后的内容
 */

public class SampleListFragment extends ListFragment {
	Context context;
	List<String> list_menu = new ArrayList<String>();

	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater.inflate(R.layout.sliding_menu_list, null);
	}

	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		context = this.getActivity();

		SampleAdapter adapter = new SampleAdapter(this.getActivity());

		list_menu = Constants.list_leftmenu;

		if (list_menu.size() != 0) {
			for (String str : list_menu) {
				String str_menu = str.trim();
				if (str_menu.equals(getResources().getString(
						R.string.yushouliyewu))) {
					adapter.add(new SampleItem(getResources().getString(
							R.string.sliding_title_1),
							android.R.drawable.ic_menu_search, false));
					adapter.add(new SampleItem(getResources().getString(
							R.string.yushouliyewu),
							android.R.drawable.ic_menu_search, true));
				}
				if (str_menu.equals(getResources().getString(
						R.string.shoujiandengji))) {
					adapter.add(new SampleItem(getResources().getString(
							R.string.sliding_title_2),
							android.R.drawable.ic_menu_search, false));
					adapter.add(new SampleItem(getResources().getString(
							R.string.shoujiandengji),
							android.R.drawable.ic_menu_search, true));
				}
				if (str_menu.equals(getResources().getString(
						R.string.yishouliyewu))) {
					adapter.add(new SampleItem(getResources().getString(
							R.string.yishouliyewu),
							android.R.drawable.ic_menu_search, true));
				}
				if (str_menu.equals(getResources().getString(
						R.string.buyushouliyewu))) {
					adapter.add(new SampleItem(getResources().getString(
							R.string.buyushouliyewu),
							android.R.drawable.ic_menu_search, true));
				}
				if (str_menu.equals(getResources().getString(
						R.string.dailurubiaodan))) {
					adapter.add(new SampleItem(getResources().getString(
							R.string.sliding_title_3),
							android.R.drawable.ic_menu_search, false));
					adapter.add(new SampleItem(getResources().getString(
							R.string.dailurubiaodan),
							android.R.drawable.ic_menu_search, true));
				}
				if (str_menu.equals(getResources().getString(
						R.string.yilurubiaodan))) {
					adapter.add(new SampleItem(getResources().getString(
							R.string.yilurubiaodan),
							android.R.drawable.ic_menu_search, true));
				}
				if (str_menu.equals(getResources()
						.getString(R.string.daishenpi))) {
					adapter.add(new SampleItem(getResources().getString(
							R.string.sliding_title_4),
							android.R.drawable.ic_menu_search, false));
					adapter.add(new SampleItem(getResources().getString(
							R.string.daishenpi),
							android.R.drawable.ic_menu_search, true));
				}
				if (str_menu
						.equals(getResources().getString(R.string.yishenpi))) {
					adapter.add(new SampleItem(getResources().getString(
							R.string.yishenpi),
							android.R.drawable.ic_menu_search, true));
				}
				if (str_menu
						.equals(getResources().getString(R.string.yituihui))) {
					adapter.add(new SampleItem(getResources().getString(
							R.string.yituihui),
							android.R.drawable.ic_menu_search, true));
				}
				if (str_menu.equals(getResources().getString(
						R.string.daifafangliebiao))) {
					adapter.add(new SampleItem(getResources().getString(
							R.string.sliding_title_5),
							android.R.drawable.ic_menu_search, false));
					adapter.add(new SampleItem(getResources().getString(
							R.string.daifafangliebiao),
							android.R.drawable.ic_menu_search, true));
				}
				if (str_menu.equals(getResources().getString(
						R.string.yifafangliebiao))) {
					adapter.add(new SampleItem(getResources().getString(
							R.string.yifafangliebiao),
							android.R.drawable.ic_menu_search, true));
				}
				if (str_menu.equals(getResources().getString(
						R.string.daoqiweibanjieliebiao))) {
					adapter.add(new SampleItem(getResources().getString(
							R.string.daoqiweibanjieliebiao),
							android.R.drawable.ic_menu_search, true));
				}
				if (str_menu.equals(getResources().getString(
						R.string.yewudanganchaxun))) {
					adapter.add(new SampleItem(getResources().getString(
							R.string.sliding_title_6),
							android.R.drawable.ic_menu_search, false));
					adapter.add(new SampleItem(getResources().getString(
							R.string.yewudanganchaxun),
							android.R.drawable.ic_menu_search, true));
				}
			}
		}

		// adapter.add(new SampleItem(getResources().getString(
		// R.string.sliding_title_7), android.R.drawable.ic_menu_search,
		// false));
		// adapter.add(new SampleItem(getResources().getString(
		// R.string.anfuwushixiangtongji),
		// android.R.drawable.ic_menu_search, true));
		// adapter.add(new SampleItem(getResources().getString(
		// R.string.anshoulijigoutongji),
		// android.R.drawable.ic_menu_search, true));
		// adapter.add(new SampleItem(getResources().getString(
		// R.string.anyewubumentongji), android.R.drawable.ic_menu_search,
		// true));
		//
		// adapter.add(new SampleItem(getResources().getString(
		// R.string.sliding_title_8), android.R.drawable.ic_menu_search,
		// false));
		// adapter.add(new SampleItem(getResources()
		// .getString(R.string.gerenxinxi),
		// android.R.drawable.ic_menu_search, true));
		// adapter.add(new SampleItem(getResources()
		// .getString(R.string.xiugaimima),
		// android.R.drawable.ic_menu_search, true));

		adapter.add(new SampleItem(getResources().getString(
				R.string.sliding_title_12), android.R.drawable.ic_menu_search,
				false));
		adapter.add(new SampleItem(getResources().getString(
				R.string.checkupdate), android.R.drawable.ic_menu_search, true));
		adapter.add(new SampleItem(getResources().getString(R.string.re_login),
				android.R.drawable.ic_menu_search, true));

		setListAdapter(adapter);
	}

	public class SampleAdapter extends ArrayAdapter<SampleItem> {
		public SampleAdapter(Context context) {
			super(context, 0);
		}

		public View getView(int position, View convertView, ViewGroup parent) {
			if (getItem(position).clickable) {
				// if (convertView == null) {
				//
				convertView = LayoutInflater.from(getContext()).inflate(
						R.layout.sliding_menu_row, null);
				// }
				TextView title = (TextView) convertView
						.findViewById(R.id.row_title);
				title.setText(getItem(position).tag);

			} else {
				// 此处不注释的话会导致列表混乱 if (convertView == null) {
				//
				convertView = LayoutInflater.from(getContext()).inflate(
						R.layout.sliding_menu_row_1, null);
				// }
				TextView title = (TextView) convertView
						.findViewById(R.id.row_title);
				title.setText(getItem(position).tag);
			}

			return convertView;
		}
	}

	private class SampleItem {
		public String tag;
		public boolean clickable;

		public SampleItem(String tag, int iconRes, boolean click) {
			this.tag = tag;
			this.clickable = click;
		}
	}

	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		Constants.SHOWSEARCH_BUTTON = true;
		SampleItem sam = (SampleItem) l.getAdapter().getItem(position);
		startListActivity(sam);
	}

	public void startListActivity(SampleItem samp) {
		if (samp.tag.equals(context.getResources().getString(
				R.string.anfuwushixiangtongji))) {

		} else if (samp.tag.equals(context.getResources().getString(
				R.string.anshoulijigoutongji))) {
			Constants.title = samp.tag;
			Intent intent = new Intent();
			intent.setClass(context, Tjbb_Sljg_Tjlb_Activity_.class);
			startActivity(intent);
			SampleListFragment.this.getActivity().finish();
		} else if (samp.tag.equals(context.getResources().getString(
				R.string.anyewubumentongji))) {

		} else if (samp.tag.equals(context.getResources().getString(
				R.string.gerenxinxi))) {
			Constants.title = samp.tag;
			Intent intent = new Intent(context, Wdpz_User_Info_Activity_.class);
			startActivity(intent);
			SampleListFragment.this.getActivity().finish();
		} else if (samp.tag.equals(context.getResources().getString(
				R.string.xiugaimima))) {
			Constants.title = samp.tag;
			Intent intent = new Intent(context, Wdpz_User_Info_Activity_.class);
			startActivity(intent);
			SampleListFragment.this.getActivity().finish();
		} else if (samp.tag.equals(context.getResources().getString(
				R.string.shoujiandengji))) {
			Constants.title = samp.tag;
			Intent Intent = new Intent();

			Intent.setClass(context, Sjsl_Sjdj_MainActivity_.class);

			startActivity(Intent);
			SampleListFragment.this.getActivity().finish();
		} else if (samp.tag.equals(context.getResources().getString(
				R.string.checkupdate))) {
			UmengUpdateAgent.setUpdateListener(new UmengUpdateListener() {
				@Override
				public void onUpdateReturned(int updateStatus,
						UpdateResponse updateInfo) {
					switch (updateStatus) {
					case UpdateStatus.Yes: // has update
						UmengUpdateAgent.showUpdateDialog(context, updateInfo);
						break;
					case UpdateStatus.No: // has no update
						Toast.makeText(context, "最新版本！", Toast.LENGTH_SHORT)
								.show();
						break;
					case UpdateStatus.NoneWifi: // none wifi
						Toast.makeText(context, "没有wifi连接， 只在wifi下更新",
								Toast.LENGTH_SHORT).show();
						break;
					case UpdateStatus.Timeout: // time out
						Toast.makeText(context, "超时", Toast.LENGTH_SHORT)
								.show();
						break;
					}
				}
			});
			UmengUpdateAgent.update(context);
		} else if (samp.tag.equals(context.getResources().getString(
				R.string.re_login))) {
			Intent intent = new Intent();

			intent.setClass(context, LoginActivity_.class);
			intent.putExtra("re_login", "true");
			startActivity(intent);
			SampleListFragment.this.getActivity().finish();
		} else {
			Constants.title = samp.tag;

			if (samp.clickable) {
				Intent Intent = new Intent();

				Intent.setClass(context, Base_ListActivity_.class);

				startActivity(Intent);
				SampleListFragment.this.getActivity().finish();
			}
		}
	}
}
