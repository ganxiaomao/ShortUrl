package com.youshusoft.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.eclipse.jetty.server.Request;

import com.youshusoft.data.UrlInfo;
import com.youshusoft.netty.ResData;
import com.youshusoft.transport.extra.EasyCaller;
import com.youshusoft.util.Config;
import com.youshusoft.util.Config.DnsConfig;


public class DnsHandler extends JettyAbstractHandler{
	public void handle(String target, Request baseRequest,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		String sUrl = request.getPathInfo().substring(1);
		if(StringUtils.isBlank(sUrl) || sUrl.indexOf(".") != -1){
			return;
		}
		String redirectUrl = ""; 
		DnsConfig dns = Config.getDnsConfig(request.getServerName());
		ResData resData = EasyCaller.getInstance(dns.getServerGroup()).createRemote(UrlHandler.class).queryUrl(sUrl);
		UrlInfo su;
		if(resData == null || (su = resData.getData()) == null || su.getLongUrl().equals("")){
			return;
		}else{
			redirectUrl = su.getLongUrl();
		}
		response.sendRedirect(redirectUrl);
	}

}
