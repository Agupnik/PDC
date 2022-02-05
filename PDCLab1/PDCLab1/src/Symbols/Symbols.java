package Symbols;

public class Symbols {
    private static final int NUMBER_OF_SYMBOLS = 100;

    public static void main(String[] args)
    {
        IPrinter writter = new PrinterSync();
        //IPrinter writter = new PrinterUnsync();
        Thread hyphenThread = new SymbolsThread(writter, NUMBER_OF_SYMBOLS, '-');
        Thread pipeThread = new SymbolsThread(writter, NUMBER_OF_SYMBOLS, '|');
        hyphenThread.start();
        pipeThread.start();
    }
}
