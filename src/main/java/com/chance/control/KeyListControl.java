package com.chance.control;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.chance.commen.CarMess;
import com.chance.commen.Page;
import com.chance.commen.Util;
import com.chance.service.KeyList;


@Controller
@RequestMapping(value="/keylist")
public class KeyListControl {
	//临时文件保存路径
	String path = "E:\\work\\";
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
	
	private String spanStart = "<strong>";
	private String spanEnd = "</strong>";
	
	//首次查询的关键词或者原话内容存储
	Map<String,Object> map1 = null;
	//浏览页面的查找条件存储
	Map<String,Object> map_t = new HashMap<>();
	@RequestMapping(value="/redirct.php")
	public ModelAndView redirctHome(){
		ModelAndView mav = new ModelAndView("test");
		mav.addObject("list", set);
		return mav;
	}
	@RequestMapping(value="/queryAll.php")
	public ModelAndView queryAll(HttpServletRequest request) throws IOException{
		ModelAndView mav = new ModelAndView("test");
		try{
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
		}catch(Exception e){
			logger.error("页面跳转出现问题:"+e.getMessage());
			mav.addObject("message", "查询失败！");
			return mav;
		}
	}
	
	/**
	 * 首页查询情况分析
	 */
	@RequestMapping(value="/keyQuery.php")
	public ModelAndView firstQuery(HttpServletRequest request) throws IOException, ClassNotFoundException{
		String opt = request.getParameter("opt");
		String[] parameterValues = request.getParameterValues("checkbox");
		ModelAndView mav = new ModelAndView("/totalMessage");
		try{
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
								map1.put(split[j],list2.get(i).get(split[j]));
							}
						}
						break;
					}
				}
				if(" ".equals(key)){
					map1.put("first_property", null);
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
			
			mav.addObject("filterOption",keyListService.reMap);
			mav.addObject("pages", page);
			return mav;
		}catch(Exception e){
			logger.error("页面跳转出现问题："+e.getMessage());
			mav.addObject("message", "查询出错");
			return mav;
		}
	}
	
	private int firstQueryCount(Map<String,Object> map){
		return keyListService.firstQueryCount(map);
	}

	/**
	 * 页面查询
	 * @throws Exception 
	 */
	@RequestMapping(value="/pageQuaryList.php")
	public ModelAndView pageQuaryList(HttpServletRequest request) throws Exception{
		ModelAndView mav = new ModelAndView("/totalMessage");
		int num = 1;
		int totalCount = 1;
		try{
			num = Integer.parseInt(request.getParameter("nowPage"));
			totalCount = Integer.parseInt(request.getParameter("allCount"));
		}catch(Exception e){
			logger.error("数据转化错误："+request.getParameter("num")+" 或者 "+request.getParameter("allCount")+"\n"+e.getMessage());
			logger.error("分页查询出错："+e.getMessage());
			mav.addObject("message","查询出错");
			return mav;
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
		try{
			List<Map<String, Object>> firstQueryList = keyListService.pageQuaryList(map_page);
			for(Map<String, Object> list : firstQueryList){
				list.put("totalDiv", getHtmlString(list));
				System.out.println(list);
			}
			Page page = new Page(totalCount,firstQueryList);
			page.setCurrentPageNum(num);
			mav.addObject("filterOption",keyListService.reMap);
			mav.addObject("pages", page);
			return mav;
		}catch(Exception e){
			logger.error("分页查询出错："+e.getMessage());
			mav.addObject("message","查询出错");
			return mav;
		}
	}
	
	@RequestMapping(value="/secondQuery.php")
	public ModelAndView secondeQueryList(HttpServletRequest request) throws UnsupportedEncodingException{
		ModelAndView mav = new ModelAndView("/totalMessage");
		try{
			map_t.put("website", new String(request.getParameter("website").getBytes("ISO-8859-1"),"UTF-8"));
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
			int cnt = firstQueryCount(map_t);
			List<Map<String,Object>> firstQueryList = keyListService.firstQueryList(map_t);
			for(Map<String, Object> list : firstQueryList){
				list.put("totalDiv", getHtmlString(list));
			}
			Page page = new Page(cnt,firstQueryList);
			keyListService.filterOption(map_t);
			mav.addObject("filterOption",keyListService.reMap);
			mav.addObject("pages", page);
			return mav;
		}catch(Exception e){
			logger.error("页面呈现出现错误："+e.getMessage());
			mav.addObject("pages", new Page(1,null));
			mav.addObject("message","查询出错");
			return mav;
		}
	}
	@RequestMapping(value="/download.php")    
    public ResponseEntity<byte[]> download() throws Exception {   
    	String path = writeFile();
        File file=new File(path);  
        HttpHeaders headers = new HttpHeaders();    
        String fileName=new String((UUID.randomUUID().toString()+".xlsx").getBytes("UTF-8"),"iso-8859-1");//为了解决中文名称乱码问题  
        headers.setContentDispositionFormData("attachment", fileName);   
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file),    
                                          headers, HttpStatus.CREATED);    
    }
    
    public String writeFile() throws Exception{
    	String filename = UUID.randomUUID().toString();
    	String fileType1 = "xlsx";
    	String fileType2 = "xls";
    	List<String> list = new ArrayList<>();
    	list.add("原话");
    	list.add("链接");
    	list.add("网站");
    	list.add("口碑/论坛");
    	list.add("年份");
    	list.add("季度");
    	list.add("月度");
    	list.add("品牌");
    	list.add("车系");
    	list.add("车型版本");
    	list.add("一级属性");
    	list.add("二级属性");
    	list.add("三级分类");
    	list.add("评价关键词");
    	list.add("实体情感正负");
    	list.add("国别");
    	list.add("细分市场");
    	list.add("省份/区域");
    	List<CarMess> carMessList = new ArrayList<>();
    	List<Map<String, Object>> writeFile = keyListService.download(map_t);
    	CarMess carMess; 
    	for (int i=0;i<writeFile.size();i++){
    		carMess = new CarMess();
    		carMess.setOriginalText(writeFile.get(i).get("original_text").toString());
    		carMess.setLink(writeFile.get(i).get("link").toString());
    		carMess.setWebsite(writeFile.get(i).get("website").toString());
    		carMess.setThread_or_praise(writeFile.get(i).get("thread_or_praise").toString());
    		carMess.setYear(writeFile.get(i).get("year").toString());
    		carMess.setQuar(writeFile.get(i).get("quar").toString());
    		carMess.setMonth(writeFile.get(i).get("quar").toString());
    		carMess.setBrand(writeFile.get(i).get("brand").toString());
    		carMess.setCarName(writeFile.get(i).get("car_name").toString());
    		carMess.setCarModelVersion(writeFile.get(i).get("car_model_version").toString());
    		carMess.setFirstProperty(writeFile.get(i).get("first_property").toString());
    		carMess.setSecondProperty(writeFile.get(i).get("second_property").toString());
    		carMess.setThirdClass(writeFile.get(i).get("third_classify").toString());
    		carMess.setCommentKey(writeFile.get(i).get("comment_key").toString());
    		carMess.setRealFeel(writeFile.get(i).get("real_feel").toString());
    		carMess.setNation(writeFile.get(i).get("nation").toString());
    		carMess.setMarketCategory(writeFile.get(i).get("market_category").toString());
    		carMess.setMarketMess(writeFile.get(i).get("market_mess").toString());
    		carMessList.add(carMess);
    	}
    	Util.writer(path, filename, fileType1, carMessList, list);
    	return path+filename+"."+fileType1;
    }
    /**
     * 拼接前台带样式的字符串
     */
    private String getHtmlString(Map<String,Object> map){
    	if(map == null){
    		return null;
    	}
    	Object original_text = map.get("original_text");
    	if(original_text != null && original_text.toString() != ""){
    		for(String str : set){
        		original_text = original_text.toString().replaceAll(str, "<strong>"+str+"</strong>");
        	}
    	}
    	map.put("original_text",original_text);
    	StringBuffer sb = new StringBuffer();
    	sb.append("<br/>");
    	sb.append("<table border='1px solid black'  width='800px' style='border-collapse: collapse;border-spacing: 0;'>");
/*    	Set<String> keySet = map.keySet();
    	for(String key : keySet){*/
    		sb.append("<tr><td width='180px' valign='middle' align='center'>");
    		sb.append("网站");
    		sb.append("</td>");
    		sb.append("<td width='600px' valign='middle'>");
    		sb.append(map.get("website"));
    		sb.append("</td></tr>");
    		
    		sb.append("<tr><td width='180px' valign='middle' align='center'>");
    		sb.append("链接");
    		sb.append("</td>");
    		sb.append("<td width='600px' valign='middle'>");
    		sb.append("<a href="+map.get("link")+" target='_blank'>"+map.get("link")+"</a>");
    		sb.append("</td></tr>");
    		
    		sb.append("<tr><td width='180px' valign='middle' align='center'>");
    		sb.append("年度");
    		sb.append("</td>");
    		sb.append("<td width='600px' valign='middle'>");
    		sb.append(map.get("year"));
    		sb.append("</td></tr>");
    		
    		sb.append("<tr><td width='180px' valign='middle' align='center'>");
    		sb.append("季度");
    		sb.append("</td>");
    		sb.append("<td width='600px' valign='middle'>");
    		sb.append(map.get("quar"));
    		sb.append("</td></tr>");
    		
    		sb.append("<tr><td width='180px' valign='middle' align='center'>");
    		sb.append("月份");
    		sb.append("</td>");
    		sb.append("<td width='600px' valign='middle'>");
    		sb.append(map.get("month"));
    		sb.append("</td></tr>");
    		
    		sb.append("<tr><td width='180px' valign='middle' align='center'>");
    		sb.append("口碑/论坛");
    		sb.append("</td>");
    		sb.append("<td width='600px' valign='middle'>");
    		sb.append(map.get("thread_or_praise"));
    		sb.append("</td></tr>");
    		
    		sb.append("<tr><td width='180px' valign='middle' align='center'>");
    		sb.append("车系");
    		sb.append("</td>");
    		sb.append("<td width='600px' valign='middle'>");
    		sb.append(map.get("car_name"));
    		sb.append("</td></tr>");
    		
    		sb.append("<tr><td width='180px' valign='middle' align='center'>");
    		sb.append("车型版本");
    		sb.append("</td>");
    		sb.append("<td width='600px' valign='middle'>");
    		sb.append(map.get("car_model_version"));
    		sb.append("</td></tr>");
    		
    		sb.append("<tr><td width='180px' valign='middle' align='center'>");
    		sb.append("一级属性");
    		sb.append("</td>");
    		sb.append("<td width='600px' valign='middle'>");
    		sb.append(map.get("first_property"));
    		sb.append("</td></tr>");
    		
    		sb.append("<tr><td width='180px' valign='middle' align='center'>");
    		sb.append("二级属性");
    		sb.append("</td>");
    		sb.append("<td width='600px' valign='middle'>");
    		sb.append(map.get("second_property"));
    		sb.append("</td></tr>");
    		
    		sb.append("<tr><td width='180px' valign='middle' align='center'>");
    		sb.append("三级分类");
    		sb.append("</td>");
    		sb.append("<td width='600px' valign='middle'>");
    		sb.append(map.get("third_classify"));
    		sb.append("</td></tr>");
    		
    		sb.append("<tr><td width='180px' valign='middle' align='center'>");
    		sb.append("关键词");
    		sb.append("</td>");
    		sb.append("<td width='600px' valign='middle'>");
    		sb.append(map.get("comment_key"));
    		sb.append("</td></tr>");
    		
    		sb.append("<tr><td width='180px' valign='middle' align='center'>");
    		sb.append("关键词");
    		sb.append("</td>");
    		sb.append("<td width='600px' valign='middle'>");
    		sb.append(map.get("comment_key"));
    		sb.append("</td></tr>");
    		
    		sb.append("<tr><td width='180px' valign='middle' align='center'>");
    		sb.append("真实情感");
    		sb.append("</td>");
    		sb.append("<td width='600px' valign='middle'>");
    		sb.append(map.get("real_feel"));
    		sb.append("</td></tr>");
    		
    		sb.append("<tr><td width='180px' valign='middle' align='center'>");
    		sb.append("国家");
    		sb.append("</td>");
    		sb.append("<td width='600px' valign='middle'>");
    		sb.append(map.get("nation"));
    		sb.append("</td></tr>");
    		
    		sb.append("<tr><td width='180px' valign='middle' align='center'>");
    		sb.append("细分市场");
    		sb.append("</td>");
    		sb.append("<td width='600px' valign='middle'>");
    		sb.append(map.get("market_mess"));
    		sb.append("</td></tr>");
    		
    		sb.append("<tr><td width='180px' valign='middle' align='center'>");
    		sb.append("原话");
    		sb.append("</td>");
    		sb.append("<td width='600px' valign='middle'>");
    		sb.append(map.get("original_text"));
    		sb.append("</td></tr>");
//    	}
    	sb.append("</table>");
		return sb.toString();
    }
}
