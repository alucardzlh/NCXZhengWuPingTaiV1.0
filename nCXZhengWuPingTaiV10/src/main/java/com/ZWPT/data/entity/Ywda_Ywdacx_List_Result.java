package com.ZWPT.data.entity;

/**
 * 业务档案查询实体类
 * 
 * @author yuhuihui
 * 
 */
public class Ywda_Ywdacx_List_Result {

	private String id;
	public String applyname; // 申请人姓名
	public String fwsx_name; // 服务事项名
	public String fwsx_id; // 服务事项id
	public String dazt; // 档案状态
	public String shenqing_date; // 申请日期
	public String shouliren; // 受理人
	public String yewuhuanjie; // 业务所处环节
	public String ywlsh; // 业务流水号
	public String blzt_real; // 办理状态

	public String getBlzt_real() {
		return blzt_real;
	}

	public void setBlzt_real(String blzt_real) {
		this.blzt_real = blzt_real;
	}

	public String getYwlsh() {
		return ywlsh;
	}

	public void setYwlsh(String ywlsh) {
		this.ywlsh = ywlsh;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getApplyname() {
		return applyname;
	}

	public void setApplyname(String applyname) {
		this.applyname = applyname;
	}

	public String getFwsx_name() {
		return fwsx_name;
	}

	public void setFwsx_name(String fwsx_name) {
		this.fwsx_name = fwsx_name;
	}

	public String getDazt() {
		return dazt;
	}

	public void setDazt(String dazt) {
		this.dazt = dazt;
	}

	public String getShenqing_date() {
		return shenqing_date;
	}

	public void setShenqing_date(String shenqing_date) {
		if (shenqing_date.contains(".0")) {
			shenqing_date = shenqing_date.replace(".0", "");
		}
		this.shenqing_date = shenqing_date;
	}

	public String getShouliren() {
		return shouliren;
	}

	public void setShouliren(String shouliren) {
		this.shouliren = shouliren;
	}

	public String getYewuhuanjie() {
		return yewuhuanjie;
	}

	public String getFwsx_id() {
		return fwsx_id;
	}

	public void setFwsx_id(String fwsx_id) {
		this.fwsx_id = fwsx_id;
	}

	public void setYewuhuanjie(String yewuhuanjie) {
		this.yewuhuanjie = yewuhuanjie;
	}

}
