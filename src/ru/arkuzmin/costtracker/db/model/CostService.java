package ru.arkuzmin.costtracker.db.model;

import java.util.Date;
import java.util.List;

import ru.arkuzmin.costtracker.db.bean.Agent;
import ru.arkuzmin.costtracker.db.bean.Cost;
import ru.arkuzmin.costtracker.model.dto.CostFilter;

public interface CostService {

		public List<Cost> getAllCosts();
		public void addCost(Cost newCost, int agentId, int catId);
		public void updateCost(Cost cost, int agentId, int catId);
		public void deleteCost(Cost cost);
		public List<Cost> getAllCostsByAgent(Agent agent, Date bDt, Date eDt);
		public List<Cost> getFilteredCosts(CostFilter filter);
}
