package czy.site.common.quartz;

import java.util.Map;

import org.quartz.Scheduler;

public final class JobStruct {
	public String JobName = "";//作业名称
    public String JobCnName = "";//作业状态
    public Map<String, String> dicParams = null;//参数集合
    public Scheduler sched = null;//作业调度器
}
