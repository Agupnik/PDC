package Counter;

import java.util.concurrent.locks.ReentrantLock;

public class CounterObjectLock extends Counter
{
    private final ReentrantLock locker = new ReentrantLock();

    @Override
    public void increment()
    {
        locker.lock();
        try{
            count++;
        }
        finally{
            locker.unlock();
        }
    }

    @Override
    public synchronized void decrement()
    {
        locker.lock();
        try{
            count--;
        }
        finally{
            locker.unlock();
        }
    }
}
