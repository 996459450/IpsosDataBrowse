package com.chance.serviceImpl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.chance.dao.KeyListDao;
import com.chance.service.KeyList;

@Service
public class KeyListService implements KeyList{

	@Resource
	private KeyListDao keyListDao;
	
	@Override
	public List<String>  queryKeyList() {
		return keyListDao.queryKeyList();
	}

}
