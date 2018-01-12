package com.chance.control;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.commons.io.FileUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.chance.commen.CarMess;
import com.chance.commen.Util;
import com.chance.service.WriteFile;

/**
 * @author 李闯
 * @date 2018年1月10日 上午10:48:35
 *
 */

@Controller
@RequestMapping(value="/download")
public class WriteFileControl {

	@Resource
	private WriteFile writeFileService;
	
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
    	String path = "E:\\work\\";
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
    	List<Map<String, String>> writeFile = writeFileService.writeFile();
    	CarMess carMess; 
    	for (int i=0;i<writeFile.size();i++){
    		carMess = new CarMess();
    		carMess.setOriginalText(writeFile.get(i).get("original_text"));
    		carMess.setLink(writeFile.get(i).get("link"));
    		carMess.setWebsite(writeFile.get(i).get("website"));
    		carMess.setThread_or_praise(writeFile.get(i).get("thread_or_praise"));
    		carMess.setYear(writeFile.get(i).get("year"));
    		carMess.setQuar(writeFile.get(i).get("quar"));
    		carMess.setMonth(writeFile.get(i).get("quar"));
    		carMess.setBrand(writeFile.get(i).get("brand"));
    		carMess.setCarName(writeFile.get(i).get("car_name"));
    		carMess.setCarModelVersion(writeFile.get(i).get("car_model_version"));
    		carMess.setFirstProperty(writeFile.get(i).get("first_property"));
    		carMess.setSecondProperty(writeFile.get(i).get("second_property"));
    		carMess.setThirdClass(writeFile.get(i).get("third_classify"));
    		carMess.setCommentKey(writeFile.get(i).get("comment_key"));
    		carMess.setRealFeel(writeFile.get(i).get("real_feel"));
    		carMess.setNation(writeFile.get(i).get("nation"));
    		carMess.setMarketCategory(writeFile.get(i).get("market_category"));
    		carMess.setMarketMess(writeFile.get(i).get("market_mess"));
    		carMessList.add(carMess);
    	}
    	Util.writer(path, filename, fileType1, carMessList, list);
    	return path+filename+"."+fileType1;
    }
    
}
