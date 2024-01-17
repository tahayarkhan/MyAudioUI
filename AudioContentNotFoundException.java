//create excpetion classes
public class AudioContentNotFoundException extends RuntimeException{
	public AudioContentNotFoundException(){
	}
	public AudioContentNotFoundException(String message){
        super(message);
	}
}