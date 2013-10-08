package ru.arkuzmin.costtracker.model.dto;

import ru.arkuzmin.costtracker.db.bean.Category;

/**
 * Объект содержит информацию о категории и сумме расходов в этой категории. 
 * @author ArKuzmin
 *
 */
public class CatCostAmount {
	
	private Category cat;
	
	private Double amount;
	
	public CatCostAmount(Category cat, Double amount) {
		this.cat = cat;
		this.amount = amount;
	}

	public Category getCat() {
		return cat;
	}

	public void setCat(Category cat) {
		this.cat = cat;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}
	
	
}
