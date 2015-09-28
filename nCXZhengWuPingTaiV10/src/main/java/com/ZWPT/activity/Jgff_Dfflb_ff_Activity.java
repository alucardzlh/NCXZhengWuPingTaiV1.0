package com.ZWPT.activity;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.ArrayList;
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

import com.ZWPT.data.DataHelper;
import com.ZWPT.data.InfoFile_;
import com.ZWPT.data.entity.HandleResult;
import com.ZWPT.service.BaseDetail_ManagerService;
import com.googlecode.androidannotations.annotations.AfterViews;
import com.googlecode.androidannotations.annotations.Bean;
import com.googlecode.androidannotations.annotations.EActivity;
import com.googlecode.androidannotations.annotations.ViewById;
import com.googlecode.androidannotations.annotations.sharedpreferences.Pref;

@EActivity(R.layout.layout_jgff_dfflb_ff)
public class Jgff_Dfflb_ff_Activity extends BaseActivity {
	@Bean
	DataHelper dataHelper;
	@Pref
	InfoFile_ infoFile_;
	@ViewById
	TextView tvTitle_two_Name, tv_list_item_1, tv_list_item_2, tv_list_item_3,
			tv_list_item_4, tv_list_item_5, tv_list_item_6, tv_list_item_7,
			tv_list_item_8, tv_list_item_9;
	@ViewById
	EditText edt_search_1, edt_search_2;
	@ViewById
	LinearLayout ll_1, ll_2, ll_3, ll_4, ll_5, ll_6;
	@ViewById
	Button btn_list_1, btn_list_2, btn_chakan, btn_upload;
	@ViewById
	RadioGroup rg;
	@ViewById
	RadioButton first, second;
	@ViewById
	View view_1, view_2, view_3, view_4, view_5, view_6;
	@ViewById
	RelativeLayout rl_title;
	String ss = "";// 图片二进制
	String path = "";

	@AfterViews
	void initView() {
		rl_title.setBackgroundDrawable(getResources().getDrawable(
				R.drawable.tt_dfflb));
		tvTitle_two_Name.setText(infoFile_.serviceSubjectName().get());
		if (infoFile_.jgff_yewuliushuihao().get() != null
				&& !infoFile_.jgff_yewuliushuihao().get().equals("")
				&& infoFile_.shengqingrenname().get() != null
				&& !infoFile_.shengqingrenname().get().equals("")
				&& infoFile_.shengqingrenCardId().get() != null
				&& !infoFile_.shengqingrenCardId().get().equals("")
				&& infoFile_.shengqingriqi().get() != null
				&& !infoFile_.shengqingriqi().get().equals("")
				&& infoFile_.banjieriqi().get() != null
				&& !infoFile_.banjieriqi().get().equals("")) {
			tv_list_item_1.setText(infoFile_.shengqingrenname().get());
			tv_list_item_2.setText(infoFile_.shengqingrenCardId().get());
			tv_list_item_3.setText(infoFile_.shengqingriqi().get());
			tv_list_item_4.setText(infoFile_.banjieriqi().get());
			tv_list_item_5.setText(infoFile_.jgff_yewuliushuihao().get());
		}

		tv_list_item_6.setText(infoFile_.shengqingrenname().get());
		tv_list_item_7.setText(infoFile_.shengqingrenCardId().get());
		tv_list_item_8.setText(infoFile_.shengqingrenname().get());
		tv_list_item_9.setText(infoFile_.shengqingrenCardId().get());
		btn_list_1.setOnClickListener(onClickListener);
		btn_list_2.setOnClickListener(onClickListener);
		btn_upload.setOnClickListener(onClickListener);
		btn_chakan.setOnClickListener(onClickListener);
		ll_1.setVisibility(View.VISIBLE);
		ll_2.setVisibility(View.VISIBLE);
		ll_3.setVisibility(View.GONE);
		ll_4.setVisibility(View.GONE);
		ll_5.setVisibility(View.GONE);
		ll_6.setVisibility(View.GONE);
		view_1.setVisibility(View.VISIBLE);
		view_2.setVisibility(View.VISIBLE);
		view_3.setVisibility(View.GONE);
		view_4.setVisibility(View.GONE);
		view_5.setVisibility(View.GONE);
		view_6.setVisibility(View.GONE);
		rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				if (checkedId == R.id.first) {
					ll_1.setVisibility(View.VISIBLE);
					ll_2.setVisibility(View.VISIBLE);
					ll_3.setVisibility(View.GONE);
					ll_4.setVisibility(View.GONE);
					ll_5.setVisibility(View.GONE);
					ll_6.setVisibility(View.GONE);
					view_1.setVisibility(View.VISIBLE);
					view_2.setVisibility(View.VISIBLE);
					view_3.setVisibility(View.GONE);
					view_4.setVisibility(View.GONE);
					view_5.setVisibility(View.GONE);
					view_6.setVisibility(View.GONE);
				} else if (checkedId == R.id.second) {
					ll_1.setVisibility(View.GONE);
					ll_2.setVisibility(View.GONE);
					ll_3.setVisibility(View.VISIBLE);
					ll_4.setVisibility(View.VISIBLE);
					ll_5.setVisibility(View.VISIBLE);
					ll_6.setVisibility(View.VISIBLE);
					view_1.setVisibility(View.GONE);
					view_2.setVisibility(View.GONE);
					view_3.setVisibility(View.VISIBLE);
					view_4.setVisibility(View.VISIBLE);
					view_5.setVisibility(View.VISIBLE);
					view_6.setVisibility(View.VISIBLE);
				}
			}
		});
	}

	public View.OnClickListener onClickListener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.btn_list_1:
				List<Map<String, Object>> list_1 = new ArrayList<Map<String, Object>>();
				Map<String, Object> oMap = new HashMap<String, Object>();
				oMap.put("bizArchivesId", infoFile_.bizArchivesId().get());
				oMap.put("userAccountId", infoFile_.infoUserId().get());

				if (ss == null || ss.equals("") || ss.equals("null")) {
					ss = "";
				} else {
					oMap.put("imageStream", ss);
				}
				list_1.add(oMap);
				baseDetail_ManagerService.doSendReceipt(
						Jgff_Dfflb_ff_Activity.this, 7, list_1);
				break;
			case R.id.btn_list_2:
				Jgff_Dfflb_ff_Activity.this.finish();
				Jgff_Dfflb_ff_Activity.this
						.startActivity(new Intent(Jgff_Dfflb_ff_Activity.this,
								Base_DetailActivity_.class));
				break;
			case R.id.btn_upload:
				Intent intent = new Intent(Jgff_Dfflb_ff_Activity.this,
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
					Jgff_Dfflb_ff_Activity.this.startActivity(intent1);
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
			if (paramHandleResult.getJfjgSuccess().equals("success")) {
				Toast.makeText(Jgff_Dfflb_ff_Activity.this, "交付成功！",
						Toast.LENGTH_LONG).show();
				Jgff_Dfflb_ff_Activity.this.finishOthers();
				Jgff_Dfflb_ff_Activity.this
						.startActivity(new Intent(Jgff_Dfflb_ff_Activity.this,
								Base_DetailActivity_.class));

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
}
