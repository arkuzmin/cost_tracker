package ru.arkuzmin.costtracker.db.model;

import ru.arkuzmin.costtracker.db.model.impl.AgentServiceImpl;
import ru.arkuzmin.costtracker.db.model.impl.CategoryServiceImpl;
import ru.arkuzmin.costtracker.db.model.impl.CostServiceImpl;

public class ServiceFactory {

	private static CostService costService;
	private static AgentService agentService;
	private static CategoryService catService;
	
	public static CategoryService getCategoryService() {
		if (catService == null) {
			catService = new CategoryServiceImpl();
		}
		return catService;
	}
	
	public static AgentService getAgentService() {
		if (agentService == null) {
			agentService = new AgentServiceImpl();
		}
		return agentService;
	}
	
	public static CostService getCostService() {
		if (costService == null) {
			costService = new CostServiceImpl();
		}
		return costService;
	}
}
