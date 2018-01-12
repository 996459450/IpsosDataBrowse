package com.chance.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;

import com.chance.control.WriteFileControl;

public class KeyListTest extends BaseJunitTest{

	@Resource
	private KeyList keyListService;
	@Resource
	private WriteFileControl writeFileControl;
	@Test
	public void keyListTest(){
		System.out.println(keyListService.queryKeyList().size());
	}
	
	@Test
	public void writeTest() throws Exception{
		System.out.println(writeFileControl.writeFile());
	}
	
	
	@Test
	public void listtest(){
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
    	
    	for(int i = 0;i < list.size();i++){
    		System.out.println(list.get(i));
    	}
	}
}
