package com.ZWPT.Utils;

import java.io.StringReader;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.xmlpull.v1.XmlPullParserException;

import com.ZWPT.data.entity.Bdlr_Dlrbd_List_Result;
import com.ZWPT.data.entity.Bdlr_Ylrbd_List_Result;
import com.ZWPT.data.entity.Detail_Ffjl_bean;
import com.ZWPT.data.entity.Detail_Sjdj_bean;
import com.ZWPT.data.entity.Detail_Wxts_bean;
import com.ZWPT.data.entity.Jgff_Dfflb_List_Result;
import com.ZWPT.data.entity.Jgff_Dqwbjlb_List_Result;
import com.ZWPT.data.entity.Jgff_Yfflb_List_Result;
import com.ZWPT.data.entity.Login_Info;
import com.ZWPT.data.entity.Sjdj_shouli_detail_bean;
import com.ZWPT.data.entity.Sjsl_Byuslyw_List_Result;
import com.ZWPT.data.entity.Sjsl_Yislyw_List_Result;
import com.ZWPT.data.entity.Spcl_Dsp_List_Result;
import com.ZWPT.data.entity.Spcl_Ysp_List_Result;
import com.ZWPT.data.entity.Spcl_Yth_List_Result;
import com.ZWPT.data.entity.Wssq_Yslyw_List_Result;
import com.ZWPT.data.entity.Ywda_Ywdacx_List_Result;
import com.ZWPT.data.entity.blcl_checkbox;

import android.util.Log;
import android.util.Xml;

/**
 * XML工具类
 * 
 * @author Goven
 * @date 2012-12-14
 */
public class XmlUtil {
	private static XmlPullParserFactory factory;

	/**
	 * 获得XmlPullParserFactory对象
	 * 
	 * @return XmlPullParserFactory对象
	 */
	private static XmlPullParserFactory getXmlPullParserFactory()
			throws Exception {
		if (factory == null) {
			synchronized (XmlUtil.class) {
				factory = XmlPullParserFactory.newInstance();
			}
		}
		return factory;
	}

	/**
	 * 解析XML,获取JSON数据部分,如果没有包含XML标签，则直接返回
	 * 
	 * @param xml
	 *            XML格式数据
	 * @return JSON格式数据
	 */
	public static String getJson(String xml) {
		if (StringUtil.isNotTrimBlank(xml) && xml.startsWith("<")
				&& xml.endsWith(">")) {
			try {
				XmlPullParser parser = getXmlPullParserFactory()
						.newPullParser();
				parser.setInput(new StringReader(xml));
				parser.next();
				xml = parser.nextText();
			} catch (Exception e) {
				return null;
			}
		}
		return xml;
	}

	/**
	 * 
	 * @param xml
	 *            需要解析的xml数据
	 * @return list 解析得到的map类型列表
	 * @throws XmlPullParserException
	 * @throws IOException
	 */

	public static Login_Info LoginXmlParse(String xml)
			throws XmlPullParserException, IOException {
		InputStream is = new ByteArrayInputStream(xml.getBytes());
		Login_Info login_info = new Login_Info();
		List<Map<String, Object>> list = null;
		Map<String, Object> map_status = null;
		Map<String, Object> map_appInfo = null;
		XmlPullParser parser = Xml.newPullParser(); // 由android.util.Xml创建一个XmlPullParser实例
		parser.setInput(is, "UTF-8"); // 设置输入流 并指明编码方式
		int eventType = parser.getEventType();
		while (eventType != XmlPullParser.END_DOCUMENT) {
			switch (eventType) {
			case XmlPullParser.START_DOCUMENT:
				list = new ArrayList<Map<String, Object>>();
				break;
			case XmlPullParser.START_TAG:
				if (parser.getName().equals("LOGINSTATUS")) {
					eventType = parser.next();
					map_status = new HashMap<String, Object>();
					map_status.put("LOGINSTATUS", parser.getText());
				} else if (parser.getName().equals("App")) {
					map_appInfo = new HashMap<String, Object>();

				} else if (parser.getName().equals("NAME")) {
					eventType = parser.next();
					map_appInfo.put("NAME", parser.getText());
				} else if (parser.getName().equals("ACCOUNT")) {
					eventType = parser.next();
					map_appInfo.put("ACCOUNT", parser.getText());
				}

				break;
			case XmlPullParser.END_TAG:
				if (parser.getName().equals("LOGINSTATUS")) {
					login_info.setMap_login_status(map_status);
				}
				if (parser.getName().equals("App")) {
					list.add(map_appInfo);
				}
				if (parser.getName().equals("AppInfo")) {
					login_info.setList_app(list);
				}
				break;
			}
			eventType = parser.next();
		}
		return login_info;
	}

	/**
	 * 解析预受理信息
	 * 
	 * @param xml
	 * @return
	 * @throws XmlPullParserException
	 * @throws IOException
	 */
	public static List<Wssq_Yslyw_List_Result> getWssq_Yslyw_List_Info(
			String xml) throws XmlPullParserException, IOException {
		InputStream is = new ByteArrayInputStream(xml.getBytes());
		List<Wssq_Yslyw_List_Result> list = null;
		Wssq_Yslyw_List_Result result = null;

		XmlPullParser parser = Xml.newPullParser();
		parser.setInput(is, "UTF-8");
		int eventType = parser.getEventType();
		while (eventType != XmlPullParser.END_DOCUMENT) {
			switch (eventType) {
			case XmlPullParser.START_DOCUMENT:
				list = new ArrayList<Wssq_Yslyw_List_Result>();
				break;
			case XmlPullParser.START_TAG:
				if (parser.getName().equals("BizArchives")) {
					result = new Wssq_Yslyw_List_Result();
					result.setId(parser.getAttributeValue(0));
					result.setApplyname(parser.getAttributeValue(1));
					result.setFwsx_name(parser.getAttributeValue(2));
					result.setFwsx_id(parser.getAttributeValue(3));
					result.setIdnum(parser.getAttributeValue(6));
					result.setWssq_sqrq(parser.getAttributeValue(5));
					result.setWsysl_num(parser.getAttributeValue(12));
					list.add(result);
				}
				break;
			case XmlPullParser.END_TAG:
				break;
			}
			eventType = parser.next();
		}
		return list;
	}

	/**
	 * 解析已受理业务数据
	 * 
	 * @param xml
	 * @return
	 * @throws XmlPullParserException
	 * @throws IOException
	 */
	public static List<Sjsl_Yislyw_List_Result> getSjslYislywListInfo(String xml)
			throws XmlPullParserException, IOException {
		InputStream is = new ByteArrayInputStream(xml.getBytes());
		List<Sjsl_Yislyw_List_Result> list = null;
		Sjsl_Yislyw_List_Result result = null;

		XmlPullParser parser = Xml.newPullParser();
		parser.setInput(is, "UTF-8");
		int eventType = parser.getEventType();
		while (eventType != XmlPullParser.END_DOCUMENT) {
			switch (eventType) {
			case XmlPullParser.START_DOCUMENT:
				list = new ArrayList<Sjsl_Yislyw_List_Result>();
				break;
			case XmlPullParser.START_TAG:
				if (parser.getName().equals("BizArchives")) {
					result = new Sjsl_Yislyw_List_Result();
					result.setId(parser.getAttributeValue(0));
					result.setApplyname(parser.getAttributeValue(1));
					result.setFwsx_name(parser.getAttributeValue(2));
					result.setFwsx_id(parser.getAttributeValue(3));
					result.setYwlsh(parser.getAttributeValue(12));
					result.setBlzt(parser.getAttributeValue(8));
					result.setBlzt_real(parser.getAttributeValue(10));
					result.setShenqing_date(parser.getAttributeValue(5));
					list.add(result);
				}
				break;
			case XmlPullParser.END_TAG:
				break;
			}
			eventType = parser.next();
		}
		return list;
	}

