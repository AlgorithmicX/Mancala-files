package mancala;
public class PitNotFoundException extends Exception {
    public PitNotFoundException(){
        super("Pit is invalid.");
    }
    public PitNotFoundException(String message){
        super(message);
    }
}
