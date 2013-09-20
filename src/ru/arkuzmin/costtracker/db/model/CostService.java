package ru.arkuzmin.costtracker.db.model;

import java.util.List;

import ru.arkuzmin.costtracker.db.bean.Cost;

public interface CostService {

		public List<Cost> getAllCosts();
		public void addCost(Cost newCost);
		public void updateCost(Cost cost);
		public void deleteCost(Cost cost);
}
