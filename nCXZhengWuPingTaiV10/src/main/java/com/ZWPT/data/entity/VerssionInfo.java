package com.ZWPT.data.entity;

public class VerssionInfo {
	private String verssionCode;// 版本号
	private String updataTime;// 版本更新时间
	private String beizhu;// 备注
	private String APKpath;// app下载路径
	private boolean isImport;// 是否是重要更新

	public String getVerssionCode() {
		return verssionCode;
	}

	public void setVerssionCode(String verssionCode) {
		this.verssionCode = verssionCode;
	}

	public String getUpdataTime() {
		return updataTime;
	}

	public void setUpdataTime(String updataTime) {
		this.updataTime = updataTime;
	}

	public String getBeizhu() {
		return beizhu;
	}

	public void setBeizhu(String beizhu) {
		this.beizhu = beizhu;
	}

	public String getAPKpath() {
		return APKpath;
	}

	public void setAPKpath(String aPKpath) {
		APKpath = aPKpath;
	}

	public boolean isImport() {
		return isImport;
	}

	public void setImport(boolean isImport) {
		this.isImport = isImport;
	}

}
