import java.util.ArrayList;

public class Termite {
    private int state;
    private int dir;
    private ArrayList<Command> tasks;
    private Pixel currentPos;
    public Termite(ArrayList<Command> inputTasks) {
        this.state = 0;
        this.dir = 0;
        tasks=new ArrayList<>(inputTasks);
        currentPos=new Pixel(500,250);
    }
    public void restPos(){
        currentPos=new Pixel(500,250);
    }
    public Pixel getCurrentPos() {
        return currentPos;
    }
    public boolean inBoundary(){
        if(currentPos.getX()>1&&currentPos.getX()<999&&currentPos.getY()>1&&currentPos.getY()<499){
            return true;
        }
        else{
            return false;
        }
    }
    public void setCommands(ArrayList<Command> commands){
        tasks=new ArrayList<>();
        for(int i=0;i<commands.size();i++){
            tasks.add(new Command(commands.get(i)));
        }
    }
    public ArrayList<Command> getCommands(){
        return tasks;
    }
    public void executeTasks(PixelDrawer frame){
        for(int i=0;i<tasks.size();i++){
            if(tasks.get(i).getState()==state){
                if(tasks.get(i).getCurrentCell()==frame.getPixelList().get(currentPos.getX()).get(currentPos.getY()).getState()){
                    if(tasks.get(i).getDir().equals("N")){
                        if(tasks.get(i).getDraw()==1)
                            frame.getPixelList().get(currentPos.getX()).get(currentPos.getY()).setState(1);
                        else{
                            frame.getPixelList().get(currentPos.getX()).get(currentPos.getY()).setState(0);
                        }
                        if(dir==0){
                            currentPos.setY(currentPos.getY()+1);
                        }
                        if(dir==90){
                            currentPos.setX(currentPos.getX()+1);
                        }
                        if(dir==180){
                            currentPos.setY(currentPos.getY()-1);
                        }
                        if(dir==270){
                            currentPos.setX(currentPos.getX()-1);
                        }

                    }else{
                        if(tasks.get(i).getDraw()==1)
                            frame.getPixelList().get(currentPos.getX()).get(currentPos.getY()).setState(1);
                        else{
                            frame.getPixelList().get(currentPos.getX()).get(currentPos.getY()).setState(0);
                        }
                        if(tasks.get(i).getDir().equals("R")){
                            dir+=90;
                        }
                        if(tasks.get(i).getDir().equals("L")){
                            if(dir==0)
                                dir=270;
                            else{
                                dir-=90;
                            }
                        }
                        if(tasks.get(i).getDir().equals("U")){
                            dir+=180;
                        }
                        dir=dir%360;
                    }
                    state=tasks.get(i).getNextState();
                }
            }

        }
    }
}
