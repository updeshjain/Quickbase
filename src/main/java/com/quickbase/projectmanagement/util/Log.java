package com.quickbase.projectmanagement.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Log {
	public static Logger getLogger(Class<?> clazz) {
		return LogManager.getLogger(clazz);
	}
}