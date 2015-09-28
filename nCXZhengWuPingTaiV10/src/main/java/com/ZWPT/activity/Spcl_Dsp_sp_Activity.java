package com.ZWPT.activity;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.util.Base64;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ZWPT.Utils.DatePickDialog;
import com.ZWPT.Utils.StringUtil;
import com.ZWPT.data.Constants;
import com.ZWPT.data.DataHelper;
import com.ZWPT.data.InfoFile_;
import com.ZWPT.data.entity.HandleResult;
import com.ZWPT.service.BaseDetail_ManagerService;
import com.googlecode.androidannotations.annotations.AfterViews;
import com.googlecode.androidannotations.annotations.Bean;
import com.googlecode.androidannotations.annotations.EActivity;
import com.googlecode.androidannotations.annotations.ViewById;
import com.googlecode.androidannotations.annotations.sharedpreferences.Pref;

@EActivity(R.layout.layout_spcl_dsp_sp)
public class Spcl_Dsp_sp_Activity extends BaseActivity {
	@Bean
	DataHelper dataHelper;
	@Pref
	InfoFile_ infoFile_;
	@ViewById
	TextView tvTitle_two_Name, tv_search_3, tv_search_2, tv_search_1;
	@ViewById
	EditText edt_search_1;
	@ViewById
	LinearLayout search_rll_shenqingriqi;
	@ViewById
	RadioGroup rg;
	@ViewById
	RadioButton first, second;
	@ViewById
	RelativeLayout rl_title;
	@ViewById
	Button btn_list_1, btn_list_2, btn_chakan, btn_upload;
	String passed = "";
	String ss = "";// 图片二进制
	String path = "";

