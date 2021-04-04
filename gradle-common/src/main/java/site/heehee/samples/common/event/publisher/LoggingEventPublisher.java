package site.heehee.samples.common.event.publisher;

import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import site.heehee.samples.common.event.LoggingEvent;

@RequiredArgsConstructor
@Component
public class LoggingEventPublisher {

    private final ApplicationEventPublisher applicationEventPublisher;

    @Async
    @EventListener
    public void publish(final String message) {
        System.out.println("LoggingEventPublisher event .");
        LoggingEvent event = new LoggingEvent(this,"Event 전달");
        applicationEventPublisher.publishEvent(event);
    }
}
