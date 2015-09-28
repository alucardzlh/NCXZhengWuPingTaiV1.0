package com.ZWPT.data;

import com.googlecode.androidannotations.annotations.sharedpreferences.SharedPref;
import com.googlecode.androidannotations.annotations.sharedpreferences.SharedPref.Scope;

@SharedPref(value = Scope.APPLICATION_DEFAULT)
public interface InfoFile {
	String infoCookie();

	boolean isAutoLogin();// 是否自动登录

	String infoUsername();// 用户名

	String infoUserId();// 用户ID

	String infoPassword();// 密码

	String numOfYslzs(); // 预受理总数

	String numOfDspzs(); // 待审批总数

	String numOfDffzs(); // 待发放总数

	String numOfDqwbjzs(); // 到期未办结总数

	String serviceSubjectId();// 事项id

	String serviceSubjectName();// 事项名称

	String postinfoname();// 提交材料名

	String postinfoid();// 提交材料id

	String postinfotype();// 提交材料类型

	String shengqingrenname();// 申请人姓名

	String shengqingrenCardId();// 申请人身份证号

	String yslyw_shengqingrenCardId();// 预受理业务申请人身份证号

	String shengqingriqi();// 申请日期

	String banjieriqi();// 办结日期

	String wssq_wsyslh();// 网上预受理号

	String baselist_yewuliushuihao();// 业务流水号

	String jgff_yewuliushuihao();// 业务流水号

	String dxtz_beizhu();// 短信通知里面的备注

	String dxtz_shibaiyuanyin();// 短信通知里面的失败原因

	String ZTYWkaishishijian();// 暂停业务开始时间

	String ZTYWyuanyin();// 暂停业务原因

	String bdlr_ywlsh(); // 表单录入需要的业务流水号

	String bizArchivesId();// 各项信息详情id

	String bz_sqr_phone();// 补正申请人电话

	String bz_msg();// 补正短信

	String bz_slczy();// 补正受理操作员

	String bz_yy();// 补正原因

	String dfflb_bjzt();// 待发放业务办结状态
	
	String serviceTargetPhone();//结果发放时自动填充的手机号码
}
