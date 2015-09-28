package com.ZWPT.Utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Map;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.SoapFault;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import android.R.integer;
import android.content.Context;
import android.os.Environment;
import android.util.Log;

/**
 * http请求工具类
 * 
 * @author yuhuihui
 * @data 2014-5-30
 */
public class HttpUtil {
	/**
	 * 普通下载
	 * 
	 * @param url
	 *            下载地址
	 * @param filePath
	 *            保存路径
	 * @return File对象，如果长度为0则返回null
	 * @author yuhuihui
	 * @date 2014-6-3
	 */
	public static File downLoad(String url, String filePath) {
		if (StringUtil.isTrimBlank(url)) {
			return null;
		}
		try {
			URL connUrl = new URL(url);
			HttpURLConnection conn = (HttpURLConnection) connUrl
					.openConnection();
			conn.setConnectTimeout(30 * 1000);
			conn.setReadTimeout(30 * 1000);
			conn.setDoInput(true);
			conn.connect();

			File file = new File(filePath);
			file.createNewFile();
			InputStream is = conn.getInputStream();
			FileOutputStream fos = new FileOutputStream(file);
			byte[] temp = new byte[1024];
			int i = 0;
			while ((i = is.read(temp)) > 0) {
				fos.write(temp, 0, i);
			}
			fos.close();
			is.close();
			if (file.length() > 0) {
				return file;
			}
		} catch (IOException e) {
			Log.e("HttpUtil (down) --> ", e.toString());
		}
		return null;
	}

