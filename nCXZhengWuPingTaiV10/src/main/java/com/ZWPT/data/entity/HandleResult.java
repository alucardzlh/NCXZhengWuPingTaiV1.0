package com.ZWPT.data.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class HandleResult {

	private String accountID;
	private String LoginSuccess;// 判断是否登录成功
	private String GetLeftMenuSuccess; // 判断是否获取左侧导航菜单成功
	private String GetSjdj_First_ListSuccess; // 判断是否获取收件登记一级列表成功
	private String GetSjdj_Second_ListSuccess; // 判断是否获取收件登记二级列表成功
	private String GetSjdj_Search_ResultSuccess; // 判断是否获取搜索收件登记事项列表成功
	private String GetCounts_archives_ResultSuccess; // 判断是否获取主菜单界面事项总数
	private String GetIfAcceptForeground_ResultSuccess; // 判断收件登记事项是否前台受理
	private String GetChushen_Address_ResultSuccess; // 判断收件登记是否获取初审地点成功
	private String Getfafang_Address_ResultSuccess; // 判断收件登记是否获取发放地点成功
	private String GetSjdj_bltj_tjcl_ResultSuccess; // 判断收件登记是否获取办理条件和提交的材料成功
	private String GetSjsj_AcceptSubmitSuccess; // 判断收件登记是否提交成功
	private String GetUpdataVerssionInfoSuccess;// 判断是否获取到版本信息
	private String GetWssq_Yslyw_List_InfoSuccess;// 判断是否获取预受理信息成功
	private String GetSjslYislywListInfoSuccess;// 判断是否获取已受理信息成功
	private String GetSjsl_Byuslyw_List_InfoSuccess;// 判断是否获取不予受理信息成功
	private String GetBdlr_Dlrbd_List_InfoSuccess;// 判断是否获取待录入表单信息成功
	private String GetBdlr_Ylrbd_List_InfoSuccess;// 判断是否获取已录入表单信息成功
	private String GetSpcl_Dsp_List_InfoSuccess;// 判断是否获取待审批信息成功
	private String GetSpcl_Ysp_List_InfoSuccess;// 判断是否获取已审批信息成功
	private String GetJgff_Dqwbjlb_List_InfoSuccess;// 判断是否获取到期未办结信息成功
	private String GetYwda_Ywdacx_List_InfoSuccess;// 判断是否获取业务档案查询信息
	private String GetSpcl_Yth_List_InfoSuccess;// 判断是否获取已退回信息成功
	private String GetJgff_Dfflb_List_InfoSuccess;// 判断是否获取待发放信息成功
	private String GetJgff_Yfflb_List_InfoSuccess;// 判断是否获取已发放列表信息
	private String GetBaseDetailSuccess;// 判断是否获取已发放列表信息
	private String ZtxxSuccess;// 判断是否暂停成功
	private String KsywSuccess;// 判断是否开始成功
	private String BztzSuccess;// 判断是否补正通知成功
	private String BzjsSuccess;// 判断是否补正业务结束
	private String ThywSuccess;// 判断是否退回成功
	private String ClywSuccess;// 判断是否处理成功
	private String JfjgSuccess;// 判断是否交付结果成功
	private String FsdxSuccess;// 判断是否发送短信成功
	private String denySuccess;// 判断是否发送短信成功

	private String Sjsj_AcceptSubmitContent; // 收件登记提交成功后获得的返回值

	private String GetBdlr_getServiceSubjectFormSuccess; // 判断事项表单是否获取成功
	private String GetSubmitServiceSubjectFormSuccess; // 判断事项表单是否提交成功
	private String SubmitBdlrFormContent; // 表单录入提交成功后获得的返回值
	private String uploadPritureSuccess;// 图片上传是否成功
	private String deletePritureSuccess;// 图片删除是否成功

	private String sjdj_getTjclSuccess;// 图片删除是否成功

	private List<String> list_leftmenu; // 左侧导航栏菜单
	private List<Login_AppInfo> list = new ArrayList<Login_AppInfo>();// 登录成功后获取到的子系统信息
	private List<Map<String, Object>> list_sjdj_first; // 收件登记一级列表
	private List<Map<String, Object>> list_sjdj_second; // 收件登记二级列表
	private List<Map<String, Object>> list_sjdj_search_result; // 收件登记二级列表
	private List<Map<String, Object>> list_counts_archives; // 主菜单界面各事项总数
	private List<Map<String, Object>> list_if_accept_foreground; // 前台受理地点列表
	private List<Map<String, Object>> list_chushen_address; // 收件登记初审地点列表
	private List<Map<String, Object>> list_fafang_address; // 收件登记发放地点列表

	private List<Map<String, Object>> list_bdlr_form; // 收件登记发放地点列表

	private Sjdj_shouli_detail_bean sjdj_detail; // 收件登记办理条件和提交材料
	private List<VerssionInfo> list_info = new ArrayList<VerssionInfo>();// 获取版本更新数据信息
	private List<String> priture_list = new ArrayList<String>();// 获取图片上传后的返回值
	private String getDeletePritureInfo;// 获取图片删除后的返回值
	private List<Wssq_Yslyw_List_Result> wssq_Yslyw_List_Results = new ArrayList<Wssq_Yslyw_List_Result>();// 获取预受理信息列表
	private List<Sjsl_Yislyw_List_Result> sjsl_Yislyw_List_Results = new ArrayList<Sjsl_Yislyw_List_Result>();// 获取已受理信息列表
	private List<Sjsl_Byuslyw_List_Result> sjsl_Byuslyw_List_Results = new ArrayList<Sjsl_Byuslyw_List_Result>();// 获取不予受理信息列表
	private List<Bdlr_Dlrbd_List_Result> bdlr_Dlrbd_List_Results = new ArrayList<Bdlr_Dlrbd_List_Result>();// 获取待录入表单信息列表
	private List<Bdlr_Ylrbd_List_Result> bdlr_Ylrbd_List_Results = new ArrayList<Bdlr_Ylrbd_List_Result>();// 获取已录入表单信息列表
	private List<Spcl_Dsp_List_Result> spcl_Dsp_List_Results = new ArrayList<Spcl_Dsp_List_Result>();// 获取待审批信息列表
	private List<Spcl_Ysp_List_Result> spcl_Ysp_List_Results = new ArrayList<Spcl_Ysp_List_Result>();// 获取已审批信息列表
	private List<Jgff_Dqwbjlb_List_Result> jgff_Dqwbjlb_List_Results = new ArrayList<Jgff_Dqwbjlb_List_Result>();// 获取到期未办结信息列表
	private List<Ywda_Ywdacx_List_Result> ywda_Ywdacx_List_Results = new ArrayList<Ywda_Ywdacx_List_Result>();// 获取业务档案查询信息列表
	private List<Spcl_Yth_List_Result> spcl_Yth_List_Results = new ArrayList<Spcl_Yth_List_Result>();// 获取已退回信息列表
	private List<Jgff_Dfflb_List_Result> jgff_Dfflb_List_Results = new ArrayList<Jgff_Dfflb_List_Result>();// 获取待发放信息列表
	private List<Jgff_Yfflb_List_Result> jgff_Yfflb_List_Results = new ArrayList<Jgff_Yfflb_List_Result>();// 获取已发放列表信息

	private List<Map<String, Object>> resultGroup_tab = new ArrayList<Map<String, Object>>();
	private List<Map<String, Object>> resultGroup_jbxx = new ArrayList<Map<String, Object>>();
	private List<Map<String, Object>> resultGroup_ywbd = new ArrayList<Map<String, Object>>();
	private List<Map<String, Object>> resultGroup_wsbd = new ArrayList<Map<String, Object>>();
	private Detail_Sjdj_bean resultGroup_sjdj_dzcl = new Detail_Sjdj_bean();
	private Detail_Sjdj_bean resultGroup_jbxx_ysl = new Detail_Sjdj_bean();
	private List<Map<String, Object>> resultGroup_bzxx = new ArrayList<Map<String, Object>>();
	private List<Map<String, Object>> resultGroup_ztxx = new ArrayList<Map<String, Object>>();
	private List<Map<String, Object>> resultGroup_fjck = new ArrayList<Map<String, Object>>();
	private Detail_Ffjl_bean resultGroup_ffjl = new Detail_Ffjl_bean();
	private Detail_Wxts_bean resultGroup_wxts = new Detail_Wxts_bean();

	private List<Map<String, Object>> sjdj_tjcl_no_checkbox = new ArrayList<Map<String, Object>>();

	public String getBzjsSuccess() {
		return BzjsSuccess;
	}

	public void setBzjsSuccess(String bzjsSuccess) {
		BzjsSuccess = bzjsSuccess;
	}

	public String getThywSuccess() {
		return ThywSuccess;
	}

	public void setThywSuccess(String thywSuccess) {
		ThywSuccess = thywSuccess;
	}

	public String getBztzSuccess() {
		return BztzSuccess;
	}

	public void setBztzSuccess(String bztzSuccess) {
		BztzSuccess = bztzSuccess;
	}

	public String getKsywSuccess() {
		return KsywSuccess;
	}

	public void setKsywSuccess(String ksywSuccess) {
		KsywSuccess = ksywSuccess;
	}

	public String getZtxxSuccess() {
		return ZtxxSuccess;
	}

	public void setZtxxSuccess(String ztxxSuccess) {
		ZtxxSuccess = ztxxSuccess;
	}

	public String getGetWssq_Yslyw_List_InfoSuccess() {
		return GetWssq_Yslyw_List_InfoSuccess;
	}

	public void setGetWssq_Yslyw_List_InfoSuccess(
			String getWssq_Yslyw_List_InfoSuccess) {
		GetWssq_Yslyw_List_InfoSuccess = getWssq_Yslyw_List_InfoSuccess;
	}

	public List<Wssq_Yslyw_List_Result> getWssq_Yslyw_List_Results() {
		return wssq_Yslyw_List_Results;
	}

	public void setWssq_Yslyw_List_Results(
			List<Wssq_Yslyw_List_Result> wssq_Yslyw_List_Results) {
		this.wssq_Yslyw_List_Results = wssq_Yslyw_List_Results;
	}

	public String getGetJgff_Yfflb_List_InfoSuccess() {
		return GetJgff_Yfflb_List_InfoSuccess;
	}

	public void setGetJgff_Yfflb_List_InfoSuccess(
			String getJgff_Yfflb_List_InfoSuccess) {
		GetJgff_Yfflb_List_InfoSuccess = getJgff_Yfflb_List_InfoSuccess;
	}

	public List<Jgff_Yfflb_List_Result> getJgff_Yfflb_List_Results() {
		return jgff_Yfflb_List_Results;
	}

	public void setJgff_Yfflb_List_Results(
			List<Jgff_Yfflb_List_Result> jgff_Yfflb_List_Results) {
		this.jgff_Yfflb_List_Results = jgff_Yfflb_List_Results;
	}

	public String getGetJgff_Dfflb_List_InfoSuccess() {
		return GetJgff_Dfflb_List_InfoSuccess;
	}

	public void setGetJgff_Dfflb_List_InfoSuccess(
			String getJgff_Dfflb_List_InfoSuccess) {
		GetJgff_Dfflb_List_InfoSuccess = getJgff_Dfflb_List_InfoSuccess;
	}

	public List<Jgff_Dfflb_List_Result> getJgff_Dfflb_List_Results() {
		return jgff_Dfflb_List_Results;
	}

	public void setJgff_Dfflb_List_Results(
			List<Jgff_Dfflb_List_Result> jgff_Dfflb_List_Results) {
		this.jgff_Dfflb_List_Results = jgff_Dfflb_List_Results;
	}

	public String getGetSpcl_Yth_List_InfoSuccess() {
		return GetSpcl_Yth_List_InfoSuccess;
	}

	public void setGetSpcl_Yth_List_InfoSuccess(
			String getSpcl_Yth_List_InfoSuccess) {
		GetSpcl_Yth_List_InfoSuccess = getSpcl_Yth_List_InfoSuccess;
	}

	public List<Spcl_Yth_List_Result> getSpcl_Yth_List_Results() {
		return spcl_Yth_List_Results;
	}

	public void setSpcl_Yth_List_Results(
			List<Spcl_Yth_List_Result> spcl_Yth_List_Results) {
		this.spcl_Yth_List_Results = spcl_Yth_List_Results;
	}

	public String getGetYwda_Ywdacx_List_InfoSuccess() {
		return GetYwda_Ywdacx_List_InfoSuccess;
	}

	public void setGetYwda_Ywdacx_List_InfoSuccess(
			String getYwda_Ywdacx_List_InfoSuccess) {
		GetYwda_Ywdacx_List_InfoSuccess = getYwda_Ywdacx_List_InfoSuccess;
	}

	public List<Ywda_Ywdacx_List_Result> getYwda_Ywdacx_List_Results() {
		return ywda_Ywdacx_List_Results;
	}

	public void setYwda_Ywdacx_List_Results(
			List<Ywda_Ywdacx_List_Result> ywda_Ywdacx_List_Results) {
		this.ywda_Ywdacx_List_Results = ywda_Ywdacx_List_Results;
	}

	public String getGetJgff_Dqwbjlb_List_InfoSuccess() {
		return GetJgff_Dqwbjlb_List_InfoSuccess;
	}

	public void setGetJgff_Dqwbjlb_List_InfoSuccess(
			String getJgff_Dqwbjlb_List_InfoSuccess) {
		GetJgff_Dqwbjlb_List_InfoSuccess = getJgff_Dqwbjlb_List_InfoSuccess;
	}

	public List<Jgff_Dqwbjlb_List_Result> getJgff_Dqwbjlb_List_Results() {
		return jgff_Dqwbjlb_List_Results;
	}

	public void setJgff_Dqwbjlb_List_Results(
			List<Jgff_Dqwbjlb_List_Result> jgff_Dqwbjlb_List_Results) {
		this.jgff_Dqwbjlb_List_Results = jgff_Dqwbjlb_List_Results;
	}

	public List<Map<String, Object>> getResultGroup_ywbd() {
		return resultGroup_ywbd;
	}

	public void setResultGroup_ywbd(List<Map<String, Object>> resultGroup_ywbd) {
		this.resultGroup_ywbd = resultGroup_ywbd;
	}

	public String getGetSpcl_Ysp_List_InfoSuccess() {
		return GetSpcl_Ysp_List_InfoSuccess;
	}

	public void setGetSpcl_Ysp_List_InfoSuccess(
			String getSpcl_Ysp_List_InfoSuccess) {
		GetSpcl_Ysp_List_InfoSuccess = getSpcl_Ysp_List_InfoSuccess;
	}

	public List<Spcl_Ysp_List_Result> getSpcl_Ysp_List_Results() {
		return spcl_Ysp_List_Results;
	}

	public void setSpcl_Ysp_List_Results(
			List<Spcl_Ysp_List_Result> spcl_Ysp_List_Results) {
		this.spcl_Ysp_List_Results = spcl_Ysp_List_Results;
	}

	public String getGetSjsl_Byuslyw_List_InfoSuccess() {
		return GetSjsl_Byuslyw_List_InfoSuccess;
	}

	public void setGetSjsl_Byuslyw_List_InfoSuccess(
			String getSjsl_Byuslyw_List_InfoSuccess) {
		GetSjsl_Byuslyw_List_InfoSuccess = getSjsl_Byuslyw_List_InfoSuccess;
	}

	public List<Sjsl_Byuslyw_List_Result> getSjsl_Byuslyw_List_Results() {
		return sjsl_Byuslyw_List_Results;
	}

	public void setSjsl_Byuslyw_List_Results(
			List<Sjsl_Byuslyw_List_Result> sjsl_Byuslyw_List_Results) {
		this.sjsl_Byuslyw_List_Results = sjsl_Byuslyw_List_Results;
	}

	public String getGetSpcl_Dsp_List_InfoSuccess() {
		return GetSpcl_Dsp_List_InfoSuccess;
	}

	public void setGetSpcl_Dsp_List_InfoSuccess(
			String getSpcl_Dsp_List_InfoSuccess) {
		GetSpcl_Dsp_List_InfoSuccess = getSpcl_Dsp_List_InfoSuccess;
	}

	public List<Spcl_Dsp_List_Result> getSpcl_Dsp_List_Results() {
		return spcl_Dsp_List_Results;
	}

	public void setSpcl_Dsp_List_Results(
			List<Spcl_Dsp_List_Result> spcl_Dsp_List_Results) {
		this.spcl_Dsp_List_Results = spcl_Dsp_List_Results;
	}

	public String getGetBdlr_Ylrbd_List_InfoSuccess() {
		return GetBdlr_Ylrbd_List_InfoSuccess;
	}

	public void setGetBdlr_Ylrbd_List_InfoSuccess(
			String getBdlr_Ylrbd_List_InfoSuccess) {
		GetBdlr_Ylrbd_List_InfoSuccess = getBdlr_Ylrbd_List_InfoSuccess;
	}

	public List<Bdlr_Ylrbd_List_Result> getBdlr_Ylrbd_List_Results() {
		return bdlr_Ylrbd_List_Results;
	}

	public void setBdlr_Ylrbd_List_Results(
			List<Bdlr_Ylrbd_List_Result> bdlr_Ylrbd_List_Results) {
		this.bdlr_Ylrbd_List_Results = bdlr_Ylrbd_List_Results;
	}

	public String getGetBdlr_Dlrbd_List_InfoSuccess() {
		return GetBdlr_Dlrbd_List_InfoSuccess;
	}

	public void setGetBdlr_Dlrbd_List_InfoSuccess(
			String getBdlr_Dlrbd_List_InfoSuccess) {
		GetBdlr_Dlrbd_List_InfoSuccess = getBdlr_Dlrbd_List_InfoSuccess;
	}

	public List<Bdlr_Dlrbd_List_Result> getBdlr_Dlrbd_List_Results() {
		return bdlr_Dlrbd_List_Results;
	}

	public void setBdlr_Dlrbd_List_Results(
			List<Bdlr_Dlrbd_List_Result> bdlr_Dlrbd_List_Results) {
		this.bdlr_Dlrbd_List_Results = bdlr_Dlrbd_List_Results;
	}

	public String getGetSjslYislywListInfoSuccess() {
		return GetSjslYislywListInfoSuccess;
	}

	public void setGetSjslYislywListInfoSuccess(
			String getSjslYislywListInfoSuccess) {
		GetSjslYislywListInfoSuccess = getSjslYislywListInfoSuccess;
	}

	public List<Sjsl_Yislyw_List_Result> getSjsl_Yislyw_List_Results() {
		return sjsl_Yislyw_List_Results;
	}

	public void setSjsl_Yislyw_List_Results(
			List<Sjsl_Yislyw_List_Result> sjsl_Yislyw_List_Results) {
		this.sjsl_Yislyw_List_Results = sjsl_Yislyw_List_Results;
	}

	public String getDeletePritureSuccess() {
		return deletePritureSuccess;
	}

	public void setDeletePritureSuccess(String deletePritureSuccess) {
		this.deletePritureSuccess = deletePritureSuccess;
	}

	public String getGetDeletePritureInfo() {
		return getDeletePritureInfo;
	}

	public void setGetDeletePritureInfo(String getDeletePritureInfo) {
		this.getDeletePritureInfo = getDeletePritureInfo;
	}

	public String getUploadPritureSuccess() {
		return uploadPritureSuccess;
	}

	public void setUploadPritureSuccess(String uploadPritureSuccess) {
		this.uploadPritureSuccess = uploadPritureSuccess;
	}

	public List<String> getPriture_list() {
		return priture_list;
	}

	public void setPriture_list(List<String> priture_list) {
		this.priture_list = priture_list;
	}

	public String getClywSuccess() {
		return ClywSuccess;
	}

	public void setClywSuccess(String clywSuccess) {
		ClywSuccess = clywSuccess;
	}

	public String getGetUpdataVerssionInfoSuccess() {
		return GetUpdataVerssionInfoSuccess;
	}

	public void setGetUpdataVerssionInfoSuccess(
			String getUpdataVerssionInfoSuccess) {
		GetUpdataVerssionInfoSuccess = getUpdataVerssionInfoSuccess;
	}

	public List<VerssionInfo> getList_info() {
		return list_info;
	}

	public void setList_info(List<VerssionInfo> list_info) {
		this.list_info = list_info;
	}

	public String getGetSjdj_bltj_tjcl_ResultSuccess() {
		return GetSjdj_bltj_tjcl_ResultSuccess;
	}

	public void setGetSjdj_bltj_tjcl_ResultSuccess(
			String getSjdj_bltj_tjcl_ResultSuccess) {
		GetSjdj_bltj_tjcl_ResultSuccess = getSjdj_bltj_tjcl_ResultSuccess;
	}

	public Sjdj_shouli_detail_bean getSjdj_detail() {
		return sjdj_detail;
	}

	public void setSjdj_detail(Sjdj_shouli_detail_bean sjdj_detail) {
		this.sjdj_detail = sjdj_detail;
	}

	public List<Map<String, Object>> getList_chushen_address() {
		return list_chushen_address;
	}

	public void setList_chushen_address(
			List<Map<String, Object>> list_chushen_address) {
		this.list_chushen_address = list_chushen_address;
	}

	public List<Map<String, Object>> getList_fafang_address() {
		return list_fafang_address;
	}

	public void setList_fafang_address(
			List<Map<String, Object>> list_fafang_address) {
		this.list_fafang_address = list_fafang_address;
	}

	public String getGetIfAcceptForeground_ResultSuccess() {
		return GetIfAcceptForeground_ResultSuccess;
	}

	public void setGetIfAcceptForeground_ResultSuccess(
			String getIfAcceptForeground_ResultSuccess) {
		GetIfAcceptForeground_ResultSuccess = getIfAcceptForeground_ResultSuccess;
	}

	public List<Map<String, Object>> getList_if_accept_foreground() {
		return list_if_accept_foreground;
	}

	public void setList_if_accept_foreground(
			List<Map<String, Object>> list_if_accept_foreground) {
		this.list_if_accept_foreground = list_if_accept_foreground;
	}

	public String getGetSjdj_Search_ResultSuccess() {
		return GetSjdj_Search_ResultSuccess;
	}

	public void setGetSjdj_Search_ResultSuccess(
			String getSjdj_Search_ResultSuccess) {
		GetSjdj_Search_ResultSuccess = getSjdj_Search_ResultSuccess;
	}

	public List<Map<String, Object>> getList_sjdj_search_result() {
		return list_sjdj_search_result;
	}

	public void setList_sjdj_search_result(
			List<Map<String, Object>> list_sjdj_search_result) {
		this.list_sjdj_search_result = list_sjdj_search_result;
	}

	public String getGetSjdj_Second_ListSuccess() {
		return GetSjdj_Second_ListSuccess;
	}

	public void setGetSjdj_Second_ListSuccess(String getSjdj_Second_ListSuccess) {
		GetSjdj_Second_ListSuccess = getSjdj_Second_ListSuccess;
	}

	public List<Map<String, Object>> getList_sjdj_second() {
		return list_sjdj_second;
	}

	public void setList_sjdj_second(List<Map<String, Object>> list_sjdj_second) {
		this.list_sjdj_second = list_sjdj_second;
	}

	public String getGetSjdj_First_ListSuccess() {
		return GetSjdj_First_ListSuccess;
	}

	public void setGetSjdj_First_ListSuccess(String getSjdj_First_ListSuccess) {
		GetSjdj_First_ListSuccess = getSjdj_First_ListSuccess;
	}

	public List<Map<String, Object>> getList_sjdj_first() {
		return list_sjdj_first;
	}

	public void setList_sjdj_first(List<Map<String, Object>> list_sjdj_first) {
		this.list_sjdj_first = list_sjdj_first;
	}

	public List<String> getList_leftmenu() {
		return list_leftmenu;
	}

	public void setList_leftmenu(List<String> list_leftmenu) {
		this.list_leftmenu = list_leftmenu;
	}

	public String getGetLeftMenuSuccess() {
		return GetLeftMenuSuccess;
	}

	public void setGetLeftMenuSuccess(String getLeftMenuSuccess) {
		GetLeftMenuSuccess = getLeftMenuSuccess;
	}

	public String getLoginSuccess() {
		return LoginSuccess;
	}

	public void setLoginSuccess(String loginSuccess) {
		LoginSuccess = loginSuccess;
	}

	public List<Login_AppInfo> getList() {
		return list;
	}

	public void setList(List<Login_AppInfo> list) {
		this.list = list;
	}

	public String getGetCounts_archives_ResultSuccess() {
		return GetCounts_archives_ResultSuccess;
	}

	public String getSjsj_AcceptSubmitContent() {
		return Sjsj_AcceptSubmitContent;
	}

	public void setSjsj_AcceptSubmitContent(String sjsj_AcceptSubmitContent) {
		Sjsj_AcceptSubmitContent = sjsj_AcceptSubmitContent;
	}

	public String getSubmitBdlrFormContent() {
		return SubmitBdlrFormContent;
	}

	public void setSubmitBdlrFormContent(String submitBdlrFormContent) {
		SubmitBdlrFormContent = submitBdlrFormContent;
	}

	public void setGetCounts_archives_ResultSuccess(
			String getCounts_archives_ResultSuccess) {
		GetCounts_archives_ResultSuccess = getCounts_archives_ResultSuccess;
	}

	public List<Map<String, Object>> getList_counts_archives() {
		return list_counts_archives;
	}

	public void setList_counts_archives(
			List<Map<String, Object>> list_counts_archives) {
		this.list_counts_archives = list_counts_archives;
	}

	public String getGetChushen_Address_ResultSuccess() {
		return GetChushen_Address_ResultSuccess;
	}

	public void setGetChushen_Address_ResultSuccess(
			String getChushen_Address_ResultSuccess) {
		GetChushen_Address_ResultSuccess = getChushen_Address_ResultSuccess;
	}

	public String getGetfafang_Address_ResultSuccess() {
		return Getfafang_Address_ResultSuccess;
	}

	public void setGetfafang_Address_ResultSuccess(
			String getfafang_Address_ResultSuccess) {
		Getfafang_Address_ResultSuccess = getfafang_Address_ResultSuccess;
	}

	public String getGetSjsj_AcceptSubmitSuccess() {
		return GetSjsj_AcceptSubmitSuccess;
	}

	public void setGetSjsj_AcceptSubmitSuccess(
			String getSjsj_AcceptSubmitSuccess) {
		GetSjsj_AcceptSubmitSuccess = getSjsj_AcceptSubmitSuccess;
	}

	public String getGetBdlr_getServiceSubjectFormSuccess() {
		return GetBdlr_getServiceSubjectFormSuccess;
	}

	public void setGetBdlr_getServiceSubjectFormSuccess(
			String getBdlr_getServiceSubjectFormSuccess) {
		GetBdlr_getServiceSubjectFormSuccess = getBdlr_getServiceSubjectFormSuccess;
	}

	public List<Map<String, Object>> getList_bdlr_form() {
		return list_bdlr_form;
	}

	public void setList_bdlr_form(List<Map<String, Object>> list_bdlr_form) {
		this.list_bdlr_form = list_bdlr_form;
	}

	public String getGetSubmitServiceSubjectFormSuccess() {
		return GetSubmitServiceSubjectFormSuccess;
	}

	public void setGetSubmitServiceSubjectFormSuccess(
			String getSubmitServiceSubjectFormSuccess) {
		GetSubmitServiceSubjectFormSuccess = getSubmitServiceSubjectFormSuccess;
	}

	public String getGetBaseDetailSuccess() {
		return GetBaseDetailSuccess;
	}

	public void setGetBaseDetailSuccess(String getBaseDetailSuccess) {
		GetBaseDetailSuccess = getBaseDetailSuccess;
	}

	public String getJfjgSuccess() {
		return JfjgSuccess;
	}

	public void setJfjgSuccess(String jfjgSuccess) {
		JfjgSuccess = jfjgSuccess;
	}

	public Detail_Sjdj_bean getResultGroup_jbxx_ysl() {
		return resultGroup_jbxx_ysl;
	}

	public void setResultGroup_jbxx_ysl(Detail_Sjdj_bean resultGroup_jbxx_ysl) {
		this.resultGroup_jbxx_ysl = resultGroup_jbxx_ysl;
	}

	public String getFsdxSuccess() {
		return FsdxSuccess;
	}

	public void setFsdxSuccess(String fsdxSuccess) {
		FsdxSuccess = fsdxSuccess;
	}

	public List<Map<String, Object>> getResultGroup_tab() {
		return resultGroup_tab;
	}

	public void setResultGroup_tab(List<Map<String, Object>> resultGroup_tab) {
		this.resultGroup_tab = resultGroup_tab;
	}

	public List<Map<String, Object>> getResultGroup_jbxx() {
		return resultGroup_jbxx;
	}

	public void setResultGroup_jbxx(List<Map<String, Object>> resultGroup_jbxx) {
		this.resultGroup_jbxx = resultGroup_jbxx;
	}

	public Detail_Sjdj_bean getResultGroup_sjdj_dzcl() {
		return resultGroup_sjdj_dzcl;
	}

	public void setResultGroup_sjdj_dzcl(Detail_Sjdj_bean resultGroup_sjdj_dzcl) {
		this.resultGroup_sjdj_dzcl = resultGroup_sjdj_dzcl;
	}

	public List<Map<String, Object>> getResultGroup_bzxx() {
		return resultGroup_bzxx;
	}

	public void setResultGroup_bzxx(List<Map<String, Object>> resultGroup_bzxx) {
		this.resultGroup_bzxx = resultGroup_bzxx;
	}

	public List<Map<String, Object>> getResultGroup_ztxx() {
		return resultGroup_ztxx;
	}

	public List<Map<String, Object>> getResultGroup_fjck() {
		return resultGroup_fjck;
	}

	public void setResultGroup_fjck(List<Map<String, Object>> resultGroup_fjck) {
		this.resultGroup_fjck = resultGroup_fjck;
	}

	public void setResultGroup_ztxx(List<Map<String, Object>> resultGroup_ztxx) {
		this.resultGroup_ztxx = resultGroup_ztxx;
	}

	public Detail_Ffjl_bean getResultGroup_ffjl() {
		return resultGroup_ffjl;
	}

	public void setResultGroup_ffjl(Detail_Ffjl_bean resultGroup_ffjl) {
		this.resultGroup_ffjl = resultGroup_ffjl;
	}

	public Detail_Wxts_bean getResultGroup_wxts() {
		return resultGroup_wxts;
	}

	public void setResultGroup_wxts(Detail_Wxts_bean resultGroup_wxts) {
		this.resultGroup_wxts = resultGroup_wxts;
	}

	public List<Map<String, Object>> getResultGroup_wsbd() {
		return resultGroup_wsbd;
	}

	public void setResultGroup_wsbd(List<Map<String, Object>> resultGroup_wsbd) {
		this.resultGroup_wsbd = resultGroup_wsbd;
	}

	public String getDenySuccess() {
		return denySuccess;
	}

	public List<Map<String, Object>> getSjdj_tjcl_no_checkbox() {
		return sjdj_tjcl_no_checkbox;
	}

	public void setSjdj_tjcl_no_checkbox(
			List<Map<String, Object>> sjdj_tjcl_no_checkbox) {
		this.sjdj_tjcl_no_checkbox = sjdj_tjcl_no_checkbox;
	}

	public String getSjdj_getTjclSuccess() {
		return sjdj_getTjclSuccess;
	}

	public void setSjdj_getTjclSuccess(String sjdj_getTjclSuccess) {
		this.sjdj_getTjclSuccess = sjdj_getTjclSuccess;
	}

	public void setDenySuccess(String denySuccess) {
		this.denySuccess = denySuccess;
	}

	public String getAccountID() {
		return accountID;
	}

	public void setAccountID(String accountID) {
		this.accountID = accountID;
	}

}
