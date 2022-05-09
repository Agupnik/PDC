package AsyncBank;

import java.util.concurrent.locks.ReentrantLock;

public class Main {
    public static final int ACCOUNTS = 10;
    public static final int INITIAL_BALANCE = 10000;

    public static void main(String[] args)
    {
        //ReentrantLock lock = new ReentrantLock();
        Bank bank = new BankSynchronized(ACCOUNTS, INITIAL_BALANCE);
        for (int i = 0; i < ACCOUNTS; i++)
        {
            //Thread thread = new TransferThreadSafe(bank, i, INITIAL_BALANCE, lock);
            Thread thread = new TransferThread(bank, i, INITIAL_BALANCE);
            thread.setPriority(Thread.NORM_PRIORITY + i % 2);
            thread.start();
        }
    }
}
