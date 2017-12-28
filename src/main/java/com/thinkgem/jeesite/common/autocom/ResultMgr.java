package com.thinkgem.jeesite.common.autocom;

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

/**
 * 用于处理结果类的管理类 为避免同一系统中存在不同的适配格式,所以没有将该类设定为静态类
 *
 * @author 耿立
 * @date 2008-2-26
 */
public class ResultMgr {

	private static String BASE_RESULT = "BaseResult";

	private static String OL_RESULT = "OlResult";

	private static String LIST_RESULT = "ListResult";

	private static String OBJECT_RESULT = "ObjectResult";

	private static String TREE_RESULT = "TreeResult";

	private static String LINE_CHART_RESULT = "LineChartResult";

	/**
	 * BaseResult ListResult ObjectResult
	 * 
	 */
	private String rtType;

	private BaseResult result;

	private static String XML = "xml";

	private static String JSON = "json";

	private String adpType = JSON;

	public ResultMgr() {

	}

	public ResultMgr(BaseResult br, String type) {
		this.result = br;
		this.rtType = br.getClass().getSimpleName();
		if (null != type && !"".equals(type)) {
			this.adpType = type;
		}
	};

	public String convertResult(BaseResult br, String type) {
		this.result = br;
		this.rtType = br.getClass().getSimpleName();
		if (null != type && !"".equals(type)) {
			this.adpType = type;
		}
		return convertResult();
	};

	public String convertResult() {

		if (XML.equals(this.adpType)) {

		} else if (JSON.equals(this.adpType)) {
			return resultToJson();
		}
		return "";
	};

	public String resultToJson(BaseResult br) {
		this.result = br;
		this.rtType = br.getClass().getSimpleName();
		return resultToJson();
	}

	public String resultToJson() {

		if (null == result) {
			return "";
		}

		if (BASE_RESULT.equals(rtType)) {

			return brToJson();
		} else if (OL_RESULT.equals(rtType)) {

			return olrToJson();
		} else if (LIST_RESULT.equals(rtType)) {

			return lrToJson();
		} else if (OBJECT_RESULT.equals(rtType)) {

			return orToJson();
		}
		// else if(TREE_RESULT.equals(rtType)){
		//
		// return trToJson();
		// }
		else if (LINE_CHART_RESULT.equals(rtType)) {

			return lcrToJson();
		}

		return "";
	};

	/** 把BaseResult转换成Json字符串 */
	private String brToJson() {
		JsonConfig jsonConfig = new JsonConfig(); // 建立配置文件
		jsonConfig.setIgnoreDefaultExcludes(false); // 设置默认忽略
		jsonConfig.setExcludes(new String[] { "office","createBy","updateBy","currentUser","page","sqlMap","createDate","updateDate" });
		JSONObject json = JSONObject.fromObject(result,jsonConfig);

		return json.toString();
	};

	/** 把ListResult转换成Json字符串 */
	private String lrToJson() {
		JsonConfig jsonConfig = new JsonConfig(); // 建立配置文件
		jsonConfig.setIgnoreDefaultExcludes(false); // 设置默认忽略
		jsonConfig.setExcludes(new String[] { "office","createBy","updateBy","currentUser","page","sqlMap","createDate","updateDate" }); // 此处是亮点，只要将所需忽略字段加到数组中即可，在上述案例中，所要忽略的是“libs”，那么将其添到数组中即可，在实际测试中，我发现在所返回数组中，存在大量无用属性，如“multipartRequestHandler”，“servletWrapper”，那么也可以将这两个加到忽略数组中.

		JSONObject json = JSONObject.fromObject(result, jsonConfig);

		return json.toString();
	};

	/** 把ObjectResult转换成Json字符串 */
	private String orToJson() {
		JsonConfig jsonConfig = new JsonConfig(); // 建立配置文件
		jsonConfig.setIgnoreDefaultExcludes(false); // 设置默认忽略
		jsonConfig.setExcludes(new String[] { "office","createBy","updateBy","currentUser","page","sqlMap","createDate","updateDate" });
		JSONObject json = JSONObject.fromObject(result,jsonConfig);

		return json.toString();
	};

	/** 把OlResult转换成Json字符串 */
	private String olrToJson() {

		JSONObject json = JSONObject.fromObject(result);

		return json.toString();
	};

	// /** 把TreeResult转换成Json字符串 */
	// private String trToJson(){
	//
	//
	//
	// TreeResult tr = (TreeResult)result;
	//
	// JSONArray json = JSONArray.fromObject(tr.getItems());
	//
	// // JSONObject json = JSONObject.fromObject(result);
	//
	// return json.toString();
	// };

	/** 把LineChartResult转换成Json字符串 */
	private String lcrToJson() {

		// LineChartResult lcr = (LineChartResult)result;

		JSONObject json = JSONObject.fromObject(result);

		return json.toString();
	}

}
