package Symbols;

public class SymbolsThread extends Thread
{
    private final IPrinter printer;
    private final int numberOfSymbols;
    private final char symbol;

    public SymbolsThread(IPrinter printer, int numberOfSymbols, char symbol)
    {
        this.printer = printer;
        this.numberOfSymbols = numberOfSymbols;
        this.symbol = symbol;
    }

    @Override
    public void run()
    {
        for (int i = 0; i < numberOfSymbols; i++)
        {
            printer.Write(symbol);
        }
    }
}