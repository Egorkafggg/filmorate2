package exception;

import org.apache.logging.log4j.message.Message;

public class ValidateException extends RuntimeException {
    public ValidateException(String message){
        super(message);
    }
}
