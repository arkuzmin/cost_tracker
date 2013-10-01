package ru.arkuzmin.costtracker.common;

import org.apache.log4j.Logger;

public class CommonUtils {

	private static final Logger logger = Logger.getLogger(CommonUtils.class);
	
	public static Double strToDoubleSafe(String str) {
		Double d = null;
		if (str == null || "".equals(str)) {
			return null;
		} else {
			try {
				d = Double.parseDouble(str);
			} catch (NumberFormatException e) {
				logger.error("Некорректный формат строки: " + str, e);
			}
			return d;
		}
	}
}
