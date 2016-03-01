package com.youshusoft.service;


import com.youshusoft.data.UrlInfo;
import com.youshusoft.redis.RedisAPI;

public class UrlInfoService {
	public static UrlInfo set(String longUrl,String shortUrl){
		UrlInfo ui = new UrlInfo();
		ui.setLongUrl(longUrl);
		ui.setShortUrl(shortUrl);
		set(ui);
		return ui;
	}
	public static void set(UrlInfo shortUrl){
		RedisAPI.set(shortUrl);
	}
	public static UrlInfo get(String shortUrl){
		UrlInfo su = RedisAPI.get(new UrlInfo(),shortUrl);
		return su;
	}
	public static UrlInfo setnx(String longUrl,String shortUrl){
		UrlInfo ui = new UrlInfo(longUrl,shortUrl);
		return RedisAPI.setnx(ui)==1?ui:null;
	}
	
}
