package com.chance.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

/**
 * @author 李闯
 * @date 2018年1月10日 上午10:28:12
 *
 */
@Repository
public interface WriteFileDao {

	public List<Map<String,String>> writeFile();
	
}
