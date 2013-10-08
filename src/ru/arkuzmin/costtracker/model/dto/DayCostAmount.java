package ru.arkuzmin.costtracker.model.dto;

import java.util.Date;

/**
 * Содержит сумму всех затрат за заданную дату.
 * @author ArKuzmin
 *
 */
public class DayCostAmount {

	private Date date;
	
	private Double amount;
	
	public DayCostAmount(Date date, Double amount) {
		this.date = date;
		this.amount = amount;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}
}
