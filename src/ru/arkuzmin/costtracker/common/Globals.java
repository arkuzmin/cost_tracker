package ru.arkuzmin.costtracker.common;

import java.text.SimpleDateFormat;

public interface Globals {
	
	public static final String PERSISTENCE_UNIT_NAME = "cost";

	public static final String RUB_SUFFIX = " руб.";
	
	public static final Integer FAKE_ID = -111111;
	
	public static final String COSTS_FILTER_WIND_NAME = "Фильтр поиска по затратам";
	
	public static SimpleDateFormat format = new SimpleDateFormat("yyyy.MM.dd");
}
