package ru.arkuzmin.costtracker.model.dto;

import java.util.Date;

import ru.arkuzmin.costtracker.common.ListSizes;
import ru.arkuzmin.costtracker.controller.utils.ControllerUtils;
import ru.arkuzmin.costtracker.db.bean.Agent;
import ru.arkuzmin.costtracker.db.bean.Category;

/** Фильтр поиска по всем расходам */
public class CostFilter {
	private String name;
	private Agent agent;
	private Category cat;
	private Date startDt;
	private Date endDt;
	private Double amount;
	private ListSizes listSize;
	
	public CostFilter() {
		init();
	}
	
	private void init() {
		this.name = null;
		this.agent = null;
		this.cat = null;
		this.startDt = ControllerUtils.getOtherDate(-7);
		this.endDt = new Date();
		this.amount = null;
		this.listSize = ListSizes.HUNDRED;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Agent getAgent() {
		return agent;
	}
	public void setAgent(Agent agent) {
		this.agent = agent;
	}
	public Category getCat() {
		return cat;
	}
	public void setCat(Category cat) {
		this.cat = cat;
	}
	public Date getStartDt() {
		return startDt;
	}
	public void setStartDt(Date startDt) {
		this.startDt = startDt;
	}
	public Date getEndDt() {
		return endDt;
	}
	public void setEndDt(Date endDt) {
		this.endDt = endDt;
	}
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	public ListSizes getListSize() {
		return listSize;
	}
	public void setListSize(ListSizes listSize) {
		this.listSize = listSize;
	}
}
