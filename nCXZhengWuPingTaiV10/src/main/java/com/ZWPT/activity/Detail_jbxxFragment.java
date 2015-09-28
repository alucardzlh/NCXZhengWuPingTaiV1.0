package com.ZWPT.activity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ZWPT.Utils.StringUtil;
import com.ZWPT.Utils.TipsUtil;
import com.ZWPT.data.DataHelper;
import com.ZWPT.data.InfoFile_;
import com.googlecode.androidannotations.annotations.AfterViews;
import com.googlecode.androidannotations.annotations.Bean;
import com.googlecode.androidannotations.annotations.EFragment;
import com.googlecode.androidannotations.annotations.ViewById;
import com.googlecode.androidannotations.annotations.sharedpreferences.Pref;

/**
 * 滑动标题栏-基本信息
 * 
 * @author durenchong
 * @date 2014-06-18
 */
@EFragment(R.layout.fragment_detail_jbxx)
public class Detail_jbxxFragment extends BaseFragment {
	@Bean
	DataHelper dataHelper;
	@Pref
	InfoFile_ oFile_;
	// 收件登记
	@ViewById
	TextView tv_sjdj_item_1, tv_sjdj_item_2, tv_sjdj_item_3, tv_sjdj_item_4,
			tv_sjdj_item_5, tv_sjdj_item_6, tv_sjdj_item_7;
	// 表单录入
	@ViewById
	TextView tv_bdlr_item_1, tv_bdlr_item_2, tv_bdlr_item_3, tv_bdlr_item_4,
			tv_bdlr_item_5, tv_bdlr_item_6;
	// 业务处理（1）
	@ViewById
	TextView tv_ywcl_item_1, tv_ywcl_item_2, tv_ywcl_item_3, tv_ywcl_item_4,
			tv_ywcl_item_5, tv_ywcl_item_6, tv_ywcl_item_7;

	// 业务处理（2）
	@ViewById
	TextView tv_ywcl_2_item_1, tv_ywcl_2_item_2, tv_ywcl_2_item_3,
			tv_ywcl_2_item_4, tv_ywcl_2_item_5, tv_ywcl_2_item_6,
			tv_ywcl_2_item_7;
	// 结果登记
	@ViewById
	TextView tv_jgdj_item_1, tv_jgdj_item_2, tv_jgdj_item_3, tv_jgdj_item_4,
			tv_jgdj_item_5, tv_jgdj_item_6, tv_jgdj_item_7;
	// 结果交付
	@ViewById
	TextView tv_jgjf_item_1, tv_jgjf_item_2, tv_jgjf_item_3, tv_jgjf_item_4,
			tv_jgjf_item_5;

	// 温馨提示
	@ViewById
	TextView tv_wxts_item_1, tv_wxts_item_2, tv_wxts_item_3;
	// 网上不予受理
	@ViewById
	TextView tv_wssl_item_1, tv_wssl_item_2, tv_wssl_item_3, tv_wssl_item_4,
			tv_wssl_item_5, tv_wssl_item_6;
	@ViewById
	LinearLayout ll_sjdj, ll_bdlr, ll_ywcl, ll_ywcl_2, ll_jgdj, ll_jgjf,
			ll_wxts, ll_wssl, linearlayout_jgjf, linearlayout_shjg,
			linearlayout_shjg2;

	@ViewById
	ImageButton btn_detail_jgjf_spcgcl, btn_detail_shjg_spcgcl,
			btn_detail_shjg_spcgcl2;

	boolean haveYWCL = false;
	List<Map<String, Object>> mylist = null;

	@AfterViews
	void initView() {
		if(mylist == null){
	//		return;
		}
		initData();
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Bundle bundle = new Bundle();
		bundle = this.getArguments();
		mylist = (List<Map<String, Object>>) bundle.getSerializable("list");

	}

