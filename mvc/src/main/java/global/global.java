package global;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.stereotype.Service;

import czy.site.common.log.LogHelper;
import czy.site.job.StartJob;
/**
 *
 * 
 * 
 * web应用的全局事件
 * 
 * @author Simon
 *
 * 
 * 
 */

@Service
public class global {

	/**
	 * 
	 * 在web启动时执行
	 * 
	 */

	@PostConstruct
	public void applicationStart() {

		LogHelper.LogWarning("application start");
		new StartJob().AddJob();
	}

	/**
	 * 
	 * 在web结束时执行
	 * 
	 */

	@PreDestroy
	public void applicationEnd() {
		LogHelper.LogWarning("InskyScheduleCenter application end");
	}

}
