package org.fast.core.plugin.quartz.job;

import org.fast.core.plugin.quartz.worker.UpdateStatsWorker;
import org.fast.core.plugin.quartz.worker.Worker;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class UpdateStatsJob implements Job {

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		Worker worker = new UpdateStatsWorker();
		worker.execute();
	}

}
