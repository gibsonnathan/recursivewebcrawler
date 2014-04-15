/**
 * Created by nathan on 4/10/14.
 *
 * NegativeDepthException is an exception that is thrown when a user supplies
 * a negative depth argument from the command line
 */
public class NegativeDepthException extends Exception {

    public NegativeDepthException(String message){
        super(message);
    }

    public NegativeDepthException(){
        super();
    }

    public NegativeDepthException(Throwable cause){
        super(cause);
    }

    public NegativeDepthException(String message, Throwable cause){
        super(message, cause);
    }
}
