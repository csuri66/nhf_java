import java.io.Serializable;

public class Command implements Serializable {
    private int state;
    private int currentCell;
    private String dir;
    private int draw;
    private int nextState;
    public Command(int state, int currentCell, String dir, int draw, int nextState) {
        this.state = state;
        this.currentCell = currentCell;
        this.dir = dir;
        this.draw = draw;
        this.nextState = nextState;
    }
    public Command(Command copy){
        this.state = copy.state;
        this.currentCell = copy.currentCell;
        this.dir = copy.dir;
        this.draw = copy.draw;
        this.nextState = copy.nextState;
    }

    public int getState() {
        return state;
    }
    public String toString(){
        return "("+state+"-"+currentCell+"-"+dir+"-"+draw+"-"+nextState+")";
    }
    public int getCurrentCell() {
        return currentCell;
    }
    public String getDir() {
        return dir;
    }
    public int getDraw() {
        return draw;
    }
    public int getNextState() {
        return nextState;
    }
}
