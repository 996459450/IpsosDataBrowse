package com.chance.control;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.chance.service.KeyList;

@Controller
@RequestMapping(value="/keylist")
public class KeyListControl {

	@Resource
	private KeyList keyListService;
	
	@RequestMapping(value="queryAll.php")
	public ModelAndView queryAll(HttpServletRequest request){
		ModelAndView mav = new ModelAndView("test");
		mav.addObject("list", keyListService.queryKeyList());
		return mav;
	}
}
