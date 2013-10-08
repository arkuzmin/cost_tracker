package ru.arkuzmin.costtracker.db.model.impl;

import java.util.Date;
import java.util.List;

import junit.framework.TestCase;

import org.junit.Test;

import ru.arkuzmin.costtracker.common.Globals;
import ru.arkuzmin.costtracker.common.ListSizes;
import ru.arkuzmin.costtracker.db.bean.Cost;
import ru.arkuzmin.costtracker.db.model.CostService;
import ru.arkuzmin.costtracker.db.model.ServiceFactory;
import ru.arkuzmin.costtracker.model.dto.AgentCostAmount;
import ru.arkuzmin.costtracker.model.dto.CatCostAmount;
import ru.arkuzmin.costtracker.model.dto.DayCostAmount;

public class CostServiceImplTest extends TestCase {

	@Test
	public void testGetTotalCosts() {
		CostService service = ServiceFactory.getCostService();
		Double total = service.getTotalCosts();
		
		assertTrue(total != Globals.UNDEFINED_DOUBLE);	
	}
	@Test
	public void testGetDateOfFirstCost() {
		CostService service = ServiceFactory.getCostService();
		Date date = service.getDateOfFirstCost();
		
		assertNotNull(date);	
	}
	@Test
	public void testGetDateOfLastCost() {
		CostService service = ServiceFactory.getCostService();
		Date date = service.getDateOfLastCost();
		
		assertNotNull(date);	
	}
	@Test
	public void testGetLargestCosts() {
		CostService service = ServiceFactory.getCostService();
		List<Cost> list = service.getLargestCosts(ListSizes.FIVE);
		
		assertTrue(list.size() <= ListSizes.FIVE.getSize());
	}
	@Test
	public void testGetLargestCatCosts() {
		CostService service = ServiceFactory.getCostService();
		List<CatCostAmount> list = service.getLargestCatCosts(ListSizes.FIVE);
		
		assertTrue(list.size() <= ListSizes.FIVE.getSize());
	}
	@Test
	public void testGetLargetsAgentCosts() {
		CostService service = ServiceFactory.getCostService();
		List<AgentCostAmount> list = service.getLargetsAgentCosts(ListSizes.FIVE);
		
		assertTrue(list.size() <= ListSizes.FIVE.getSize());
	}
	@Test 
	public void testGetLargestDayCosts() {
		CostService service = ServiceFactory.getCostService();
		List<DayCostAmount> list = service.getLargestDayCosts(ListSizes.FIFTY);
		
		assertTrue(list.size() <= ListSizes.FIVE.getSize());
	}
}