	private void initData() {
		ll_sjdj.setVisibility(View.GONE);
		ll_bdlr.setVisibility(View.GONE);
		ll_ywcl.setVisibility(View.GONE);
		ll_ywcl_2.setVisibility(View.GONE);
		ll_jgdj.setVisibility(View.GONE);
		ll_jgjf.setVisibility(View.GONE);
		ll_wxts.setVisibility(View.GONE);
		ll_wssl.setVisibility(View.GONE);
		for (int i = 0; i < mylist.size(); i++) {
			if (mylist.get(i).get("title").equals("收件登记 ")) {
				ll_sjdj.setVisibility(View.VISIBLE);
				for (Map<String, Object> map : (List<Map<String, Object>>) mylist
						.get(i).get("item_list")) {
					String str = (String) map.get("name");
					map.put("name", StringUtil.replaceSpace(str));
					if (map.get("name").equals("办件流水号")) {
						checkMapValue(map);
						tv_sjdj_item_1.setText((String) map.get("value"));
						oFile_.edit().jgff_yewuliushuihao()
								.put((String) map.get("value")).apply();
					} else if (map.get("name").equals("申请人姓名")) {
						checkMapValue(map);
						tv_sjdj_item_2.setText((String) map.get("value"));
						oFile_.edit().shengqingrenname()
								.put((String) map.get("value")).apply();
					} else if (map.get("name").equals("身份证号")) {
						checkMapValue(map);
						tv_sjdj_item_3.setText((String) map.get("value"));
						oFile_.edit().shengqingrenCardId()
								.put((String) map.get("value")).apply();
					} else if (map.get("name").equals("手机号码")) {
						checkMapValue(map);
						tv_sjdj_item_4.setText((String) map.get("value"));
					} else if (map.get("name").equals("申请日期")) {
						checkMapValue(map);
						tv_sjdj_item_5.setText((String) map.get("value"));
						oFile_.edit().shengqingriqi()
								.put((String) map.get("value")).apply();
					} else if (map.get("name").equals("受理机构")) {
						checkMapValue(map);
						tv_sjdj_item_6.setText((String) map.get("value"));
					} else if (map.get("name").equals("受理人员")) {
						checkMapValue(map);
						tv_sjdj_item_7.setText((String) map.get("value"));
					}
				}
			}

			else if (mylist.get(i).get("title").equals("表单录入")) {

				ll_bdlr.setVisibility(View.VISIBLE);
				for (Map<String, Object> map : (List<Map<String, Object>>) mylist
						.get(i).get("item_list")) {
					String str = (String) map.get("name");
					map.put("name", StringUtil.replaceSpace(str));
					if (map.get("name").equals("经办机构")) {
						checkMapValue(map);
						tv_bdlr_item_1.setText((String) map.get("value"));
					} else if (map.get("name").equals("录入人")) {
						checkMapValue(map);
						tv_bdlr_item_2.setText((String) map.get("value"));
					} else if (map.get("name").equals("录入日期")) {
						checkMapValue(map);
						tv_bdlr_item_3.setText((String) map.get("value"));
					} else if (map.get("name").equals("录入状态")) {
						checkMapValue(map);
						tv_bdlr_item_4.setText((String) map.get("value"));
					} else if (map.get("name").equals("办理时限")) {
						checkMapValue(map);
						tv_bdlr_item_5.setText((String) map.get("value"));
					} else if (map.get("name").equals("是否超时")) {
						checkMapValue(map);
						tv_bdlr_item_6.setText((String) map.get("value"));
					}
				}
			}

			else if (mylist.get(i).get("title").equals("业务处理")) {
				if (haveYWCL) {
					haveYWCL = false;
					ll_ywcl_2.setVisibility(View.VISIBLE);
					for (final Map<String, Object> map : (List<Map<String, Object>>) mylist
							.get(i).get("item_list")) {
						String str = (String) map.get("name");
						map.put("name", StringUtil.replaceSpace(str));
						if (map.get("name").equals("经办机构")) {
							checkMapValue(map);
							tv_ywcl_2_item_1.setText((String) map.get("value"));
						} else if (map.get("name").equals("经办人")) {
							checkMapValue(map);
							tv_ywcl_2_item_2.setText((String) map.get("value"));
						} else if (map.get("name").equals("办理日期")) {
							checkMapValue(map);
							tv_ywcl_2_item_3.setText((String) map.get("value"));
						} else if (map.get("name").equals("办理状态")) {
							checkMapValue(map);
							tv_ywcl_2_item_4.setText((String) map.get("value"));
						} else if (map.get("name").equals("办理时限")) {
							checkMapValue(map);
							tv_ywcl_2_item_5.setText((String) map.get("value"));
						} else if (map.get("name").equals("是否超时")) {
							checkMapValue(map);
							tv_ywcl_2_item_6.setText((String) map.get("value"));
						} else if (map.get("name").equals("备注")) {
							checkMapValue(map);
							tv_ywcl_2_item_7.setText((String) map.get("value"));
						} else if (map.get("name").equals("审核结果图片")) {
							linearlayout_shjg2.setVisibility(View.VISIBLE);
							checkMapValue(map);
							if (map.get("value").equals("-")) {
								linearlayout_shjg2.setVisibility(View.GONE);
							}
							btn_detail_shjg_spcgcl2
									.setOnClickListener(new OnClickListener() {

										@Override
										public void onClick(View arg0) {
											// TODO 自动生成的方法存根
											String s = (String) map
													.get("value");
											if (s.trim().length() > 0) {
												Intent intent = new Intent(
														context,
														PritureShowActivity_.class);
												intent.putExtra("pritureUrl", s);
												intent.putExtra("postinfoname",
														"审核结果图片");
												startActivity(intent);
											}
										}
									});
						}
					}
				} else {
					ll_ywcl.setVisibility(View.VISIBLE);
					haveYWCL = true;
					for (final Map<String, Object> map : (List<Map<String, Object>>) mylist
							.get(i).get("item_list")) {
						String str = (String) map.get("name");
						map.put("name", StringUtil.replaceSpace(str));
						if (map.get("name").equals("经办机构")) {
							checkMapValue(map);
							tv_ywcl_item_1.setText((String) map.get("value"));
						} else if (map.get("name").equals("经办人")) {
							checkMapValue(map);
							tv_ywcl_item_2.setText((String) map.get("value"));
						} else if (map.get("name").equals("办理日期")) {
							checkMapValue(map);
							tv_ywcl_item_3.setText((String) map.get("value"));
						} else if (map.get("name").equals("办理状态")) {
							checkMapValue(map);
							tv_ywcl_item_4.setText((String) map.get("value"));
						} else if (map.get("name").equals("办理时限")) {
							checkMapValue(map);
							tv_ywcl_item_5.setText((String) map.get("value"));
						} else if (map.get("name").equals("是否超时")) {
							checkMapValue(map);
							tv_ywcl_item_6.setText((String) map.get("value"));
						} else if (map.get("name").equals("备注")) {
							checkMapValue(map);
							tv_ywcl_item_7.setText((String) map.get("value"));
							oFile_.edit().dxtz_beizhu()
									.put((String) map.get("value")).apply();
						} else if (map.get("name").equals("审核结果图片")) {
							linearlayout_shjg.setVisibility(View.VISIBLE);
							checkMapValue(map);
							if (map.get("value").equals("-")) {
								linearlayout_shjg.setVisibility(View.GONE);
							}
							btn_detail_shjg_spcgcl
									.setOnClickListener(new OnClickListener() {

										@Override
										public void onClick(View arg0) {
											// TODO 自动生成的方法存根
											String s = (String) map
													.get("value");
											if (s.trim().length() > 0) {
												Intent intent = new Intent(
														context,
														PritureShowActivity_.class);
												intent.putExtra("pritureUrl", s);
												intent.putExtra("postinfoname",
														"审核结果图片");
												startActivity(intent);
											}
										}
									});
						}
					}
				}

			}

			else if (mylist.get(i).get("title").equals("结果登记")) {
				ll_jgdj.setVisibility(View.VISIBLE);
				for (Map<String, Object> map : (List<Map<String, Object>>) mylist
						.get(i).get("item_list")) {
					String str = (String) map.get("name");
					map.put("name", StringUtil.replaceSpace(str));
					if (map.get("name").equals("经办机构")) {
						checkMapValue(map);
						tv_jgdj_item_1.setText((String) map.get("value"));
					} else if (map.get("name").equals("经办人")) {
						checkMapValue(map);
						tv_jgdj_item_2.setText((String) map.get("value"));
					} else if (map.get("name").equals("办结日期")) {
						checkMapValue(map);
						tv_jgdj_item_3.setText((String) map.get("value"));
						oFile_.edit().banjieriqi()
								.put((String) map.get("value")).apply();
					} else if (map.get("name").equals("办理结果")) {
						checkMapValue(map);
						tv_jgdj_item_4.setText((String) map.get("value"));
					} else if (map.get("name").equals("备注")) {
						checkMapValue(map);
						tv_jgdj_item_5.setText((String) map.get("value"));
					} else if (map.get("name").equals("登记人")) {
						checkMapValue(map);
						tv_jgdj_item_6.setText((String) map.get("value"));
					} else if (map.get("name").equals("登记日期")) {
						checkMapValue(map);
						tv_jgdj_item_7.setText((String) map.get("value"));
					}
				}
			}

			else if (mylist.get(i).get("title").equals("结果交付")) {
				ll_jgjf.setVisibility(View.VISIBLE);
				for (final Map<String, Object> map : (List<Map<String, Object>>) mylist
						.get(i).get("item_list")) {
					String str = (String) map.get("name");
					map.put("name", StringUtil.replaceSpace(str));
					if (map.get("name").equals("发放机构")) {
						checkMapValue(map);
						tv_jgjf_item_1.setText((String) map.get("value"));
					} else if (map.get("name").equals("发放人")) {
						checkMapValue(map);
						tv_jgjf_item_2.setText((String) map.get("value"));
					} else if (map.get("name").equals("发放日期")) {
						checkMapValue(map);
						tv_jgjf_item_3.setText((String) map.get("value"));
					} else if (map.get("name").equals("签收人")) {
						checkMapValue(map);
						tv_jgjf_item_4.setText((String) map.get("value"));
					} else if (map.get("name").equals("签收人身份证")) {
						checkMapValue(map);
						tv_jgjf_item_5.setText((String) map.get("value"));
					} else if (map.get("name").equals("结果交付图片")) {
						linearlayout_jgjf.setVisibility(View.VISIBLE);
						checkMapValue(map);
						if (map.get("value").equals("-")) {
							linearlayout_jgjf.setVisibility(View.GONE);
						}
						btn_detail_jgjf_spcgcl
								.setOnClickListener(new OnClickListener() {

									@Override
									public void onClick(View arg0) {
										// TODO 自动生成的方法存根
										String s = (String) map.get("value");
										if (s.trim().length() > 0) {
											Intent intent = new Intent(context,
													PritureShowActivity_.class);
											intent.putExtra("pritureUrl", s);
											intent.putExtra("postinfoname",
													"结果交付图片");
											startActivity(intent);
										}
									}
								});
					}
				}
			}

			else if (mylist.get(i).get("title").equals("温馨提示")) {
				ll_wxts.setVisibility(View.VISIBLE);
				for (Map<String, Object> map : (List<Map<String, Object>>) mylist
						.get(i).get("item_list")) {
					String str = (String) map.get("name");
					map.put("name", StringUtil.replaceSpace(str));
					if (map.get("name").equals("经办机构")) {
						checkMapValue(map);
						tv_wxts_item_1.setText((String) map.get("value"));
					} else if (map.get("name").equals("经办人")) {
						checkMapValue(map);
						tv_wxts_item_2.setText((String) map.get("value"));
					} else if (map.get("name").equals("办理日期")) {
						checkMapValue(map);
						tv_wxts_item_3.setText((String) map.get("value"));
					}
				}
			} else if (mylist.get(i).get("title").equals("网上预受理失败")) {
				ll_wssl.setVisibility(View.VISIBLE);
				for (Map<String, Object> map : (List<Map<String, Object>>) mylist
						.get(i).get("item_list")) {
					String str = (String) map.get("name");
					map.put("name", StringUtil.replaceSpace(str));
					if (map.get("name").equals("办件流水号")) {
						checkMapValue(map);
						tv_wssl_item_1.setText((String) map.get("value"));
					} else if (map.get("name").equals("申请人姓名")) {
						checkMapValue(map);
						tv_wssl_item_2.setText((String) map.get("value"));
					} else if (map.get("name").equals("身份证号")) {
						checkMapValue(map);
						tv_wssl_item_3.setText((String) map.get("value"));
					} else if (map.get("name").equals("手机号码")) {
						checkMapValue(map);
						tv_wssl_item_4.setText((String) map.get("value"));
					} else if (map.get("name").equals("申请日期")) {
						checkMapValue(map);
						tv_wssl_item_5.setText((String) map.get("value"));
					} else if (map.get("name").equals("不予受理原因")) {
						checkMapValue(map);
						tv_wssl_item_6.setText((String) map.get("value"));
					}
				}
			}
		}
	}

	private void checkMapValue(Map<String, Object> map) {
		if (map.get("value").equals("null") || map.get("value") == null
				|| map.get("value").equals("")) {
			map.put("value", "-");
		}
	}
}