	/**
	 * 解析不予受理业务信息
	 * 
	 * @param xml
	 * @return
	 * @throws XmlPullParserException
	 * @throws IOException
	 */
	public static List<Sjsl_Byuslyw_List_Result> getSjsl_Byuslyw_List_Info(
			String xml) throws XmlPullParserException, IOException {
		InputStream is = new ByteArrayInputStream(xml.getBytes());
		List<Sjsl_Byuslyw_List_Result> oList = null;
		Sjsl_Byuslyw_List_Result result = null;

		XmlPullParser parser = Xml.newPullParser();
		parser.setInput(is, "UTF-8");
		int eventType = parser.getEventType();
		while (eventType != XmlPullParser.END_DOCUMENT) {
			switch (eventType) {
			case XmlPullParser.START_DOCUMENT:
				oList = new ArrayList<Sjsl_Byuslyw_List_Result>();
				break;
			case XmlPullParser.START_TAG:
				if (parser.getName().equals("BizArchives")) {
					result = new Sjsl_Byuslyw_List_Result();
					result.setId(parser.getAttributeValue(0));
					result.setApplyname(parser.getAttributeValue(1));
					result.setFwsx_name(parser.getAttributeValue(2));
					result.setFwsx_id(parser.getAttributeValue(3));
					result.setShenqing_date(parser.getAttributeValue(5));
					result.setYwlsh(parser.getAttributeValue(12));
					oList.add(result);
				}
				break;
			case XmlPullParser.END_TAG:
				break;
			}
			eventType = parser.next();
		}
		return oList;
	}

	/**
	 * 解析待录入表单数据
	 * 
	 * @param xml
	 * @return
	 * @throws XmlPullParserException
	 * @throws IOException
	 */
	public static List<Bdlr_Dlrbd_List_Result> getBdlr_Dlrbd_List_Info(
			String xml) throws XmlPullParserException, IOException {
		InputStream is = new ByteArrayInputStream(xml.getBytes());
		List<Bdlr_Dlrbd_List_Result> oList = null;
		Bdlr_Dlrbd_List_Result result = null;

		XmlPullParser parser = Xml.newPullParser();
		parser.setInput(is, "UTF-8");
		int eventType = parser.getEventType();
		while (eventType != XmlPullParser.END_DOCUMENT) {
			switch (eventType) {
			case XmlPullParser.START_DOCUMENT:
				oList = new ArrayList<Bdlr_Dlrbd_List_Result>();
				break;
			case XmlPullParser.START_TAG:
				if (parser.getName().equals("BizArchives")) {
					result = new Bdlr_Dlrbd_List_Result();
					result.setId(parser.getAttributeValue(0));
					result.setApplyname(parser.getAttributeValue(1));
					result.setFwsx_name(parser.getAttributeValue(2));
					result.setFwsx_id(parser.getAttributeValue(3));
					result.setShenqing_date(parser.getAttributeValue(5));
					result.setApplyid(parser.getAttributeValue(6));
					result.setYwlsh(parser.getAttributeValue(12));
					oList.add(result);
				}
				break;
			case XmlPullParser.END_TAG:
				break;
			}
			eventType = parser.next();
		}
		return oList;
	}

	/**
	 * 解析已录入表单数据
	 * 
	 * @param xml
	 * @return
	 * @throws XmlPullParserException
	 * @throws IOException
	 */
	public static List<Bdlr_Ylrbd_List_Result> getBdlr_Ylrbd_List_Info(
			String xml) throws XmlPullParserException, IOException {
		InputStream is = new ByteArrayInputStream(xml.getBytes());
		List<Bdlr_Ylrbd_List_Result> oList = null;
		Bdlr_Ylrbd_List_Result result = null;

		XmlPullParser parser = Xml.newPullParser();
		parser.setInput(is, "UTF-8");
		int eventType = parser.getEventType();
		while (eventType != XmlPullParser.END_DOCUMENT) {
			switch (eventType) {
			case XmlPullParser.START_DOCUMENT:
				oList = new ArrayList<Bdlr_Ylrbd_List_Result>();
				break;
			case XmlPullParser.START_TAG:
				if (parser.getName().equals("BizArchives")) {
					result = new Bdlr_Ylrbd_List_Result();
					result.setId(parser.getAttributeValue(0));
					result.setApplyname(parser.getAttributeValue(1));
					result.setFwsx_name(parser.getAttributeValue(2));
					result.setFwsx_id(parser.getAttributeValue(3));
					result.setShenqing_date(parser.getAttributeValue(5));
					result.setBlzt(parser.getAttributeValue(8));
					result.setBlzt_real(parser.getAttributeValue(10));
					result.setYwlsh(parser.getAttributeValue(12));
					oList.add(result);
				}
				break;
			case XmlPullParser.END_TAG:
				break;
			}
			eventType = parser.next();
		}
		return oList;
	}

	/**
	 * 解析待审批信息
	 * 
	 * @param xml
	 * @return
	 * @throws XmlPullParserException
	 * @throws IOException
	 */
	public static List<Spcl_Dsp_List_Result> getSpcl_Dsp_List_Info(String xml)
			throws XmlPullParserException, IOException {
		InputStream is = new ByteArrayInputStream(xml.getBytes());
		Spcl_Dsp_List_Result result = null;
		List<Spcl_Dsp_List_Result> oList = null;

		XmlPullParser parser = Xml.newPullParser();
		parser.setInput(is, "UTF-8");
		int eventType = parser.getEventType();
		while (eventType != XmlPullParser.END_DOCUMENT) {
			switch (eventType) {
			case XmlPullParser.START_DOCUMENT:
				oList = new ArrayList<Spcl_Dsp_List_Result>();
				break;
			case XmlPullParser.START_TAG:
				if (parser.getName().equals("BizArchives")) {
					result = new Spcl_Dsp_List_Result();
					result.setId(parser.getAttributeValue(0));
					result.setApplyname(parser.getAttributeValue(1));
					result.setFwsx_name(parser.getAttributeValue(2));
					result.setFwsx_id(parser.getAttributeValue(3));
					result.setShenqing_date(parser.getAttributeValue(5));
					result.setYwlsh(parser.getAttributeValue(12));
					oList.add(result);
				}
				break;
			case XmlPullParser.END_TAG:
				break;
			}
			eventType = parser.next();
		}
		return oList;
	}

	/**
	 * 解析已审批信息
	 * 
	 * @param xml
	 * @return
	 * @throws XmlPullParserException
	 * @throws IOException
	 */
	public static List<Spcl_Ysp_List_Result> getSpcl_Ysp_List_Info(String xml)
			throws XmlPullParserException, IOException {
		InputStream is = new ByteArrayInputStream(xml.getBytes());
		List<Spcl_Ysp_List_Result> oList = null;
		Spcl_Ysp_List_Result result = null;

		XmlPullParser parser = Xml.newPullParser();
		parser.setInput(is, "UTF-8");
		int eventType = parser.getEventType();
		while (eventType != XmlPullParser.END_DOCUMENT) {
			switch (eventType) {
			case XmlPullParser.START_DOCUMENT:
				oList = new ArrayList<Spcl_Ysp_List_Result>();
				break;
			case XmlPullParser.START_TAG:
				if (parser.getName().equals("BizArchives")) {
					result = new Spcl_Ysp_List_Result();
					result.setId(parser.getAttributeValue(0));
					result.setApplyname(parser.getAttributeValue(1));
					result.setFwsx_name(parser.getAttributeValue(2));
					result.setFwsx_id(parser.getAttributeValue(3));
					result.setShenqing_date(parser.getAttributeValue(5));
					result.setBlzt(parser.getAttributeValue(8));
					result.setBlzt_real(parser.getAttributeValue(10));
					result.setYwlsh(parser.getAttributeValue(12));
					oList.add(result);
				}
				break;
			case XmlPullParser.END_TAG:
				break;
			}
			eventType = parser.next();
		}

		return oList;
	}

	/**
	 * 解析已退回信息
	 * 
	 * @param xml
	 * @return
	 * @throws XmlPullParserException
	 * @throws IOException
	 */
	public static List<Spcl_Yth_List_Result> getSpcl_Yth_List_Info(String xml)
			throws XmlPullParserException, IOException {
		InputStream is = new ByteArrayInputStream(xml.getBytes());
		List<Spcl_Yth_List_Result> oList = null;
		Spcl_Yth_List_Result result = null;

		XmlPullParser parser = Xml.newPullParser();
		parser.setInput(is, "UTF-8");
		int eventType = parser.getEventType();
		while (eventType != XmlPullParser.END_DOCUMENT) {
			switch (eventType) {
			case XmlPullParser.START_DOCUMENT:
				oList = new ArrayList<Spcl_Yth_List_Result>();
				break;
			case XmlPullParser.START_TAG:
				if (parser.getName().equals("BizArchives")) {
					result = new Spcl_Yth_List_Result();
					result.setId(parser.getAttributeValue(0));
					result.setApplyname(parser.getAttributeValue(1));
					result.setYwlsh(parser.getAttributeValue(12));
					result.setDazt(parser.getAttributeValue(8));
					result.setBlzt_real(parser.getAttributeValue(10));
					result.setFwsx_name(parser.getAttributeValue(2));
					result.setFwsx_id(parser.getAttributeValue(3));
					result.setShenqing_date(parser.getAttributeValue(5));
					result.setShouliren(parser.getAttributeValue(9));
					result.setYewuhuanjie(parser.getAttributeValue(4));
					oList.add(result);
				}
				break;

			}
			eventType = parser.next();
		}

		return oList;
	}