	@AfterViews
	void initView() {
		initTitle();
		tvTitle_two_Name.setText(infoFile_.serviceSubjectName().get());
		tv_search_1.setText(infoFile_.shengqingrenname().get());
		tv_search_2.setText(infoFile_.shengqingriqi().get());
		SimpleDateFormat formatter = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss ");
		Date curDate = new Date(System.currentTimeMillis());// 获取当前时间
		String str = formatter.format(curDate);
		tv_search_3.setText(str);
		btn_list_1.setOnClickListener(onClickListener);
		btn_list_2.setOnClickListener(onClickListener);
		btn_upload.setOnClickListener(onClickListener);
		btn_chakan.setOnClickListener(onClickListener);
		search_rll_shenqingriqi.setOnClickListener(onClickListener);
		rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				if (checkedId == R.id.first) {
					passed = "Success";
					edt_search_1.setText("经审核，同意！");
				} else if (checkedId == R.id.second) {
					passed = "Fail";
					edt_search_1.setText("经审核，不同意！");
				}
			}
		});
	}

	public View.OnClickListener onClickListener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.btn_list_2:
				List<Map<String, Object>> list_1 = new ArrayList<Map<String, Object>>();
				Map<String, Object> oMap = new HashMap<String, Object>();
				oMap.put("bizArchivesId", infoFile_.bizArchivesId().get());
				if (passed.equals("")) {
					if (rg.getCheckedRadioButtonId() == R.id.first) {
						infoFile_.edit().dfflb_bjzt().put("成功").apply();
						passed = "Success";
					} else if (rg.getCheckedRadioButtonId() == R.id.second) {
						infoFile_.edit().dfflb_bjzt().put("失败").apply();
						passed = "Fail";
					}
				}
				oMap.put("handleStatus", passed);
				oMap.put("userAccountId", infoFile_.infoUserId().get());
				if (tv_search_3.getText() == null
						|| tv_search_3.getText().toString().equals("")) {
					showToastLong("请填写办理日期！");
				} else {
					oMap.put("resultDate", tv_search_3.getText().toString());
					if (edt_search_1.getText() == null
							|| edt_search_1.getText().toString().equals("")) {
						showToastLong("请填写意见！");
					} else {
						if (ss == null || ss.equals("") || ss.equals("null")) {
							ss = "";
						} else {
							oMap.put("imageStream", ss);
						}
						oMap.put("handleSuggestion", edt_search_1.getText()
								.toString());
						list_1.add(oMap);

						baseDetail_ManagerService.doBizArchives(
								Spcl_Dsp_sp_Activity.this, 4, list_1);
					}
				}
				break;

			case R.id.btn_list_1:
				Spcl_Dsp_sp_Activity.this.finish();
				Spcl_Dsp_sp_Activity.this.startActivity(new Intent(
						Spcl_Dsp_sp_Activity.this, Base_DetailActivity_.class));
				break;
			case R.id.search_rll_shenqingriqi:
				DatePickDialog.showDateCheckDialog(Spcl_Dsp_sp_Activity.this,
						tv_search_3, true);
				break;
			case R.id.btn_upload:
				Intent intent = new Intent(Spcl_Dsp_sp_Activity.this,
						ChiocePritureMethodActiivty_.class);
				startActivityForResult(intent, 1);
				break;
			case R.id.btn_chakan:
				if (path.equals("")) {
					Toast.makeText(context, "请上传图片！", Toast.LENGTH_SHORT)
							.show();
					break;
				}
				File file = new File(path);
				if (file != null && file.isFile() == true) {
					Intent intent1 = new Intent();
					intent1.setAction(android.content.Intent.ACTION_VIEW);
					intent1.setDataAndType(Uri.fromFile(file), "image/*");
					Spcl_Dsp_sp_Activity.this.startActivity(intent1);
				}
				break;
			default:
				break;
			}
		}
	};

	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == 1 && resultCode == Activity.RESULT_OK) {
			// 将图片地址保存，用于展示图片
			path = data
					.getStringExtra(ChiocePritureMethodActiivty_.KEY_PHOTO_PATH);
			if (path != null) {

				// 上传前弹出对话框，用户确认上传该图片，避免用户选错图片上传
				// showIsUploadDialog(path);
				ss = getimage(path);

			} else {
				ss = "null";
			}
		}
	};

	/**
	 * 图片压缩至100KB以下，避免上传时造成内存溢出
	 * 
	 * @param image
	 * @return 二进制流
	 */
	private String compressImage(Bitmap image) {

		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		image.compress(Bitmap.CompressFormat.JPEG, 100, baos);// 质量压缩方法，这里100表示不压缩，把压缩后的数据存放到baos中
		int options = 100;
		while (baos.toByteArray().length / 1024 > 100) { // 循环判断如果压缩后图片是否大于100kb,大于继续压缩
			baos.reset();// 重置baos即清空baos
			image.compress(Bitmap.CompressFormat.JPEG, options, baos);// 这里压缩options%，把压缩后的数据存放到baos中
			options -= 10;// 每次都减少10
		}
		// ByteArrayInputStream isBm = new
		// ByteArrayInputStream(baos.toByteArray());//把压缩后的数据baos存放到ByteArrayInputStream中
		// Bitmap bitmap = BitmapFactory.decodeStream(isBm, null,
		// null);//把ByteArrayInputStream数据生成图片

		String str = Base64.encodeToString(baos.toByteArray(), Base64.DEFAULT);
		return str;
	}

	/**
	 * 通过图片路径压缩图片
	 * 
	 * @param srcPath
	 * @return
	 */
	private String getimage(String srcPath) {
		BitmapFactory.Options newOpts = new BitmapFactory.Options();
		// 开始读入图片，此时把options.inJustDecodeBounds 设回true了
		newOpts.inJustDecodeBounds = true;
		Bitmap bitmap = BitmapFactory.decodeFile(srcPath, newOpts);// 此时返回bm为空

		newOpts.inJustDecodeBounds = false;
		int w = newOpts.outWidth;
		int h = newOpts.outHeight;
		// 现在主流手机比较多是800*480分辨率，所以高和宽我们设置为
		float hh = 800f;// 这里设置高度为800f
		float ww = 480f;// 这里设置宽度为480f
		// 缩放比。由于是固定比例缩放，只用高或者宽其中一个数据进行计算即可
		int be = 1;// be=1表示不缩放
		if (w > h && w > ww) {// 如果宽度大的话根据宽度固定大小缩放
			be = (int) (newOpts.outWidth / ww);
		} else if (w < h && h > hh) {// 如果高度高的话根据宽度固定大小缩放
			be = (int) (newOpts.outHeight / hh);
		}
		if (be <= 0)
			be = 1;
		newOpts.inSampleSize = be;// 设置缩放比例
		// 重新读入图片，注意此时已经把options.inJustDecodeBounds 设回false了
		bitmap = BitmapFactory.decodeFile(srcPath, newOpts);
		return compressImage(bitmap);// 压缩好比例大小后再进行质量压缩
	}

	private BaseDetail_ManagerService baseDetail_ManagerService = new BaseDetail_ManagerService() {

		@Override
		public void handlerBaseDetailInfo(Context context,
				HandleResult paramHandleResult, int paramInt) {
			if (paramHandleResult.getClywSuccess().equals("success")) {
				Toast.makeText(Spcl_Dsp_sp_Activity.this, "审核成功！",
						Toast.LENGTH_LONG).show();
				Spcl_Dsp_sp_Activity.this.finish();
				Spcl_Dsp_sp_Activity.this.startActivity(new Intent(
						Spcl_Dsp_sp_Activity.this, Base_DetailActivity_.class));

			}

		}

	};

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			// exitBy2Click();
			Intent intent = new Intent();
			intent.setClass(this, Base_DetailActivity_.class);
			startActivity(intent);
			finish();
		}
		return false;
	}

	public void initTitle() {
		if (Constants.title.equals(context.getResources().getString(
				R.string.yushouliyewu))) {
			rl_title.setBackgroundDrawable(getResources().getDrawable(
					R.drawable.tt_yuslyw));
		}
		if (Constants.title.equals(context.getResources().getString(
				R.string.shoujiandengji))) {

			rl_title.setBackgroundDrawable(getResources().getDrawable(
					R.drawable.tt_sjdj));

		}
		if (Constants.title.equals(context.getResources().getString(
				R.string.yishouliyewu))) {

			rl_title.setBackgroundDrawable(getResources().getDrawable(
					R.drawable.tt_yslyw));

		}
		if (Constants.title.equals(context.getResources().getString(
				R.string.buyushouliyewu))) {

			rl_title.setBackgroundDrawable(getResources().getDrawable(
					R.drawable.tt_byslyw));

		}
		if (Constants.title.equals(context.getResources().getString(
				R.string.dailurubiaodan))) {

			rl_title.setBackgroundDrawable(getResources().getDrawable(
					R.drawable.tt_dllbd));

		}
		if (Constants.title.equals(context.getResources().getString(
				R.string.yilurubiaodan))) {

			rl_title.setBackgroundDrawable(getResources().getDrawable(
					R.drawable.tt_yllbd));

		}
		if (Constants.title.equals(context.getResources().getString(
				R.string.daishenpi))) {

			rl_title.setBackgroundDrawable(getResources().getDrawable(
					R.drawable.tt_dsp));

		}
		if (Constants.title.equals(context.getResources().getString(
				R.string.yishenpi))) {

			rl_title.setBackgroundDrawable(getResources().getDrawable(
					R.drawable.tt_ysp));

		}
		if (Constants.title.equals(context.getResources().getString(
				R.string.yituihui))) {

			rl_title.setBackgroundDrawable(getResources().getDrawable(
					R.drawable.tt_yth));

		}
		if (Constants.title.equals(context.getResources().getString(
				R.string.daifafangliebiao))) {

			rl_title.setBackgroundDrawable(getResources().getDrawable(
					R.drawable.tt_dfflb));

		}
		if (Constants.title.equals(context.getResources().getString(
				R.string.yifafangliebiao))) {

			rl_title.setBackgroundDrawable(getResources().getDrawable(
					R.drawable.tt_yfflb));

		}
		if (Constants.title.equals(context.getResources().getString(
				R.string.daoqiweibanjieliebiao))) {

			rl_title.setBackgroundDrawable(getResources().getDrawable(
					R.drawable.tt_dqwbjlb));

		}
		if (Constants.title.equals(context.getResources().getString(
				R.string.yewudanganchaxun))) {

			rl_title.setBackgroundDrawable(getResources().getDrawable(
					R.drawable.tt_ywdacx));

		}
	}

}
