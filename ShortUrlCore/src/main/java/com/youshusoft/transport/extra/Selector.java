package com.youshusoft.transport.extra;

import java.util.List;
/**
 * ����ѡ����
 * @author ����
 *
 */
public interface Selector<T> {
	 T  select(List<T> list);
}
