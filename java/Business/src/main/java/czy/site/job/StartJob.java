package czy.site.job;

import java.util.HashMap;

import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import czy.site.common.quartz.JobManagerBase;
import czy.site.common.quartz.JobStaticConfigValue;
import czy.site.model.myjob;
import czy.site.service.jobservice;

@Component // 将工具类声明为spring组件，这个必须不能忘
public class StartJob {
	@Autowired
	private jobservice service;
	public static StartJob startJob;

	@PostConstruct
	public void init() {
		startJob = this;
	}

	public void AddJob() {
		myjob jobObj = new myjob();
		List<myjob> list = startJob.service.selectByCondition(jobObj);

		if (list != null && list.size() > 0) {
			for (myjob item : list) {
				Map<String, String> dicParams = new HashMap<String, String>();
				dicParams.put(JobStaticConfigValue.JobType, String.valueOf(item.getJobtype()));
				dicParams.put(JobStaticConfigValue.JobTriggerId, String.valueOf(item.getId()));
				dicParams.put(JobStaticConfigValue.JobCronExpr, item.getJobcron());
				dicParams.put(JobStaticConfigValue.JobStatus, String.valueOf(item.getJobstatus()));

				JobManagerBase.AddJob(item.getJobname(), dicParams);
			}
		}

	}
}
