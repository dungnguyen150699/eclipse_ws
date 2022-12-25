package dungnt_ptit.com.securitystandard.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledTasks {
    private static final Logger logger = LoggerFactory.getLogger(ScheduledTasks.class);

    @Scheduled(fixedRate = 2000)
    public void scheduleTaskWithFixedRate() {
        // call send email method here
        logger.info("Send email to producers to inform quantity sold items-- FIXRATE");
    }

    public void scheduleTaskWithFixedDelay() {
    }

    public void scheduleTaskWithInitialDelay() {
    }

    // Link convert sang CRON : https://www.freeformatter.com/cron-expression-generator-quartz.html -> ** Cái này sai
    @Scheduled(cron = "0 */20 0/2 ? * * ") // Cứ vào mỗi ngày giờ chẵn thì chạy Làm như này sẽ đúng nhé
    public void scheduleTaskWithCronExpression() {
        // call send email method here
        logger.info("Send email to producers to inform quantity sold items-- CRON");
    }
}