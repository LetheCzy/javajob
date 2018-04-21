package czy.site.common.quartz;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;
import org.quartz.TriggerUtils;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.spi.OperableTrigger;
import org.springframework.util.StringUtils;

import czy.site.common.log.LogHelper;
import czy.site.job.JobProcess;
import czy.site.model.JobStatus;

public class JobManagerBase {
	/* private static SchedulerFactory sf = new StdSchedulerFactory(); */
	/*
	 * private static String JOB_GROUP_NAME = "ddlib"; private static String
	 * TRIGGER_GROUP_NAME = "ddlibTrigger";
	 */
	/// <summary>
	/// 作业集合，用于存储多个job
	/// </summary>
	private static Map<String, JobStruct> jobs = new HashMap<String, JobStruct>();

	/**
	 * 开始一个cronSchedule()调度
	 */
	public static JobStruct Instance(String jobName, String jobCnName, Map<String, String> dicParams) {
		try {
			// 为作业结构体赋值
			JobStruct jobStruct = new JobStruct();
			jobStruct.JobName = jobName;
			jobStruct.JobCnName = jobCnName;
			jobStruct.dicParams = dicParams;

			// 初始化调度器工厂
			Properties nvc = new Properties();
			nvc.put(JobStaticConfigValue.InstanceName, jobName + JobStaticConfigValue.SchedulerInstanceName);
			SchedulerFactory schedulerFactory = new StdSchedulerFactory();//nvc
			jobStruct.sched = schedulerFactory.getScheduler();

			Map<String, Object> dataMap = new HashMap<String, Object>();
			dataMap.put(JobStaticConfigValue.JobName, jobName);
			dataMap.put(JobStaticConfigValue.JobParam, dicParams);

			// 初始化作业器
			JobDetail jobDetail = JobBuilder.newJob(JobProcess.class)
					.withIdentity(new JobKey(jobName, jobName + JobStaticConfigValue.Group))
					.setJobData(new JobDataMap(dataMap)).build();

			CronScheduleBuilder builder = CronScheduleBuilder
					.cronSchedule(dicParams.get(JobStaticConfigValue.JobCronExpr));
			// 初始化作业触发器
			// 触发周期，采用配置模式，针对不同的作业新增配置不同的周期
			Trigger trigger = TriggerBuilder.newTrigger()
					.withIdentity(new TriggerKey(jobName + JobStaticConfigValue.Trigger,
							jobName + JobStaticConfigValue.Group))
					// .StartNow()
					.withSchedule(builder).build();

			jobStruct.sched.scheduleJob(jobDetail, trigger);
			// jobStruct.sched.Start();

			return jobStruct;

		} catch (SchedulerException e) {
			e.printStackTrace();
			return null;
		}
	}

	/// <summary>
	/// 新增作业
	/// </summary>
	/// <param name="jobName">作业名称</param>
	/// <param name="dicParams">作业参数集合</param>
	/// <returns>是否添加成功</returns>
	public static boolean AddJob(String jobName, Map<String, String> dicParams) {
		if (!StringUtils.isEmpty(jobName)) {
			String jobCnName = JobStatus.None.getName();
			if (jobs.containsKey(jobName)) {
				if (jobs.get(jobName) != null) {
					return true;
				} else {
					jobs.remove(jobName);
					JobStruct jobstruct = Instance(jobName, jobCnName, dicParams);
					if (jobstruct != null) {
						jobs.put(jobName, jobstruct);
						return true;
					}
				}
			} else {
				JobStruct jobstruct = Instance(jobName, jobCnName, dicParams);
				if (jobstruct != null) {
					jobs.put(jobName, jobstruct);
					if (dicParams.get(JobStaticConfigValue.JobStatus).equals(String
							.valueOf((JobStatus.Running.getIndex())))) {
						StartJob(jobName);
					}

					return true;
				}
			}
		}
		return false;
	}

