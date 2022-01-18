package business.exceptions;

public class WrongInputException extends Exception {

    public WrongInputException(String message){
        CustomMessageException(message);
    }

    public void CustomMessageException(String message){
        System.out.println(message);
    }

}

