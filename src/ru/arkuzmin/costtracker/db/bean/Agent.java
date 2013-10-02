package ru.arkuzmin.costtracker.db.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="AGENT")
public class Agent {
	
	@Transient
	public static final String ID = "id";
	@Transient
	public static final String NAME = "name";
	
	@Id
	@Column(name="AGENT_ID")
	private int id;
	
	@Column(name="AGENT_NAME")
	private String name;
	
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
	
	public void update(Agent agent) {
		this.name = agent.name;
	}
	
	public String toString() {
		return name;
	}
}
