package ProducerConsumer;

public class Main
{
    public static void main(String[] args)
    {
        Drop drop = new Drop(50);
        (new Thread(new Producer(drop, 100))).start();
        (new Thread(new Consumer(drop))).start();
    }
}
