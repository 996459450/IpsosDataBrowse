package com.chance.serviceImpl;

import java.util.HashMap;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.chance.dao.StudentDao;
import com.chance.service.Student;

import jodd.util.StringUtil;

@Service
public class StudentService implements Student {

	@Resource
	StudentDao studentDao;
	@Override
	public HashMap<String, Object> queryStudentByid(String id) {
		if (StringUtil.isEmpty(id)){
			return null;
		}
		return studentDao.queryStudentByid(id);
	}

}