	/**
	 * 解析待发放列表信息
	 * 
	 * @param xml
	 * @return
	 * @throws XmlPullParserException
	 * @throws IOException
	 */
	public static List<Jgff_Dfflb_List_Result> getJgff_Dfflb_List_Info(
			String xml) throws XmlPullParserException, IOException {
		InputStream is = new ByteArrayInputStream(xml.getBytes());
		List<Jgff_Dfflb_List_Result> oList = null;
		Jgff_Dfflb_List_Result result = null;

		XmlPullParser parser = Xml.newPullParser();
		parser.setInput(is, "UTF-8");
		int eventType = parser.getEventType();

		while (eventType != XmlPullParser.END_DOCUMENT) {
			switch (eventType) {
			case XmlPullParser.START_DOCUMENT:
				oList = new ArrayList<Jgff_Dfflb_List_Result>();
				break;
			case XmlPullParser.START_TAG:
				if (parser.getName().equals("BizArchives")) {
					result = new Jgff_Dfflb_List_Result();
					result.setId(parser.getAttributeValue(0));
					result.setApplyname(parser.getAttributeValue(1));
					result.setFwsx_name(parser.getAttributeValue(2));
					result.setFwsx_id(parser.getAttributeValue(3));
					result.setShenqing_date(parser.getAttributeValue(5));
					result.setApply_id(parser.getAttributeValue(6));
					result.setYwlsh(parser.getAttributeValue(12));
					result.setBlzt(parser.getAttributeValue(8));
					result.setBlzt_real(parser.getAttributeValue(10));
					result.setYewuliushuihao(parser.getAttributeValue(12));
					result.setBanjie_date(parser.getAttributeValue(15));
					oList.add(result);
				}
				break;
			}
			eventType = parser.next();
		}
		return oList;
	}

	/**
	 * 解析已发放列表信息
	 * 
	 * @param xml
	 * @return
	 * @throws XmlPullParserException
	 * @throws IOException
	 */
	public static List<Jgff_Yfflb_List_Result> getJgff_Yfflb_List_Info(
			String xml) throws XmlPullParserException, IOException {
		InputStream is = new ByteArrayInputStream(xml.getBytes());
		List<Jgff_Yfflb_List_Result> oList = null;
		Jgff_Yfflb_List_Result result = null;

		XmlPullParser parser = Xml.newPullParser();
		parser.setInput(is, "UTF-8");
		int eventType = parser.getEventType();
		while (eventType != XmlPullParser.END_DOCUMENT) {
			switch (eventType) {
			case XmlPullParser.START_DOCUMENT:
				oList = new ArrayList<Jgff_Yfflb_List_Result>();
				break;
			case XmlPullParser.START_TAG:
				if (parser.getName().equals("BizArchives")) {
					result = new Jgff_Yfflb_List_Result();
					result.setId(parser.getAttributeValue(0));
					result.setApplyname(parser.getAttributeValue(1));
					result.setBlzt(parser.getAttributeValue(8));
					result.setFwsx_name(parser.getAttributeValue(2));
					result.setYwlsh(parser.getAttributeValue(12));
					result.setFwsx_id(parser.getAttributeValue(3));
					result.setBlzt_real(parser.getAttributeValue(10));
					result.setFafang_date(parser.getAttributeValue(13));
					result.setBanjianliushuihao(parser.getAttributeValue(12));
					oList.add(result);
				}
				break;
			}
			eventType = parser.next();
		}
		return oList;
	}

	/**
	 * 解析到期未办结信息
	 * 
	 * @param xml
	 * @return
	 * @throws XmlPullParserException
	 * @throws IOException
	 */
	public static List<Jgff_Dqwbjlb_List_Result> getJgff_Dqwbjlb_List_Info(
			String xml) throws XmlPullParserException, IOException {
		InputStream is = new ByteArrayInputStream(xml.getBytes());
		List<Jgff_Dqwbjlb_List_Result> oList = null;
		Jgff_Dqwbjlb_List_Result result = null;

		XmlPullParser parser = Xml.newPullParser();
		parser.setInput(is, "UTF-8");
		int evevtType = parser.getEventType();
		while (evevtType != XmlPullParser.END_DOCUMENT) {
			switch (evevtType) {
			case XmlPullParser.START_DOCUMENT:
				oList = new ArrayList<Jgff_Dqwbjlb_List_Result>();
				break;
			case XmlPullParser.START_TAG:
				if (parser.getName().equals("BizArchives")) {
					result = new Jgff_Dqwbjlb_List_Result();
					result.setId(parser.getAttributeValue(0));
					result.setApplyname(parser.getAttributeValue(1));
					result.setFwsx_name(parser.getAttributeValue(2));
					result.setFwsx_id(parser.getAttributeValue(3));
					result.setShenqing_date(parser.getAttributeValue(5));
					result.setBlzt(parser.getAttributeValue(8));
					result.setBlzt_real(parser.getAttributeValue(10));
					result.setYwlsh(parser.getAttributeValue(12));
					result.setBanjie_date(parser.getAttributeValue(15));
					result.setChaoshi_date(parser.getAttributeValue(16));
					result.setSldjdd(parser.getAttributeValue(14));
					oList.add(result);
				}
				break;
			case XmlPullParser.END_TAG:
				break;
			}
			evevtType = parser.next();
		}
		return oList;
	}

	/**
	 * 解析业务档案查询信息
	 * 
	 * @param xml
	 * @return
	 * @throws XmlPullParserException
	 * @throws IOException
	 */
	public static List<Ywda_Ywdacx_List_Result> getYwda_Ywdacx_List_Info(
			String xml) throws XmlPullParserException, IOException {
		InputStream is = new ByteArrayInputStream(xml.getBytes());
		List<Ywda_Ywdacx_List_Result> oList = null;
		Ywda_Ywdacx_List_Result result = null;

		XmlPullParser parser = Xml.newPullParser();
		parser.setInput(is, "UTF-8");
		int eventType = parser.getEventType();
		while (eventType != XmlPullParser.END_DOCUMENT) {
			switch (eventType) {
			case XmlPullParser.START_DOCUMENT:
				oList = new ArrayList<Ywda_Ywdacx_List_Result>();
				break;
			case XmlPullParser.START_TAG:
				if (parser.getName().equals("BizArchives")) {
					result = new Ywda_Ywdacx_List_Result();
					result.setId(parser.getAttributeValue(0));
					result.setApplyname(parser.getAttributeValue(1));
					result.setFwsx_name(parser.getAttributeValue(2));
					result.setFwsx_id(parser.getAttributeValue(3));
					result.setShenqing_date(parser.getAttributeValue(5));
					result.setDazt(parser.getAttributeValue(8));
					result.setShouliren(parser.getAttributeValue(9));
					result.setBlzt_real(parser.getAttributeValue(10));
					result.setYwlsh(parser.getAttributeValue(12));
					result.setYewuhuanjie(parser.getAttributeValue(4));
					oList.add(result);
				}
				break;
			}
			eventType = parser.next();
		}
		return oList;
	}

	/**
	 * 
	 * @param xml
	 *            需要解析的xml数据
	 * @return list 解析得到的map类型列表
	 * @throws XmlPullParserException
	 * @throws IOException
	 */

	public static List<String> LeftMenuXmlParse(String xml)
			throws XmlPullParserException, IOException {
		InputStream is = new ByteArrayInputStream(xml.getBytes());
		List<String> list = null;
		String str = null;
		XmlPullParser parser = Xml.newPullParser(); // 由android.util.Xml创建一个XmlPullParser实例
		parser.setInput(is, "UTF-8"); // 设置输入流 并指明编码方式
		int eventType = parser.getEventType();
		while (eventType != XmlPullParser.END_DOCUMENT) {
			switch (eventType) {
			case XmlPullParser.START_DOCUMENT:
				list = new ArrayList<String>();
				break;
			case XmlPullParser.START_TAG:
				if (parser.getName().equals("FirstMenu")) {
					str = new String();
				} else if (parser.getName().equals("SecondMenu")) {
					str = parser.getAttributeValue(0);
					list.add(str);
				}
				break;
			case XmlPullParser.END_TAG:
				break;
			}
			eventType = parser.next();
		}
		return list;
	}

