package com.sunjoy.framework.utils;

import ch.qos.logback.classic.AsyncAppender;
import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.core.Appender;
import ch.qos.logback.core.FileAppender;
import java.util.Iterator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogbackUtils {
	private static Logger log = LoggerFactory.getLogger(LogbackUtils.class);

	private LogbackUtils() {
		throw new IllegalStateException("Utility class");
	}

	public static boolean updateSystemPropertyWithLogbackFileName() {
		try {
			Iterator e = ((LoggerContext) LoggerFactory.getILoggerFactory()).getLogger("ROOT").iteratorForAppenders();

			while (true) {
				while (e.hasNext()) {
					Appender appender = (Appender) e.next();
					if (appender instanceof AsyncAppender) {
						AsyncAppender fileName2 = (AsyncAppender) appender;
						Iterator asyncIt = fileName2.iteratorForAppenders();

						while (asyncIt.hasNext()) {
							Appender asyncRef = (Appender) asyncIt.next();
							if (asyncRef instanceof FileAppender) {
								String fileName1 = ((FileAppender) asyncRef).getFile();
								System.setProperty("logging.file", fileName1);
								return true;
							}
						}
					} else if (appender instanceof FileAppender) {
						String fileName = ((FileAppender) appender).getFile();
						System.setProperty("logging.file", fileName);
						return true;
					}
				}

				return false;
			}
		} catch (Exception arg5) {
			log.warn("can not init admin log file.", arg5);
			return false;
		}
	}
}