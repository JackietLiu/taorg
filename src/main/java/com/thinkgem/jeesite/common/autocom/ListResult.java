package com.thinkgem.jeesite.common.autocom;

import java.util.List;

public class ListResult extends BaseResult{
	private int total;
	private List items;

	public List getItems() {
		return items;
	}

	public void setItems(List items) {
		this.items = items;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

}
