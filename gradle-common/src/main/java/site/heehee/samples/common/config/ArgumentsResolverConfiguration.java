package site.heehee.samples.common.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import site.heehee.samples.common.resolver.TokenResolver;

import java.util.List;

@RequiredArgsConstructor
@Configuration
public class ArgumentsResolverConfiguration implements WebMvcConfigurer {

    private final TokenResolver tokenResolver;

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(tokenResolver);
    }
}
