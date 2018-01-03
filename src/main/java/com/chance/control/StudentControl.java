package com.chance.control;

import java.util.Date;
import java.util.HashMap;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.chance.service.Student;

@Controller
@RequestMapping(value="/student")
public class StudentControl {

	public static Logger logger = LogManager.getLogger(StudentControl.class);
	@Resource
	public Student studentService;
	@RequestMapping(value="/select.php")
	public ModelAndView queryStudentByid(HttpServletRequest request){
		HashMap<String,Object> map = studentService.queryStudentByid(request.getParameter("id"));
		ModelAndView mav = new ModelAndView("test");
		mav.addObject("map",map);
		logger.error(new Date()+":success");
		return mav;
	}
	
}
