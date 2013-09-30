package ru.arkuzmin.costtracker.db.model.impl;

import java.util.List;

import junit.framework.TestCase;

import org.junit.Ignore;
import org.junit.Test;

import ru.arkuzmin.costtracker.db.bean.Agent;
import ru.arkuzmin.costtracker.db.bean.Cost;

public class CostServiceImplTest extends TestCase {
	
	@Test
	public void testGetFilteredCosts() {
		CostServiceImpl cs = new CostServiceImpl();
		List<Cost> resList = cs.getFilteredCosts(null, null, null, null, null, null, 100);
		assertNotNull(resList);
		assertTrue(resList.size() > 0);
	}
	
	@Test
	public void testGetFilteredCosts2() {
		CostServiceImpl cs = new CostServiceImpl();
		List<Cost> resList = cs.getFilteredCosts(null, null, null, null, null, null, 2);
		assertNotNull(resList);
		assertTrue(resList.size() == 2);
	}
	
	@Ignore
	public void testGetFilteredCosts3() {
		CostServiceImpl cs = new CostServiceImpl();
		List<Cost> resList = cs.getFilteredCosts("тестовое%", null, null, null, null, null, 12);
		assertNotNull(resList);
		assertTrue(resList.size() == 3);
	}
	
	@Test
	public void testGetFilteredCosts4() {
		CostServiceImpl cs = new CostServiceImpl();
		Agent ag = new Agent();
		ag.setId(12);
		List<Cost> resList = cs.getFilteredCosts(null, ag, null, null, null, null, 12);
		assertNotNull(resList);
		assertTrue(resList.size() == 3);
	}
}
