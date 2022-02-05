package Symbols;

public class PrinterSync implements IPrinter {
    private final Object lock = new Object();
    private char currentCharacter;
    private int characterCount;

    public void Write(char character)
    {
        synchronized (lock)
        {
            if (currentCharacter == character)
            {
                try
                {
                    lock.wait();
                } catch (InterruptedException ignored) {}
            }
            System.out.print(character);
            characterCount++;
            if (characterCount % 50 == 0) {
                System.out.print('\n');
            }
            currentCharacter = character;
            lock.notify();
        }
    }
}
