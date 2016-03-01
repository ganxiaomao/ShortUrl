package com.youshusoft.data;

import java.io.Serializable;

public abstract class RedisData implements Serializable{
	public abstract String getKey();
	public abstract  byte[] write();
	public abstract void read(byte[] bs);
}
