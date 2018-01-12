package com.chance.serviceImpl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.chance.dao.WriteFileDao;
import com.chance.service.WriteFile;

/**
 * @author 李闯
 * @date 2018年1月10日 上午10:37:12
 *
 */

@Service
public class WriteFileService implements WriteFile{

	@Resource
	private WriteFileDao writeFileDao;
	@Override
	public List<Map<String, String>> writeFile() {
		List<Map<String, String>> writeFile = writeFileDao.writeFile();
		return writeFile;
	}

	
	
}
