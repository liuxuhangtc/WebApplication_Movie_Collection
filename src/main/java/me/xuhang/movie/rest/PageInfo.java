package me.xuhang.movie.rest;

import lombok.Data;

import java.util.List;

/**
 * Created by xuhang on 2019/11/20.
 * Pagination
 */
@Data
public class PageInfo<T> {

    private int pageSize = 40;

    private int pageNo = 1;

    private int totalRows = -1;

    private int startRow;

    private int endRow;

    @SuppressWarnings("unused")
	private int totalPages;

    private List<T> resultList;

    public PageInfo(int pageNo, int pageSize, List<T> resultList, int totalRows) {
        super();
        this.pageNo = pageNo;
        this.pageSize = pageSize;
        this.resultList = resultList;
        this.totalRows = totalRows;
    }

    public PageInfo() {
    }

    public PageInfo(int pageNo) {
        int endRowNum = pageNo * pageSize;
        this.startRow = endRowNum - pageSize;
    }

    public PageInfo(int pageNo, int pageSize) {
        this.pageNo = pageNo;
        int endRowNum = pageNo * pageSize;
        this.startRow = endRowNum - pageSize;
        if (pageSize != 0)
            this.pageSize = pageSize;
    }

    public int getTotalPages() {
        return (int) Math.ceil((float) totalRows / pageSize);
    }

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public int getTotalRows() {
		return totalRows;
	}

	public void setTotalRows(int totalRows) {
		this.totalRows = totalRows;
	}

	public int getStartRow() {
		return startRow;
	}

	public void setStartRow(int startRow) {
		this.startRow = startRow;
	}

	public int getEndRow() {
		return endRow;
	}

	public void setEndRow(int endRow) {
		this.endRow = endRow;
	}

	public List<T> getResultList() {
		return resultList;
	}

	public void setResultList(List<T> resultList) {
		this.resultList = resultList;
	}

	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}


}
