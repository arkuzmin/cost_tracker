package ru.arkuzmin.costtracker.db.bean;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="COST")

@NamedQueries({
	@NamedQuery(name="Cost.getAllByAgent",	query="select c from Cost c where c.agent = :agent and c.date >= :bDt and c.date <= :eDt")
	
	})
public class Cost {
	
	@Id
	@Column(name="COST_ID")
	private int id;
	
	@Column(name="COST_NAME")
	private String name;
	
	@Column(name="COST_DESC")
	private String desc;
	
	@JoinColumn(name="COST_CAT")
	private Category category;
	
	@JoinColumn(name="COST_AGENT")
	private Agent agent;
	
	@Column(name="COST_AMOUNT")
	private double amount;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="COST_DT")
	private Date date;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Agent getAgent() {
		return agent;
	}

	public void setAgent(Agent agent) {
		this.agent = agent;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}
	
	public void setAmount(String amount) {
		Double am = Double.parseDouble(amount);
		this.amount = am;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
	public void update(Cost cost) {
		this.agent = cost.getAgent();
		this.category = cost.getCategory();
		this.name = cost.getName();
		this.desc = cost.getDesc();
		this.amount = cost.getAmount();
		this.date = cost.getDate();
	}
}
