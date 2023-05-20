package webapi.restapplication.exception;

public class OrderNotFoundException extends RuntimeException{

    public OrderNotFoundException(Long id)
    {
        // RuntimeExceptionכלומר לגשת לקונסטרקטור של
        super("There is no order corresponding to id "+ id);
    }

}
