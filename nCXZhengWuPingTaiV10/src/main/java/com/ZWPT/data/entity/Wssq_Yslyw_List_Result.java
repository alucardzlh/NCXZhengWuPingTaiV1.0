package com.ZWPT.data.entity;

/**
 * 预受理业务实体类
 * 
 * @author yuhuihui
 * 
 */
public class Wssq_Yslyw_List_Result {
	public String id;
	public String applyname; // 申请人姓名
	public String idnum; // 身份证号
	public String wsysl_num; // 网上预受理号
	public String fwsx_name; // 服务事项名
	public String fwsx_id; // 服务事项id
	public String wssq_sjhm; // 手机号码
	public String wssq_dzyj; // 电子邮件
	public String wssq_sqrq; // 申请日期

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

	public String getIdnum() {
		return idnum;
	}

	public void setIdnum(String idnum) {
		this.idnum = idnum;
	}

	public String getWsysl_num() {
		return wsysl_num;
	}

	public void setWsysl_num(String wsysl_num) {
		this.wsysl_num = wsysl_num;
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

	public String getWssq_sjhm() {
		return wssq_sjhm;
	}

	public void setWssq_sjhm(String wssq_sjhm) {
		this.wssq_sjhm = wssq_sjhm;
	}

	public String getWssq_dzyj() {
		return wssq_dzyj;
	}

	public String getWssq_sqrq() {
		return wssq_sqrq;
	}

	public void setWssq_sqrq(String wssq_sqrq) {
		if (wssq_sqrq.contains(".0")) {
			wssq_sqrq = wssq_sqrq.replace(".0", "");
		}
		this.wssq_sqrq = wssq_sqrq;
	}

	public void setWssq_dzyj(String wssq_dzyj) {
		this.wssq_dzyj = wssq_dzyj;
	}

}
