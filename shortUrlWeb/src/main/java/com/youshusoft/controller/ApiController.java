package com.youshusoft.controller;


import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSONArray;
import com.youshusoft.conts.CodeConts;
import com.youshusoft.data.UrlInfo;
import com.youshusoft.handler.UrlHandler;
import com.youshusoft.netty.ResData;
import com.youshusoft.other.CommonConts;
import com.youshusoft.util.Config;
import com.youshusoft.util.Config.WebConfig;

@Controller
@RequestMapping(value = "/api")
public class ApiController extends BaseController {
	private static Logger logger = Logger.getLogger(ApiController.class);
	@RequestMapping(value = "/create" + CommonConts.API_SUFFIX)
	public ResData create(String serviceId,String longUrl,String alias){
		logger.info("args " + "serverId=" + serviceId + " longUrl=" + longUrl + " alias=" + alias);
		if(StringUtils.isBlank(longUrl)){
			return error("longUrl 参数不可为空。");
		}
		WebConfig wc = Config.getWebConfig(serviceId);
		if(wc == null){
			return error("短网址服务器配置不存在 (serviceId:"+ serviceId +")");
		}
		ResData resData = createRemote(wc.getServerGroup(),UrlHandler.class).create(longUrl, alias);
		if(resData == null){
			return error("请求短网址服务器超时。");
		}
		if(resData.getStatus() == CodeConts.SUCCEED){
			//成功返回短网址
			UrlInfo su = resData.getData();
			if(su != null){
				return succ(wc.getDomain() + "/" + su.getShortUrl());
			}else{
				//TODO
			}
			
		}
		return resData;
	}
	@RequestMapping(value = "/getServices" + CommonConts.API_SUFFIX)
	public ResData getServices(){
		return succ(JSONArray.toJSON(Config.getAllWebConfig()));
	}

}
