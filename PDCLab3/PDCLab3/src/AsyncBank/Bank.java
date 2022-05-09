package AsyncBank;

public interface Bank
{
    int TESTS = 10000;

    void transfer(int from, int to, int amount) throws InterruptedException;

    void test();

    int size();
}