	/**
	 * 获取xml 类型数据
	 * 
	 * @param activity上下文
	 * @param methodName
	 *            方法
	 * @param paramList
	 *            参数
	 * @return result.toString
	 * @throws SoapFault
	 */
	public static String getXmlData(Context context, String methodName,
			String endPoint, List<Map<String, Object>> list) throws SoapFault {
		SoapPrimitive result = null;
		SoapObject result_1 = null;
		SoapPrimitive result_ysl = null;
		String nameSpace;
		String soapAction;
		if (methodName.equals("getAPPLogin")) {
			// 命名空间
			nameSpace = context.getResources().getString(
					com.ZWPT.activity.R.string.namespace);
			soapAction = nameSpace + "/" + methodName;
		} else {
			nameSpace = "http://services.publicservices.gov/";
			soapAction = nameSpace + methodName;
		}

		// 指定WebService的命名空间和调用的方法名
		SoapObject rpc = new SoapObject(nameSpace, methodName);

		// 设置需调用WebService接口需要传入的参数
		if (list != null) {
			for (int i = 0; i < list.size(); i++) {
				if (methodName.equals("getAPPLogin")) {
					rpc.addProperty("account", list.get(i).get("account"));
					rpc.addProperty("password", list.get(i).get("password"));
				}
				if (methodName.equals("getLeftMenu")) {
					rpc.addProperty("account", list.get(i).get("account"));
				}
				if (methodName.equals("getDepartmentSubject")) {
					rpc.addProperty("adminOrgId", list.get(i).get("adminOrgId"));
					rpc.addProperty("account", list.get(i).get("account"));
				}
				if (methodName.equals("getSearchSubject")) {
					rpc.addProperty("content", list.get(i).get("content"));
					rpc.addProperty("account", list.get(i).get("account"));
				}
				if (methodName.equals("getBizArchivesCount")) {
					rpc.addProperty("account", list.get(i).get("account"));
				}
				/*
				 * 此接口，如果serviceSubjectId为1，则接口的作用为获取用户ID 否则接口的作用为收件登记中的获取前台受理
				 */
				if (methodName.equals("getAcceptQtsl")) {
					if (list.get(i).get("serviceSubjectId").equals("1")) {
						rpc.addProperty("serviceSubjectId", "1");
						rpc.addProperty("account", list.get(i).get("account"));
					} else {
						rpc.addProperty("serviceSubjectId",
								list.get(i).get("serviceSubjectId"));
						rpc.addProperty("account", list.get(i).get("account"));
					}

				}
				if (methodName.equals("getAcceptCsdd")) {
					rpc.addProperty("serviceSubjectId",
							list.get(i).get("serviceSubjectId"));
					rpc.addProperty("divisionCode",
							list.get(i).get("divisionCode"));
				}
				if (methodName.equals("getAcceptFfdd")) {
					rpc.addProperty("serviceSubjectId",
							list.get(i).get("serviceSubjectId"));
					rpc.addProperty("organizationId", list.get(i).get("orgId"));
				}
				if (methodName.equals("getAcceptInfo")) {
					rpc.addProperty("serviceSubjectId",
							list.get(i).get("serviceSubjectId"));
				}
				if (methodName.equals("getAcceptSubmit")) {
					rpc.addProperty("bizArchivesId",
							list.get(i).get("bizArchivesId"));
					rpc.addProperty("serviceSubjectId",
							list.get(i).get("serviceSubjectId"));
					rpc.addProperty("accountId", list.get(i).get("accountId"));
					rpc.addProperty("userName", list.get(i).get("userName"));
					rpc.addProperty("identityNumber",
							list.get(i).get("identityNumber"));
					rpc.addProperty("handleLocation",
							list.get(i).get("handleLocation"));
					rpc.addProperty("handleLocationName",
									list.get(i).get("handleLocationName"));
					rpc.addProperty("grantLocation",
							list.get(i).get("grantLocation"));
					rpc.addProperty("grantLocationName",
							list.get(i).get("grantLocationName"));
					rpc.addProperty("resultCode", list.get(i).get("resultCode"));
					if(list.get(i).get("serviceTargetPhone") != null){
						rpc.addProperty("serviceTargetPhone",list.get(i).get("serviceTargetPhone"));
					}
					if(list.get(i).get("serviceTargetEmail") != null){
						rpc.addProperty("serviceTargetEmail",list.get(i).get("serviceTargetEmail"));
					}
				}
				if (methodName.equals("getAcceptCjImage")) {
					rpc.addProperty("accountId", list.get(i).get("accountId"));// 用户ID
					rpc.addProperty("serviceSubjectId",
							list.get(i).get("serviceSubjectId"));// 事项ID
					rpc.addProperty("name", list.get(i).get("name"));// 提交材料姓名
					rpc.addProperty("holderName", list.get(i).get("holderName"));// 申请人姓名
					rpc.addProperty("holderIdentityNumber",
							list.get(i).get("holderIdentityNumber"));// 申请人身份证
					rpc.addProperty("identityType",
							list.get(i).get("identityType"));// 提交材料的类型
					rpc.addProperty("materialId", list.get(i).get("materialId"));// 提交材料的ID
					rpc.addProperty("imageStream",
							list.get(i).get("imageStream"));// 上传图片的二进制
				}
				if (methodName.equals("getServiceSubjectForm")) {
					rpc.addProperty("serviceSubjectName",
							list.get(i).get("serviceSubjectName"));// 事项ID
				}
				if (methodName.equals("fillForm")) {
					rpc.addProperty("xml", list.get(i).get("xml"));
				}
				if (methodName.equals("deleteCertificateImage")) {
					rpc.addProperty("imageId", list.get(i).get("imageId"));
				}
				if (methodName.equals("getBizArchivesList")) {
					rpc.addProperty("account", list.get(i).get("account"));// 用户登录账号
					rpc.addProperty("type", list.get(i).get("type"));// 类型
					rpc.addProperty("pageSize", list.get(i).get("pageSize"));// 分页数
					rpc.addProperty("pageCount", list.get(i).get("pageCount"));// 每页的个数
					rpc.addProperty("serviceTargetId",
							list.get(i).get("serviceTargetId"));// 身份证号
					rpc.addProperty("serialNumber",
							list.get(i).get("serialNumber"));// 流水号
					rpc.addProperty("serviceTargetName",
							list.get(i).get("serviceTargetName"));// 申请人姓名
					rpc.addProperty("serviceSubjectName",
							list.get(i).get("serviceSubjectName"));// 事项名称
					rpc.addProperty("applyDateStart",
							list.get(i).get("applyDateStart"));// 申请开始日期
					rpc.addProperty("applyDateEnd",
							list.get(i).get("applyDateEnd"));// 申请结束日期
				}
				if (methodName.equals("getShowTabs")) {

					rpc.addProperty("bizArchivesId",
							list.get(i).get("bizArchivesId"));
					rpc.addProperty("account", list.get(i).get("account"));
				}
				if (methodName.equals("getBasicInfo")//基本信息
						|| methodName.equals("getBizReceiptInfo")//收件登记和电子材料
						|| methodName.equals("getDeliveryRecordInfo")
						|| methodName.equals("getTipInfo")
						|| methodName.equals("getCorrectInfo")
						|| methodName.equals("getPauseReasonInfo")) {
					rpc.addProperty("bizArchivesId",
							list.get(i).get("bizArchivesId"));
				}
				if (methodName.equals("getBizArchivesForm")) {//业务表单
					rpc.addProperty("serviceSubjectName",
							list.get(i).get("serviceSubjectName"));// 事项名称
					rpc.addProperty("serialNumber",
							list.get(i).get("serialNumber"));
				}
				if (methodName.equals("doPauseBizArchives")) {
					rpc.addProperty("bizArchivesId",
							list.get(i).get("bizArchivesId"));
					rpc.addProperty("pauseReason",
							list.get(i).get("pauseReason"));
					rpc.addProperty("userAccountId",
							list.get(i).get("userAccountId"));
					// rpc.addProperty("userName", "王武中文");
					rpc.addProperty("userName", list.get(i).get("userName"));
				}
				if (methodName.equals("doStartBizArchives")) {
					rpc.addProperty("bizArchivesId",
							list.get(i).get("bizArchivesId"));
					rpc.addProperty("userAccountId",
							list.get(i).get("userAccountId"));
					rpc.addProperty("userName", list.get(i).get("userName"));

				}
				if (methodName.equals("doCorrectSendNote")) {
					rpc.addProperty("bizArchivesId",
							list.get(i).get("bizArchivesId"));
					rpc.addProperty("userName", list.get(i).get("userName"));
					rpc.addProperty("phoneNum", list.get(i).get("phoneNum"));
					rpc.addProperty("messageContent",
							list.get(i).get("messageContent"));
					rpc.addProperty("correctReason",
							list.get(i).get("correctReason"));
				}
				if (methodName.equals("doCorrectAccept")) {
					rpc.addProperty("bizArchivesId",
							list.get(i).get("bizArchivesId"));
					rpc.addProperty("userAccountId",
							list.get(i).get("userAccountId"));
					rpc.addProperty("userName", list.get(i).get("userName"));
				}
				if (methodName.equals("doBizArchivesWithDrawal")) {
					rpc.addProperty("bizArchivesId",
							list.get(i).get("bizArchivesId"));
					rpc.addProperty("userAccountId",
							list.get(i).get("userAccountId"));
					rpc.addProperty("withDrawalReason",
							list.get(i).get("withDrawalReason"));
				}
				if (methodName.equals("doBizArchives")) {
					rpc.addProperty("bizArchivesId",
							list.get(i).get("bizArchivesId"));
					// rpc.addProperty("bizArchivesId",
					// "5227c331-4801-419f-9210-a9d5a7222db6");
					rpc.addProperty("handleStatus",
							list.get(i).get("handleStatus"));
					rpc.addProperty("resultDate", list.get(i).get("resultDate"));
					rpc.addProperty("handleSuggestion",
							list.get(i).get("handleSuggestion"));
					rpc.addProperty("userAccountId",
							list.get(i).get("userAccountId"));
					rpc.addProperty("imageStream",
							list.get(i).get("imageStream"));
				}
				if (methodName.equals("doSendReceipt")) {
					rpc.addProperty("bizArchivesId",
							list.get(i).get("bizArchivesId"));
					rpc.addProperty("userAccountId",
							list.get(i).get("userAccountId"));
					rpc.addProperty("imageStream",
							list.get(i).get("imageStream"));
					// byte[] buffer =
					// list.get(i).get("imageStream").toString().getBytes();
					// try {
					// writeSDCardFile(foldername,buffer);
					// } catch (IOException e) {
					// // TODO 自动生成的 catch 块
					// e.printStackTrace();
					// }
				}
				if (methodName.equals("doSendSM")) {
					rpc.addProperty("bizArchivesId",
							list.get(i).get("bizArchivesId"));
					rpc.addProperty("userAccountId",
							list.get(i).get("userAccountId"));
					// rpc.addProperty("messageContent",
					// list.get(i).get("messageContent"));
					rpc.addProperty("sendDelivery",
							list.get(i).get("sendDelivery"));
					rpc.addProperty("serviceTargetPhone",
							list.get(i).get("serviceTargetPhone"));
				}
				if (methodName.equals("getYslReceiptByBizArchivesId")) {
					rpc.addProperty("bizArchivesId",
							list.get(i).get("bizArchivesId"));
				}
				if (methodName.equals("getBizAttachmentByBizArchivesId")) {
					rpc.addProperty("bizArchivesId",
							list.get(i).get("bizArchivesId"));
				}
				if (methodName.equals("getBizArchivesOnlineForm")) {
					rpc.addProperty("serviceSubjectName",
							list.get(i).get("serviceSubjectName"));
					rpc.addProperty("serviceTargetId",
							list.get(i).get("serviceTargetId"));
				}
				if (methodName.equals("noAccept")) {
					rpc.addProperty("bizArchivesId",
							list.get(i).get("bizArchivesId"));
					rpc.addProperty("userAccountId",
							list.get(i).get("userAccountId"));
					rpc.addProperty("handleSuggestion",
							list.get(i).get("handleSuggestion"));
				}
				if (methodName.equals("getAcceptInfo2")) {
					rpc.addProperty("serviceSubjectId",
							list.get(i).get("serviceSubjectId"));
					rpc.addProperty("resultCode", list.get(i).get("resultCode"));
				}
				// 匹配当前方法名
				if (methodName.equals("")) {
					// 添加入参版本号信息
				}
			}
		}

		// 生成调用WebService方法的SOAP请求信息,并指定SOAP的版本
		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
				SoapEnvelope.VER11);

