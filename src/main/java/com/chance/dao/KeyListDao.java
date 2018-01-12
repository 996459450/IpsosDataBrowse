package com.chance.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

@Repository
public interface KeyListDao {

	/**
	 * 关键字查询
	 */
	public List<Map<String,String>>  queryKeyList();
	
	/**
	 * 首页条件查询
	 */
	public List<Map<String,String>> firstQueryList(Map<String,String> map);
	
	/**
	 * 首页查询数据条数定位
	 */
	public int firstQueryCount(Map<String,String> map);
	
	/**
	 * 条件筛选框查询
	 */
	public List<Map<String,String>> filterOption(Map<String,String> map);
}
