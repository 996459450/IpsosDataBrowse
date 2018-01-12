package com.chance.serviceImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.chance.dao.KeyListDao;
import com.chance.service.KeyList;

@Service
public class KeyListService implements KeyList{
	
	@Resource
	private KeyListDao keyListDao;
	
	@Override
	public List<Map<String,String>>  queryKeyList() {
		return keyListDao.queryKeyList();
	}

	@Override
	public List<Map<String, String>> firstQueryList(Map<String,String> map) {
		if(map != null){
			Set<String> keySet = map.keySet();
			for(String str : keySet){
				System.out.println(map.get(str));
			}
			return keyListDao.firstQueryList(map);
		}
		return null;
	}

	@Override
	public int firstQueryCount(Map<String, String> map) {
		if(map != null){
			return keyListDao.firstQueryCount(map);
		}
		return 0;
	}

	@Override
	public void filterOption(Map<String, String> map) {
		Set<String> website = new HashSet<String>();
		Set<String> thread_or_praise = new HashSet<String>();
		Set<String> year = new HashSet<String>();
		Set<String> quar = new HashSet<String>();
		Set<String> month = new HashSet<String>();
		Set<String> brand = new HashSet<String>();
		Set<String> car_name = new HashSet<String>();
		Set<String> car_model_version = new HashSet<String>();
		Set<String> first_property = new HashSet<String>();
		Set<String> second_property = new HashSet<String>();
		Set<String> third_classify = new HashSet<String>();
		Set<String> comment_key = new HashSet<String>();
		Set<String> real_feel = new HashSet<String>();
		Set<String> market_category = new HashSet<String>();
		Set<String> market_mess = new HashSet<String>();
		Set<String> nation = new HashSet<String>();
		if(map != null){
			List<Map<String,String>> list = keyListDao.filterOption(map);
			if(list != null && list.size() > 0){
				for(Map<String,String> line : list){
					website.add(line.get("website"));
					thread_or_praise.add(line.get("thread_or_praise"));
					year.add(line.get("year"));
					quar.add(line.get("quar"));
					month.add(line.get("month"));
					car_name.add(line.get("car_name"));
					car_model_version.add(line.get("car_model_version"));
					first_property.add(line.get("first_property"));
					brand.add(line.get("brand"));
					second_property.add(line.get("second_property"));
					third_classify.add(line.get("third_classify"));
					comment_key.add(line.get("comment_key"));
					real_feel.add(line.get("real_feel"));
					market_category.add(line.get("market_category"));
					market_mess.add(line.get("market_mess"));
					nation.add(line.get("nation"));
				}
			}
		}
		reMap.put("website", website);
		reMap.put("thread_or_praise", thread_or_praise);
		reMap.put("year", year);
		reMap.put("quar", quar);
		reMap.put("month", month);
		reMap.put("car_name", car_name);
		reMap.put("car_model_version", car_model_version);
		reMap.put("first_property", first_property);
		reMap.put("brand", brand);
		reMap.put("second_property", second_property);
		reMap.put("third_classify", third_classify);
		reMap.put("comment_key", comment_key);
		reMap.put("real_feel", real_feel);
		reMap.put("market_category", market_category);
		reMap.put("market_mess", market_mess);
		reMap.put("nation", nation);
	}

}