	public static List<Map<String, Object>> Sjdj_First_ListXmlParse(String xml)
			throws XmlPullParserException, IOException {
		InputStream is = new ByteArrayInputStream(xml.getBytes());
		List<Map<String, Object>> list = null;
		Map<String, Object> map = null;
		// String str = null;
		XmlPullParser parser = Xml.newPullParser(); // 由android.util.Xml创建一个XmlPullParser实例
		parser.setInput(is, "UTF-8"); // 设置输入流 并指明编码方式
		int eventType = parser.getEventType();
		while (eventType != XmlPullParser.END_DOCUMENT) {
			switch (eventType) {
			case XmlPullParser.START_DOCUMENT:
				list = new ArrayList<Map<String, Object>>();
				break;
			case XmlPullParser.START_TAG:
				if (parser.getName().equals("Department")) {
					map = new HashMap<String, Object>();
					map.put("name", parser.getAttributeValue(0));
					map.put("id", parser.getAttributeValue(1));
					list.add(map);
				}
				break;
			case XmlPullParser.END_TAG:
				break;
			}
			eventType = parser.next();
		}
		return list;
	}

	public static List<Map<String, Object>> Sjdj_Second_ListXmlParse(String xml)
			throws XmlPullParserException, IOException {
		InputStream is = new ByteArrayInputStream(xml.getBytes());
		List<Map<String, Object>> list = null;
		Map<String, Object> map = null;
		// String str = null;
		XmlPullParser parser = Xml.newPullParser(); // 由android.util.Xml创建一个XmlPullParser实例
		parser.setInput(is, "UTF-8"); // 设置输入流 并指明编码方式
		int eventType = parser.getEventType();
		while (eventType != XmlPullParser.END_DOCUMENT) {
			switch (eventType) {
			case XmlPullParser.START_DOCUMENT:
				list = new ArrayList<Map<String, Object>>();
				break;
			case XmlPullParser.START_TAG:
				if (parser.getName().equals("ServiceSubject")) {
					map = new HashMap<String, Object>();
					map.put("name", parser.getAttributeValue(0));
					map.put("id", parser.getAttributeValue(1));
					map.put("serviceCode", parser.getAttributeValue(2));
					list.add(map);
				}
				break;
			case XmlPullParser.END_TAG:
				break;
			}
			eventType = parser.next();
		}
		return list;
	}

	public static List<Map<String, Object>> CountsOfArchivesXmlParse(String xml)
			throws XmlPullParserException, IOException {
		InputStream is = new ByteArrayInputStream(xml.getBytes());
		List<Map<String, Object>> list = null;
		Map<String, Object> map = null;
		// String str = null;
		XmlPullParser parser = Xml.newPullParser(); // 由android.util.Xml创建一个XmlPullParser实例
		parser.setInput(is, "UTF-8"); // 设置输入流 并指明编码方式
		int eventType = parser.getEventType();
		while (eventType != XmlPullParser.END_DOCUMENT) {
			switch (eventType) {
			case XmlPullParser.START_DOCUMENT:
				list = new ArrayList<Map<String, Object>>();
				break;
			case XmlPullParser.START_TAG:
				if (parser.getName().equals("ServiceSubjectCount")) {
					map = new HashMap<String, Object>();
					map.put("name", parser.getAttributeValue(0));
					map.put("count", parser.getAttributeValue(1));
					list.add(map);
				}
				break;
			case XmlPullParser.END_TAG:
				break;
			}
			eventType = parser.next();
		}
		return list;
	}

	public static List<Map<String, Object>> getAcceptQtslXmlParse(String xml)
			throws XmlPullParserException, IOException {
		InputStream is = new ByteArrayInputStream(xml.getBytes());
		List<Map<String, Object>> list = null;
		Map<String, Object> map = null;
		// String str = null;
		XmlPullParser parser = Xml.newPullParser(); // 由android.util.Xml创建一个XmlPullParser实例
		parser.setInput(is, "UTF-8"); // 设置输入流 并指明编码方式
		int eventType = parser.getEventType();
		while (eventType != XmlPullParser.END_DOCUMENT) {
			switch (eventType) {
			case XmlPullParser.START_DOCUMENT:
				list = new ArrayList<Map<String, Object>>();
				map = new HashMap<String, Object>();
				break;
			case XmlPullParser.START_TAG:
				if (parser.getName().equals("ServiceSubjectSl")) {
					map.put("booleanAcceptQtsl", parser.getAttributeValue(0));
				} else if (parser.getName().equals("UserAccount")) {

					map.put("account", parser.getAttributeValue(0));
					map.put("accountId", parser.getAttributeValue(1));
					map.put("name", parser.getAttributeValue(2));
					map.put("org", parser.getAttributeValue(3));
					map.put("orgId", parser.getAttributeValue(4));
					map.put("divisionCode", parser.getAttributeValue(5));
					map.put("nextBizNode", parser.getAttributeValue(6));
				}
				break;
			case XmlPullParser.END_TAG:
				if (parser.getName().equals("ZsoftInfo")) {
					list.add(map);
				}
				break;
			}

			eventType = parser.next();
		}
		return list;
	}

	public static List<Map<String, Object>> getAddressCsddXmlParse(String xml)
			throws XmlPullParserException, IOException {
		InputStream is = new ByteArrayInputStream(xml.getBytes());
		List<Map<String, Object>> list_chushen_address = null;
		List<Map<String, Object>> list_chushen_address_sure = null;
		List<Map<String, Object>> list_second = null;
		Map<String, Object> map_first = null;
		Map<String, Object> map_second = null;
		// String str = null;
		XmlPullParser parser = Xml.newPullParser(); // 由android.util.Xml创建一个XmlPullParser实例
		parser.setInput(is, "UTF-8"); // 设置输入流 并指明编码方式
		int eventType = parser.getEventType();
		while (eventType != XmlPullParser.END_DOCUMENT) {
			switch (eventType) {
			case XmlPullParser.START_DOCUMENT:
				list_chushen_address = new ArrayList<Map<String, Object>>();
				break;
			case XmlPullParser.START_TAG:
				if (parser.getName().equals("FirstMenu")) {
					list_second = new ArrayList<Map<String, Object>>();
					map_first = new HashMap<String, Object>();
					map_first.put("firstname", parser.getAttributeValue(0));
					map_first.put("code", parser.getAttributeValue(1));
					map_first.put("ifdefaultfirstvalue",
							parser.getAttributeValue(2));
				} else if (parser.getName().equals("SecondMenu")) {
					map_second = new HashMap<String, Object>();
					map_second.put("ifdefaultsecondvalue",
							parser.getAttributeValue(2));
					map_second.put("code", parser.getAttributeValue(1));
					map_second.put("secondname", parser.getAttributeValue(0));
				}
				break;
			case XmlPullParser.END_TAG:
				if (parser.getName().equals("FirstMenu")) {
					map_first.put("my_second_list", list_second);
					list_chushen_address.add(map_first);
				} else if (parser.getName().equals("SecondMenu")) {
					list_second.add(map_second);
				}
				break;
			}

			eventType = parser.next();
		}
		list_chushen_address_sure = new ArrayList<Map<String, Object>>();
		for (Map<String, Object> map : list_chushen_address) {
			if (((List<Map<String, Object>>) map.get("my_second_list")).size() != 0) {
				list_chushen_address_sure.add(map);
			}
		}
		return list_chushen_address_sure;
	}

	public static List<Map<String, Object>> getAddressFfddXmlParse(String xml)
			throws XmlPullParserException, IOException {
		InputStream is = new ByteArrayInputStream(xml.getBytes());
		List<Map<String, Object>> list_fafang_address = null;
		List<Map<String, Object>> list_fafang_address_sure = null;
		List<Map<String, Object>> list_second = null;
		Map<String, Object> map_first = null;
		Map<String, Object> map_second = null;
		// String str = null;
		XmlPullParser parser = Xml.newPullParser(); // 由android.util.Xml创建一个XmlPullParser实例
		parser.setInput(is, "UTF-8"); // 设置输入流 并指明编码方式
		int eventType = parser.getEventType();
		while (eventType != XmlPullParser.END_DOCUMENT) {
			switch (eventType) {
			case XmlPullParser.START_DOCUMENT:
				list_fafang_address = new ArrayList<Map<String, Object>>();
				break;
			case XmlPullParser.START_TAG:
				if (parser.getName().equals("FirstMenu")) {
					list_second = new ArrayList<Map<String, Object>>();
					map_first = new HashMap<String, Object>();
					map_first.put("firstname", parser.getAttributeValue(0));
					map_first.put("code", parser.getAttributeValue(1));
					map_first.put("ifdefaultfirstvalue",
							parser.getAttributeValue(2));
				} else if (parser.getName().equals("SecondMenu")) {
					map_second = new HashMap<String, Object>();
					map_second.put("ifdefaultsecondvalue",
							parser.getAttributeValue(2));
					map_second.put("code", parser.getAttributeValue(1));
					map_second.put("secondname", parser.getAttributeValue(0));
				}
				break;
			case XmlPullParser.END_TAG:
				if (parser.getName().equals("FirstMenu")) {
					map_first.put("my_second_list", list_second);
					list_fafang_address.add(map_first);
				} else if (parser.getName().equals("SecondMenu")) {
					list_second.add(map_second);
				}
				break;
			}

			eventType = parser.next();
		}
		list_fafang_address_sure = new ArrayList<Map<String, Object>>();
		for (Map<String, Object> map : list_fafang_address) {
			if (((List<Map<String, Object>>) map.get("my_second_list")).size() != 0) {
				list_fafang_address_sure.add(map);
			}
		}
		return list_fafang_address_sure;
	}

