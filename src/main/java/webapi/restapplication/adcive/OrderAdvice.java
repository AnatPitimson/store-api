package webapi.restapplication.adcive;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import webapi.restapplication.exception.OrderNotFoundException;

@ControllerAdvice
public class OrderAdvice {
    @ExceptionHandler(OrderNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    String orderNotFoundHandler(OrderNotFoundException notFound) {
        return notFound.getMessage();
    }
}
