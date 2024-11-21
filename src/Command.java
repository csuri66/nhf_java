import java.io.Serializable;

public class Command implements Serializable {
    private final int state;
    private final int currentCell;
    private final String dir;
    private final int draw;
    private final int nextState;
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
