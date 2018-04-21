package czy.site.job;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import czy.site.common.log.LogHelper;
import czy.site.common.quartz.JobStaticConfigValue;
import czy.site.model.ProcessType;

public class JobProcess implements Job {

	public void execute(JobExecutionContext context) throws JobExecutionException {

		String jobName = (String) context.getJobDetail().getJobDataMap().get(JobStaticConfigValue.JobName);// job名称
		@SuppressWarnings("unchecked")
		HashMap<String, String> dicParams = (HashMap<String, String>) context.getJobDetail().getJobDataMap()
				.get(JobStaticConfigValue.JobParam);// 方法参数

		String jobType = dicParams.get(JobStaticConfigValue.JobType).trim();
		String triggerId = dicParams.get(JobStaticConfigValue.JobTriggerId).trim();

		try {
			ExecutorService executor = Executors.newCachedThreadPool();
			executor.submit(new Runnable() {
				public void run() {
					ProcessTask(ProcessType.getEnum(Integer.parseInt(jobType)), triggerId, jobName);
				}
			});
			executor.shutdown();

			// Task.Run(() => { });
		} catch (Exception e) {
			// 记录错误
			// Log4NetHelper.WriteExceptionLog(jobName + " -JobDetails触发器执行异常：", e);
		}
		//LogHelper.LogInfo("Hello quzrtz  " + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss ").format(new Date()));
	}

	public void ProcessTask(ProcessType type, String triggerId, String jobName) {
		try {
			switch (type) {
			case Level1:
				new JobFunction().Level1Fun(jobName);
				break;

			case Level2:
				// ResourceProcessFunction.SwitchFunction(jobName);
				break;

			case Level3:
				// SupplierProcessFunction.SwitchFunction(jobName);
				break;

			default:
				// Log4NetHelper.WriteExceptionLog("执行异常：" + type);
				break;
			}
		} catch (Exception ex) {
			// Log4NetHelper.WriteExceptionLog("执行ProcessTask 异常;异常信息：" + ex);
		}
	}
}

// 异步任务
