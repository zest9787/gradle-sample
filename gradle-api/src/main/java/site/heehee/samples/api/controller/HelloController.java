package site.heehee.samples.api.controller;

import lombok.RequiredArgsConstructor;
import org.apache.catalina.core.ApplicationPushBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import site.heehee.samples.common.event.LoggingEvent;
import site.heehee.samples.common.event.publisher.LoggingEventPublisher;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class HelloController {

    private final Logger logger = LoggerFactory.getLogger(HelloController.class);

    private final LoggingEventPublisher loggingEventPublisher;

    @GetMapping("/hello")
    public String hello() {
        loggingEventPublisher.publish("Logging Event 전달");
        return "hello";
    }
}
