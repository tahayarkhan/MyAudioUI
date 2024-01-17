//create excpetion classes
public class AudioContentExistsException extends RuntimeException{
	public AudioContentExistsException(){
	}
	public AudioContentExistsException(String message){
        super(message);
	}
}