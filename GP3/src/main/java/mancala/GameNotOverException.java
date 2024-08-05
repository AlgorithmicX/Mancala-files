package mancala;
public class GameNotOverException extends Exception{
    public GameNotOverException(){
        super("Game is not OVER YET!");
    }
    public GameNotOverException(String message){
        super(message);
    }
}
