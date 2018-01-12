package com.chance.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public interface KeyList {

	public Map<String, Set<String>> reMap = new HashMap<String,Set<String>>();
	public List<Map<String, String>> queryKeyList();
	
	
	public List<Map<String,String>> firstQueryList(Map<String,String> map);
	
	public int firstQueryCount(Map<String,String> map);
	
	public void filterOption(Map<String,String> map);
	
}
