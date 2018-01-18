package com.chance.service;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public interface KeyList {

	/**
	 * 用于存储筛选框的筛选条件
	 */
	public Map<String, Set<String>> reMap = new HashMap<String,Set<String>>();
	/**
	 * 首页查询时检索模糊匹配的数据
	 */
	public List<Map<String, Object>> queryKeyList();
	
	/**
	 * 查询每页的数据
	 */
	public List<Map<String, Object>> firstQueryList(Map<String,Object> map);
	
	/**
	 * 查询数据量
	 */
	public int firstQueryCount(Map<String,Object> map);
	
	/**
	 * 筛选框数据检索
	 */
	public void filterOption(Map<String,Object> map);
	/**
	 * 分页查询
	 */
	public List<Map<String, Object>> pageQuaryList(Map<String,Object> map);
	
	/**
	 * 下载查询
	 */
	public List<Map<String,Object>> download(Map<String,Object> map);
}