	public static List<Map<String, Object>> getSjdj_PritureXmlParse(String xml)
			throws XmlPullParserException, IOException {
		InputStream is = new ByteArrayInputStream(xml.getBytes());
		List<Map<String, Object>> priture_upload_info = null;

		XmlPullParser parser = Xml.newPullParser(); // 由android.util.Xml创建一个XmlPullParser实例
		parser.setInput(is, "UTF-8"); // 设置输入流 并指明编码方式

		return priture_upload_info;
	}

	public static Sjdj_shouli_detail_bean getSjdj_detailXmlParse(String xml)
			throws XmlPullParserException, IOException {
		InputStream is = new ByteArrayInputStream(xml.getBytes());

		Sjdj_shouli_detail_bean sjdj = new Sjdj_shouli_detail_bean(); // 需要返回的收件登记关于办理条件和提交材料的实体类
		List<Map<String, Object>> list_bltj = null; // 用来存放办理条件的list
		Map<String, Object> list_bltj_map = null; // 办理条件的map

		List<Map<String, Object>> list_bltj_no_checkbox = null; // 用来存放办理条件的list
		List<blcl_checkbox> list_tjcl = null; // 存放待提交材料的实体类列表
		blcl_checkbox blcl = null;
		List<Map<String, Object>> blcl_second_name = null;
		Map<String, Object> second_name_map = null;

		List<Map<String, Object>> list_blcl_three = null; // 存放办理材料的列表
		Map<String, Object> list_blcl_map = null; // 提交材料的map

		XmlPullParser parser = Xml.newPullParser(); // 由android.util.Xml创建一个XmlPullParser实例
		parser.setInput(is, "UTF-8"); // 设置输入流 并指明编码方式
		int eventType = parser.getEventType();
		while (eventType != XmlPullParser.END_DOCUMENT) {
			switch (eventType) {
			case XmlPullParser.START_DOCUMENT:
				list_bltj = new ArrayList<Map<String, Object>>();
				list_tjcl = new ArrayList<blcl_checkbox>();
				list_bltj_no_checkbox = new ArrayList<Map<String, Object>>();
				list_blcl_three = new ArrayList<Map<String, Object>>();
				break;
			case XmlPullParser.START_TAG:
				if (parser.getName().equals("ServiceSubjectBltjInfo")) {

					list_bltj_map = new HashMap<String, Object>();
					list_bltj_map.put("name", parser.getAttributeValue(0));
					list_bltj_map.put("id", parser.getAttributeValue(1));
					list_bltj.add(list_bltj_map);
				} else if (parser.getName().equals("ServiceSubjectBlclFirst")) {
					blcl = new blcl_checkbox();
					sjdj.setIfHaveCheck(true);
					blcl_second_name = new ArrayList<Map<String, Object>>();
					blcl.setBlcl_first_name(parser.getAttributeValue(0));
					blcl.setBlcl_first_id(parser.getAttributeValue(1));
				} else if (parser.getName().equals("ServiceSubjectBlclSecond")) {
					second_name_map = new HashMap<String, Object>();
					second_name_map.put("second_name",
							parser.getAttributeValue(0));
					blcl_second_name.add(second_name_map);
				} else if (parser.getName().equals("ServiceSubjectBlclThree")) {
					sjdj.setIfHaveCheck(false);
					list_blcl_map = new HashMap<String, Object>();
					list_blcl_map.put("name", parser.getAttributeValue(0));
					list_blcl_map.put("id", parser.getAttributeValue(1));
					list_blcl_map.put("yscl", parser.getAttributeValue(2));
					list_blcl_map.put("identityType",
							parser.getAttributeValue(3));
					// if(blcl == null){
					// sjdj.setIfHaveCheck(false);
					list_bltj_no_checkbox.add(list_blcl_map);
					// }else{
					// sjdj.setIfHaveCheck(true);
					// list_blcl_three.add(list_blcl_map);
					// }

				}
				break;
			case XmlPullParser.END_TAG:
				if (parser.getName().equals("ServiceSubjectBltj")) {
					sjdj.setList_bltj(list_bltj);
				} else if (parser.getName().equals("ServiceSubjectBlclFirst")) {
					blcl.setBlcl_second_name(blcl_second_name);
					list_tjcl.add(blcl);

				} else if (parser.getName().equals("ServiceSubjectBlcl")) {
					if (sjdj.isIfHaveCheck()) {
						sjdj.setList_tjcl(list_tjcl);
					} else {
						sjdj.setList_tjcl_no_checkbox(list_bltj_no_checkbox);
					}

				}
				break;
			}

			eventType = parser.next();
		}
		return sjdj;
	}

	public static List<Map<String, Object>> getSjdj_detail_tjcl_XmlParse(
			String xml) throws XmlPullParserException, IOException {
		InputStream is = new ByteArrayInputStream(xml.getBytes());

		List<Map<String, Object>> list_blcl_three = null; // 存放办理材料的列表
		Map<String, Object> list_blcl_map = null; // 提交材料的map

		XmlPullParser parser = Xml.newPullParser(); // 由android.util.Xml创建一个XmlPullParser实例
		parser.setInput(is, "UTF-8"); // 设置输入流 并指明编码方式
		int eventType = parser.getEventType();
		while (eventType != XmlPullParser.END_DOCUMENT) {
			switch (eventType) {
			case XmlPullParser.START_DOCUMENT:
				list_blcl_three = new ArrayList<Map<String, Object>>();
				break;
			case XmlPullParser.START_TAG:
				if (parser.getName().equals("ServiceSubjectBlclThree")) {
					list_blcl_map = new HashMap<String, Object>();
					list_blcl_map.put("name", parser.getAttributeValue(0));
					list_blcl_map.put("id", parser.getAttributeValue(1));
					list_blcl_map.put("yscl", parser.getAttributeValue(2));
					list_blcl_map.put("identityType",
							parser.getAttributeValue(3));
					list_blcl_three.add(list_blcl_map);
				}
				break;
			case XmlPullParser.END_TAG:

				break;
			}
			eventType = parser.next();
		}
		return list_blcl_three;
	}

	public static List<Map<String, Object>> getServiceSubjecrFormXmlParse(
			String xml) throws XmlPullParserException, IOException {
		InputStream is = new ByteArrayInputStream(xml.getBytes());
		List<Map<String, Object>> list = null;
		Map<String, Object> map = null;
		// String str = null;
		XmlPullParser parser = Xml.newPullParser(); // 由android.util.Xml创建一个XmlPullParser实例
		parser.setInput(is, "UTF-8"); // 设置输入流 并指明编码方式
		int eventType = parser.getEventType();
		while (eventType != XmlPullParser.END_DOCUMENT) {
			switch (eventType) {
			case XmlPullParser.START_DOCUMENT:
				list = new ArrayList<Map<String, Object>>();
				break;
			case XmlPullParser.START_TAG:
				if (parser.getName().equals("ServiceSubjectColumn")) {
					map = new HashMap<String, Object>();
					map.put("columnName", parser.getAttributeValue(0));
					map.put("name", parser.getAttributeValue(1));
					map.put("value", parser.getAttributeValue(2));
					map.put("dataType", parser.getAttributeValue(3));
					map.put("length", parser.getAttributeValue(4));
					map.put("nonEmpty", parser.getAttributeValue(5));
					map.put("controlType", parser.getAttributeValue(6));
					map.put("index", parser.getAttributeValue(7));
					map.put("groupName", parser.getAttributeValue(8));
					map.put("groupIndex", parser.getAttributeValue(9));
					map.put("isShowGroupTitle", parser.getAttributeValue(10));
					map.put("inputControlValueScope",
							parser.getAttributeValue(11));
					list.add(map);
				}
				break;
			case XmlPullParser.END_TAG:
				break;
			}
			eventType = parser.next();
		}
		return list;
	}

