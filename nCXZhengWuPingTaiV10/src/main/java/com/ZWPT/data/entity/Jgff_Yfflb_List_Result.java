package com.ZWPT.data.entity;

/**
 * 已发放列表实体类
 * 
 * @author yuhuihui
 * 
 */
public class Jgff_Yfflb_List_Result {
	private String id;
	public String applyname; // 申请人姓名
	public String fwsx_name; // 服务事项名
	public String fwsx_id; // 服务事项id

	public String getFwsx_id() {
		return fwsx_id;
	}

	public void setFwsx_id(String fwsx_id) {
		this.fwsx_id = fwsx_id;
	}

	public String blzt; // 办理状态,开始定义失误，这里其实是办结状态
	public String fafang_date; // 发放日期
	public String banjianliushuihao; // 办结流水号

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

	public String getBlzt() {
		return blzt;
	}

	public void setBlzt(String blzt) {
		this.blzt = blzt;
	}

	public String getFafang_date() {
		return fafang_date;
	}

	public void setFafang_date(String fafang_date) {
		if (fafang_date.contains(".0")) {
			fafang_date = fafang_date.replace(".0", "");
		}
		this.fafang_date = fafang_date;
	}

	public String getBanjianliushuihao() {
		return banjianliushuihao;
	}

	public void setBanjianliushuihao(String banjianliushuihao) {
		this.banjianliushuihao = banjianliushuihao;
	}

}
