package com.youshusoft.transport.extra;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.RandomUtils;
import org.apache.log4j.Logger;

import com.youshusoft.netty.NettyClient;
/**
 * Ëæ»úÑ¡ÔñÆ÷
 * @author ÓÆÊ÷
 *
 */
public class RanSelector implements Selector<NettyClient> {
	private static Logger logger = Logger.getLogger(RanSelector.class);
	private static RanSelector selector = new RanSelector();
	public static RanSelector getInstance(){
		return selector;
	}
	@Override
	public NettyClient select(List<NettyClient> list) {
		if(list == null || list.isEmpty()){
			return null;
		}
		List<NettyClient> ncs = new ArrayList<NettyClient>(list);
		
		NettyClient nc;
		do {
			nc = ncs.get(RandomUtils.nextInt(0, ncs.size()));
			if(nc == null ||
					!nc.isOpen()){
				ncs.remove(nc);
				if(ncs.isEmpty()){
					return null;
				}else{
					continue;
				}
			}
			logger.info("RanSelector " + nc.toString());
			return nc;
				
		} while (true);
	}

}
