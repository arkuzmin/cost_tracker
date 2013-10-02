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
}
