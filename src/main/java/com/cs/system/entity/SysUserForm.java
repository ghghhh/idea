package com.cs.system.entity;

import java.time.LocalDateTime;

/**
 * Created by cs on 2017/6/22.
 */
public class SysUserForm extends SystemUser{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String sort;
    private int page;
    private int rows;
    private String order;
    private LocalDateTime createDateStart;
    private LocalDateTime createDateEnd;
    private String search;
    private String searchType;
    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

	public LocalDateTime getCreateDateStart() {
		return createDateStart;
	}

	public void setCreateDateStart(LocalDateTime createDateStart) {
		this.createDateStart = createDateStart;
	}

	public LocalDateTime getCreateDateEnd() {
		return createDateEnd;
	}

	public void setCreateDateEnd(LocalDateTime createDateEnd) {
		this.createDateEnd = createDateEnd;
	}

	public String getSearch() {
		return search;
	}

	public void setSearch(String search) {
		this.search = search;
	}

	public String getSearchType() {
		return searchType;
	}

	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}
	
	

    
}