	/**
	 * 解析事项业务详情TAB跟按钮显示
	 * 
	 * @param xml
	 * @return
	 * @throws XmlPullParserException
	 * @throws IOException
	 */
	public static List<Map<String, Object>> getSubject_Detail_TAB(String xml)
			throws XmlPullParserException, IOException {
		InputStream is = new ByteArrayInputStream(xml.getBytes());
		List<Map<String, Object>> oList = null;
		Map<String, Object> result = null;

		XmlPullParser parser = Xml.newPullParser();
		parser.setInput(is, "UTF-8");
		int eventType = parser.getEventType();
		while (eventType != XmlPullParser.END_DOCUMENT) {
			switch (eventType) {
			case XmlPullParser.START_DOCUMENT:
				oList = new ArrayList<Map<String, Object>>();
				break;
			case XmlPullParser.START_TAG:
				if (parser.getName().equals("BizArchivesInfo")) {
					result = new HashMap<String, Object>();
					result.put("name", parser.getAttributeValue(0));
					result.put("type", parser.getAttributeValue(1));
					result.put("isShow", parser.getAttributeValue(2));
					oList.add(result);
				}
				break;
			}
			eventType = parser.next();
		}
		return oList;
	}

	/**
	 * 解析事项业务详情基本信息
	 * 
	 * @param xml
	 * @return
	 * @throws XmlPullParserException
	 * @throws IOException
	 */
	public static List<Map<String, Object>> getSubject_Detail_JBXX(String xml)
			throws XmlPullParserException, IOException {
		InputStream is = new ByteArrayInputStream(xml.getBytes());
		List<Map<String, Object>> oList = null;
		List<Map<String, Object>> list = null;
		Map<String, Object> result = null;
		Map<String, Object> oresult = null;

		XmlPullParser parser = Xml.newPullParser();
		parser.setInput(is, "UTF-8");
		int eventType = parser.getEventType();
		while (eventType != XmlPullParser.END_DOCUMENT) {
			switch (eventType) {
			case XmlPullParser.START_DOCUMENT:
				oList = new ArrayList<Map<String, Object>>();
				break;
			case XmlPullParser.START_TAG:
				if (parser.getName().equals("FlowSummary")) {
					result = new HashMap<String, Object>();
					result.put("title", parser.getAttributeValue(0));
					result.put("actived", parser.getAttributeValue(1));
					list = new ArrayList<Map<String, Object>>();
				} else if (parser.getName().equals("Item")) {
					oresult = new HashMap<String, Object>();
					oresult.put("name", parser.getAttributeValue(0));
					String value = "";
					if (parser.getAttributeValue(1).contains(".0")) {
						value = parser.getAttributeValue(1).replace(".0", "");
						oresult.put("value", value);
					} else {
						oresult.put("value", parser.getAttributeValue(1));
					}
					list.add(oresult);
				}
				break;
			case XmlPullParser.END_TAG:
				if (parser.getName().equals("FlowSummary")) {
					if (result != null && list != null) {
						result.put("item_list", list);
					}
					oList.add(result);
				}
				break;
			}
			eventType = parser.next();
		}
		return oList;
	}

	/**
	 * 解析预受理基本信息
	 * 
	 * @param xml
	 * @return
	 * @throws XmlPullParserException
	 * @throws IOException
	 */
	public static Detail_Sjdj_bean getSubject_Detail_JBXX_YSL(String xml)
			throws XmlPullParserException, IOException {
		InputStream is = new ByteArrayInputStream(xml.getBytes());
		Detail_Sjdj_bean detail_Sjdj_bean = new Detail_Sjdj_bean();
		List<String> list_bltj = new ArrayList<String>();
		List<Map<String, Object>> List_blfztj = null;
		List<String> List_blfztj_2 = new ArrayList<String>();
		Map<String, Object> map_blfztj = null;

		List<Map<String, Object>> List_blcl = null;
		Map<String, Object> map_blcl = null;

		XmlPullParser parser = Xml.newPullParser();
		parser.setInput(is, "UTF-8");
		int eventType = parser.getEventType();
		while (eventType != XmlPullParser.END_DOCUMENT) {
			switch (eventType) {
			case XmlPullParser.START_DOCUMENT:
				// oList=new ArrayList<Map<String,Object>>();
				break;
			case XmlPullParser.START_TAG:
				if (parser.getName().equals("BizReceiptInfo")) {
					detail_Sjdj_bean.setSerialNumber_ysl(parser
							.getAttributeValue(0));
					detail_Sjdj_bean.setServiceTargetName(parser
							.getAttributeValue(1));
					detail_Sjdj_bean.setServiceTargetPhone(parser
							.getAttributeValue(2));
					detail_Sjdj_bean.setServiceTargetEmail(parser
							.getAttributeValue(3));
					String value = "";
					if (parser.getAttributeValue(4).contains(".0")) {
						value = parser.getAttributeValue(4).replace(".0", "");
						detail_Sjdj_bean.setApplyDate_ysl(value);
					} else {
						detail_Sjdj_bean.setApplyDate_ysl(parser
								.getAttributeValue(4));
					}

				} else if (parser.getName().equals("ServiceSubjectBltjInfo")) {
					list_bltj.add(parser.getAttributeValue(0));
				} else if (parser.getName().equals("ServiceSubjectBlfztj")) {
					List_blfztj = new ArrayList<Map<String, Object>>();
				} else if (parser.getName().equals("ServiceSubjectBlfztjFirst")) {
					map_blfztj = new HashMap<String, Object>();
					map_blfztj.put("name", parser.getAttributeValue(0));
					map_blfztj.put("checked", parser.getAttributeValue(2));
					// List_blfztj_2 = new ArrayList<String>();
				} else if (parser.getName()
						.equals("ServiceSubjectBlfztjSecond")) {
					List_blfztj_2.add(parser.getAttributeValue(0));
				} else if (parser.getName().equals("ServiceSubjectBlcl")) {
					List_blcl = new ArrayList<Map<String, Object>>();
				} else if (parser.getName().equals("ServiceSubjectBlclFirst")) {
					map_blcl = new HashMap<String, Object>();
					map_blcl.put("name", parser.getAttributeValue(0));
					map_blcl.put("id", parser.getAttributeValue(1));
					map_blcl.put("yscl", parser.getAttributeValue(2));
					map_blcl.put("identityType", parser.getAttributeValue(3));
					map_blcl.put("imgUrl", parser.getAttributeValue(4));

				}
				break;
			case XmlPullParser.END_TAG:
				if (parser.getName().equals("ServiceSubjectBltj")) {
					detail_Sjdj_bean.setList_bltj(list_bltj);
				}
				if (parser.getName().equals("ServiceSubjectBlfztjFirst")) {
					map_blfztj.put("list_second", List_blfztj_2);
					List_blfztj.add(map_blfztj);
				}
				if (parser.getName().equals("ServiceSubjectBlfztj")) {
					detail_Sjdj_bean.setList_blfztj(List_blfztj);
				}
				if (parser.getName().equals("ServiceSubjectBlclFirst")) {
					List_blcl.add(map_blcl);
				}
				if (parser.getName().equals("ServiceSubjectBlcl")) {
					detail_Sjdj_bean.setList_blcl(List_blcl);
				}
				break;
			}
			eventType = parser.next();
		}
		return detail_Sjdj_bean;
	}

