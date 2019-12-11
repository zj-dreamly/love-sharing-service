package com.github.zj.dreamly.content.util;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * 对Page<E>结果进行包装
 * <p/>
 * 新增分页的多项属性，主要参考:http://bbs.csdn.net/topics/360010907
 *
 * @author liuzh/abel533/isea533
 * @version 3.3.0
 * @since 3.2.2
 * 项目地址 : http://git.oschina.net/free/Mybatis_PageHelper
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class PageInfo<T> extends PageSerializable<T> {
	private int pageNum;
	private int pageSize;
	private int size;
	private int startRow;
	private int endRow;
	private int pages;
	private int prePage;
	private int nextPage;
	private boolean isFirstPage = false;
	private boolean isLastPage = false;
	private boolean hasPreviousPage = false;
	private boolean hasNextPage = false;
	private int navigatePages;
	private int[] navigatepageNums;
	private int navigateFirstPage;
	private int navigateLastPage;

	public PageInfo() {
	}

	/**
	 * 包装Page对象
	 */
	public PageInfo(List<T> list) {
		this(list, 8);
	}

	/**
	 * 包装Page对象
	 *
	 * @param list          page结果
	 * @param navigatePages 页码数量
	 */
	public PageInfo(List<T> list, int navigatePages) {
		super(list);
		if (list instanceof Page) {
			Page page = (Page) list;
			this.pageNum = page.getPageNum();
			this.pageSize = page.getPageSize();

			this.pages = page.getPages();
			this.size = page.size();
			//由于结果是>startRow的，所以实际的需要+1
			if (this.size == 0) {
				this.startRow = 0;
				this.endRow = 0;
			} else {
				this.startRow = page.getStartRow() + 1;
				//计算实际的endRow（最后一页的时候特殊）
				this.endRow = this.startRow - 1 + this.size;
			}
		} else if (list != null) {
			this.pageNum = 1;
			this.pageSize = list.size();

			this.pages = this.pageSize > 0 ? 1 : 0;
			this.size = list.size();
			this.startRow = 0;
			this.endRow = list.size() > 0 ? list.size() - 1 : 0;
		}
		if (list != null) {
			this.navigatePages = navigatePages;
			//计算导航页
			calcNavigatepageNums();
			//计算前后页，第一页，最后一页
			calcPage();
			//判断页面边界
			judgePageBoudary();
		}
	}

	public static <T> PageInfo<T> of(List<T> list) {
		return new PageInfo<T>(list);
	}

	public static <T> PageInfo<T> of(List<T> list, int navigatePages) {
		return new PageInfo<T>(list, navigatePages);
	}

	/**
	 * 计算导航页
	 */
	private void calcNavigatepageNums() {
		//当总页数小于或等于导航页码数时
		if (pages <= navigatePages) {
			navigatepageNums = new int[pages];
			for (int i = 0; i < pages; i++) {
				navigatepageNums[i] = i + 1;
			}
		} else { //当总页数大于导航页码数时
			navigatepageNums = new int[navigatePages];
			int startNum = pageNum - navigatePages / 2;
			int endNum = pageNum + navigatePages / 2;

			if (startNum < 1) {
				startNum = 1;
				//(最前navigatePages页
				for (int i = 0; i < navigatePages; i++) {
					navigatepageNums[i] = startNum++;
				}
			} else if (endNum > pages) {
				endNum = pages;
				//最后navigatePages页
				for (int i = navigatePages - 1; i >= 0; i--) {
					navigatepageNums[i] = endNum--;
				}
			} else {
				//所有中间页
				for (int i = 0; i < navigatePages; i++) {
					navigatepageNums[i] = startNum++;
				}
			}
		}
	}

	/**
	 * 计算前后页，第一页，最后一页
	 */
	private void calcPage() {
		if (navigatepageNums != null && navigatepageNums.length > 0) {
			navigateFirstPage = navigatepageNums[0];
			navigateLastPage = navigatepageNums[navigatepageNums.length - 1];
			if (pageNum > 1) {
				prePage = pageNum - 1;
			}
			if (pageNum < pages) {
				nextPage = pageNum + 1;
			}
		}
	}

	/**
	 * 判定页面边界
	 */
	private void judgePageBoudary() {
		isFirstPage = pageNum == 1;
		isLastPage = pageNum == pages || pages == 0;
		hasPreviousPage = pageNum > 1;
		hasNextPage = pageNum < pages;
	}
}
