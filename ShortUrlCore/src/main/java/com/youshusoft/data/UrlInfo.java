package com.youshusoft.data;

import com.youshusoft.util.Base64Util;
import com.youshusoft.util.KryoUtil;

public class UrlInfo extends RedisData{
	
	public UrlInfo() {
		super();
	}

	public UrlInfo(String longUrl,String shortUrl) {
		super();
		this.shortUrl = shortUrl;
		this.longUrl = longUrl;
	}

	private String shortUrl;
	
	private String longUrl;

	public String getShortUrl() {
		return shortUrl;    
	}

	public void setShortUrl(String shortUrl) {
		this.shortUrl = shortUrl;
	}

	public String getLongUrl() {
		return longUrl;
	}

	public void setLongUrl(String longUrl) {
		this.longUrl = longUrl;
	}
	
	public byte[] write(){
		return KryoUtil.serialize(this);
	}
	public void read(byte[] b){
		UrlInfo su = KryoUtil.deserialization(b, UrlInfo.class);
		this.setShortUrl(su.getShortUrl());
		this.setLongUrl(su.getLongUrl());
	}

	@Override
	public String getKey() {
		return shortUrl;
	}

	@Override
	public String toString() {
		return "ShortUrl[shortUrl="+ shortUrl +",longUrl="+ longUrl +"]";
	}
	
	
}
