package site.heehee.samples.api.controller;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import site.heehee.samples.common.annotation.Token;
import site.heehee.samples.common.event.publisher.LoggingEventPublisher;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class HelloController {

    private final Logger logger = LoggerFactory.getLogger(HelloController.class);

    private final LoggingEventPublisher loggingEventPublisher;

    @GetMapping("/hello")
    public String hello(@Token String username) {
        loggingEventPublisher.publish("Logging Event 전달");
        return "hello";
    }
}
