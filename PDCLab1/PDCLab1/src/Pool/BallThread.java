package Pool;

public class BallThread extends Thread {
    private Ball b;
    private Thread thread = null;

    public BallThread(Ball ball, Thread thread){
        b = ball;
        this.thread = thread;
        setPriority(getBallPriority(ball));
    }

    public BallThread(Ball ball){
        b = ball;
        setPriority(getBallPriority(ball));
    }

    private int getBallPriority(Ball ball)
    {
        Priority priority = ball.priority;
        return priority == Priority.Low ? 4 : MAX_PRIORITY;
    }
    @Override
    public void run(){
        try{
            if (thread != null)
            {
                thread.join();
            }
            while(b.isAlive()){
                b.move();
                b.checkPocketIntersections();

                System.out.println("Thread name = "
                        + Thread.currentThread().getName());
                Thread.sleep(5);

            }
        } catch(InterruptedException ex){
            System.out.println("Thread finished! Thread name = "
                    + Thread.currentThread().getName());
        }
    }
}