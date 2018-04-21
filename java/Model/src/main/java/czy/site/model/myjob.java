package czy.site.model;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;

public class myjob {
	private int id = 0;
	private int jobtype = 0;
	private String jobname = "";
	private String jobcron = "";
	private int jobstatus = 0;
	private String jobdescription = "";
	private String creatorname = "";
	private String creatornum = "";
	private Date creatdate;
	private String lastmodifyname = "";
	private String lastmodifynum = "";
	private Date lastmodifydate;
	//private String jobstatusdesc = "";
	/*DateFormat mediumDateFormat = DateFormat.getDateTimeInstance
       		(DateFormat.MEDIUM,DateFormat.MEDIUM); 
	*/
	public myjob() {
		Calendar cal = Calendar.getInstance();
		cal.set(1900, 1, 1);
		this.creatdate = cal.getTime();
		this.lastmodifydate = cal.getTime();
	}

	public int getId() {
		return this.id;
	};

	public void setId(int Id) {
		this.id = Id;
	};

	public int getJobtype() {
		return this.jobtype;
	};

	public void setJobtype(int jobtype) {
		this.jobtype = jobtype;
	};

	public String getJobname() {
		return this.jobname;
	};

	public void setJobname(String jobname) {
		this.jobname = jobname;
	};

	public String getJobcron() {
		return this.jobcron;
	};

	public void setJobcron(String jobcron) {
		this.jobcron = jobcron;
	};

	public int getJobstatus() {
		return this.jobstatus;
	};

	public void setJobstatus(int jobstatus) {
		this.jobstatus = jobstatus;
	};

	public String getJobdescription() {
		return this.jobdescription;
	};

	public void setJobdescription(String jobdescription) {
		this.jobdescription = jobdescription;
	};

	public String getCreatorname() {
		return this.creatorname;
	};

	public void setCreatorname(String creatorname) {
		this.creatorname = creatorname;
	};

	public String getCreatornum() {
		return this.creatornum;
	};

	public void setCreatornum(String creatornum) {
		this.creatornum = creatornum;
	};

	@JSONField(format="yyyy-MM-dd HH:mm:ss")
	public Date getCreatdate() {
		
		return this.creatdate;
	};

	public void setCreatdate(Date creatdate) {
		this.creatdate = creatdate;
	};

	public String getLastmodifyname() {
		return this.lastmodifyname;
	};

	public void setLastmodifyname(String lastmodifyname) {
		this.lastmodifyname = lastmodifyname;
	};

	public String getLastmodifynum() {
		return this.lastmodifynum;
	};

	public void setLastmodifynum(String lastmodifynum) {
		this.lastmodifynum = lastmodifynum;
	};

	@JSONField(format="yyyy-MM-dd HH:mm:ss")
	public Date getLastmodifydate() {
		return this.lastmodifydate;
	};
	
	public void setLastmodifydate(Date lastmodifydate) {
		this.lastmodifydate = lastmodifydate;
	};
	
	public String getJobStatusDesc() {
		return JobStatus.getName(this.jobstatus);
	}
	
	public String getJobTypeDesc() {
		return ProcessType.getName(this.jobtype);
	}
}
