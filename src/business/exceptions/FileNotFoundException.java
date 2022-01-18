package business.exceptions;

public class FileNotFoundException extends Exception {

    public FileNotFoundException(String message){
        CustomMessageException(message);
    }

    public void CustomMessageException(String message){
        System.out.println(message);
    }
}
