/**
 *IncorrectProtocolError is an exception that is thrown if the user supplies
 * a URL that is not of the HTTP or HTTPS protocols
 */
public class IncorrectProtocolError extends Exception {

    public IncorrectProtocolError(String message){
        super(message);
    }

    public IncorrectProtocolError(){
        super();
    }

    public IncorrectProtocolError(Throwable cause){
        super(cause);
    }

    public IncorrectProtocolError(String message, Throwable cause){
        super(message, cause);
    }
}
