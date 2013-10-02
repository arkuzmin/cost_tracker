package ru.arkuzmin.costtracker.model.report;

import java.util.Calendar;
import java.util.Date;

import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.Months;
import org.joda.time.Years;

import ru.arkuzmin.costtracker.db.model.CostService;
import ru.arkuzmin.costtracker.db.model.ServiceFactory;

/** 
 * Содержит методы для формирования текстового отчета.
 * @author ArKuzmin
 *
 */
public class TextReportUtils {
	
	private Calendar firstCostDt;
	private Calendar lastCostDt;
	private Double totalCosts;
	
	private void init() {
		CostService service = ServiceFactory.getCostService();
		
		Date time = service.getDateOfFirstCost();
		if (time != null) {
			firstCostDt = Calendar.getInstance();
			firstCostDt.setTime(time);
		}
		
		time = service.getDateOfLastCost();
		if (time != null) {
			lastCostDt = Calendar.getInstance();
			lastCostDt.setTime(time);
		}
		
		totalCosts = service.getTotalCosts();
	}
	
	public TextReportUtils() {
		init();
	}
	
	private DateTime toDateTimeM(Calendar c) {
		return new DateTime(c.get(Calendar.YEAR), c.get(Calendar.MONTH) + 1, 1, 0, 0);
	}
	
	private DateTime toDateTimeD(Calendar c) {
		return new DateTime(c.get(Calendar.YEAR), c.get(Calendar.MONTH) + 1, c.get(Calendar.DAY_OF_MONTH), 0, 0);
	}
	
	private DateTime toDateTimeY(Calendar c) {
		return new DateTime(c.get(Calendar.YEAR), 1, 1, 0, 0);
	}
	
	private boolean checkConstraints() {
		return firstCostDt != null && lastCostDt != null && totalCosts != null;
	}
	
	/** 
	 * Возвращает средний расход за месяц по всем месяцам.
	 * @return средний расход за месяц
	 */
	public Double getAVGMonthCost() {
		if (checkConstraints()) {
			DateTime start = toDateTimeM(firstCostDt);
			DateTime end = toDateTimeM(lastCostDt);
			int mc = Months.monthsBetween(start, end).getMonths() + 1;
			return (Double) (totalCosts / mc);
		} else {
			return null;
		}

	}
	
	/** 
	 * Возвращает средний расход за день по всем дням.
	 * @return средний расход за день
	 */
	public Double getAVGDayCost() {
		if (checkConstraints()) {
			DateTime start = toDateTimeD(firstCostDt);
			DateTime end = toDateTimeD(lastCostDt);
			int dc = Days.daysBetween(start, end).getDays() + 1;
			return (Double) (totalCosts / dc);
		} else {
			return null;
		}
	}
	
	/** 
	 * Возвращает средний расход за год по всем годам.
	 * @return средний расход за год
	 */
	public Double getAVGYearCost() {
		if (checkConstraints()) {
			DateTime start = toDateTimeY(firstCostDt);
			DateTime end = toDateTimeY(lastCostDt);
			int dy = Years.yearsBetween(start, end).getYears() + 1;
			return (Double) (totalCosts / dy);
		} else {
			return null;
		}
	}
	
	/**
	 * Возвращает общие расходы за все время.
	 * @return общие расходы за все время
	 */
	public Double getTotalCosts() {
		return totalCosts;
	}
	
	
}
