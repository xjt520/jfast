package org.fast.core.plugin.quartz.job;

import static org.quartz.CronScheduleBuilder.cronSchedule;
import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;
import java.io.IOException;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import org.quartz.Job;
import org.quartz.JobDetail;
import org.quartz.Trigger;
import com.google.common.collect.Maps;
import com.jfinal.log.Log;

@SuppressWarnings("unchecked")
public class JobManager {

	private static Log log = Log.getLog(JobManager.class);
	
	private Map<JobDetail, Trigger> jobs;
	
	public JobManager() {
		jobs = Maps.newHashMap();
		Properties props = new Properties();
		try {
			props.load(this.getClass().getClassLoader().getResourceAsStream("cron.properties"));
			for (Entry<Object, Object> entry : props.entrySet()) {
				String jobname = (String) entry.getKey();
				String executionInfo = (String) entry.getValue();
				log.info("Parsing {"+jobname+"} using {"+executionInfo+"}");
				Class<Job> jobClass;
				try {
					jobClass = (Class<Job>) Class.forName(jobname);
					JobDetail jobDetail = newJob(jobClass).withIdentity(jobname, jobname).build();//cronSchedule("0 " + executionInfo)
					Trigger trigger = newTrigger().withIdentity("trigger"+jobname, jobname).withSchedule(cronSchedule(executionInfo)).build();
					jobs.put(jobDetail, trigger);
				} catch (ClassNotFoundException e) {
					log.error(e.getMessage());
				}
			}
		} catch (IOException e) {
			log.error(e.getMessage());
		}
		
	}
	
	public Map<JobDetail, Trigger> getJobs() {
		return jobs;
	}
	
}
