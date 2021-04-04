package site.heehee.samples.common.event;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class LoggingEvent extends ApplicationEvent {

    private final String eventName;

    public LoggingEvent(Object object, String source) {
        super(object);
        this.eventName = source;
    }

}
