package ru.arkuzmin.costtracker.common;

import java.util.ArrayList;
import java.util.List;

/** 
 * Возможные значения количества записей в таблице для отображения.
 * @author ArKuzmin
 *
 */
public enum ListSizes {
	FIVE(5), TEN(10), TWENTY(20), FIFTY(50), HUNDRED(100), ALL(-1);
	
	private Integer size;
	
	ListSizes(Integer size) {
		this.size = size;
	}
	
	public Integer getSize() {
		return this.size;
	}
	
	@Override
	public String toString() {
		return ListSizes.this.equals(ListSizes.ALL) ? "Все" 
													: String.valueOf(this.size);
	}
	
	public static List<ListSizes> getList() {
		ArrayList<ListSizes> list = new ArrayList<ListSizes>();
		for (ListSizes size : values()) {
			list.add(size);
		}
		return list;
	}
}
