package com.ZWPT.data.entity;

/**
 * 待发放列表实体类
 * 
 * @author yuhuihui
 * 
 */
public class Jgff_Dfflb_List_Result {

	private String id;
	public String applyname; // 申请人姓名
	public String fwsx_name; // 服务事项名
	public String fwsx_id; // 服务事项id
	public String blzt; // 办理状态,开始定义失误，这里其实是办结状态
	public String banjie_date; // 办结日期
	public String shenqing_date; // 申请日期
	public String apply_id; // 申请人身份证号
	public String yewuliushuihao; // 业务流水号
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

	public String getFwsx_id() {
		return fwsx_id;
	}

	public void setFwsx_id(String fwsx_id) {
		this.fwsx_id = fwsx_id;
	}

	public String getBlzt() {
		return blzt;
	}

	public void setBlzt(String blzt) {
		this.blzt = blzt;
	}

	public String getBanjie_date() {
		return banjie_date;
	}

	public void setBanjie_date(String banjie_date) {
		if (banjie_date.contains(".0")) {
			banjie_date = banjie_date.replace(".0", "");
		}
		this.banjie_date = banjie_date;
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

	public String getApply_id() {
		return apply_id;
	}

	public void setApply_id(String apply_id) {
		this.apply_id = apply_id;
	}

	public String getYewuliushuihao() {
		return yewuliushuihao;
	}

	public void setYewuliushuihao(String yewuliushuihao) {
		this.yewuliushuihao = yewuliushuihao;
	}

}
