import java.util.ArrayList;

public class Termite {
    private int state;
    private String dir;
    private ArrayList<Command> tasks;
    private Pixel currentPos;
    public Termite(ArrayList<Command> inputTasks) {
        this.state = 0;
        this.dir = "";
        tasks=new ArrayList<>(inputTasks);
        currentPos=new Pixel(0,0);
    }
    public void executeTasks(PixelDrawer frame){
        if(tasks.get(0).getState()==state){
            if(tasks.get(0).getCurrentCell()==currentPos.getState()){
                dir=tasks.get(0).getDir();
                if(dir.equals("N")){
                    currentPos.setX(currentPos.getX()+1);
                    currentPos.setY(currentPos.getY()+1);
                }
                frame.getPixelList().get(currentPos.getX()).get(currentPos.getY()).setState(1);
            }
        }
    }
}
