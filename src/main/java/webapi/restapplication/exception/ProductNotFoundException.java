package webapi.restapplication.exception;

public class ProductNotFoundException extends RuntimeException {

    public ProductNotFoundException(Long id)
    {
        // RuntimeExceptionכלומר לגשת לקונסטרקטור של
        super("There is no product corresponding to id "+ id);
    }


}
