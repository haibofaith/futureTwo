package com.haibo.futwo.web.entity;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;

public class BaseResponse {

	/**
	 * 默认0:为正常, 业务异常>0
	 * 系统错误<0
	 */
	private int retcode = 0;
	/**
	 * 异常描述
	 */
	private String retdesc = "";
	/**
	 * 返回数据信息
	 */
	private Object body;


	/**   
	 * 得到retcode的值   
	 *   
	 * @return retcode的值
	 */
	public int getRetcode() {
		return retcode;
	}

	/**
	 * 设置retcode的值
	 *   
	 * @param retcode 被设置的值
	 */
	public void setRetcode(int retcode) {
		this.retcode = retcode;
	}

	/**   
	 * 得到retdesc的值   
	 *   
	 * @return retdesc的值
	 */
	public String getRetdesc() {
		return retdesc;
	}

	/**
	 * 设置retdesc的值
	 *   
	 * @param retdesc 被设置的值
	 */
	public void setRetdesc(String retdesc) {
		this.retdesc = retdesc;
	}


	/**   
	 * 得到body的值   
	 *   
	 * @return body的值
	 */
	public Object getBody() {
		return body;
	}

	/**
	 * 设置body的值
	 *   
	 * @param body 被设置的值
	 */
	public void setBody(Object body) {
		this.body = body;
	}

	@Override
	public String toString() {
		return JSONObject.toJSONString(this, SerializerFeature.WriteDateUseDateFormat, SerializerFeature.WriteMapNullValue, SerializerFeature.DisableCircularReferenceDetect);
	}
}
