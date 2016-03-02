package com.youshusoft.other;

import java.util.List;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

import com.youshusoft.util.Config;
import com.youshusoft.util.Config.WebConfig;
import com.youshusoft.util.ServerHelper;
public class InstantiationProcessor implements ApplicationListener<ContextRefreshedEvent>{
	
	public void onApplicationEvent(ContextRefreshedEvent event) {
		if(event.getApplicationContext().getParent() == null){//root application context 没有parent，他就是老大.  
			//初始化时建立netty连接
			List<WebConfig> webs = Config.getAllWebConfig();
			for (WebConfig web : webs) {
				ServerHelper.init(Config.getServerConfigByGroup(web.getServerGroup()));
			}
	    }
	}

}
