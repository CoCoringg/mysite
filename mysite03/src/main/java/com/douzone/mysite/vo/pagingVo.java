package com.douzone.mysite.vo;

public class pagingVo {
	private int totalCount; // 전체 게시글 수 (get)
	private int currentPage; // 현재 페이지 (get)
	private int totalPage; // 전체 페이지 수 (get)
	private int countPage;  // 한 페이지에 몇 개의 페이지 (선택 set)
	private int countList;  // 한 페이지에 몇 개의 로우 (선택 set)
	private int startPage; // 페이징 시작 페이지 수
	private int endPage; // 페이징 종료 페이지 수
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public int getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	public int getCountPage() {
		return countPage;
	}
	public void setCountPage(int countPage) {
		this.countPage = countPage;
	}
	public int getCountList() {
		return countList;
	}
	public void setCountList(int countList) {
		this.countList = countList;
	}
	public int getStartPage() {
		return startPage;
	}
	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}
	public int getEndPage() {
		return endPage;
	}
	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}
	@Override
	public String toString() {
		return "pagingVo [totalCount=" + totalCount + ", currentPage=" + currentPage + ", totalPage=" + totalPage
				+ ", countPage=" + countPage + ", countList=" + countList + ", startPage=" + startPage + ", endPage="
				+ endPage + "]";
	}
	
}