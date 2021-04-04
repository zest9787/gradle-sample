package site.heehee.samples.common.event.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import site.heehee.samples.common.event.LoggingEvent;

@Component
public class LoggingEventListener implements ApplicationListener<LoggingEvent> {

    public static final Logger logger = LoggerFactory.getLogger(LoggingEventListener.class);

    @Override
    public void onApplicationEvent(LoggingEvent event) {
        try {
            Thread.sleep(5000);
            logger.info("Event Logging " + event.getEventName());
            System.out.println("Event Logging " + event.getEventName());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
