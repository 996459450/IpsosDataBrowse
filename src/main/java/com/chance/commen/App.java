package com.chance.commen;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 李闯
 * @date 2018年1月17日 下午3:40:01
 *
 */
public class App {

	
	public static void main(String[] args) {
		Map<String,Object> map = new HashMap<>();
		map.put("asd",null);
		System.out.println(map.getOrDefault("asd", " ").toString());
	}
}
