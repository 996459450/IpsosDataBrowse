package com.chance.commen;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 分页类
 * @date 2018年1月9日 上午11:03:26
 *
 */
public class Page {

	private int currentPageNum =  1;//当前第几页，默认第一页
	
	private int totalPageNum ;//总页数
	private int totalCount ;//总记录数
	private int visiblePages = 0;//设置最多显示的页码数，默认0
	private int perPageSize = 15;//每页显示条数
	private List entitys = new ArrayList();//记录当前页的数据条目
	
	public Page(int currentPageNum, int totalCount, int perPageSize,  
            List entitys){
		this.totalCount = totalCount;  
        this.perPageSize = perPageSize; 
        this.totalPageNum = totalCount % perPageSize == 0?totalCount  
                / perPageSize : totalCount / perPageSize + 1;
        this.entitys = entitys;
        //当前页小于1，停在第一页
        this.currentPageNum = currentPageNum<1?1:(currentPageNum>totalPageNum?totalPageNum:currentPageNum);
        this.visiblePages = this.totalPageNum > 10?10:this.totalPageNum;
	}
	// 使用默认的当前页和每页显示记录条数  
    public Page( int totalCount, List entitys) {  
        this.totalCount = totalCount;  
        this.totalPageNum = totalCount % perPageSize == 0 ? totalCount  
                / perPageSize : totalCount / perPageSize + 1;  
        this.entitys = entitys;  
        this.currentPageNum = currentPageNum<1?1:(currentPageNum>totalPageNum?totalPageNum:currentPageNum);//如果当前页小于第一页，则停留在第一页  
        this.visiblePages = this.totalPageNum > 10?10:this.totalPageNum;
    } 
    
    public int getVisiblePages() {
		return visiblePages;
	}
	public void setVisiblePages(int visiblePages) {
		this.visiblePages = visiblePages;
	}
	public int getCurrentPageNum() {  
        return currentPageNum;  
    }  
  
    public void setCurrentPageNum(int currentPageNum) {  
        this.currentPageNum = currentPageNum<1?1:(currentPageNum>totalPageNum?totalPageNum:currentPageNum);//如果当前页小于第一页，则停留在第一页  
    }  
  
    public int getTotalPageNum() {  
        return totalPageNum;  
    }  
  
    public void setTotalPageNum(int totalPageNum) {  
        this.totalPageNum = totalCount % perPageSize == 0 ? totalCount  
                / perPageSize : totalCount / perPageSize + 1;  
    }  
  
    public int getTotalCount() {  
        return totalCount;  
    }  
  
    public void setTotalCount(int totalCount) {  
        this.totalCount = totalCount;  
    }  
  
    public int getPerPageSize() {  
        return perPageSize;  
    }  
  
    public void setPerPageSize(int perPageSize) {  
        this.perPageSize = perPageSize;  
    }  
  
    public List getEntitys() {  
        return entitys;  
    }  
  
    public void setEntitys(List entitys) {  
        this.entitys = entitys;  
    }  
    @Override  
    public String toString() {  
        return "PageUtil [currentPageNum=" + currentPageNum + ", totalPageNum="  
                + totalPageNum + ", totalCount=" + totalCount  
                + ", perPageSize=" + perPageSize + ", entitys=" + entitys + "]";  
    } 
    
}
