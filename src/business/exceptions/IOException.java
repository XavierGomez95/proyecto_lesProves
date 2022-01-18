package business.exceptions;

public class IOException {

    public IOException(String message){
        CustomMessageException(message);
    }

    public void CustomMessageException(String message){
        System.out.println(message);
    }
}
