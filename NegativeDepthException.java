/**
 * Created by nathan on 4/10/14.
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
