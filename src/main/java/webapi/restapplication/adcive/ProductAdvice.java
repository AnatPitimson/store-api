package webapi.restapplication.adcive;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import webapi.restapplication.exception.ProductNotFoundException;

@ControllerAdvice//  ProductNotFoundException במידה ויופיע
//כך נציג ללקוח הודעה מProductNotFoundException
public class ProductAdvice {
    @ExceptionHandler(ProductNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody//להעביר ללקוח את ההודעה של סוג הException
    String productNotFoundHandler(ProductNotFoundException notFound) {
        return notFound.getMessage();
    }
}

//an advice class is a component that intercepts and handles exceptions that are thrown by the application.
