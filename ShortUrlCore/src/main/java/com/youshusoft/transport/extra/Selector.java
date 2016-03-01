package com.youshusoft.transport.extra;

import java.util.List;
/**
 * ¾ùºâÑ¡ÔñÆ÷
 * @author ÓÆÊ÷
 *
 */
public interface Selector<T> {
	 T  select(List<T> list);
}
