package com.youshusoft.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
/**
 * ���л�&�����й���
 * @author Administrator
 *
 */
public class KryoUtil {
	private final static Kryo kryo = new Kryo();
	/**
	 * ���л�
	 * @param o
	 * @return
	 */
	public static byte[] serialize(Object o){
		Output output = new Output(new ByteArrayOutputStream());
		kryo.writeObject(output, o);
		byte[] b = output.toBytes();
		output.close();
		return b;
	}
	/**
	 * �����л�
	 * @param b
	 * @param c
	 * @return
	 */
	public static Object deserialization(byte[] b,Class c){
		Input input = new Input(new ByteArrayInputStream(b));
		Object object = kryo.readObject(input, c);
		return object;
	}
}