		envelope.bodyOut = rpc;
		// 设置是否调用的是dotNet开发的WebService
		// envelope.dotNet = true;
		// 等价于envelope.bodyOut = rpc;
		// envelope.setOutputSoapObject(rpc);
        int timeout=60000;
		HttpTransportSE transport = new HttpTransportSE(endPoint,timeout);
		
		try {
			// 调用WebService
			transport.call(soapAction, envelope);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (envelope.bodyIn != null) {
			// result = (SoapPrimitive) envelope.getResponse();
			if (methodName.equals("getBizArchivesOnlineForm")) {
				result_ysl = (SoapPrimitive) envelope.getResponse();
				return result_ysl.toString();
			} else {
				String str = envelope.getResponse().getClass().toString();
				if (str.equals("class org.ksoap2.serialization.SoapPrimitive")) {
					result = (SoapPrimitive) envelope.getResponse();
					if (result != null) {
						return result.toString();
					}
				} else if (str
						.equals("class org.ksoap2.serialization.SoapObject")) {
					result_1 = (SoapObject) envelope.getResponse();
					if (result_1 != null) {
						return result_1.toString();
					}
				}

			}

		} 
		/*if(methodName.equals("getServiceSubjectForm")&&envelope.bodyIn == null){
			String abc="<?xml version=\"1.0\" encoding=\"utf-8\"?><case><ZsoftInfo><ServiceSubjectColumn columnName='BH' name='编号' value='' dataType='STRING' length='50' nonEmpty='true' controlType='TEXTBOX' index='1' groupName='文件信息' groupIndex='1'  isShowGroupTitle='true' inputControlValueScope='null'></ServiceSubjectColumn><ServiceSubjectColumn columnName='SQRXM' name='申请人姓名' value='' dataType='STRING' length='50' nonEmpty='false' controlType='TEXTBOX' index='2' groupName='申请人信息' groupIndex='2'  isShowGroupTitle='true' inputControlValueScope='null'></ServiceSubjectColumn><ServiceSubjectColumn columnName='SQRSFZHM' name='申请人身份证号码' value='' dataType='STRING' length='50' nonEmpty='false' controlType='TEXTBOX' index='3' groupName='申请人信息' groupIndex='2'  isShowGroupTitle='true' inputControlValueScope='null'></ServiceSubjectColumn><ServiceSubjectColumn columnName='SQRCSNY' name='申请人出生年月' value='' dataType='DATETIME' length='12' nonEmpty='true' controlType='DATEFIELD' index='4' groupName='申请人信息' groupIndex='2'  isShowGroupTitle='true' inputControlValueScope='null'></ServiceSubjectColumn><ServiceSubjectColumn columnName='SQRHYZK' name='申请人婚姻状况' value='' dataType='STRING' length='20' nonEmpty='true' controlType='DROPDOWN_LIST' index='5' groupName='申请人信息' groupIndex='2'  isShowGroupTitle='true' inputControlValueScope='初婚,再婚'></ServiceSubjectColumn><ServiceSubjectColumn columnName='SQRHKSZDXZ' name='申请人户口所在地详址' value='' dataType='STRING' length='300' nonEmpty='true' controlType='ADDRESS' index='6' groupName='申请人信息' groupIndex='2'  isShowGroupTitle='true' inputControlValueScope='null'></ServiceSubjectColumn><ServiceSubjectColumn columnName='SQRHKSZDXZQH' name='申请人户口所在地行政区划' value='' dataType='STRING' length='300' nonEmpty='true' controlType='ADDRESS' index='7' groupName='申请人信息' groupIndex='2'  isShowGroupTitle='true' inputControlValueScope='null'></ServiceSubjectColumn><ServiceSubjectColumn columnName='SQRXJZDXZ' name='申请人现居住地详址' value='' dataType='STRING' length='300' nonEmpty='true' controlType='ADDRESS' index='8' groupName='申请人信息' groupIndex='2'  isShowGroupTitle='true' inputControlValueScope='null'></ServiceSubjectColumn><ServiceSubjectColumn columnName='SQRXJZDXZQH' name='申请人现居住地行政区划' value='' dataType='STRING' length='300' nonEmpty='true' controlType='ADDRESS' index='9' groupName='申请人信息' groupIndex='2'  isShowGroupTitle='true' inputControlValueScope='null'></ServiceSubjectColumn><ServiceSubjectColumn columnName='PODJRXM' name='配偶登记人姓名' value='' dataType='STRING' length='50' nonEmpty='true' controlType='TEXTBOX' index='10' groupName='配偶信息' groupIndex='3'  isShowGroupTitle='true' inputControlValueScope='null'></ServiceSubjectColumn><ServiceSubjectColumn columnName='POSFZHM' name='配偶身份证号码' value='' dataType='STRING' length='50' nonEmpty='true' controlType='TEXTBOX' index='11' groupName='配偶信息' groupIndex='3'  isShowGroupTitle='true' inputControlValueScope='null'></ServiceSubjectColumn><ServiceSubjectColumn columnName='POCSNY' name='配偶出生年月' value='' dataType='DATETIME' length='12' nonEmpty='true' controlType='DATEFIELD' index='12' groupName='配偶信息' groupIndex='3'  isShowGroupTitle='true' inputControlValueScope='null'></ServiceSubjectColumn><ServiceSubjectColumn columnName='POHYZK' name='配偶婚姻状况' value='' dataType='STRING' length='20' nonEmpty='true' controlType='DROPDOWN_LIST' index='13' groupName='配偶信息' groupIndex='3'  isShowGroupTitle='true' inputControlValueScope='初婚,再婚'></ServiceSubjectColumn><ServiceSubjectColumn columnName='POHKSZDXZ' name='配偶户口所在地详址' value='' dataType='STRING' length='300' nonEmpty='true' controlType='ADDRESS' index='14' groupName='配偶信息' groupIndex='3'  isShowGroupTitle='true' inputControlValueScope='null'></ServiceSubjectColumn><ServiceSubjectColumn columnName='POHKSZDXZQH' name='配偶户口所在地行政区划' value='' dataType='STRING' length='300' nonEmpty='true' controlType='ADDRESS' index='15' groupName='配偶信息' groupIndex='3'  isShowGroupTitle='true' inputControlValueScope='null'></ServiceSubjectColumn><ServiceSubjectColumn columnName='POXJZDXZ' name='配偶现居住地详址' value='' dataType='STRING' length='300' nonEmpty='true' controlType='ADDRESS' index='16' groupName='配偶信息' groupIndex='3'  isShowGroupTitle='true' inputControlValueScope='null'></ServiceSubjectColumn><ServiceSubjectColumn columnName='POXJZDXZQH' name='配偶现居住地行政区划' value='' dataType='STRING' length='300' nonEmpty='true' controlType='ADDRESS' index='17' groupName='配偶信息' groupIndex='3'  isShowGroupTitle='true' inputControlValueScope='null'></ServiceSubjectColumn></ZsoftInfo></case>";
			return abc;
		}*/
		else {
			result = null;
		}
		return "error";
	}

	static String foldername = Environment.getExternalStorageDirectory()
			.getPath() + "/log_2222.txt";
	static String foldername1 = Environment.getExternalStorageDirectory()
			.getPath() + "/log_ffdd.txt";

	public static void writeSDCardFile(String path, byte[] buffer)
			throws IOException {

		File file = new File(path);

		FileOutputStream fos = new FileOutputStream(file);

		fos.write(buffer);// 写入buffer数组。如果想写入一些简单的字符，可以将String.getBytes()再写入文件;

		fos.close();

	}
}
