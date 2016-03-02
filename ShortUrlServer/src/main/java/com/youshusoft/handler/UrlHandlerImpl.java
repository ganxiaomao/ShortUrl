package com.youshusoft.handler;


import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import com.youshusoft.data.UrlInfo;
import com.youshusoft.netty.NettyHandler;
import com.youshusoft.netty.ResData;
import com.youshusoft.service.UrlInfoService;
import com.youshusoft.util.ShortUrlUtil;

public class UrlHandlerImpl extends NettyHandler implements UrlHandler{
	private static Logger logger = Logger.getLogger(UrlHandlerImpl.class);
	public static byte[] aliasLock = new byte[]{};
	@Override
	public ResData create(String longUrl,String alias) {
		String shortUrl;
		if(StringUtils.isBlank(longUrl)){
			return error("url 参数不可为空");
		}
		UrlInfo su = null;
		if(StringUtils.isBlank(alias)){
			shortUrl = ShortUrlUtil.genShortUrl(longUrl);
			su = UrlInfoService.set(longUrl,shortUrl);
		}else{
			shortUrl = alias;
			su = UrlInfoService.set(longUrl, shortUrl);
			if(su == null){
				return error("自定义网址已被使用");
			}
		}
		logger.info("create url: \nlongUrl=" + longUrl + "\nshortUrl=" + shortUrl + "\nalias=" + alias );
		return succ(su);
	}
	@Override
	public ResData queryUrl(String shortUrl) {
		UrlInfo su = UrlInfoService.get(shortUrl);
		logger.info("queryUrl url: " + shortUrl.toString() );
		return succ(su);
	}

}
