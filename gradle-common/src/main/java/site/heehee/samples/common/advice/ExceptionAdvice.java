package site.heehee.samples.common.advice;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import site.heehee.samples.common.advice.exception.BaseUserNotFoundException;
import site.heehee.samples.common.advice.exception.NotHaveAccessTokenException;
import site.heehee.samples.common.model.response.CommonResult;
import site.heehee.samples.common.service.ResponseService;

import javax.servlet.http.HttpServletRequest;

@RestControllerAdvice
@RequiredArgsConstructor
@Slf4j
public class ExceptionAdvice {

    private final ResponseService responseService;
    private final MessageSource messageSource;

    private String getMessage(String code) {
        return getMessage(code, null);
    }

    private String getMessage(String code, Object[] args) {
        return messageSource.getMessage(code, args, LocaleContextHolder.getLocale());
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    protected CommonResult baseException(HttpServletRequest request,  Exception e) {
        return responseService.getFailResult(Integer.valueOf(getMessage("unKnown.code")),
                getMessage("unKnown.msg"));
    }

    @ExceptionHandler(BaseUserNotFoundException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    protected CommonResult baseUserNotFoundException(HttpServletRequest request,  BaseUserNotFoundException e) {
        String lang = LocaleContextHolder.getLocale().getLanguage();
        log.debug("lang==" + lang);
        log.info("userNotFound.code : {}", getMessage("userNotFound.code"));
        return responseService.getFailResult(Integer.valueOf(getMessage("userNotFound.code")),
                getMessage("userNotFound.msg"));
    }

    @ExceptionHandler(NotHaveAccessTokenException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected CommonResult notHanvAccessTokenException(HttpServletRequest request,  NotHaveAccessTokenException e) {
        return responseService.getFailResult(Integer.valueOf(getMessage("NotHaveAccessTokenException.code")),
                getMessage("NotHaveAccessTokenException.msg"));
    }
}
