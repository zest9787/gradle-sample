package site.heehee.samples.common.resolver;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import site.heehee.samples.common.advice.exception.NotHaveAccessTokenException;
import site.heehee.samples.common.annotation.Token;

@RequiredArgsConstructor
@Component
@Slf4j
public class TokenResolver implements HandlerMethodArgumentResolver {

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        boolean isToken = parameter.getParameterAnnotation(Token.class) != null;
        boolean isString = String.class.equals(parameter.getParameterType());
        return isToken && isString;
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        String authorizationHeader = webRequest.getHeader("Authorization");
        log.info("Authorization Header ::: " + authorizationHeader);

        if (authorizationHeader == null) {
            throw new NotHaveAccessTokenException("Access Token이 존재하지 않습니다.");
        }
        String jwtToken = authorizationHeader.substring(7);

        DecodedJWT decodedToken = JWT.decode(jwtToken);
        String username = decodedToken.getClaims().get("user_name").asString();
        log.info("Decoded username is ::: " + username);
        return username;
    }
}
