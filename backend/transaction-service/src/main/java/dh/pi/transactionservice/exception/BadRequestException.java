package dh.pi.transactionservice.exception;

public class BadRequestException extends Exception{
    public BadRequestException(String message){
        super(message);
    }
}
