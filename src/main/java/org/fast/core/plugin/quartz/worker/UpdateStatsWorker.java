package org.fast.core.plugin.quartz.worker;

import com.jfinal.log.Log;


public class UpdateStatsWorker implements Worker {

	Log logger = Log.getLog(UpdateStatsWorker.class);
	
	@Override
	public void execute() {
		logger.info("-------UpdateStatsWorker is worked-------");
	}

}
