public class Command {
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
    public int getState() {
        return state;
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
