package stackjava.com.quartzexample;

import java.util.Date;

import org.quartz.*;

public class MyJob implements Job{

	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		System.out.println("Run my Job: "+ new Date());
	}

}
