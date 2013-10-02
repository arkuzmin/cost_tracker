package ru.arkuzmin.costtracker.db.model;

import java.util.Date;
import java.util.List;

import ru.arkuzmin.costtracker.common.ListSizes;
import ru.arkuzmin.costtracker.db.bean.Cost;
import ru.arkuzmin.costtracker.model.dto.CostFilter;

public interface CostService {
		/** 
		 * Возвращает список всех расходов.
		 * @return список всех расходов
		 */
		public List<Cost> getAllCosts();
		
		/** 
		 * Добавление нового расхода.
		 * @param newCost - новый расход
		 * @param agentId - агент
		 * @param catId - категория
		 */
		public void addCost(Cost newCost, int agentId, int catId);
		
		/**
		 * Обновление информации по расходу.
		 * @param cost - расход
		 * @param agentId - агент 
		 * @param catId - категория
		 */
		public void updateCost(Cost cost, int agentId, int catId);
		
		/** 
		 * Удаление расхода.
		 * @param cost - расход
		 */
		@Deprecated
		public void deleteCost(Cost cost);
		
		/** 
		 * Возвращает отфильтрованный список расходов
		 * @param filter - фильтр
		 * @return отфильтрованный список
		 */
		public List<Cost> getFilteredCosts(CostFilter filter);
		
		/** 
		 * Возвращает общую сумму всех расходов. 
		 * @return сумма всех расходов
		 */
		public Double getTotalCosts();
		
		/** 
		 * Возвращает дату первого расхода.
		 * @return дата первого расхода
		 */
		public Date getDateOfFirstCost();
		
		/** 
		 * Возвращает дату последнего расхода.
		 * @return дата последнего расхода
		 */
		public Date getDateOfLastCost();
		
		/**
		 * Возвращает топ самых больших затрат.
		 * @param size - размер топа
		 * @return топ самых больших затрат
		 */
		public List<Cost> getLargestCosts(ListSizes size);
}
