package business.exceptions;

public class CustomMessageException extends Exception {

    public CustomMessageException(String message){
        System.out.println(message);
    }

}

