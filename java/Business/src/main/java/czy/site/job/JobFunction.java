package czy.site.job;

import java.text.SimpleDateFormat;
import java.util.Date;

import czy.site.common.log.LogHelper;

public class JobFunction {
	public void Level1Fun(String jobName) {
		switch (jobName) {
		case "job1":
			SimpleDateFormat bartDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

			LogHelper.LogWarning("W:" + bartDateFormat.format(new Date()));
			// new WriteFile().Write();
			break;
		default:
			break;
		}
	}
}
