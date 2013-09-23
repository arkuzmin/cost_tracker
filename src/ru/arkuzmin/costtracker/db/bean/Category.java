package ru.arkuzmin.costtracker.db.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="CATEGORY")
public class Category {
	@Id
	@Column(name="CAT_ID")
	private int id;
	
	@Column(name="CAT_NAME")
	private String name;
	
	@Column(name="CAT_DESC")
	private String desc;

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
	
	public String toString() {
		return name;
	}
	
	public void update(Category cat) {
		this.name = cat.getName();
		this.desc = cat.getDesc();
	}
}