	/**
	 * 解析事项业务详情收件登记和电子材料
	 * 
	 * @param xml
	 * @return
	 * @throws XmlPullParserException
	 * @throws IOException
	 */
	public static Detail_Sjdj_bean getSubject_Detail_SJDJ_DZCL(String xml)
			throws XmlPullParserException, IOException {
		InputStream is = new ByteArrayInputStream(xml.getBytes());
		Detail_Sjdj_bean detail_Sjdj_bean = new Detail_Sjdj_bean();
		List<String> list_bltj = new ArrayList<String>();
		List<Map<String, Object>> List_blfztj = null;
		List<String> List_blfztj_2 = null;
		Map<String, Object> map_blfztj = null;

		List<Map<String, Object>> List_blcl = null;
		Map<String, Object> map_blcl = null;

		XmlPullParser parser = Xml.newPullParser();
		parser.setInput(is, "UTF-8");
		int eventType = parser.getEventType();
		while (eventType != XmlPullParser.END_DOCUMENT) {
			switch (eventType) {
			case XmlPullParser.START_DOCUMENT:
				// oList=new ArrayList<Map<String,Object>>();
				break;
			case XmlPullParser.START_TAG:
				if (parser.getName().equals("BizReceiptInfo")) {
					detail_Sjdj_bean.setSerialNumber(parser
							.getAttributeValue(0));
					detail_Sjdj_bean.setServiceTargetName(parser
							.getAttributeValue(2));
					detail_Sjdj_bean.setApplyDate(parser.getAttributeValue(3));
					detail_Sjdj_bean.setServiceTargetPhone(parser
							.getAttributeValue(1));
					detail_Sjdj_bean.setApplyDate_ysl(parser
							.getAttributeValue(3));
					detail_Sjdj_bean.setServiceOrgName(parser
							.getAttributeValue(4));
					detail_Sjdj_bean.setHandleDate(parser.getAttributeValue(5));
					detail_Sjdj_bean.setOperatorName(parser
							.getAttributeValue(6));
				} else if (parser.getName().equals("ServiceSubjectBltjInfo")) {
					list_bltj.add(parser.getAttributeValue(0));
				} else if (parser.getName().equals("ServiceSubjectBlfztj")) {
					List_blfztj = new ArrayList<Map<String, Object>>();
				} else if (parser.getName().equals("ServiceSubjectBlfztjFirst")) {
					map_blfztj = new HashMap<String, Object>();
					map_blfztj.put("name", parser.getAttributeValue(0));
					map_blfztj.put("checked", parser.getAttributeValue(2));
					List_blfztj_2 = new ArrayList<String>();
				} else if (parser.getName()
						.equals("ServiceSubjectBlfztjSecond")) {
					List_blfztj_2.add(parser.getAttributeValue(0));
				} else if (parser.getName().equals("ServiceSubjectBlcl")) {
					List_blcl = new ArrayList<Map<String, Object>>();
				} else if (parser.getName().equals("ServiceSubjectBlclFirst")) {
					map_blcl = new HashMap<String, Object>();
					map_blcl.put("name", parser.getAttributeValue(0));
					map_blcl.put("id", parser.getAttributeValue(1));
					map_blcl.put("yscl", parser.getAttributeValue(2));
					map_blcl.put("identityType", parser.getAttributeValue(3));
					map_blcl.put("imgUrl", parser.getAttributeValue(4));

				}
				break;
			case XmlPullParser.END_TAG:
				if (parser.getName().equals("ServiceSubjectBltj")) {
					detail_Sjdj_bean.setList_bltj(list_bltj);
				}
				if (parser.getName().equals("ServiceSubjectBlfztjFirst")) {
					map_blfztj.put("list_second", List_blfztj_2);
					List_blfztj.add(map_blfztj);
				}
				if (parser.getName().equals("ServiceSubjectBlfztj")) {
					detail_Sjdj_bean.setList_blfztj(List_blfztj);
				}
				if (parser.getName().equals("ServiceSubjectBlclFirst")) {
					List_blcl.add(map_blcl);
				}
				if (parser.getName().equals("ServiceSubjectBlcl")) {
					detail_Sjdj_bean.setList_blcl(List_blcl);
				}
				break;
			}
			eventType = parser.next();
		}
		return detail_Sjdj_bean;
	}

	/**
	 * 解析事项业务详情暂停信息
	 * 
	 * @param xml
	 * @return
	 * @throws XmlPullParserException
	 * @throws IOException
	 */
	public static List<Map<String, Object>> getSubject_Detail_ZTXX(String xml)
			throws XmlPullParserException, IOException {
		InputStream is = new ByteArrayInputStream(xml.getBytes());
		List<Map<String, Object>> oList = null;
		List<Map<String, Object>> oList_1 = null;
		Map<String, Object> result = null;
		Map<String, Object> result_1 = null;

		XmlPullParser parser = Xml.newPullParser();
		parser.setInput(is, "UTF-8");
		int eventType = parser.getEventType();
		while (eventType != XmlPullParser.END_DOCUMENT) {
			switch (eventType) {
			case XmlPullParser.START_DOCUMENT:
				oList = new ArrayList<Map<String, Object>>();
				break;
			case XmlPullParser.START_TAG:
				if (parser.getName().equals("PauseInfo")) {
					oList_1 = new ArrayList<Map<String, Object>>();
				} else if (parser.getName().equals("Item")) {
					result_1 = new HashMap<String, Object>();
					result_1.put("name", parser.getAttributeValue(0));
					String value = "";
					if (parser.getAttributeValue(1).contains(".0")) {
						value = parser.getAttributeValue(1).replace(".0", "");
						result_1.put("value", value);
					} else {
						result_1.put("value", parser.getAttributeValue(1));
					}
					// list.add(oresult);
					// result_1.put("value", parser.getAttributeValue(1));
					oList_1.add(result_1);
				}
				break;
			case XmlPullParser.END_TAG:
				if (parser.getName().equals("PauseInfo")) {
					result = new HashMap<String, Object>();
					result.put("list_item_list", oList_1);
					oList.add(result);
				}
				break;
			}
			eventType = parser.next();
		}
		return oList;
	}

	/**
	 * 解析事项业务详情补正信息
	 * 
	 * @param xml
	 * @return
	 * @throws XmlPullParserException
	 * @throws IOException
	 */
	public static List<Map<String, Object>> getSubject_Detail_BZXX(String xml)
			throws XmlPullParserException, IOException {
		InputStream is = new ByteArrayInputStream(xml.getBytes());
		List<Map<String, Object>> oList = null;
		List<Map<String, Object>> oList_1 = null;
		Map<String, Object> result = null;
		Map<String, Object> result_1 = null;

		XmlPullParser parser = Xml.newPullParser();
		parser.setInput(is, "UTF-8");
		int eventType = parser.getEventType();
		while (eventType != XmlPullParser.END_DOCUMENT) {
			switch (eventType) {
			case XmlPullParser.START_DOCUMENT:
				oList = new ArrayList<Map<String, Object>>();
				break;
			case XmlPullParser.START_TAG:
				if (parser.getName().equals("CorrectInfo")) {
					oList_1 = new ArrayList<Map<String, Object>>();
				} else if (parser.getName().equals("Item")) {
					result_1 = new HashMap<String, Object>();
					result_1.put("name", parser.getAttributeValue(0));
					String value = "";
					if (parser.getAttributeValue(1).contains(".0")) {
						value = parser.getAttributeValue(1).replace(".0", "");
						result_1.put("value", value);
					} else {
						result_1.put("value", parser.getAttributeValue(1));
					}
					// result_1.put("value", parser.getAttributeValue(1));
					oList_1.add(result_1);
				}
				break;
			case XmlPullParser.END_TAG:
				if (parser.getName().equals("CorrectInfo")) {
					result = new HashMap<String, Object>();
					result.put("list_item_list", oList_1);
					oList.add(result);
				}
				break;
			}
			eventType = parser.next();
		}
		return oList;
	}

	/**
	 * 解析事项业务详情温馨提示
	 * 
	 * @param xml
	 * @return
	 * @throws XmlPullParserException
	 * @throws IOException
	 */
	public static Detail_Wxts_bean getSubject_Detail_WXTS(String xml)
			throws XmlPullParserException, IOException {
		InputStream is = new ByteArrayInputStream(xml.getBytes());
		List<Map<String, Object>> ConditionDataList = null;
		List<Map<String, Object>> MaterialDataList = null;
		Map<String, Object> ConditionData = null;
		Map<String, Object> MaterialData = null;

		Detail_Wxts_bean detail_Wxts_bean = new Detail_Wxts_bean();

		XmlPullParser parser = Xml.newPullParser();
		parser.setInput(is, "UTF-8");
		int eventType = parser.getEventType();
		while (eventType != XmlPullParser.END_DOCUMENT) {
			switch (eventType) {
			case XmlPullParser.START_DOCUMENT:
				ConditionDataList = new ArrayList<Map<String, Object>>();
				MaterialDataList = new ArrayList<Map<String, Object>>();
				break;
			case XmlPullParser.START_TAG:
				if (parser.getName().equals("TipMsg")) {
					detail_Wxts_bean.setServiceTargetName(parser
							.getAttributeValue(0));
					detail_Wxts_bean.setApplyDate(parser.getAttributeValue(1));
					detail_Wxts_bean.setServiceSubjectName(parser
							.getAttributeValue(2));
				} else if (parser.getName().equals("ConditionDataInfo")) {
					ConditionData = new HashMap<String, Object>();
					ConditionData.put("name", parser.getAttributeValue(0));
					ConditionData.put("value", parser.getAttributeValue(1));
					ConditionDataList.add(ConditionData);
				} else if (parser.getName().equals("MaterialDataInfo")) {
					MaterialData = new HashMap<String, Object>();
					MaterialData.put("name", parser.getAttributeValue(0));
					MaterialData.put("value", parser.getAttributeValue(1));
					MaterialDataList.add(MaterialData);
				} else if (parser.getName().equals("AdviceMsg")) {
					detail_Wxts_bean.setServiceOrgPhone(parser
							.getAttributeValue(0));
					detail_Wxts_bean.setServiceOrgName(parser
							.getAttributeValue(1));
					detail_Wxts_bean.setServiceOrgAddress(parser
							.getAttributeValue(2));
				}
				break;
			case XmlPullParser.END_TAG:
				if (parser.getName().equals("ZsoftInfo")) {
					detail_Wxts_bean.setConditionDataList(ConditionDataList);
					detail_Wxts_bean.setMaterialDataList(MaterialDataList);
				}
				break;
			}
			eventType = parser.next();
		}
		return detail_Wxts_bean;
	}

