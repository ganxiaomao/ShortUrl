package com.youshusoft.handler;

import com.youshusoft.netty.ResData;

public interface UrlHandler {
	ResData create(String longUrl, String alias);

	ResData queryUrl(String shortUrl);

}
