package czy.site.common.log;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LogHelper {
	private static Logger logger = LogManager.getLogger(LogHelper.class.getName());
	
	public static void LogInfo(String msg) {
		logger.info(msg);
	}
	
	public static void LogWarning(String msg) {
		logger.warn(msg);
	}
	
	public static void LogError(String msg) {
		logger.error(msg);
	}
}
