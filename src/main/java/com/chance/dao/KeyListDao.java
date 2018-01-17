package com.chance.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

@Repository
public interface KeyListDao {

	/**
	 * 关键字查询
	 */
	public List<Map<String,Object>>  queryKeyList();
	
	/**
	 * 首页条件查询
	 */
	public List<Map<String,Object>> firstQueryList(Map<String,Object> map);
	
	/**
	 * 首页查询数据条数定位
	 */
	public int firstQueryCount(Map<String,Object> map);
	
	/**
	 * 条件筛选框查询
	 */
	public List<Map<String,Object>> filterOption(Map<String,Object> map);
	
	/**
	 * 分页查询
	 */
	public List<Map<String,Object>> pageQuaryList(Map<String,Object> map);
}
