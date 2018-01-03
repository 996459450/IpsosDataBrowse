package com.chance.dao;

import java.util.HashMap;

import org.springframework.stereotype.Repository;

@Repository
public interface StudentDao {

	HashMap<String,Object> queryStudentByid(String id);
	
}
