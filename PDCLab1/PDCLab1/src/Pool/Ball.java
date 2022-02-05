package Pool;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;
import java.util.Random;

class Ball {
    private final BallCanvas canvas;
    private static final int XSIZE = 20;
    private static final int YSIZE = 20;
    private int x = 0;
    private int y= 0;
    private int dx = 2;
    private int dy = 2;
    private final int[] xPocketsArray;
    private final int[] yPocketsArray;
    private boolean alive = true;
    private Color color = Color.darkGray;
    public Priority priority = Priority.Low;

    public Ball(BallCanvas c, int[] xPocketsArray, int[] yPocketsArray){
        this.canvas = c;
        this.xPocketsArray = xPocketsArray;
        this.yPocketsArray = yPocketsArray;

        if(Math.random()<0.5){
            x = new Random().nextInt(this.canvas.getWidth());
            y = 0;
        }else{
            x = 0;
            y = new Random().nextInt(this.canvas.getHeight());
        }
    }

    public Ball(BallCanvas c, int[] xPocketsArray, int[] yPocketsArray, Color color){
        this(c, xPocketsArray, yPocketsArray);
        this.color = color;
    }

    public Ball(BallCanvas c, int[] xPocketsArray, int[] yPocketsArray, Priority priority) {
        this(c, xPocketsArray, yPocketsArray);
        this.priority = priority;
        if(priority == Priority.High){
            color = Color.RED;
        }
        else if(priority == Priority.Low){
            color = Color.BLUE;
        }
    }

    public Ball(BallCanvas c, int[] xPocketsArray, int[] yPocketsArray, Priority priority, int x, int y) {
        this(c, xPocketsArray, yPocketsArray);
        this.x = x;
        this.y = y;
        this.priority = priority;
        if(priority == Priority.High){
            color = Color.RED;
        }
        else if(priority == Priority.Low){
            color = Color.BLUE;
        }
    }

    public static ArrayList<Ball> priorityBallsFactory(
            BallCanvas c,
            int[] xPocketsArray,
            int[] yPocketsArray,
            int lowPriorityCount,
            int highPriorityCount){
        ArrayList<Ball> balls = new ArrayList<>();

        for (int i = 0; i < lowPriorityCount; i++){
            balls.add(new Ball(c, xPocketsArray, yPocketsArray, Priority.Low,
                    c.getWidth()/3 - 60, c.getHeight()/3 - 60));
        }
        for (int i = 0; i < highPriorityCount; i++){
            balls.add(new Ball(c, xPocketsArray, yPocketsArray, Priority.High,
                    c.getWidth()/3 - 60, c.getHeight()/3 - 60));
        }
        return balls;
    }


    public void draw (Graphics2D g2){
        if (this.alive)
        {
            g2.setColor(color);
            g2.fill(new Ellipse2D.Double(x,y,XSIZE,YSIZE));
        }
    }

    public boolean isAlive()
    {
        return alive;
    }

    public void makeDead()
    {
        alive = false;
        canvas.ballsInPocket++;
    }

    public void checkPocketIntersections()
    {
        boolean isInteracted = false;
        for(int i=0; i<xPocketsArray.length; i++){
            int pocketX = xPocketsArray[i];
            int pocketY = yPocketsArray[i];
            double sqrtDistance = Math.pow(x - pocketX, 2) + Math.pow(y - pocketY, 2);
            if (sqrtDistance <= XSIZE * XSIZE) {
                isInteracted = true;
                break;
            }
        }
        if (isInteracted)
        {
            makeDead();
        }
    }

    public void move(){
        x+=dx;
        y+=dy;
        if(x<0){
            x = 0;
            dx = -dx;
        }
        if(x+XSIZE>=this.canvas.getWidth()){
            x = this.canvas.getWidth()-XSIZE;
            dx = -dx;
        }
        if(y<0){
            y=0;
            dy = -dy;
        }
        if(y+YSIZE>=this.canvas.getHeight()){
            y = this.canvas.getHeight()-YSIZE;
            dy = -dy;
        }
        this.canvas.repaint();
    }
}