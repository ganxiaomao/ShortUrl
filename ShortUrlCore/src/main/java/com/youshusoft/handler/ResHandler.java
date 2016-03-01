package com.youshusoft.handler;

import com.youshusoft.conts.CodeConts;
import com.youshusoft.netty.ResData;

public class ResHandler {
	public static ResData succ(Object obj){
		ResData  rm = new ResData();
		rm.setStatus(CodeConts.SUCCEED);
		rm.setData(obj);
		return rm;
	}
	public static ResData error(String msg){
		return error(CodeConts.ERROR, "");
	}
	public static ResData error(int errCode,String msg){
		ResData  rm = new ResData();
		rm.setStatus(errCode);
		rm.setData(msg);
		return rm;
	}
}
