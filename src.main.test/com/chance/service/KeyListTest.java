package com.chance.service;

import javax.annotation.Resource;

import org.junit.Test;

public class KeyListTest extends BaseJunitTest{

	@Resource
	private KeyList keyListService;
	@Test
	public void keyListTest(){
		System.out.println(keyListService.queryKeyList().size());
	}
	
}
