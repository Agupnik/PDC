package Symbols;

public class PrinterUnsync implements IPrinter {

    private int characterCount;
    public void Write(char character)
    {
        System.out.print(character);
        characterCount++;
        if (characterCount % 50 == 0) {
            System.out.print('\n');
        }
    }
}
