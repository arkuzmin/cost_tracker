package ru.arkuzmin.costtracker.model.chart;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;
import ru.arkuzmin.costtracker.common.ListSizes;
import ru.arkuzmin.costtracker.controller.utils.ControllerUtils;
import ru.arkuzmin.costtracker.db.bean.Agent;
import ru.arkuzmin.costtracker.db.bean.Category;
import ru.arkuzmin.costtracker.db.bean.Cost;
import ru.arkuzmin.costtracker.db.model.CostService;
import ru.arkuzmin.costtracker.db.model.ServiceFactory;
import ru.arkuzmin.costtracker.model.dto.CostFilter;


public class PieChartUtils {

	public static ObservableList<PieChart.Data> createPieChartContentByAgent(Agent agent, Date beginDT, Date endDT) {
		CostService cService = ServiceFactory.getCostService();
		ObservableList<PieChart.Data> list = FXCollections.<PieChart.Data>observableArrayList();
		
		CostFilter filter = ControllerUtils.createFilter(null, agent, null, beginDT, endDT, null, ListSizes.ALL);
		List<Cost> costList = cService.getFilteredCosts(filter);
		
		Map<String, Double> ccMap = new HashMap<String, Double>();
		for (Cost cost : costList) {
			Category c = cost.getCategory();
			Double amount = ccMap.get(c.getName());
			if (amount == null) {
				ccMap.put(c.getName(), cost.getAmount());
			} else {
				ccMap.put(c.getName(), cost.getAmount() + amount);
			}
		}
		
		for (String key : ccMap.keySet()) {
			list.add(new PieChart.Data(key, ccMap.get(key)));
		}
		
		return list;
	}
}
