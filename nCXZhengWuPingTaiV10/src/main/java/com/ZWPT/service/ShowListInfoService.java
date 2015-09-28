package com.ZWPT.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.xmlpull.v1.XmlPullParserException;

import com.ZWPT.Utils.BaseAsyncTask;
import com.ZWPT.Utils.XmlUtil;
import com.ZWPT.data.entity.Bdlr_Dlrbd_List_Result;
import com.ZWPT.data.entity.Bdlr_Ylrbd_List_Result;
import com.ZWPT.data.entity.HandleResult;
import com.ZWPT.data.entity.Jgff_Dfflb_List_Result;
import com.ZWPT.data.entity.Jgff_Dqwbjlb_List_Result;
import com.ZWPT.data.entity.Jgff_Yfflb_List_Result;
import com.ZWPT.data.entity.Sjsl_Byuslyw_List_Result;
import com.ZWPT.data.entity.Sjsl_Yislyw_List_Result;
import com.ZWPT.data.entity.Spcl_Dsp_List_Result;
import com.ZWPT.data.entity.Spcl_Ysp_List_Result;
import com.ZWPT.data.entity.Spcl_Yth_List_Result;
import com.ZWPT.data.entity.Wssq_Yslyw_List_Result;
import com.ZWPT.data.entity.Ywda_Ywdacx_List_Result;

import android.content.Context;
import android.widget.Toast;

public abstract class ShowListInfoService {
	public static final String TAG = "ShowListInfoService";

