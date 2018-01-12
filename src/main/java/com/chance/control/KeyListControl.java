package com.chance.control;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.chance.commen.Page;
import com.chance.service.KeyList;


@Controller
@RequestMapping(value="/keylist")
public class KeyListControl {

	@Resource
	private KeyList keyListService;
	private List<Map<String,String>> list = new ArrayList<>();
	//
	private List<Map<String, String>> list2 = new ArrayList<>();
	//去重关键字集合
	private Set<String> set = new HashSet<>();
	private String optTemp = null;
	private String[] parameterValuesTemp = null;
	@RequestMapping(value="/queryAll.php")
	public ModelAndView queryAll(HttpServletRequest request) throws IOException{
		ModelAndView mav = new ModelAndView("test");
		Map<String,String> map;
		list2 = keyListService.queryKeyList();
		list.clear();
		 for(int i=0;i<list2.size();i++){
			 map = new HashMap<>();
			 Set<String> keySet = list2.get(i).keySet();
			 for(String str :keySet){
				 set.add(list2.get(i).get(str));
				 map.put(list2.get(i).get(str), str);
			 }
			 list.add(map);
		 }
		mav.addObject("list", set);
		return mav;
	}
	
	/**
	 * 首页查询情况分析
	 */
	@RequestMapping(value="/keyQuery.php")
	public ModelAndView firstQuery(HttpServletRequest request) throws IOException, ClassNotFoundException{
		String opt = request.getParameter("opt");
		String[] parameterValues = request.getParameterValues("checkbox");
		if(opt != null){
			optTemp = opt;
		}
		if(parameterValues != null){
			parameterValuesTemp = parameterValues;
		}
		if (opt == null){
			opt=optTemp;
		}
		if(parameterValues == null){
			parameterValues= parameterValuesTemp;
		}
		System.out.println(parameterValues.length+"===="+parameterValues[0]+"===="+opt);
		Map<String,String> map1 = new HashMap<>();
		//判定筛选条件为原话：选中原话或者什么都不选
		if(parameterValues.length == 0 || (parameterValues.length == 1 && "text".equals(parameterValues[0]))){
			map1.put("original_text", opt);
		//判定筛选条件为关键字
		}else if(parameterValues.length == 1 && "key".equals(parameterValues[0])){
			String key = " ";
			System.out.println(list.size());
			for(int i = 0;i<list.size();i++){
				if(list.get(i).get(opt)!=null && !list.get(i).get(opt).equals("")){
					key = list.get(i).get(opt);
					
					String[] split = key.split("-");
					if(split.length>0){
						for(int j = 0;j<split.length;j++){
							System.out.println(split[j]+"<------>"+list2.get(i).get(split[j]));
							map1.put(split[j],list2.get(i).get(split[j]));
						}
					}
					break;
				}
			}
			if(" ".equals(key)){
				map1.put("first_property", "asoiuhfduasd");
			}
		}else{
			String key = "";
			for(int i = 0;i<list.size();i++){
				if(list.get(i).get(opt)!=null && !list.get(i).get(opt).equals("")){
					key = list.get(i).get(opt);
					String[] split = key.split("-");
					if(split.length>0){
						for(int j = 0;j<split.length;j++){
							map1.put(split[j],list2.get(i).get(split[j]));
						}
					}
					break;
				}
			}
			map1.put("original_text", opt);
		}
		int cnt = firstQueryCount(map1);
		
		List<Map<String, String>> queryList = keyListService.firstQueryList(map1);
		Page page = new Page(cnt,queryList);
		keyListService.filterOption(map1);
		ModelAndView mav = new ModelAndView("/totalMessage");
		mav.addObject("filterOption",keyListService.reMap);
		mav.addObject("pages", page);
		mav.addObject("map", map1.toString());
		mav.addObject("opt", opt);
		mav.addObject("parameterValues", parameterValues);
		return mav;
	}
	
	private int firstQueryCount(Map<String,String> map){
		return keyListService.firstQueryCount(map);
	}

	@RequestMapping(value="/secondQuery.php")
	public ModelAndView secondeQueryList(HttpServletRequest request){
		
		
		return null;
		
	}
}
