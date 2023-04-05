import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Random;


@Data
public class Robot implements Runnable{

    private String name;
    private boolean running = true;
    Exploration explore = new Exploration();

    public Robot(String name){
        this.name=name;
    }

    public void run(){
        while(running){
            boolean visit = false;
            while(!visit){
                Random random = new Random();
                int x = random.nextInt(5);
                int y = random.nextInt(5);
                visit = explore.getMap().visit(x, y, this);
            }
            System.out.println(this.name);
            System.out.println(this.getExplore().getMap().toString());
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            running=false;
            for(int i = 0; i<5;i++){
                for(int j = 0; j< 5; j++)
                {
                    if(!this.getExplore().getMap().isComplet()){
                        running=true;
                    }
                }
            }
        }

    }
}