	public void getListInfo(final Context paramActivity, final int paramInt,
			final List<Map<String, Object>> list) {
		final String url = paramActivity.getResources().getString(
				com.ZWPT.activity.R.string.endpoint);
		BaseAsyncTask loacl = new BaseAsyncTask(paramActivity, true) {
			String result = null;
			int tage = 1;
			List<Wssq_Yslyw_List_Result> Yslyw_resultGroup = new ArrayList<Wssq_Yslyw_List_Result>();
			List<Sjsl_Yislyw_List_Result> resultGroup = new ArrayList<Sjsl_Yislyw_List_Result>();
			List<Bdlr_Dlrbd_List_Result> Dlrbd_resultGroup = new ArrayList<Bdlr_Dlrbd_List_Result>();
			List<Bdlr_Ylrbd_List_Result> Ylrbd_resultGroup = new ArrayList<Bdlr_Ylrbd_List_Result>();
			List<Spcl_Dsp_List_Result> Dsp_resultGroup = new ArrayList<Spcl_Dsp_List_Result>();
			List<Sjsl_Byuslyw_List_Result> Byuslyw_resultGroup = new ArrayList<Sjsl_Byuslyw_List_Result>();
			List<Spcl_Ysp_List_Result> Ysp_resultGroup = new ArrayList<Spcl_Ysp_List_Result>();
			List<Jgff_Dqwbjlb_List_Result> Dqwbjlb_resultGroup = new ArrayList<Jgff_Dqwbjlb_List_Result>();
			List<Spcl_Yth_List_Result> Yth_resultGroup = new ArrayList<Spcl_Yth_List_Result>();
			List<Ywda_Ywdacx_List_Result> Ywdacx_resultGroup = new ArrayList<Ywda_Ywdacx_List_Result>();
			List<Jgff_Dfflb_List_Result> Dfflb_resultGroup = new ArrayList<Jgff_Dfflb_List_Result>();
			List<Jgff_Yfflb_List_Result> Yfflb_resultGroup = new ArrayList<Jgff_Yfflb_List_Result>();

			@Override
			protected Integer doInBackground(Integer[] paramArrayOfInteger) {
				// 调用方法，到服务器上验证，第一个参数是要调用的服务器的方法，
				// 第二个参数是要传的参数，获取返回值
				try {
					result = downloadDB("getBizArchivesList", url, list);
					if(result == null){
						return -1;
					}
					switch (paramInt) {
					case 1:// 预受理业务
						Yslyw_resultGroup = XmlUtil
								.getWssq_Yslyw_List_Info(result);
						if (Yslyw_resultGroup == null) {
							tage = -1;
						}
						break;
					case 2:// 已受理业务
						resultGroup = XmlUtil.getSjslYislywListInfo(result);
						if (resultGroup == null) {
							tage = -1;
						}
						break;
					case 3:// 未受理业务
						Byuslyw_resultGroup = XmlUtil
								.getSjsl_Byuslyw_List_Info(result);
						if (Byuslyw_resultGroup == null) {
							tage = -1;
						}
						break;
					case 4:// 待录入表单
						Dlrbd_resultGroup = XmlUtil
								.getBdlr_Dlrbd_List_Info(result);
						if (Dlrbd_resultGroup == null) {
							tage = -1;
						}
						break;
					case 5:// 已录入表单
						Ylrbd_resultGroup = XmlUtil
								.getBdlr_Ylrbd_List_Info(result);
						if (Ylrbd_resultGroup == null) {
							tage = -1;
						}
						break;
					case 6:// 待审批
						Dsp_resultGroup = XmlUtil.getSpcl_Dsp_List_Info(result);
						if (Dsp_resultGroup == null) {
							tage = -1;
						}
						break;
					case 7:// 已审批
						Ysp_resultGroup = XmlUtil.getSpcl_Ysp_List_Info(result);
						if (Ysp_resultGroup == null) {
							tage = -1;
						}
						break;
					case 8:// 已退回
						Yth_resultGroup = XmlUtil.getSpcl_Yth_List_Info(result);
						if (Yth_resultGroup == null) {
							tage = -1;
						}
						break;
					case 9:// 待发放列表
						Dfflb_resultGroup = XmlUtil
								.getJgff_Dfflb_List_Info(result);
						if (Dfflb_resultGroup == null) {
							tage = -1;
						}
						break;
					case 10:// 已发放列表
						Yfflb_resultGroup = XmlUtil
								.getJgff_Yfflb_List_Info(result);
						if (Yfflb_resultGroup == null) {
							tage = -1;
						}
						break;
					case 11:// 到期未办结列表
						Dqwbjlb_resultGroup = XmlUtil
								.getJgff_Dqwbjlb_List_Info(result);
						if (Dqwbjlb_resultGroup == null) {
							tage = -1;
						}
						break;
					case 12:// 业务档案查询
						Ywdacx_resultGroup = XmlUtil
								.getYwda_Ywdacx_List_Info(result);
						if (Ywdacx_resultGroup == null) {
							tage = -1;
						}
						break;
					}
				} catch (XmlPullParserException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
				return Integer.valueOf(tage);
			}

			@Override
			protected void onPostExecute(Integer paramInteger) {
				super.onPostExecute(paramInteger);
				HandleResult handleResult = new HandleResult();
				if (paramInteger == 1) {// 获取到返回的信息
					switch (paramInt) {
					case 1:// 预受理业务
						if (Yslyw_resultGroup.size() > 0) {
							handleResult
									.setGetWssq_Yslyw_List_InfoSuccess("success");
							handleResult
									.setWssq_Yslyw_List_Results(Yslyw_resultGroup);
						} else {
							handleResult
									.setGetWssq_Yslyw_List_InfoSuccess("fail");
						}
						break;
					case 2:// 已受理业务
						if (resultGroup.size() > 0) {
							handleResult
									.setGetSjslYislywListInfoSuccess("success");
							handleResult
									.setSjsl_Yislyw_List_Results(resultGroup);
						} else {
							handleResult
									.setGetSjslYislywListInfoSuccess("fail");
						}
						break;
					case 3:// 不予受理业务
						if (Byuslyw_resultGroup.size() > 0) {
							handleResult
									.setGetSjsl_Byuslyw_List_InfoSuccess("success");
							handleResult
									.setSjsl_Byuslyw_List_Results(Byuslyw_resultGroup);
						} else {
							handleResult
									.setGetSjsl_Byuslyw_List_InfoSuccess("fail");
						}
						break;
					case 4:// 待录入表单
						if (Dlrbd_resultGroup.size() > 0) {
							handleResult
									.setGetBdlr_Dlrbd_List_InfoSuccess("success");
							handleResult
									.setBdlr_Dlrbd_List_Results(Dlrbd_resultGroup);
						} else {
							handleResult
									.setGetBdlr_Dlrbd_List_InfoSuccess("fail");
						}
						break;
					case 5:// 已录入表单
						if (Ylrbd_resultGroup.size() > 0) {
							handleResult
									.setGetBdlr_Ylrbd_List_InfoSuccess("success");
							handleResult
									.setBdlr_Ylrbd_List_Results(Ylrbd_resultGroup);
						} else {
							handleResult
									.setGetBdlr_Ylrbd_List_InfoSuccess("fail");
						}
						break;
					case 6:// 待审批
						if (Dsp_resultGroup.size() > 0) {
							handleResult
									.setGetSpcl_Dsp_List_InfoSuccess("success");
							handleResult
									.setSpcl_Dsp_List_Results(Dsp_resultGroup);
						} else {
							handleResult
									.setGetSpcl_Dsp_List_InfoSuccess("fail");
						}
						break;
					case 7:// 已审批
						if (Ysp_resultGroup.size() > 0) {
							handleResult
									.setGetSpcl_Ysp_List_InfoSuccess("success");
							handleResult
									.setSpcl_Ysp_List_Results(Ysp_resultGroup);
						} else {
							handleResult
									.setGetSpcl_Ysp_List_InfoSuccess("fail");
						}
						break;
					case 8:// 已退回
						if (Yth_resultGroup.size() > 0) {
							handleResult
									.setGetSpcl_Yth_List_InfoSuccess("success");
							handleResult
									.setSpcl_Yth_List_Results(Yth_resultGroup);
						} else {
							handleResult
									.setGetSpcl_Yth_List_InfoSuccess("fail");
						}
						break;
					case 9:// 待发放列表
						if (Dfflb_resultGroup.size() > 0) {
							handleResult
									.setGetJgff_Dfflb_List_InfoSuccess("success");
							handleResult
									.setJgff_Dfflb_List_Results(Dfflb_resultGroup);
						} else {
							handleResult
									.setGetJgff_Dfflb_List_InfoSuccess("fail");
						}
						break;
					case 10:// 已发放列表
						if (Yfflb_resultGroup.size() > 0) {
							handleResult
									.setGetJgff_Yfflb_List_InfoSuccess("success");
							handleResult
									.setJgff_Yfflb_List_Results(Yfflb_resultGroup);
						} else {
							handleResult
									.setGetJgff_Yfflb_List_InfoSuccess("fail");
						}
						break;
					case 11:// 到期未办结列表
						if (Dqwbjlb_resultGroup.size() > 0) {
							handleResult
									.setGetJgff_Dqwbjlb_List_InfoSuccess("success");
							handleResult
									.setJgff_Dqwbjlb_List_Results(Dqwbjlb_resultGroup);
						} else {
							handleResult
									.setGetJgff_Dqwbjlb_List_InfoSuccess("fail");
						}
						break;
					case 12:// 业务档案查询
						if (Ywdacx_resultGroup.size() > 0) {
							handleResult
									.setGetYwda_Ywdacx_List_InfoSuccess("success");
							handleResult
									.setYwda_Ywdacx_List_Results(Ywdacx_resultGroup);
						} else {
							handleResult
									.setGetYwda_Ywdacx_List_InfoSuccess("fail");
						}
						break;
					}

				} else if (paramInteger == -1) {// 链接服务器失败

					Toast.makeText(paramActivity, "连接服务器失败！", Toast.LENGTH_LONG)
							.show();
					return;
				}
				ShowListInfoService.this.handlerShowListInfo(paramActivity,
						handleResult, paramInt);
			}
		};
		loacl.execute(1);
	}

	public abstract void handlerShowListInfo(Context context,
			HandleResult paramHandleResult, int paramInt);
}
