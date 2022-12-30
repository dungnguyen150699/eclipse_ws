package stackjava.com.quartzexample;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

public class Demo2 {
	public static void main(String[] args) throws SchedulerException {
		Trigger trigger = TriggerBuilder.newTrigger().withIdentity("triggerName", "group1")
				.withSchedule(CronScheduleBuilder.cronSchedule("0/5 1-30 * * * ?")).build();

		JobDetail job = JobBuilder.newJob(MyJob.class).withIdentity("jobName", "group1").build();

		Scheduler scheduler = new StdSchedulerFactory().getScheduler();
		scheduler.start();
		scheduler.scheduleJob(job, trigger);

	}
}