	/// <summary>
	/// 移除作业
	/// </summary>
	/// <param name="jobName">作业名称</param>
	/// <returns>是否移除</returns>
	public static boolean RemoveJob(String jobName) {
		if (StringUtils.isEmpty(jobName)) {
			return false;
		}
		boolean isHasRemove = false;
		try {
			if (jobs.containsKey(jobName)) {
				JobStruct jobStruct = jobs.get(jobName);
				if (jobStruct != null && jobStruct.sched != null && !jobStruct.sched.isShutdown())// 判断作业是否存在并且是否还活动着
				{
					jobStruct.sched.shutdown(false);
				}
				jobs.remove(jobName);
				isHasRemove = true;
			} else {
				// String log = "jobdict中没有找到该job";

				// Log4NetHelper.WriteRuningLog(jobName + "---移除成功：" + log);
			}
			if (isHasRemove) {
				// Log4NetHelper.WriteRuningLog(jobName + "---移除成功");
			}

		} catch (Exception e) {
			// Log4NetHelper.WriteExceptionLog(jobName+"---移除job失败：",e);
		}

		return isHasRemove;
	}

	/// <summary>
	/// 启动作业
	/// </summary>
	/// <param name="jobName">作业名称</param>
	/// <returns>是否启动</returns>
	public static boolean StartJob(String jobName) {
		if (StringUtils.isEmpty(jobName)) {
			return false;
		}

		try {

			if (jobs.containsKey(jobName) && jobs.get(jobName) != null && jobs.get(jobName).sched != null) {
				jobs.get(jobName).sched.start();// 启动作业
				LogHelper.LogWarning(jobName+"启动");
				if (jobs.get(jobName).sched.isStarted())// 检测作业是否运行中
				{
					jobs.get(jobName).JobCnName = JobStatus.Running.getName();// 修改当前的作业状态为正在运行
					LogHelper.LogWarning(jobName+"启动成功");
					return true;
				}
			}
			String log = jobs.containsKey(jobName) ? "jobdict中有该"+jobName+"，jobstruct 无效" : "jobdict中没有找到该"+jobName;
			LogHelper.LogWarning(log);
			// Log4NetHelper.WriteRuningLog(jobName + "---启动失败：" + log);

		} catch (Exception e) {
			// Log4NetHelper.WriteExceptionLog(jobName + "---启动失败startjob: ", e);
		}
		return false;
	}

	/// <summary>
	/// 停止作业
	/// </summary>
	/// <param name="jobName">作业名称</param>
	/// <returns>是否停止</returns>
	public static boolean StopJob(String jobName) {
		if (StringUtils.isEmpty(jobName)) {
			return false;
		}

		try {

			if (jobs.containsKey(jobName) && jobs.get(jobName) != null && jobs.get(jobName).sched != null) {
				jobs.get(jobName).sched.shutdown(false);
				jobs.replace(jobName, Instance(jobName, JobStatus.None.getName(), jobs.get(jobName).dicParams));
				// Log4NetHelper.WriteRuningLog(jobName+"---停止成功");
				return true;
			}

			String log = jobs.containsKey(jobName) ? "jobdict中有该job，jobstruct 无效" : "jobdict中没有找到该job";

			// Log4NetHelper.WriteRuningLog(jobName + "---停止失败：" + log);
		} catch (Exception e) {
			// Log4NetHelper.WriteExceptionLog(jobName +"--- 停止失败stop job",e);
		}

		return false;
	}

	/// <summary>
	/// 获取作业状态
	/// </summary>
	/// <param name="JobName">作业名称</param>
	/// <returns>作业名称</returns>
	public static String GetCnName(String JobName) {
		String rtnName = "";
		if (jobs.containsKey(JobName)) {
			rtnName = jobs.get(JobName).JobCnName;
		}

		return StringUtils.isEmpty(rtnName) ? JobName : rtnName;
	}

	public static ArrayList<String> GetTaskeFireTime(String CronExpressionString, int numTimes) {
		if (numTimes <= 0) {
			numTimes = 5;
		}

		ArrayList<String> list = new ArrayList<String>();
		try {
			CronScheduleBuilder builder = CronScheduleBuilder.cronSchedule(CronExpressionString);

			Trigger trigger = TriggerBuilder.newTrigger().withSchedule(builder).build();
			List<Date> dates = TriggerUtils.computeFireTimes((OperableTrigger) trigger, null, numTimes);

			SimpleDateFormat bartDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

			for (Date dtf : dates) {
				list.add(bartDateFormat.format(dtf));
			}
		} catch (Exception e) {

		}
		// 时间表达式

		return list;
	}

	public static Map<String, JobStruct> getExsitJob() {
		return jobs;
	}
}
