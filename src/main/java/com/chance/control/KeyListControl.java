package com.chance.control;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.chance.commen.Page;
import com.chance.service.KeyList;


@Controller
@RequestMapping(value="/keylist")
public class KeyListControl {
	
	public static Logger logger = LogManager.getLogger(KeyListControl.class);
	@Resource
	private KeyList keyListService;
	private List<Map<String,Object>> list = new ArrayList<>();
	//
	private List<Map<String, Object>> list2 = new ArrayList<>();
	//去重关键字集合
	private Set<String> set = new HashSet<>();
	private String optTemp = null;
	private String[] parameterValuesTemp = null;
	//首次查询的关键词或者原话内容存储
	Map<String,Object> map1 = null;
	//浏览页面的查找条件存储
	Map<String,Object> map_t = new HashMap<>();
	@RequestMapping(value="/queryAll.php")
	public ModelAndView queryAll(HttpServletRequest request) throws IOException{
		ModelAndView mav = new ModelAndView("test");
		Map<String,Object> map;
		list2 = keyListService.queryKeyList();
		list.clear();
		 for(int i=0;i<list2.size();i++){
			 map = new HashMap<>();
			 Set<String> keySet = list2.get(i).keySet();
			 for(String str :keySet){
				 set.add(list2.get(i).get(str).toString());
				 map.put(list2.get(i).get(str).toString(), str);
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
		map1 = new HashMap<>();
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
//		System.out.println(parameterValues.length+"===="+parameterValues[0]+"===="+opt);
		//判定筛选条件为原话：选中原话或者什么都不选
		map1.put("perPageSize", Page.perPageSize);
		if(parameterValues.length == 0 || (parameterValues.length == 1 && "text".equals(parameterValues[0]))){
			map1.put("original_text", opt);
		//判定筛选条件为关键字
		}else if(parameterValues.length == 1 && "key".equals(parameterValues[0])){
			String key = " ";
			System.out.println(list.size());
			for(int i = 0;i<list.size();i++){
				if(list.get(i).get(opt)!=null && !list.get(i).get(opt).equals("")){
					key = list.get(i).get(opt).toString();
					
					String[] split = key.split("-");
					if(split.length>0){
						for(int j = 0;j<split.length;j++){
//							System.out.println(split[j]+"<------>"+list2.get(i).get(split[j]));
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
					key = list.get(i).get(opt).toString();
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
		
		List<Map<String, Object>> queryList = keyListService.firstQueryList(map1);
		Page page = new Page(cnt,queryList);
		map_t.putAll(map1);
		keyListService.filterOption(map1);
		ModelAndView mav = new ModelAndView("/totalMessage");
		mav.addObject("filterOption",keyListService.reMap);
		mav.addObject("pages", page);
//		mav.addObject("map", map1.toString());
//		mav.addObject("opt", opt);
//		mav.addObject("parameterValues", parameterValues);
		return mav;
	}
	
	private int firstQueryCount(Map<String,Object> map){
		return keyListService.firstQueryCount(map);
	}

	/**
	 * 页面查询
	 */
	@RequestMapping(value="/pageQuaryList.php")
	public ModelAndView pageQuaryList(HttpServletRequest request){
		ModelAndView mav = new ModelAndView("/totalMessage");
		
		int num = 1;
		int totalCount = 1;
		try{
			num = Integer.parseInt(request.getParameter("nowPage"));
			totalCount = Integer.parseInt(request.getParameter("allCount"));
		}catch(Exception e){
			logger.error("数据转化错误："+request.getParameter("num")+" 或者 "+request.getParameter("allCount")+"\n"+e.getMessage());
		}
		if(num < 1){
			num = 1;
		}
		if(totalCount < 1){
			totalCount = 1;
		}
		Map<String,Object> map_page = new HashMap<>();
		map_page.putAll(map_t);
		map_page.put("start", (num -1) * Page.perPageSize);
		map_page.put("end",num * Page.perPageSize);
		System.out.println("totalCount===="+totalCount);
		Page page = new Page(totalCount,keyListService.pageQuaryList(map_page));
		page.setCurrentPageNum(num);
		mav.addObject("filterOption",keyListService.reMap);
		mav.addObject("pages", page);
		return mav;
	}
	
	@RequestMapping(value="/secondQuery.php")
	public ModelAndView secondeQueryList(HttpServletRequest request) throws UnsupportedEncodingException{
		ModelAndView mav = new ModelAndView("/totalMessage");
		map_t.put("website", new String(new String(request.getParameter("website").getBytes("ISO-8859-1"),"UTF-8")));
		map_t.put("year", new String(request.getParameter("year").getBytes("ISO-8859-1"),"UTF-8"));
		map_t.put("month", new String(request.getParameter("month").getBytes("ISO-8859-1"),"UTF-8"));
		map_t.put("quar", new String(request.getParameter("quar").getBytes("ISO-8859-1"),"UTF-8"));
		map_t.put("thread_or_praise", new String(request.getParameter("thread_or_praise").getBytes("ISO-8859-1"),"UTF-8"));
		map_t.put("car_name", new String(request.getParameter("car_name").getBytes("ISO-8859-1"),"UTF-8"));
		map_t.put("car_model_version",  new String(request.getParameter("car_model_version").getBytes("ISO-8859-1"),"UTF-8"));
		map_t.put("brand", new String(request.getParameter("brand").getBytes("ISO-8859-1"),"UTF-8"));
		map_t.put("real_feel", new String(request.getParameter("real_feel").getBytes("ISO-8859-1"),"UTF-8"));
		map_t.put("market_mess", new String(request.getParameter("market_mess").getBytes("ISO-8859-1"),"UTF-8"));
		map_t.put("nation", new String(request.getParameter("nation").getBytes("ISO-8859-1"),"UTF-8"));
//		System.out.println(map_t.size());
//		System.out.println(map1.toString());
		int cnt = firstQueryCount(map_t);
		List<Map<String,Object>> firstQueryList = keyListService.firstQueryList(map_t);
		Page page = new Page(cnt,firstQueryList);
		keyListService.filterOption(map_t);
		mav.addObject("filterOption",keyListService.reMap);
		mav.addObject("pages", page);
		return mav;
	}
}
