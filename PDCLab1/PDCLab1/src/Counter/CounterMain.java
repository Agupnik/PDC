package Counter;

public class CounterMain {
    private static final int NUMBER_OF_OPERATIONS = 100000;
    public static void main(String[] args) throws InterruptedException
    {
        Counter counter = new CounterObjectLock();
        //Counter counter = new Counter();
        //Counter counter = new CounterSynchronizedMethod();
        //Counter counter = new CounterSynchronizedBlock();
        Thread incrementThread = new IncrementThread(counter, NUMBER_OF_OPERATIONS);
        Thread decrementThread = new DecrementThread(counter, NUMBER_OF_OPERATIONS);
        Thread incrementThread2 = new IncrementThread(counter, NUMBER_OF_OPERATIONS);
        Thread decrementThread2 = new DecrementThread(counter, NUMBER_OF_OPERATIONS);


        long startTime = System.nanoTime();
        incrementThread.start();
        decrementThread.start();
        incrementThread2.start();
        decrementThread2.start();
        incrementThread.join();
        decrementThread.join();
        incrementThread2.join();
        decrementThread2.join();
        long elapsedTime = System.nanoTime() - startTime;

        System.out.println("Total execution time in millis: "
                + elapsedTime/1000000);

        System.out.println(counter.getCount());
    }
}
