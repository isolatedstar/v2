package com.zllh.base.mybatis;

import java.util.Collections;
import java.util.List;

@SuppressWarnings("serial")
public class Page<E> implements java.io.Serializable{
	
	/** 显示页数 */
	private int pageSize = 5; 
	/** 总共多少页 */
	private int totalPage;
	/** 总条数 */
	private int totalCount;
	/** 开始条数 */
	private int beginIndex;
	/** 当前页 */
	private int nowPage;
	/** 返回结果集 */
	private List<E> result = Collections.emptyList();
	/** 前台标记用*/
	private int indexCode;
	
	public int getBeginIndex() {
		beginIndex = (getNowPage()-1)*getPageSize();
		if(beginIndex<0){
			beginIndex = 0;
		}
		return beginIndex;
	}
	public void setBeginIndex(int beginIndex) {
		this.beginIndex = beginIndex;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	public List<E> getResult() {
		return result;
	}
	public void setResult(List<E> result) {
		this.result = result;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	public void setNowPage(int nowPage) {
		this.nowPage = nowPage;
	}
	public int getTotalPage() {
		totalPage = (int)Math.ceil(totalCount*1.0/pageSize);
		return totalPage;
	}
	public int getNowPage() {
		if(nowPage<=0) nowPage = 1;
		if(nowPage>getTotalPage()) nowPage = getTotalPage();
		return nowPage;
	}
	public int getIndexCode() {
		return indexCode;
	}
	public void setIndexCode(int indexCode) {
		this.indexCode = indexCode;
	}
	@Override
	public String toString() {
		return "Page [pageShow=" + pageSize + ", totalPage=" + getTotalPage()
				+ ", totalCount=" + totalCount + ", nowPage=" + nowPage
				+ ", result=" + result + "]";
	}
	
	
}