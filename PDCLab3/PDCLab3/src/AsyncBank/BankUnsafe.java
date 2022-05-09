package AsyncBank;

public class BankUnsafe implements Bank
{
    private final int[] accounts;
    private long transactions;

    public BankUnsafe(int numberOfAccounts, int initialBalance)
    {
        accounts = new int[numberOfAccounts];
        int i;
        for (i = 0; i < accounts.length; i++)
        {
            accounts[i] = initialBalance;
        }
        transactions = 0;
    }

    public void transfer(int from, int to, int amount) throws InterruptedException
    {
        accounts[from] -= amount;
        accounts[to] += amount;
        transactions++;
        if (transactions % TESTS == 0)
        {
            test();
        }
    }

    public void test()
    {
        int sum = 0;
        for (int account : accounts)
        {
            sum += account;
        }
        System.out.println("Transactions:" + transactions + " Sum: " + sum);
    }

    public int size()
    {
        return accounts.length;
    }
}
