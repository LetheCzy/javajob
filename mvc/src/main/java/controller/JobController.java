package controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;

import czy.site.common.log.LogHelper;
import czy.site.common.quartz.JobManagerBase;
import czy.site.common.quartz.JobStaticConfigValue;
import czy.site.common.quartz.JobStruct;
import czy.site.model.JobStatus;
import czy.site.model.myjob;
import czy.site.service.jobservice;
import model.ComReqModel;
import model.ExeResultModel;
import model.JobSearchModel;
import model.ResponseModel;

@Controller
public class JobController {

	@Autowired
	private jobservice service;

	@RequestMapping(value = "/job/joblist")
	public String getjoblistpage() {
		LogHelper.LogInfo("LogInfojoblist");
		return "/job/joblist";
	}

	@RequestMapping(value = "/job/jobdetail")
	public String getjobdetail(String id, Model model) {
		model.addAttribute("id", id);
		LogHelper.LogWarning("LogWarningjobdetail");
		return "/job/jobdetail";
	}

	@RequestMapping(value = "/job/cron")
	public String Cron() {
		LogHelper.LogError("LogErrorcron");
		return "/job/cron";
	}

	@RequestMapping(value = "/job/getjoblist", method = RequestMethod.GET)
	@ResponseBody
	public ResponseModel<List<myjob>> getjoblist(JobSearchModel model, ComReqModel comModel) {

		myjob jobObj = new myjob();
		if (model != null) {
			jobObj.setJobname(model.getJobname());
			jobObj.setJobstatus(Integer.parseInt(model.getJobstatus()));

		}
		// String tString = JSON.toJSONString(jobObj);
		List<myjob> t = service.selectByCondition(jobObj);

		ResponseModel<List<myjob>> list = new ResponseModel<List<myjob>>(t);
		if (t != null) {
			list.setTotalCount(t.size());

		}

		return list;
	}

	@RequestMapping(value = "/job/getjobdetail", method = RequestMethod.GET)
	@ResponseBody
	public myjob getjobdetail(String id) {

		myjob jobObj = new myjob();
		if (!StringUtils.isEmpty(id) && Integer.parseInt(id) > 0) {
			jobObj = service.selectById(Integer.parseInt(id));
		}

		return jobObj;
	}

	@RequestMapping(value = "/job/savejobdata", method = RequestMethod.POST)
	@ResponseBody
	public ExeResultModel savejobdata(@RequestBody(required = false) myjob model) {

		ExeResultModel jobObj = new ExeResultModel();
		boolean success = false;
		if (model != null) {
			if (StringUtils.isEmpty(model.getJobcron())) {
				jobObj = new ExeResultModel(success, "cron必设");
				return jobObj;
			}
			if (StringUtils.isEmpty(model.getJobname())) {
				jobObj = new ExeResultModel(success, "job名称必填");
				return jobObj;
			}
			// if (model.getJobstatus() < 1) {
			// jobObj = new ExeResultModel(success, "job状态必选");
			// return jobObj;
			// }
			if (model.getJobtype() < 1) {
				jobObj = new ExeResultModel(success, "job类型必选");
				return jobObj;
			}
			if (service.selectExsitByNameAndId(model.getJobname(), model.getId()) > 0) {
				jobObj = new ExeResultModel(success, "job已存在");
				return jobObj;
			}
			if (model.getId() > 0) {
				model.setLastmodifydate(new Date());
				model.setLastmodifyname("czy");
				model.setLastmodifynum("11808");
				success = service.updateByPrimaryKeySelective(model) > 0;
			} else {
				model.setCreatdate(new Date());
				model.setCreatorname("czy");
				model.setCreatornum("11808");
				model.setLastmodifydate(new Date());
				model.setLastmodifyname("czy");
				model.setLastmodifynum("11808");
				model.setJobstatus(JobStatus.None.getIndex());
				success = service.insert(model) > 0;
			}
		}

		// return Json(new { IsSuccess = success, Msg = success ? "保存成功" : "保存失败" });
		jobObj = new ExeResultModel(success, success ? "保存成功" : "保存失败");
		return jobObj;
	}

	@RequestMapping(value = "/job/changestatus", method = RequestMethod.POST)
	@ResponseBody
	public ExeResultModel ChangeJobStatus(@RequestBody myjob reqModel) {
		boolean success = false;
		ExeResultModel resp = new ExeResultModel();
		if (reqModel != null && reqModel.getId() > 0) {
			myjob model = service.selectById(reqModel.getId());
			if (model != null) {
				model.setLastmodifydate(new Date());
				model.setLastmodifyname("czy");
				model.setLastmodifynum("11808");

				Map<String, String> dicParams = new HashMap<String, String>();
				dicParams.put(JobStaticConfigValue.JobType, String.valueOf(model.getJobtype()));
				dicParams.put(JobStaticConfigValue.JobTriggerId, String.valueOf(model.getId()));
				dicParams.put(JobStaticConfigValue.JobCronExpr, model.getJobcron());
				dicParams.put(JobStaticConfigValue.JobStatus, String.valueOf(reqModel.getJobstatus()));

				JobStatus jobStatus = JobStatus.getEnum(model.getJobstatus());
				JobStatus reqstatus = JobStatus.getEnum(reqModel.getJobstatus());
				if (reqstatus == JobStatus.Running && jobStatus == JobStatus.None) {
					model.setJobstatus(JobStatus.Running.getIndex());
					success = service.updateByPrimaryKeySelective(model) > 0;
					if (success) {
						JobManagerBase.AddJob(model.getJobname(), dicParams);
					}
				}
				if (reqstatus == JobStatus.ShutDown && jobStatus == JobStatus.None) {
					model.setJobstatus(JobStatus.ShutDown.getIndex());
					success = service.updateByPrimaryKeySelective(model) > 0;
					if (success) {
						JobManagerBase.RemoveJob(model.getJobname());
					}
				}
				if (reqstatus == JobStatus.Running && jobStatus == JobStatus.ShutDown) {
					model.setJobstatus(JobStatus.Running.getIndex());
					success = service.updateByPrimaryKeySelective(model) > 0;
					if (success) {
						JobManagerBase.AddJob(model.getJobname(), dicParams);
					}
				} else if (reqstatus == JobStatus.ShutDown && jobStatus == JobStatus.Running) {
					model.setJobstatus(JobStatus.ShutDown.getIndex());
					success = service.updateByPrimaryKeySelective(model) > 0;
					if (success) {
						JobManagerBase.RemoveJob(model.getJobname());
					}
				} else if (reqstatus == JobStatus.ShutDown && jobStatus == JobStatus.Suspend) {
					model.setJobstatus(JobStatus.ShutDown.getIndex());
					success = service.updateByPrimaryKeySelective(model) > 0;
					if (success) {
						JobManagerBase.RemoveJob(model.getJobname());
					}
				}
			}
		}

		resp.setSuccess(success);
		resp.setMsg(success ? "操作成功" : "非法操作");
		return resp;
	}

	@RequestMapping(value = "/job/gettaskefiretime", method = RequestMethod.GET)
	@ResponseBody
	public List<String> GetTaskeFireTime(String CronExpression) {
		List<String> times = new ArrayList<String>();
		if (!StringUtils.isEmpty(CronExpression)) {
			times = JobManagerBase.GetTaskeFireTime(CronExpression.trim(), 5);
		}

		return times;
	}
	
	@RequestMapping(value = "/job/getexsitjob", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, JobStruct> GetExsitJob(String CronExpression) {
		return JobManagerBase.getExsitJob();
	}
}