	/**
	 * 解析事项业务详情温馨提示
	 * 
	 * @param xml
	 * @return
	 * @throws XmlPullParserException
	 * @throws IOException
	 */
	public static Detail_Ffjl_bean getSubject_Detail_FFJL(String xml)
			throws XmlPullParserException, IOException {
		InputStream is = new ByteArrayInputStream(xml.getBytes());
		Detail_Ffjl_bean detail_Ffjl_bean = new Detail_Ffjl_bean();
		XmlPullParser parser = Xml.newPullParser();
		parser.setInput(is, "UTF-8");
		int eventType = parser.getEventType();
		while (eventType != XmlPullParser.END_DOCUMENT) {
			switch (eventType) {
			case XmlPullParser.START_DOCUMENT:

				break;
			case XmlPullParser.START_TAG:
				if (parser.getName().equals("TopMsg")) {
					if (parser.getAttributeValue(0).equals("业务流水号")) {
						detail_Ffjl_bean.setBjlsh(parser.getAttributeValue(1));
					} else if (parser.getAttributeValue(0).equals("申请人姓名")) {
						detail_Ffjl_bean.setSqrxm(parser.getAttributeValue(1));
					}
				} else if (parser.getName().equals("Item")) {
					if (parser.getAttributeValue(0).equals("发放人")) {
						detail_Ffjl_bean.setFfr(parser.getAttributeValue(1));
					} else if (parser.getAttributeValue(0).equals("发放时间")) {
						detail_Ffjl_bean.setFfsj(parser.getAttributeValue(1));
					} else if (parser.getAttributeValue(0).equals("发放机构")) {
						detail_Ffjl_bean.setFfjg(parser.getAttributeValue(1));
					} else if (parser.getAttributeValue(0).equals("签收人")) {
						detail_Ffjl_bean.setQsr(parser.getAttributeValue(1));
					} else if (parser.getAttributeValue(0).equals("接收人")) {
						detail_Ffjl_bean.setJsr(parser.getAttributeValue(1));
					} else if (parser.getAttributeValue(0).equals("接收号码")) {
						detail_Ffjl_bean.setJshm(parser.getAttributeValue(1));
					} else if (parser.getAttributeValue(0).equals("发送人")) {
						detail_Ffjl_bean.setFsr(parser.getAttributeValue(1));
					} else if (parser.getAttributeValue(0).equals("发送时间")) {
						detail_Ffjl_bean.setFssj(parser.getAttributeValue(1));
					} else if (parser.getAttributeValue(0).equals("短信内容")) {
						detail_Ffjl_bean.setDxnr(parser.getAttributeValue(1));
					}
				}
				break;
			}
			eventType = parser.next();
		}
		return detail_Ffjl_bean;
	}

	public static List<Map<String, Object>> getSubject_Detail_YWBD(String xml)
			throws XmlPullParserException, IOException {
		InputStream is = new ByteArrayInputStream(xml.getBytes());
		List<Map<String, Object>> list = null;
		Map<String, Object> map = null;
		// String str = null;
		XmlPullParser parser = Xml.newPullParser(); // 由android.util.Xml创建一个XmlPullParser实例
		parser.setInput(is, "UTF-8"); // 设置输入流 并指明编码方式
		int eventType = parser.getEventType();
		while (eventType != XmlPullParser.END_DOCUMENT) {
			switch (eventType) {
			case XmlPullParser.START_DOCUMENT:
				list = new ArrayList<Map<String, Object>>();
				break;
			case XmlPullParser.START_TAG:
				if (parser.getName().equals("ServiceSubjectColumn")) {
					map = new HashMap<String, Object>();
					map.put("columnName", parser.getAttributeValue(0));
					map.put("name", parser.getAttributeValue(2));
					String value = "";
					if (parser.getAttributeValue(1).contains(".0")) {
						value = parser.getAttributeValue(1).replace(".0", "");
						map.put("value", value);
					} else {
						map.put("value", parser.getAttributeValue(1));
					}
					map.put("dataType", parser.getAttributeValue(3));
					map.put("length", parser.getAttributeValue(4));
					map.put("nonEmpty", parser.getAttributeValue(5));
					map.put("controlType", parser.getAttributeValue(6));
					map.put("index", parser.getAttributeValue(7));
					map.put("groupName", parser.getAttributeValue(8));
					map.put("groupIndex", parser.getAttributeValue(9));
					map.put("isShowGroupTitle", parser.getAttributeValue(10));
					list.add(map);
				}
				break;
			case XmlPullParser.END_TAG:
				break;
			}
			eventType = parser.next();
		}
		return list;
	}

	public static List<Map<String, Object>> getSubject_Detail_FJCK(String xml)
			throws XmlPullParserException, IOException {
		InputStream is = new ByteArrayInputStream(xml.getBytes());
		List<Map<String, Object>> list = null;
		Map<String, Object> map = null;
		XmlPullParser parser = Xml.newPullParser(); // 由android.util.Xml创建一个XmlPullParser实例
		parser.setInput(is, "UTF-8"); // 设置输入流 并指明编码方式
		int eventType = parser.getEventType();
		while (eventType != XmlPullParser.END_DOCUMENT) {
			switch (eventType) {
			case XmlPullParser.START_DOCUMENT:
				list = new ArrayList<Map<String, Object>>();
				break;
			case XmlPullParser.START_TAG:
				if (parser.getName().equals("BizAttachment")) {
					map = new HashMap<String, Object>();
					map.put("name", parser.getAttributeValue(0));
					map.put("url", parser.getAttributeValue(1));
					list.add(map);
				}
				break;
			case XmlPullParser.END_TAG:
				break;
			}
			eventType = parser.next();
		}
		return list;
	}

	public static List<Map<String, Object>> getDetail_WSBD_ServiceSubjecrFormXmlParse(
			String xml) throws XmlPullParserException, IOException {
		InputStream is = new ByteArrayInputStream(xml.getBytes());
		List<Map<String, Object>> list = null;
		Map<String, Object> map = null;
		// String str = null;
		XmlPullParser parser = Xml.newPullParser(); // 由android.util.Xml创建一个XmlPullParser实例
		parser.setInput(is, "UTF-8"); // 设置输入流 并指明编码方式
		int eventType = parser.getEventType();
		while (eventType != XmlPullParser.END_DOCUMENT) {
			switch (eventType) {
			case XmlPullParser.START_DOCUMENT:
				list = new ArrayList<Map<String, Object>>();
				break;
			case XmlPullParser.START_TAG:
				if (parser.getName().equals("ServiceSubjectColumn")) {
					map = new HashMap<String, Object>();
					map.put("columnName", parser.getAttributeValue(0));
					map.put("name", parser.getAttributeValue(2));
					map.put("value", parser.getAttributeValue(1));
					map.put("dataType", parser.getAttributeValue(3));
					map.put("length", parser.getAttributeValue(4));
					map.put("nonEmpty", parser.getAttributeValue(5));
					map.put("controlType", parser.getAttributeValue(6));
					map.put("index", parser.getAttributeValue(7));
					map.put("groupName", parser.getAttributeValue(8));
					map.put("groupIndex", parser.getAttributeValue(9));
					map.put("isShowGroupTitle", parser.getAttributeValue(10));
					list.add(map);
				}
				break;
			case XmlPullParser.END_TAG:
				break;
			}
			eventType = parser.next();
		}
		return list;
	}
}
