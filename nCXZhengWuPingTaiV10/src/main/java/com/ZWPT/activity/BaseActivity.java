package com.ZWPT.activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ZWPT.Utils.CommUtil;
import com.ZWPT.data.Constants;
import com.ZWPT.data.InfoFile_;
import com.ZWPT.data.entity.HandleResult;
import com.ZWPT.service.LeftMenuManagerService;
import com.ZWPT.sliding.SlidingFragmentActivity;
import com.ZWPT.sliding.SlidingMenu;
import com.ZWPT.view.SampleListFragment;
import com.googlecode.androidannotations.annotations.sharedpreferences.Pref;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

/**
 * 基础Activity，所有的Activity都要继承此类， 定义了一些公共的方法和方便的操作，以后还可以扩展
 * 
 * @date 2014-5-30
 * @author yuhuihui
 */
public class BaseActivity extends SlidingFragmentActivity {
	protected static ActivityApp app;

	protected BaseActivity context;
	private Class<? extends Activity> destActivity;

	/**
	 * 打印info级别的日志
	 * 
	 * @param info
	 *            信息
	 * @author Goven
	 * @date 2013-4-24
	 */
	public void log(String info) {
		CommUtil.log(getClass().getName() + "--->"
				+ Thread.currentThread().getStackTrace()[3].getMethodName(),
				info);
	}

	/**
	 * 打印debug级别的日志
	 * 
	 * @param info
	 *            信息
	 * @author Goven
	 * @date 2013-4-24
	 */
	public void logD(String info) {
		CommUtil.logD(getClass().getName() + "--->"
				+ Thread.currentThread().getStackTrace()[3].getMethodName(),
				info);
	}

	/**
	 * 打印error级别的日志
	 * 
	 * @param info
	 *            信息
	 * @author Goven
	 * @date 2013-4-24
	 */
	public void logE(String info) {
		CommUtil.logE(getClass().getName() + "--->"
				+ Thread.currentThread().getStackTrace()[3].getMethodName(),
				info);
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if (app == null) {
			app = (ActivityApp) getApplicationContext();
		}
		addActivity();
		context = this;
		// 初始化滑动菜单
		initSlidingMenu(savedInstanceState);
	}

	public void initLeftMenu() {
		List<Map<String, Object>> paramlist = new ArrayList<Map<String, Object>>();
		Map<String, Object> map_account = new HashMap<String, Object>();
		map_account.put("account", Constants.account);
		paramlist.add(map_account);
		mLeftMenuManagerService.getLeftMenu(this, 1, paramlist);
	}

	private LeftMenuManagerService mLeftMenuManagerService = new LeftMenuManagerService() {

		@Override
		public void handlerLoginInfo(Context context,
				HandleResult paramHandleResult, int paramInt) {
			switch (paramInt) {
			case 1:
				if (paramHandleResult.getGetLeftMenuSuccess().equals("success")) {
					Constants.list_leftmenu = paramHandleResult
							.getList_leftmenu();
					Log.e("mLeftMenuManagerService",
							Constants.list_leftmenu.size() + "");
					getSupportFragmentManager().beginTransaction()
							.replace(R.id.menu_frame, new SampleListFragment())
							.commit();
				} else if (paramHandleResult.getGetLeftMenuSuccess().equals(
						"fail")) {
				}
				break;
			case 2:
				break;
			default:
				break;
			}
		}
	};

	@Override
	protected void onResume() {
		// overridePendingTransition(R.anim.zoomin, R.anim.zoomout);
		super.onResume();
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		app.removeActivity(this);
	}

	/**
	 * 在应用的Activity管理器里面添加当前Activity
	 * 
	 * @author yuhuihui
	 * @date 2014-5-30
	 */
	protected void addActivity() {
		app.addActivity(this);
	}

	/**
	 * 结束所有的Activity
	 * 
	 * @author yuhuihui
	 * @date 2014-5-30
	 */
	protected void finishAllActivity() {
		app.finishAll();
	}

	protected void finishOthers() {
		app.finishAllExcept(this);
	}

	/**
	 * 除指定activity外，其他的activity全部结束
	 * 
	 * @author yuhuihui
	 * @date 2014-5-30
	 * @param clazz
	 */
	protected void finishOthers(Class<? extends Activity> clazz) {
		if (!app.finishAllExcept(clazz)) {
			// startActivity(new Intent(context, MainActivity_.class));
		}
	}

	/**
	 * 长时间的Toast
	 * 
	 * @author yuhuihui
	 * @date 2014-5-30
	 */
	public void showToastLong(String... values) {
		if (values == null || values.length == 0) {
			Toast.makeText(context, "网络不给力!", Toast.LENGTH_LONG).show();
		} else {
			Toast.makeText(context, values[0], Toast.LENGTH_LONG).show();
		}
	}

	/**
	 * 短时间的Toast
	 * 
	 * @author yuhuihui
	 * @date 2014-5-30
	 */
	public void showToastShort(String... values) {
		if (values == null || values.length == 0) {
			Toast.makeText(context, "网络不给力!", Toast.LENGTH_SHORT).show();
		} else {
			Toast.makeText(context, values[0], Toast.LENGTH_SHORT).show();
		}
	}

	/**
	 * 弹出仅带有确定按钮的对话框
	 * 
	 * @param title
	 *            标题
	 * @param info
	 *            内容
	 * @param icon
	 *            图标
	 * @author yuhuihui
	 * @date 2014-5-30
	 */
	public void showOKDialog(String title, String info, int icon) {
		AlertDialog.Builder dialog = new AlertDialog.Builder(this);
		dialog.setIcon(icon).setTitle(title).setMessage(info)
				.setPositiveButton("确定", null).show();
	}

	/**
	 * 初始化滑动菜单
	 */
	protected void initSlidingMenu(Bundle savedInstanceState) {
		// 设置滑动菜单的视图
		setBehindContentView(R.layout.sliding_menu_frame);
		if (Constants.list_leftmenu.size() > 0) {
			getSupportFragmentManager().beginTransaction()
					.replace(R.id.menu_frame, new SampleListFragment())
					.commit();
		}
		// 实例化滑动菜单对象
		sm = getSlidingMenu();
		// 设置滑动阴影的宽度
		sm.setShadowWidthRes(R.dimen.shadow_width);
		// 设置滑动阴影的图像资源
		sm.setShadowDrawable(R.drawable.shadow);
		// 设置滑动菜单视图的宽度
		sm.setBehindOffsetRes(R.dimen.slidingmenu_offset);
		// 设置渐入渐出效果的值
		sm.setFadeDegree(0.35f);
		// 设置触摸屏幕的模式
		sm.setTouchModeAbove(SlidingMenu.TOUCHMODE_MARGIN);

	}

	SlidingMenu sm;
	private boolean isExit = false;

	/**
	 * 双击退出
	 */
	public void exitBy2Click() {
		if (sm.isMenuShowing()) {
			if (!isExit) {
				// isExit = true; // 准备退出
				// Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
				//
				// new Thread(new Runnable() {
				//
				// @Override
				// public void run() {
				// SystemClock.sleep(2000);
				// isExit = false; // 取消退出
				// }
				// }).start();
				Intent intent = new Intent();
				intent.setClass(this, MainActivity_.class);
				startActivity(intent);
				finishOthers();
				this.finish();
			} else {
				app.finishAll();
			}
		} else {
			sm.showMenu();
		}

	}

}
