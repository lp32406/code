package com.eb.dianlianbao_server.util;

import java.time.LocalDateTime;

import org.apache.log4j.Logger;

/**
 * 日志工具类
 * @author Administrator
 * @date 2018年10月30日 上午10:56:18
 */
public class LogUtils {

	private static Logger log = Logger.getLogger(LogUtils.class);
	private static final String PATTERN = " #############################################################################";

	public static void logInfo(final String message) {
		log.info("(" + LocalDateTime.now() + ")" + message + PATTERN);
	}

	public static void logInfo(final String message, Throwable t) {
		log.info("(" + LocalDateTime.now() + ")" + message + PATTERN, t);
	}

	public static void logWarn(final String message) {
		log.warn("(" + LocalDateTime.now() + ")" + message + PATTERN);
	}

	public static void logWarn(final String message, Throwable t) {
		log.warn("(" + LocalDateTime.now() + ")" + message + PATTERN, t);
	}

	public static void logError(final String message) {
		log.error("(" + LocalDateTime.now() + ")" + message + PATTERN);
	}

	public static void logError(final String message, Throwable t) {
		log.error("(" + LocalDateTime.now() + ")" + message + PATTERN, t);
	}

	public static void logDebug(final String message) {
		log.debug("(" + LocalDateTime.now() + ")" + message + PATTERN);
	}

	public static void logDebug(final String message, Throwable t) {
		log.debug("(" + LocalDateTime.now() + ")" + message + PATTERN, t);
	}

}
