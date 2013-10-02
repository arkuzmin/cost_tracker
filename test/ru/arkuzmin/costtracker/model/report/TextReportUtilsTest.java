package ru.arkuzmin.costtracker.model.report;

import junit.framework.TestCase;

import org.junit.Test;

public class TextReportUtilsTest extends TestCase {
	
	@Test
	public void testGetAVGMonthCost() {
		TextReportUtils utils = new TextReportUtils();
		Double avg = utils.getAVGMonthCost();
		
		if (avg != null) {
			assertTrue(avg > 0);
		}
	}
	
	@Test
	public void testGetAVGDayCost() {
		TextReportUtils utils = new TextReportUtils();
		Double avg = utils.getAVGDayCost();
		
		if (avg != null) {
			assertTrue(avg > 0);
		}
	}
	
	@Test
	public void testGetAVGYearCost() {
		TextReportUtils utils = new TextReportUtils();
		Double avg = utils.getAVGYearCost();
		
		if (avg != null) {
			assertTrue(avg > 0);
		}
	}
}
